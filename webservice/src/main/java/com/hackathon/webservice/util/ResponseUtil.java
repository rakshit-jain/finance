package com.hackathon.webservice.util;

import com.hackathon.webservice.model.GenericResponse;
import com.hackathon.webservice.model.Result;

public class ResponseUtil {

	public static GenericResponse getResponse(){
		GenericResponse response = new GenericResponse(Result.GENERIC_RESPONSE);
		return response;
	}
	
}
