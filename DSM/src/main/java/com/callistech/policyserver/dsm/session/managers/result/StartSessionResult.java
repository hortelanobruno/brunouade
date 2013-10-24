package com.callistech.policyserver.dsm.session.managers.result;

import java.io.Serializable;

public class StartSessionResult implements Serializable {

	private boolean success = false;
	private String message;

	public StartSessionResult() {
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
		return "StartSessionResult [success=" + success + ", message=" + message + "]";
	}

}
