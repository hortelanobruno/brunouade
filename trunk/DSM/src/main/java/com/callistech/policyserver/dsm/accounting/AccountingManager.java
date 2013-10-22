package com.callistech.policyserver.dsm.accounting;

import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.FastTreeMap;
import org.apache.log4j.Logger;

import com.callistech.policyserver.dsm.accounting.csv.AccountingCSVManager;
import com.callistech.policyserver.dsm.accounting.db.AccountingDBManager;
import com.callistech.policyserver.dsm.accounting.task.GetPeriodicCountersTask;
import com.callistech.policyserver.dsm.common.DynamicSession;

public class AccountingManager {

	private int periodGeneratingRecords = 5;
	private AccountingCSVManager accountingCSVManager;
	private AccountingDBManager accountingDBManager;
	private AccountingModule accountingModule;
	private Logger logger = Logger.getLogger(getClass());
	private ScheduledExecutorService periodicPoolThreand = Executors.newScheduledThreadPool(1);
	private GetPeriodicCountersTask getPeriodicCountersTask;
	private ScheduledFuture<?> future;

	public AccountingManager(AccountingModule accountingModule) {
		this.accountingModule = accountingModule;
		accountingCSVManager = new AccountingCSVManager();
		accountingDBManager = new AccountingDBManager();
	}

	public void start() {
		initPeriodicTask();
	}

	private void initPeriodicTask() {
		if (getPeriodicCountersTask == null) {
			getPeriodicCountersTask = new GetPeriodicCountersTask(accountingModule);
		}
		long delay = getDelayInSeconds();
		logger.info("Initing periodic tasks. Delay: " + delay);
		future = periodicPoolThreand.scheduleAtFixedRate(getPeriodicCountersTask, delay, periodGeneratingRecords * 60, TimeUnit.SECONDS);
	}

	private long getDelayInSeconds() {
		Calendar cal = Calendar.getInstance();
		int min = cal.get(Calendar.MINUTE);
		int mod = min % periodGeneratingRecords;
		cal.add(Calendar.MINUTE, mod == 0 ? periodGeneratingRecords : periodGeneratingRecords - mod);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
	}

	public void stop() {
		if (future != null) {
			future.cancel(false);
		}
	}

	public void notifyPeriodicCounters(FastTreeMap mapServicesCounters, FastTreeMap mapSubscribersCounters, Set<String> total_active_subscribers) {
		logger.info("Receiving counters periodics...");
		logger.info("Generating CSV records...");
		accountingCSVManager.generatePeriodicDSRInCSV(mapServicesCounters, mapSubscribersCounters, total_active_subscribers);
		logger.info("FIN Generating CSV records.");
		logger.info("Generating DB records...");
		accountingDBManager.generatePeriodicDSRInDBReport(mapServicesCounters, mapSubscribersCounters, total_active_subscribers);
		logger.info("FIN Generating DB records.");
		logger.info("Counters periodics received.");
	}

	public void quotaVolumeUpdates(FastTreeMap consumptions) {
		logger.info("Receiving quota volume updates...");
		accountingDBManager.quotaVolumeUpdates(consumptions);
		logger.info("Quota volume updates recieved.");
	}

	public void startSession(DynamicSession ds) {
		logger.info("Receiving startSession. ds: " + ds + "...");
		accountingCSVManager.startSession(ds);
		accountingDBManager.startSession(ds);
	}

	public void stopSession(DynamicSession ds) {
		logger.info("Receiving stopSession. ds: " + ds + "...");
		accountingCSVManager.stopSession(ds);
		accountingDBManager.stopSession(ds);
	}

	public void pauseSession(DynamicSession ds) {
		logger.info("Receiving pauseSession. ds: " + ds + "...");
		accountingCSVManager.pauseSession(ds);
		accountingDBManager.pauseSession(ds);
	}

	public void resumeSession(DynamicSession ds) {
		logger.info("Receiving resumeSession. ds: " + ds + "...");
		accountingCSVManager.resumeSession(ds);
		accountingDBManager.resumeSession(ds);
	}

}
