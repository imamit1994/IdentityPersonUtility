package com.o2.co.uk.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateService {

	private static final Logger logger = Logger.getLogger(UpdateService.class);
	
    @Autowired
    private IdentityV3Service identityV3Service;

    @Autowired
    private IdentityActivationDetailsService identityActivationDetailsService;
    
    @Autowired 
    private AssetService assetService;

	public void deleteByUserName(String inputUserName) {
		boolean deletedFromIdentityActivation=identityActivationDetailsService.deleteFromActivationdetails(inputUserName);
		if(deletedFromIdentityActivation) {
			identityV3Service.deleteFromIdentityV3(inputUserName);
		}
		else {
			logger.info("Skipping for identityV3 collection");
		}
	}

	public void deleteByIdentifierValue(String identifierValue,String assetType) {
		assetService.deleteFromAsset(identifierValue,assetType);
	}

}
