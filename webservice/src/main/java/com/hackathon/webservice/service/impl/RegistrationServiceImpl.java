package com.hackathon.webservice.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.hackathon.mario.domain.UserProfile;
import com.hackathon.mario.domain.UserRole;
import com.hackathon.mario.domain.constants.UserRoleTypeEnum;
import com.hackathon.mario.domain.constants.UserStatusEnum;
import com.hackathon.webservice.Constants;
import com.hackathon.webservice.service.OtpService;
import com.hackathon.webservice.service.RegistrationService;
import com.hackathon.webservice.util.PasswordUtils;

public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	MongoOperations template;
	
	@Autowired
	OtpService otpService;

	@Override
	public UserProfile registration(String emailAddress, String mobileNumber, String name, String userCredential ) {
		UserProfile registration = new UserProfile();
		registration.setEmailAddress(emailAddress);
		if (!StringUtils.isBlank(mobileNumber)) {
			registration.setMobileNumber(mobileNumber);
			
		}
		registration.setName(name);
		registration.setCreatedDate(new Date());
		List<UserRole> userRoles = new LinkedList<>();
		userRoles.add(new UserRole(UserRoleTypeEnum.USER));
		registration.setUserRole(userRoles);
		registration.setStatus(UserStatusEnum.INACTIVE);
		registration.setUserCredential(PasswordUtils.encryptPassword(userCredential, Constants.PP_ENCODING));
		otpService.otp(emailAddress, mobileNumber);
		template.save(registration);
		return registration;
	}
	@Override
	public UserProfile registration(String emailAddress, String address, String name, String mobileNumber,
			String employeeId , String userCredential) {
		UserProfile registration = new UserProfile();
		registration.setAddress(address);
		registration.setEmailAddress(emailAddress);
		registration.setName(name);
		if (StringUtils.isNotEmpty(mobileNumber)) {
			registration.setMobileNumber(mobileNumber);
		}
		if (StringUtils.isNotEmpty(employeeId)) {
			registration.setEmployeeId(employeeId);
		}
		List<UserRole> userRoles = new LinkedList<>();
		userRoles.add(new UserRole(UserRoleTypeEnum.USER));
		registration.setUserRole(userRoles);
		registration.setUserCredential(PasswordUtils.encryptPassword(userCredential, Constants.PP_ENCODING));
		registration.setCreatedDate(new Date());
		template.save(registration);
		return registration;

	}
}