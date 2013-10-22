package com.callistech.policyserver.dsm.meter.counters;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.collections.FastTreeMap;

public class CountersQuotaContainer {

	private boolean procesando = false;
	private Queue<FastTreeMap> consumptions = new ConcurrentLinkedQueue<FastTreeMap>();

	public CountersQuotaContainer() {
	}

	public void updateConsumptions(FastTreeMap newConsumptions) {
		consumptions.offer(newConsumptions);
	}

	public FastTreeMap getAndResetConsumptions() {
		return consumptions.poll();
	}

	public synchronized void setProcesando(boolean procesando) {
		this.procesando = procesando;
	}

	public synchronized boolean isProcesando() {
		return procesando;
	}

}
