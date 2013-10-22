package com.callistech.policyserver.dsm.meter.pycin;

import org.apache.commons.collections.FastTreeMap;
import org.apache.log4j.Logger;

import com.callistech.policyserver.common.pyc.PYCConsumerImplementation;
import com.callistech.policyserver.common.pyc.PYCProducerConsumerImplementation;
import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.dsm.meter.MeterModule;
import com.callistech.policyserver.dsm.meter.pycin.events.SendCountersPeriodicsEvent;
import com.callistech.policyserver.dsm.meter.pycin.events.StartSessionEvent;
import com.callistech.policyserver.dsm.meter.pycin.events.StopSessionEvent;
import com.callistech.policyserver.dsm.meter.pycin.events.SubscriberConsumptionEvent;

public class MeterInEventsPC extends PYCProducerConsumerImplementation {

	private MeterModule meterModule;
	private Logger logger = Logger.getLogger(getClass());

	public MeterInEventsPC(MeterModule meterModule) {
		super("meterInRateLog");
		this.meterModule = meterModule;
	}

	protected PYCConsumerImplementation generateConsumer() {
		MeterInEventsC consumer = new MeterInEventsC(getQueue(), meterModule.getManager());
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

	public void sendCountersPeriodics() {
		SendCountersPeriodicsEvent event = new SendCountersPeriodicsEvent();
		super.addEvent(event);
	}

	public void subscribersConsumptionsNotifications(FastTreeMap consumptions) {
		SubscriberConsumptionEvent subscriberConsumptions = new SubscriberConsumptionEvent();
		subscriberConsumptions.setConsumptions(consumptions);
		super.addEvent(subscriberConsumptions);
	}
}
