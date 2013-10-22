package com.callistech.policyserver.dsm.meter.counters;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.collections.FastTreeMap;

public class CountersQuotaContainer {

	private boolean procesando = false;
	private Queue<FastTreeMap> consumptions = new ConcurrentLinkedQueue<FastTreeMap>();

	public CountersQuotaContainer() {
		// TODO Auto-generated constructor stub
	}

	public void updateConsumptions(FastTreeMap newConsumptions) {
		// while (isProcesando()) {
		// // System.out.println("Esta ocupado xq lo toy cargando...");
		// try {
		// Thread.sleep(100L);
		// } catch (InterruptedException e) {
		// }
		// }
		// setProcesando(true);
		// Procesando nuevos consumos
		// if (consumptions != null) {
		// String sesion;
		// QuotaVolume quota = null;
		// QuotaVolume newQuota = null;
		// for (Object obj : newConsumptions.keySet()) {
		// sesion = (String) obj;
		// newQuota = (QuotaVolume) newConsumptions.get(sesion);
		// if (consumptions.containsKey(sesion)) {
		// quota = (QuotaVolume) consumptions.get(sesion);
		// } else {
		// quota = new QuotaVolume();
		// consumptions.put(sesion, quota);
		// }
		//
		// quota.update(newQuota);
		// }
		// } else {
		// consumptions = newConsumptions;
		// }
		// setProcesando(false);
		consumptions.offer(newConsumptions);
	}

	public FastTreeMap getAndResetConsumptions() {
		// while (isProcesando()) {
		// // System.out.println("Esta ocupado xq lo toy cargando...");
		// try {
		// Thread.sleep(100L);
		// } catch (InterruptedException e) {
		// }
		// }
		// setProcesando(true);
		// FastTreeMap old = null;
		// if (consumptions != null) {
		// old = this.consumptions;
		// this.consumptions = new FastTreeMap();
		// }
		// setProcesando(false);
		// return old;
		return consumptions.poll();
	}

	public synchronized void setProcesando(boolean procesando) {
		this.procesando = procesando;
	}

	public synchronized boolean isProcesando() {
		return procesando;
	}

}
