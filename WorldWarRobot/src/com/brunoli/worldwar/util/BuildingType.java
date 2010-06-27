package com.brunoli.worldwar.util;

public enum BuildingType {
	INCOME("Income"), DEFENSE("Defense"), ENERGY("Energy");

	private String value;

	private BuildingType(String v) {
		this.value = v;
	}

	public String getValue() {
		return value;
	}

	public static BuildingType getType(String v) {
		for (BuildingType b : values()) {
			if (b.getValue().equalsIgnoreCase(v)) {
				return b;
			}
		}
		return null;
	}
}
