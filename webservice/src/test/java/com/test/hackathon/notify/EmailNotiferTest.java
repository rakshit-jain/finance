package com.test.hackathon.notify;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hackathon.mario.domain.UserOTP;
import com.hackathon.mario.domain.UserProfile;
import com.hackathon.webservice.Constants;
import com.hackathon.webservice.notify.EmailNotifier;
import com.hackathon.webservice.util.LoggerUtil;
import com.test.hackathon.BaseTest;

public class EmailNotiferTest extends BaseTest{

	@Autowired
	EmailNotifier notifier;
	
	@Test
	public void sendMail(){
		UserOTP otp = new UserOTP();
		otp.setEmail("Puneet739@gmail.com");
		otp.setOtp("12234");
		LoggerUtil.debug("Doing our test"+notifier);
		notifier.sendNotifier(Constants.EMAIL.REGISTER_OTP, otp.getEmail(), otp);
	}
}
