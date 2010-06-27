package com.brunoli.worldwar.beans;

import com.brunoli.util.UnitType;

public class Unit {

	private Long id;
	private UnitType unitType;
	private Integer levelRequiered;
	private String name;
	private Long price;
	private Long unpkeep;
	private Integer attack;
	private Integer defense;
	private String urlImgRepresent;
	
	public Unit() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUrlImgRepresent() {
		return urlImgRepresent;
	}
	
	public void setUrlImgRepresent(String urlImgRepresent) {
		this.urlImgRepresent = urlImgRepresent;
	}

	public UnitType getUnitType() {
		return unitType;
	}

	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}

	public Integer getLevelRequiered() {
		return levelRequiered;
	}

	public void setLevelRequiered(Integer levelRequiered) {
		this.levelRequiered = levelRequiered;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getUnpkeep() {
		return unpkeep;
	}

	public void setUnpkeep(Long unpkeep) {
		this.unpkeep = unpkeep;
	}

	public Integer getAttack() {
		return attack;
	}

	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	public Integer getDefense() {
		return defense;
	}

	public void setDefense(Integer defense) {
		this.defense = defense;
	}
	
	

}
