package com.callistech.policyserver.dsm.meter.pycin;

import com.callistech.policyserver.common.pyc.PYCConsumerImplementation;
import com.callistech.policyserver.common.pyc.PYCProducerConsumerImplementation;
import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.dsm.meter.MeterModule;
import com.callistech.policyserver.dsm.meter.pycin.events.StartSessionEvent;
import com.callistech.policyserver.dsm.meter.pycin.events.StopSessionEvent;

public class MeterInEventsPC extends PYCProducerConsumerImplementation {
	
	private MeterModule meterModule;
	
	public MeterInEventsPC(MeterModule meterModule) {
		super();
		this.meterModule=meterModule;
	}

	protected PYCConsumerImplementation generateConsumer() {
		MeterInEventsC consumer = new MeterInEventsC(getQueue(),meterModule.getManager());
		return consumer;
	}

	public void startSession(DynamicSession ds) {
		StartSessionEvent event = new StartSessionEvent(ds);
		super.addEvent(event);
	}

	public void stopSession(String sessionId) {
		StopSessionEvent event = new StopSessionEvent(sessionId);
		super.addEvent(event);
	}
}
