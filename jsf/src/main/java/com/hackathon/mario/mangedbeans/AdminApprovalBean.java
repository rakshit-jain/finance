package com.hackathon.mario.mangedbeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hackathon.mario.Constants;
import com.hackathon.mario.domain.Claim;
import com.hackathon.mario.domain.UserDocuments;
import com.hackathon.mario.domain.UserProfile;
import com.hackathon.mario.util.GsonHelper;
import com.hackathon.mario.util.Util;

@Component
@Scope(value="request")
public class AdminApprovalBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4405106248665035975L;
	private List<UserDocuments> documents;
	private Gson gson = GsonHelper.getBaseGsonBuilder().create();
	private UserProfile userProfile;
	private String claimId;
	private String URL = Constants.SERVICE_URL;
	
	public void init(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		 claimId = request.getParameter("claimId");
		if(claimId != null && !claimId.isEmpty()){
			initData(claimId);
		}
		
	}
	public List<UserDocuments> getDocuments() {
		return documents;
	}

	public void setDocuments(List<UserDocuments> documents) {
		this.documents = documents;
	}
	
	public String viewDocument(String documentId){
		System.out.println(documentId);
		return "http://deti.zp.ua/images/big4/krtsenamed.jpg?faces-redirect=true";
	}
	
	private void initData(String claimId) {
		Map<String,String> headers = new HashMap<>();
		headers.put("claimId", claimId);
		JsonObject jsonResponse = Util.httpGetRequest(Constants.SERVICE_URL + "/claim/getClaimById",headers);
		Claim claim = gson.fromJson(jsonResponse, Claim.class);
		if(claim != null){
			userProfile = claim.getUserProfile();
			documents= claim.getUserDocuments();
		}
	}
	
	public UserProfile getUserProfile() {
		return userProfile;
	}
	
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
	
	public String getClaimId() {
		return claimId;
	}
	public String getURL() {
		return URL;
	}
}
