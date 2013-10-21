package com.callistech.policyserver.dsm.meter.pycout;

import java.util.Queue;

import com.callistech.policyserver.common.pyc.PYCConsumerImplementation;
import com.callistech.policyserver.common.pyc.PYCEvent;
import com.callistech.policyserver.dsm.meter.MeterManager;
import com.callistech.policyserver.dsm.meter.pycout.event.DisableNotificationSubscriberConsumptionEvent;
import com.callistech.policyserver.dsm.meter.pycout.event.EnableNotificationSubscriberConsumptionEvent;

public class MeterOutEventsC extends PYCConsumerImplementation {

	private MeterManager  manager;
	
	public MeterOutEventsC(Queue<PYCEvent> queue, MeterManager  manager) {
		super(queue);
		this.manager=manager;
	}

	@Override
	protected void processEvent(PYCEvent event) {
		if(event instanceof EnableNotificationSubscriberConsumptionEvent){
			EnableNotificationSubscriberConsumptionEvent enableNotification = (EnableNotificationSubscriberConsumptionEvent) event;
//			manager.eventStartSession(enableNotification.getDs());
		}else if(event instanceof DisableNotificationSubscriberConsumptionEvent){
			DisableNotificationSubscriberConsumptionEvent disableNotification = (DisableNotificationSubscriberConsumptionEvent) event;
//			manager.eventStopSession(stopEvent.getSessionId());
		}
	}

}
