package com.callistech.policyserver.dsm.session;

import org.apache.log4j.Logger;

import com.callistech.policyserver.dsm.accounting.AccountingFacade;
import com.callistech.policyserver.dsm.meter.MeterFacade;
import com.callistech.policyserver.dsm.policy.PolicyFacade;
import com.callistech.policyserver.dsm.session.pycin.SessionInEventsPC;
import com.callistech.policyserver.dsm.session.pycout.SessionOutEventsPC;

public class SessionModule {

	private SessionFacade sessionFacade;
	private SessionManager sessionManager;
	private SessionInEventsPC sessionInEventsPC;
	private SessionOutEventsPC sessionOutEventsPC;
	private PolicyFacade policyFacade;
	private MeterFacade meterFacade;
	private AccountingFacade accountingFacade;
	private Logger logger = Logger.getLogger(getClass());

	public SessionModule() {
		sessionInEventsPC = new SessionInEventsPC(this);
		sessionOutEventsPC = new SessionOutEventsPC(this);
		sessionFacade = new SessionFacade(this);
		sessionManager = new SessionManager(this);
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

}
