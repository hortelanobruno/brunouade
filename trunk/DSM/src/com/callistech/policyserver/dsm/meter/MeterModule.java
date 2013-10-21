package com.callistech.policyserver.dsm.meter;

import com.callistech.policyserver.dsm.meter.external.CollectorFacade;
import com.callistech.policyserver.dsm.meter.pycin.MeterInEventsPC;
import com.callistech.policyserver.dsm.meter.pycout.MeterOutEventsPC;

public class MeterModule {

	private MeterFacade facade;
	private MeterManager manager;
	private MeterInEventsPC inEventsPC;
	private MeterOutEventsPC outEventsPC;
	private CollectorFacade collectorFacade;

	public MeterModule() {
		inEventsPC = new MeterInEventsPC(this);
		outEventsPC = new MeterOutEventsPC(this);
		facade = new MeterFacade(this);
		manager = new MeterManager(this);
	}

	public void start() {
		outEventsPC.start();
		inEventsPC.start();
	}

	public void stop() {
		inEventsPC.stop();
		outEventsPC.stop();
	}

	public MeterInEventsPC getInEventsPC() {
		return inEventsPC;
	}

	public MeterOutEventsPC getOutEventsPC() {
		return outEventsPC;
	}

	public MeterFacade getFacade() {
		return facade;
	}

	public MeterManager getManager() {
		return manager;
	}

	public void setCollectorFacade(CollectorFacade collectorFacade) {
		this.collectorFacade = collectorFacade;
	}

	public CollectorFacade getCollectorFacade() {
		return collectorFacade;
	}
}
