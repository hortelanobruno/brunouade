package com.callistech.policyserver.dsm.common;

import java.io.Serializable;

import com.callistech.policyserver.psm.entities.vo.gui.af.bod.service.BoDServiceAFVO;

public class DynamicServiceLimits implements Serializable {

	private BoDServiceAFVO dynamicService;
	private Limits limits;

	public DynamicServiceLimits() {
		// TODO Auto-generated constructor stub
	}

	public BoDServiceAFVO getDynamicService() {
		return dynamicService;
	}

	public void setDynamicService(BoDServiceAFVO dynamicService) {
		this.dynamicService = dynamicService;
	}

	public Limits getLimits() {
		return limits;
	}

	public void setLimits(Limits limits) {
		this.limits = limits;
	}

}
