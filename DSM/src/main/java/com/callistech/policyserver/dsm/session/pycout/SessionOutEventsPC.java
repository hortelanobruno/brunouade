package com.callistech.policyserver.dsm.session.pycout;

import com.callistech.policyserver.common.pyc.PYCConsumerImplementation;
import com.callistech.policyserver.common.pyc.PYCProducerConsumerImplementation;
import com.callistech.policyserver.dsm.common.DynamicServiceLimits;
import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.dsm.common.subscriber.SubscriberDS;
import com.callistech.policyserver.dsm.session.SessionModule;
import com.callistech.policyserver.dsm.session.pycout.events.StartSessionEvent;
import com.callistech.policyserver.dsm.session.pycout.events.StopSessionEvent;

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

	public void startSession(SubscriberDS subscriberDS, DynamicServiceLimits dynamicServiceLimits, DynamicSession dynamicSession) {
		StartSessionEvent event = new StartSessionEvent();
		event.setSubscriberDS(subscriberDS);
		event.setDynamicSession(dynamicSession);
		event.setDynamicService(dynamicServiceLimits);
		super.addEvent(event);
	}

	public void stopSession(SubscriberDS subscriberDS, Integer sessionId, Long stopTime) {
		StopSessionEvent event = new StopSessionEvent();
		event.setSubscriberDS(subscriberDS);
		event.setStopTime(stopTime);
		event.setSessionId(sessionId);
		super.addEvent(event);
	}
}
