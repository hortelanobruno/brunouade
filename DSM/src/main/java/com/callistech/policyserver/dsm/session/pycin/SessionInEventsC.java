package com.callistech.policyserver.dsm.session.pycin;

import java.util.Queue;

import com.callistech.policyserver.common.pyc.PYCConsumerImplementation;
import com.callistech.policyserver.common.pyc.PYCEvent;
import com.callistech.policyserver.dsm.session.SessionManager;
import com.callistech.policyserver.dsm.session.managers.result.StartSessionResult;
import com.callistech.policyserver.dsm.session.managers.result.StopSessionResult;
import com.callistech.policyserver.dsm.session.pycin.events.StartSessionEvent;
import com.callistech.policyserver.dsm.session.pycin.events.StopSessionEvent;

public class SessionInEventsC extends PYCConsumerImplementation {

	private SessionManager sessionManager;

	public SessionInEventsC(Queue<PYCEvent> queue, SessionManager sessionManager) {
		super(queue);
		this.sessionManager = sessionManager;
	}

	@Override
	protected void processEvent(PYCEvent event) {
		if (event != null) {
			if (event instanceof StartSessionEvent) {
				StartSessionEvent startEvent = (StartSessionEvent) event;
				StartSessionResult result = sessionManager.eventStartSession(startEvent.getSubscriberId(), startEvent.getDsd());
				logger.info("Response: " + result);
			} else if (event instanceof StopSessionEvent) {
				StopSessionEvent stopEvent = (StopSessionEvent) event;
				StopSessionResult result = sessionManager.eventStopSession(stopEvent.getSubscriberId(), stopEvent.getDsd());
				logger.info("Response: " + result);
			}
		}
	}

}
