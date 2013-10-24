package com.callistech.policyserver.dsm.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.collections.FastTreeMap;

import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.dsm.common.QuotaVolume;

public class TaskUpdateAndCheckConsumptionsSimulator implements Runnable {

	private TestMeterManager core;
	private TaskCuotaVolumeCoumsumptionSimulator cuotaSimulator;
	private FastTreeMap mapSesiones = new FastTreeMap();
	private Queue<DynamicSession> sessionesToAdd = new ConcurrentLinkedQueue<DynamicSession>();
	private Queue<Integer> sessionesToRemove = new ConcurrentLinkedQueue<Integer>();

	public TaskUpdateAndCheckConsumptionsSimulator(TestMeterManager core, TaskCuotaVolumeCoumsumptionSimulator cuotaSimulator) {
		this.core = core;
		this.cuotaSimulator = cuotaSimulator;
	}

	@Override
	public void run() {
		List<Integer> forDeplete = new ArrayList<Integer>();
		System.out.println(Calendar.getInstance().getTime() + ": Chequeando tiempo y volumen.");
		while (true) {
			// System.out.println(Calendar.getInstance().getTime() + ": Chequeando...");
			long spentTime = System.currentTimeMillis();
			// Me fijo las nuevas y las que tengo que borrar
			DynamicSession ds;
			Integer sesion;
			// System.out.println("a");
			int amountNewSessions = 0;
			for (Object obj : sessionesToAdd) {
				ds = (DynamicSession) obj;
				if (!mapSesiones.containsKey(ds.getSessionId())) {
					mapSesiones.put(ds.getSessionId(), ds);
					amountNewSessions++;
				}
			}
			sessionesToAdd.clear();
			// System.out.println("b");
			int amountDeleteSessions = 0;
			for (Object obj : sessionesToRemove) {
				sesion = (Integer) obj;
				if (mapSesiones.containsKey(sesion)) {
					forDeplete.add(sesion);
					amountDeleteSessions++;
				}
			}
			sessionesToRemove.clear();

			// Hago gilada de tiempo
			// System.out.println("c");
			updateAndCheckTime(forDeplete);
			// Hago gilada de volumen
			// System.out.println("d");
			updateAndCheckVolume(forDeplete);
			// Notify depleteds
			// System.out.println("e");
			int resultDeplete = 0;
			if (!forDeplete.isEmpty()) {
				resultDeplete = notifyDepleted(forDeplete);
				forDeplete.clear();
			}
			System.out.println(Calendar.getInstance().getTime() + ": Sesiones activas : " + mapSesiones.size() + ". Sesiones nuevas: " + amountNewSessions + ". Sesiones borradas externamente: " + amountDeleteSessions + ". Sesiones depleteadas: " + (resultDeplete - amountDeleteSessions) + ".");
			// System.out.println(Calendar.getInstance().getTime() + ": Termino chequeo.");
			if (mapSesiones.isEmpty()) {
				break;
			}
			spentTime = System.currentTimeMillis() - spentTime;
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

	private void updateAndCheckVolume(List<Integer> forDeplete) {
		FastTreeMap consumptions = cuotaSimulator.getAndResetConsumptions();
		String sesion;
		QuotaVolume qv;
		DynamicSession ds;
		for (Object obj : consumptions.keySet()) {
			sesion = (String) obj;
			qv = (QuotaVolume) consumptions.get(sesion);
			if (mapSesiones.containsKey(sesion)) {
				ds = (DynamicSession) mapSesiones.get(sesion);
				ds.countingVolume(qv.getDown(), qv.getUp());
				if (ds.isDepleted()) {
					forDeplete.add(ds.getSessionId());
				}
			}
		}
	}

	private void updateAndCheckTime(List<Integer> forDeplete) {
		DynamicSession ds;
		long timestamp = System.currentTimeMillis();
		for (Object obj : mapSesiones.values()) {
			ds = (DynamicSession) obj;
			ds.countTime(timestamp);
			if (ds.isDepleted()) {
				forDeplete.add(ds.getSessionId());
			}
		}
	}

	public int notifyDepleted(List<Integer> forDeplete) {
		DynamicSession ds;
		int aux = 0;
		for (Integer sessionId : forDeplete) {
			ds = (DynamicSession) mapSesiones.get(sessionId);
			mapSesiones.remove(sessionId);
			aux++;
		}
		return aux;
	}

	public void addSession(DynamicSession ds) {
		sessionesToAdd.add(ds);
	}

	public void removeSession(Integer sessionId) {
		sessionesToRemove.add(sessionId);
	}

	public boolean noMoreSessions() {
		return mapSesiones.isEmpty();
	}

}
