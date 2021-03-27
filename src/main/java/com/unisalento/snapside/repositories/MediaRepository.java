package com.unisalento.snapside.repositories;

import com.unisalento.snapside.generated.domain.MediaEntity;
import com.unisalento.snapside.generated.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;


public interface MediaRepository extends JpaRepository<MediaEntity, Integer> {
    /*
    @Query("select a from MediaEntity a where a.idAd=:idAd")
    public MediaEntity getAdEntitiesById(@Param("idAd") int idAd);
    */



    @Query(value = "SELECT * FROM snapside.media as m, snapside.ad as a WHERE a.id_ad=?1 AND m.ad_id_ad=a.id_ad   ",
            nativeQuery = true)
    public List<MediaEntity> getAllByAd(@Param("idAd") int idAd);

//    @Query("select u from UserEntity u, MediaEntity a, BenefitEntity b where a.idAd=:idAd and a.idAd=:b.adByAdIdAd ")


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM snapside.media WHERE snapside.media.ad_id_ad = ?1",
            nativeQuery = true)
    public void resetMedia(@Param("idAd") int idAd);
}

