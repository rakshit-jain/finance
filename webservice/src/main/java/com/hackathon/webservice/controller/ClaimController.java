package com.hackathon.webservice.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hackathon.mario.domain.Claim;
import com.hackathon.mario.domain.UserDocuments;
import com.hackathon.mario.domain.filter.ClaimFilter;
import com.hackathon.webservice.Constants;
import com.hackathon.webservice.model.GenericResponse;
import com.hackathon.webservice.model.Result;
import com.hackathon.webservice.service.ClaimService;
import com.hackathon.webservice.service.UserService;
import com.hackathon.webservice.util.ResponseUtil;

public class ClaimController {

	@Autowired
	ClaimService claimService;
	
	@Autowired
	UserService userService;
	
	@Context
	private MessageContext context;
	
	@Path(value = "/getClaim")
	@POST
	public GenericResponse getClaim(ClaimFilter filter) {
		List<Claim> claims = claimService.getUserClaims(filter);
		GenericResponse response = ResponseUtil.getResponse();
		response.setBody(claims);
		response.setStatus(Result.OK);
		return response;
	}
	
	
	@Path(value = "/getClaimById")
	@GET
	public GenericResponse getClaimById() {
		HttpServletRequest request = context.getHttpServletRequest();
		String claimId = request.getHeader(Constants.CLAIM.CLAIM_ID);
		Claim claim = claimService.getClaimById(claimId);
		GenericResponse response = ResponseUtil.getResponse();
		response.setBody(claim);
		response.setStatus(Result.OK);
		return response;
	}
	
	@Path(value = "/addClaim")
	@POST
	public GenericResponse addClaim(Claim claim, @QueryParam(value="useremail") String userEmail) {
		claim.setUserProfile(userService.findUserByUsername(userEmail));
		if (claim!=null && claim.getUserDocuments()!=null)
			for (UserDocuments documents: claim.getUserDocuments()){
				documents.setId(Long.parseLong(RandomStringUtils.randomNumeric(5)));
				documents.setCreatedDate(new Date());
			}
		claim = claimService.addClaim(claim);
		GenericResponse response = ResponseUtil.getResponse();
		response.setBody(claim);
		response.setStatus(Result.OK);
		return response;
	}
	
	
	@Path(value = "/pendingClaims")
	@GET
	public GenericResponse getPendingClaim() {
		List<Claim> claims = claimService.getPendingClaims();
		GenericResponse response = ResponseUtil.getResponse();
		response.setBody(claims);
		response.setStatus(Result.OK);
		return response;
	}
	

	@Path(value = "/settledClaims")
	@GET
	public GenericResponse getSettledClaim() {
		List<Claim> claims = claimService.getSettledClaims();
		GenericResponse response = ResponseUtil.getResponse();
		response.setBody(claims);
		response.setStatus(Result.OK);
		return response;
	}
	
	@Path(value = "/updateDocumentStatus")
	@POST
	public GenericResponse updateDocumentStatus(@QueryParam(Constants.CLAIM.CLAIM_ID) String claimId,
			@QueryParam(Constants.CLAIM.DOCUMENT_ID) String documentId,
			@QueryParam(Constants.CLAIM.DOCUMENT_STATUS) String documentStatus) {
		claimService.updateDocumentStatus(Long.valueOf(documentId), claimId, documentStatus);
		GenericResponse response = ResponseUtil.getResponse();
		response.setStatus(Result.OK);
		return response;
	}

}
