package com.callistech.policyserver.dsm.common.counters;

import java.io.Serializable;

import com.callistech.policyserver.dsm.common.QuotaVolume;

public class ServiceCounter implements Serializable {

	private Integer dsId;
	private Long up_volume = 0L;
	private Long down_volume = 0L;
	private Long time = 0L;
	private Integer active_subscribers = 0;
	private Integer concurrent_sessions = 0;

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

	public Integer getActive_subscribers() {
		return active_subscribers;
	}

	public void setActive_subscribers(Integer active_subscribers) {
		this.active_subscribers = active_subscribers;
	}

	public Integer getConcurrent_sessions() {
		return concurrent_sessions;
	}

	public void setConcurrent_sessions(Integer concurrent_sessions) {
		this.concurrent_sessions = concurrent_sessions;
	}

	public void updateVolumeCounter(QuotaVolume qv) {
		up_volume += qv.getUp();
		down_volume += qv.getDown();
	}

	public void updateTimeCounter(int time2) {
		time += time2;
	}

	public void increateActiveSubscribers() {
		active_subscribers++;
	}

	public void increaseConcurrentSessions() {
		concurrent_sessions++;
	}

	public void updateCounters(Long down_volume2, Long up_volume2, Long time2, Integer sessions) {
		up_volume += up_volume2;
		down_volume += down_volume2;
		time += time2;
		active_subscribers++;
		concurrent_sessions += sessions;
	}

}
