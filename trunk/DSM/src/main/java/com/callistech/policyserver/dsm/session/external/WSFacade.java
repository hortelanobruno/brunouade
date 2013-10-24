package com.callistech.policyserver.dsm.session.external;

import org.apache.log4j.Logger;

import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.dsm.common.MatchCriteria;
import com.callistech.policyserver.dsm.session.SessionFacade;

public class WSFacade implements Runnable {

	private int session_amount = 10;
	private final int serviceId = 1;
	private int index = 1;
	private Logger logger = Logger.getLogger(getClass());
	private SessionFacade sessionFacade;

	public WSFacade(SessionFacade sessionFacade) {
		this.sessionFacade = sessionFacade;
	}

	@Override
	public void run() {
		// runOld();
		runNew();
	}

	private void runNew() {
		// while (true) {
		logger.info("Start simulation start/stop.");
		for (int i = 0; i < session_amount; i++) {
			// Creando session
			String dsd;
			// if (i % 2 == 0) {
			// dsd = getDsDVolume();
			// } else {
			dsd = getDsDTime();
			// }

			String subscriberId = getSubscriberId(i);
			logger.info("Generando SubscriberId: " + subscriberId + ". DsD: " + dsd + ".");
			sessionFacade.startSessionNB(subscriberId, dsd);

			// Borrando session
			// if (i % 20 == 0) {
			// int ul = (int) (Math.random() * (((i - 1) - 0) + 1));
			// sessionFacade.stopSessionNB(subscriberId, dsd);
			// }

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		}
		logger.info("Fin simulation start/stop.");
		// logger.info("Sleeping 10 min.");
		// try {
		// Thread.sleep(10 * 60 * 1000);
		// } catch (InterruptedException e) {
		// }
		// }
	}

	private String getSubscriberId(int index) {
		return "" + index;
	}

	// [testVol50mb, testVol1gb, testTime3min, testTime60min]
	private String getDsDTime() {
		return "testTime3min";
	}

	private String getDsDVolume() {
		return "testVol50mb";
	}

	private void runOld() {
		// // while (true) {
		// logger.info("Start simulation start/stop.");
		// for (int i = 0; i < session_amount; i++) {
		// // Creando session
		// DynamicSession ds;
		// if (i % 2 == 0) {
		// ds = generateSessionVolume();
		// } else {
		// ds = generateSessionTime();
		// }
		// // System.out.println("Generando Session: " + ds);
		// facade.startSession(ds);
		//
		// // Borrando session
		// // if (i % 20 == 0) {
		// // int ul = (int) (Math.random() * (((i - 1) - 0) + 1));
		// // facade.stopSession(generateSessionId("" + ul, serviceId));
		// // }
		//
		// try {
		// Thread.sleep(1);
		// } catch (InterruptedException e) {
		// }
		// }
		// logger.info("Fin simulation start/stop.");
		// // logger.info("Sleeping 10 min.");
		// // try {
		// // Thread.sleep(10 * 60 * 1000);
		// // } catch (InterruptedException e) {
		// // }
		// // }
	}

	public DynamicSession generateSessionVolume() {
		DynamicSession ds = new DynamicSession();
		ds.setSubscriberId("" + index);
		ds.setServiceId(serviceId);
		ds.setSessionId(index);
		ds.setStartTime(System.currentTimeMillis());

		long ul = 5000000 + (long) (Math.random() * ((10000000 - 5000000) + 1));

		ds.setUl_downVolume(ul);
		ds.setActive(true);
		ds.setMatchCriteria(MatchCriteria.MATCH_ALL);
		ds.setDownVolumeLimitEnabled(true);
		index++;
		return ds;
	}

	public DynamicSession generateSessionTime() {
		DynamicSession ds = new DynamicSession();
		ds.setSubscriberId("" + index);
		ds.setServiceId(serviceId);
		ds.setSessionId(index);
		ds.setStartTime(System.currentTimeMillis());

		long ul = 3 + (long) (Math.random() * ((5 - 3) + 1));

		ds.setUl_time(ul);
		ds.setActive(true);
		ds.setMatchCriteria(MatchCriteria.MATCH_ALL);
		ds.setTimeLimitEnabled(true);
		index++;
		return ds;
	}
}
