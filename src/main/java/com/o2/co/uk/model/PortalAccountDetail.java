package com.o2.co.uk.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PortalAccountDetail {

    private String portalId;
    private String portalAccountType;
    private String bfId;

    public String getPortalId() {
        return portalId;
    }

    public void setPortalId(String portalId) {
        this.portalId = portalId;
    }

    public String getPortalAccountType() {
        return portalAccountType;
    }

    public void setPortalAccountType(String portalAccountType) {
        this.portalAccountType = portalAccountType;
    }

    public String getBfId() {
        return bfId;
    }

    public void setBfId(String bfId) {
        this.bfId = bfId;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return "PortalAccountDetail{" +
                "portalId='" + portalId + '\'' +
                ", portalAccountType='" + portalAccountType + '\'' +
                ", bfId='" + bfId + '\'' +
                '}';
    }
}
