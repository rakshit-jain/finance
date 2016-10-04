package com.hackathon.webservice.service;

import com.hackathon.mario.domain.UserProfile;

/**
 * @author Pranav.Nijhawan
 *
 */
public interface RegistrationService {

	
	public UserProfile registration(String emailAddress ,String mobileNumber , String name , String userCredential);
	
	/**
	 * @param emailAddress
	 * @param address
	 * @param name
	 * @param mobileNumber
	 * @param employeeId
	 * @param userCredential
	 * @return
	 */
	public UserProfile registration(String emailAddress, String address, String name, String mobileNumber,
			String employeeId ,String userCredential);
}
