package com.callistech.policyserver.dsm.core;

import com.callistech.policyserver.dsm.meter.MeterModule;
import com.callistech.policyserver.dsm.meter.external.CollectorFacade;

public class DSMCore {

	private MeterModule meterModule;
	private CollectorFacade collectorFacade;
	
	public DSMCore() {
		// TODO Auto-generated constructor stub
	}
	
	public void init(){
		collectorFacade = new CollectorFacade();
		meterModule = new MeterModule();
		meterModule.setCollectorFacade(collectorFacade);
	}
}
