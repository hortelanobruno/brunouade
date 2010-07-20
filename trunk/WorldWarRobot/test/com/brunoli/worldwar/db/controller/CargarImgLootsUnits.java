package com.brunoli.worldwar.db.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.brunoli.worldwar.db.controller.exceptions.NonexistentEntityException;
import com.brunoli.worldwar.db.entity.UnitEntity;

public class CargarImgLootsUnits {

	public CargarImgLootsUnits() {
		solver();
	}

	public static void main(String[] args){
		new CargarImgLootsUnits();
	}
	
	private void solver() {
		UnitEntityController con = new UnitEntityController();
		try {
			Scanner scan = new Scanner(new File("./files/data/urlImgLoots.log"));
			while (scan.hasNext()) {
				String name = scan.nextLine();
				String url = scan.nextLine();
				UnitEntity ent = con.findUnitEntityByName(name);
				if(ent!=null){
					ent.setUrlImg(url);
					con.edit(ent);
				}else{
					System.out.println("No se encontro unit: "+name);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NonexistentEntityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
