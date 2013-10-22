package com.callistech.policyserver.dsm.common;

import java.io.Serializable;

public class QuotaVolume implements Serializable {

	private Long down = 0L;
	private Long up = 0L;

	public QuotaVolume() {
		// TODO Auto-generated constructor stub
	}

	public Long getDown() {
		return down;
	}

	public void setDown(Long down) {
		this.down = down;
	}

	public Long getUp() {
		return up;
	}

	public void setUp(Long up) {
		this.up = up;
	}

	public void sum(long u, long d) {
		down += d;
		up += u;
	}

	public void update(QuotaVolume newQuota) {
		down += newQuota.getDown();
		up += newQuota.getUp();
	}

}
