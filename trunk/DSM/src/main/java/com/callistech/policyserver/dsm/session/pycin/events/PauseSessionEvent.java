package com.callistech.policyserver.dsm.session.pycin.events;

import com.callistech.policyserver.common.pyc.PYCEvent;

public class PauseSessionEvent implements PYCEvent {

	private String subscriberId;
	private String dsd;

	public PauseSessionEvent() {
	}

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getDsd() {
		return dsd;
	}

	public void setDsd(String dsd) {
		this.dsd = dsd;
	}

	@Override
	public String toStringBeauty() {
		return toString();
	}

	@Override
	public String toString() {
		return "PauseSessionEvent [subscriberId=" + subscriberId + ", dsd=" + dsd + "]";
	}

}
