package com.callistech.policyserver.dsm.accounting.db;

import org.apache.commons.collections.FastTreeMap;

import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.dsm.session.managers.DBManager;

public class AccountingDBManager {

	private DBManager dbManager;

	public AccountingDBManager() {
	}

	public void setDBManager(DBManager dbManager) {
		this.dbManager = dbManager;
	}

	public void generatePeriodicDSRInDBReport(FastTreeMap mapServicesCounters, FastTreeMap mapSubscribersCounters, int total_active_subscribers) {
		// TODO Auto-generated method stub

	}

	public void quotaVolumeUpdates(FastTreeMap consumptions) {
		dbManager.quotaVolumeUpdates(consumptions);
	}

	public void startSession(DynamicSession ds) {
		dbManager.startSession(ds);
	}

	public void stopSession(DynamicSession ds) {
		dbManager.stopSession(ds.getSessionId());
	}

	public void pauseSession(DynamicSession ds) {
		// TODO Auto-generated method stub

	}

	public void resumeSession(DynamicSession ds) {
		// TODO Auto-generated method stub

	}

	public DynamicSession getDynamicSession(Integer sessionId) {
		return dbManager.dynamicSessionQuery(sessionId);
	}

}
