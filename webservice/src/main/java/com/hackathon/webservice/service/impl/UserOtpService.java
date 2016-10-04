package com.hackathon.webservice.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hackathon.mario.domain.UserOTP;
import com.hackathon.webservice.Constants;
import com.hackathon.webservice.model.AuthException;
import com.hackathon.webservice.notify.EmailNotifier;
import com.hackathon.webservice.notify.SMSNotifier;
import com.hackathon.webservice.service.OtpService;

public class UserOtpService implements OtpService {

	@Autowired
	MongoOperations template;

	@Autowired
	EmailNotifier emailNotifier;
	
	@Autowired
	SMSNotifier smsNotifier;
	
	@Override
	public UserOTP otp(String email, String mobile) {
		String rnd = RandomStringUtils.randomNumeric(4);
		UserOTP userOtp = saveUserOtp(email, mobile, rnd);
		if (email != null) {
			emailNotifier.sendNotifier(Constants.EMAIL.REGISTER_OTP, email, userOtp);
		}
		if (mobile != null) {
			smsNotifier.sendNotifier(Constants.EMAIL.REGISTER_OTP, mobile, userOtp);
		}
		return userOtp;
	}

	public UserOTP saveUserOtp(String email, String mobile, String rnd) {
		UserOTP userOTP = new UserOTP();
		if (StringUtils.isNotEmpty(email)) {
			userOTP.setEmail(email);
		}
		if (StringUtils.isNotEmpty(mobile)) {
			userOTP.setMobile(mobile);
		}
		userOTP.setOtp(rnd);
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE,15);
		userOTP.setExpiryDate(now.getTime());
		template.save(userOTP);
		return userOTP;
	}

	@Override
	public boolean validate(String email, String otp) {
		Query query = QueryBuilder(email);
		List<UserOTP> userData = template.find(query, UserOTP.class);
		if(userData !=null){
			UserOTP userOtpData = userData.get(0);
			Date ll = userOtpData.getExpiryDate();
			if (ll.equals(new Date())|| ll.after(new Date())) {
				
				userOtpData.getOtp().equals(otp);
			}
			else {
				throw new AuthException(403, "Otp is Expired");
			}
		}else{
				throw new AuthException(403, "Invalid request");
		}
		return true;
	}

	private Query QueryBuilder(String email) {
		Query query= new Query();
		query.addCriteria(Criteria.where("email").is(email));
		return query;
		 
		
	}

}
