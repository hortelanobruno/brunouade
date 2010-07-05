package com.brunoli.worldwar.db;

import java.util.ArrayList;
import java.util.List;

import com.brunoli.worldwar.beans.Building;
import com.brunoli.worldwar.beans.Mission;
import com.brunoli.worldwar.beans.Unit;
import com.brunoli.worldwar.db.controller.BuildingEntityController;
import com.brunoli.worldwar.db.controller.BuildingToCreateEntityController;
import com.brunoli.worldwar.db.controller.MissionEntityController;
import com.brunoli.worldwar.db.controller.UnitEntityController;
import com.brunoli.worldwar.db.controller.exceptions.NonexistentEntityException;
import com.brunoli.worldwar.db.entity.BuildingEntity;
import com.brunoli.worldwar.db.entity.BuildingToCreateEntity;
import com.brunoli.worldwar.db.entity.UnitEntity;

public class DBManager {

	private BuildingEntityController buildingEntityController;
	private UnitEntityController unitEntityController;
	private MissionEntityController missionEntityController;
	private BuildingToCreateEntityController buildingToCreateEntityController;
	
	public DBManager() {
		buildingEntityController = new BuildingEntityController();
		unitEntityController = new UnitEntityController();
		missionEntityController = new MissionEntityController();
		buildingToCreateEntityController = new BuildingToCreateEntityController();
	}
	
	
	public List<Building> getBuildings(){
		List<BuildingEntity> entities = buildingEntityController.findBuildingEntityEntities();
		if(entities!=null){
			List<Building> builds = new ArrayList<Building>();
			for (BuildingEntity buildingEntity : entities) {
				builds.add(buildingEntity.getVO());
			}
			return builds;
		}else{
			return null;
		}
	}


	public List<Unit> getUnits() {
		List<UnitEntity> entities = unitEntityController.findUnitEntityEntities();
		if(entities!=null){
			List<Unit> units = new ArrayList<Unit>();
			for (UnitEntity buildingEntity : entities) {
				units.add(buildingEntity.getVO());
			}
			return units;
		}else{
			return null;
		}
	}


	public void guardarUnits(List<Unit> units) {
		UnitEntity ent;
		for (Unit unit : units) {
			ent = new UnitEntity();
			ent.setVO(unit);
			try {
				unitEntityController.edit(ent);
			} catch (NonexistentEntityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}


	public Unit getUnitByUrlImg(String url) {
		UnitEntity ent = unitEntityController.findUnitEntityByUrlImg(url);
		if(ent!=null){
			return ent.getVO();
		}else{
			return null;
		}
	}


	public void guardarBuildings(List<Building> buildings) {
		BuildingEntity ent;
		for (Building building : buildings) {
			ent = new BuildingEntity();
			ent.setVO(building);
			try {
				buildingEntityController.edit(ent);
			} catch (NonexistentEntityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public Building getBuildingByUrlImg(String url) {
		BuildingEntity ent = buildingEntityController.findBuildingEntityByUrlImg(url);
		if(ent!=null){
			return ent.getVO();
		}else{
			return null;
		}
	}


	public String getBuildingToCreate() {
		List<BuildingToCreateEntity> b = buildingToCreateEntityController.findBuildingEntityEntities(1, 0);
		if(b.size()==1){
			return b.get(0).getName();
		}else{
			return null;
		}
	}


	public void buildingCreated(Building b) {
		List<BuildingToCreateEntity> bu = buildingToCreateEntityController.findBuildingEntityEntities(1, 0);
		if(bu.size()==1){
			if(bu.get(0).getName().equalsIgnoreCase(b.getName())){
				try {
					buildingToCreateEntityController.destroy(bu.get(0).getId());
				} catch (NonexistentEntityException e) {
					e.printStackTrace();
				}
			}
		}else{
			try {
				throw new Exception("Error al querer crear una Building");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public Mission getMission(String missionName) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
