package com.o2.co.uk.infra;

import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("propertyLoaderBean")
public class PropertyLoader {

	private String inputFilePath;
	private String email_prefix;
	private String email_suffix;
	private String msisdn_prefix;
	private String identityActivationDetailsbackupFilePath;
	private String persistedIdentitybackupFilePath;
	private String deletedIdentitybackupFilePath;
	private String identityMsisdnbackupFilePath;
	private String identityV3backupFilePath;
	

	public PropertyLoader() {
		super();
	}

	public String getIdentityV3backupFilePath() {
		return identityV3backupFilePath;
	}

	public void setIdentityV3backupFilePath(String backupFilePath) {
		this.identityV3backupFilePath = backupFilePath;
	}

	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}

	public void setEmail_prefix(String email_prefix) {
		this.email_prefix = email_prefix;
	}

	public void setEmail_suffix(String email_suffix) {
		this.email_suffix = email_suffix;
	}

	public void setMsisdn_prefix(String msisdn_prefix) {
		this.msisdn_prefix = msisdn_prefix;
	}

	public String getInputFilePath() {
		return inputFilePath;
	}

	public String getEmail_prefix() {
		return email_prefix;
	}

	public String getEmail_suffix() {
		return email_suffix;
	}

	public String getMsisdn_prefix() {
		return msisdn_prefix;
	}

	public String getIdentityActivationDetailsbackupFilePath() {
		return identityActivationDetailsbackupFilePath;
	}

	public void setIdentityActivationDetailsbackupFilePath(String identityActivationDetailsbackupFilePath) {
		this.identityActivationDetailsbackupFilePath = identityActivationDetailsbackupFilePath;
	}

	public String getDeletedIdentitybackupFilePath() {
		return deletedIdentitybackupFilePath;
	}

	public void setDeletedIdentitybackupFilePath(String deletedIdentitybackupFilePath) {
		this.deletedIdentitybackupFilePath = deletedIdentitybackupFilePath;
	}

	public String getIdentityMsisdnbackupFilePath() {
		return identityMsisdnbackupFilePath;
	}

	public void setIdentityMsisdnbackupFilePath(String identityMsisdnbackupFilePath) {
		this.identityMsisdnbackupFilePath = identityMsisdnbackupFilePath;
	}

	public String getPersistedIdentitybackupFilePath() {
		return persistedIdentitybackupFilePath;
	}

	public void setPersistedIdentitybackupFilePath(String persistedIdentitybackupFilePath) {
		this.persistedIdentitybackupFilePath = persistedIdentitybackupFilePath;
	}
		
}
