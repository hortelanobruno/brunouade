package com.callistech.policyserver.dsm.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.callistech.policyserver.dsm.accounting.AccountingFacade;
import com.callistech.policyserver.dsm.meter.MeterModule;
import com.callistech.policyserver.dsm.meter.external.CollectorFacade;
import com.callistech.policyserver.dsm.session.SessionFacade;

public class DSMCore {

	private MeterModule meterModule;
	private CollectorFacade collectorFacade;
	private SessionFacade sessionFacade;
	private AccountingFacade accountingFacade;
	private Logger logger = Logger.getLogger(getClass());
	private ExecutorService poolThreand = Executors.newCachedThreadPool();

	public DSMCore() {
		// TODO Auto-generated constructor stub
	}

	public void init() {
		DOMConfigurator.configure("./config/log4j.xml");
		logger.info("DSMCore initing...");
		meterModule = new MeterModule();
		collectorFacade = new CollectorFacade(meterModule.getFacade());
		sessionFacade = new SessionFacade(meterModule.getFacade());
		accountingFacade = new AccountingFacade(meterModule.getFacade());
		meterModule.setCollectorFacade(collectorFacade);
		meterModule.setSessionFacade(sessionFacade);
		meterModule.setAccountingFacade(accountingFacade);
		logger.info("Fin DSMCore inited.");
	}

	public void start() {
		logger.info("DSMCore starting...");
		meterModule.start();
		// Inicio simulador start stop
		poolThreand.submit(accountingFacade);
		poolThreand.submit(collectorFacade);
		poolThreand.submit(sessionFacade);
		logger.info("Fin DSMCore started.");
	}

	public void stop() {
		logger.info("DSMCore stoping...");
		meterModule.stop();
		logger.info("Fin DSMCore stoped.");
	}
}
