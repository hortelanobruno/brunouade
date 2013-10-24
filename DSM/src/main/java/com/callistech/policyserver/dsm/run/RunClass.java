package com.callistech.policyserver.dsm.run;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import com.callistech.policyserver.af.configuration.AFConfiguration;
import com.callistech.policyserver.configuration.loader.ConfigurationManager;
import com.callistech.policyserver.dsm.core.DSMCore;
import com.callistech.policyserver.pm.newconfig.exceptions.PMPolicyConfigurationPersistenceException;
import com.callistech.policyserver.psm.entities.vo.af.configuration.ConfigurationAFManager;
import com.callistech.policyserver.psm.entities.vo.gui.utils.ConfigurationServiceManagerVO;

public class RunClass {

	public static void main(String args[]) {
		try {
			DSMCore core;
			if (args.length > 0) {
				core = new DSMCore(args[0]);
				ConfigurationAFManager configurationAFManager = readBinaryConfig(args[1]);
				AFConfiguration afConfiguration = readConfig(args[2]);
				core.setConfiguration(afConfiguration);
				core.setConfigurationAFManager(configurationAFManager);
			} else {
				core = new DSMCore();
			}

			core.init();
			core.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static AFConfiguration readConfig(String string) throws PMPolicyConfigurationPersistenceException {
		return new ConfigurationManager<AFConfiguration>().load(string);
	}

	private static ConfigurationAFManager readBinaryConfig(String sourcePath) {
		ConfigurationServiceManagerVO conf = null;
		try {
			ObjectInputStream inputStream = null;
			inputStream = new ObjectInputStream(new FileInputStream(sourcePath));
			conf = (ConfigurationServiceManagerVO) inputStream.readObject();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConfigurationAFManager configurationManager = new ConfigurationAFManager();
		ConfigurationServiceManagerVO serviceManagerVO = conf;
		configurationManager.setNetworkConfiguration(serviceManagerVO.getNetworkConfiguration());
		configurationManager.setPolicyConfiguration(serviceManagerVO.getPolicyConfiguration());
		configurationManager.setBodServiceConfiguration(serviceManagerVO.getBodServiceConfiguration());
		configurationManager.setOnlineCharging(serviceManagerVO.getOnlineCharging());
		// configurationManager.setDiameterRxConfiguration(getDiameterRxConfiguration(serviceManagerVO));
		// configurationManager.setServiceConfiguration(serviceManagerVO.getServiceConfiguration());
		configurationManager.setDynamicSessionsConfiguration(serviceManagerVO.getSystemSettings().getDynamicSessionsConfiguration());
		return configurationManager;
	}

}
