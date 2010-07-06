package com.brunoli.worldwar.db.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.brunoli.worldwar.db.entity.BuildingIncomeToCreateEntity;

public class BuildingIncomeToCreateEntityControllerTest {

	public static void main(String[] args){
		new BuildingIncomeToCreateEntityControllerTest();
	}
	
	public BuildingIncomeToCreateEntityControllerTest() {
		try {
			Scanner scan = new Scanner(new File("./files/data/buildingIncomeToCreate.txt"));
			String line ;
			BuildingIncomeToCreateEntityController controller = new BuildingIncomeToCreateEntityController();
			BuildingIncomeToCreateEntity entity;
			while(scan.hasNext()){
				line = scan.nextLine();
				entity = new BuildingIncomeToCreateEntity();
				entity.setName(line);
				controller.create(entity);
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		BuildingToCreateEntityController controller = new BuildingToCreateEntityController();
//		List<BuildingToCreateEntity> builds = controller.findBuildingEntityEntities(1, 0);
//		for (BuildingToCreateEntity b : builds) {
//			System.out.println("b: "+b.getName());
//		}
	}
}
