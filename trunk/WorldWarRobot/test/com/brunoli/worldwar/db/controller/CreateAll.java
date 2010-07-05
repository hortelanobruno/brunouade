package com.brunoli.worldwar.db.controller;

public class CreateAll {

	public static void main(String[] args){
		new CreateAll();
	}
	
	public CreateAll() {
		new UnitEntityControllerTest();
		new MissionEntityControllerTest();
		new BuildingEntityControllerTest();
	}
}
