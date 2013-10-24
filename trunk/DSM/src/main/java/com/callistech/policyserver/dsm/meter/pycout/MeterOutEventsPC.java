package com.callistech.policyserver.dsm.meter.pycout;

import org.apache.commons.collections.FastTreeMap;

import com.callistech.policyserver.common.pyc.PYCConsumerImplementation;
import com.callistech.policyserver.common.pyc.PYCProducerConsumerImplementation;
import com.callistech.policyserver.dsm.meter.MeterModule;
import com.callistech.policyserver.dsm.meter.pycout.event.CallbackSendCountersPeriodicsEvent;
import com.callistech.policyserver.dsm.meter.pycout.event.DisableNotificationSubscriberConsumptionEvent;
import com.callistech.policyserver.dsm.meter.pycout.event.EnableNotificationSubscriberConsumptionEvent;
import com.callistech.policyserver.dsm.meter.pycout.event.SendQuotaVolumeUpdatesEvent;

public class MeterOutEventsPC extends PYCProducerConsumerImplementation {

	private MeterModule meterModule;

	public MeterOutEventsPC(MeterModule meterModule) {
		super("meterOutRateLoge");
		this.meterModule = meterModule;
	}

	protected PYCConsumerImplementation generateConsumer() {
		MeterOutEventsC consumer = new MeterOutEventsC(getQueue(), meterModule.getManager());
		return consumer;
	}

	public void enableNotificationSubscriberConsumption(String subscriberId, Integer integer) {
		EnableNotificationSubscriberConsumptionEvent event = new EnableNotificationSubscriberConsumptionEvent();
		event.setSessionId(integer);
		event.setSubscriberId(subscriberId);
		super.addEvent(event);
	}

	public void disableNotificationSubscriberConsumption(String subscriberId, Integer sessionId) {
		DisableNotificationSubscriberConsumptionEvent event = new DisableNotificationSubscriberConsumptionEvent();
		event.setSessionId(sessionId);
		event.setSubscriberId(subscriberId);
		super.addEvent(event);
	}

	public void callbackSendPeriodicCounters(FastTreeMap mapSubscribersCounters) {
		CallbackSendCountersPeriodicsEvent event = new CallbackSendCountersPeriodicsEvent();
		event.setMapSubscribersCounters(mapSubscribersCounters);
		super.addEvent(event);
	}

	public void sendQuotaVolumeUpdates(FastTreeMap consumptions) {
		SendQuotaVolumeUpdatesEvent event = new SendQuotaVolumeUpdatesEvent();
		event.setConsumptions(consumptions);
		super.addEvent(event);
	}
}
