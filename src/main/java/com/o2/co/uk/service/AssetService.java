package com.o2.co.uk.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.o2.co.uk.model.Asset;
import com.o2.co.uk.model.IdentityActivationDetails;
import com.o2.co.uk.util.FileUtility;

@Component
public class AssetService {
	 private static final Logger logger = Logger.getLogger(AssetService.class);

	    @Autowired
	    @Qualifier(value="assetMongoTemplate")
	    private MongoTemplate mongoTemplate;

	    @Autowired
	    private FileUtility fileUtility;

		public void getBackUpFromIdentifierValue(String identifierValue,String assetType) {
			int counter=0;
	    	logger.info("creating backup from Asset for IdentifierValue::"+identifierValue+" and assetType: "+assetType);
			Query query = new Query(Criteria.where("identifier.value").is(identifierValue).and("assetType").is(assetType).regex(assetType,"i"));
	         List<Asset> assetIdentifier = mongoTemplate.find(query, Asset.class);
	         counter=fileUtility.writeTOFileIfDataIsPresent(assetIdentifier, "asset.backup.file",counter);
	         if(counter==1) {
	           	 logger.info("IdentifierValue::"+identifierValue+" not found in db for assetType::"+assetType);
	            }
	           if(counter==0)	 {
	            logger.info("backup has been created from Asset IdentifierValue::"+identifierValue+" for assetType::"+assetType);
	           }
		}

		public void deleteFromAsset(String identifierValue,String assetType) {
			Query query = new Query(Criteria.where("identifier.value").is(identifierValue).and("assetType").is(assetType).regex(assetType,"i"));
			List<Asset> assetIdentifier = mongoTemplate.find(query, Asset.class);
			 if(assetIdentifier.size()!=0) {
				 logger.info("Deletion started from Asset for IdentifierValue::"+identifierValue+" and assetType::"+assetType);
				 mongoTemplate.remove(query, Asset.class);
				 logger.info("Deletion completed from Asset for IdentifierValue::"+identifierValue+" and assetType::"+assetType);
			 }
		}
}
