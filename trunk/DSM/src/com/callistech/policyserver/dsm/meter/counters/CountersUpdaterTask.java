package com.callistech.policyserver.dsm.meter.counters;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.FastTreeMap;
import org.apache.log4j.Logger;

import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.dsm.common.QuotaVolume;

public class CountersUpdaterTask implements Runnable {

	private Logger logger = Logger.getLogger(getClass());
	private CountersAdministrator countersAdministrator;
	private boolean started = false;
	private List<String> forDeleteDueToExternal = new ArrayList<String>();
	private List<String> forDeleteDueToDeplete = new ArrayList<String>();

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
		DynamicSession ds;
		String sesion;
		logger.info("CountersUpdaterTask started.");
		while (started) {
			long spentTime = System.currentTimeMillis();
			int amountNewSessions = 0;
			int amountDeleteSessions = 0;
			int amountDepleted = 0;
			// Me fijo hay nuevas y las que tengo que borrar
			// System.out.println("a");
			while (!countersAdministrator.getSessionesToAdd().isEmpty()) {
				ds = countersAdministrator.getSessionesToAdd().poll();
				if (ds != null) {
					if (addSession(ds)) {
						amountNewSessions++;
					}
				}
			}

			// System.out.println("b");
			while (!countersAdministrator.getSessionesToRemove().isEmpty()) {
				sesion = countersAdministrator.getSessionesToRemove().poll();
				if (sesion != null) {
					if (removeSession(sesion)) {
						amountDeleteSessions++;
					}
				}
			}

			if (!countersAdministrator.getMapSesiones().isEmpty()) {
				// Hago gilada de tiempo
				// System.out.println("c");
				updateAndCheckTime();
				// Hago gilada de volumen
				// System.out.println("d");
				updateAndCheckVolume();
				// Check and notify depleteds
				// System.out.println("e");
				if (!forDeleteDueToDeplete.isEmpty()) {
					amountDepleted = depleteSessions();
				}
				// Check deleteds
				// System.out.println("e");
				if (!forDeleteDueToDeplete.isEmpty()) {
					deleteSessionsExtenallydeleted();
				}
			}

			spentTime = System.currentTimeMillis() - spentTime;
			System.out.println(Calendar.getInstance().getTime() + ": Sesiones activas : " + countersAdministrator.getMapSesiones().size() + ". Sesiones nuevas: " + amountNewSessions + ". Sesiones borradas externamente: " + amountDeleteSessions + ". Sesiones depleteadas: " + amountDepleted
					+ ". Tardo: " + spentTime + ".");
			// System.out.println(Calendar.getInstance().getTime() + ": Termino chequeo.");

			try {
				long aux = 10 * 1000L;
				if (spentTime > aux) {
					System.out.println("No alcanzo para dormir.");
				} else {
					Thread.sleep(aux - spentTime);
				}
			} catch (Exception ex) {
			}
		}
		System.out.println(Calendar.getInstance().getTime() + ": Termino thread TaskUpdateAndCheckConsumptionsSimulator.");
	}

	private void updateAndCheckTime() {
		DynamicSession ds;
		long timestamp = System.currentTimeMillis();
		for (Object obj : countersAdministrator.getMapSesiones().values()) {
			ds = (DynamicSession) obj;
			ds.countTime(timestamp);
			if (ds.isDepleted()) {
				forDeleteDueToDeplete.add(ds.getSessionId());
			}
		}
	}

	private void updateAndCheckVolume() {
		FastTreeMap consumptions = countersAdministrator.getQuotaContainer().getAndResetConsumptions();
		String sesion;
		QuotaVolume qv;
		DynamicSession ds;
		for (Object obj : consumptions.keySet()) {
			sesion = (String) obj;
			qv = (QuotaVolume) consumptions.get(sesion);
			if (countersAdministrator.getMapSesiones().containsKey(sesion)) {
				ds = (DynamicSession) countersAdministrator.getMapSesiones().get(sesion);
				ds.countingVolume(qv.getDown(), qv.getUp());
				if (ds.isDepleted()) {
					forDeleteDueToDeplete.add(ds.getSessionId());
				}
			}
		}
	}

	public int depleteSessions() {
		int aux = 0;
		for (String sessionId : forDeleteDueToDeplete) {
			if (countersAdministrator.getMapSesiones().containsKey(sessionId)) {
				countersAdministrator.getMapSesiones().remove(sessionId);
			}
			aux++;
		}
		countersAdministrator.notifyDepletedSessions(forDeleteDueToDeplete);
		forDeleteDueToDeplete = new ArrayList<String>();
		return aux;
	}

	private void deleteSessionsExtenallydeleted() {
		for (String sessionId : forDeleteDueToExternal) {
			if (countersAdministrator.getMapSesiones().containsKey(sessionId)) {
				countersAdministrator.getMapSesiones().remove(sessionId);
			}
		}
		forDeleteDueToExternal.clear();
	}

	private boolean removeSession(String sesion) {
		if (countersAdministrator.getMapSesiones().containsKey(sesion)) {
			forDeleteDueToExternal.add(sesion);
			return true;
		}
		return false;
	}

	private boolean addSession(DynamicSession ds) {
		if (!countersAdministrator.getMapSesiones().containsKey(ds.getSessionId())) {
			countersAdministrator.getMapSesiones().put(ds.getSessionId(), ds);
			return true;
		}
		return false;
	}

}
