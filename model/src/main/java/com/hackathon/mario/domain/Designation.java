package com.hackathon.mario.domain;

public class Designation extends Persistent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8794977066086940599L;

	private Long id;
	
	private String name;
	
	private Long allowanceId;
	
	private Long organizationId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAllowanceId() {
		return allowanceId;
	}

	public void setAllowanceId(Long allowanceId) {
		this.allowanceId = allowanceId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
}
