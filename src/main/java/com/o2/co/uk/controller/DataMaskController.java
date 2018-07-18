package com.o2.co.uk.controller;


import com.o2.co.uk.util.FileUtility;
import com.o2.co.uk.infra.PropertiesManager;
import com.o2.co.uk.service.BackUpService;
import com.o2.co.uk.service.UpdateService;
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
    private UpdateService updateService;

    @Autowired
    private BackUpService backUpService;

    @Autowired
    private FileUtility fileUtility;

    private static final Logger logger = LoggerFactory.getLogger(DataMaskController.class);

    public void executeIdentityDb() {
        try {
            File inputECMfile = fileUtility.getInputECMFile();
            BufferedReader reader = new BufferedReader(new FileReader(inputECMfile));            
            reader.lines().filter(inputECMLine -> inputECMLine.length() > 1).forEach(inputECMLine -> {
                String inputUserId = inputECMLine;
                updateIdentity(inputUserId);
            });
            
        } catch (FileNotFoundException ex) {
            logger.error("File Not found at given location. " + ex.getMessage());
        }
    }
    
    public void executePersonDb() {
        try {
            File inputECMfile = fileUtility.getInputECMFile();
            BufferedReader reader = new BufferedReader(new FileReader(inputECMfile));            
            reader.lines().filter(inputECMLine -> inputECMLine.length() > 1).forEach(inputECMLine -> {
                String inputUserId = inputECMLine;
                updatePerson(inputUserId);
            });
            
        } catch (FileNotFoundException ex) {
            logger.error("File Not found at given location. " + ex.getMessage());
        }
    }
    
    private void updatePerson(String inputUserName) {
    	logger.info("Processing: UserName: "+ inputUserName);
    	backUpService.takeBackUpBeforeDeletePersonDb(inputUserName);
    	updateService.deleteByIdentifierValue(inputUserName);
	}

	private void updateIdentity(String inputUserName) {
    	logger.info("Processing: UserName: "+ inputUserName);
    	backUpService.takeBackUpBeforeDeleteIdentityDb(inputUserName);
    	updateService.deleteByUserName(inputUserName);
	}

}
