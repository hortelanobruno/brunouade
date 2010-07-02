package com.brunoli.worldwar.db.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.brunoli.worldwar.beans.Mission;

@Entity
@Table(name="MISSIONS")
public class MissionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2781052072640110011L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "MISSION_NAME")
	private String missionName;
	@Column(name = "PERCENT_COMPLETED")
	private Integer percentCompleted;
	@Column(name = "LEVEL_REQUIERED")
	private Integer levelRequiered;
	@Column(name = "ALIANZE_SIRE_REQUIERED")
	private Integer alianzeSizeRequiered;
	@Column(name = "ENERGY_REQUIERED")
	private Integer energyRequiered;
	@Column(name = "UNITS_REQUIERED")
	private Map<String, Integer> unitsRequiered;
	@Column(name = "POSIBLE_LOOT")
	private String posibleLoot;
	@Column(name = "MIN_MONEY_GAINED")
	private Integer minMoneyGained;
	@Column(name = "MAX_MONEY_GAINED")
	private Integer maxMoneyGained;
	@Column(name = "TAB_INDEX")
	private Integer tabIndex;
	@Column(name = "MISSION_URL")
	private String missionUrl;
	
	public MissionEntity() {
		unitsRequiered = new HashMap<String,Integer>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(Integer tabIndex) {
		this.tabIndex = tabIndex;
	}

	public String getMissionUrl() {
		return missionUrl;
	}

	public void setMissionUrl(String missionUrl) {
		this.missionUrl = missionUrl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Mission getVO(){
		Mission mi = new Mission();
		mi.setId(id);
		mi.setAlianzeSizeRequiered(alianzeSizeRequiered);
		mi.setEnergyRequiered(energyRequiered);
		mi.setLevelRequiered(levelRequiered);
		mi.setMaxMoneyGained(maxMoneyGained);
		mi.setMinMoneyGained(minMoneyGained);
		mi.setMissionName(missionName);
		mi.setMissionUrl(missionUrl);
		mi.setPercentCompleted(percentCompleted);
		mi.setPosibleLoot(posibleLoot);
		mi.setTabIndex(tabIndex);
		mi.setUnitsRequiered(unitsRequiered);
		return mi;
	}
	
	public void setVO(Mission mi){
		this.setAlianzeSizeRequiered(mi.getAlianzeSizeRequiered());
		this.setEnergyRequiered(mi.getEnergyRequiered());
		this.setId(mi.getId());
		this.setLevelRequiered(mi.getLevelRequiered());
		this.setMaxMoneyGained(mi.getMaxMoneyGained());
		this.setMinMoneyGained(mi.getMinMoneyGained());
		this.setMissionName(mi.getMissionName());
		this.setMissionUrl(mi.getMissionUrl());
		this.setPercentCompleted(mi.getPercentCompleted());
		this.setPosibleLoot(mi.getPosibleLoot());
		this.setTabIndex(mi.getTabIndex());
		this.setUnitsRequiered(mi.getUnitsRequiered());
	}
}
