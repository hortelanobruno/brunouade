package com.brunoli.worldwar.util;

public enum UnitType {

	INFANTRY("Infantry"), GROUND("Ground"), WATER("Water"), AIR("Air");

	private String value;

	private UnitType(String v) {
		this.value = v;
	}

	public String getValue() {
		return value;
	}

	public static UnitType getType(String v) {
		for (UnitType b : values()) {
			if (b.getValue().equalsIgnoreCase(v)) {
				return b;
			}
		}
		return null;
	}
}
