package com.hackathon.webservice.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.hackathon.mario.domain.Claim;
import com.hackathon.mario.domain.UserDocuments;
import com.hackathon.mario.domain.constants.ClaimStatusEnum;
import com.hackathon.mario.domain.constants.DocumentStatusEnum;
import com.hackathon.mario.domain.filter.ClaimFilter;
import com.hackathon.webservice.Constants;
import com.hackathon.webservice.notify.EmailNotifier;
import com.hackathon.webservice.service.ClaimService;

public class ClaimServiceImpl implements ClaimService {

	@Autowired
	MongoOperations template;
	
	@Autowired
	EmailNotifier emailNotifier;
	
	@Override
	public List<Claim> getUserClaims(ClaimFilter filter) {
		Query query = getQuery(filter);
		List<Claim> claims = template.find(query, Claim.class);
		return claims;
	}

	private Query getQuery(ClaimFilter filter) {
		Query query = new Query();
		if (filter==null){
			return query;
		}
		if (filter.getEmailAddress()!=null){
			query.addCriteria(Criteria.where("userProfile.emailAddress").is(filter.getEmailAddress()));
		}
		query.with(new Sort(Sort.Direction.DESC, "_id"));
		query.limit(5);
		return query; 
	}

	@Override
	public Claim getClaimById(String claimid) {
		return template.findById(claimid, Claim.class);
	}

	@Override
	public Claim addClaim(Claim claim) {
		claim.setCreatedDate(new Date());
		claim.setStatus(ClaimStatusEnum.PENDING);
		template.save(claim);
		return claim;
	}

	@Override
	public List<Claim> getPendingClaims() {
		return template.find(getClaimsByStatusQuery(ClaimStatusEnum.PENDING), Claim.class);
	}

	@Override
	public List<Claim> getSettledClaims() {
		return template.find(getClaimsByStatusQuery(ClaimStatusEnum.SETTLED), Claim.class);
	}
	
	private Query getClaimsByStatusQuery(ClaimStatusEnum status) {
		Query query = new Query();
		query.addCriteria(Criteria.where("status").in(status));
		return query;
	}
	
	@Override
	public void updateDocumentStatus(Long documentId, String claimId, String status) {
		Query query = getQuery(documentId, claimId);
		template.updateMulti(query, Update.update("userDocuments.$.status", status), Claim.class);
		Claim claim = getClaimById(claimId);
		boolean pendingBill= false;
		for(UserDocuments documents : claim.getUserDocuments()){
			if(DocumentStatusEnum.PENDING.equals(documents.getStatus())){
				pendingBill = true;
			}
		}
		if(!pendingBill){
			emailNotifier.sendNotifier(Constants.EMAIL.CLAIM_COMPLETED, claim.getUserProfile().getEmailAddress(), claim,claim.getUserProfile());
			claim.setStatus(ClaimStatusEnum.SETTLED);
			template.save(claim);
		}
	}

	private Query getQuery(Long documentId, String claimId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userDocuments._id").is(documentId));
		query.addCriteria(Criteria.where("id").is(claimId));
		return query;
	}
}
