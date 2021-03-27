package com.unisalento.snapside.adapters;

import com.unisalento.snapside.generated.domain.AdEntity;
import com.unisalento.snapside.generated.domain.BenefitEntity;
import com.unisalento.snapside.generated.domain.UserEntity;
import com.unisalento.snapside.models.BenefitDTO;

public class BenefitAdapter {
    public static BenefitEntity BenefitDTOToBenefitEntity(BenefitDTO benefitDTO, UserEntity customerUser, AdEntity ad) {
        BenefitEntity benefitEntity = new BenefitEntity();
        benefitEntity.setCheckinDate(benefitDTO.getCheckinDate());
        benefitEntity.setCheckoutDate(benefitDTO.getCheckoutDate());
        benefitEntity.setInterested(benefitDTO.getInterested());
        benefitEntity.setPaymentType(benefitDTO.getPaymentType());
        benefitEntity.setPaidAmount(benefitDTO.getPaidAmount());
        benefitEntity.setPaid(benefitDTO.getPaid());
        benefitEntity.setAdByAdIdAd(ad);
        benefitEntity.setUserByUserIdUser(customerUser);
        return benefitEntity;
    }

    public static BenefitDTO BenefitEntityToBenefitDTO(BenefitEntity benefitEntity) {
        BenefitDTO benefitDTO = new BenefitDTO();
        benefitDTO.setIdBenefit(benefitEntity.getIdBenefit());
        benefitDTO.setCheckinDate(benefitEntity.getCheckinDate());
        benefitDTO.setCheckoutDate(benefitEntity.getCheckoutDate());
        benefitDTO.setInterested(benefitEntity.getInterested());
        benefitDTO.setPaymentType(benefitEntity.getPaymentType());
        benefitDTO.setPaidAmount(benefitEntity.getPaidAmount());
        benefitDTO.setPaid(benefitEntity.getPaid());
        benefitDTO.setUser_id_user(benefitEntity.getUserByUserIdUser().getIdUser());
        benefitDTO.setAd_id_ad(benefitEntity.getAdByAdIdAd().getIdAd());
//        benefitDTO.setInterested_seller_side(benefitEntity.getInterestedSellerSide());
        return benefitDTO;
    }
}
