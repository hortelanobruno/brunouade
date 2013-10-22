package com.callistech.policyserver.dsm.meter.pycout;

import java.util.Queue;

import com.callistech.policyserver.common.pyc.PYCConsumerImplementation;
import com.callistech.policyserver.common.pyc.PYCEvent;
import com.callistech.policyserver.dsm.meter.MeterManager;
import com.callistech.policyserver.dsm.meter.pycout.event.CallbackSendCountersPeriodicsEvent;
import com.callistech.policyserver.dsm.meter.pycout.event.DisableNotificationSubscriberConsumptionEvent;
import com.callistech.policyserver.dsm.meter.pycout.event.EnableNotificationSubscriberConsumptionEvent;
import com.callistech.policyserver.dsm.meter.pycout.event.SendQuotaVolumeUpdatesEvent;

public class MeterOutEventsC extends PYCConsumerImplementation {

	private MeterManager manager;

	public MeterOutEventsC(Queue<PYCEvent> queue, MeterManager manager) {
		super(queue);
		this.manager = manager;
	}

	@Override
	protected void processEvent(PYCEvent event) {
		if (event instanceof EnableNotificationSubscriberConsumptionEvent) {
			EnableNotificationSubscriberConsumptionEvent enableNotification = (EnableNotificationSubscriberConsumptionEvent) event;
			manager.dispachEnableNotificationSubscriberConsumption(enableNotification.getSubscriberId(), enableNotification.getSessionId());
		} else if (event instanceof DisableNotificationSubscriberConsumptionEvent) {
			DisableNotificationSubscriberConsumptionEvent disableNotification = (DisableNotificationSubscriberConsumptionEvent) event;
			manager.dispachDisableNotificationSubscriberConsumption(disableNotification.getSubscriberId(), disableNotification.getSessionId());
		} else if (event instanceof CallbackSendCountersPeriodicsEvent) {
			CallbackSendCountersPeriodicsEvent callbackSendCountersPeriodicsEvent = (CallbackSendCountersPeriodicsEvent) event;
			manager.dispachSendPeriodicCounters(callbackSendCountersPeriodicsEvent.getMapSubscribersCounters());
		} else if (event instanceof SendQuotaVolumeUpdatesEvent){
			SendQuotaVolumeUpdatesEvent quotaVolumeUpdates = (SendQuotaVolumeUpdatesEvent) event;
			manager.dispachSendQuotaVolumeUpdates(quotaVolumeUpdates.getConsumptions());
		}
	}

}
