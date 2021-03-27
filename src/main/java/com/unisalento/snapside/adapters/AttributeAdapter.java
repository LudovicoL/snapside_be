package com.unisalento.snapside.adapters;

import com.unisalento.snapside.generated.domain.AttributeEntity;
import com.unisalento.snapside.generated.domain.CategoryEntity;
import com.unisalento.snapside.generated.domain.ItemEntity;
import com.unisalento.snapside.models.AttributeDTO;

public class AttributeAdapter {
    public static AttributeEntity AttributeDTOToAttributeEntity(AttributeDTO attributeDTO, ItemEntity item) {
        AttributeEntity attribute = new AttributeEntity();
        attribute.setIdAttribute(attributeDTO.getIdattribute());
        attribute.setAttribName(attributeDTO.getAttributeName());
//        attribute.setAttributeValue(attributeDTO.getAttributeValue());
        attribute.setItemByItemIdItem(item);
//        attribute.setCategoryByCategoryIdCategory(category);
        return attribute;
    }

    public static AttributeDTO AttributeEntityToAttributeDTO (AttributeEntity attribute) {
        AttributeDTO attributeDTO = new AttributeDTO();
        attributeDTO.setIdattribute(attribute.getIdAttribute());
        attributeDTO.setAttributeName(attribute.getAttribName());
//        attributeDTO.setAttributeValue(attribute.getAttributeValue());
//        attributeDTO.setCategory_idcategory(attribute.getCategoryByCategoryIdCategory().getIdCategory());
        attributeDTO.setItemByItemIdItem(attribute.getItemByItemIdItem().getIdItem());
        return attributeDTO;
    }
}
