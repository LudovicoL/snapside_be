package com.unisalento.snapside.iservices;

import com.unisalento.snapside.exceptions.AdHasAttributeNotFoundException;
import com.unisalento.snapside.exceptions.AttributeNotFoundException;
import com.unisalento.snapside.generated.domain.AdHasAttributeEntity;
import com.unisalento.snapside.generated.domain.AdHasAttributeEntity;
import com.unisalento.snapside.generated.domain.UserEntity;

import java.util.List;

public interface IAdHasAttributeService {
    public List<AdHasAttributeEntity> getAll() throws AdHasAttributeNotFoundException;
    public AdHasAttributeEntity getById(int id) throws AdHasAttributeNotFoundException;
    public AdHasAttributeEntity save(AdHasAttributeEntity ad);
    public List<AdHasAttributeEntity> getAllAttribValuesFromAd(int idAd) throws AdHasAttributeNotFoundException;
    public void resetAttributes(int idAd);
}
