package com.test.hackathon.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hackathon.webservice.service.ClaimService;
import com.test.hackathon.BaseTest;

public class ClaimServiceTest extends BaseTest {

	@Autowired
	ClaimService claimService;
	
	@Test
	public void fetchClaim(){
		claimService.updateDocumentStatus(1l, "57e795fece46c6381885df1c", "puneet");
	}
}
