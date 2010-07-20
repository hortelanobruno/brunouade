package com.brunoli.worldwar.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brunoli.worldwar.util.BuildingType;
import com.brunoli.worldwar.util.Menus;

public class Profile {

	private Integer level;
	private Integer alianzeSize;
	private String startUrl;
	private Map<Menus, String> menuUrls;
	private Long money;
	private Integer staminaCurrent;
	private Integer staminaMax;
	private Integer healthCurrent;
	private Integer healthMax;
	private Integer energyCurrent;
	private Integer energyMax;
	private String experience;
	private List<Unit> units;
	private List<Building> buildings;

	public Profile() {
		menuUrls = new HashMap<Menus, String>();
		units = new ArrayList<Unit>();
		buildings = new ArrayList<Building>();
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getAlianzeSize() {
		return alianzeSize;
	}

	public void setAlianzeSize(Integer alianzeSize) {
		this.alianzeSize = alianzeSize;
	}

	public String getStartUrl() {
		return startUrl;
	}

	public void setStartUrl(String startUrl) {
		this.startUrl = startUrl;
	}

	public Map<Menus, String> getMenuUrls() {
		return menuUrls;
	}

	public void setMenuUrls(Map<Menus, String> menuUrls) {
		this.menuUrls = menuUrls;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public Integer getStaminaCurrent() {
		return staminaCurrent;
	}

	public void setStaminaCurrent(Integer staminaCurrent) {
		this.staminaCurrent = staminaCurrent;
	}

	public Integer getStaminaMax() {
		return staminaMax;
	}

	public void setStaminaMax(Integer staminaMax) {
		this.staminaMax = staminaMax;
	}

	public Integer getHealthCurrent() {
		return healthCurrent;
	}

	public void setHealthCurrent(Integer healthCurrent) {
		this.healthCurrent = healthCurrent;
	}

	public Integer getHealthMax() {
		return healthMax;
	}

	public void setHealthMax(Integer healthMax) {
		this.healthMax = healthMax;
	}

	public Integer getEnergyCurrent() {
		return energyCurrent;
	}

	public void setEnergyCurrent(Integer energyCurrent) {
		this.energyCurrent = energyCurrent;
	}

	public Integer getEnergyMax() {
		return energyMax;
	}

	public void setEnergyMax(Integer energyMax) {
		this.energyMax = energyMax;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	public List<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}

	public Integer calcularPointAttack() {
		// units
		List<Unit> unitsOrdenadas = ordenarUnitsPorAttack();
		return calcularPointAttack(unitsOrdenadas);
	}

	private Integer calcularPointAttack(List<Unit> unitsOrdenadas) {
		Integer points = 0;
		Integer cantUnits = 6 * alianzeSize;
		Map<Unit, Integer> auxUnits = new HashMap<Unit, Integer>();
		// llenar mapa
		for (Unit u : units) {
			if (u.getCantBuild() != null) {
				auxUnits.put(u, u.getCantBuild());
			}
		}
		Integer aux = null;
		for (int i = 0; i < cantUnits; i++) {
			for (Unit u : unitsOrdenadas) {
				if(auxUnits.containsKey(u)){
					aux = auxUnits.get(u);
					if (aux > 0) {
						points += u.getAttack();
						aux--;
						auxUnits.put(u, aux);
						break;
					}
				}
			}
		}
		return points;
	}

	private List<Unit> ordenarUnitsPorAttack() {
		List<Unit> unitsSorted = new ArrayList<Unit>(units);
		Collections.sort(unitsSorted, new UnitComparator());
		Collections.reverse(unitsSorted);
		return unitsSorted;
	}

	public class UnitComparator implements Comparator<Unit> {

		// Comparator interface requires defining compare method.
		public int compare(Unit unit1, Unit unit2) {
			return unit1.getAttack().compareTo(unit2.getAttack());
		}
	}

	public Integer calcularPointIncome() {
		int points = 0;
		for(Building b : buildings){
			if(b.getCategory().equals(BuildingType.INCOME)){
				if(b.getCantBuild()!=null){
					points += b.getCantBuild()*b.getTypeValue();
				}
			}
		}
		return points;
	}

}
