package com.callistech.policyserver.dsm.meter;

import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.dsm.meter.pycin.events.StartSessionEvent;

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

	public void getCountersPeriodics() {

	}
}
