package com.callistech.policyserver.dsm.meter.pycin.events;

import com.callistech.policyserver.common.pyc.PYCEvent;
import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.psm.entities.vo.gui.af.bod.service.BoDServiceAFVO;

public class StartSessionEvent implements PYCEvent {

	private DynamicSession ds;
	private BoDServiceAFVO boDServiceAFVO;

	public StartSessionEvent(BoDServiceAFVO boDServiceAFVO, DynamicSession ds) {
		this.ds = ds;
		this.boDServiceAFVO = boDServiceAFVO;
	}

	public DynamicSession getDs() {
		return ds;
	}

	public void setDs(DynamicSession ds) {
		this.ds = ds;
	}

	public BoDServiceAFVO getBoDServiceAFVO() {
		return boDServiceAFVO;
	}

	public void setBoDServiceAFVO(BoDServiceAFVO boDServiceAFVO) {
		this.boDServiceAFVO = boDServiceAFVO;
	}

	@Override
	public String toStringBeauty() {
		return "StartSessionEvent: ds: " + ds + ".";
	}

}
