package com.unisalento.snapside.adapters;

import com.unisalento.snapside.generated.domain.AdEntity;
import com.unisalento.snapside.generated.domain.ItemEntity;
import com.unisalento.snapside.generated.domain.UserEntity;
import com.unisalento.snapside.services.UserService;
import com.unisalento.snapside.models.AdDTO;

public class AdAdapter {
    public static AdEntity AdDTOToAdEntity(AdDTO adDTO, UserEntity user, ItemEntity item) {
        AdEntity ad = new AdEntity();
        ad.setIdAd(adDTO.getIdAd());
        ad.setTitle(adDTO.getTitle());
        ad.setDescription(adDTO.getDescription());
        ad.setSellPrice(adDTO.getSellPrice());
        ad.setAddress(adDTO.getAddress());
        ad.setCoordinates(adDTO.getCoordinates());
        ad.setApproved(adDTO.getApproved());
        ad.setActive(adDTO.getActive());
        ad.setBeginDate(adDTO.getBeginDate());
        ad.setEndDate(adDTO.getEndDate());
        ad.setAdType(adDTO.getAdType());
        ad.setUserByUserIdSeller(user);
        ad.setItemByItemIdItem(item);
        ad.setFiles(adDTO.getFiles());
        ad.setCreationDate(adDTO.getCreationDate());
        return ad;
    }
    public static AdDTO AdEntityToAdDTO(AdEntity ad) {
        UserService userService;
        AdDTO adDTO = new AdDTO();
        adDTO.setIdAd(ad.getIdAd());
        adDTO.setTitle(ad.getTitle());
        adDTO.setDescription(ad.getDescription());
        adDTO.setSellPrice(ad.getSellPrice());
        adDTO.setAddress(ad.getAddress());
        adDTO.setCoordinates(ad.getCoordinates());
        adDTO.setApproved(ad.getApproved());
        adDTO.setActive(ad.getActive());
        adDTO.setBeginDate(ad.getBeginDate());
        adDTO.setEndDate(ad.getEndDate());
        adDTO.setAdType(ad.getAdType());
        adDTO.setUser_id_seller(ad.getUserByUserIdSeller().getIdUser());
        adDTO.setFiles(ad.getFiles());
        adDTO.setDeleted(ad.getDeleted());
        adDTO.setLastEdit(ad.getLastEdit());
        adDTO.setCreationDate(ad.getCreationDate());
        adDTO.setItem_id_item(ad.getItemByItemIdItem().getIdItem());
        return adDTO;
    }

}
