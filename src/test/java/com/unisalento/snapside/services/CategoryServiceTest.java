package com.unisalento.snapside.services;

import com.unisalento.snapside.exceptions.CategoryNotFoundException;
import com.unisalento.snapside.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.unisalento.snapside.generated.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepositoryMock;
    @InjectMocks
    private CategoryService categoryService;

    private CategoryEntity category = new CategoryEntity();
    private CategoryEntity category1 = new CategoryEntity();
    private CategoryEntity category2 = new CategoryEntity();

    @BeforeEach
    void setUp() {
        category.setIdCategory(1);
        category.setCategoryName("Name");
        category.setDescriptionCat("Description");

        category1.setIdCategory(2);
        category1.setCategoryName("Name1");
        category1.setDescriptionCat("Description1");

        category2.setIdCategory(3);
        category2.setCategoryName("Name2");
        category2.setDescriptionCat("Description2");
    }

    @Test
    void getAll() throws CategoryNotFoundException {
        when(categoryRepositoryMock.findAll()).thenReturn(Arrays.asList(category1, category2));
        List<CategoryEntity> category0 = categoryService.getAll();

        assertEquals(category1.getIdCategory(), category0.get(0).getIdCategory());
        assertEquals(category1.getCategoryName(), category0.get(0).getCategoryName());
        assertEquals(category1.getDescriptionCat(), category0.get(0).getDescriptionCat());
        assertEquals(category2.getIdCategory(), category0.get(1).getIdCategory());
        assertEquals(category2.getCategoryName(), category0.get(1).getCategoryName());
        assertEquals(category2.getDescriptionCat(), category0.get(1).getDescriptionCat());
    }

    @Test
    void getById() throws CategoryNotFoundException {
        when(categoryRepositoryMock.getOne(1)).thenReturn(category);
        CategoryEntity category0 = categoryService.getById(category.getIdCategory());
        assertEquals(category.getIdCategory(), category0.getIdCategory());
        assertEquals(category.getCategoryName(), category0.getCategoryName());
        assertEquals(category.getDescriptionCat(), category0.getDescriptionCat());
    }

    @Test
    void getCategoryFromAd() throws CategoryNotFoundException {
        AdEntity ad = new AdEntity();
        ad.setIdAd(1);
        when(categoryRepositoryMock.getCategoryFromAd(1)).thenReturn(category);
        CategoryEntity category0 = categoryService.getCategoryFromAd(ad.getIdAd());
        assertEquals(category.getIdCategory(), category0.getIdCategory());
        assertEquals(category.getCategoryName(), category0.getCategoryName());
        assertEquals(category.getDescriptionCat(), category0.getDescriptionCat());
    }

    @Test
    void save() {
        when(categoryRepositoryMock.save(Mockito.any(CategoryEntity.class))).thenReturn(category);
        CategoryEntity category0 = categoryService.save(category);
        assertEquals(category.getIdCategory(), category0.getIdCategory());
        assertEquals(category.getCategoryName(), category0.getCategoryName());
        assertEquals(category.getDescriptionCat(), category0.getDescriptionCat());
    }

    @Test
    void saveAndFlush() {
        when(categoryRepositoryMock.saveAndFlush(Mockito.any(CategoryEntity.class))).thenReturn(category);
        CategoryEntity category0 = categoryService.saveAndFlush(category);
        assertEquals(category.getIdCategory(), category0.getIdCategory());
        assertEquals(category.getCategoryName(), category0.getCategoryName());
        assertEquals(category.getDescriptionCat(), category0.getDescriptionCat());
    }

    @Test
    void delete() {
    }
}