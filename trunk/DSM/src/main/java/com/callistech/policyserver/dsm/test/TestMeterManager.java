package com.callistech.policyserver.dsm.test;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.callistech.policyserver.dsm.common.DynamicSession;

public class TestMeterManager {

	private final int amount_sessions_time = 5;
	private final int amount_sessions_volume = 5;
	private ExecutorService poolThreand = Executors.newCachedThreadPool();

	public static void main(String args[]) {
		new TestMeterManager();
	}

	public TestMeterManager() {
		solver();
	}

	private void solver() {
		System.out.println(Calendar.getInstance().getTime() + ": Inicio.");
		DynamicSession ds;
		System.out.println(Calendar.getInstance().getTime() + ": Creando sesiones.");
		TaskCuotaVolumeCoumsumptionSimulator cuotaSimulator = new TaskCuotaVolumeCoumsumptionSimulator(this);
		TaskUpdateAndCheckConsumptionsSimulator checkSimulator = new TaskUpdateAndCheckConsumptionsSimulator(this, cuotaSimulator);
		TaskGeneratorStartStopSession generatorSimulator = new TaskGeneratorStartStopSession(this, checkSimulator);
		for (int i = 0; i < amount_sessions_time; i++) {
			ds = generatorSimulator.generateSessionTime();
			checkSimulator.addSession(ds);
		}
		for (int i = 0; i < amount_sessions_volume; i++) {
			ds = generatorSimulator.generateSessionVolume();
			cuotaSimulator.add(ds);
			checkSimulator.addSession(ds);
		}
		System.out.println(Calendar.getInstance().getTime() + ": FIN Creando sesiones.");

		poolThreand.submit(cuotaSimulator);
		poolThreand.submit(checkSimulator);
		poolThreand.submit(generatorSimulator);
		System.out.println(Calendar.getInstance().getTime() + ": Sleepeo hasta terminar.");
		try {
			Thread.sleep(10 * 1000);
		} catch (Exception ex) {
		}
		while (true) {
			if (checkSimulator.noMoreSessions()) {
				System.out.println(Calendar.getInstance().getTime() + ": Termino.");
				System.exit(0);
			}
			try {
				Thread.sleep(1000);
			} catch (Exception ex) {
			}
		}
	}

}
