package com.callistech.policyserver.dsm.common.counters;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.callistech.policyserver.dsm.common.QuotaVolume;

public class SubscriberCounter implements Serializable {

	private String subscriberId;
	private Map<Integer, SubscriberServiceCounter> dynamicServices;

	public SubscriberCounter() {
		dynamicServices = new HashMap<Integer, SubscriberServiceCounter>();
	}

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	public Map<Integer, SubscriberServiceCounter> getDynamicServices() {
		return dynamicServices;
	}

	public void setDynamicServices(Map<Integer, SubscriberServiceCounter> dynamicServices) {
		this.dynamicServices = dynamicServices;
	}

	public void updateVolumeCounter(Integer serviceId, String sessionId, QuotaVolume qv) {
		SubscriberServiceCounter subscriberServiceCounter = null;
		if (dynamicServices.containsKey(serviceId)) {
			subscriberServiceCounter = dynamicServices.get(serviceId);
		} else {
			subscriberServiceCounter = new SubscriberServiceCounter();
			dynamicServices.put(serviceId, subscriberServiceCounter);
		}
		subscriberServiceCounter.updateVolumeCounter(sessionId, qv);
	}

	public void updateTimeCounter(Integer serviceId, String sessionId, int time) {
		SubscriberServiceCounter subscriberServiceCounter = null;
		if (dynamicServices.containsKey(serviceId)) {
			subscriberServiceCounter = dynamicServices.get(serviceId);
		} else {
			subscriberServiceCounter = new SubscriberServiceCounter();
			dynamicServices.put(serviceId, subscriberServiceCounter);
		}
		subscriberServiceCounter.updateTimeCounter(sessionId, time);
	}

}
