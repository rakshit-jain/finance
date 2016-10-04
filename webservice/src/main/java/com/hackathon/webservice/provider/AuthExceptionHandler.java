package com.hackathon.webservice.provider;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.hackathon.webservice.model.AuthException;
import com.hackathon.webservice.model.GenericResponse;


public class AuthExceptionHandler implements ExceptionMapper<AuthException>{

	@Override
	public Response toResponse(AuthException exception) {
		GenericResponse response = new GenericResponse(exception.getStatus());
		response.setBody(exception.getMessage());
		return Response.status(401).entity(response).build();
	}
	
}
