package com.brunoli.worldwar.util;

public enum BuildingEnum {
	SUPPLY_DEPLOY("Supply Depot"), BUNKER("Bunker"), REFINERY("Refinery"), BARRACKS(
			"Barracks"), GUARD_TOWER("Guard Tower"), WEAPONS_FACTORY(
			"Weapons Factory"), ANTI_AIRCRAFT_LAUNCHER("Anti-Aircraft Launcher"), POWER_PLANT(
			"Power Plant"), TURRET("Turret"), AIRFIELD("Airfield"), LANDMINE_FIELD(
			"Landmine Field"), OIL_RIG("Oil Rig");

	private String value;

	private BuildingEnum(String v) {
		this.value = v;
	}

	public String getValue() {
		return value;
	}

	public static BuildingEnum getType(String v) {
		for (BuildingEnum b : values()) {
			if (b.getValue().equalsIgnoreCase(v)) {
				return b;
			}
		}
		return null;
	}
}
