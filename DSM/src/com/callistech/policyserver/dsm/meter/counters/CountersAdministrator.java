package com.callistech.policyserver.dsm.meter.counters;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.collections.FastTreeMap;

import com.callistech.policyserver.dsm.common.DynamicSession;

public class CountersAdministrator {

	private FastTreeMap mapSesiones = new FastTreeMap();
	private Queue<DynamicSession> sessionesToAdd = new ConcurrentLinkedQueue<DynamicSession>();
	private Queue<String> sessionesToRemove = new ConcurrentLinkedQueue<String>();
	private ExecutorService poolThreand = Executors.newCachedThreadPool();
	private CountersUpdaterTask updaterTask = new CountersUpdaterTask(this);
	private CountersQuotaContainer quotaContainer = new CountersQuotaContainer();
	
	public CountersAdministrator() {
		// TODO Auto-generated constructor stub
	}
	
	public void start(){
		updaterTask.init();
		poolThreand.submit(updaterTask);
	}
	
	public void stop(){
		updaterTask.stop();
	}
	
	public FastTreeMap getMapSesiones() {
		return mapSesiones;
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

	public void addEventStartSession(DynamicSession ds) {
		sessionesToAdd.add(ds);
	}

	public void addEventStopSession(String sessionId) {
		sessionesToRemove.add(sessionId);
	}

	public void notifyDepletedSessions(List<String> forDeleteDueToDeplete) {
		// TODO Auto-generated method stub
		
	}
}
