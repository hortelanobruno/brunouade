package com.callistech.policyserver.dsm.accounting;

import org.apache.log4j.Logger;

import com.callistech.policyserver.dsm.accounting.pycin.AccountingInEventsPC;
import com.callistech.policyserver.dsm.meter.MeterFacade;

public class AccountingModule {

	private AccountingFacade accountingFacade;
	private AccountingManager accountingManager;
	private AccountingInEventsPC accountingInEventsPC;
	private MeterFacade meterFacade;
	private Logger logger = Logger.getLogger(getClass());

	public AccountingModule() {
		accountingInEventsPC = new AccountingInEventsPC(this);
		accountingFacade = new AccountingFacade(this);
		accountingManager = new AccountingManager(this);
	}

	public void start() {
		accountingInEventsPC.start();
		accountingManager.start();
	}

	public void stop() {
		accountingManager.stop();
		accountingInEventsPC.stop();
	}

	public AccountingInEventsPC getAccountingInEventsPC() {
		return accountingInEventsPC;
	}

	public AccountingFacade getAccountingFacade() {
		return accountingFacade;
	}

	public AccountingManager getAccountingManager() {
		return accountingManager;
	}

	public void setMeterFacade(MeterFacade meterFacade) {
		this.meterFacade = meterFacade;
	}

	public MeterFacade getMeterFacade() {
		return meterFacade;
	}
}
