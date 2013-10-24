package com.callistech.policyserver.dsm.meter.pycin.events;

import com.callistech.policyserver.common.pyc.PYCEvent;

public class StopSessionEvent implements PYCEvent {

	private String subscriberId;
	private Integer sessionId;

	public StopSessionEvent(Integer sessionId2) {
		this.sessionId = sessionId2;
	}

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public String toStringBeauty() {
		return "StopSessionEvent: subscriberId: " + subscriberId + ". sessionId: " + sessionId + ".";
	}

}
