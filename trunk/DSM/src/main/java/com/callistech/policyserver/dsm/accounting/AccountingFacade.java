package com.callistech.policyserver.dsm.accounting;

import org.apache.commons.collections.FastTreeMap;

import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.dsm.common.subscriber.SubscriberDS;

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

	public void startSession(SubscriberDS subscriberDS, DynamicSession dynamicSession) {
		accountingModule.getAccountingInEventsPC().startSession(subscriberDS, dynamicSession);
	}

	public void stopSession(SubscriberDS subscriberDS, Integer sessionId, Long stopTime) {
		accountingModule.getAccountingInEventsPC().stopSession(subscriberDS, sessionId, stopTime);
	}

}
