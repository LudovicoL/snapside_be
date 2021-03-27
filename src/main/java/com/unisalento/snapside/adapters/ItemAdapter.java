package com.unisalento.snapside.adapters;

import com.unisalento.snapside.generated.domain.CategoryEntity;
import com.unisalento.snapside.generated.domain.ItemEntity;
import com.unisalento.snapside.models.ItemDTO;

public class ItemAdapter {
    public static ItemEntity ItemDTOToItemEntity(ItemDTO itemDTO, CategoryEntity category) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setIdItem(itemDTO.getIdItem());
        itemEntity.setCategoryByCategoryIdCategory(category);
        itemEntity.setItemName(itemDTO.getName());
        itemEntity.setDescriptionItem(itemDTO.getDescription());
        return itemEntity;
    }
    public static ItemDTO ItemEntityToItemDTO(ItemEntity itemEntity) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setIdItem(itemEntity.getIdItem());
        itemDTO.setName(itemEntity.getItemName());
        itemDTO.setDescription(itemEntity.getDescriptionItem());
        itemDTO.setCategory_idcategory(itemEntity.getCategoryByCategoryIdCategory().getIdCategory());
        return itemDTO;
    }

}
