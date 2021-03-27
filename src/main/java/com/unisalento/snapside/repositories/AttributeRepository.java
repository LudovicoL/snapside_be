package com.unisalento.snapside.repositories;

import com.unisalento.snapside.generated.domain.AdEntity;
import com.unisalento.snapside.generated.domain.AttributeEntity;
import com.unisalento.snapside.generated.domain.AdHasAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttributeRepository extends JpaRepository<AttributeEntity, Integer> {

    @Query(value = "SELECT * FROM snapside.ad as a, snapside.ad_has_attribute as has, snapside.attribute as att " +
            "WHERE a.id_ad=?1 AND has.ad_id_ad=a.id_ad AND has.attribute_id_attribute=att.id_attribute " +
            "ORDER BY has.attribute_id_attribute ASC",
            nativeQuery = true)
    public List<AttributeEntity> getAllFromAd(@Param("idAd") int idAd );
}
