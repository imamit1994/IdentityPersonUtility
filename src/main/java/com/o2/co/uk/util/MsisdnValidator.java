package com.o2.co.uk.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.o2.co.uk.model.IdentityV3;
import com.o2.co.uk.service.IdentityV3Service;

@Component
public class MsisdnValidator {

	private static final Logger logger = Logger.getLogger(MsisdnValidator.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	// public String validateMsisdn(String msisdn) {
	// if((msisdn.length()==10)&&(msisdn.matches("\\d+"))) {
	// msisdn="+44"+msisdn;
	// return msisdn;
	// }
	// else
	// if((msisdn.length()==12)&&(msisdn.startsWith("44"))&&(msisdn.matches("\\d+")))
	// {
	// msisdn="+"+msisdn;
	// return msisdn;
	// }
	// else if((msisdn.length()==13) &&
	// (msisdn.startsWith("+44"))&&(msisdn.matches("\\+\\d+"))) {
	// return msisdn;
	// }
	// else
	// if((msisdn.length()==11)&&(msisdn.startsWith("0"))&&(msisdn.matches("\\d+")))
	// {
	// msisdn="+44"+msisdn.substring(1,11);
	// return msisdn;
	// }
	// else
	// return "invalid";
	// }

	public String validateNewMsisdn(String msisdn) {
		if ((msisdn.matches("\\+447\\d+")) && (msisdn.length() == 13)) {
			Query query = new Query(Criteria.where("msisdn").is(msisdn));
			List<IdentityV3> identityV3s = mongoTemplate.find(query, IdentityV3.class);
			if (identityV3s.size() > 0) {
				logger.info("NewMsisdn::" + msisdn + " already present in identityV3");
				return "invalid";
			} else
				return msisdn;
		} else {
			logger.info("NewMsisdn must start with +447 and NewMsisdn length must be 13");
			return "invalid";
		}
	}

	public String validateOldMsisdn(String msisdn) {
		if ((msisdn.length() >= 10) && (msisdn.length() <= 13)) {
			return msisdn;
		} else
			return "invalid";
	}
}
