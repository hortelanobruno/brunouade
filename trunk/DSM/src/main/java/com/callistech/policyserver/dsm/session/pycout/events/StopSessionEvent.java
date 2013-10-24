package com.callistech.policyserver.dsm.session.pycout.events;

import com.callistech.policyserver.common.pyc.PYCEvent;
import com.callistech.policyserver.dsm.common.subscriber.SubscriberDS;

public class StopSessionEvent implements PYCEvent {

	private SubscriberDS subscriberDS;
	private Integer sessionId;
	private Long stopTime;

	public StopSessionEvent() {
		// TODO Auto-generated constructor stub
	}

	public SubscriberDS getSubscriberDS() {
		return subscriberDS;
	}

	public void setSubscriberDS(SubscriberDS subscriberDS) {
		this.subscriberDS = subscriberDS;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public Long getStopTime() {
		return stopTime;
	}

	public void setStopTime(Long stopTime) {
		this.stopTime = stopTime;
	}

	@Override
	public String toStringBeauty() {
		return toString();
	}

	@Override
	public String toString() {
		return "StopSessionEvent [subscriberDS=" + subscriberDS + ", sessionId=" + sessionId + ", stopTime=" + stopTime + "]";
	}

}
