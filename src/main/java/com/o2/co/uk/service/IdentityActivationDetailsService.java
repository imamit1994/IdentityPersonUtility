package com.o2.co.uk.service;

import com.mongodb.client.result.UpdateResult;
import com.o2.co.uk.infra.PropertiesManager;
import com.o2.co.uk.model.IdentityActivationDetails;
import com.o2.co.uk.model.IdentityV3;
import com.o2.co.uk.util.CustomStringUtil;
import com.o2.co.uk.util.FileUtility;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IdentityActivationDetailsService {

	private static final Logger logger = Logger.getLogger(IdentityActivationDetailsService.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private FileUtility fileUtility;

	public boolean getBackupUsingUid(String email, String oldMsisdn) {
		Query query = new Query(Criteria.where("email").is(email));
		List<IdentityActivationDetails> identityActivationDetailsList = mongoTemplate.find(query,
				IdentityActivationDetails.class);
		if (identityActivationDetailsList.size() != 0) {
			String contactNoIdentityActivationdetails = null;
			for (IdentityActivationDetails identityActivationDetails : identityActivationDetailsList) {
				contactNoIdentityActivationdetails = identityActivationDetails.getContactNumber();
			}
			if (contactNoIdentityActivationdetails.equals(oldMsisdn)) {
				fileUtility.writeToFileIfDataIsPresent(
						CustomStringUtil.getStringFormatFromList(identityActivationDetailsList),
						"identityActivationDetails.backup.file");
				logger.info("backup has been created for Email: " + email);
				return true;
			} else {
				logger.info("Provided msisdn is not matched with ContactNo present in database");
				String UnProcessedData = "Email: " + email + "," + "ContactNumber: " + oldMsisdn;
				fileUtility.writeToFileIfDataIsPresent(UnProcessedData,
						"identityActivationDetails.unprocessed.backup.file");
				return false;
			}

		} else {
			logger.info("No entry found for Email: " + email);
			String UnProcessedData = "Email: " + email + "," + "ContactNumber: " + oldMsisdn;
			fileUtility.writeToFileIfDataIsPresent(UnProcessedData,
					"identityActivationDetails.unprocessed.backup.file");
			return false;
		}
	}

	public void updateIdentityActivationDetailCollection(String email, String oldMsisdn, String newMsisdn) {
		Query query = new Query(Criteria.where("email").is(email));
		Update update = new Update();
		update.set("contactNumber",newMsisdn);
		UpdateResult updateResult = mongoTemplate.updateMulti(query, update, IdentityActivationDetails.class);
		logger.info("updated IdentityActivationDetail for Email: "+email+" OldContactNo: "+oldMsisdn+" NewContactNo: "+newMsisdn+" UpdateResult: "+updateResult);
	}
}
