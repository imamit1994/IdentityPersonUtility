package com.o2.co.uk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateService {

    @Autowired
    private IdentityV3Service identityV3Service;

    @Autowired
    private IdentityActivationDetailsService identityActivationDetailsService;
    
    @Autowired 
    private AssetService assetService;

	public void deleteByUserName(String inputUserName) {
		identityV3Service.deleteFromIdentityV3(inputUserName);
		identityActivationDetailsService.deleteFromActivationdetails(inputUserName);
	}

	public void deleteByIdentifierValue(String identifierValue) {
		assetService.deleteFromAsset(identifierValue);
	}

}
