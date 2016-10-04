package com.test.hackathon.notify;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hackathon.mario.domain.UserOTP;
import com.hackathon.mario.domain.UserProfile;
import com.hackathon.webservice.Constants;
import com.hackathon.webservice.notify.SMSNotifier;
import com.hackathon.webservice.util.LoggerUtil;
import com.test.hackathon.BaseTest;

public class SMSNotifierTest extends BaseTest {

	@Autowired
	SMSNotifier notifier;
	
	@Test
	public void sendSMS(){
		UserProfile profile = new UserProfile();
		profile.setName("Puneet");
		profile.setEmailAddress("puneet739@gmail.com");
		LoggerUtil.debug("Doing our test"+notifier);
		notifier.sendNotifier(Constants.EMAIL.USER_REGISTER, "9711616135", profile);
	}
	
	@Test
	public void sendOTP(){
		UserOTP otp = new UserOTP();
		otp.setEmail("Puneet739@gmail.com");
		otp.setOtp("11122");
		LoggerUtil.debug("Doing our test"+notifier);
		notifier.sendNotifier(Constants.EMAIL.REGISTER_OTP, "9711616135", otp);
	}
}
