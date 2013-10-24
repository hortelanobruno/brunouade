package com.callistech.policyserver.dsm.common.subscriber;

import java.util.ArrayList;
import java.util.List;

public class SubscriberDS {

	private String subscriberId;
	private Integer siteId;
	private Integer tierId;
	private Integer scabbPolicyId;
	private Integer technologyId;
	private List<Integer> dsSubscriptions;

	public SubscriberDS() {
		dsSubscriptions = new ArrayList<Integer>();
	}

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Integer getTierId() {
		return tierId;
	}

	public void setTierId(Integer tierId) {
		this.tierId = tierId;
	}

	public Integer getScabbPolicyId() {
		return scabbPolicyId;
	}

	public void setScabbPolicyId(Integer scabbPolicyId) {
		this.scabbPolicyId = scabbPolicyId;
	}

	public Integer getTechnologyId() {
		return technologyId;
	}

	public void setTechnologyId(Integer technologyId) {
		this.technologyId = technologyId;
	}

	public List<Integer> getDsSubscriptions() {
		return dsSubscriptions;
	}

	public void setDsSubscriptions(List<Integer> dsSubscriptions) {
		this.dsSubscriptions = dsSubscriptions;
	}

	public boolean containsSubscription(Integer dsId) {
		return dsSubscriptions.contains(dsId);
	}

}
