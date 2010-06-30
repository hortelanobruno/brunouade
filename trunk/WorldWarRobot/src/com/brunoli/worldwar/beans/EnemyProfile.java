package com.brunoli.worldwar.beans;

import java.util.HashMap;
import java.util.Map;

public class EnemyProfile {

	private Integer battleWon;
	private Integer battleLost;
	private Map<Building, Integer> buildings;// cantidad
	private Map<Unit, Integer> units;

	public EnemyProfile() {
		buildings = new HashMap<Building, Integer>();
		units = new HashMap<Unit, Integer>();
	}

	public Map<Building, Integer> getBuildings() {
		return buildings;
	}

	public Map<Unit, Integer> getUnits() {
		return units;
	}

	public void setBuildings(Map<Building, Integer> buildings) {
		this.buildings = buildings;
	}

	public void setUnits(Map<Unit, Integer> units) {
		this.units = units;
	}

	public Integer getBattleLost() {
		return battleLost;
	}

	public Integer getBattleWon() {
		return battleWon;
	}

	public void setBattleLost(Integer battleLost) {
		this.battleLost = battleLost;
	}

	public void setBattleWon(Integer battleWon) {
		this.battleWon = battleWon;
	}
	
}
