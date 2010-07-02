package com.brunoli.worldwar.beans;


public class Mission {

	private String missionName;
	private String missionUrl;
	private Integer percentCompleted;
	private Integer alianzeSizeRequiered;
	private Integer energyRequiered;

	public Mission() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getEnergyRequiered() {
		return energyRequiered;
	}
	
	public void setEnergyRequiered(Integer energyRequiered) {
		this.energyRequiered = energyRequiered;
	}
	
	public Integer getAlianzeSizeRequiered() {
		return alianzeSizeRequiered;
	}
	
	public void setAlianzeSizeRequiered(Integer alianzeSizeRequiered) {
		this.alianzeSizeRequiered = alianzeSizeRequiered;
	}

	public Integer getPercentCompleted() {
		return percentCompleted;
	}

	public void setPercentCompleted(Integer percentCompleted) {
		this.percentCompleted = percentCompleted;
	}

	public String getMissionName() {
		return missionName;
	}

	public String getMissionUrl() {
		return missionUrl;
	}

	public void setMissionName(String missionName) {
		this.missionName = missionName;
	}

	public void setMissionUrl(String missionUrl) {
		this.missionUrl = missionUrl;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Mission Name:"+this.getMissionName()+"\n");
		sb.append("Mission URL:"+this.getMissionUrl()+"\n");
		sb.append("Completed:"+this.getPercentCompleted()+"% \n");
		sb.append("Alianze Size requiered:"+this.getAlianzeSizeRequiered()+"\n");
		sb.append("Requiered requiered:"+this.getEnergyRequiered()+"\n");
		return sb.toString();
	}

}
