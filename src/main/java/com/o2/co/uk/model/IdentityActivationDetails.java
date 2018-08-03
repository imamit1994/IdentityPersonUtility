package com.o2.co.uk.model;

import com.o2.co.uk.util.CustomStringUtil;
import com.o2.co.uk.util.StringFormatterInterface;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class IdentityActivationDetails implements StringFormatterInterface {

	private String _id;
	private String _class;
	private String migrated;
	private String securityQuestion;
	private String uid;
	private String contactNumber;
	private String email;
	private String securityAnswer;
	private String  clientId;

	public IdentityActivationDetails(String _id, String _class, String migrated, String securityQuestion, String uid, String contactNumber, String email, String securityAnswer, String clientId) {
		this._id = _id;
		this._class = _class;
		this.migrated = migrated;
		this.securityQuestion = securityQuestion;
		this.uid = uid;
		this.contactNumber = contactNumber;
		this.email = email;
		this.securityAnswer = securityAnswer;
		this.clientId = clientId;
	}

	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String get_id() {return _id;}
	public void set_id(String _id) {this._id = _id;	}

	public String get_class() {	return _class;}
	public void set_class(String _class) {this._class = _class;}

	public String getMigrated() {return migrated;}
	public void setMigrated(String migrated) {this.migrated = migrated;}

	public String getSecurityQuestion() {return securityQuestion;}

	public void setSecurityQuestion(String securityQuestion) {this.securityQuestion = securityQuestion;}

	public String getClientId() {return clientId;}

	public void setClientId(String clientId) {this.clientId = clientId;}

	@Override
	public String getFieldsInStringFormat() {
		return "Email:"+CustomStringUtil.getStringIfPresentElseEmpty(email)+","+"ContactNumber:"+CustomStringUtil.getStringIfPresentElseEmpty(contactNumber);
	}
}
