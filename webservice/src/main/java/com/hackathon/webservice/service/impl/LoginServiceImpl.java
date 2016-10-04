package com.hackathon.webservice.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.hackathon.mario.domain.UserProfile;
import com.hackathon.mario.domain.UserRole;
import com.hackathon.mario.domain.constants.UserStatusEnum;
import com.hackathon.webservice.Constants;
import com.hackathon.webservice.model.AuthException;
import com.hackathon.webservice.service.LoginService;
import com.hackathon.webservice.service.UserService;
import com.hackathon.webservice.util.LoggerUtil;
import com.hackathon.webservice.util.PasswordUtils;

public class LoginServiceImpl implements LoginService {
	@Autowired
	UserService userService;

	@Autowired
	MongoOperations template;

	@Override
	public UserProfile doLogin(String username, String password, String loginAs) {
		UserProfile userData = null;
		if (StringUtils.isNotEmpty(username)) {
			userData = userService.findUserByUsername(username);
			if(userData != null){
				if (userData.getStatus()==UserStatusEnum.INACTIVE|| userData.getStatus()==UserStatusEnum.TERMINATED){
					throw new AuthException(403, "User not activated");
				}
				String userPassword = userData.getUserCredential();
				String encryptedPassword = PasswordUtils.encryptPassword(password, Constants.PP_ENCODING);
				if (!userPassword.equals(encryptedPassword)) {
					LoggerUtil.debug("Incorrect login Credential");
					throw new AuthException(403, "Incorrect login Credential");
				}
				boolean hasRole = false;
				for(UserRole role : userData.getUserRole()){
					if(role.getRoleTypeEnum() !=null && role.getRoleTypeEnum().name().equals(loginAs)){
						hasRole = true;
						break;
					}
				}
				if(!hasRole){
					throw new AuthException(403, "Incorrect login Credential");
				}
			}else{
				throw new AuthException(404, "User not found in system");
			}
		}else{
			throw new AuthException(405, "Username can not be null");
		}
		return userData;
	}

	protected boolean validUser(String username, String password) {
		boolean isValid = false;
		if ((username != null) && (username.length() > 0)) {
			UserProfile userData = userService.findUserByUsername(username);
			String userPassword = userData.getUserCredential();
			String encryptedPassword = PasswordUtils.encryptPassword(userPassword, Constants.PP_ENCODING);
			isValid = encryptedPassword.equals(password);
		}

		return isValid;
	}
}
