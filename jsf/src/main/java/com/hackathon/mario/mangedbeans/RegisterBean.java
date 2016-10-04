package com.hackathon.mario.mangedbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hackathon.mario.Constants;
import com.hackathon.mario.domain.UserProfile;
import com.hackathon.mario.util.GsonHelper;
import com.hackathon.mario.util.LoggerUtil;
import com.hackathon.mario.util.Util;

@Component
@Scope(value = "session")
public class RegisterBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6269388490164874803L;
	private Gson gson = GsonHelper.getBaseGsonBuilder().create();
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String password;
	
	@Autowired
	UserProfile currentUser;
	
	public String submitRegisterAction(){
		UserProfile userProfile = new UserProfile();
		userProfile.setEmailAddress(email);
		userProfile.setName(firstName+lastName);
		userProfile.setMobileNumber(mobileNumber);
		userProfile.setUserCredential(password);
		LoggerUtil.debug("Trying to register "+userProfile);
		JsonObject jsonResponse = Util.httpPost(Constants.SERVICE_URL + "/user", userProfile,null);
		UserProfile registeredUser = gson.fromJson(jsonResponse, UserProfile.class);
		if (registeredUser!=null){
			currentUser.setName(userProfile.getName());
			currentUser.setEmailAddress(userProfile.getEmailAddress());
			currentUser.setMobileNumber(userProfile.getMobileNumber());
			return "otppage?faces-redirect=true";
		}
		LoggerUtil.debug(jsonResponse);
		//TODO:: Need to move these messages to JSR messagess
		FacesContext.getCurrentInstance().addMessage("errorMessage", new FacesMessage("OOOPs we were not able to register you","OOOPs we were not able to register you"));
		return null;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
