package com.callistech.policyserver.dsm.meter.pycin.events;

import org.apache.commons.collections.FastTreeMap;

import com.callistech.policyserver.common.pyc.PYCEvent;

public class SubscriberConsumptionEvent implements PYCEvent {

	private FastTreeMap consumptions;

	public SubscriberConsumptionEvent() {
	}

	public FastTreeMap getConsumptions() {
		return consumptions;
	}

	public void setConsumptions(FastTreeMap consumptions) {
		this.consumptions = consumptions;
	}

	@Override
	public String toStringBeauty() {
		return "SubscriberConsumptionEvent: consumptions: " + consumptions.size() + ".";
	}

}
