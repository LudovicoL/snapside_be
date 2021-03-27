package com.unisalento.snapside.repositories;

import com.unisalento.snapside.generated.domain.AdEntity;
import com.unisalento.snapside.generated.domain.AdHasAttributeEntity;
import com.unisalento.snapside.generated.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface AdHasAttributeRepository extends JpaRepository<AdHasAttributeEntity, Integer> {
    /*
    @Query("select a from AdEntity a where a.idAd=:idAd")
    public AdEntity getAdEntitiesById(@Param("idAd") int idAd);
    */



    @Query(value = "SELECT has.* FROM snapside.ad as a, snapside.ad_has_attribute as has WHERE a.id_ad=?1 AND has.ad_id_ad=a.id_ad " +
            "ORDER BY has.attribute_id_attribute ASC",
            nativeQuery = true)
    public List<AdHasAttributeEntity> getAllAttribValuesFromAd(@Param("idAd") int idAd );

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM snapside.ad_has_attribute WHERE snapside.ad_has_attribute.ad_id_ad = ?1",
            nativeQuery = true)
    public void resetAttributes(@Param("idAd") int idAd);

//    @Query("select u from UserEntity u, AdEntity a, BenefitEntity b where a.idAd=:idAd and a.idAd=:b.adByAdIdAd ")

}