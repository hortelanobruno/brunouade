package com.callistech.policyserver.dsm.meter.pycin;

import java.util.Queue;

import com.callistech.policyserver.common.pyc.PYCConsumerImplementation;
import com.callistech.policyserver.common.pyc.PYCEvent;
import com.callistech.policyserver.dsm.meter.MeterManager;
import com.callistech.policyserver.dsm.meter.pycin.events.StartSessionEvent;
import com.callistech.policyserver.dsm.meter.pycin.events.StopSessionEvent;

public class MeterInEventsC extends PYCConsumerImplementation {

	private MeterManager  manager;
	
	public MeterInEventsC(Queue<PYCEvent> queue, MeterManager  manager) {
		super(queue);
		this.manager=manager;
	}

	@Override
	protected void processEvent(PYCEvent event) {
		if(event instanceof StartSessionEvent){
			StartSessionEvent startEvent = (StartSessionEvent) event;
			manager.eventStartSession(startEvent.getDs());
		}else if(event instanceof StopSessionEvent){
			StopSessionEvent stopEvent = (StopSessionEvent) event;
			manager.eventStopSession(stopEvent.getSessionId());
		}
	}

}
