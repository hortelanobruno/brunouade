package com.callistech.policyserver.dsm.session.pycout;

import java.util.Queue;

import com.callistech.policyserver.common.pyc.PYCConsumerImplementation;
import com.callistech.policyserver.common.pyc.PYCEvent;
import com.callistech.policyserver.dsm.session.SessionManager;

public class SessionOutEventsC extends PYCConsumerImplementation {

	private SessionManager sessionManager;

	public SessionOutEventsC(Queue<PYCEvent> queue, SessionManager sessionManager) {
		super(queue);
		this.sessionManager = sessionManager;
	}

	@Override
	protected void processEvent(PYCEvent event) {
		// TODO Auto-generated method stub

	}

}
