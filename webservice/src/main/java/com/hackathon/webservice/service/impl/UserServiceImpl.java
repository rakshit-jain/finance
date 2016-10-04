package com.hackathon.webservice.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hackathon.mario.domain.UserProfile;
import com.hackathon.webservice.Constants;
import com.hackathon.webservice.service.UserService;
import com.hackathon.webservice.util.PasswordUtils;

/**
 * @author Pranav.Nijhawan
 *
 */
public class UserServiceImpl implements UserService {
	@Autowired
	MongoOperations template;

	@Override
	public UserProfile findUserByUsername(String username) {
		Query query = QueryBuilder(username);

		List<UserProfile> userProfile = template.find(query, UserProfile.class);
		UserProfile user = null;
		if (userProfile != null && userProfile.size() >= 1) {
			user = userProfile.get(0);
		}
		return user;

	}

	private Query QueryBuilder(String username) {
		Query query = new Query();

		query.addCriteria(Criteria.where("emailAddress").is(username));
		return query;
	}

	/* (non-Javadoc)
	 * @see com.hackathon.webservice.service.UserService#findUserByUserId(java.lang.String)
	 */
	@Override
	public UserProfile findUserByUserId(String Id) {

		UserProfile userProfile = template.findById(Id, UserProfile.class);
		UserProfile user = null;
		if (userProfile != null) {
			user = userProfile;
		}
		return user;

	}

	@Override
	public void updatePassword(String Id, String password) {
		UserProfile userData = findUserByUserId(Id);

		userData.setUserCredential(PasswordUtils.encryptPassword(password, Constants.PP_ENCODING));
		template.save(userData);

	}
	//todo check employee id is send or Id 
	/**
	 * @param employeeId
	 * @param mobileNumber
	 * @param address
	 * @param name
	 */
	public void ModifyUserDetail( String employeeId ,String mobileNumber , String address , String name){
		UserProfile userProfile = template.findById(employeeId, UserProfile.class);
		if (userProfile!=null){
			
			userProfile.setAddress(address);
			userProfile.setMobileNumber(mobileNumber);
			userProfile.setName(name);
			userProfile.setEmployeeId(employeeId);
			userProfile.setUpdatedBy(name);
			userProfile.setUpdatedDate(new Date());
			template.save(userProfile);
			
		}
		
		
		
	}

	@Override
	public void modifyUser(UserProfile profile) {
		template.save(profile);
	}

}
