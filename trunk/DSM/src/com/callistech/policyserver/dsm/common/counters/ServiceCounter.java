package com.callistech.policyserver.dsm.common.counters;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.callistech.policyserver.dsm.common.QuotaVolume;

public class ServiceCounter implements Serializable {

	private Integer dsId;
	private Long up_volume = 0L;
	private Long down_volume = 0L;
	private Long time = 0L;
	private Set<String> active_subscribers = new HashSet<String>();
	private Set<String> concurrent_sessions = new HashSet<String>();

	public ServiceCounter() {
		// TODO Auto-generated constructor stub
	}

	public Integer getDsId() {
		return dsId;
	}

	public void setDsId(Integer dsId) {
		this.dsId = dsId;
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

	public Set<String> getActive_subscribers() {
		return active_subscribers;
	}

	public void setActive_subscribers(Set<String> active_subscribers) {
		this.active_subscribers = active_subscribers;
	}

	public Set<String> getConcurrent_sessions() {
		return concurrent_sessions;
	}

	public void setConcurrent_sessions(Set<String> concurrent_sessions) {
		this.concurrent_sessions = concurrent_sessions;
	}

	public void updateVolumeCounter(String subscriberId, String sessionId, QuotaVolume qv) {
		up_volume += qv.getUp();
		down_volume += qv.getDown();
		active_subscribers.add(subscriberId);
		concurrent_sessions.add(sessionId);
	}

	public void updateTimeCounter(String subscriberId, String sessionId, int time2) {
		time += time2;
		active_subscribers.add(subscriberId);
		concurrent_sessions.add(sessionId);
	}

}
