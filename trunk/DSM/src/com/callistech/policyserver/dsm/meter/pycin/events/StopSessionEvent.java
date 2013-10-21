package com.callistech.policyserver.dsm.meter.pycin.events;

import com.callistech.policyserver.common.pyc.PYCEvent;

public class StopSessionEvent implements PYCEvent {

	private String sessionId;

	public StopSessionEvent(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public String toStringBeauty() {
		// TODO Auto-generated method stub
		return null;
	}

}
