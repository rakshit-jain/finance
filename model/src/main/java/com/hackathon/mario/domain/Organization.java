package com.hackathon.mario.domain;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class represents the organization.
 * 
 * @author rakshit.jain
 */
@Document
public class Organization extends Persistent {

	private static final long serialVersionUID = -4742128311602767617L;

	private Long id;

	private String name;

	private String address;

	private String domain;

	private List<Allowance> allowances;

	private String logoPath;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public List<Allowance> getAllowances() {
		return allowances;
	}

	public void setAllowances(List<Allowance> allowances) {
		this.allowances = allowances;
	}

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

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	
	
}
