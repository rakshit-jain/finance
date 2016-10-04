package com.hackathon.webservice.service;

import com.hackathon.mario.domain.UserProfile;

/**
 * @author Pranav.Nijhawan
 *
 */
public interface UserService {
	public UserProfile findUserByUsername(String username);
	
	public void updatePassword(String Id, String password);

	public UserProfile findUserByUserId(String Id);
	
	public void modifyUser(UserProfile profile);

}
