package com.o2.co.uk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BackUpService {

    @Autowired
    private IdentityActivationDetailsService identityActivationDetailsService;

    @Autowired
    private IdentityV3Service identityV3Service;

    @Autowired
    private AssetService assetService;

    public void takeBackUpBeforeDeleteIdentityDb(String userName) {
    	identityV3Service.getBackUPFromUserName(userName);
    	identityActivationDetailsService.getBackUPFromEmail(userName);
    }

	public void takeBackUpBeforeDeletePersonDb(String userName) {
		assetService.getBackUpFromIdentifierValue(userName);
	}

}
