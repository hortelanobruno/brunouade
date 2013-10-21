package com.callistech.policyserver.dsm.meter;

import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.dsm.meter.counters.CountersAdministrator;

public class MeterManager {

	private CountersAdministrator countersAdministrator;
	private MeterModule meterModule;

	public MeterManager(MeterModule meterModule) {
		this.meterModule = meterModule;
		this.countersAdministrator = new CountersAdministrator();
	}

	public void eventStartSession(DynamicSession ds) {
		// Aca hay que hacer todos los pasos:
		// 1) Cargarlo en el countersAdministrator
		countersAdministrator.addEventStartSession(ds);
		// 2) Notificarle al collector si corresponde
		
	}

	public void eventStopSession(String sessionId) {
		// Aca hay que hacer todos los pasos:
		// 1) Cargarlo en el countersAdministrator
		countersAdministrator.addEventStopSession(sessionId);
		// 2) Notificarle al collector si corresponde
	}

}
