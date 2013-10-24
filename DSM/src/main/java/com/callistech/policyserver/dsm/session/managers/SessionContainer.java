package com.callistech.policyserver.dsm.session.managers;

import java.util.concurrent.ConcurrentHashMap;

import com.callistech.policyserver.dsm.common.DynamicSessionKey;

public class SessionContainer {

	private ConcurrentHashMap<DynamicSessionKey, Integer> sessions = new ConcurrentHashMap<DynamicSessionKey, Integer>();

	public SessionContainer() {

	}

	public boolean existSession(DynamicSessionKey key) {
		return sessions.contains(key);
	}

	public void updateSessionDueToStopSessionExternally(DynamicSessionKey key) {
		sessions.remove(key);
	}

	public void updateSessionDueToStartSession(DynamicSessionKey key, Integer sessionId) {
		sessions.put(key, sessionId);
	}

	public Integer getSessionId(DynamicSessionKey key) {
		return sessions.get(key);
	}

}
