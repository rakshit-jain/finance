package com.hackathon.webservice.provider;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.hackathon.webservice.model.GenericResponse;
import com.hackathon.webservice.model.ZirconException;

public class ZirconExceptionHandler implements ExceptionMapper<ZirconException> {

	@Override
	public Response toResponse(ZirconException exception) {
		GenericResponse response = new GenericResponse(exception.getStatus());
		response.setBody(exception.getMessage());
		return Response.status(502).entity(response).build();
	}

}
