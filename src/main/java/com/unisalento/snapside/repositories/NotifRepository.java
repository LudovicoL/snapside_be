package com.unisalento.snapside.repositories;

import com.unisalento.snapside.generated.domain.NotifEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NotifRepository extends JpaRepository<NotifEntity, Integer> {



    @Query(value = "SELECT * FROM snapside.notif as n WHERE n.user_id_user=?1",
            nativeQuery = true)
    public List<NotifEntity> getAllbyUser(@Param("iduser") int iduser );


    @Modifying
    @Transactional
    @Query(value = "UPDATE notif SET notif.cleared = 1 WHERE notif.id_notif = ?1",
            nativeQuery = true)
    public void clearPush(@Param("idnotif") int ifnotif );

    @Modifying
    @Transactional
    @Query(value = "UPDATE notif SET notif.cleared = 2 WHERE notif.id_notif = ?1",
            nativeQuery = true)
    public void clearNotif(@Param("idnotif") int idnotif );


//    @Modifying
//    @Transactional
//    @Query(value = "DELETE FROM snapside.notif WHERE snapside.user.id_user=notif.user_id_user AND snapside.user.id_user=?1",
//            nativeQuery = true)
//    public void clearAllByUser(@Param("userid") int userid );

    @Modifying
    @Transactional
    @Query(value = "UPDATE notif,snapside.user SET notif.cleared = 2 WHERE snapside.user.id_user=notif.user_id_user AND snapside.user.id_user=?1",
            nativeQuery = true)
    public void clearAllByUser(@Param("userid") int userid );
}
