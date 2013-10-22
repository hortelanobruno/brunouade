package com.callistech.policyserver.dsm.meter.pycout;

import java.util.Set;

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

	public void callbackSendPeriodicCounters(Set<String> total_active_subscribers, FastTreeMap mapSubscribersCounters, FastTreeMap mapServicesCounters) {
		CallbackSendCountersPeriodicsEvent event = new CallbackSendCountersPeriodicsEvent();
		event.setMapServicesCounters(mapServicesCounters);
		event.setMapSubscribersCounters(mapSubscribersCounters);
		event.setTotal_active_subscribers(total_active_subscribers);
		super.addEvent(event);
	}

	public void sendQuotaVolumeUpdates(FastTreeMap consumptions) {
		SendQuotaVolumeUpdatesEvent event = new SendQuotaVolumeUpdatesEvent();
		event.setConsumptions(consumptions);
		super.addEvent(event);
	}
}
