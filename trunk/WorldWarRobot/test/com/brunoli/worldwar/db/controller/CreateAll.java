package com.brunoli.worldwar.db.controller;

public class CreateAll {

	public static void main(String[] args){
		new CreateAll();
	}
	
	public CreateAll() {
		System.out.println("Cargando datos units...");
		new UnitEntityControllerTest();
		System.out.println("Fin cargar datos units.");
		System.out.println("Cargando datos missions...");
		new MissionEntityControllerTest();
		System.out.println("Fin cargar datos mission.");
		System.out.println("Cargando datos buildings...");
		new BuildingEntityControllerTest();
		System.out.println("Fin cargar datos buildings.");
		BuildingIncomeToCreateEntityController b = new BuildingIncomeToCreateEntityController();
		if(b.getBuildingEntityCount()==0){
			System.out.println("Cargando units to build...");
			new BuildingToCreateEntityControllerTest();
			new BuildingIncomeToCreateEntityControllerTest();
			System.out.println("Fin cargar units to build.");
		}
	}
}
