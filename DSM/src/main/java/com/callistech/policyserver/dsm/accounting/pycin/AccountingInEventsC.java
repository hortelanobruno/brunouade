package com.callistech.policyserver.dsm.accounting.pycin;

import java.util.Queue;

import com.callistech.policyserver.common.pyc.PYCConsumerImplementation;
import com.callistech.policyserver.common.pyc.PYCEvent;
import com.callistech.policyserver.dsm.accounting.AccountingManager;
import com.callistech.policyserver.dsm.accounting.pycin.events.NotifyPeriodicCountersEvent;
import com.callistech.policyserver.dsm.accounting.pycin.events.QuotaVolumeUpdatesEvent;

public class AccountingInEventsC extends PYCConsumerImplementation {

	private AccountingManager accountingManager;

	public AccountingInEventsC(Queue<PYCEvent> queue, AccountingManager accountingManager) {
		super(queue);
		this.accountingManager = accountingManager;
	}

	@Override
	protected void processEvent(PYCEvent event) {
		if (event != null) {
			if(event instanceof NotifyPeriodicCountersEvent){
				NotifyPeriodicCountersEvent notifyPeriodicCounters = (NotifyPeriodicCountersEvent) event;
				accountingManager.notifyPeriodicCounters(notifyPeriodicCounters.getMapServicesCounters(),notifyPeriodicCounters.getMapSubscribersCounters(),notifyPeriodicCounters.getTotal_active_subscribers());
			}else if(event instanceof QuotaVolumeUpdatesEvent){
				QuotaVolumeUpdatesEvent quotaVolumeUpdates = (QuotaVolumeUpdatesEvent) event;
				accountingManager.quotaVolumeUpdates(quotaVolumeUpdates.getConsumptions());
			}
		}
	}

}
