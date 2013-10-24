package com.callistech.policyserver.dsm.session;

import com.callistech.policyserver.af.exception.AttributeNotFoundException;
import com.callistech.policyserver.common.utils.ManagerSessionIds;
import com.callistech.policyserver.common.utils.exceptions.IdUnavailableException;
import com.callistech.policyserver.dsm.common.DynamicServiceLimits;
import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.dsm.common.DynamicSessionKey;
import com.callistech.policyserver.dsm.common.subscriber.SubscriberDS;
import com.callistech.policyserver.dsm.session.managers.AuthorizationManager;
import com.callistech.policyserver.dsm.session.managers.ConfigurationManager;
import com.callistech.policyserver.dsm.session.managers.DBManager;
import com.callistech.policyserver.dsm.session.managers.SessionContainer;
import com.callistech.policyserver.dsm.session.managers.result.StartSessionResult;
import com.callistech.policyserver.dsm.session.managers.result.StopSessionResult;

public class SessionManager {

	private AuthorizationManager authorizationManager;
	private DBManager dbManager;
	private SessionContainer sessionContainer;
	private ConfigurationManager configurationManager;
	private SessionModule sessionModule;
	private ManagerSessionIds managerSessionIds;

	public SessionManager(SessionModule sessionModule, Integer maxConcurrentSessions) {
		this.sessionModule = sessionModule;
		authorizationManager = new AuthorizationManager(this);
		sessionContainer = new SessionContainer();
		configurationManager = new ConfigurationManager(this);
		managerSessionIds = new ManagerSessionIds(1, maxConcurrentSessions);
	}

	public void start() {

	}

	public void stop() {

	}

	public void setDBManager(DBManager dbManager2) {
		this.dbManager = dbManager2;
	}

	public SessionModule getSessionModule() {
		return sessionModule;
	}

	public StartSessionResult eventStartSession(String subscriberId, String dsd) {
		StartSessionResult result = new StartSessionResult();
		try {
			// Format_SubId
			String formatedSubscriberId = configurationManager.formatSubscriberId(subscriberId);
			if (formatedSubscriberId != null) {
				// getSubscriber
				SubscriberDS subscriberDS = dbManager.getSubscriberDS(formatedSubscriberId);
				if (subscriberDS != null) {
					// getDynamicService
					DynamicServiceLimits dynamicServiceLimits;
					try {
						dynamicServiceLimits = configurationManager.getDynamicServiceLimits(subscriberDS, dsd);
						if (dynamicServiceLimits != null) {
							DynamicSessionKey key = configurationManager.generateDynamicSessionKey(subscriberDS, dynamicServiceLimits);
							Long startTime = System.currentTimeMillis();
							// checkDynamicSessionExist
							if (!sessionContainer.existSession(key)) {
								// checkSubscription
								if (subscriberDS.containsSubscription(dynamicServiceLimits.getDynamicService().getId())) {
									// checkAuthorizationPolicy
									if (authorizationManager.validate(subscriberDS, dynamicServiceLimits, startTime)) {
										// Genero sessionId
										Integer sessionId = managerSessionIds.getAvailableId();
										// Esta todo perfecto, se puede activar
										DynamicSession dynamicSession = configurationManager.generateDynamicSession(subscriberDS, dynamicServiceLimits, sessionId, startTime);
										// updateSessionMap
										sessionContainer.updateSessionDueToStartSession(key, sessionId);
										// notifyAccountingM
										// notifyPolicyM
										// notifyMeterM
										sessionModule.getSessionOutEventsPC().startSession(subscriberDS, dynamicServiceLimits, dynamicSession);
										result.setSuccess(true);
										result.setMessage("Success.");
									} else {
										// Exception, invalid authorization for subscriber,dsd
										result.setMessage("Unsuccess. Invalid authorization. SubscriberId: " + subscriberId + ". DsD: " + dsd + ".");
									}
								} else {
									// Exception, subscriber doesnt have subscription for dsd
									result.setMessage("Unsuccess. Subscription not found. SubscriberId: " + subscriberId + ". DsD: " + dsd + ".");
								}
							} else {
								// Exception, session already exist for subscriber,dsd
								result.setMessage("Unsuccess. Session already started. SubscriberId: " + subscriberId + ". DsD: " + dsd + ".");
							}
						} else {
							// Exception, invalid dsd
							result.setMessage("Unsuccess. Invalid DsD. SubscriberId: " + subscriberId + ". DsD: " + dsd + ".");
						}
					} catch (AttributeNotFoundException e) {
						// Aca falla a la hora de obtener el dsd
						e.printStackTrace();
						result.setMessage("Unsuccess. Error parsing DsD. SubscriberId: " + subscriberId + ". DsD: " + dsd + ".");
					}
				} else {
					// Exception, subscriber not exist in CSM
					result.setMessage("Unsuccess. Subscriber doesnt exist in CSM. SubscriberId: " + subscriberId + ". DsD: " + dsd + ".");
				}
			} else {
				// Exception, invalid subscriberId
				result.setMessage("Unsuccess. Invalid subscriberId. SubscriberId: " + subscriberId + ". DsD: " + dsd + ".");
			}
		} catch (IdUnavailableException ex1) {
			ex1.printStackTrace();
			result.setMessage("Unsuccess. Max concurrent sessions reached. SubscriberId: " + subscriberId + ". DsD: " + dsd + ".");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.setMessage("Unsuccess. Internal error. SubscriberId: " + subscriberId + ". DsD: " + dsd + ".");
		}
		return result;
	}

