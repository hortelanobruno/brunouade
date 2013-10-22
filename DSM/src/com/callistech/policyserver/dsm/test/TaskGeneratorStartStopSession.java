package com.callistech.policyserver.dsm.test;

import java.util.TreeSet;

import com.callistech.policyserver.dsm.common.CountingType;
import com.callistech.policyserver.dsm.common.DSState;
import com.callistech.policyserver.dsm.common.DynamicSession;

public class TaskGeneratorStartStopSession implements Runnable {

	private int session_amount = 1000000;
	private TestMeterManager core;
	private TaskUpdateAndCheckConsumptionsSimulator check;
	private final int serviceId = 1;
	private int index = 1;
	private TreeSet<String> sessiones = new TreeSet<String>();

	public TaskGeneratorStartStopSession(TestMeterManager core, TaskUpdateAndCheckConsumptionsSimulator check) {
		this.core = core;
		this.check = check;
	}

	@Override
	public void run() {
		for (int i = 0; i < session_amount; i++) {
			// Creando session
			DynamicSession ds;
			if (i % 2 == 0) {
				ds = generateSessionVolume();
			} else {
				ds = generateSessionTime();
			}
			// System.out.println("Generando Session: " + ds);
			check.addSession(ds);

			// Borrando session
			if (i % 20 == 0) {
				int ul = (int) (Math.random() * (((i - 1) - 0) + 1));
				check.removeSession(generateSessionId("" + ul, serviceId));
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		}
	}

	public DynamicSession generateSessionVolume() {
		DynamicSession ds = new DynamicSession();
		ds.setSubscriberId("" + index);
		ds.setServiceId(serviceId);
		ds.setSessionId(generateSessionId(ds.getSubscriberId(), ds.getServiceId()));
		sessiones.add(ds.getSessionId());
		ds.setStartTime(System.currentTimeMillis());

		long ul = 2000 + (long) (Math.random() * ((10000 - 2000) + 1));

		ds.setUl_downVolume(ul);
		ds.setState(DSState.ACTIVATED);
		ds.setCountingType(CountingType.DOWN);
		index++;
		return ds;
	}

	public DynamicSession generateSessionTime() {
		DynamicSession ds = new DynamicSession();
		ds.setSubscriberId("" + index);
		ds.setServiceId(serviceId);
		ds.setSessionId(generateSessionId(ds.getSubscriberId(), ds.getServiceId()));
		sessiones.add(ds.getSessionId());
		ds.setStartTime(System.currentTimeMillis());

		long ul = 2 + (long) (Math.random() * ((10 - 2) + 1));

		ds.setUl_time(ul);
		ds.setState(DSState.ACTIVATED);
		ds.setCountingType(CountingType.TIME);
		index++;
		return ds;
	}

	private String generateSessionId(String sessionId, Integer serviceId) {
		return sessionId + "," + serviceId;
	}

}
