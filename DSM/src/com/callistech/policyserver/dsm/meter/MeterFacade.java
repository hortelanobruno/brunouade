package com.callistech.policyserver.dsm.meter;

import org.apache.commons.collections.FastTreeMap;

import com.callistech.policyserver.dsm.common.DynamicSession;

public class MeterFacade {

	private MeterModule meterModule;

	public MeterFacade(MeterModule meterModule) {
		this.meterModule = meterModule;
	}

	public void startSession(DynamicSession ds) {
		meterModule.getInEventsPC().startSession(ds);
	}

	public void stopSession(String sessionId) {
		meterModule.getInEventsPC().stopSession(sessionId);
	}

	public void pauseSession(String sessionId) {

	}

	public void resumeSession(String sessionId) {

	}

	public void sendCountersPeriodics() {
		meterModule.getInEventsPC().sendCountersPeriodics();
	}

	public void subscribersConsumptionsNotifications(FastTreeMap consumptions) {
		meterModule.getInEventsPC().subscribersConsumptionsNotifications(consumptions);
	}
}
