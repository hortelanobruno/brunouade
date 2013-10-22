package com.callistech.policyserver.dsm.session;

import com.callistech.policyserver.dsm.common.DynamicService;
import com.callistech.policyserver.dsm.common.subscriber.SubscriberDS;
import com.callistech.policyserver.dsm.session.managers.AuthorizationManager;
import com.callistech.policyserver.dsm.session.managers.ConfigurationManager;
import com.callistech.policyserver.dsm.session.managers.DBManager;
import com.callistech.policyserver.dsm.session.managers.SessionContainer;

public class SessionManager {

	private AuthorizationManager authorizationManager;
	private DBManager dbManager;
	private SessionContainer sessionContainer;
	private ConfigurationManager configurationManager;
	private SessionModule sessionModule;

	public SessionManager(SessionModule sessionModule) {
		this.sessionModule = sessionModule;
		authorizationManager = new AuthorizationManager(this);
		dbManager = new DBManager();
		sessionContainer = new SessionContainer();
		configurationManager = new ConfigurationManager();
	}

	public void start() {

	}

	public void stop() {

	}

	public void eventStartSession(String subscriberId, String dsd) {
		// Format_SubId
		String formatedSubscriberId = configurationManager.formatSubscriberId(subscriberId);
		if (formatedSubscriberId != null) {
			// getSubscriber
			SubscriberDS subscriberDS = dbManager.getSubscriberDS(formatedSubscriberId);
			if (subscriberDS != null) {
				// getDynamicService
				DynamicService dynamicService = configurationManager.getDynamicService(dsd);
				if (dynamicService != null) {
					// checkDynamicSessionExist
					if (!sessionContainer.existSession(subscriberDS.getSubscriberId(), dynamicService.getDsId())) {
						// checkSubscription
						if (subscriberDS.containsSubscription(dynamicService.getDsId())) {
							// checkAuthorizationPolicy
							if (authorizationManager.validate(subscriberDS, dynamicService)) {
								// Esta todo perfecto, se puede activar
								// updateSessionMap
								sessionContainer.updateSessionDueToStartSession(subscriberDS,dynamicService);
								// notifyAccountingM
								// notifyPolicyM
								// notifyMeterM
								sessionModule.getSessionOutEventsPC().startSession(subscriberDS,dynamicService);
								
							} else {
								// Exception, invalid authorization for subscriber,dsd
							}
						} else {
							// Exception, subscriber doesnt have subscription for dsd
						}
					} else {
						// Exception, session already exist for subscriber,dsd
					}
				} else {
					// Exception, invalid dsd
				}
			} else {
				// Exception, subscriber not exist in CSM
			}
		} else {
			// Exception, invalid subscriberId
		}
	}

	public void eventStopSession(String subscriberId, String dsd) {
		// Format_SubId
		// getDynamicService

		// checkDynamicSessionExist
		// getSubscriber

		// updateSessionMap
		// notifyAccountingM
		// notifyPolicyM
		// notifyMeterM
	}

}
