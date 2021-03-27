package com.unisalento.snapside.services;

import com.unisalento.snapside.exceptions.AttributeNotFoundException;
import com.unisalento.snapside.generated.domain.AdHasAttributeEntity;
import com.unisalento.snapside.generated.domain.AttributeEntity;
import com.unisalento.snapside.iservices.IAttributeService;
import com.unisalento.snapside.repositories.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeService implements IAttributeService {

    @Autowired
    AttributeRepository attributeRepository;

    @Override
    public List<AttributeEntity> getAll() throws AttributeNotFoundException {
        // TODO Auto-generated method stub
        return attributeRepository.findAll();
    }

    @Override
    public AttributeEntity getById(int id) throws AttributeNotFoundException {
        // TODO Auto-generated method stub
        return attributeRepository.getOne(id);
    }
    @Override
    public AttributeEntity save(AttributeEntity attribute) {
        // TODO Auto-generated method stub
        return attributeRepository.save(attribute);
    }

    @Override
    public List<AttributeEntity> getAllFromAd(int idAd) throws AttributeNotFoundException {
        // TODO Auto-generated method stub
        return attributeRepository.getAllFromAd(idAd);
    }

    @Override
    public AttributeEntity saveAndFlush(AttributeEntity attribute) {
        // TODO Auto-generated method stub
        return attributeRepository.saveAndFlush(attribute);
    }
}
