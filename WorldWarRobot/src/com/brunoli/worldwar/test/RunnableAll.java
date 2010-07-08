package com.brunoli.worldwar.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brunoli.worldwar.beans.Building;
import com.brunoli.worldwar.beans.Profile;
import com.brunoli.worldwar.beans.Unit;
import com.brunoli.worldwar.db.DBManager;
import com.brunoli.worldwar.event.EventManager;
import com.brunoli.worldwar.manager.BuildingManager;
import com.brunoli.worldwar.manager.FightManager;
import com.brunoli.worldwar.manager.MissionManager;
import com.brunoli.worldwar.manager.UnitsManager;
import com.brunoli.worldwar.parser.ObtainBuildings;
import com.brunoli.worldwar.parser.ObtainInformation;
import com.brunoli.worldwar.parser.ObtainMission;
import com.brunoli.worldwar.parser.ObtainRestore;
import com.brunoli.worldwar.parser.ObtainUnits;
import com.brunoli.worldwar.util.Menus;
import com.brunoli.worldwar.util.RestoreValue;
import com.brunoli.worldwar.util.UnitType;
import com.brunoli.worldwar.webmanager.HttpGetUrl;

public class RunnableAll implements Runnable {

	public static Double MINIMO_RENTABILIDAD;// rentabilidad de money ganada en
												// batallas
	public static Integer DIFF_POINT_MINIMA;// puntos minimo de dif con el rival
	private String url;
	private String unitDefense;
	private String unitAttack;
	// ////////////////////////////////////////////////
	private ObtainInformation obtainInformation;
	private ObtainMission obtainMission;
	private HttpGetUrl get;
	private ObtainUnits obtainUnits;
	private ObtainBuildings obtainBuildings;
	private FightManager fightManager;
	private DBManager dbManager;
	private ObtainRestore obtainRestore;
	private BuildingManager buildingManager;
	private UnitsManager unitsManager;
	private MissionManager mManager;

	public RunnableAll(String url, String unitAttack, String unitDefense,
			Double MINIMO_RENTABILIDAD, Integer DIFF_POINT_MINIMA) {

		this.url = url;
		this.unitAttack = unitAttack;
		this.unitDefense = unitDefense;
		this.MINIMO_RENTABILIDAD = MINIMO_RENTABILIDAD;
		this.DIFF_POINT_MINIMA = DIFF_POINT_MINIMA;

		Thread t = new Thread(this);
		t.run();
	}

	@Override
	public void run() {
		obtainInformation = new ObtainInformation();
		obtainMission = new ObtainMission();
		obtainUnits = new ObtainUnits();
		dbManager = new DBManager();
		obtainBuildings = new ObtainBuildings();
		fightManager = new FightManager();
		obtainRestore = new ObtainRestore();
		buildingManager = new BuildingManager();
		unitsManager = new UnitsManager();
		mManager = new MissionManager();
		while (true) {
			get = new HttpGetUrl();
			EventManager.getInstance().info("Atacando...");
			Profile profile = null;
			try {
				StringBuilder page = get.getUrl(url);
				profile = new Profile();
				profile.setStartUrl(url);
				// Leo datos
				obtainInformation.leerDatosUsuario(page, profile);
				obtainInformation.leerLinks(page, profile);
				// Leo units
				leerUnits(profile);
				// Leo building
				leerBuildings(profile);
				// Primero chequeo cuantos puntos de batalla tengo,
				// Si tengo menos del total espero hasta tener a todos.
				if (profile.getStaminaMax() > profile.getStaminaCurrent()) {
					// Espero para tener toda la stamina. 2min x stamina
					int dif = profile.getStaminaMax()
							- profile.getStaminaCurrent();
					EventManager.getInstance().info(
							"Durmiendo " + dif * 2
									+ " mins para recargar Stamina.");
					Thread.sleep(1000 * 60 * 2 * dif);
				}
				// RECARGAR HEALTH
				page = get.getUrl(profile.getMenuUrls().get(Menus.HOME));
				obtainInformation.leerDatosUsuario(page, profile);
				recargarHealth(profile);
				page = get.getUrl(profile.getMenuUrls().get(Menus.HOME));
				obtainInformation.leerDatosUsuario(page, profile);
				// INICIAR ATAQUES
				attackAll(profile);
				EventManager.getInstance().info("Fin de los ataques.");
				ejecutarMisiones(profile);
				// Leo datos
				page = get.getUrl(profile.getMenuUrls().get(Menus.HOME));
				obtainInformation.leerDatosUsuario(page, profile);
				// Chequeo si tengo energy porque pase de nivel
				if (fightManager.canDoAttacks(profile)) {
					EventManager.getInstance().info("Sigo atacando porque tengo energia.");
					attackAll(profile);
				}
				// HAGO UN DEPOSITO PARA ASEGURAR LA PLATA PARA EL RESTORE
				depositarParaElRestore(profile);
				// CONSTRUYENDO UNITS
				// Primero actualizo las units
				leerUnits(profile);
				// Construyo units defensa
				page = get.getUrl(profile.getMenuUrls().get(Menus.UNITS));
				unitsManager.buyUnitsDefense(get, page, profile, unitDefense);
				// Contruyo units ataque
				page = get.getUrl(profile.getMenuUrls().get(Menus.UNITS));
				unitsManager.buyUnitsAttack(get, page, profile, unitAttack);
				// HACIENDO BUILDINGS
				buildingManager.doAllBuilding(get,profile);
				// FIN Actualizo el profile
				// Leo datos
				page = get.getUrl(profile.getMenuUrls().get(Menus.HOME));
				obtainInformation.leerDatosUsuario(page, profile);
			} catch (Exception e) {
				EventManager.getInstance().error(
						"Error en el get. " + e.getMessage(), e);
			}
			get.close();
			System.gc();
			try {
				int dif = profile.getStaminaMax() - profile.getStaminaCurrent();
				EventManager.getInstance().info(
						"Durmiendo " + dif * 2 + " minutos.");
				Thread.sleep(1000 * 60 * dif * 2);
			} catch (InterruptedException e) {
			}
		}
	}

