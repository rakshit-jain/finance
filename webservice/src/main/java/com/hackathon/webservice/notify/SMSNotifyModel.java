package com.hackathon.webservice.notify;

import java.io.Serializable;

public class SMSNotifyModel implements Serializable {

	private String to;
	private String message;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
