package com.hackathon.mario.mangedbeans;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hackathon.mario.Constants;
import com.hackathon.mario.domain.UserProfile;
import com.hackathon.mario.util.Util;

@Component
@Scope(value = "request")
public class UserProfileBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1445423007864630L;
	
	
	@Autowired
	UserProfile userProfile;
	
	public String updateProfile(){
		Util.httpPost(Constants.SERVICE_URL +"/user/updateProfile", userProfile, null);
		return null;
	}
}
