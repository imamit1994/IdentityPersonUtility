package com.o2.co.uk.service;

import com.o2.co.uk.model.IdentityActivationDetails;
import com.o2.co.uk.model.IdentityV3;
import com.o2.co.uk.util.FileUtility;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IdentityActivationDetailsService {

	private static final Logger logger = Logger.getLogger(IdentityActivationDetailsService.class);

	@Autowired
	@Qualifier(value="identityMongoTemplate")
	private MongoTemplate mongoTemplate;

	@Autowired
	private FileUtility fileUtility;

	public int getBackUPFromEmail(String email) {
		int counter=0;
		logger.info("creating backup from identityActivationDetail for Email::"+email);
		Query query = new Query(Criteria.where("email").is(email));
		List<IdentityActivationDetails> identityActivationDetailsList = mongoTemplate.find(query, IdentityActivationDetails.class);
		counter=fileUtility.writeTOFileIfDataIsPresent(identityActivationDetailsList,"identityActivationDetails.backup.file",counter); 
		if(counter==1) {
       	 logger.info("Email::"+email+" not found in db");
        }
       if(counter==0)	 {
        logger.info("backup has been created from identityActivationDetail for Email::"+email);
       }
       return counter;
	}

	public boolean deleteFromActivationdetails(String email) {
		boolean deleted;
		 Query query = new Query(Criteria.where("email").is(email));
		 List<IdentityActivationDetails> identityActivationDetailsList = mongoTemplate.find(query, IdentityActivationDetails.class);
		 if(identityActivationDetailsList.size()!=0) {
			 logger.info("Deletion started from identityActivationDetails for Email::"+email);
			 mongoTemplate.remove(query, IdentityActivationDetails.class);
			 logger.info("Deletion completed from identityActivationDetails for Email"+email);
			 deleted=true;
		 }
		 else {
			 logger.info("No data found for Email::"+email+" in identityActivationDetails");
			 deleted=false;
		 }
		 return deleted;
	}
}
