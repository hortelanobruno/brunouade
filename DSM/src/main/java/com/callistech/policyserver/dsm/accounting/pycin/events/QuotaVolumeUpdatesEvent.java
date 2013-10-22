package com.callistech.policyserver.dsm.accounting.pycin.events;

import org.apache.commons.collections.FastTreeMap;

import com.callistech.policyserver.common.pyc.PYCEvent;

public class QuotaVolumeUpdatesEvent implements PYCEvent {

	private FastTreeMap consumptions;

	public QuotaVolumeUpdatesEvent() {
		// TODO Auto-generated constructor stub
	}

	public FastTreeMap getConsumptions() {
		return consumptions;
	}

	public void setConsumptions(FastTreeMap consumptions) {
		this.consumptions = consumptions;
	}

	@Override
	public String toStringBeauty() {
		return "QuotaVolumeUpdatesEvent";
	}

}
