package com.hackathon.webservice.model;

/**
 * Default custom exception work.
 * @author puneet
 */
public class ZirconException extends RuntimeException {

	private int status;
	private String message;

	public ZirconException(int status){
		this.status=status;
	}
	
	public ZirconException(int status, String message){
		this.status=status;
		this.message=message;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
