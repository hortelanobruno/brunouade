package com.brunoli.worldwar.db.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.brunoli.worldwar.db.controller.exceptions.NonexistentEntityException;
import com.brunoli.worldwar.db.entity.BuildingEntity;
import com.brunoli.worldwar.util.BuildingType;
import com.brunoli.worldwar.util.UnitType;

public class BuildingEntityControllerTest {

	public BuildingEntityControllerTest() {
		solve2();
	}

	public static void main(String[] args) {
		new BuildingEntityControllerTest();
	}

	private void solve() {
		BuildingEntityController controller = new BuildingEntityController();
		BuildingEntity building;
		// ////////////////////////////
		try {
			controller.destroyAll();
			Scanner scan = new Scanner(new File("./files/buildings.txt"));
			String line;
			while (scan.hasNext()) {
				line = scan.nextLine();
				building = new BuildingEntity();
				building.setCategory(BuildingType.getType(line.split("\t")[0]));
				building.setName(line.split("\t")[1]);
				try {
					building.setLevelRequiered(Integer.parseInt(line
							.split("\t")[2]));
				} catch (Exception ex) {
					building.setLevelRequiered(200);
				}
				try {
					building.setInitialCost(Long.parseLong(line.split("\t")[3]
							.replaceAll(",", "")));
				} catch (Exception e) {
					building.setInitialCost(0L);
				}
				try{
					building.setNextCost(Long.parseLong(line.split("\t")[4]
					                             						.split(" ")[0].replaceAll(",", "")));
				}catch(Exception ex){
					building.setNextCost(0L);
				}
				switch (building.getCategory()) {
				case INCOME:
					building.setTypeValue(Long.parseLong(line.split("\t")[5].split(" ")[0].replaceAll(",", "")));
					break;
				case DEFENSE:
					building.setTypeValue(Long.parseLong(line.split("\t")[5].split(" ")[0].replaceAll("\\+", "")));
					break;
				case ENERGY:
					building.setTypeValue(Long.parseLong(line.split("\t")[5].split(" ")[0].replaceAll("\\+", "")));
					break;
				}

				controller.create(building);
				System.out.println("Name: " + building.getName());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error. " + e.getMessage());
		} catch (NonexistentEntityException e) {
			System.out.println("Error2. " + e.getMessage());
		}

	}
	
	private void solve2() {
		BuildingEntityController controller = new BuildingEntityController();
		BuildingEntity building = null;
		// ////////////////////////////
		try {
			controller.destroyAll();
			Scanner scan = new Scanner(new File("./files/data/buildings.txt"));
			StringBuilder page = new StringBuilder();
			while (scan.hasNext()) {
				page.append(scan.nextLine()+"/n");
			}
			for(String a : page.toString().split("/n")){
				if(!a.isEmpty()){
					if(a.contains("Level:")){
						building.setLevelRequiered(Integer.parseInt(a.replaceAll("Level:", "").trim()));
					}else if(a.contains("Income:")){
						building.setCategory(BuildingType.INCOME);
						building.setTypeValue(Long.parseLong(a.replaceAll("Income:", "").replaceAll(",", "").replaceAll("\\$", "").trim()));
					}else if(a.contains("Energy:")){
						building.setCategory(BuildingType.ENERGY);
						building.setTypeValue(Long.parseLong(a.replaceAll("Energy:", "").trim()));
					}else if(a.contains("Defense:")){
						building.setCategory(BuildingType.DEFENSE);
						building.setTypeValue(Long.parseLong(a.replaceAll("Defense:", "").trim()));
					}else if(a.contains("Start Cost:")){
						building.setInitialCost(Long.parseLong(a.replaceAll("Start Cost:", "").replaceAll(",", "").replaceAll("\\$", "").trim()));
					}else if(a.contains("Next Cost:")){
						building.setInitialCost(Long.parseLong(a.replaceAll("More Than Previous", "").replaceAll("Next Cost:", "").replaceAll(",", "").replaceAll("\\$", "").trim()));
					}else{
						//Namejajja
						if(building!=null){
							controller.create(building);
						}
						building = new BuildingEntity();
						building.setName(a.trim());
					}
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error. " + e.getMessage());
		} catch (NonexistentEntityException e) {
			System.out.println("Error2. " + e.getMessage());
		}

	}
}
