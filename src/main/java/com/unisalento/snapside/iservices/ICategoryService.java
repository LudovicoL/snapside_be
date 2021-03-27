package com.unisalento.snapside.iservices;

import com.unisalento.snapside.exceptions.CategoryNotFoundException;
import com.unisalento.snapside.generated.domain.CategoryEntity;
import java.util.List;

public interface ICategoryService {
    public List<CategoryEntity> getAll() throws CategoryNotFoundException;
    public CategoryEntity getById(int id) throws CategoryNotFoundException;
    public CategoryEntity getCategoryFromAd(int idAd) throws CategoryNotFoundException;
    public CategoryEntity save(CategoryEntity category);
    public CategoryEntity saveAndFlush(CategoryEntity category);
    public void delete(int id);
}
