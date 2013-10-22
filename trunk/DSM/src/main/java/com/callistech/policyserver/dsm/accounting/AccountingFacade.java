package com.callistech.policyserver.dsm.accounting;

import org.apache.commons.collections.FastTreeMap;

public class AccountingFacade {

	private AccountingModule accountingModule;

	public AccountingFacade(AccountingModule accountingModule) {
		this.accountingModule = accountingModule;
	}

	public void notifyPeriodicCounters(FastTreeMap mapSubscribersCounters) {
		accountingModule.getAccountingInEventsPC().notifyPeriodicCounters(mapSubscribersCounters);
	}

	public void quotaVolumeUpdates(FastTreeMap consumptions) {
		accountingModule.getAccountingInEventsPC().quotaVolumeUpdates(consumptions);
	}

}
