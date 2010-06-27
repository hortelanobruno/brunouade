package com.brunoli.worldwar.db.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.brunoli.worldwar.db.controller.exceptions.NonexistentEntityException;
import com.brunoli.worldwar.db.entity.UnitEntity;
import com.brunoli.worldwar.util.UnitType;

public class UnitEntityControllerTest {

	public UnitEntityControllerTest() {
		solve();
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
				try{
					unit.setLevelRequiered(Integer.parseInt(line.split("\t")[1]));
				}catch(Exception ex){
					unit.setLevelRequiered(200);
				}
				unit.setName(line.split("\t")[2]);
				try{
					unit.setPrice(Long.parseLong(line.split("\t")[3].replaceAll(",", "")));
				}catch(Exception e){
					unit.setPrice(0L);
				}
				unit.setUnpkeep(Long.parseLong(line.split("\t")[4].replaceAll(",", "")));
				unit.setAttack(Integer.parseInt(line.split("\t")[5]));
				unit.setDefense(Integer.parseInt(line.split("\t")[6]));
				controller.create(unit);
				System.out.println("Name: "+unit.getName());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error. " + e.getMessage());
		} catch (NonexistentEntityException e) {
			System.out.println("Error2. " + e.getMessage());
		}

	}
}
