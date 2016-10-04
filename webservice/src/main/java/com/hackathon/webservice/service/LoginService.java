package com.hackathon.webservice.service;

import com.hackathon.mario.domain.UserProfile;

public interface LoginService {

	public UserProfile doLogin(String username,String password, String loginAs);
	
	
}
