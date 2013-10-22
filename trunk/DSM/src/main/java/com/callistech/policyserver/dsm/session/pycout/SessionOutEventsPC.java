package com.callistech.policyserver.dsm.session.pycout;

import com.callistech.policyserver.common.pyc.PYCConsumerImplementation;
import com.callistech.policyserver.common.pyc.PYCProducerConsumerImplementation;
import com.callistech.policyserver.dsm.common.DynamicService;
import com.callistech.policyserver.dsm.common.subscriber.SubscriberDS;
import com.callistech.policyserver.dsm.meter.pycout.MeterOutEventsC;
import com.callistech.policyserver.dsm.session.SessionModule;

public class SessionOutEventsPC extends PYCProducerConsumerImplementation {

	private SessionModule sessionModule;

	public SessionOutEventsPC(SessionModule sessionModule) {
		super("sessionOutRateLoge");
		this.sessionModule = sessionModule;
	}

	@Override
	protected PYCConsumerImplementation generateConsumer() {
		SessionOutEventsC consumer = new SessionOutEventsC(getQueue(), sessionModule.getSessionManager());
		return consumer;
	}

	public void startSession(SubscriberDS subscriberDS, DynamicService dynamicService) {
		// TODO Auto-generated method stub

	}
}
