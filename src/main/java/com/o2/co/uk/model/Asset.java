package com.o2.co.uk.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Asset")
public class Asset {
	private String _id;
	private String _class;
	private String rawAssetValue;
	private String normalisedAssetValue;
	private String assetType;
	private String displayName;
	private String createdBy;
	private String createdOn;
	private AssetIdentifier identifier;
	private String uid;
	private String assetClaimedOn;

	public Asset(String _id, String _class, String rawAssetValue, String normalisedAssetValue, String assetType,
			String displayName, String createdBy, String createdOn, AssetIdentifier identifier, String uid,
			String assetClaimedOn) {
		this._id = _id;
		this._class = _class;
		this.rawAssetValue = rawAssetValue;
		this.normalisedAssetValue = normalisedAssetValue;
		this.assetType = assetType;
		this.displayName = displayName;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.identifier = identifier;
		this.uid = uid;
		this.assetClaimedOn = assetClaimedOn;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_class() {
		return _class;
	}

	public void set_class(String _class) {
		this._class = _class;
	}

	public String getRawAssetValue() {
		return rawAssetValue;
	}

	public void setRawAssetValue(String rawAssetValue) {
		this.rawAssetValue = rawAssetValue;
	}

	public String getNormalisedAssetValue() {
		return normalisedAssetValue;
	}

	public void setNormalisedAssetValue(String normalisedAssetValue) {
		this.normalisedAssetValue = normalisedAssetValue;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public AssetIdentifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(AssetIdentifier identifier) {
		this.identifier = identifier;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAssetClaimedOn() {
		return assetClaimedOn;
	}

	public void setAssetClaimedOn(String assetClaimedOn) {
		this.assetClaimedOn = assetClaimedOn;
	}

	@Override
	public String toString() {
		return "Asset [_id=" + _id + ", _class=" + _class + ", rawAssetValue=" + rawAssetValue
				+ ", normalisedAssetValue=" + normalisedAssetValue + ", assetType=" + assetType + ", displayName="
				+ displayName + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", identifier=" + identifier
				+ ", uid=" + uid + ", assetClaimedOn=" + assetClaimedOn + "]" + "\n";
	}

}
