package com.brunoli.worldwar.test;

import com.brunoli.worldwar.beans.Profile;
import com.brunoli.worldwar.manager.BuildingManager;
import com.brunoli.worldwar.parser.ObtainInformation;
import com.brunoli.worldwar.util.Menus;
import com.brunoli.worldwar.webmanager.HttpGetUrl;

public class TestDoBuildings {

	private String urlInicio = "http://wwar.storm8.com/aindex.php?version=a1.54&udid=200145da554359fa&pf=0f86e6501f5b2c2654be9ddfd212da28&model=HTC+Dream&sv=2.1-update1";
	private ObtainInformation obtainInformation;
	private BuildingManager buildingManager;
	private HttpGetUrl get;
	
	public static void main(String[] arg) {
		new TestDoBuildings();
	}
	
	public TestDoBuildings() {
		solver();
	}

	private void solver() {
		get = new HttpGetUrl();
		obtainInformation = new ObtainInformation();
		buildingManager = new BuildingManager();
		try {
			StringBuilder page = get.getUrl(urlInicio);
			Profile profile = new Profile();
			profile.setStartUrl(urlInicio);
			// Leo datos
			obtainInformation.leerDatosUsuario(page, profile);
			// Leo links
			obtainInformation.leerLinks(page, profile);
			
			// HACIENDO BUILDINGS
			page = get.getUrl(profile.getMenuUrls().get(Menus.BUILDINGS));
			buildingManager.doAllBuilding(get, page, profile);
		} catch (Exception e) {
			System.out.println("Error en el get. "+e.getMessage());
		}
		get.close();
		
		
	}
}
