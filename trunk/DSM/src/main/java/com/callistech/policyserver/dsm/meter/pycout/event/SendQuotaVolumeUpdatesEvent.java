package com.callistech.policyserver.dsm.meter.pycout.event;

import org.apache.commons.collections.FastTreeMap;

import com.callistech.policyserver.common.pyc.PYCEvent;

public class SendQuotaVolumeUpdatesEvent implements PYCEvent {

	private FastTreeMap consumptions;

	public SendQuotaVolumeUpdatesEvent() {
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
		return "SendQuotaVolumeUpdatesEvent consumptions: " + consumptions.size() + ".";
	}

}
