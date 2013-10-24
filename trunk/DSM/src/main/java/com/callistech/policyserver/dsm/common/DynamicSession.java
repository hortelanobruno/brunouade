package com.callistech.policyserver.dsm.common;

import java.io.Serializable;

public class DynamicSession implements Serializable {

	private Integer sessionId;
	private String subscriberId;
	private Integer serviceId;
	private Long startTime;
	private Long stopTime;
	private Long pauseTime;
	private Long resumeTime;
	private Long tc_upVolume = 0L;
	private Long tc_downVolume = 0L;
	private Long tc_bothVolume = 0L;
	private Long tc_time = 0L;
	private Long ul_upVolume = 0L;
	private Long ul_downVolume = 0L;
	private Long ul_bothVolume = 0L;
	private Long ul_time = 0L;
	private boolean active = false;
	private MatchCriteria matchCriteria;
	private boolean timeLimitEnabled = false;
	private boolean upVolumeLimitEnabled = false;
	private boolean downVolumeLimitEnabled = false;
	private Long timestamp;

	public DynamicSession() {
		// TODO Auto-generated constructor stub
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getStopTime() {
		return stopTime;
	}

	public void setStopTime(Long stopTime) {
		this.stopTime = stopTime;
	}

	public Long getPauseTime() {
		return pauseTime;
	}

	public void setPauseTime(Long pauseTime) {
		this.pauseTime = pauseTime;
	}

	public Long getResumeTime() {
		return resumeTime;
	}

	public void setResumeTime(Long resumeTime) {
		this.resumeTime = resumeTime;
	}

	public Long getTc_upVolume() {
		return tc_upVolume;
	}

	public void setTc_upVolume(Long tc_upVolume) {
		this.tc_upVolume = tc_upVolume;
	}

	public Long getTc_downVolume() {
		return tc_downVolume;
	}

	public void setTc_downVolume(Long tc_downVolume) {
		this.tc_downVolume = tc_downVolume;
	}

	public Long getTc_bothVolume() {
		return tc_bothVolume;
	}

	public void setTc_bothVolume(Long tc_bothVolume) {
		this.tc_bothVolume = tc_bothVolume;
	}

	public Long getTc_time() {
		return tc_time;
	}

	public void setTc_time(Long tc_time) {
		this.tc_time = tc_time;
	}

	public Long getUl_upVolume() {
		return ul_upVolume;
	}

	public void setUl_upVolume(Long ul_upVolume) {
		this.ul_upVolume = ul_upVolume;
	}

	public Long getUl_downVolume() {
		return ul_downVolume;
	}

	public void setUl_downVolume(Long ul_downVolume) {
		this.ul_downVolume = ul_downVolume;
	}

	public Long getUl_bothVolume() {
		return ul_bothVolume;
	}

	public void setUl_bothVolume(Long ul_bothVolume) {
		this.ul_bothVolume = ul_bothVolume;
	}

	public Long getUl_time() {
		return ul_time;
	}

	public void setUl_time(Long ul_time) {
		this.ul_time = ul_time;
	}

	public MatchCriteria getMatchCriteria() {
		return matchCriteria;
	}

	public void setMatchCriteria(MatchCriteria matchCriteria) {
		this.matchCriteria = matchCriteria;
	}

	public boolean isTimeLimitEnabled() {
		return timeLimitEnabled;
	}

	public void setTimeLimitEnabled(boolean timeLimitEnabled) {
		this.timeLimitEnabled = timeLimitEnabled;
	}

	public boolean isUpVolumeLimitEnabled() {
		return upVolumeLimitEnabled;
	}

	public void setUpVolumeLimitEnabled(boolean upVolumeLimitEnabled) {
		this.upVolumeLimitEnabled = upVolumeLimitEnabled;
	}

	public boolean isDownVolumeLimitEnabled() {
		return downVolumeLimitEnabled;
	}

	public void setDownVolumeLimitEnabled(boolean downVolumeLimitEnabled) {
		this.downVolumeLimitEnabled = downVolumeLimitEnabled;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int countTime(long timestamp) {
		if (timeLimitEnabled) {
			if (this.timestamp == null) {
				this.timestamp = startTime;
			}
			if (timestamp > (this.timestamp + (1000 * 60))) {
				tc_time++;
				this.timestamp = timestamp;
				return 1;
			}
		}
		return 0;
	}

	public void countingVolume(long down, long up) {
		if (downVolumeLimitEnabled) {
			this.tc_downVolume += down;
		}
		if (upVolumeLimitEnabled) {
			this.tc_upVolume += up;
		}
	}

	public boolean isDepleted() {
		switch (matchCriteria) {
		case MATCH_ALL:
			if (downVolumeLimitEnabled) {
				if (tc_downVolume < ul_downVolume) {
					return false;
				}
			}
			if (upVolumeLimitEnabled) {
				if (tc_upVolume < ul_upVolume) {
					return false;
				}
			}
			if (timeLimitEnabled) {
				if (tc_time < ul_time) {
					return false;
				}
			}
			return true;
		case MATCH_ANY:
			if (downVolumeLimitEnabled) {
				if (tc_downVolume >= ul_downVolume) {
					return true;
				}
			}
			if (upVolumeLimitEnabled) {
				if (tc_upVolume >= ul_upVolume) {
					return true;
				}
			}
			if (timeLimitEnabled) {
				if (tc_time >= ul_time) {
					return true;
				}
			}
			break;
		}
		return false;
	}

	public void setLimits(Limits limits) {
		setUl_bothVolume(limits.getVolumeBoth());
		setUl_downVolume(limits.getVolumeDownstream());
		setUl_upVolume(limits.getVolumeUpstream());
		setUl_time(limits.getDuration());
	}

	@Override
	public String toString() {
		return "DynamicSession [sessionId=" + sessionId + ", subscriberId=" + subscriberId + ", serviceId=" + serviceId + ", startTime=" + startTime + ", stopTime=" + stopTime + ", pauseTime=" + pauseTime + ", resumeTime=" + resumeTime + ", tc_upVolume=" + tc_upVolume + ", tc_downVolume=" + tc_downVolume + ", tc_bothVolume=" + tc_bothVolume + ", tc_time=" + tc_time + ", ul_upVolume=" + ul_upVolume + ", ul_downVolume=" + ul_downVolume + ", ul_bothVolume=" + ul_bothVolume + ", ul_time=" + ul_time
				+ ", active=" + active + ", matchCriteria=" + matchCriteria + ", timeLimitEnabled=" + timeLimitEnabled + ", upVolumeLimitEnabled=" + upVolumeLimitEnabled + ", downVolumeLimitEnabled=" + downVolumeLimitEnabled + ", timestamp=" + timestamp + "]";
	}

}
