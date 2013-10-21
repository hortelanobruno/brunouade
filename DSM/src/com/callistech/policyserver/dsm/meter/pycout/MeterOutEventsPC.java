package com.callistech.policyserver.dsm.meter.pycout;

import com.callistech.policyserver.common.pyc.PYCConsumerImplementation;
import com.callistech.policyserver.common.pyc.PYCProducerConsumerImplementation;
import com.callistech.policyserver.dsm.meter.MeterModule;
import com.callistech.policyserver.dsm.meter.pycout.event.DisableNotificationSubscriberConsumptionEvent;
import com.callistech.policyserver.dsm.meter.pycout.event.EnableNotificationSubscriberConsumptionEvent;

public class MeterOutEventsPC extends PYCProducerConsumerImplementation {

	private MeterModule meterModule;

	public MeterOutEventsPC(MeterModule meterModule) {
		super();
		this.meterModule = meterModule;
	}

	protected PYCConsumerImplementation generateConsumer() {
		MeterOutEventsC consumer = new MeterOutEventsC(getQueue(), meterModule.getManager());
		return consumer;
	}

	public void enableNotificationSubscriberConsumption(String subscriberId, String sessionId) {
		EnableNotificationSubscriberConsumptionEvent event = new EnableNotificationSubscriberConsumptionEvent();
		event.setSessionId(sessionId);
		event.setSubscriberId(subscriberId);
		super.addEvent(event);
	}

	public void disableNotificationSubscriberConsumption(String subscriberId, String sessionId) {
		DisableNotificationSubscriberConsumptionEvent event = new DisableNotificationSubscriberConsumptionEvent();
		event.setSessionId(sessionId);
		event.setSubscriberId(subscriberId);
		super.addEvent(event);
	}
}
