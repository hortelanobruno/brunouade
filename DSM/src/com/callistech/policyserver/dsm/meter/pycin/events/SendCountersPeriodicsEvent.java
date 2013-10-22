package com.callistech.policyserver.dsm.meter.pycin.events;

import com.callistech.policyserver.common.pyc.PYCEvent;

public class SendCountersPeriodicsEvent implements PYCEvent {

	public SendCountersPeriodicsEvent() {
	}

	@Override
	public String toStringBeauty() {
		return "SendCountersPeriodicsEvent.";
	}

}
