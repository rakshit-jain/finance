package com.hackathon.mario.mangedbeans;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.hackathon.mario.Constants;
import com.hackathon.mario.domain.Claim;
import com.hackathon.mario.util.GsonHelper;
import com.hackathon.mario.util.LoggerUtil;
import com.hackathon.mario.util.Util;

@Component
@Scope(value = "request")
public class AdminViewBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2641884785856058932L;

	private List<Claim> pendingClaims;
	private List<Claim> closedClaims;

	private Gson gson = GsonHelper.getBaseGsonBuilder().create();

	public void init() {
		LoggerUtil.debug("We are initalizating the Page");
		pendingClaims = getPendingCalims();
		closedClaims = getSettledCalims();
	}

	private List<Claim> getPendingCalims() {
		JsonArray jsonResponse = Util.httpGetRequestArray(Constants.SERVICE_URL + "/claim/pendingClaims");
		Type listType = new TypeToken<List<Claim>>() {}.getType();
		List<Claim> claims = gson.fromJson(jsonResponse, listType);
		return claims;
	}

	private List<Claim> getSettledCalims() {
		JsonArray jsonResponse = Util.httpGetRequestArray(Constants.SERVICE_URL + "/claim/settledClaims");
		Type listType = new TypeToken<List<Claim>>() {}.getType();
		List<Claim> claims = gson.fromJson(jsonResponse, listType);
		return claims;
	}
	
	
	public List<Claim> getPendingClaims() {
		return pendingClaims;
	}

	public void setPendingClaims(List<Claim> pendingClaims) {
		this.pendingClaims = pendingClaims;
	}

	public List<Claim> getClosedClaims() {
		return closedClaims;
	}

	public void setClosedClaims(List<Claim> closedClaims) {
		this.closedClaims = closedClaims;
	}

}
