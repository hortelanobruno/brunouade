package com.brunoli.worldwar.beans;

import java.util.HashMap;
import java.util.Map;

public class Mission {

	// Datos
	private Long id;
	private String missionName;
	private Integer percentCompleted;
	private Integer levelRequiered;
	private Integer alianzeSizeRequiered;
	private Integer energyRequiered;
	private Map<String, Integer> unitsRequiered;
	private String posibleLoot;
	private Integer minMoneyGained;
	private Integer maxMoneyGained;
	private Integer tabIndex;
	// URL
	private String missionUrl;

	public Mission() {
		// TODO Auto-generated constructor stub
		unitsRequiered = new HashMap<String, Integer>();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getTabIndex() {
		return tabIndex;
	}
	
	public void setTabIndex(Integer tabIndex) {
		this.tabIndex = tabIndex;
	}

	public String getMissionName() {
		return missionName;
	}

	public void setMissionName(String missionName) {
		this.missionName = missionName;
	}

	public Integer getPercentCompleted() {
		return percentCompleted;
	}

	public void setPercentCompleted(Integer percentCompleted) {
		this.percentCompleted = percentCompleted;
	}

	public Integer getLevelRequiered() {
		return levelRequiered;
	}

	public void setLevelRequiered(Integer levelRequiered) {
		this.levelRequiered = levelRequiered;
	}

	public Integer getAlianzeSizeRequiered() {
		return alianzeSizeRequiered;
	}

	public void setAlianzeSizeRequiered(Integer alianzeSizeRequiered) {
		this.alianzeSizeRequiered = alianzeSizeRequiered;
	}

	public Integer getEnergyRequiered() {
		return energyRequiered;
	}

	public void setEnergyRequiered(Integer energyRequiered) {
		this.energyRequiered = energyRequiered;
	}

	public Map<String, Integer> getUnitsRequiered() {
		return unitsRequiered;
	}

	public void setUnitsRequiered(Map<String, Integer> unitsRequiered) {
		this.unitsRequiered = unitsRequiered;
	}

	public String getPosibleLoot() {
		return posibleLoot;
	}

	public void setPosibleLoot(String posibleLoot) {
		this.posibleLoot = posibleLoot;
	}

	public Integer getMinMoneyGained() {
		return minMoneyGained;
	}

	public void setMinMoneyGained(Integer minMoneyGained) {
		this.minMoneyGained = minMoneyGained;
	}

	public Integer getMaxMoneyGained() {
		return maxMoneyGained;
	}

	public void setMaxMoneyGained(Integer maxMoneyGained) {
		this.maxMoneyGained = maxMoneyGained;
	}

	public String getMissionUrl() {
		return missionUrl;
	}

	public void setMissionUrl(String missionUrl) {
		this.missionUrl = missionUrl;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Mission Name:" + this.getMissionName() + "\n");
		sb.append("Mission URL:" + this.getMissionUrl() + "\n");
		sb.append("Completed:" + this.getPercentCompleted() + "% \n");
		sb.append("Alianze Size requiered:" + this.getAlianzeSizeRequiered()
				+ "\n");
		sb.append("Requiered requiered:" + this.getEnergyRequiered() + "\n");
		return sb.toString();
	}

}
