package com.brunoli.worldwar.test;

import java.util.List;
import java.util.Map;

import com.brunoli.worldwar.beans.Building;
import com.brunoli.worldwar.beans.Profile;
import com.brunoli.worldwar.beans.Unit;
import com.brunoli.worldwar.db.DBManager;
import com.brunoli.worldwar.parser.ObtainBuildings;
import com.brunoli.worldwar.parser.ObtainInformation;
import com.brunoli.worldwar.parser.ObtainUnits;
import com.brunoli.worldwar.util.Menus;
import com.brunoli.worldwar.util.UnitType;
import com.brunoli.worldwar.webmanager.HttpGetUrl;

public class TestCargarProfile {

	private String urlInicio = "http://wwar.storm8.com/aindex.php?version=a1.54&udid=200145da554359fa&pf=0f86e6501f5b2c2654be9ddfd212da28&model=HTC+Dream&sv=2.1-update1";
	private ObtainInformation obtainInformation;
	private ObtainUnits obtainUnits;
	private ObtainBuildings obtainBuildings;
	private HttpGetUrl get;
	private DBManager dbManager;

	public static void main(String[] arg) {
		new TestCargarProfile();
	}

	public TestCargarProfile() {
		get = new HttpGetUrl();
		obtainInformation = new ObtainInformation();
		obtainUnits = new ObtainUnits();
		dbManager = new DBManager();
		obtainBuildings = new ObtainBuildings();
		try {
			StringBuilder page = get.getUrl(urlInicio);
			Profile profile = new Profile();
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
		} catch (Exception e) {
			System.out.println("Error en el get. " + e.getMessage());
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

}
