package com.brunoli.worldwar.db.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.brunoli.worldwar.db.entity.BuildingToCreateEntity;

public class BuildingToCreateEntityControllerTest {

	public static void main(String[] args){
		new BuildingToCreateEntityControllerTest();
	}
	
	public BuildingToCreateEntityControllerTest() {
		try {
			Scanner scan = new Scanner(new File("./files/data/buildingToCreate.txt"));
			String line ;
			BuildingToCreateEntityController controller = new BuildingToCreateEntityController();
			BuildingToCreateEntity entity;
			while(scan.hasNext()){
				line = scan.nextLine();
				entity = new BuildingToCreateEntity();
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
