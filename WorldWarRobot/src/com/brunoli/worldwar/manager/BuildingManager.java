package com.brunoli.worldwar.manager;

import java.util.List;

import com.brunoli.worldwar.beans.Building;
import com.brunoli.worldwar.beans.Profile;
import com.brunoli.worldwar.db.DBManager;
import com.brunoli.worldwar.event.EventManager;
import com.brunoli.worldwar.parser.ObtainBuildings;
import com.brunoli.worldwar.parser.ObtainInformation;
import com.brunoli.worldwar.util.BuildingType;
import com.brunoli.worldwar.util.FightResultType;
import com.brunoli.worldwar.util.Menus;
import com.brunoli.worldwar.webmanager.HttpGetUrl;

public class BuildingManager {

	private ObtainInformation obtainInformation;
	private ObtainBuildings obtainBuildings;
	private DBManager dbManager;
	private String urlFights = "http://wwar.storm8.com/ajax/getNewsFeedStories.php?selectedTab=fight";
	
	public BuildingManager() {
		obtainInformation = new ObtainInformation();
		obtainBuildings = new ObtainBuildings();
		dbManager = new DBManager();
	}
	
	public void doAllBuilding(HttpGetUrl get, Profile profile){
		try {
			//Primero obtengo los resultados de los ultimos ataques
			List<FightResultType> results = obtainInformation.leerUltimosAtaquesRecibidos(get.getUrl(urlFights));
			BuildingType buildingTypeToCreate = calcularBuildingsToCreate(results);
			EventManager.getInstance().info("Contruyendo building de "+buildingTypeToCreate.getValue());
			List<Building> buildings = dbManager.getBuildings();
			StringBuilder pageBuilding = get.getUrl(profile.getMenuUrls().get(Menus.BUILDINGS));
			//Cargo datos energia
			obtainInformation.leerDatosUsuario(pageBuilding, profile);
			//Actualizo la informacion de las buildings
			obtainBuildings.cargarDatosBuildings(pageBuilding,buildings);
			//Obtener building a realizar 
			String building = dbManager.getBuildingToCreate(buildingTypeToCreate);
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
				building = dbManager.getBuildingToCreate(buildingTypeToCreate);
				b = getBuilding(building,buildings);
			}
			dbManager.guardarBuildings(buildings);
			EventManager.getInstance().info("Fin de contruir buildings.");
		} catch (Exception e) {
			EventManager.getInstance().error("Error en el get. "+e.getMessage(),e);
		}
	}

	private BuildingType calcularBuildingsToCreate(List<FightResultType> results) {
		int cantWon = 0;
		int cantLost = 0;
		for (FightResultType f : results) {
			if(f.equals(FightResultType.WON)){
				cantWon++;
			} else if(f.equals(FightResultType.LOST)){
				cantLost++;
			}
		}
		if(cantLost>=3){
			return BuildingType.DEFENSE;
		}else{
			return BuildingType.INCOME;
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
