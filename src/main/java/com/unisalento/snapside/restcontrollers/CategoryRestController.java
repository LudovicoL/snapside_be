package com.unisalento.snapside.restcontrollers;

import com.unisalento.snapside.adapters.AdAdapter;
import com.unisalento.snapside.adapters.CategoryAdapter;
import com.unisalento.snapside.exceptions.AdNotFoundException;
import com.unisalento.snapside.exceptions.CategoryNotFoundException;
import com.unisalento.snapside.exceptions.ItemNotFoundException;
import com.unisalento.snapside.exceptions.UserNotFoundException;
import com.unisalento.snapside.generated.domain.AdEntity;
import com.unisalento.snapside.generated.domain.CategoryEntity;
import com.unisalento.snapside.generated.domain.ItemEntity;
import com.unisalento.snapside.generated.domain.UserEntity;
import com.unisalento.snapside.iservices.ICategoryService;
import com.unisalento.snapside.models.AdDTO;
import com.unisalento.snapside.models.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController()
@RequestMapping(value="/category")
public class CategoryRestController {

    ICategoryService categoryService;

    @Autowired
    public CategoryRestController(ICategoryService categoryService) {
        super();
        this.categoryService = categoryService;
    }


    @RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDTO> getAll() throws CategoryNotFoundException {
        List<CategoryDTO> dtos = new ArrayList<CategoryDTO>();
        List<CategoryEntity> entities = categoryService.getAll();
        Iterator<CategoryEntity> iteratorElement = entities.iterator();
        while(iteratorElement.hasNext()){
            CategoryEntity category = iteratorElement.next();
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO= CategoryAdapter.CategoryEntityToCategoryDTO(category);
            dtos.add(categoryDTO);
        }
        return dtos;
    }

    @GetMapping(value="/getById/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public CategoryDTO getById(@PathVariable("id") int id) throws CategoryNotFoundException{
        CategoryDTO categoryDTO = new CategoryDTO();
        CategoryEntity category = new CategoryEntity();
        category = categoryService.getById(id);
        categoryDTO = CategoryAdapter.CategoryEntityToCategoryDTO(category);
        return categoryDTO;
    }

    @GetMapping(value="/getCategoryFromAd/{idAd}", produces=MediaType.APPLICATION_JSON_VALUE)
    public CategoryDTO getCategoryFromAd(@PathVariable("idAd") int idAd) throws CategoryNotFoundException{
        CategoryDTO categoryDTO = new CategoryDTO();
        CategoryEntity category = new CategoryEntity();
        category = categoryService.getCategoryFromAd(idAd);
        categoryDTO = CategoryAdapter.CategoryEntityToCategoryDTO(category);
        return categoryDTO;
    }

    @PostMapping(value="/save", consumes= MediaType.APPLICATION_JSON_VALUE)
    public CategoryDTO saveCategory(@RequestBody CategoryDTO categoryDTO) throws AdNotFoundException, UserNotFoundException, ItemNotFoundException {

        CategoryEntity category = new CategoryEntity();
        System.out.println(categoryDTO);
        category = CategoryAdapter.CategoryDTOToCategoryEntity(categoryDTO);

        CategoryEntity newCategory=categoryService.saveAndFlush(category);
//        return "Category SAVED!";
        return CategoryAdapter.CategoryEntityToCategoryDTO(newCategory);
    }

    @GetMapping(value="/delete/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable("id") int id) {

        categoryService.delete(id);

        return "Category DELETED!";
    }

}
