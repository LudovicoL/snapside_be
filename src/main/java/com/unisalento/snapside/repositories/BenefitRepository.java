package com.unisalento.snapside.repositories;

import com.unisalento.snapside.generated.domain.BenefitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BenefitRepository extends JpaRepository<BenefitEntity, Integer> {

    @Query(value = "SELECT b.* FROM snapside.user as u, snapside.ad as adv, snapside.benefit as b WHERE adv.id_ad=?1 AND adv.id_ad=b.ad_id_ad AND b.user_id_user=u.id_user",
            nativeQuery = true)
    public List<BenefitEntity> getAllBenefitsAtAd(@Param("idAd")  int idAd );

    @Query(value = "SELECT b.* FROM snapside.benefit as b WHERE b.user_id_user=?1 AND TIMEDIFF(NOW(),DATE_ADD(b.checkout_date, INTERVAL 30 DAY)) < 0 ORDER BY b.id_benefit DESC",
            nativeQuery = true)
    public List<BenefitEntity> getAllBenefitsByCustomer(@Param("id")  int id );

    @Query(value = "SELECT b.* FROM snapside.benefit as b, snapside.ad as a WHERE b.ad_id_ad=a.id_ad AND a.user_id_seller=?1 AND TIMEDIFF(NOW(),DATE_ADD(b.checkout_date, INTERVAL 30 DAY)) < 0 ORDER BY b.id_benefit DESC",
            nativeQuery = true)
    public List<BenefitEntity> getAllBenefitsBySeller(@Param("id")  int id );
}
