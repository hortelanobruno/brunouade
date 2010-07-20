package com.brunoli.worldwar.run;

import java.util.ArrayList;
import java.util.List;

import com.brunoli.worldwar.db.controller.CreateAll;
import com.brunoli.worldwar.test.RunnableAll;

public class RunBruno {
	
	//Attack
	//IAI Harop UAV
	//BAE Mantis UAV
	//
	//Defense
	//M80 Stiletto

	public final static Double MINIMO_RENTABILIDAD = 400000D;// rentabilidad de money ganada en batallas
	public final static Integer DIFF_POINT_MINIMA = 50;//puntos minimo de dif con el rival
	public final static Integer DIFF_INCOME_POINT_MINIMA = 100;//puntos minimo de dif con el rival
	private String urlInicioBrunoli = "http://wwar.storm8.com/aindex.php?version=a1.54&udid=200145da554359fa&pf=0f86e6501f5b2c2654be9ddfd212da28&model=HTC+Dream&sv=2.1-update1";
	private String alianzeCode = "GP4XK2";
	
	public static void main (String[] args){
		new RunBruno();
	}
	
	public RunBruno() {
		List<String> unitsAttack = generateUnitsAttack();
		List<String> unitsDefense = generateUnitsDefense();
		
		new CreateAll();
		new RunnableAll(alianzeCode,urlInicioBrunoli,unitsAttack,unitsDefense,MINIMO_RENTABILIDAD,DIFF_POINT_MINIMA,DIFF_INCOME_POINT_MINIMA);
	}

	private List<String> generateUnitsAttack() {
		List<String> units = new ArrayList<String>();
		units.add("Harrier Jet");
		units.add("B-1 Lancer");
		units.add("B-2 Bomber");
		return units;
	}
	
	private List<String> generateUnitsDefense(){
		List<String> units = new ArrayList<String>();
		units.add("Naval Tanker");
		units.add("Heavy Equipment Transporter");
		units.add("Horizon Frigate");
		return units;
		
	}
}
