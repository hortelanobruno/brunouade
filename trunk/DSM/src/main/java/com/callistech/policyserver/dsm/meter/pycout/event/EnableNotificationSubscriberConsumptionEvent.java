package com.callistech.policyserver.dsm.meter.pycout.event;

import com.callistech.policyserver.common.pyc.PYCEvent;

public class EnableNotificationSubscriberConsumptionEvent implements PYCEvent {

	private String subscriberId;
	private Integer sessionId;

	public EnableNotificationSubscriberConsumptionEvent() {
		// TODO Auto-generated constructor stub
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	@Override
	public String toStringBeauty() {
		return "EnableNotificationSubscriberConsumptionEvent: subscriberId: " + subscriberId + ". sessionId: " + sessionId + ".";
	}

}
