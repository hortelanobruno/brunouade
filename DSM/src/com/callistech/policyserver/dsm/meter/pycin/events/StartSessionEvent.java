package com.callistech.policyserver.dsm.meter.pycin.events;

import com.callistech.policyserver.common.pyc.PYCEvent;
import com.callistech.policyserver.dsm.common.DynamicSession;

public class StartSessionEvent implements PYCEvent {

	private DynamicSession ds;

	public StartSessionEvent(DynamicSession ds) {
		this.ds = ds;
	}

	public DynamicSession getDs() {
		return ds;
	}

	public void setDs(DynamicSession ds) {
		this.ds = ds;
	}

	@Override
	public String toStringBeauty() {
		// TODO Auto-generated method stub
		return null;
	}

}
