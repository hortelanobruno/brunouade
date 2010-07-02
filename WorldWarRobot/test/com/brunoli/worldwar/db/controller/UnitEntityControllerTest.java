package com.brunoli.worldwar.db.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.brunoli.worldwar.db.controller.exceptions.NonexistentEntityException;
import com.brunoli.worldwar.db.entity.UnitEntity;
import com.brunoli.worldwar.util.UnitType;

public class UnitEntityControllerTest {

	public UnitEntityControllerTest() {
		solve2();
	}

	public static void main(String[] args) {
		new UnitEntityControllerTest();
	}

	private void solve() {
		UnitEntityController controller = new UnitEntityController();
		UnitEntity unit;
		// ////////////////////////////
		try {
			controller.destroyAll();
			Scanner scan = new Scanner(new File("./files/units.txt"));
			String line;
			while (scan.hasNext()) {
				line = scan.nextLine();
				unit = new UnitEntity();
				unit.setUnitType(UnitType.getType(line.split("\t")[0]));
				try {
					unit.setLevelRequiered(Integer.parseInt(line.split("\t")[1]));
				} catch (Exception ex) {
					unit.setLevelRequiered(200);
				}
				unit.setName(line.split("\t")[2]);
				try {
					unit.setPrice(Long.parseLong(line.split("\t")[3]
							.replaceAll(",", "")));
				} catch (Exception e) {
					unit.setPrice(0L);
				}
				unit.setUnpkeep(Long.parseLong(line.split("\t")[4].replaceAll(
						",", "")));
				unit.setAttack(Integer.parseInt(line.split("\t")[5]));
				unit.setDefense(Integer.parseInt(line.split("\t")[6]));
				controller.create(unit);
				System.out.println("Name: " + unit.getName());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error. " + e.getMessage());
		} catch (NonexistentEntityException e) {
			System.out.println("Error2. " + e.getMessage());
		}

	}

	private void solve2() {
		UnitEntityController controller = new UnitEntityController();
		UnitEntity unit = null;
		// ////////////////////////////
		try {
			controller.destroyAll();
			Scanner scan = new Scanner(new File("./files/data/units.txt"));
			StringBuilder page = new StringBuilder();
			while (scan.hasNext()) {
				page.append(scan.nextLine() + "/n");
			}
			// Minigunners
			// Level: 1
			// Attack: 1
			// Defense: 1
			// Cost: $400
			// Disband: $200
			// Upkeep: $0
			for (String a : page.toString().split("/n")) {
				if (!a.isEmpty()) {
					if (a.contains("Level:")) {
						unit.setLevelRequiered(Integer.parseInt(a.replaceAll(
								"Level:", "").trim()));
					} else if (a.contains("Attack:")) {
						unit.setAttack(Integer.parseInt(a.replaceAll("Attack:",
								"").trim()));
					} else if (a.contains("Defense:")) {
						unit.setDefense(Integer.parseInt(a.replaceAll(
								"Defense:", "").trim()));
					} else if (a.contains("Cost:")) {
						if(!a.contains("Honor")){
							if (!a.contains("Loot")) {
								unit.setPrice(Long.parseLong(a
										.replaceAll("Cost:", "")
										.replaceAll("\\$", "").replaceAll(",", "")
										.trim()));
								unit.setLootType(false);
							} else {
								unit.setLootType(true);
							}
						}else{
							unit.setLootType(false);
						}
					}
					if (a.contains("elsePromotion:")) {

					} else if (a.contains("Disband:")) {

					} else if (a.contains("Upkeep:")) {
						unit.setUnpkeep(Long.parseLong(a
								.replaceAll("Upkeep:", "")
								.replaceAll("\\$", "").replaceAll(",", "")
								.trim()));
					} else {
						// NAME
						if (unit != null) {
							controller.create(unit);
						}
						unit = new UnitEntity();
						unit.setName(a.trim());
					}
				}
			}

			// unit = new UnitEntity();
			// unit.setUnitType(UnitType.getType(line.split("\t")[0]));
			// try{
			// unit.setLevelRequiered(Integer.parseInt(line.split("\t")[1]));
			// }catch(Exception ex){
			// unit.setLevelRequiered(200);
			// }
			// unit.setName(line.split("\t")[2]);
			// try{
			// unit.setPrice(Long.parseLong(line.split("\t")[3].replaceAll(",",
			// "")));
			// }catch(Exception e){
			// unit.setPrice(0L);
			// }
			// unit.setUnpkeep(Long.parseLong(line.split("\t")[4].replaceAll(",",
			// "")));
			// unit.setAttack(Integer.parseInt(line.split("\t")[5]));
			// unit.setDefense(Integer.parseInt(line.split("\t")[6]));
			// controller.create(unit);
			// System.out.println("Name: "+unit.getName());

			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error. " + e.getMessage());
		} catch (NonexistentEntityException e) {
			System.out.println("Error2. " + e.getMessage());
		}

	}
}
