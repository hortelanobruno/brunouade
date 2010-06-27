package com.brunoli.worldwar.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.brunoli.worldwar.util.UnitType;

/**
 * Entity implementation class for Entity: UnitEntity
 *
 */
@Entity
@Table(name="UNITS")
public class UnitEntity implements Serializable {

	
	private static final long serialVersionUID = 1312867974732498910L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "UNIT_TYPE")
	private UnitType unitType;
	@Column(name = "LEVEL_REQUIERED")
	private Integer levelRequiered;
	@Column(name = "NAME")
	private String name;
	@Column(name = "PRICE")
	private Long price;
	@Column(name = "UNPKEEP")
	private Long unpkeep;
	@Column(name = "ATTACK")
	private Integer attack;
	@Column(name = "DEFENSE")
	private Integer defense;
	@Column(name = "URL_IMG_REPRESENT")
	private String urlImgRepresent;

	public UnitEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getUrlImgRepresent() {
		return urlImgRepresent;
	}

	public void setUrlImgRepresent(String urlImgRepresent) {
		this.urlImgRepresent = urlImgRepresent;
	}   
	
	
   
}
