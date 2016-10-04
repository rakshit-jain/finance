package com.test.hackathon.service;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hackathon.mario.domain.UserProfile;
import com.hackathon.mario.domain.UserRole;
import com.hackathon.mario.domain.constants.UserRoleTypeEnum;
import com.hackathon.mario.domain.constants.UserStatusEnum;
import com.hackathon.webservice.Constants;
import com.hackathon.webservice.service.UserService;
import com.hackathon.webservice.util.PasswordUtils;
import com.test.hackathon.BaseTest;

public class UserServiceTest extends BaseTest{

	@Autowired
	UserService userService;
	
	@Test
	public void addUser(){
		UserProfile userProfile = new UserProfile();
		userProfile.setName("Nitish Kumar");
		userProfile.setEmailAddress("nitish@gmail.com");
		userProfile.setMobileNumber("9711616135");
		userProfile.setStatus(UserStatusEnum.ACTIVE);
		userProfile.setUserCredential(PasswordUtils.encryptPassword("123456", Constants.PP_ENCODING));
		List<UserRole> userRoles = new LinkedList<>();
		userRoles.add(new UserRole(UserRoleTypeEnum.USER));
		userProfile.setUserRole(userRoles);
		userService.modifyUser(userProfile);
	}
}
