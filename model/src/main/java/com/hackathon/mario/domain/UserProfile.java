package com.hackathon.mario.domain;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.hackathon.mario.domain.constants.UserStatusEnum;

/**
 * Class represents a user in the system. User organization, designation and status governs the privileges available. 
 * @author rakshit.jain
 */
@Document
@Scope("session")
@Component
public class UserProfile extends Persistent{

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", employeeId=" + employeeId + ", name=" + name + ", address=" + address
				+ ", emailAddress=" + emailAddress + ", userRole=" + userRole + ", status=" + status + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4213908634054748703L;
    @Id
	private String id ;
	
	private String employeeId;
	
	private String name;
	
	private String address;
	
	private String description;
	
	private String emailAddress;
	
	private String mobileNumber;
	
	private Long organizationId;
	
	private Long designationId;
	
	private List<GovernmentDocuments> governmentDocs;
	
	private List<UserDocuments> userDocuments;
	
	private List<UserRole> userRole;
	
	private UserStatusEnum status;
	
	private  String userCredential;
	
	private String profileURL;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}

	public List<GovernmentDocuments> getGovernmentDocs() {
		return governmentDocs;
	}

	public void setGovernmentDocs(List<GovernmentDocuments> governmentDocs) {
		this.governmentDocs = governmentDocs;
	}

	public UserStatusEnum getStatus() {
		return status;
	}

	public void setStatus(UserStatusEnum status) {
		this.status = status;
	}

	public List<UserDocuments> getUserDocuments() {
		return userDocuments;
	}
	
	public void setUserDocuments(List<UserDocuments> userDocuments) {
		this.userDocuments = userDocuments;
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public List<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}

	public String getUserCredential() {
		return userCredential;
	}

	public void setUserCredential(String userCredential) {
		this.userCredential = userCredential;
	}

	public String getProfileURL() {
		return profileURL;
	}

	public void setProfileURL(String profileURL) {
		this.profileURL = profileURL;
	}
}
