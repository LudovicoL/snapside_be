package com.unisalento.snapside.iservices;

import com.unisalento.snapside.exceptions.AdNotFoundException;
import com.unisalento.snapside.generated.domain.AdEntity;
import com.unisalento.snapside.generated.domain.UserEntity;

import java.util.List;

public interface IAdService {
    public List<AdEntity> getAll() throws AdNotFoundException;
    public List<AdEntity> getAllPublished() throws AdNotFoundException;
    public AdEntity getById(int id) throws AdNotFoundException;
    public List<AdEntity> getAllByTitle(String title) throws AdNotFoundException;
    public AdEntity save(AdEntity ad);
    public void editAd(AdEntity ad);
    public List<AdEntity> getAllbySeller(int seller_id) throws AdNotFoundException;
    public void logicDeleteAd(int adAd);
    public void approve(int adAd, int approved);
    public AdEntity getLastBySeller(int idSeller) throws AdNotFoundException;
    public void deleteAd(int adAd);
}


