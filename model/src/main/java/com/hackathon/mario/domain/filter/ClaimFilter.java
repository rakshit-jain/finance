package com.hackathon.mario.domain.filter;

import java.util.Date;

import com.hackathon.mario.domain.constants.ClaimStatusEnum;

public class ClaimFilter {

	private String userID;
	private String emailAddress;
	private Date startDate;
	private Date endDate;
	private ClaimStatusEnum claimStatus;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ClaimStatusEnum getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(ClaimStatusEnum claimStatus) {
		this.claimStatus = claimStatus;
	}

}
