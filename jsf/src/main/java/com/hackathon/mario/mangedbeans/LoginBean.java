package com.hackathon.mario.mangedbeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hackathon.mario.Constants;
import com.hackathon.mario.domain.UserProfile;
import com.hackathon.mario.domain.constants.UserRoleTypeEnum;
import com.hackathon.mario.util.GsonHelper;
import com.hackathon.mario.util.LoggerUtil;
import com.hackathon.mario.util.Util;

@Component
@Scope(value="session")
public class LoginBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6092558307923995413L;
	private Gson gson = GsonHelper.getBaseGsonBuilder().create();
	private String email;
	private String password;
	private String loginAs=UserRoleTypeEnum.USER.name();
	private String homeURL;
	public String submitLoginAction(){
		LoggerUtil.debug("User is doing login here username"+email+" with password");
		Map<String,String> headers = new HashMap<>();
		headers.put("username", email);
		headers.put("password", password);
		headers.put("loginAs", loginAs);
		JsonObject jsonResponse = Util.httpPost(Constants.SERVICE_URL + "/access/login",null,headers);
		UserProfile registeredUser = gson.fromJson(jsonResponse, UserProfile.class);
		if (registeredUser!=null){
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("userProfile", registeredUser);
			if(UserRoleTypeEnum.USER.name().equals(loginAs)){
				homeURL= "userdashboard?faces-redirect=true";
			}else{
				homeURL= "adminLanding?faces-redirect=true";
			}
			return homeURL;
		}
		FacesContext.getCurrentInstance().addMessage("errorMessage", new FacesMessage("Invalid Credentials","Invalid Credentials"));
		return null;
	}
	
	
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return"loginPage?faces-redirect=true";
	}
	
	//Getter and Setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLoginAs() {
		return loginAs;
	}
	
	public void setLoginAs(String loginAs) {
		this.loginAs = loginAs;
	}
	
	public String getHomeURL() {
		return homeURL;
	}
	
	public void setHomeURL(String homeURL) {
		this.homeURL = homeURL;
	}
}
