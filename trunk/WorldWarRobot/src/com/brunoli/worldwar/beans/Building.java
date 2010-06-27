package com.brunoli.worldwar.beans;

import com.brunoli.util.BuildingEnum;
import com.brunoli.util.BuildingType;

public class Building {

	private BuildingEnum name;
	private Integer cantBuild;
	private BuildingType type;
	private Long typeValue;
	private Long price;
	private String url;
	
	public Building() {
		// TODO Auto-generated constructor stub
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public BuildingEnum getName() {
		return name;
	}

	public void setName(BuildingEnum name) {
		this.name = name;
	}

	public Integer getCantBuild() {
		return cantBuild;
	}

	public void setCantBuild(Integer cantBuild) {
		this.cantBuild = cantBuild;
	}

	public BuildingType getType() {
		return type;
	}

	public void setType(BuildingType type) {
		this.type = type;
	}

	public Long getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(Long typeValue) {
		this.typeValue = typeValue;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: "+name.getValue()+"\n");
		sb.append("Type: "+type.name()+"\n");
		sb.append("Value: "+typeValue+"\n");
		sb.append("Cant Build: "+cantBuild+"\n");
		sb.append("Price: "+price+"\n");
		sb.append("URL: "+url+"\n");
		return sb.toString();
	}
	
	
}
