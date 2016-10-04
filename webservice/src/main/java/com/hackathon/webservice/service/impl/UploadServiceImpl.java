package com.hackathon.webservice.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.hackathon.webservice.service.UploadService;
import com.hackathon.webservice.util.LoggerUtil;

public class UploadServiceImpl implements UploadService{

	@Override
	public void saveNewFile(String fileAbsolutePath, InputStream in) {
		Path path = Paths.get(fileAbsolutePath);
		try{
			Files.copy(in, path);
			LoggerUtil.debug("File saved at : "+fileAbsolutePath);
		}catch(IOException e){
			LoggerUtil.error(e);
		}
	}
}
