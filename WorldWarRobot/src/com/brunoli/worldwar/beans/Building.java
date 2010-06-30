package com.brunoli.worldwar.beans;

import com.brunoli.worldwar.util.BuildingType;

public class Building {

	private Long id;
	private String name;
	private Integer cantBuild;
	private Integer levelRequiered;
	private BuildingType category;
	private Long typeValue;
	private Long initialCost;
	private Long nextCost;
	private String urlDeploy;
	private String urlImg;

	public Building() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCantBuild() {
		return cantBuild;
	}

	public void setCantBuild(Integer cantBuild) {
		this.cantBuild = cantBuild;
	}

	public Integer getLevelRequiered() {
		return levelRequiered;
	}

	public void setLevelRequiered(Integer levelRequiered) {
		this.levelRequiered = levelRequiered;
	}

	public BuildingType getCategory() {
		return category;
	}

	public void setCategory(BuildingType category) {
		this.category = category;
	}

	public Long getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(Long typeValue) {
		this.typeValue = typeValue;
	}

	public Long getInitialCost() {
		return initialCost;
	}

	public void setInitialCost(Long initialCost) {
		this.initialCost = initialCost;
	}

	public Long getNextCost() {
		return nextCost;
	}

	public void setNextCost(Long nextCost) {
		this.nextCost = nextCost;
	}

	public String getUrlDeploy() {
		return urlDeploy;
	}
	
	public void setUrlDeploy(String urlDeploy) {
		this.urlDeploy = urlDeploy;
	}
	
	public String getUrlImg() {
		return urlImg;
	}
	
	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + name + "\n");
		sb.append("Type: " + category.getValue() + "\n");
		sb.append("Value: " + typeValue + "\n");
		sb.append("Cant Build: " + cantBuild + "\n");
		sb.append("Price: " + initialCost + "\n");
		sb.append("URL_DEPLOY: " + urlDeploy + "\n");
		sb.append("URL_IMG: " + urlImg + "\n");
		return sb.toString();
	}

}
