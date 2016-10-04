package com.hackathon.webservice.model;

public class FileUploadResponse {

	private String fileName;
	private int status;

	public FileUploadResponse(String fileName, int status) {
		this.fileName = fileName;
		this.status = status;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
