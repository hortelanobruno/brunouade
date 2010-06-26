package com.brunoli.worldwar.db.entity;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UnitEntity
 *
 */
@Entity
@Table(name="Units")

public class UnitEntity implements Serializable {

	
	private String name;   
	@Id
	private Long id;
	private Long price;
	private String type;
	private Long typeValue;
	private Integer cantOwned;
	private String urlPictureRepresent;
	private static final long serialVersionUID = 1L;

	public UnitEntity() {
		super();
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public Long getPrice() {
		return this.price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}   
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}   
	public Long getTypeValue() {
		return this.typeValue;
	}

	public void setTypeValue(Long typeValue) {
		this.typeValue = typeValue;
	}   
	public Integer getCantOwned() {
		return this.cantOwned;
	}

	public void setCantOwned(Integer cantOwned) {
		this.cantOwned = cantOwned;
	}   
	public String getUrlPictureRepresent() {
		return this.urlPictureRepresent;
	}

	public void setUrlPictureRepresent(String urlPictureRepresent) {
		this.urlPictureRepresent = urlPictureRepresent;
	}
   
}
