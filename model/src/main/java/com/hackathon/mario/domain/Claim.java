package com.hackathon.mario.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.hackathon.mario.domain.constants.ClaimStatusEnum;

public class Claim extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6587852667193182541L;

	@Id
	private String id;

	private UserProfile userProfile;

	private ClaimStatusEnum status;

	private String statusReason;

	private List<UserDocuments> userDocuments;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public ClaimStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ClaimStatusEnum status) {
		this.status = status;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public List<UserDocuments> getUserDocuments() {
		return userDocuments;
	}

	public void setUserDocuments(List<UserDocuments> userDocuments) {
		this.userDocuments = userDocuments;
	}
}
