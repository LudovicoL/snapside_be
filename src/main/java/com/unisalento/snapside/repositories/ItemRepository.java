package com.unisalento.snapside.repositories;

import com.unisalento.snapside.generated.domain.CategoryEntity;
import com.unisalento.snapside.generated.domain.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {

    @Query(value = "SELECT * FROM snapside.item as i , snapside.ad as a " +
            "WHERE a.id_ad=?1 AND a.item_id_item=i.id_item",
            nativeQuery = true)
    public ItemEntity getItemFromAd(@Param("idAd") int idAd );

}
