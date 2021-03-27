package com.unisalento.snapside.adapters;

import com.unisalento.snapside.generated.domain.AdEntity;
import com.unisalento.snapside.generated.domain.AdHasAttributeEntity;
import com.unisalento.snapside.generated.domain.AdHasAttributeEntity;
import com.unisalento.snapside.generated.domain.AttributeEntity;
import com.unisalento.snapside.models.AdHasAttributeDTO;
import com.unisalento.snapside.models.AdHasAttributeDTO;

public class AdHasAttributeAdapter {
    public static AdHasAttributeEntity AdHasAttributeDTOToAdHasAttributeEntity(AdHasAttributeDTO adhasDTO, AdEntity ad, AttributeEntity attribute) {
        AdHasAttributeEntity adhas = new AdHasAttributeEntity();
        adhas.setIdHasAttribute(adhasDTO.getIdHasAttribute());
        adhas.setAdByAdIdAd(ad);
        adhas.setAttributeByAttributeIdAttribute(attribute);
        adhas.setAttributeValue(adhasDTO.getAttributeValue());
        return adhas;
    }
    public static AdHasAttributeDTO AdHasAttributeEntityToAdHasAttributeDTO(AdHasAttributeEntity adhas) {

        AdHasAttributeDTO adhasDTO = new AdHasAttributeDTO();
        adhasDTO.setIdHasAttribute(adhas.getIdHasAttribute());
        adhasDTO.setAdByAdIdAd(adhas.getAdByAdIdAd().getIdAd());
//        adhasDTO.setAttributeByAttributeIdAttribute(adhas.getAttributeByAttributeIdAttribute());
        adhasDTO.setAttributeValue(adhas.getAttributeValue());
        
        return adhasDTO;
    }

}
