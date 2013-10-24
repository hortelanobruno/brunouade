package com.callistech.policyserver.dsm.session.pycin;

import org.apache.log4j.Logger;

import com.callistech.policyserver.common.pyc.PYCConsumerImplementation;
import com.callistech.policyserver.common.pyc.PYCProducerConsumerImplementation;
import com.callistech.policyserver.dsm.session.SessionModule;
import com.callistech.policyserver.dsm.session.pycin.events.StartSessionEvent;
import com.callistech.policyserver.dsm.session.pycin.events.StopSessionEvent;

public class SessionInEventsPC extends PYCProducerConsumerImplementation {

	private SessionModule sessionModule;
	private Logger logger = Logger.getLogger(getClass());

	public SessionInEventsPC(SessionModule sessionModule) {
		super("sessionInRateLog");
		this.sessionModule = sessionModule;
	}

	@Override
	protected PYCConsumerImplementation generateConsumer() {
		SessionInEventsC consumer = new SessionInEventsC(getQueue(), sessionModule.getSessionManager());
		return consumer;
	}

	public void startSession(String subscriberId, String dsd) {
		StartSessionEvent event = new StartSessionEvent();
		event.setDsd(dsd);
		event.setSubscriberId(subscriberId);
		super.addEvent(event);
	}

	public void stopSession(String subscriberId, String dsd) {
		StopSessionEvent event = new StopSessionEvent();
		event.setDsd(dsd);
		event.setSubscriberId(subscriberId);
		super.addEvent(event);
	}

}
