package com.callistech.policyserver.dsm.accounting.csv;

import java.util.Calendar;

import org.apache.commons.collections.FastTreeMap;
import org.apache.log4j.Logger;

import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.dsm.common.counters.ServiceCounter;
import com.callistech.policyserver.dsm.common.counters.SubscriberCounter;
import com.callistech.policyserver.dsm.common.counters.SubscriberServiceCounter;

public class AccountingCSVManager {

	private Logger servicesCSV = Logger.getLogger("fileAccountingServicesCSVLog");
	private Logger subscribersCSV = Logger.getLogger("fileAccountingSubscribersCSVLog");

	public AccountingCSVManager() {
		// TODO Auto-generated constructor stub
	}

	public void generatePeriodicDSRInCSV(FastTreeMap mapServicesCounters, FastTreeMap mapSubscribersCounters, int total_active_subscribers) {
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
				printDSR_Subscriber(timestamp, subscriberId, dsId, subscriberServiceCounter.getUp_volume(), subscriberServiceCounter.getDown_volume(), subscriberServiceCounter.getTime(), subscriberServiceCounter.getSessions());
			}
		}

		// Genero DSR Services
		ServiceCounter serviceCounter = null;
		Integer dsId;
		for (Object obj : mapServicesCounters.keySet()) {
			dsId = (Integer) obj;
			serviceCounter = (ServiceCounter) mapServicesCounters.get(obj);

			printDSR_Service(timestamp, dsId, serviceCounter.getUp_volume(), serviceCounter.getDown_volume(), serviceCounter.getTime(), serviceCounter.getActive_subscribers(), total_active_subscribers, serviceCounter.getConcurrent_sessions());
		}
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

	public void startSession(DynamicSession ds) {
		// TODO Auto-generated method stub

	}

	public void stopSession(DynamicSession ds) {
		// TODO Auto-generated method stub

	}

	public void pauseSession(DynamicSession ds) {
		// TODO Auto-generated method stub

	}

	public void resumeSession(DynamicSession ds) {
		// TODO Auto-generated method stub

	}
}
