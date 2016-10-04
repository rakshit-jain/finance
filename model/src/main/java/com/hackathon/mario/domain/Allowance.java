package com.hackathon.mario.domain;

/**
 * This class represents the type of allowance available in an organization.
 * 
 * @author rakshit.jain
 *
 */
public class Allowance extends Persistent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6710533648255512377L;

	private Long id;
	
	private String name;
	
	private String description;

	private int maxAmount;
	
	private int minAmount;
	
	private boolean defaultAvailable;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
	}

	public int getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(int minAmount) {
		this.minAmount = minAmount;
	}

	public boolean isDefaultAvailable() {
		return defaultAvailable;
	}

	public void setDefaultAvailable(boolean defaultAvailable) {
		this.defaultAvailable = defaultAvailable;
	}

	

}
