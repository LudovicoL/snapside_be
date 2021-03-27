package com.unisalento.snapside.restcontrollers;

import com.unisalento.snapside.adapters.ItemAdapter;
import com.unisalento.snapside.exceptions.CategoryNotFoundException;
import com.unisalento.snapside.exceptions.ItemNotFoundException;
import com.unisalento.snapside.generated.domain.CategoryEntity;
import com.unisalento.snapside.generated.domain.ItemEntity;
import com.unisalento.snapside.iservices.ICategoryService;
import com.unisalento.snapside.iservices.IItemService;
import com.unisalento.snapside.models.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController()
@RequestMapping(value="/item")
public class ItemRestController {

    IItemService itemService;
    ICategoryService categoryService;

    @Autowired
    public ItemRestController(IItemService itemService, ICategoryService categoryService) {
        super();
        this.itemService = itemService;
        this.categoryService = categoryService;
    }



    @RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getAll() throws ItemNotFoundException {
        List<ItemDTO> dtos = new ArrayList<ItemDTO>();
        List<ItemEntity> entities = itemService.getAll();
        Iterator<ItemEntity> iteratorElement = entities.iterator();
        while(iteratorElement.hasNext()){
            ItemEntity item = iteratorElement.next();
            ItemDTO itemDTO = new ItemDTO();
            itemDTO= ItemAdapter.ItemEntityToItemDTO(item);
            dtos.add(itemDTO);
        }
        return dtos;
    }

    @GetMapping(value="/getById/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ItemDTO getById(@PathVariable("id") int id) throws ItemNotFoundException{
        ItemDTO itemDTO = new ItemDTO();
        ItemEntity item = new ItemEntity();
        item = itemService.getById(id);
        itemDTO=ItemAdapter.ItemEntityToItemDTO(item);
        return itemDTO;
    }

    @GetMapping(value="/getItemFromAd/{idAd}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ItemDTO getItemFromAd(@PathVariable("idAd") int idAd) throws ItemNotFoundException{
        ItemDTO itemDTO = new ItemDTO();
        ItemEntity item = new ItemEntity();
        item = itemService.getItemFromAd(idAd);
        itemDTO=ItemAdapter.ItemEntityToItemDTO(item);
        return itemDTO;
    }

    @PostMapping(value="/save", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ItemDTO saveItem(@RequestBody ItemDTO itemDTO) throws ItemNotFoundException, CategoryNotFoundException {

        ItemEntity item = new ItemEntity();
        CategoryEntity category = categoryService.getById(itemDTO.getCategory_idcategory());
        item = ItemAdapter.ItemDTOToItemEntity(itemDTO,category);

        ItemEntity newItem=itemService.saveAndFlush(item);
        return ItemAdapter.ItemEntityToItemDTO(newItem);
    }
}