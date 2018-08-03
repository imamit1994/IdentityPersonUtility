package com.o2.co.uk.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessUpdateRequestService {
	
	private static final Logger logger = Logger.getLogger(ProcessUpdateRequestService.class);
	
	@Autowired
	private IdentityV3Service identityV3Service;
	
	@Autowired
    private IdentityActivationDetailsService identityActivationDetailsService;
	
	public void processUpdateRequestForIdentityV3(String userName,String oldMsisdn,String newMsisdn) {
		logger.info("processing for identityV3 Collection");
		boolean backupCreated=identityV3Service.getBackupUsingUid(userName,oldMsisdn);
		if(backupCreated) {
			//identityV3Service.updateIdentityV3Collection(userName, oldMsisdn, newMsisdn);
		}
	}
	
	public void processUpdateForIdentitityActivationDetails(String email,String oldMsisdn,String newMsisdn) {
		logger.info("processing for identityActivationDetail Collection");
		boolean backupCreated=identityActivationDetailsService.getBackupUsingUid(email,oldMsisdn);
		if(backupCreated) {
			identityActivationDetailsService.updateIdentityActivationDetailCollection(email, oldMsisdn, newMsisdn);
		}
	}
}
