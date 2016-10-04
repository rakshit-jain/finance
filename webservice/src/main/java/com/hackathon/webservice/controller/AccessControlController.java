package com.hackathon.webservice.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hackathon.mario.domain.UserProfile;
import com.hackathon.mario.domain.UserRole;
import com.hackathon.mario.domain.constants.UserRoleTypeEnum;
import com.hackathon.mario.domain.constants.UserStatusEnum;
import com.hackathon.webservice.Constants;
import com.hackathon.webservice.model.GenericResponse;
import com.hackathon.webservice.model.Result;
import com.hackathon.webservice.model.ZirconException;
import com.hackathon.webservice.service.LoginService;
import com.hackathon.webservice.service.OtpService;
import com.hackathon.webservice.service.UserService;
import com.hackathon.webservice.util.ResponseUtil;

public class AccessControlController {

	@Context
	private MessageContext context;
	
	@Autowired
	UserService userService;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	OtpService otpService;
	
	@Path(value = "/login")
	@POST
	public GenericResponse doLogin() {
		HttpServletRequest request = context.getHttpServletRequest();
		String username = request.getHeader(Constants.USER.USER_NAME);
		String password = request.getHeader(Constants.USER.USER_PASSWORD);
		String loginAs = request.getHeader(Constants.USER.LOGIN_AS);
		UserProfile userProfile = loginService.doLogin(username, password,loginAs);
		GenericResponse response = ResponseUtil.getResponse();
		response.setBody(userProfile);
		response.setStatus(Result.OK);
		return response;
	}
	
	@Path(value = "/generateOTP")
	@GET
	public GenericResponse generateOTP(@QueryParam(value="email") String email,@QueryParam(value="mobileNo") String mobileno) {
		if (email!=null || mobileno!=null){
			otpService.otp(email, mobileno);
			GenericResponse response = ResponseUtil.getResponse();
			response.setStatus(Result.OK);
			return response;
		}
		throw new ZirconException(Result.INVALID_REQUEST);
	}
	
	@Path(value = "/validateOTP")
	@GET
	public GenericResponse validateOTP(@QueryParam(value="email") String email,@QueryParam(value="otp") String otp) {
		if ((email!=null||otp!=null) && otp!=null){
			if (otpService.validate(email, otp)){
				UserProfile profile = userService.findUserByUsername(email);
				profile.setStatus(UserStatusEnum.ACTIVE);
				List<UserRole> userRoles = new LinkedList<>();
				userRoles.add(new UserRole(UserRoleTypeEnum.USER));
				profile.setUserRole(userRoles);
				userService.modifyUser(profile);
			};
			GenericResponse response = ResponseUtil.getResponse();
			response.setStatus(Result.OK);
			return response;
		}
		return null;
	}
}
