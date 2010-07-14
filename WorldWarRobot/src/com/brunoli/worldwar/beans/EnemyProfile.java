package com.brunoli.worldwar.beans;

import java.util.HashMap;
import java.util.Map;

public class EnemyProfile {

	private Integer battleWon;
	private Integer battleLost;
	private Map<Building, Integer> buildings;// cantidad
	private Map<Unit, Integer> units;
	private String attackUrl;
	private String commentTab;

	public EnemyProfile() {
		buildings = new HashMap<Building, Integer>();
		units = new HashMap<Unit, Integer>();
	}
	
	public String getAttackUrl() {
		return attackUrl;
	}
	
	public void setAttackUrl(String attackUrl) {
		this.attackUrl = attackUrl;
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
	
	public String getCommentTab() {
		return commentTab;
	}
	
	public void setCommentTab(String commentTab) {
		this.commentTab = commentTab;
	}
	
}
