package com.callistech.policyserver.dsm.accounting.pycin.events;

import org.apache.commons.collections.FastTreeMap;

import com.callistech.policyserver.common.pyc.PYCEvent;

public class NotifyPeriodicCountersEvent implements PYCEvent {

	private FastTreeMap mapSubscribersCounters;

	public NotifyPeriodicCountersEvent() {
		// TODO Auto-generated constructor stub
	}

	public FastTreeMap getMapSubscribersCounters() {
		return mapSubscribersCounters;
	}

	public void setMapSubscribersCounters(FastTreeMap mapSubscribersCounters) {
		this.mapSubscribersCounters = mapSubscribersCounters;
	}

	@Override
	public String toStringBeauty() {
		return "NotifyPeriodicCountersEvent";
	}

}
