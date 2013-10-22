package com.callistech.policyserver.dsm.meter.counters;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.collections.FastTreeMap;
import org.apache.log4j.Logger;

import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.dsm.meter.MeterManager;

public class CountersAdministrator {

	private Logger logger = Logger.getLogger(getClass());
	private FastTreeMap mapSesionesCounters = new FastTreeMap();
	private MeterManager meterManager;
	private ExecutorService poolThreand = Executors.newCachedThreadPool();
	private CountersUpdaterTask updaterTask = new CountersUpdaterTask(this);
	private CountersQuotaContainer quotaContainer = new CountersQuotaContainer();
	//Gilada para procesar periodicamente
	private Queue<DynamicSession> sessionesToAdd = new ConcurrentLinkedQueue<DynamicSession>();
	private Queue<String> sessionesToRemove = new ConcurrentLinkedQueue<String>();
	private Boolean resetAndSendPeriodicCounters = false;

	public CountersAdministrator(MeterManager meterManager) {
		this.meterManager = meterManager;
	}

	public void start() {
		updaterTask.init();
		poolThreand.submit(updaterTask);
	}

	public void stop() {
		updaterTask.stop();
	}

	public Queue<DynamicSession> getSessionesToAdd() {
		return sessionesToAdd;
	}

	public Queue<String> getSessionesToRemove() {
		return sessionesToRemove;
	}

	public CountersQuotaContainer getQuotaContainer() {
		return quotaContainer;
	}

	public FastTreeMap getMapSesionesCounters() {
		return mapSesionesCounters;
	}

	/**
	 * Llega evento start session
	 * 
	 * @param ds
	 */
	public void addEventStartSession(DynamicSession ds) {
		sessionesToAdd.add(ds);
	}

	/**
	 * Llega evento stop session
	 * 
	 * @param sessionId
	 */
	public void addEventStopSession(String sessionId) {
		sessionesToRemove.add(sessionId);
	}

	/**
	 * Notificar sesion depleted
	 * 
	 * @param forDeleteDueToDeplete
	 */
	public void notifyDepletedSessions(List<DynamicSession> forDeleteDueToDeplete) {
		this.meterManager.dispachNotifyDepletedSessions(forDeleteDueToDeplete);
	}

	public void resetAndSendCountersPeriodics() {
		resetAndSendPeriodicCounters = true;
	}

	public Boolean getResetAndSendPeriodicCounters() {
		return resetAndSendPeriodicCounters;
	}

	public void sendPeriodicCounters(Set<String> total_active_subscribers, FastTreeMap mapSubscribersCounters, FastTreeMap mapServicesCounters) {
		// TODO Auto-generated method stub
		meterManager.callbackSendPeriodicCounters(total_active_subscribers,mapSubscribersCounters,mapServicesCounters);
		resetAndSendPeriodicCounters = false;
	}

	public void updateConsumptions(FastTreeMap newConsumptions) {
		quotaContainer.updateConsumptions(newConsumptions);
	}
}
