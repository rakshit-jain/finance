package com.hackathon.webservice.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;

import com.hackathon.mario.domain.UserProfile;
import com.hackathon.webservice.model.GenericResponse;
import com.hackathon.webservice.model.Result;
import com.hackathon.webservice.service.RegistrationService;
import com.hackathon.webservice.service.UserService;
import com.hackathon.webservice.util.ResponseUtil;

public class UserController {
	
	@Autowired
	RegistrationService registerService;
	
	@Autowired 
	UserService userService;
	
	@Path(value = "/")
	@POST
	public GenericResponse registerUser(UserProfile userProfile) {
		UserProfile registeredUser = registerService.registration(userProfile.getEmailAddress(), userProfile.getMobileNumber(), userProfile.getName(),userProfile.getUserCredential());
		GenericResponse response = ResponseUtil.getResponse();
		response.setBody(registeredUser);
		response.setStatus(Result.OK);
		return response;
	}
	
	@Path(value = "/updateProfile")
	@POST
	public GenericResponse updateProfile(UserProfile userProfile) {
		userService.modifyUser(userProfile);
		GenericResponse response = ResponseUtil.getResponse();
		response.setStatus(Result.OK);
		return response;
	}
}
