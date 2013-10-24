package com.callistech.policyserver.dsm.accounting.pycin.events;

import com.callistech.policyserver.common.pyc.PYCEvent;
import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.dsm.common.subscriber.SubscriberDS;

public class StartSessionEvent implements PYCEvent {

	private SubscriberDS subscriberDS;
	private DynamicSession dynamicSession;

	public StartSessionEvent() {
		// TODO Auto-generated constructor stub
	}

	public SubscriberDS getSubscriberDS() {
		return subscriberDS;
	}

	public void setSubscriberDS(SubscriberDS subscriberDS) {
		this.subscriberDS = subscriberDS;
	}

	public DynamicSession getDynamicSession() {
		return dynamicSession;
	}

	public void setDynamicSession(DynamicSession dynamicSession) {
		this.dynamicSession = dynamicSession;
	}

	@Override
	public String toStringBeauty() {
		return "StartSessionEvent";
	}

}
