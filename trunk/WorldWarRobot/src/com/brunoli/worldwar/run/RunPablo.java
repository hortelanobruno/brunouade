package com.brunoli.worldwar.run;

import com.brunoli.worldwar.test.RunnableAll;

public class RunPablo {

	private Double MINIMO_RENTABILIDAD = 10000D;// rentabilidad de money ganada en batallas
	private Integer DIFF_POINT_MINIMA = 0;//puntos minimo de dif con el rival
	private String urlInicioPablo = "http://wwar.storm8.com/index.php?version=1.54&premium=true&udid=7194cce3b2603abc0e34362ce7746ea1b5bc7544&pf=7258609AC8B028A08EC92603B5EB8F51&model=iPhone&sv=3.1.2";
	private String unitDefense = "Naval Tanker";
	private String unitAttack = "Harrier Jet";
	
	public static void main (String[] args){
		new RunPablo();
	}
	
	public RunPablo() {
		new RunnableAll(urlInicioPablo,unitAttack,unitDefense,MINIMO_RENTABILIDAD,DIFF_POINT_MINIMA);
	}
}
