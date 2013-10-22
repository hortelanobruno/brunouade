package com.callistech.policyserver.dsm.accounting.pycin;

import java.util.Set;

import org.apache.commons.collections.FastTreeMap;

import com.callistech.policyserver.common.pyc.PYCConsumerImplementation;
import com.callistech.policyserver.common.pyc.PYCProducerConsumerImplementation;
import com.callistech.policyserver.dsm.accounting.AccountingModule;
import com.callistech.policyserver.dsm.accounting.pycin.events.NotifyPeriodicCountersEvent;
import com.callistech.policyserver.dsm.accounting.pycin.events.QuotaVolumeUpdatesEvent;

public class AccountingInEventsPC extends PYCProducerConsumerImplementation {

	private AccountingModule accountingModule;

	public AccountingInEventsPC(AccountingModule accountingModule) {
		super("accountingInRateLog");
		this.accountingModule = accountingModule;
	}

	@Override
	protected PYCConsumerImplementation generateConsumer() {
		AccountingInEventsC consumer = new AccountingInEventsC(getQueue(), accountingModule.getAccountingManager());
		return consumer;
	}

	public void notifyPeriodicCounters(Set<String> total_active_subscribers, FastTreeMap mapSubscribersCounters, FastTreeMap mapServicesCounters) {
		NotifyPeriodicCountersEvent event = new NotifyPeriodicCountersEvent();
		event.setMapServicesCounters(mapServicesCounters);
		event.setMapSubscribersCounters(mapSubscribersCounters);
		event.setTotal_active_subscribers(total_active_subscribers);
		super.addEvent(event);
	}

	public void quotaVolumeUpdates(FastTreeMap consumptions) {
		QuotaVolumeUpdatesEvent event = new QuotaVolumeUpdatesEvent();
		event.setConsumptions(consumptions);
		super.addEvent(event);
	}

}
