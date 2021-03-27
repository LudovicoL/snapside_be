package com.unisalento.snapside.repositories;

import com.unisalento.snapside.generated.domain.AdEntity;
import com.unisalento.snapside.generated.domain.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {


    @Query(value = "SELECT * FROM snapside.category as c , snapside.ad as a, snapside.item as i " +
            "WHERE a.id_ad=?1 AND a.item_id_item=i.id_item AND i.category_id_category=c.id_category",
            nativeQuery = true)
    public CategoryEntity getCategoryFromAd(@Param("idAd") int idAd );

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM snapside.category WHERE category.id_category = ?1",
            nativeQuery = true)
    public void delete(@Param("id") int id );
}


