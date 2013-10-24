package com.callistech.policyserver.dsm.session;

import org.apache.log4j.Logger;

import com.callistech.policyserver.af.configuration.AFConfiguration;
import com.callistech.policyserver.dsm.accounting.AccountingFacade;
import com.callistech.policyserver.dsm.core.DSMCore;
import com.callistech.policyserver.dsm.meter.MeterFacade;
import com.callistech.policyserver.dsm.policy.PolicyFacade;
import com.callistech.policyserver.dsm.session.managers.DBManager;
import com.callistech.policyserver.dsm.session.pycin.SessionInEventsPC;
import com.callistech.policyserver.dsm.session.pycout.SessionOutEventsPC;
import com.callistech.policyserver.psm.entities.vo.af.configuration.ConfigurationAFManager;

public class SessionModule {

	private DSMCore core;
	private SessionFacade sessionFacade;
	private SessionManager sessionManager;
	private SessionInEventsPC sessionInEventsPC;
	private SessionOutEventsPC sessionOutEventsPC;
	private PolicyFacade policyFacade;
	private MeterFacade meterFacade;
	private AccountingFacade accountingFacade;
	private Logger logger = Logger.getLogger(getClass());

	public SessionModule(DSMCore core) {
		this.core = core;
		sessionInEventsPC = new SessionInEventsPC(this);
		sessionOutEventsPC = new SessionOutEventsPC(this);
		sessionFacade = new SessionFacade(this);
		sessionManager = new SessionManager(this,core.getMax_concurrent_sessions());
	}

	public void start() {
		sessionOutEventsPC.start();
		sessionInEventsPC.start();
		sessionManager.start();
	}

	public void stop() {
		sessionManager.stop();
		sessionInEventsPC.stop();
		sessionOutEventsPC.stop();
	}

	public AFConfiguration getConfiguration() {
		return core.getConfiguration();
	}

	public ConfigurationAFManager getConfigurationAFManager() {
		return core.getConfigurationAFManager();
	}

	public SessionFacade getSessionFacade() {
		return sessionFacade;
	}

	public SessionInEventsPC getSessionInEventsPC() {
		return sessionInEventsPC;
	}

	public SessionManager getSessionManager() {
		return sessionManager;
	}

	public SessionOutEventsPC getSessionOutEventsPC() {
		return sessionOutEventsPC;
	}

	public PolicyFacade getPolicyFacade() {
		return policyFacade;
	}

	public void setPolicyFacade(PolicyFacade policyFacade) {
		this.policyFacade = policyFacade;
	}

	public MeterFacade getMeterFacade() {
		return meterFacade;
	}

	public void setMeterFacade(MeterFacade meterFacade) {
		this.meterFacade = meterFacade;
	}

	public AccountingFacade getAccountingFacade() {
		return accountingFacade;
	}

	public void setAccountingFacade(AccountingFacade accountingFacade) {
		this.accountingFacade = accountingFacade;
	}

	public void setDBManager(DBManager dbManager) {
		sessionManager.setDBManager(dbManager);
	}

}
