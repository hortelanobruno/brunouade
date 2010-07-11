package com.brunoli.worldwar.run;

import com.brunoli.worldwar.test.RunnableAll;

public class RunBruno {

	public final static Double MINIMO_RENTABILIDAD = 1000000D;// rentabilidad de money ganada en batallas
	public final static Integer DIFF_POINT_MINIMA = 0;//puntos minimo de dif con el rival
	private String urlInicioBrunoli = "http://wwar.storm8.com/aindex.php?version=a1.54&udid=200145da554359fa&pf=0f86e6501f5b2c2654be9ddfd212da28&model=HTC+Dream&sv=2.1-update1";
	private String unitDefense = "Horizon Frigate";
	private String unitAttack = "IAI Harop UAV";
	
	public static void main (String[] args){
		new RunBruno();
	}
	
	public RunBruno() {
		new RunnableAll(urlInicioBrunoli,unitAttack,unitDefense,MINIMO_RENTABILIDAD,DIFF_POINT_MINIMA);
	}
}
