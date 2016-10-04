package com.hackathon.mario.domain;

import java.util.Date;

import com.hackathon.mario.domain.constants.DocumentStatusEnum;

/**
 * Class used to store documents uploaded by a user.
 * @author rakshit.jain
 */
public class UserDocuments extends Persistent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7299335276101565211L;

	private Long id;
	
	private String billType;
	
	private Allowance allowance;
	
	private String fileName;

	private String billNumber;
	
	private Date billDate;
	
	private Long billAmount;
	
	private DocumentStatusEnum status=DocumentStatusEnum.PENDING;
	
	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public Long getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(Long billAmount) {
		this.billAmount = billAmount;
	}

	public DocumentStatusEnum getStatus() {
		return status;
	}

	public void setStatus(DocumentStatusEnum status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Allowance getAllowance() {
		return allowance;
	}
	
	public void setAllowance(Allowance allowance) {
		this.allowance = allowance;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}
	
}
