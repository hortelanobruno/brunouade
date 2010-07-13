package com.brunoli.worldwar.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * 
 * @author brunoli
 */
public class EventManager {

	private static EventManager manager;
	private Log logError;
	private Log logInfo;
	private Log logOther;

	private EventManager() {
		try {
			DOMConfigurator.configure("./config/log4j.xml");
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		logError = LogFactory.getLog("worldWarError");
		logInfo = LogFactory.getLog("worldWarInfo");
		logOther = LogFactory.getLog("worldWarOther");
	}


	public static EventManager getInstance() {
		if (manager == null) {
			manager = new EventManager();
		}
		return manager;
	}
	
	public void other(String msj) {
		logOther.info(msj);
	}

	public void info(String msj) {
		logInfo.info(msj);
	}
	
	public void error(String msj, Exception ex) {
		logError.error(msj,ex);
	}
}