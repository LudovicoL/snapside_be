package com.unisalento.snapside.repositories;

import com.unisalento.snapside.adapters.UserAdapter;
import com.unisalento.snapside.generated.domain.AdEntity;
import com.unisalento.snapside.adapters.UserAdapter;
import com.unisalento.snapside.generated.domain.UserEntity;
import com.unisalento.snapside.generated.domain.BenefitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;


public interface AdRepository extends JpaRepository<AdEntity, Integer> {
    /*
    @Query("select a from AdEntity a where a.idAd=:idAd")
    public AdEntity getAdEntitiesById(@Param("idAd") int idAd);
    */

    @Query(value="SELECT * FROM snapside.ad as a WHERE a.user_id_seller=?1 AND a.deleted IS NULL",
            nativeQuery = true)
    public List<AdEntity> getAllbySeller(@Param("seller_id") int seller_id );

    @Query(value="SELECT * FROM snapside.ad as a WHERE a.approved=1 AND a.end_date > NOW() AND a.deleted IS NULL",
            nativeQuery = true)
    public List<AdEntity> getAllPublished();

    @Query(value = "SELECT * FROM snapside.ad as a WHERE a.title LIKE %:title% ",
            nativeQuery = true)
    public List<AdEntity> getAllByTitle(@Param("title") String title );

    @Query(value = "SELECT * FROM snapside.ad as a WHERE a.user_id_seller=?1 ORDER BY a.id_ad DESC LIMIT 1 ",
            nativeQuery = true)
    public AdEntity getLastBySeller(@Param("idSeller") int idSeller );

    @Modifying
    @Transactional
    @Query(value = "UPDATE ad SET ad.deleted = 'DELETED' WHERE ad.id_ad = ?1",
            nativeQuery = true)
    public void logicDeleteAd(@Param("idAd") int idAd );

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM snapside.ad WHERE ad.id_ad = ?1",
            nativeQuery = true)
    public void deleteAd(@Param("id") int id );


    @Modifying
    @Transactional
    @Query(value = "UPDATE ad SET ad.title = ?2, ad.description=?3, ad.address=?4, ad.coordinates=?5, ad.files=?6, ad.end_date=?7, ad.last_edit=?8, ad.approved=?9, ad.sell_price=?10 WHERE ad.id_ad = ?1",
            nativeQuery = true)
    public void editAd(@Param("idAd") int idAd, @Param("title") String title, @Param("description") String description, @Param("address") String address,
                       @Param("coordinates") String coordinates, @Param("files") byte[] files,
                       @Param("endDate") Timestamp endDate, @Param("lastEdit") Timestamp lastEdit, @Param("approved") int approved, @Param("sellPrice") Double sellPrice);



    @Modifying
    @Transactional
    @Query(value = "UPDATE snapside.ad SET snapside.ad.approved = ?2  WHERE snapside.ad.id_ad = ?1",
            nativeQuery = true)
    public void approve(@Param("idAd") int idAd, @Param("approve") int approve );

//    @Query("select u from UserEntity u, AdEntity a, BenefitEntity b where a.idAd=:idAd and a.idAd=:b.adByAdIdAd ")

}

