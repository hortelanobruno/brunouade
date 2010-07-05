package com.brunoli.worldwar.test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brunoli.worldwar.beans.Building;
import com.brunoli.worldwar.beans.Mission;
import com.brunoli.worldwar.beans.Profile;
import com.brunoli.worldwar.beans.Unit;
import com.brunoli.worldwar.db.DBManager;
import com.brunoli.worldwar.manager.BuildingManager;
import com.brunoli.worldwar.manager.FightManager;
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

	private String urlInicio = "http://wwar.storm8.com/aindex.php?version=a1.54&udid=200145da554359fa&pf=0f86e6501f5b2c2654be9ddfd212da28&model=HTC+Dream&sv=2.1-update1";
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
	
	public static void main (String[] args){
		new RunnableAll();
	}
	
	
	public RunnableAll() {
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
		while(true){
			get = new HttpGetUrl();
			System.out.println(Calendar.getInstance().getTime().toLocaleString()+" Atacando...");
			Profile profile = null;
			try {
				StringBuilder page = get.getUrl(urlInicio);
				profile = new Profile();
				profile.setStartUrl(urlInicio);
				// Leo datos
				obtainInformation.leerDatosUsuario(page, profile);
				// Leo links
				obtainInformation.leerLinks(page, profile);
				// Leo units
				leerUnits(profile);
				// Leo building
				leerBuildings(profile);
				//mostrar profile
				mostrarProfile(profile);
				//RECARGAR HEALTH
				recargarHealth(profile);
				//INICIAR ATAQUES
				attackAll(profile);
				System.out.println(Calendar.getInstance().getTime().toLocaleString()+" Fin de los ataques.");
				System.out.println(Calendar.getInstance().getTime().toLocaleString()+" Ejecutando todas las misiones.");
//				ejecutarAllMissions(profile);
				System.out.println(Calendar.getInstance().getTime().toLocaleString()+" Fin Ejecutando todas las misiones.");
				// Leo datos
				page = get.getUrl(profile.getMenuUrls().get(Menus.HOME));
				obtainInformation.leerDatosUsuario(page, profile);
				// Chequeo si tengo energy porque pase de nivel
				if(fightManager.canDoAttacks(profile)){
					System.out.println(Calendar.getInstance().getTime().toLocaleString()+" Sigo atacando porque tengo energia.");
					attackAll(profile);
				}
				//CONSTRUYENDO UNITS
				page = get.getUrl(profile.getMenuUrls().get(Menus.UNITS));
				unitsManager.buyUnitsAttack(get, page, profile);
				page = get.getUrl(profile.getMenuUrls().get(Menus.UNITS));
				unitsManager.buyUnitsDefense(get, page, profile);
				// HACIENDO BUILDINGS
				page = get.getUrl(profile.getMenuUrls().get(Menus.BUILDINGS));
				buildingManager.doAllBuilding(get, page, profile);
			} catch (Exception e) {
				System.out.println("Error en el get. " + e.getMessage());
			}
			try {
				System.out.println(Calendar.getInstance().getTime().toLocaleString()+" Durmiendo 1 hora.");
				Thread.sleep(1000 * 60 * 60 );
			} catch (InterruptedException e) {
			}
			get.close();
		}
	}
	
	private void recargarHealth(Profile profile) {
		System.out.println("Go to hospital.");
		String unitUrl = profile.getMenuUrls().get(Menus.HOSPITAL);
		System.out.println("Link... "+unitUrl);
		StringBuilder page;
		try{
			page = get.getUrl(unitUrl);
			RestoreValue rv = obtainRestore.leerDatos(page);
			if(rv.getValueVault()>rv.getValueRestore()){
				page = get.getUrl(rv.getUrlRestore());
				System.out.println("Se hizo el restore health.");
			}else{
				System.out.println("No alcanza para el restore health. Asi que deposito.");
				depositar(profile,get,rv.getValueRestore()-rv.getValueVault());
				page = get.getUrl(unitUrl);
				rv = obtainRestore.leerDatos(page);
				if(rv.getValueVault()>rv.getValueRestore()){
					page = get.getUrl(rv.getUrlRestore());
					System.out.println("Se hizo el restore health.");
				}else{
					System.out.println("No se pudo hacer el restore entonces salgo de la aplicacion.");
					System.exit(0);
				}
			}
		}catch(Exception ex){
			//SI ENTRA ACA ES PORQUE NO HAY QUE RECUPERAR HEALTH
		}
	}


	private void attackAll(Profile profile) {
		fightManager.startFighting(get,profile);
	}


	private void depositar(Profile profile, HttpGetUrl get, Long depositValue) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Go to bank.");
			String unitUrl = profile.getMenuUrls().get(Menus.BANK);
			System.out.println("Link... "+unitUrl);
			StringBuilder page = get.getUrl(unitUrl);
			Double aux = depositValue * 1.15;
			System.out.println("Depositando "+aux.longValue());
			Map<String,String> params = new HashMap<String,String>();
			params.put("depositAmount", ""+aux.longValue());
			params.put("action", "Deposit");
			params.put("sk", "1");
			page = get.postUrl("http://wwar.storm8.com/bank.php", params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void mostrarProfile(Profile profile) {
		System.out.println("Level: "+profile.getLevel());
		System.out.println("Alianze Size: "+profile.getAlianzeSize());
		System.out.println("Money: "+profile.getMoney());
		System.out.println("Experience: "+profile.getExperience());
		System.out.println("Health: "+profile.getHealthCurrent()+"/"+profile.getHealthMax());
		System.out.println("Stamina: "+profile.getStaminaCurrent()+"/"+profile.getStaminaMax());
		System.out.println("Energy: "+profile.getEnergyCurrent()+"/"+profile.getEnergyMax());
		System.out.println("---------------------------------------------------------------");
		for(Unit u : profile.getUnits()){
			System.out.println(u.toString());
		}
		for(Building b : profile.getBuildings()){
			System.out.println(b.toString());
		}
		System.out.println("Attack Points: "+profile.calcularPointAttack());
	}

	private void leerBuildings(Profile profile) {
		try {
		String unitUrl = profile.getMenuUrls().get(Menus.BUILDINGS);
		System.out.println("Going to Page Buildings....");
		StringBuilder page = get.getUrl(unitUrl);
		System.out.println("Actualizando buildings...");
		List<Building> buildings = dbManager.getBuildings();
		obtainBuildings.cargarDatosBuildings(page,buildings);
		dbManager.guardarBuildings(buildings);
		profile.setBuildings(buildings);
		System.out.println("Buildings actualizadas.");
		} catch (Exception e) {
			System.out.println("Error en leer Buildings. "+e.getMessage());
		}
	}

	private void leerUnits(Profile profile) {
		try {
			String unitUrl = profile.getMenuUrls().get(Menus.UNITS);
			System.out.println("Going to Page Units....");
			StringBuilder page = get.getUrl(unitUrl);
			Map<UnitType, String> linksUnits = obtainUnits.leerLinksUnits(page);
			System.out.println("Actualizando units...");
			List<Unit> units = dbManager.getUnits();
			for (UnitType ut : linksUnits.keySet()) {
				System.out.println("Go to " + ut.getValue());
				page = get.getUrl(linksUnits.get(ut));
				obtainUnits.cargarDatosUnits(page, units);
			}
			dbManager.guardarUnits(units);
			profile.setUnits(units);
			System.out.println("Units actualizadas.");
		} catch (Exception e) {
			System.out.println("Error en leer Units. "+e.getMessage());
		}
	}
	
	public void ejecutarAllMissions(Profile profile) {
		try {
			// Chequeo si tengo energia para procesar misiones
			if (profile.getEnergyCurrent() > 60) {
				System.out.println("Go missions.");
				String missionUrl = profile.getMenuUrls().get(Menus.MISSION);
				System.out.println("Link... " + missionUrl);
				StringBuilder page = get.getUrl(missionUrl);
				consumeAllMission(page);
			} else {
				System.out.println("No tengo energia para procesar misiones");
			}
			System.out.println("FIN.");
		} catch (Exception e) {
			System.out.println("Error en el get. " + e.getMessage());
		}
	}

	public void consumeAllMission(StringBuilder page) {
		try {
			System.out.println("Missions....");
			// Obtengo misiones
			List<Mission> missions = obtainMission.leerMissions(page);
			// Obtengo la mision deseada
			Mission mission = obtainMission.getMission("Repel Counterattack",
					missions);
			// Consumo la primer mision
			System.out.println("Proceso primer mision");
			page = get.getUrl(mission.getMissionUrl());
			// ACA sigo consumiendo hasta el fin
			while (!obtainMission.checkRefillEnergy(page)) {
				// Tengo energia
				if (obtainMission.checkDeployUnit(page)) {
					System.out.println("Tengo que deployar");
					// Tengo que deployar units
					String urlDeploy = obtainMission
							.obtainUrlToDeployUnit(page);
					page = get.getUrl(urlDeploy);
					if(!obtainMission.checkDeployUnitsOK(page)){
						System.out.println("NO TENGO PLATA PARA DEPLOYEAR, ESPERAR PROXIMA RUEDA");
						return;
					}
				}
				// Proceso mision
				System.out.println("Procesando siguiente mision");
				mission = obtainMission.leerMissionHeader(page);
				page = get.getUrl(mission.getMissionUrl());

			}
			System.out.println("Se acabo la energia.");
		} catch (Exception e) {
			System.out.println("Error en el get2. " + e.getMessage());
		}
	}
}
