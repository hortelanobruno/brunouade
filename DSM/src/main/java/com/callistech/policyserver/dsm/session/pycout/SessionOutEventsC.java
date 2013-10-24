package com.callistech.policyserver.dsm.session.pycout;

import java.util.Queue;

import com.callistech.policyserver.common.pyc.PYCConsumerImplementation;
import com.callistech.policyserver.common.pyc.PYCEvent;
import com.callistech.policyserver.dsm.session.SessionManager;
import com.callistech.policyserver.dsm.session.pycout.events.StartSessionEvent;
import com.callistech.policyserver.dsm.session.pycout.events.StopSessionEvent;

public class SessionOutEventsC extends PYCConsumerImplementation {

	private SessionManager sessionManager;

	public SessionOutEventsC(Queue<PYCEvent> queue, SessionManager sessionManager) {
		super(queue);
		this.sessionManager = sessionManager;
	}

	@Override
	protected void processEvent(PYCEvent event) {
		if (event instanceof StartSessionEvent) {
			StartSessionEvent startSession = (StartSessionEvent) event;
			sessionManager.dispachStartSessionEvent(startSession.getSubscriberDS(), startSession.getDynamicService(), startSession.getDynamicSession());
		} else if(event instanceof StopSessionEvent){
			StopSessionEvent stopSession = (StopSessionEvent) event;
			sessionManager.dispachStopSessionEvent(stopSession.getSubscriberDS(), stopSession.getSessionId(), stopSession.getStopTime());
		}
	}

}
