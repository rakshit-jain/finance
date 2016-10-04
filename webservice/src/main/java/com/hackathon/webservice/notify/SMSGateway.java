package com.hackathon.webservice.notify;

public interface SMSGateway {

	public void send(String message, String to);
	
}
