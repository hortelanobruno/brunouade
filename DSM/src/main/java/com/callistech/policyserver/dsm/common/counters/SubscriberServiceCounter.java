package com.callistech.policyserver.dsm.common.counters;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.callistech.policyserver.dsm.common.QuotaVolume;

public class SubscriberServiceCounter implements Serializable {

	private Long up_volume = 0L;
	private Long down_volume = 0L;
	private Long time = 0L;
	private Set<String> sessions = new HashSet<String>();

	public SubscriberServiceCounter() {
		// TODO Auto-generated constructor stub
	}

	public Long getUp_volume() {
		return up_volume;
	}

	public void setUp_volume(Long up_volume) {
		this.up_volume = up_volume;
	}

	public Long getDown_volume() {
		return down_volume;
	}

	public void setDown_volume(Long down_volume) {
		this.down_volume = down_volume;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Set<String> getSessions() {
		return sessions;
	}

	public void setSessions(Set<String> sessions) {
		this.sessions = sessions;
	}

	public void updateVolumeCounter(String sessionId, QuotaVolume qv) {
		up_volume += qv.getUp();
		down_volume += qv.getDown();
		sessions.add(sessionId);
	}

	public void updateTimeCounter(String sessionId, int time2) {
		time += time2;
		sessions.add(sessionId);
	}

}
