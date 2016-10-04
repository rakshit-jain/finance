package com.hackathon.mario.domain;

import com.hackathon.mario.domain.constants.GovernmentDocumentTypeEnum;

/**
 * 
 * @author rakshit.jain
 */
public class GovernmentDocuments extends Persistent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8921372279155501276L;

	private Long id;
	
	private GovernmentDocumentTypeEnum documentEnum;
	
	private String fileName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GovernmentDocumentTypeEnum getDocumentEnum() {
		return documentEnum;
	}

	public void setDocumentEnum(GovernmentDocumentTypeEnum documentEnum) {
		this.documentEnum = documentEnum;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
