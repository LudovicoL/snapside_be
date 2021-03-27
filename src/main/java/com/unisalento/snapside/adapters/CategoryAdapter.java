package com.unisalento.snapside.adapters;

import com.unisalento.snapside.generated.domain.CategoryEntity;
import com.unisalento.snapside.models.CategoryDTO;

public class CategoryAdapter {
    public static CategoryEntity CategoryDTOToCategoryEntity(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setIdCategory(categoryDTO.getIdcategory());
        categoryEntity.setCategoryName(categoryDTO.getCategoryname());
        categoryEntity.setDescriptionCat(categoryDTO.getDescription());
        return categoryEntity;
    }

    public static CategoryDTO CategoryEntityToCategoryDTO(CategoryEntity categoryEntity) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setIdcategory(categoryEntity.getIdCategory());
        System.out.println(categoryEntity.getIdCategory());
        categoryDTO.setCategoryname(categoryEntity.getCategoryName());
        categoryDTO.setDescription(categoryEntity.getDescriptionCat());
        categoryDTO.setIdcategory(categoryEntity.getIdCategory());
        return categoryDTO;
    }
}
