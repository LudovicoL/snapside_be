package com.unisalento.snapside.repositories;

import com.unisalento.snapside.generated.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {


    /*@Query("select a from UserEntity a where a.idUser=:idUser")
    public UserEntity getUserEntityById(@Param("idUser") int idUser);
*/

    @Query(value = "SELECT b.* FROM snapside.user as u, snapside.ad as adv, snapside.benefit as b WHERE adv.id_ad=?1 AND adv.id_ad=b.ad_id_ad AND b.user_id_user=u.id_user",
            nativeQuery = true)
    public List<String> getAllBenefitsAtAd(@Param("idAd")  int idAd );


    @Query(value = "SELECT u.* FROM snapside.comment as c, snapside.user as u WHERE c.id_comment=?1 AND c.user_id_user = u.id_user",
            nativeQuery = true)
    public UserEntity getUserFromComment(@Param("idComment")  int idComment );

    @Query(value = "SELECT u.* FROM snapside.user as u WHERE u.username=?1",
            nativeQuery = true)
    public UserEntity getByUsername(@Param("username")  String username );


    @Modifying
    @Transactional
    @Query(value = "UPDATE snapside.user SET snapside.user.last_access = ?2  WHERE snapside.user.id_user = ?1",
            nativeQuery = true)
    public void updateLastAccess(@Param("idUser") int idUser, @Param("lastAccess") Timestamp lastAccess );

    @Modifying
    @Transactional
    @Query(value = "UPDATE snapside.user SET snapside.user.token = ?2  WHERE snapside.user.id_user = ?1",
            nativeQuery = true)
    public void updateFirebaseToken(@Param("idUser") int idUser, @Param("token") String token );
}
