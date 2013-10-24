package com.callistech.policyserver.dsm.accounting.pycin;

import java.util.Queue;

import com.callistech.policyserver.common.pyc.PYCConsumerImplementation;
import com.callistech.policyserver.common.pyc.PYCEvent;
import com.callistech.policyserver.dsm.accounting.AccountingManager;
import com.callistech.policyserver.dsm.accounting.pycin.events.NotifyPeriodicCountersEvent;
import com.callistech.policyserver.dsm.accounting.pycin.events.QuotaVolumeUpdatesEvent;
import com.callistech.policyserver.dsm.accounting.pycin.events.StartSessionEvent;
import com.callistech.policyserver.dsm.accounting.pycin.events.StopSessionEvent;

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
				accountingManager.notifyPeriodicCounters(notifyPeriodicCounters.getMapSubscribersCounters());
			}else if(event instanceof QuotaVolumeUpdatesEvent){
				QuotaVolumeUpdatesEvent quotaVolumeUpdates = (QuotaVolumeUpdatesEvent) event;
				accountingManager.quotaVolumeUpdates(quotaVolumeUpdates.getConsumptions());
			}else if(event instanceof StartSessionEvent){
				StartSessionEvent startSession = (StartSessionEvent) event;
				accountingManager.startSession(startSession.getSubscriberDS(),startSession.getDynamicSession());
			}else if(event instanceof StopSessionEvent){
				StopSessionEvent stopSession = (StopSessionEvent) event;
				accountingManager.stopSession(stopSession.getSubscriberDS(),stopSession.getSessionId(),stopSession.getStopTime());
			}
		}
	}

}
