package com.o2.co.uk.service;

import com.mongodb.client.result.UpdateResult;
import com.o2.co.uk.infra.PropertiesManager;
import com.o2.co.uk.model.IdentityV3;
import com.o2.co.uk.util.CustomStringUtil;
import com.o2.co.uk.util.FileUtility;
import com.o2.co.uk.util.MsisdnValidator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IdentityV3Service {

    private static final Logger logger = Logger.getLogger(IdentityV3Service.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PropertiesManager propertiesManager;

    @Autowired
    private FileUtility fileUtility;

    public boolean getBackupUsingUid(String username,String oldMsisdn) {
    	Query query = new Query(Criteria.where("username").is(username));
        List<IdentityV3> identityV3s = mongoTemplate.find(query, IdentityV3.class);
        if(identityV3s.size()!=0) {
        	String msisdnInIdentityV3 = null;
        	for (IdentityV3 identityV3 : identityV3s) {
        		msisdnInIdentityV3=identityV3.getMsisdn();
        		if(msisdnInIdentityV3==null)
        			msisdnInIdentityV3 = "null";
            }
        	if(msisdnInIdentityV3.equals(oldMsisdn)) {
        		fileUtility.writeToFileIfDataIsPresent(CustomStringUtil.getStringFormatFromList(identityV3s), "identityV3.backup.file");
        		logger.info("backup has been created for Username: "+username);
        		return true;
        	}
        	else if(msisdnInIdentityV3.equals("null")){
        		fileUtility.writeToFileIfDataIsPresent(CustomStringUtil.getStringFormatFromList(identityV3s), "identityV3.backup.file");
        		logger.info("backup has been created for Username: "+username);
        		return true;
        	}
        	else {
        		logger.info("Provided msisdn is not matched with msisdn present in database");
            	String UnProcessedData="Username: "+username+","+"OldMsisdn: "+oldMsisdn;
            	fileUtility.writeToFileIfDataIsPresent(UnProcessedData, "identityV3.unprocessed.backup.file");
            	return false;
        	}
        		
        }
        else {
        	logger.info("No entry found for Username: "+username);
        	String UnProcessedData="Username: "+username+","+"OldMsisdn: "+oldMsisdn;
        	fileUtility.writeToFileIfDataIsPresent(UnProcessedData, "identityV3.unprocessed.backup.file");
        	return false;
        }
    }
    
    public void updateIdentityV3Collection(String username,String oldMsisdn,String newMsisdn) {
    	Query query = new Query(Criteria.where("username").is(username));
    	 Update update = new Update();
    	 update.set("msisdn",newMsisdn);
    	 UpdateResult updateResult = mongoTemplate.updateMulti(query, update, IdentityV3.class );
    	 logger.info("updated IdentityV3 for Username: "+username+" OldMsisdn: "+oldMsisdn+" NewMsisdn: "+newMsisdn+" UpdateResult: "+updateResult);
    }
}

