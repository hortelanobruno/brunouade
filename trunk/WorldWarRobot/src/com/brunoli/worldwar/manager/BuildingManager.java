package com.brunoli.worldwar.manager;

import java.util.List;

import com.brunoli.worldwar.beans.Building;
import com.brunoli.worldwar.beans.Profile;
import com.brunoli.worldwar.db.DBManager;
import com.brunoli.worldwar.event.EventManager;
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
			EventManager.getInstance().info("Contruyendo buildings...");
			while(building!=null && canDoBuilding(b,profile)){
				EventManager.getInstance().info("Contruyendo: "+b.getName());
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
			EventManager.getInstance().info("Fin de contruir buildings.");
		} catch (Exception e) {
			EventManager.getInstance().error("Error en el get. "+e.getMessage(),e);
		}
	}

	private boolean canDoBuilding(Building b, Profile profile) {
		if(b.getInitialCost()<=profile.getMoney()){
			return true;
		}else{
			EventManager.getInstance().info("No se pudo construir: "+b.getName()+". My money: "+profile.getMoney()+". Building cost: "+
					b.getInitialCost()+".");
			return false;
		}
	}

	private StringBuilder doBuilding(Building b, HttpGetUrl get) {
		try {
			StringBuilder ba = get.getUrl(b.getUrlDeploy());
			dbManager.buildingCreated(b);
			return ba;
		} catch (Exception e) {
			EventManager.getInstance().error("Error al contruir la building: "+b.toString()+". "+e.getMessage(),e);
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
