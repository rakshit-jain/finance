package com.hackathon.mario.domain;

import com.hackathon.mario.domain.constants.UserRoleTypeEnum;

public class UserRole extends Persistent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5000639728813219044L;
	public UserRole() {
	}
	
	public UserRole(UserRoleTypeEnum roleType){
		roleTypeEnum=roleType;
	}
	
	public UserRoleTypeEnum getRoleTypeEnum() {
		return roleTypeEnum;
	}

	public void setRoleTypeEnum(UserRoleTypeEnum roleTypeEnum) {
		this.roleTypeEnum = roleTypeEnum;
	}

	private UserRoleTypeEnum roleTypeEnum;
}
