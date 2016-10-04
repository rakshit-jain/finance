package com.hackathon.webservice.service;

import java.io.InputStream;

public interface UploadService {

	public void saveNewFile(String fileAbsolutePath, InputStream in);

}
