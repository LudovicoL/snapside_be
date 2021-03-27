package com.unisalento.snapside.generated.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ad_has_attribute", schema = "snapside", catalog = "")
public class AdHasAttributeEntity {
    private int idHasAttribute;
    private String attributeValue;
    private AdEntity adByAdIdAd;
    private AttributeEntity attributeByAttributeIdAttribute;

    @Id
    @Column(name = "id_has_attribute", nullable = false)
    public int getIdHasAttribute() {
        return idHasAttribute;
    }

    public void setIdHasAttribute(int idHasAttribute) {
        this.idHasAttribute = idHasAttribute;
    }

    @Basic
    @Column(name = "attribute_value", nullable = true, length = 5000)
    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdHasAttributeEntity that = (AdHasAttributeEntity) o;
        return idHasAttribute == that.idHasAttribute &&
                Objects.equals(attributeValue, that.attributeValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHasAttribute, attributeValue);
    }

    @ManyToOne
    @JoinColumn(name = "ad_id_ad", referencedColumnName = "id_ad", nullable = false)
    public AdEntity getAdByAdIdAd() {
        return adByAdIdAd;
    }

    public void setAdByAdIdAd(AdEntity adByAdIdAd) {
        this.adByAdIdAd = adByAdIdAd;
    }

    @ManyToOne
    @JoinColumn(name = "attribute_id_attribute", referencedColumnName = "id_attribute", nullable = false)
    public AttributeEntity getAttributeByAttributeIdAttribute() {
        return attributeByAttributeIdAttribute;
    }

    public void setAttributeByAttributeIdAttribute(AttributeEntity attributeByAttributeIdAttribute) {
        this.attributeByAttributeIdAttribute = attributeByAttributeIdAttribute;
    }
}
