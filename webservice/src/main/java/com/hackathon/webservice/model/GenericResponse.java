package com.hackathon.webservice.model;

/**
 * This is the generic reponse
 * @author puneet
 *
 */
public class GenericResponse {

	private Object body;
	private int status;
	private String message;

	public GenericResponse(int status){
		this.status=status;
	}
	
	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
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
