package com.callistech.policyserver.dsm.accounting;

import java.util.Set;

import org.apache.commons.collections.FastTreeMap;

public class AccountingFacade {

	private AccountingModule accountingModule;

	public AccountingFacade(AccountingModule accountingModule) {
		this.accountingModule = accountingModule;
	}

	public void notifyPeriodicCounters(Set<String> total_active_subscribers, FastTreeMap mapSubscribersCounters, FastTreeMap mapServicesCounters) {
		accountingModule.getAccountingInEventsPC().notifyPeriodicCounters(total_active_subscribers, mapSubscribersCounters, mapServicesCounters);
	}

	public void quotaVolumeUpdates(FastTreeMap consumptions) {
		accountingModule.getAccountingInEventsPC().quotaVolumeUpdates(consumptions);
	}

}
