package com.unisalento.snapside.repositories;

import com.unisalento.snapside.generated.domain.AdHasAttributeEntity;
import com.unisalento.snapside.generated.domain.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    @Query(value = "SELECT c.* FROM snapside.comment as c, snapside.ad as a WHERE a.id_ad=?1 AND a.id_ad=c.ad_id_ad ORDER BY c.id_comment ASC",
            nativeQuery = true)
    public List<CommentEntity> getAllCommentsFromAd(@Param("idAd") int idAd );

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM snapside.comment WHERE comment.id_comment = ?1",
            nativeQuery = true)
    public void delete(@Param("id") int id );
}