	private void ejecutarMisiones(Profile profile) throws Exception {
		EventManager.getInstance().info(
				"Ejecutando todas las misiones.");
		// EJECUTAMOS MISIONES
		StringBuilder page = get.getUrl(profile.getMenuUrls().get(Menus.MISSION));
		obtainInformation.leerDatosUsuario(page, profile);
		mManager.doAllMission(get, page, profile);
		EventManager.getInstance().info(
				"Fin Ejecutando todas las misiones.");
	}

	private void depositarParaElRestore(Profile profile) {
		try {
			StringBuilder page = get.getUrl(profile.getMenuUrls().get(
					Menus.HOME));
			obtainInformation.leerDatosUsuario(page, profile);
			if(profile.getHealthCurrent()!=profile.getHealthMax()){
				page = get.getUrl(profile.getMenuUrls().get(Menus.HOSPITAL));
				RestoreValue rv = obtainRestore.leerDatos(page);
				if (rv != null) {
					if (rv.getValueVault() < rv.getValueRestore()) {
						depositar(profile, get,
								rv.getValueRestore() - rv.getValueVault());
						EventManager.getInstance().info(
								"Se realizo el deposito para el restore.");
					}
				}
			}else{
				EventManager.getInstance().info(
				"No se hace deposito porque estoy full of health.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void recargarHealth(Profile profile) {
		EventManager.getInstance().info("Go to hospital.");
		String unitUrl = profile.getMenuUrls().get(Menus.HOSPITAL);
		StringBuilder page;
		try {
			page = get.getUrl(unitUrl);
			RestoreValue rv = obtainRestore.leerDatos(page);
			if (rv != null) {
				if (rv.getValueVault() > rv.getValueRestore()) {
					page = get.getUrl(rv.getUrlRestore());
					EventManager.getInstance().info(
							"Se hizo el restore health.");
				} else {
					EventManager.getInstance().info("No alcanza para el restore health. Asi que deposito.");
					obtainInformation.leerDatosUsuario(page, profile);
					//Chequeo si tengo la plata para depositar
					Double aux = (rv.getValueRestore() - rv.getValueVault()) * 1.15;
					if(profile.getMoney()>aux.longValue()){
						depositar(profile, get,aux.longValue());
						page = get.getUrl(unitUrl);
						rv = obtainRestore.leerDatos(page);
						if(rv!=null){
							if (rv.getValueVault() > rv.getValueRestore()) {
								page = get.getUrl(rv.getUrlRestore());
								EventManager.getInstance().info(
										"Se hizo el restore health.");
							}
						}
					}else {
						EventManager.getInstance().info("No alcanza mi plata para hacer el restore asi que tengo que esperar y juntar plata.");
						//Ejecuto misiones para ganar plata
						ejecutarMisiones(profile);
						//Actualizo profile
						unitUrl = profile.getMenuUrls().get(Menus.HOME);
						page = get.getUrl(unitUrl);
						obtainInformation.leerDatosUsuario(page, profile);
						//Deposito lo que tengo
						depositar(profile, get,profile.getMoney());
						//Espero un rato
						Long timeToWait = obtainInformation.getTimeToGainMoney(page);
						EventManager.getInstance().info("No se pudo hacer el restore entonces espero ("+timeToWait+" seconds) para recaudar la plata.");
						Thread.sleep((timeToWait + 60)*1000);
						//Actualizo profile
						unitUrl = profile.getMenuUrls().get(Menus.HOME);
						page = get.getUrl(unitUrl);
						obtainInformation.leerDatosUsuario(page, profile);
						//Vuelvo a recargar health
						recargarHealth(profile);
					}
				}
			} else {
				EventManager.getInstance().info("Estoy full of health.");
			}
		} catch (Exception ex) {
			// SI ENTRA ACA ES PORQUE NO HAY QUE RECUPERAR HEALTH
		}
	}

	private void attackAll(Profile profile) {
		fightManager.startFighting(get, profile);
	}

	private void depositar(Profile profile, HttpGetUrl get, Long depositValue) {
		try {
			String unitUrl = profile.getMenuUrls().get(Menus.BANK);
			StringBuilder page = get.getUrl(unitUrl);
			EventManager.getInstance().info("Depositando " + depositValue);
			Map<String, String> params = new HashMap<String, String>();
			params.put("depositAmount", "" + depositValue);
			params.put("action", "Deposit");
			params.put("sk", "1");
			page = get.postUrl("http://wwar.storm8.com/bank.php", params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void mostrarProfile(Profile profile) {
		EventManager.getInstance().info("Level: " + profile.getLevel());
		EventManager.getInstance().info(
				"Alianze Size: " + profile.getAlianzeSize());
		EventManager.getInstance().info("Money: " + profile.getMoney());
		EventManager.getInstance().info(
				"Experience: " + profile.getExperience());
		EventManager.getInstance().info(
				"Health: " + profile.getHealthCurrent() + "/"
						+ profile.getHealthMax());
		EventManager.getInstance().info(
				"Stamina: " + profile.getStaminaCurrent() + "/"
						+ profile.getStaminaMax());
		EventManager.getInstance().info(
				"Energy: " + profile.getEnergyCurrent() + "/"
						+ profile.getEnergyMax());
		EventManager
				.getInstance()
				.info("---------------------------------------------------------------");
		for (Unit u : profile.getUnits()) {
			EventManager.getInstance().info(u.toString());
		}
		for (Building b : profile.getBuildings()) {
			EventManager.getInstance().info(b.toString());
		}
		EventManager.getInstance().info(
				"Attack Points: " + profile.calcularPointAttack());
	}

	private void leerBuildings(Profile profile) {
		try {
			String unitUrl = profile.getMenuUrls().get(Menus.BUILDINGS);
			EventManager.getInstance().info("Going to Page Buildings....");
			StringBuilder page = get.getUrl(unitUrl);
			EventManager.getInstance().info("Actualizando buildings...");
			List<Building> buildings = dbManager.getBuildings();
			obtainBuildings.cargarDatosBuildings(page, buildings);
			dbManager.guardarBuildings(buildings);
			profile.setBuildings(buildings);
			EventManager.getInstance().info("Buildings actualizadas.");
		} catch (Exception e) {
			EventManager.getInstance().error(
					"Error en leer Buildings. " + e.getMessage(), e);
		}
	}

	private void leerUnits(Profile profile) {
		try {
			String unitUrl = profile.getMenuUrls().get(Menus.UNITS);
			EventManager.getInstance().info("Going to Page Units....");
			StringBuilder page = get.getUrl(unitUrl);
			Map<UnitType, String> linksUnits = obtainUnits.leerLinksUnits(page);
			EventManager.getInstance().info("Actualizando units...");
			List<Unit> units = dbManager.getUnits();
			for (UnitType ut : linksUnits.keySet()) {
				page = get.getUrl(linksUnits.get(ut));
				obtainUnits.cargarDatosUnits(page, units);
			}
			dbManager.guardarUnits(units);
			profile.setUnits(units);
			EventManager.getInstance().info("Units actualizadas.");
		} catch (Exception e) {
			EventManager.getInstance().error(
					"Error en leer Units. " + e.getMessage(), e);
		}
	}

}
