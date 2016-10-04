package com.hackathon.mario.mangedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.hackathon.mario.Constants;
import com.hackathon.mario.domain.UserProfile;
import com.hackathon.mario.util.LoggerUtil;
import com.hackathon.mario.util.Util;

@Scope("session")
@Component(value = "otpbean")
public class OTPBean {

	@Autowired
	UserProfile user;

	private String otp;

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String doOTPValidate() {
		LoggerUtil.debug("Validate OTP here: " + otp);
		try {
			JsonObject jsonResponse = Util.httpGetRequest(
					Constants.SERVICE_URL + "/access/validateOTP?email=" + user.getEmailAddress() + "&otp=" + otp,
					null);
			if (jsonResponse != null) {
				LoggerUtil.debug("User Registered Successfully");
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("errorMessage",
					new FacesMessage("OTP Is invalid/Expired", "OTP Is invalid/Expired"));
			return null;
		}
		return "loginPage?faces-redirect=true";
	}
}
