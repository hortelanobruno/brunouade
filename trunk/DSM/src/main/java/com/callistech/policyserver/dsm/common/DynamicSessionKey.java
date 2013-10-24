package com.callistech.policyserver.dsm.common;

import java.io.Serializable;

import com.callistech.policyserver.af.entities.vo.bod.ClassifierVO;

public class DynamicSessionKey implements Serializable {

	private String subscriberId;
	private Integer dsId;
	private ClassifierVO classifierVO;

	public DynamicSessionKey() {
		// TODO Auto-generated constructor stub
	}

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	public Integer getDsId() {
		return dsId;
	}

	public void setDsId(Integer dsId) {
		this.dsId = dsId;
	}

	public ClassifierVO getClassifierVO() {
		return classifierVO;
	}

	public void setClassifierVO(ClassifierVO classifierVO) {
		this.classifierVO = classifierVO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classifierVO == null) ? 0 : classifierVO.hashCode());
		result = prime * result + ((dsId == null) ? 0 : dsId.hashCode());
		result = prime * result + ((subscriberId == null) ? 0 : subscriberId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DynamicSessionKey other = (DynamicSessionKey) obj;
		if (classifierVO == null) {
			if (other.classifierVO != null)
				return false;
		} else if (!classifierVO.equals(other.classifierVO))
			return false;
		if (dsId == null) {
			if (other.dsId != null)
				return false;
		} else if (!dsId.equals(other.dsId))
			return false;
		if (subscriberId == null) {
			if (other.subscriberId != null)
				return false;
		} else if (!subscriberId.equals(other.subscriberId))
			return false;
		return true;
	}

}