	public StopSessionResult eventStopSession(String subscriberId, String dsd) {
		StopSessionResult result = new StopSessionResult();
		try {
			// Format_SubId
			String formatedSubscriberId = configurationManager.formatSubscriberId(subscriberId);
			if (formatedSubscriberId != null) {
				// getSubscriber
				SubscriberDS subscriberDS = dbManager.getSubscriberDS(formatedSubscriberId);
				if (subscriberDS != null) {
					// getDynamicService
					DynamicServiceLimits dynamicServiceLimits;
					try {
						dynamicServiceLimits = configurationManager.getDynamicServiceLimits(subscriberDS, dsd);
						if (dynamicServiceLimits != null) {
							DynamicSessionKey key = configurationManager.generateDynamicSessionKey(subscriberDS, dynamicServiceLimits);
							Long stopTime = System.currentTimeMillis();
							// checkDynamicSessionExist
							if (sessionContainer.existSession(key)) {
								// Obtengo sessionId
								Integer sessionId = sessionContainer.getSessionId(key);
								// updateSessionMap
								sessionContainer.updateSessionDueToStopSessionExternally(key);
								// notifyAccountingM
								// notifyPolicyM
								// notifyMeterM
								sessionModule.getSessionOutEventsPC().stopSession(subscriberDS, sessionId, stopTime);
								result.setSuccess(true);
								result.setMessage("Success.");
							} else {
								// Exception, session not exist for subscriber,dsd
								result.setMessage("Unsuccess. Session not started. SubscriberId: " + subscriberId + ". DsD: " + dsd + ".");
							}
						} else {
							// Exception, invalid dsd
							result.setMessage("Unsuccess. Invalid DsD. SubscriberId: " + subscriberId + ". DsD: " + dsd + ".");
						}
					} catch (AttributeNotFoundException e) {
						// Aca falla a la hora de obtener el dsd
						e.printStackTrace();
						result.setMessage("Unsuccess. Error parsing DsD. SubscriberId: " + subscriberId + ". DsD: " + dsd + ".");
					}
				} else {
					// Exception, subscriber not exist in CSM
					result.setMessage("Unsuccess. Subscriber doesnt exist in CSM. SubscriberId: " + subscriberId + ". DsD: " + dsd + ".");
				}
			} else {
				// Exception, invalid subscriberId
				result.setMessage("Unsuccess. Invalid subscriberId. SubscriberId: " + subscriberId + ". DsD: " + dsd + ".");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			result.setMessage("Unsuccess. Internal error. SubscriberId: " + subscriberId + ". DsD: " + dsd + ".");
		}
		return result;
	}

	public void dispachStartSessionEvent(SubscriberDS subscriberDS, DynamicServiceLimits dynamicServiceLimits, DynamicSession dynamicSession) {
		// notifyMeterM
		sessionModule.getMeterFacade().startSession(dynamicServiceLimits.getDynamicService(), dynamicSession);
		// notifyPolicyM
		sessionModule.getPolicyFacade().activatePolicyProfile(subscriberDS, dynamicServiceLimits.getDynamicService());
		// notifyAccountingM
		sessionModule.getAccountingFacade().startSession(subscriberDS, dynamicSession);
	}

	public void dispachStopSessionEvent(SubscriberDS subscriberDS, Integer sessionId, Long stopTime) {
		// notifyMeterM
		sessionModule.getMeterFacade().stopSession(sessionId);
		// notifyPolicyM
		sessionModule.getPolicyFacade().deactivatePolicyProfile(subscriberDS);
		// notifyAccountingM
		sessionModule.getAccountingFacade().stopSession(subscriberDS, sessionId, stopTime);
	}

}
