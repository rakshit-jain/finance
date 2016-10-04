package com.hackathon.webservice.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.fusesource.hawtbuf.ByteArrayInputStream;
import org.springframework.beans.factory.annotation.Autowired;

import com.hackathon.webservice.Constants;
import com.hackathon.webservice.model.FileUploadResponse;
import com.hackathon.webservice.model.GenericResponse;
import com.hackathon.webservice.model.Result;
import com.hackathon.webservice.model.ZirconException;
import com.hackathon.webservice.service.UploadService;
import com.hackathon.webservice.util.Config;
import com.hackathon.webservice.util.LoggerUtil;
import com.hackathon.webservice.util.ResponseUtil;
import com.hackathon.webservice.util.Util;

public class FileUploadController {

	@Autowired
	UploadService service;

	@POST
	@Path(Constants.FILE_UPLOAD_CONTROLLER.IMAGE)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public GenericResponse uploadImage(Attachment attachment) {
		LoggerUtil.info("Trying to upload a new file");
		final String contentType = attachment.getContentType().toString();
		if (!contentType.matches(".*")) {

		}
		final InputStream in = attachment.getObject(InputStream.class);

		String filePath = Config.getString("hackathon.images.path");
		LoggerUtil.debug("Uploading file path: " + filePath);

		GenericResponse response = ResponseUtil.getResponse();
		String fileName = Util.generateRandomFileName("png");
		service.saveNewFile(filePath + fileName, in);
		File finalFile = new File(filePath+fileName);
		LoggerUtil.info("The file was successfully uploaded");
		FileUploadResponse fResponse = new FileUploadResponse(Config.getString("hackathon.image.url.prefix")+fileName, Result.OK);
		response.setBody(fResponse);
		response.setStatus(Result.OK);
		return response;
	}

	@POST
	@Path(Constants.FILE_UPLOAD_CONTROLLER.IMAGE_B64)
	public GenericResponse upload(Base64ImageModel imageBytes) {
		String fileName = saveImage(imageBytes.getBase64());
		GenericResponse response = ResponseUtil.getResponse();
		response.setBody(Config.getString("zircon.image.url.prefix")+fileName);
		response.setStatus(Result.OK);
		return response;
	}

	private String saveImage(String bytes) {
		String filePrefix = Config.getString("zircon.images.path");
		String fileName = RandomStringUtils.randomAlphanumeric(10)+System.nanoTime();
		try {
			if (bytes!=null && bytes.split(",").length>1){
				String imageBytestring = bytes.split(",")[1];
				byte[] imageByte = org.apache.commons.codec.binary.Base64.decodeBase64(imageBytestring);
				File imgOutFile = new File(filePrefix+"/"+fileName+".png");
				BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imageByte));
				ImageIO.write(bufImg, "png", imgOutFile);
				fileName+=".png";
			}else{
				throw new ZirconException(Result.FILE_UPLOAD.FILE_FORMAT_NOT_APPROPRIATE);
			}
		} catch (IOException ex) {
			LoggerUtil.error("Excption caused while saving image",ex);
		}
		return fileName;
	}
	
}


class Base64ImageModel {
	private String base64;
	private String fileName;

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
}