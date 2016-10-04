package com.hackathon.webservice.model;

import java.io.Serializable;

public class NotifyModel implements Serializable {

	private Integer status;
	private String[] to;
	private String from;
	private Serializable[] obj;

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public NotifyModel(Integer status, Serializable... object) {
		this.status = status;
		this.obj = object;
	}

	public Serializable[] getObj() {
		return obj;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setObj(Serializable... obj) {
		this.obj = obj;
	}

}
