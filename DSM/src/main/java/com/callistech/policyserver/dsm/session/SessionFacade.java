package com.callistech.policyserver.dsm.session;

import java.util.List;

import com.callistech.policyserver.dsm.common.DynamicSession;

public class SessionFacade {

	private SessionModule sessionModule;

	public SessionFacade(SessionModule sessionModule) {
		this.sessionModule = sessionModule;
	}

	public void startSessionB(String subscriberId, String dsd) {
		// llama
		// return sessionModule.getSessionInEventsPC().readResponse();
		sessionModule.getSessionManager().eventStartSession(subscriberId, dsd);
	}

	public void startSessionNB(String subscriberId, String dsd) {
		sessionModule.getSessionInEventsPC().startSession(subscriberId, dsd);
	}

	public void stopSessionB(String subscriberId, String dsd) {
		sessionModule.getSessionManager().eventStopSession(subscriberId, dsd);
	}

	public void stopSessionNB(String subscriberId, String dsd) {
		sessionModule.getSessionInEventsPC().stopSession(subscriberId, dsd);
	}

	public void sessionsDepleteds(List<DynamicSession> forDeleteDueToDeplete) {
		// TODO Auto-generated method stub

	}

}
