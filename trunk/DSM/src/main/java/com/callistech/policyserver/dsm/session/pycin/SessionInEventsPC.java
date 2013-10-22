package com.callistech.policyserver.dsm.session.pycin;

import org.apache.log4j.Logger;

import com.callistech.policyserver.common.pyc.PYCConsumerImplementation;
import com.callistech.policyserver.common.pyc.PYCProducerConsumerImplementation;
import com.callistech.policyserver.dsm.meter.pycin.MeterInEventsC;
import com.callistech.policyserver.dsm.session.SessionModule;

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

}
