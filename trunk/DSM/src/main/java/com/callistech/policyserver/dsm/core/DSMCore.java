package com.callistech.policyserver.dsm.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.callistech.policyserver.af.configuration.AFConfiguration;
import com.callistech.policyserver.dsm.accounting.AccountingFacade;
import com.callistech.policyserver.dsm.accounting.AccountingModule;
import com.callistech.policyserver.dsm.meter.MeterFacade;
import com.callistech.policyserver.dsm.meter.MeterModule;
import com.callistech.policyserver.dsm.meter.external.CollectorFacade;
import com.callistech.policyserver.dsm.policy.PolicyFacade;
import com.callistech.policyserver.dsm.session.SessionFacade;
import com.callistech.policyserver.dsm.session.SessionModule;
import com.callistech.policyserver.dsm.session.external.WSFacade;
import com.callistech.policyserver.dsm.session.managers.DBManager;
import com.callistech.policyserver.psm.entities.vo.af.configuration.ConfigurationAFManager;

public class DSMCore {

	// Estas variables meterlas en la config
	private Integer max_concurrent_sessions = 1000000;
	private Boolean enablePolicyModule = true;
	private Boolean enableMeterModule = true;
	private Boolean enableAccountingModule = true;
	private Boolean enableSessionModule = true;
	private AFConfiguration configuration;
	private ConfigurationAFManager configurationAFManager;
	private DBManager dbManager;
	private MeterModule meterModule;
	private AccountingModule accountingModule;
	private SessionModule sessionModule;
	private PolicyFacade policyFacade;
	private WSFacade wsFacade;
	private CollectorFacade collectorFacade;
	private Logger logger = Logger.getLogger(getClass());
	private ExecutorService poolThreand = Executors.newCachedThreadPool();

	public DSMCore() {
		DOMConfigurator.configure("./config/log4j.dsm.xml");
	}

	public DSMCore(String log4jFile) {
		DOMConfigurator.configure(log4jFile);
	}

	public void init() {
		logger.info("DSMCore initing...");
		dbManager = new DBManager(this);
		dbManager.init();

		MeterFacade meterFacade = null;
		AccountingFacade accountingFacade = null;
		SessionFacade sessionFacade = null;

		if (enableMeterModule) {
			meterModule = new MeterModule();
			meterFacade = meterModule.getFacade();
		}
		if (enableAccountingModule) {
			accountingModule = new AccountingModule();
			accountingFacade = accountingModule.getAccountingFacade();
		}
		if (enableSessionModule) {
			sessionModule = new SessionModule(this);
			sessionFacade = sessionModule.getSessionFacade();
		}

		collectorFacade = new CollectorFacade(meterFacade);
		meterModule.setCollectorFacade(collectorFacade);
		meterModule.setSessionFacade(sessionFacade);
		meterModule.setAccountingFacade(accountingFacade);

		wsFacade = new WSFacade(sessionFacade);
		sessionModule.setAccountingFacade(accountingFacade);
		sessionModule.setMeterFacade(meterFacade);
		sessionModule.setPolicyFacade(policyFacade);
		sessionModule.setDBManager(dbManager);
		policyFacade = new PolicyFacade();

		accountingModule.setMeterFacade(meterFacade);
		accountingModule.setDBManager(dbManager);
		logger.info("Fin DSMCore inited.");
	}

	public void start() {
		logger.info("DSMCore starting...");
		meterModule.start();
		accountingModule.start();
		sessionModule.start();
		// Inicio simulador start stop
		poolThreand.submit(collectorFacade);
		poolThreand.submit(wsFacade);
		logger.info("Fin DSMCore started.");
	}

	public void stop() {
		logger.info("DSMCore stoping...");
		sessionModule.stop();
		meterModule.stop();
		accountingModule.stop();
		logger.info("Fin DSMCore stoped.");
	}

	public AFConfiguration getConfiguration() {
		return configuration;
	}

	public ConfigurationAFManager getConfigurationAFManager() {
		return configurationAFManager;
	}

	public Integer getMax_concurrent_sessions() {
		return max_concurrent_sessions;
	}

	public void setConfiguration(AFConfiguration configuration) {
		this.configuration = configuration;
	}

	public void setConfigurationAFManager(ConfigurationAFManager configurationAFManager) {
		this.configurationAFManager = configurationAFManager;
	}
}
