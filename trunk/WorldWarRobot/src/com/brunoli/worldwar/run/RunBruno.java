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

	public final static Double MINIMO_RENTABILIDAD = 50000D;// rentabilidad de money ganada en batallas
	public final static Integer DIFF_POINT_MINIMA = 50;//puntos minimo de dif con el rival
	private String urlInicioBrunoli = "http://wwar.storm8.com/aindex.php?version=a1.54&udid=200145da554359fa&pf=0f86e6501f5b2c2654be9ddfd212da28&model=HTC+Dream&sv=2.1-update1";
	private String unitDefense = "Naval Tanker";
	private String alianzeCode = "GP4XK2";
	
	public static void main (String[] args){
		new RunBruno();
	}
	
	public RunBruno() {
		List<String> unitsAttack = generateUnitsAttack();
		
		new CreateAll();
		new RunnableAll(alianzeCode,urlInicioBrunoli,unitsAttack,unitDefense,MINIMO_RENTABILIDAD,DIFF_POINT_MINIMA);
	}

	private List<String> generateUnitsAttack() {
		List<String> units = new ArrayList<String>();
		units.add("Harrier Jet");
//		units.add("B-1 Lancer");
//		units.add("B-2 Bomber");
		return units;
	}
}
