package com.unisalento.snapside.services;

import com.unisalento.snapside.exceptions.AdHasAttributeNotFoundException;
import com.unisalento.snapside.exceptions.AttributeNotFoundException;
import com.unisalento.snapside.generated.domain.AdHasAttributeEntity;
import com.unisalento.snapside.generated.domain.AdHasAttributeEntity;
import com.unisalento.snapside.iservices.IAdHasAttributeService;
import com.unisalento.snapside.iservices.IAttributeService;
import com.unisalento.snapside.repositories.AdHasAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdHasAttributeService implements IAdHasAttributeService {

    @Autowired
    AdHasAttributeRepository hasAttributeRepository;

    @Override
    public List<AdHasAttributeEntity> getAll() throws AdHasAttributeNotFoundException {
        // TODO Auto-generated method stub
        return hasAttributeRepository.findAll();
    }

    @Override
    public AdHasAttributeEntity getById(int id) throws AdHasAttributeNotFoundException {
        // TODO Auto-generated method stub
        return hasAttributeRepository.getOne(id);
    }
    @Override
    public AdHasAttributeEntity save(AdHasAttributeEntity attribute) {
        // TODO Auto-generated method stub
        return hasAttributeRepository.save(attribute);
    }

    @Override
    public void resetAttributes(int idAd) {
        // TODO Auto-generated method stub
        hasAttributeRepository.resetAttributes(idAd);
    }

    @Override
    public List<AdHasAttributeEntity> getAllAttribValuesFromAd(int idAd) throws AdHasAttributeNotFoundException {
        // TODO Auto-generated method stub
        return hasAttributeRepository.getAllAttribValuesFromAd(idAd);
    }
}
