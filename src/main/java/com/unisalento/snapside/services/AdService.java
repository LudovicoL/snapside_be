package com.unisalento.snapside.services;

import com.unisalento.snapside.exceptions.AdNotFoundException;
import com.unisalento.snapside.generated.domain.AdEntity;
import com.unisalento.snapside.generated.domain.UserEntity;
import com.unisalento.snapside.repositories.AdRepository;
import com.unisalento.snapside.iservices.IAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AdService implements IAdService {

    @Autowired
    AdRepository adRepository;

    @Override
    public List<AdEntity> getAll() throws AdNotFoundException {
        // TODO Auto-generated method stub
        return adRepository.findAll();
    }

    @Override
    public List<AdEntity> getAllbySeller(int seller_id) throws AdNotFoundException {
        // TODO Auto-generated method stub
        return adRepository.getAllbySeller(seller_id);
    }

    @Override
    public AdEntity getById(int id) throws AdNotFoundException {
        // TODO Auto-generated method stub
        return adRepository.getOne(id);
    }

    @Override
    public AdEntity save(AdEntity ad) {
        // TODO Auto-generated method stub
        return adRepository.save(ad);
    }

    @Override
    public List<AdEntity> getAllByTitle(String title) {
        // TODO Auto-generated method stub
        return adRepository.getAllByTitle(title);
    }

    @Override
    public void logicDeleteAd(int idAd) {
        // TODO Auto-generated method stub
        adRepository.logicDeleteAd(idAd);
    }

    @Override
    public void deleteAd(int idAd) {
        // TODO Auto-generated method stub
        adRepository.deleteAd(idAd);
    }

    @Override
    public AdEntity getLastBySeller(int idSeller) {
        // TODO Auto-generated method stub
        return adRepository.getLastBySeller(idSeller);
    }

    @Override
    public List<AdEntity> getAllPublished() {
        // TODO Auto-generated method stub
        return adRepository.getAllPublished();
    }

    @Override
    public void approve(int idAd, int approve) {
        // TODO Auto-generated method stub
        adRepository.approve(idAd,approve);
    }

    @Override
    public void editAd(AdEntity ad) {
        // TODO Auto-generated method stub
        int idAd=ad.getIdAd();
        String title= ad.getTitle();
        byte[] files= ad.getFiles();
        String address=ad.getAddress();
        int approved= ad.getApproved();
        String coordinates=ad.getCoordinates();
        String description = ad.getDescription();
        Timestamp endDate = ad.getEndDate();
        Timestamp lastEdit = ad.getLastEdit();
        Double sellPrice = ad.getSellPrice();

        adRepository.editAd(idAd, title, description, address, coordinates,files, endDate, lastEdit, approved, sellPrice);
    }

}
