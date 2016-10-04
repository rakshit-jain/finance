package com.hackathon.webservice;

public class Constants {

	public static class USER{
		public static final String USER_NAME="username";
		public static final String USER_PASSWORD="password";
		public static final String LOGIN_AS="loginAs";
		
	}
	
	public static class EMAIL{
		public static final Integer USER_REGISTER=101;
		public static final Integer REGISTER_OTP=102;
		public static final Integer CLAIM_COMPLETED=103;
	}
	public static class FILE_UPLOAD_CONTROLLER{
		public static final String IMAGE="/image";
		public static final String IMAGE_B64="/image/base64";
	}
	
	public static class CLAIM{
		public static final String CLAIM_ID="claimId";
		public static final String DOCUMENT_ID="documentId";
		public static final String DOCUMENT_STATUS="documentStatus";
	}
	public static final String PLAIN_ENCODING="plaintext";
	public static final String PP_ENCODING="ppencoding";
}
