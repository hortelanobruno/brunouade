package com.callistech.policyserver.dsm.meter.external;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.collections.FastTreeMap;
import org.apache.log4j.Logger;

import com.callistech.policyserver.dsm.common.QuotaVolume;
import com.callistech.policyserver.dsm.meter.MeterFacade;

public class CollectorFacade implements Runnable {

	private Logger logger = Logger.getLogger(getClass());
	private FastTreeMap consumptions = new FastTreeMap();
	private Queue<Integer> sessionesVolume = new ConcurrentLinkedQueue<Integer>();
	private MeterFacade meterFacade;

	public CollectorFacade(MeterFacade meterFacade) {
		this.meterFacade = meterFacade;
	}

	public void enableNotificationSubscriberConsumption(String subscriberId, Integer sessionId) {
		sessionesVolume.add(sessionId);
	}

	public void disableNotificationSubscriberConsumption(String subscriberId, Integer sessionId) {
		sessionesVolume.remove(sessionId);
	}

	@Override
	public void run() {
		try {
			logger.info("CollectorFacade run: " + consumptions.size() + ".");
			while (true) {
				long tardo = System.currentTimeMillis();

				QuotaVolume quota = null;

				// Cargo y remuevo
				Integer sesionId;
				logger.info("1 " + sessionesVolume.size());

				for (Object obj : sessionesVolume) {
					sesionId = (Integer) obj;
					if (consumptions.containsKey(sesionId)) {
						quota = (QuotaVolume) consumptions.get(sesionId);
					} else {
						quota = new QuotaVolume();
						consumptions.put(sesionId, quota);
					}

					long u = 100 + (long) (Math.random() * ((1000 - 100) + 1));
					long d = 100 + (long) (Math.random() * ((1000 - 100) + 1));
					quota.sum(u, d);
				}
				logger.info("4");

				logger.info("Notifing consumptions: " + consumptions.size() + ".");
				meterFacade.subscribersConsumptionsNotifications(consumptions);
				consumptions = new FastTreeMap();
				logger.info("5");
				tardo = System.currentTimeMillis() - tardo;
				try {
					long aux = 60 * 1000;
					if (tardo > aux) {
						logger.info("No alcanzo para dormir en TaskCuotaVolumeCoumsumptionSimulator.");
					} else {
						Thread.sleep((aux - tardo));
					}
				} catch (Exception ex) {
				}
			}
		} catch (Exception ex) {
			logger.error("CollectorFacade run.", ex);
		}
	}
}
