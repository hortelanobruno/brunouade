package com.callistech.policyserver.dsm.meter;

import com.callistech.policyserver.dsm.meter.pycin.MeterInEventsPC;

public class MeterModule {

	private MeterFacade facade;
	private MeterManager manager;
	private MeterInEventsPC eventsPC;

	public MeterModule() {
		eventsPC = new MeterInEventsPC(this);
		facade = new MeterFacade(this);
		manager = new MeterManager(this);
	}

	public void start() {
		eventsPC.start();
	}

	public void stop() {
		eventsPC.stop();
	}

	public MeterInEventsPC getEventsPC() {
		return eventsPC;
	}

	public MeterFacade getFacade() {
		return facade;
	}

	public MeterManager getManager() {
		return manager;
	}
}
