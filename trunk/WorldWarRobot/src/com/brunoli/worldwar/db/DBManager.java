package com.brunoli.worldwar.db;

import java.util.ArrayList;
import java.util.List;

import com.brunoli.worldwar.beans.Building;
import com.brunoli.worldwar.beans.Unit;
import com.brunoli.worldwar.db.controller.BuildingEntityController;
import com.brunoli.worldwar.db.controller.UnitEntityController;
import com.brunoli.worldwar.db.controller.exceptions.NonexistentEntityException;
import com.brunoli.worldwar.db.entity.BuildingEntity;
import com.brunoli.worldwar.db.entity.UnitEntity;

public class DBManager {

	private BuildingEntityController buildingEntityController;
	private UnitEntityController unitEntityController;
	
	public DBManager() {
		buildingEntityController = new BuildingEntityController();
		unitEntityController = new UnitEntityController();
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
	
}
