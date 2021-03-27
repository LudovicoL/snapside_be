package com.unisalento.snapside.models;

import com.unisalento.snapside.generated.domain.AdEntity;
import com.unisalento.snapside.generated.domain.AttributeEntity;

public class AdHasAttributeDTO {
    private int idHasAttribute;
    private String attributeValue;
    private Integer adByAdIdAd;
    private Integer attributeByAttributeIdAttribute;

    public int getIdHasAttribute() {
        return idHasAttribute;
    }

    public void setIdHasAttribute(int idHasAttribute) {
        this.idHasAttribute = idHasAttribute;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public Integer getAdByAdIdAd() {
        return adByAdIdAd;
    }

    public void setAdByAdIdAd(Integer adByAdIdAd) {
        this.adByAdIdAd = adByAdIdAd;
    }

    public Integer getAttributeByAttributeIdAttribute() {
        return attributeByAttributeIdAttribute;
    }

    public void setAttributeByAttributeIdAttribute(Integer attributeByAttributeIdAttribute) {
        this.attributeByAttributeIdAttribute = attributeByAttributeIdAttribute;
    }
}
