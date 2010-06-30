package com.brunoli.worldwar.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.brunoli.worldwar.beans.Building;
import com.brunoli.worldwar.util.BuildingType;

/**
 * Entity implementation class for Entity: BuildingEntity
 *
 */
@Entity
@Table(name="BUILDINGS")
public class BuildingEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "CANT_BUILD")
	private Integer cantBuild;
	@Column(name = "LEVEL_REQUIERED")
	private Integer levelRequiered;
	@Column(name = "CATEGORY")
	private BuildingType category;
	@Column(name = "TYPE_VALUE")
	private Long typeValue;
	@Column(name = "INITIAL_COST")
	private Long initialCost;
	@Column(name = "NEXT_COST")
	private Long nextCost;
	@Column(name = "URL_DEPLOY")
	private String urlDeploy;
	@Column(name = "URL_IMG")
	private String urlImg;
	
	public BuildingEntity() {
		super();
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
	
	public String getUrlImg() {
		return urlImg;
	}
	
	public void setUrlDeploy(String urlDeploy) {
		this.urlDeploy = urlDeploy;
	}
	
	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}
	
	public Building getVO(){
		Building building = new Building();
		building.setCantBuild(cantBuild);
		building.setCategory(category);
		building.setId(id);
		building.setInitialCost(initialCost);
		building.setLevelRequiered(levelRequiered);
		building.setName(name);
		building.setNextCost(nextCost);
		building.setTypeValue(typeValue);
		building.setUrlDeploy(urlDeploy);
		building.setUrlImg(urlImg);
		return building;
	}
	
	public void setVO(Building building){
		this.setCantBuild(building.getCantBuild());
		this.setCategory(building.getCategory());
		this.setId(building.getId());
		this.setInitialCost(building.getInitialCost());
		this.setLevelRequiered(building.getLevelRequiered());
		this.setName(building.getName());
		this.setNextCost(building.getNextCost());
		this.setTypeValue(building.getTypeValue());
		this.setUrlDeploy(building.getUrlDeploy());
		this.setUrlImg(building.getUrlImg());
	}
   
}
