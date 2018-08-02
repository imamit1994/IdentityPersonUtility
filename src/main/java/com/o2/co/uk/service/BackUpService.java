package com.o2.co.uk.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BackUpService {

	private static final Logger logger = Logger.getLogger(BackUpService.class);
	
    @Autowired
    private IdentityActivationDetailsService identityActivationDetailsService;

    @Autowired
    private IdentityV3Service identityV3Service;

    @Autowired
    private AssetService assetService;

    public void takeBackUpBeforeDeleteIdentityDb(String userName) {
    	int presentInActivationDetailflag=identityActivationDetailsService.getBackUPFromEmail(userName);
    	if(presentInActivationDetailflag==0) {
    	identityV3Service.getBackUPFromUserName(userName);
    	}
    }

	public void takeBackUpBeforeDeletePersonDb(String userName,String assetType) {
		assetService.getBackUpFromIdentifierValue(userName,assetType);
	}

}
