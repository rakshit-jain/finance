package com.hackathon.webservice.service;

import com.hackathon.mario.domain.UserOTP;

public interface OtpService {
	
	public UserOTP otp(String email , String moblie);
	
	public boolean validate (String email ,String otp);

}
