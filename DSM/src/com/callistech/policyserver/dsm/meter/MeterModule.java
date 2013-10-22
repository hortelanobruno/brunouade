package com.callistech.policyserver.dsm.meter;

import org.apache.log4j.Logger;

import com.callistech.policyserver.dsm.accounting.AccountingFacade;
import com.callistech.policyserver.dsm.meter.external.CollectorFacade;
import com.callistech.policyserver.dsm.meter.pycin.MeterInEventsPC;
import com.callistech.policyserver.dsm.meter.pycout.MeterOutEventsPC;
import com.callistech.policyserver.dsm.session.SessionFacade;

public class MeterModule {

	private MeterFacade facade;
	private MeterManager manager;
	private MeterInEventsPC inEventsPC;
	private MeterOutEventsPC outEventsPC;
	private CollectorFacade collectorFacade;
	private SessionFacade sessionFacade;
	private AccountingFacade accountingFacade;
	private Logger logger = Logger.getLogger(getClass());

	public MeterModule() {
		inEventsPC = new MeterInEventsPC(this);
		outEventsPC = new MeterOutEventsPC(this);
		facade = new MeterFacade(this);
		manager = new MeterManager(this);
	}

	public void start() {
		outEventsPC.start();
		inEventsPC.start();
		manager.start();
	}

	public void stop() {
		manager.stop();
		inEventsPC.stop();
		outEventsPC.stop();
	}

	public MeterInEventsPC getInEventsPC() {
		return inEventsPC;
	}

	public MeterOutEventsPC getOutEventsPC() {
		return outEventsPC;
	}

	public MeterFacade getFacade() {
		return facade;
	}

	public MeterManager getManager() {
		return manager;
	}

	public void setCollectorFacade(CollectorFacade collectorFacade) {
		this.collectorFacade = collectorFacade;
	}

	public CollectorFacade getCollectorFacade() {
		return collectorFacade;
	}

	public void setSessionFacade(SessionFacade sessionFacade) {
		this.sessionFacade = sessionFacade;
	}

	public SessionFacade getSessionFacade() {
		return sessionFacade;
	}

	public void setAccountingFacade(AccountingFacade accountingFacade) {
		this.accountingFacade = accountingFacade;
	}

	public AccountingFacade getAccountingFacade() {
		return accountingFacade;
	}
}
