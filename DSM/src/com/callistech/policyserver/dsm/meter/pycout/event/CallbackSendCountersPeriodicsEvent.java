package com.callistech.policyserver.dsm.meter.pycout.event;

import java.util.Set;

import org.apache.commons.collections.FastTreeMap;

import com.callistech.policyserver.common.pyc.PYCEvent;

public class CallbackSendCountersPeriodicsEvent implements PYCEvent {

	private Set<String> total_active_subscribers;
	private FastTreeMap mapSubscribersCounters;
	private FastTreeMap mapServicesCounters;

	public CallbackSendCountersPeriodicsEvent() {
		// TODO Auto-generated constructor stub
	}

	public Set<String> getTotal_active_subscribers() {
		return total_active_subscribers;
	}

	public void setTotal_active_subscribers(Set<String> total_active_subscribers) {
		this.total_active_subscribers = total_active_subscribers;
	}

	public FastTreeMap getMapSubscribersCounters() {
		return mapSubscribersCounters;
	}

	public void setMapSubscribersCounters(FastTreeMap mapSubscribersCounters) {
		this.mapSubscribersCounters = mapSubscribersCounters;
	}

	public FastTreeMap getMapServicesCounters() {
		return mapServicesCounters;
	}

	public void setMapServicesCounters(FastTreeMap mapServicesCounters) {
		this.mapServicesCounters = mapServicesCounters;
	}

	@Override
	public String toStringBeauty() {
		return "CallbackSendCountersPeriodicsEvent.";
	}

}
