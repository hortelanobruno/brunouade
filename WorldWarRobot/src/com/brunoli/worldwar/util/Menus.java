package com.brunoli.worldwar.util;

public enum Menus {
	HOME("HOME"), MISSION("MISSION"), BATTLE("BATTLE"), UNITS("UNITS"), BUILDINGS(
			"BUILDINGS"), HOSPITAL("HOSPITAL"), BANK("BANK");

	private String value;

	private Menus(String v) {
		this.value = v;
	}

	public String getValue() {
		return value;
	}

	public static Menus getType(String v) {
		for (Menus b : values()) {
			if (b.getValue().equalsIgnoreCase(v)) {
				return b;
			}
		}
		return null;
	}
}
