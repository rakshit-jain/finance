package com.hackathon.mario.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class is is used to bring user into the system. It will be a standalone
 * class .
 * 
 * @author rakshit.jain
 *
 */

@Document
public class UserOTP extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4402624436466638156L;
	@Id
	private String id;

	private String email;

	private String mobile;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	private String otp;

	private Date expiryDate;

}
