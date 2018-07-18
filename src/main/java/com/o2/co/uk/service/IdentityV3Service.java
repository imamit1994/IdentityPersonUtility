package com.o2.co.uk.service;

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
public class IdentityV3Service {

    private static final Logger logger = Logger.getLogger(IdentityV3Service.class);

    @Autowired
    @Qualifier(value="identityMongoTemplate")
    private MongoTemplate mongoTemplate;

    @Autowired
    private FileUtility fileUtility;

    public void getBackUPFromUserName(String userName) {
    	int counter=0;
    	logger.info("creating backup from identityV3 for UserName::"+userName);
    	 Query query = new Query(Criteria.where("username").is(userName));
         List<IdentityV3> identityV3s = mongoTemplate.find(query, IdentityV3.class);
         counter=fileUtility.writeTOFileIfDataIsPresent(identityV3s, "identityV3.backup.file",counter);
         if(counter==1) {
        	 logger.info("UserName::"+userName+" not found in db");
         }
        if(counter==0)	 {
         logger.info("backup has been created from identityV3 for UserName::"+userName);
        }
    }

	public void deleteFromIdentityV3(String inputUserName) {
		 Query query = new Query(Criteria.where("username").is(inputUserName));
		 List<IdentityV3> identityV3s = mongoTemplate.find(query, IdentityV3.class);
		 if(identityV3s.size()!=0) {
			 logger.info("Deletion started from identityV3 for UserName::"+inputUserName);
			 mongoTemplate.remove(query, IdentityV3.class);
			 logger.info("Deletion completed from identityV3 for UserName"+inputUserName);
		 }
		 else {
			 logger.info("No data found for UserName::"+inputUserName+" in identityV3");
		 }
	}
}

