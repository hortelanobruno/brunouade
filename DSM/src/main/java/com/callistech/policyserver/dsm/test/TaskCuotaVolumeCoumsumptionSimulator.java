package com.callistech.policyserver.dsm.test;

import java.util.TreeSet;

import org.apache.commons.collections.FastTreeMap;

import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.dsm.common.QuotaVolume;

public class TaskCuotaVolumeCoumsumptionSimulator implements Runnable {

	private FastTreeMap consumptions = null;
	private TreeSet<Integer> sessionesVolume = new TreeSet<Integer>();
	private TreeSet<Integer> sessionesToAdd = new TreeSet<Integer>();
	private TreeSet<Integer> sessionesToRemove = new TreeSet<Integer>();
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
			Integer sessionId;
			for (Object obj : sessionesToAdd) {
				// for (int i = 0; i < 500000; i++) {
				// sesion = "" + (2000000 + i);
				sessionId = (Integer) obj;
				if (!sessionesVolume.contains(sessionId)) {
					sessionesVolume.add(sessionId);
					quota = new QuotaVolume();
					consumptions.put(sessionId, quota);
				}
			}
			sessionesToAdd.clear();
			// System.out.println("2");
			for (Object obj : sessionesToRemove) {
				sessionId = (Integer) obj;
				if (sessionesVolume.contains(sessionId)) {
					sessionesVolume.remove(sessionId);
					if (consumptions.containsKey(sessionId)) {
						consumptions.remove(sessionId);
					}
				}
			}
			sessionesToRemove.clear();
			// System.out.println("3");

			for (Object obj : sessionesVolume) {
				sessionId = (Integer) obj;
				if (consumptions.containsKey(sessionId)) {
					quota = (QuotaVolume) consumptions.get(sessionId);
				} else {
					quota = new QuotaVolume();
					consumptions.put(sessionId, quota);
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

	public void remove(Integer sessionId) {
		sessionesToRemove.add(sessionId);
	}

}
