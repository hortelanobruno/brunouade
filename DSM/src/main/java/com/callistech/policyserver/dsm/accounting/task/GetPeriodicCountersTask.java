package com.callistech.policyserver.dsm.accounting.task;

import org.apache.log4j.Logger;

import com.callistech.policyserver.dsm.accounting.AccountingModule;

public class GetPeriodicCountersTask implements Runnable {

	private AccountingModule accountingModule;
	private Logger logger = Logger.getLogger(getClass());

	public GetPeriodicCountersTask(AccountingModule accountingModule) {
		this.accountingModule = accountingModule;
	}

	@Override
	public void run() {
		logger.info("Requesting counters periodics...");
		accountingModule.getMeterFacade().sendCountersPeriodics();
		logger.info("Counters periodics requested.");
	}

}
