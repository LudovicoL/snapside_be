package com.unisalento.snapside.services;

import com.unisalento.snapside.exceptions.CategoryNotFoundException;
import com.unisalento.snapside.generated.domain.CategoryEntity;
import com.unisalento.snapside.iservices.ICategoryService;
import com.unisalento.snapside.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> getAll() throws CategoryNotFoundException {
        // TODO Auto-generated method stub
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity getById(int id) throws CategoryNotFoundException {
        // TODO Auto-generated method stub
        return categoryRepository.getOne(id);
    }

    @Override
    public CategoryEntity getCategoryFromAd(int idAd) throws CategoryNotFoundException {
        // TODO Auto-generated method stub
        return categoryRepository.getCategoryFromAd(idAd);
    }

    @Override
    public CategoryEntity save(CategoryEntity category) {
        // TODO Auto-generated method stub
        return categoryRepository.save(category);
    }

    @Override
    public CategoryEntity saveAndFlush(CategoryEntity category) {
        // TODO Auto-generated method stub
        return categoryRepository.saveAndFlush(category);
    }
    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
        categoryRepository.delete(id);
    }


}
