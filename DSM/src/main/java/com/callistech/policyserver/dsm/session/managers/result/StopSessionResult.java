package com.callistech.policyserver.dsm.session.managers.result;

import java.io.Serializable;

public class StopSessionResult implements Serializable {

	private boolean success = false;
	private String message;

	public StopSessionResult() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "StopSessionResult [success=" + success + ", message=" + message + "]";
	}

}
