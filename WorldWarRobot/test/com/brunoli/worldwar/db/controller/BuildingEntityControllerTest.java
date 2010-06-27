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
		solve();
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
}
