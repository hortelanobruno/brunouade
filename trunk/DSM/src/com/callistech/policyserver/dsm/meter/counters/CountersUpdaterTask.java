package com.callistech.policyserver.dsm.meter.counters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.FastTreeMap;
import org.apache.log4j.Logger;

import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.dsm.common.QuotaVolume;
import com.callistech.policyserver.dsm.common.counters.ServiceCounter;
import com.callistech.policyserver.dsm.common.counters.SubscriberCounter;

public class CountersUpdaterTask implements Runnable {

	// Data mapa periodicos
	private Set<String> total_active_subscribers = new HashSet<String>();
	private FastTreeMap mapSubscribersCounters = new FastTreeMap();
	private FastTreeMap mapServicesCounters = new FastTreeMap();
	// Fin data periodica
	private Logger logger = Logger.getLogger(getClass());
	private CountersAdministrator countersAdministrator;
	private boolean started = false;
	private List<String> forDeleteDueToExternal = new ArrayList<String>();
	private List<DynamicSession> forDeleteDueToDeplete = new ArrayList<DynamicSession>();

	public CountersUpdaterTask(CountersAdministrator countersAdministrator) {
		this.countersAdministrator = countersAdministrator;
	}

	public void init() {
		started = true;
	}

	public void stop() {
		started = false;
	}

	@Override
	public void run() {
		try {
			DynamicSession ds;
			String sesion;
			logger.info("CountersUpdaterTask started.");
			while (started) {
				long spentTime = System.currentTimeMillis();
				int amountNewSessions = 0;
				int amountDeleteSessions = 0;
				int amountDepleted = 0;
				// Me fijo hay nuevas y las que tengo que borrar
				logger.info("a");
				while (!countersAdministrator.getSessionesToAdd().isEmpty()) {
					ds = countersAdministrator.getSessionesToAdd().poll();
					if (ds != null) {
						if (addSession(ds)) {
							amountNewSessions++;
						}
					}
				}
				logger.info("b");
				while (!countersAdministrator.getSessionesToRemove().isEmpty()) {
					sesion = countersAdministrator.getSessionesToRemove().poll();
					if (sesion != null) {
						if (removeSession(sesion)) {
							amountDeleteSessions++;
						}
					}
				}
				logger.info("c");
				// Cuento tiempo y volumen
				if (!countersAdministrator.getMapSesionesCounters().isEmpty()) {
					// Hago gilada de tiempo
					logger.info("d");
					updateAndCheckTime();
					// Hago gilada de volumen
					logger.info("e");
					updateAndCheckVolume();
					// Check and notify depleteds
					logger.info("f");
					if (!forDeleteDueToDeplete.isEmpty()) {
						amountDepleted = depleteSessions();
					}
					// Check deleteds
					logger.info("g");
					if (!forDeleteDueToExternal.isEmpty()) {
						deleteSessionsExtenallydeleted();
					}
					// Check reset and send counters
					logger.info("h");
					if (countersAdministrator.getResetAndSendPeriodicCounters()) {
						countersAdministrator.sendPeriodicCounters(total_active_subscribers, mapSubscribersCounters, mapServicesCounters);
						resetPeriodicCounters();
					}
				}
				logger.info("i");
				spentTime = System.currentTimeMillis() - spentTime;
				logger.info("Sesiones activas : " + countersAdministrator.getMapSesionesCounters().size() + ". Sesiones nuevas: " + amountNewSessions + ". Sesiones borradas externamente: " + amountDeleteSessions + ". Sesiones depleteadas: " + amountDepleted + ". Tardo: " + spentTime + ".");

				try {
					long aux = 10 * 1000L;
					if (spentTime > aux) {
						logger.warn("No alcanzo para dormir.");
					} else {
						Thread.sleep(aux - spentTime);
					}
				} catch (Exception ex) {
				}
			}
			logger.info("Termino thread TaskUpdateAndCheckConsumptionsSimulator.");
		} catch (Exception ex) {
			logger.error("ERROR en CountersUpdaterTask run.", ex);
		}
	}

	private void resetPeriodicCounters() {
		total_active_subscribers = new HashSet<String>();
		mapSubscribersCounters = new FastTreeMap();
		mapServicesCounters = new FastTreeMap();
	}

	private void updateAndCheckTime() {
		DynamicSession ds;
		long timestamp = System.currentTimeMillis();
		for (Object obj : countersAdministrator.getMapSesionesCounters().values()) {
			ds = (DynamicSession) obj;
			int time = ds.countTime(timestamp);
			if (time > 0) {
				updateTimeCountersServicesAndSubscribers(ds, time);
			}
			if (ds.isDepleted()) {
				forDeleteDueToDeplete.add(ds);
			}
		}
	}

