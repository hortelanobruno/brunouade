package com.callistech.policyserver.dsm.accounting;

import java.util.Calendar;
import java.util.Set;

import org.apache.commons.collections.FastTreeMap;
import org.apache.log4j.Logger;

import com.callistech.policyserver.dsm.common.counters.ServiceCounter;
import com.callistech.policyserver.dsm.common.counters.SubscriberCounter;
import com.callistech.policyserver.dsm.common.counters.SubscriberServiceCounter;
import com.callistech.policyserver.dsm.meter.MeterFacade;

public class AccountingFacade implements Runnable {

	private MeterFacade meterFacade;
	private Logger logger = Logger.getLogger(getClass());
	private Logger servicesCSV = Logger.getLogger("fileAccountingServicesCSVLog");
	private Logger subscribersCSV = Logger.getLogger("fileAccountingSubscribersCSVLog");

	public AccountingFacade(MeterFacade meterFacade) {
		this.meterFacade = meterFacade;
	}

	public void notifyPeriodicCounters(Set<String> total_active_subscribers, FastTreeMap mapSubscribersCounters, FastTreeMap mapServicesCounters) {
		logger.info("Receiving counters periodics...");

		// Genero DSR Subscriber
		String subscriberId;
		String timestamp = Calendar.getInstance().getTime().toString();
		SubscriberCounter subscriberCounter = null;
		SubscriberServiceCounter subscriberServiceCounter = null;
		for (Object obj : mapSubscribersCounters.keySet()) {
			subscriberId = (String) obj;
			subscriberCounter = (SubscriberCounter) mapSubscribersCounters.get(obj);

			for (Integer dsId : subscriberCounter.getDynamicServices().keySet()) {
				subscriberServiceCounter = subscriberCounter.getDynamicServices().get(dsId);
				printDSR_Subscriber(timestamp, subscriberId, dsId, subscriberServiceCounter.getUp_volume(), subscriberServiceCounter.getDown_volume(), subscriberServiceCounter.getTime(), subscriberServiceCounter.getSessions().size());
			}
		}

		// Genero DSR Services
		ServiceCounter serviceCounter = null;
		Integer dsId;
		for (Object obj : mapServicesCounters.keySet()) {
			dsId = (Integer) obj;
			serviceCounter = (ServiceCounter) mapServicesCounters.get(obj);

			printDSR_Service(timestamp, dsId, serviceCounter.getUp_volume(), serviceCounter.getDown_volume(), serviceCounter.getTime(), serviceCounter.getActive_subscribers().size(), total_active_subscribers.size(), serviceCounter.getConcurrent_sessions().size());
		}

		logger.info("Counters periodics received.");
	}

	private void printDSR_Service(String timestamp, Integer dsId, Long up_volume, Long down_volume, Long time, int active_subscribers, int total_active_subscribers, int concurrent_sessions) {
		StringBuilder sb = new StringBuilder();
		sb.append(timestamp);
		sb.append(",");
		sb.append(dsId);
		sb.append(",");
		sb.append(up_volume);
		sb.append(",");
		sb.append(down_volume);
		sb.append(",");
		sb.append(time);
		sb.append(",");
		sb.append(active_subscribers);
		sb.append(",");
		sb.append(total_active_subscribers);
		sb.append(",");
		sb.append(concurrent_sessions);
		servicesCSV.info(sb.toString());
	}

	private void printDSR_Subscriber(String timestamp, String subscriberId, Integer dsId, Long up_volume, Long down_volume, Long time, int sessions) {
		StringBuilder sb = new StringBuilder();
		sb.append(timestamp);
		sb.append(",");
		sb.append(subscriberId);
		sb.append(",");
		sb.append(dsId);
		sb.append(",");
		sb.append(up_volume);
		sb.append(",");
		sb.append(down_volume);
		sb.append(",");
		sb.append(time);
		sb.append(",");
		sb.append(sessions);
		subscribersCSV.info(sb.toString());
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(5 * 60 * 1000);
			} catch (InterruptedException e) {
			}
			logger.info("Requesting counters periodics...");
			meterFacade.sendCountersPeriodics();
			logger.info("Counters periodics requested.");
		}

	}

}
