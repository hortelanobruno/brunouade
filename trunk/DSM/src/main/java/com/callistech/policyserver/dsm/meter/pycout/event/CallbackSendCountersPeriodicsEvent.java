package com.callistech.policyserver.dsm.meter.pycout.event;

import org.apache.commons.collections.FastTreeMap;

import com.callistech.policyserver.common.pyc.PYCEvent;

public class CallbackSendCountersPeriodicsEvent implements PYCEvent {

	private FastTreeMap mapSubscribersCounters;

	public CallbackSendCountersPeriodicsEvent() {
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
		return "CallbackSendCountersPeriodicsEvent.";
	}

}
