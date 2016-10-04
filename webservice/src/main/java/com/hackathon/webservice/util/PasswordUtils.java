package com.hackathon.webservice.util;

import org.apache.commons.codec.digest.DigestUtils;

import com.hackathon.webservice.Constants;

public class PasswordUtils {

	public static String encryptPassword(String text, String targetEncoding) {
		return encryptPassword(Constants.PLAIN_ENCODING,text, targetEncoding);
	}
	
	/**
	 * This function is used to convert the password to encrypted system
	 * 
	 * @param text
	 * @param targetEncoding
	 *            Wheter the password is plain or encrypted
	 * @return
	 */
	public static String encryptPassword(String baseEncoding, String text, String targetEncoding) {
		if (text == null)
			return text;
		String result = text;
		if (targetEncoding.equalsIgnoreCase(Constants.PP_ENCODING)) {
			String firstEncryption = DigestUtils.sha512Hex(text);
			String secondEncryption = DigestUtils.sha1Hex(firstEncryption);
			result = DigestUtils.sha1Hex(text);
		}
		if (targetEncoding.equalsIgnoreCase(Constants.PLAIN_ENCODING)) {
			result = text;
		}
		return result;
	}
}
