package com.brunoli.worldwar.test;

import java.util.Calendar;

public class RunnableMissions implements Runnable{
	
	public static void main (String[] args){
		new RunnableMissions();
	}
	
	
	public RunnableMissions() {
		Thread t = new Thread(this);
		t.run();
	}

	@Override
	public void run() {
		TestDoAllMission test = new TestDoAllMission();
		while(true){
			System.out.println(Calendar.getInstance().getTime().toLocaleString()+" Ejecutando todas las misiones.");
			test.ejecutarAllMissions();
			System.out.println(Calendar.getInstance().getTime().toLocaleString()+" Fin Ejecutando todas las misiones.");
			try {
				Thread.sleep(1000 * 60 * 60 * 2 + (30 * 60 * 1000));
			} catch (InterruptedException e) {
			}
		}
	}

}
