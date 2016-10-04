package com.hackathon.webservice.util;

import org.apache.commons.lang.RandomStringUtils;

public class Util {

	public static String generateRandomFileName(String extenstion) {
		StringBuffer fileName = new StringBuffer(RandomStringUtils.randomAlphabetic(70));
		fileName.append(".");
		fileName.append(extenstion);
		return fileName.toString();
	}
}
