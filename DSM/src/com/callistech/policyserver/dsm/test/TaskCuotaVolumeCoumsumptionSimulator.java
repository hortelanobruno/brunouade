package com.callistech.policyserver.dsm.test;

import java.util.TreeSet;

import org.apache.commons.collections.FastTreeMap;

import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.dsm.common.QuotaVolume;

public class TaskCuotaVolumeCoumsumptionSimulator implements Runnable {

	private FastTreeMap consumptions = null;
	private TreeSet<String> sessionesVolume = new TreeSet<String>();
	private TreeSet<String> sessionesToAdd = new TreeSet<String>();
	private TreeSet<String> sessionesToRemove = new TreeSet<String>();
	private boolean procesando = false;

	public TaskCuotaVolumeCoumsumptionSimulator(TestMeterManager testCountingInMemoryCuotaTimeAndVolume) {
		consumptions = new FastTreeMap();
	}

	@Override
	public void run() {
		while (true) {
			long tardo = System.currentTimeMillis();
			while (isProcesando()) {
				// System.out.println("Esta ocupado xq lo toy swapeandooooo...");
				try {
					Thread.sleep(100L);
				} catch (InterruptedException e) {
				}
			}
			setProcesando(true);
			// System.out.println("Cargando volumen...");
			QuotaVolume quota = null;
			// System.out.println("1");
			// Cargo y remuevo
			String sesion;
			for (Object obj : sessionesToAdd) {
				// for (int i = 0; i < 500000; i++) {
				// sesion = "" + (2000000 + i);
				sesion = (String) obj;
				if (!sessionesVolume.contains(sesion)) {
					sessionesVolume.add(sesion);
					quota = new QuotaVolume();
					consumptions.put(sesion, quota);
				}
			}
			sessionesToAdd.clear();
			// System.out.println("2");
			for (Object obj : sessionesToRemove) {
				sesion = (String) obj;
				if (sessionesVolume.contains(sesion)) {
					sessionesVolume.remove(sesion);
					if (consumptions.containsKey(sesion)) {
						consumptions.remove(sesion);
					}
				}
			}
			sessionesToRemove.clear();
			// System.out.println("3");

			for (Object obj : sessionesVolume) {
				sesion = (String) obj;
				if (consumptions.containsKey(sesion)) {
					quota = (QuotaVolume) consumptions.get(sesion);
				} else {
					quota = new QuotaVolume();
					consumptions.put(sesion, quota);
				}

				long u = 100 + (long) (Math.random() * ((1000 - 100) + 1));
				long d = 100 + (long) (Math.random() * ((1000 - 100) + 1));
				quota.sum(u, d);
			}
			// System.out.println("Fin Cargando volumen.");
			setProcesando(false);
			tardo = System.currentTimeMillis() - tardo;
			try {
				long aux = 60 * 1000;
				if (tardo > aux) {
					System.out.println("No alcanzo para dormir en TaskCuotaVolumeCoumsumptionSimulator.");
				} else {
					Thread.sleep((aux - tardo));
				}

			} catch (Exception ex) {
			}
		}
	}

	public FastTreeMap getAndResetConsumptions() {
		while (isProcesando()) {
			// System.out.println("Esta ocupado xq lo toy cargando...");
			try {
				Thread.sleep(100L);
			} catch (InterruptedException e) {
			}
		}
		setProcesando(true);
		FastTreeMap old = this.consumptions;
		this.consumptions = new FastTreeMap();
		setProcesando(false);
		return old;
	}

	public synchronized void setProcesando(boolean procesando) {
		this.procesando = procesando;
	}

	public synchronized boolean isProcesando() {
		return procesando;
	}

	public void add(DynamicSession ds) {
		sessionesToAdd.add(ds.getSessionId());
	}

	public void remove(DynamicSession ds) {
		sessionesToRemove.add(ds.getSessionId());
	}

	public void remove(String sessionId) {
		sessionesToRemove.add(sessionId);
	}

}
