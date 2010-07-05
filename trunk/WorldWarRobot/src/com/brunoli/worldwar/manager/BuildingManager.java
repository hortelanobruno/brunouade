package com.brunoli.worldwar.manager;

import java.util.List;

import com.brunoli.worldwar.beans.Building;
import com.brunoli.worldwar.beans.Profile;
import com.brunoli.worldwar.db.DBManager;
import com.brunoli.worldwar.parser.ObtainBuildings;
import com.brunoli.worldwar.parser.ObtainInformation;
import com.brunoli.worldwar.webmanager.HttpGetUrl;

public class BuildingManager {

	private ObtainInformation obtainInformation;
	private ObtainBuildings obtainBuildings;
	private DBManager dbManager;
	
	public BuildingManager() {
		obtainInformation = new ObtainInformation();
		obtainBuildings = new ObtainBuildings();
		dbManager = new DBManager();
	}
	
	public void doAllBuilding(HttpGetUrl get, StringBuilder pageBuilding, Profile profile){
		
		try {
			List<Building> buildings = dbManager.getBuildings();
			//Cargo datos energia
			obtainInformation.leerDatosUsuario(pageBuilding, profile);
			//Actualizo la informacion de las buildings
			obtainBuildings.cargarDatosBuildings(pageBuilding,buildings);
			//Obtener building a realizar 
			String building = dbManager.getBuildingToCreate();
			Building b = getBuilding(building,buildings);
			System.out.println("Contruyendo buildings...");
			while(building!=null && canDoBuilding(b,profile)){
				System.out.println("Contruyendo: "+b.getName());
				//Construir building
				pageBuilding = doBuilding(b,get);
				//Cargo datos energia
				obtainInformation.leerDatosUsuario(pageBuilding, profile);
				//Actualizo la informacion de las buildings
				obtainBuildings.cargarDatosBuildings(pageBuilding,buildings);
				//Obtener building a realizar 
				building = dbManager.getBuildingToCreate();
				b = getBuilding(building,buildings);
			}
			dbManager.guardarBuildings(buildings);
			System.out.println("Fin de contruir buildings.");
		} catch (Exception e) {
			System.out.println("Error en el get. "+e.getMessage());
		}
	}

	private boolean canDoBuilding(Building b, Profile profile) {
		if(b.getInitialCost()<=profile.getMoney()){
			return true;
		}else{
			return false;
		}
	}

	private StringBuilder doBuilding(Building b, HttpGetUrl get) {
		try {
			StringBuilder ba = get.getUrl(b.getUrlDeploy());
			dbManager.buildingCreated(b);
			return ba;
		} catch (Exception e) {
			System.out.println("Error al contruir la building: "+b.toString()+". "+e.getMessage());
		}
		return null;
	}

	private Building getBuilding(String building, List<Building> buildings) {
		for (Building b : buildings) {
			if(b.getName().equalsIgnoreCase(building)){
				return b;
			}
		}
		return null;
	}

}
