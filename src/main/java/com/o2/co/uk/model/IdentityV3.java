package com.o2.co.uk.model;

import com.o2.co.uk.util.CustomStringUtil;
import com.o2.co.uk.util.StringFormatterInterface;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
public class IdentityV3 implements StringFormatterInterface {

	private String _id;
	private String _class;
	private String uid;
	private String username;
	private String password;
	private String securityQuestion;
	private String securityAnswer;
	private Boolean usernameVerified;
	private Boolean passwordResetByAgent;
	private String displayName;
	private String loginPortalBfid;
	private String loginPortalUsername;
	private String createdBy;
	private String modifiedBy;
	private String addedBy;
	private Date createdTimestampUtc;
	private Date passwordModifiedTimestampUtc;
	private Date lastAccessedTimestampUtc;
	private Date lastModifiedTimestampUtc;
	private Date lastLoginTimestampUtc;
	private String lastName;
	private String dateOfBirth;
	private String msisdn;
	private Boolean msisdnVerified;
	private String alternateEmail;
	private List<PortalAccountDetail> portalAccountDetails;
	private String portalId;

	private String forename;
	private String title;

//	added constructor
	public IdentityV3(String _id, String _class, String uid, String username, String password, String securityQuestion, String securityAnswer,
					  Boolean usernameVerified, Boolean passwordResetByAgent, String displayName, String loginPortalBfid, String loginPortalUsername,
					  String createdBy, String modifiedBy, String addedBy, Date createdTimestampUtc, Date passwordModifiedTimestampUtc, Date lastAccessedTimestampUtc,
					  Date lastModifiedTimestampUtc, Date lastLoginTimestampUtc, String lastName, String dateOfBirth, String msisdn, Boolean msisdnVerified, String alternateEmail,
					  List<PortalAccountDetail> portalAccountDetails, String portalId, String forename, String title) {
		this._id = _id;
		this._class = _class;
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.usernameVerified = usernameVerified;
		this.passwordResetByAgent = passwordResetByAgent;
		this.displayName = displayName;
		this.loginPortalBfid = loginPortalBfid;
		this.loginPortalUsername = loginPortalUsername;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.addedBy = addedBy;
		this.createdTimestampUtc = createdTimestampUtc;
		this.passwordModifiedTimestampUtc = passwordModifiedTimestampUtc;
		this.lastAccessedTimestampUtc = lastAccessedTimestampUtc;
		this.lastModifiedTimestampUtc = lastModifiedTimestampUtc;
		this.lastLoginTimestampUtc = lastLoginTimestampUtc;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.msisdn = msisdn;
		this.msisdnVerified = msisdnVerified;
		this.alternateEmail = alternateEmail;
		this.portalAccountDetails = portalAccountDetails;
		this.portalId = portalId;
		this.forename = forename;
		this.title = title;
	}

	public String getPortalId() {
		return portalId;
	}

	public void setPortalId(String portalId) {
		this.portalId = portalId;
	}

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		this._id = _id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<PortalAccountDetail> getPortalAccountDetails() {
		return portalAccountDetails;
	}

	public void setPortalAccountDetails(List<PortalAccountDetail> portalAccountDetails) {
		this.portalAccountDetails = portalAccountDetails;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getPasswordResetByAgent() {
		return passwordResetByAgent;
	}

	public void setPasswordResetByAgent(Boolean passwordResetByAgent) {
		this.passwordResetByAgent = passwordResetByAgent;
	}

	public String getLoginPortalUsername() {
		return loginPortalUsername;
	}

	public void setLoginPortalUsername(String loginPortalUsername) {
		this.loginPortalUsername = loginPortalUsername;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Date getLastLoginTimestampUtc() {
		return lastLoginTimestampUtc;
	}

	public void setLastLoginTimestampUtc(Date lastLoginTimestampUtc) {
		this.lastLoginTimestampUtc = lastLoginTimestampUtc;
	}

	public Date getPasswordModifiedTimestampUtc() {
		return passwordModifiedTimestampUtc;
	}

	public void setPasswordModifiedTimestampUtc(Date passwordModifiedTimestampUtc) {
		this.passwordModifiedTimestampUtc = passwordModifiedTimestampUtc;
	}

	public String getLoginPortalBfid() {
		return loginPortalBfid;
	}

	public void setLoginPortalBfid(String loginPortalBfid) {
		this.loginPortalBfid = loginPortalBfid;
	}

	public Date getLastAccessedTimestampUtc() {
		return lastAccessedTimestampUtc;
	}

	public void setLastAccessedTimestampUtc(Date lastAccessedTimestampUtc) {
		this.lastAccessedTimestampUtc = lastAccessedTimestampUtc;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getAlternateEmail() {
		return alternateEmail;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public Date getLastModifiedTimestampUtc() {
		return lastModifiedTimestampUtc;
	}

	public void setLastModifiedTimestampUtc(Date lastModifiedTimestampUtc) {
		this.lastModifiedTimestampUtc = lastModifiedTimestampUtc;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public Date getCreatedTimestampUtc() {
		return createdTimestampUtc;
	}

	public void setCreatedTimestampUtc(Date createdTimestampUtc) {
		this.createdTimestampUtc = createdTimestampUtc;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Boolean getUsernameVerified() {
		return usernameVerified;
	}

	public void setUsernameVerified(Boolean usernameVerified) {
		this.usernameVerified = usernameVerified;
	}

	public Boolean getMsisdnVerified() {
		return msisdnVerified;
	}

	public void setMsisdnVerified(Boolean msisdnVerified) {
		this.msisdnVerified = msisdnVerified;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String get_class() {
		return _class;
	}

	public void set_class(String _class) {
		this._class = _class;
	}

	@Override
	public String getFieldsInStringFormat() {
		return "Username:"+CustomStringUtil.getStringIfPresentElseEmpty(username)+","+"MSISDN:"+CustomStringUtil.getStringIfPresentElseEmpty(msisdn);
	}
}
