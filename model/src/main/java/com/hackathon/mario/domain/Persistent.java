package com.hackathon.mario.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Base class to be used by POJO.
 * @author rakshit.jain
 *
 */
public class Persistent implements Serializable{

	private static final long serialVersionUID = -1165859848049636558L;
	
	protected Date createdDate;

	protected Date updatedDate;

	protected String updatedBy;
	
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
}
