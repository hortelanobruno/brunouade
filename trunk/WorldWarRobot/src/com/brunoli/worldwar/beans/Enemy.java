package com.brunoli.worldwar.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brunoli.worldwar.util.BuildingType;

public class Enemy {

	private String name;
	private Integer level;
	private Integer alianceSize;
	private String profileUrl;
	private EnemyProfile profile;

	public Enemy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + name + "\n");
		sb.append("Level: " + level + "\n");
		sb.append("Aliance Size: " + alianceSize + "\n");
		sb.append("URL: " + profileUrl + "\n");
		return sb.toString();
	}

	public String toStringWithProfile() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + name + "\n");
		sb.append("Level: " + level + "\n");
		sb.append("Aliance Size: " + alianceSize + "\n");
		sb.append("URL: " + profileUrl + "\n");
		sb.append("Profile: " + profile.toString() + "\n");
		return sb.toString();
	}

	public EnemyProfile getProfile() {
		return profile;
	}

	public void setProfile(EnemyProfile profile) {
		this.profile = profile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getAlianceSize() {
		return alianceSize;
	}

	public void setAlianceSize(Integer alianceSize) {
		this.alianceSize = alianceSize;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public Integer calcularPointDefense() {
		// units
		List<Unit> unitsOrdenadas = ordenarUnitsPorDefense();
		List<Building> buildingsOrdenadas = ordenarBuildingsPorDefense();
		return calcularPointDefense(unitsOrdenadas,buildingsOrdenadas);
	}

	private Integer calcularPointDefense(List<Unit> unitsOrdenadas,
			List<Building> buildingsOrdenadas) {
		Integer points = 0;
		for(Building b : buildingsOrdenadas){
			points += b.getTypeValue().intValue() * profile.getBuildings().get(b);
		}
		Integer cantUnits = 6 * alianceSize;
		Map<Unit,Integer> auxUnits = new HashMap<Unit,Integer>(profile.getUnits());
		Integer aux = null;
		for(int i = 0 ; i < cantUnits ; i++){
			for(Unit u : unitsOrdenadas){
				aux = auxUnits.get(u);
				if(aux>0){
					points += u.getDefense();
					aux--;
					auxUnits.put(u, aux);
					break;
				}
			}
		}
		return points;
	}

	private List<Building> ordenarBuildingsPorDefense() {
		List<Building> buildingsSorted = new ArrayList<Building>();
		for(Building b : profile.getBuildings()
				.keySet()){
			if(b.getCategory().equals(BuildingType.DEFENSE)){
				buildingsSorted.add(b);
			}
		}
		Collections.sort(buildingsSorted, new BuildingComparator());
		Collections.reverse(buildingsSorted);
		return buildingsSorted;
	}

	private List<Unit> ordenarUnitsPorDefense() {
		List<Unit> unitsSorted = new ArrayList<Unit>(profile.getUnits()
				.keySet());
		Collections.sort(unitsSorted, new UnitComparator());
		Collections.reverse(unitsSorted);
		return unitsSorted;
	}

	public class UnitComparator implements Comparator<Unit> {

		// Comparator interface requires defining compare method.
		public int compare(Unit unit1, Unit unit2) {
			return unit1.getDefense().compareTo(unit2.getDefense());
		}
	}
	
	public class BuildingComparator implements Comparator<Building> {

		// Comparator interface requires defining compare method.
		public int compare(Building building1, Building building2) {
			return building1.getTypeValue().compareTo(building2.getTypeValue());
		}
	}

	public Integer calcularPointIncome() {
		// units
		int points = 0;
		Integer cant = null;
		for(Building b : profile.getBuildings()
				.keySet()){
			if(b.getCategory().equals(BuildingType.INCOME)){
				cant = profile.getBuildings().get(b);
				if(cant!=null){
					points += cant*b.getTypeValue();
				}
			}
		}
		return points;
	}

}
