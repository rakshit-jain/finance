package com.hackathon.mario.mangedbeans;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.hackathon.mario.Constants;
import com.hackathon.mario.domain.Claim;
import com.hackathon.mario.domain.UserProfile;
import com.hackathon.mario.domain.filter.ClaimFilter;
import com.hackathon.mario.util.GsonHelper;
import com.hackathon.mario.util.LoggerUtil;
import com.hackathon.mario.util.Util;

@Component(value = "userviewbean")
@Scope(value = "request")
public class UserViewBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6287723843023424130L;

	private List<Claim> claims;
	
	private Gson gson = GsonHelper.getBaseGsonBuilder().create();
	@Autowired
	UserProfile userProfile;

	public void fetchclaims() {
		
		ClaimFilter filter = new ClaimFilter();
		filter.setEmailAddress(userProfile.getEmailAddress());
		filter.setUserID(userProfile.getEmployeeId());
		
		JsonArray jsonArray =Util.httpPostRequestArray(Constants.SERVICE_URL + "/claim/getClaim", filter, null);
		
		Type listType = new TypeToken<List<Claim>>() {}.getType();
		 claims = gson.fromJson(jsonArray, listType);
		
		LoggerUtil.debug(claims);
	}

	public List<Claim> getClaims() {
		
		return claims;
	}

	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}
	
	

}