	private void updateAndCheckVolume() {
		String sesion;
		QuotaVolume qv;
		DynamicSession ds;
		FastTreeMap consumptions = countersAdministrator.getQuotaContainer().getAndResetConsumptions();
		if (consumptions != null) {
			for (Object obj : consumptions.keySet()) {
				sesion = (String) obj;
				qv = (QuotaVolume) consumptions.get(sesion);
				if (countersAdministrator.getMapSesionesCounters().containsKey(sesion)) {
					ds = (DynamicSession) countersAdministrator.getMapSesionesCounters().get(sesion);
					ds.countingVolume(qv.getDown(), qv.getUp());
					updateVolumeCountersServicesAndSubscribers(ds, qv);
					if (ds.isDepleted()) {
						forDeleteDueToDeplete.add(ds);
					}
				}
			}
		}
	}

	private void updateVolumeCountersServicesAndSubscribers(DynamicSession ds, QuotaVolume qv) {
		// Actualizo contadores de servicio
		ServiceCounter serviceCounter = null;
		if (!mapServicesCounters.containsKey(ds.getServiceId())) {
			serviceCounter = new ServiceCounter();
			serviceCounter.setDsId(ds.getServiceId());
			mapServicesCounters.put(ds.getServiceId(), serviceCounter);
		} else {
			serviceCounter = (ServiceCounter) mapServicesCounters.get(ds.getServiceId());
		}
		serviceCounter.updateVolumeCounter(ds.getSubscriberId(), ds.getSessionId(), qv);
		// Actualizo total active subscribers
		total_active_subscribers.add(ds.getSubscriberId());
		// Actualizo contadores de subscriber
		SubscriberCounter subscriberCounter = null;
		if (!mapSubscribersCounters.containsKey(ds.getSubscriberId())) {
			subscriberCounter = new SubscriberCounter();
			subscriberCounter.setSubscriberId(ds.getSubscriberId());
			mapSubscribersCounters.put(ds.getSubscriberId(), subscriberCounter);
		} else {
			subscriberCounter = (SubscriberCounter) mapSubscribersCounters.get(ds.getSubscriberId());
		}
		subscriberCounter.updateVolumeCounter(ds.getServiceId(), ds.getSessionId(), qv);
	}

	private void updateTimeCountersServicesAndSubscribers(DynamicSession ds, int time) {
		// Actualizo contadores de servicio
		ServiceCounter serviceCounter = null;
		if (!mapServicesCounters.containsKey(ds.getServiceId())) {
			serviceCounter = new ServiceCounter();
			serviceCounter.setDsId(ds.getServiceId());
			mapServicesCounters.put(ds.getServiceId(), serviceCounter);
		} else {
			serviceCounter = (ServiceCounter) mapServicesCounters.get(ds.getServiceId());
		}
		serviceCounter.updateTimeCounter(ds.getSubscriberId(), ds.getSessionId(), time);
		// Actualizo total active subscribers
		total_active_subscribers.add(ds.getSubscriberId());
		// Actualizo contadores de subscriber
		SubscriberCounter subscriberCounter = null;
		if (!mapSubscribersCounters.containsKey(ds.getSubscriberId())) {
			subscriberCounter = new SubscriberCounter();
			subscriberCounter.setSubscriberId(ds.getSubscriberId());
			mapSubscribersCounters.put(ds.getSubscriberId(), subscriberCounter);
		} else {
			subscriberCounter = (SubscriberCounter) mapSubscribersCounters.get(ds.getSubscriberId());
		}
		subscriberCounter.updateTimeCounter(ds.getServiceId(), ds.getSessionId(), time);
	}

	public int depleteSessions() {
		int aux = 0;
		for (DynamicSession ds : forDeleteDueToDeplete) {
			if (countersAdministrator.getMapSesionesCounters().containsKey(ds.getSessionId())) {
				countersAdministrator.getMapSesionesCounters().remove(ds.getSessionId());
			}
			aux++;
		}
		countersAdministrator.notifyDepletedSessions(forDeleteDueToDeplete);
		forDeleteDueToDeplete = new ArrayList<DynamicSession>();
		return aux;
	}

	private void deleteSessionsExtenallydeleted() {
		for (String sessionId : forDeleteDueToExternal) {
			if (countersAdministrator.getMapSesionesCounters().containsKey(sessionId)) {
				countersAdministrator.getMapSesionesCounters().remove(sessionId);
			}
		}
		forDeleteDueToExternal.clear();
	}

	private boolean removeSession(String sesion) {
		if (countersAdministrator.getMapSesionesCounters().containsKey(sesion)) {
			forDeleteDueToExternal.add(sesion);
			return true;
		}
		return false;
	}

	private boolean addSession(DynamicSession ds) {
		if (!countersAdministrator.getMapSesionesCounters().containsKey(ds.getSessionId())) {
			countersAdministrator.getMapSesionesCounters().put(ds.getSessionId(), ds);
			return true;
		}
		return false;
	}

}
