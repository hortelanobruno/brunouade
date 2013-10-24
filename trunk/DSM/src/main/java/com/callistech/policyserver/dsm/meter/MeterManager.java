package com.callistech.policyserver.dsm.meter;

import java.util.List;

import org.apache.commons.collections.FastTreeMap;

import com.callistech.policyserver.dsm.common.DynamicSession;
import com.callistech.policyserver.dsm.meter.counters.CountersAdministrator;

public class MeterManager {

	private CountersAdministrator countersAdministrator;
	private MeterModule meterModule;

	public MeterManager(MeterModule meterModule) {
		this.meterModule = meterModule;
		this.countersAdministrator = new CountersAdministrator(this);
	}

	public void start() {
		countersAdministrator.start();
	}

	public void stop() {
		countersAdministrator.stop();
	}

	public void eventStartSession(DynamicSession ds) {
		// Aca hay que hacer todos los pasos:
		// 1) Cargarlo en el countersAdministrator
		countersAdministrator.addEventStartSession(ds);
		// 2) Notificarle al collector si corresponde
		meterModule.getOutEventsPC().enableNotificationSubscriberConsumption(ds.getSubscriberId(), ds.getSessionId());
	}

	public void eventStopSession(String subscriberId, Integer sessionId) {
		// Aca hay que hacer todos los pasos:
		// 1) Cargarlo en el countersAdministrator
		countersAdministrator.addEventStopSession(sessionId);
		// 2) Notificarle al collector si corresponde
		meterModule.getOutEventsPC().disableNotificationSubscriberConsumption(subscriberId, sessionId);
	}

	public void eventSendCountersPeriodics() {
		// Resetear y mandar counters periodics
		countersAdministrator.resetAndSendCountersPeriodics();
	}

	public void eventSubscriberConsumption(FastTreeMap consumptions) {
		// Llego evento con consumptions
		countersAdministrator.updateConsumptions(consumptions);
	}

	public void callbackSendPeriodicCounters(FastTreeMap mapSubscribersCounters) {
		// Cargo evento de salida con los contadores periodicos
		meterModule.getOutEventsPC().callbackSendPeriodicCounters(mapSubscribersCounters);
	}

	public void sendQuotaVolumeUpdates(FastTreeMap consumptions) {
		// Cargo evento de salida con los updates de quota
		meterModule.getOutEventsPC().sendQuotaVolumeUpdates(consumptions);
	}

	/**
	 * Este metodo forwardea la llamada al Collector Facade
	 * 
	 * @param subscriberId
	 * @param sessionId
	 */
	public void dispachEnableNotificationSubscriberConsumption(String subscriberId, Integer sessionId) {
		// Notificar al collector
		meterModule.getCollectorFacade().enableNotificationSubscriberConsumption(subscriberId, sessionId);
	}

	/**
	 * Este metodo forwardea la llamada al Collector Facade
	 * 
	 * @param subscriberId
	 * @param sessionId
	 */
	public void dispachDisableNotificationSubscriberConsumption(String subscriberId, Integer sessionId) {
		// Notificar al collector
		meterModule.getCollectorFacade().disableNotificationSubscriberConsumption(subscriberId, sessionId);
	}

	/**
	 * Este metodo notifica al Session Module de las sessiones que expiraron
	 * 
	 * @param forDeleteDueToDeplete
	 */
	public void dispachNotifyDepletedSessions(List<DynamicSession> forDeleteDueToDeplete) {
		// Notifico al session module las sessiones depleteds
		meterModule.getSessionFacade().sessionsDepleteds(forDeleteDueToDeplete);
	}

	/**
	 * Este metodo notifica al Accounting Module de los contadores de las sesiones
	 * 
	 * @param mapSubscribersCounters
	 */
	public void dispachSendPeriodicCounters(FastTreeMap mapSubscribersCounters) {
		// Notifico al Accounting module de los contadores periodicos
		meterModule.getAccountingFacade().notifyPeriodicCounters(mapSubscribersCounters);
	}

	/**
	 * Este metodo notifica al Accounting Module los updates de quota
	 * 
	 * @param consumptions
	 */
	public void dispachSendQuotaVolumeUpdates(FastTreeMap consumptions) {
		// Notifico al Accounting los updates de cuota
		meterModule.getAccountingFacade().quotaVolumeUpdates(consumptions);
	}

}
