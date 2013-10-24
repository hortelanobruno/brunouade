package com.callistech.policyserver.dsm.common;

import java.io.Serializable;

import com.callistech.policyserver.af.entities.vo.CountingTimeTypeVO;

public class Limits implements Serializable {

	private Long volumeUpstream = 0L;
	private Long volumeDownstream = 0L;
	private Long volumeBoth = 0L;
	private Long duration = 0L;
	private CountingTimeTypeVO countingType;

	public Limits() {
		// TODO Auto-generated constructor stub
	}

	public Long getVolumeUpstream() {
		return volumeUpstream;
	}

	public void setVolumeUpstream(Long volumeUpstream) {
		this.volumeUpstream = volumeUpstream;
	}

	public Long getVolumeDownstream() {
		return volumeDownstream;
	}

	public void setVolumeDownstream(Long volumeDownstream) {
		this.volumeDownstream = volumeDownstream;
	}

	public Long getVolumeBoth() {
		return volumeBoth;
	}

	public void setVolumeBoth(Long volumeBoth) {
		this.volumeBoth = volumeBoth;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public CountingTimeTypeVO getCountingType() {
		return countingType;
	}

	public void setCountingType(CountingTimeTypeVO countingType) {
		this.countingType = countingType;
	}

}
