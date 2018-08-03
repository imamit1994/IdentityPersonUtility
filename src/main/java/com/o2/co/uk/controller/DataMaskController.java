package com.o2.co.uk.controller;


import com.o2.co.uk.util.FileUtility;
import com.o2.co.uk.util.MsisdnValidator;
import com.o2.co.uk.infra.PropertiesManager;
import com.o2.co.uk.service.ProcessUpdateRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class DataMaskController {

    @Autowired
    private PropertiesManager loader;

    @Autowired
    private FileUtility fileUtility;
    
    @Autowired
    private MsisdnValidator msisdnValidator;
    
    @Autowired
    private ProcessUpdateRequestService processUpdateRequestService;

    private static final Logger logger = LoggerFactory.getLogger(DataMaskController.class);

    public void execute() {
        try {
            File inputECMfile = fileUtility.getInputECMFile();
            BufferedReader reader = new BufferedReader(new FileReader(inputECMfile));
            reader.lines().filter(inputECMLine -> inputECMLine.length() > 1).forEach(inputECMLine -> {
                String[] inputUidAndMsisdns = inputECMLine.split(",");
                updateAll(inputUidAndMsisdns[0], inputUidAndMsisdns[1],inputUidAndMsisdns[2]);
            });
        } catch (FileNotFoundException ex) {
            logger.error("File Not found at given location. " + ex.getMessage());
        }
    }

    public void updateAll(String email,String oldMsisdn,String newMsisdn) {
    	logger.info("processing: Email: " + email + " OldMsisdn: " + oldMsisdn + " NewMsisdn: " +newMsisdn);
    	String validatedNewMsisdn=msisdnValidator.validateNewMsisdn(newMsisdn);
    	String validateOldMsisdn=msisdnValidator.validateOldMsisdn(oldMsisdn);
    	if(!validatedNewMsisdn.equals("invalid")) {
    		processUpdateRequestService.processUpdateRequestForIdentityV3(email,oldMsisdn,validatedNewMsisdn);
        	//processUpdateRequestService.processUpdateForIdentitityActivationDetails(email, oldMsisdn, validatedMsisdn);
    	}
    	else {
    		logger.info("Provided NewMsisdn: "+newMsisdn+" is Invalid");
    		String UnProcessDataidentityV3="Username: "+email+","+"OldMsisdn: "+oldMsisdn;
    		fileUtility.writeToFileIfDataIsPresent(UnProcessDataidentityV3, "identityV3.unprocessed.backup.file");
    		String UnProcessDataidentityActivationdetail="Email: " + email + "," + "ContactNumber: " + oldMsisdn;
    		fileUtility.writeToFileIfDataIsPresent(UnProcessDataidentityActivationdetail, "identityActivationDetails.unprocessed.backup.file");
    	}
    }
    
}
