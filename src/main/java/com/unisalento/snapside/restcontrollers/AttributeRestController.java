package com.unisalento.snapside.restcontrollers;


import com.unisalento.snapside.adapters.AdHasAttributeAdapter;
import com.unisalento.snapside.adapters.AttributeAdapter;
import com.unisalento.snapside.exceptions.AdHasAttributeNotFoundException;
import com.unisalento.snapside.exceptions.AttributeNotFoundException;
import com.unisalento.snapside.exceptions.CategoryNotFoundException;
import com.unisalento.snapside.exceptions.ItemNotFoundException;
import com.unisalento.snapside.generated.domain.AdHasAttributeEntity;
import com.unisalento.snapside.generated.domain.AttributeEntity;
import com.unisalento.snapside.generated.domain.CategoryEntity;
import com.unisalento.snapside.generated.domain.ItemEntity;
import com.unisalento.snapside.iservices.IAttributeService;
import com.unisalento.snapside.iservices.ICategoryService;
import com.unisalento.snapside.iservices.IItemService;
import com.unisalento.snapside.models.AttributeDTO;
import com.unisalento.snapside.models.AttributeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController()
@RequestMapping(value="/attribute")
public class AttributeRestController {

    IAttributeService attributeService;
    ICategoryService categoryService;
    IItemService itemService;

    @Autowired
    public AttributeRestController(IAttributeService attributeService, ICategoryService categoryService, IItemService itemService) {
        super();
        this.attributeService = attributeService;
        this.categoryService = categoryService;
        this.itemService = itemService;
    }

    @RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<AttributeDTO> getAll() throws AttributeNotFoundException {
        List<AttributeDTO> dtos = new ArrayList<AttributeDTO>();
        List<AttributeEntity> entities = attributeService.getAll();
        Iterator<AttributeEntity> iteratorElement = entities.iterator();
        while(iteratorElement.hasNext()){
            AttributeEntity attribute = iteratorElement.next();
            AttributeDTO attributeDTO = new AttributeDTO();
            attributeDTO= AttributeAdapter.AttributeEntityToAttributeDTO(attribute);
            dtos.add(attributeDTO);
        }
        return dtos;
    }


    @RequestMapping(value="/getAllFromAd/{idAd}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<AttributeDTO> getAllFromAd(@PathVariable("idAd") int idAd) throws AttributeNotFoundException {
        List<AttributeDTO> dtos = new ArrayList<AttributeDTO>();


        List<AttributeEntity> entities = attributeService.getAllFromAd(idAd);

        Iterator<AttributeEntity> iteratorElement = entities.iterator();

        while(iteratorElement.hasNext()){
            AttributeEntity attribute = iteratorElement.next();
            AttributeDTO attributeDTO = new AttributeDTO();
            attributeDTO= AttributeAdapter.AttributeEntityToAttributeDTO(attribute);
            dtos.add(attributeDTO);
        }
        return dtos;
    }


    @GetMapping(value="/getById/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public AttributeDTO getById(@PathVariable("id") int id) throws AttributeNotFoundException {
        AttributeDTO attributeDTO = new AttributeDTO();
        AttributeEntity attribute = new AttributeEntity();
        attribute = attributeService.getById(id);
        attributeDTO=AttributeAdapter.AttributeEntityToAttributeDTO(attribute);
        return attributeDTO;
    }


    @PostMapping(value="/save", consumes= MediaType.APPLICATION_JSON_VALUE)
    public AttributeDTO saveAttribute(@RequestBody AttributeDTO attributeDTO) throws ItemNotFoundException, CategoryNotFoundException, ItemNotFoundException {

        AttributeEntity attribute = new AttributeEntity();
        ItemEntity item = itemService.getById(attributeDTO.getItemByItemIdItem());
        attribute = AttributeAdapter.AttributeDTOToAttributeEntity(attributeDTO,item);

        AttributeEntity newAttribute=attributeService.saveAndFlush(attribute);
        return AttributeAdapter.AttributeEntityToAttributeDTO(newAttribute);
    }

}
