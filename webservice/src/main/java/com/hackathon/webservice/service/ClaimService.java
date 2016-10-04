package com.hackathon.webservice.service;

import java.util.List;

import com.hackathon.mario.domain.Claim;
import com.hackathon.mario.domain.constants.DocumentStatusEnum;
import com.hackathon.mario.domain.filter.ClaimFilter;

public interface ClaimService {

	public List<Claim> getUserClaims(ClaimFilter filter);

	public Claim getClaimById(String claimid);

	public Claim addClaim(Claim claim);

	public List<Claim> getPendingClaims();

	public List<Claim> getSettledClaims();

	void updateDocumentStatus(Long documentId, String claimId, String statusEnum);
}
