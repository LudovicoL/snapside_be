package com.unisalento.snapside.restcontrollers;


import com.unisalento.snapside.adapters.AdHasAttributeAdapter;
import com.unisalento.snapside.adapters.AttributeAdapter;
import com.unisalento.snapside.exceptions.AdHasAttributeNotFoundException;
import com.unisalento.snapside.exceptions.AdNotFoundException;
import com.unisalento.snapside.exceptions.AttributeNotFoundException;
import com.unisalento.snapside.exceptions.CategoryNotFoundException;
import com.unisalento.snapside.generated.domain.*;
import com.unisalento.snapside.generated.domain.AdHasAttributeEntity;
import com.unisalento.snapside.iservices.IAdHasAttributeService;
import com.unisalento.snapside.iservices.IAdService;
import com.unisalento.snapside.iservices.IAttributeService;
import com.unisalento.snapside.iservices.ICategoryService;
import com.unisalento.snapside.models.AdHasAttributeDTO;
import com.unisalento.snapside.models.AdHasAttributeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController()
@RequestMapping(value="/hasattrib")
public class AdHasAttributeRestController {
    //@Autowired
    IAdHasAttributeService hasAttributeService;
    //@Autowired
    ICategoryService categoryService;
    //@Autowired
    IAdService adService;
    //@Autowired
    IAttributeService attributeService;

    @Autowired
    public AdHasAttributeRestController(IAdHasAttributeService hasAttributeService, ICategoryService categoryService, IAdService adService, IAttributeService attributeService) {
        super();
        this.hasAttributeService = hasAttributeService;
        this.categoryService = categoryService;
        this.adService = adService;
        this.attributeService = attributeService;
    }



    @RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<AdHasAttributeDTO> getAll() throws AttributeNotFoundException, AdHasAttributeNotFoundException {
        List<AdHasAttributeDTO> dtos = new ArrayList<AdHasAttributeDTO>();
        List<AdHasAttributeEntity> entities = hasAttributeService.getAll();
        Iterator<AdHasAttributeEntity> iteratorElement = entities.iterator();
        while(iteratorElement.hasNext()){
            AdHasAttributeEntity attribute = iteratorElement.next();
            AdHasAttributeDTO attributeDTO = new AdHasAttributeDTO();
            attributeDTO= AdHasAttributeAdapter.AdHasAttributeEntityToAdHasAttributeDTO(attribute);
            dtos.add(attributeDTO);
        }
        return dtos;
    }


    @RequestMapping(value="/getAllAttribValuesFromAd/{idAd}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<AdHasAttributeDTO> getAllAttribValuesFromAd(@PathVariable("idAd") int idAd) throws AdHasAttributeNotFoundException {
        List<AdHasAttributeDTO> dtos = new ArrayList<AdHasAttributeDTO>();


        List<AdHasAttributeEntity> entities = hasAttributeService.getAllAttribValuesFromAd(idAd);

        Iterator<AdHasAttributeEntity> iteratorElement = entities.iterator();

        while(iteratorElement.hasNext()){
            AdHasAttributeEntity attribute = iteratorElement.next();
            AdHasAttributeDTO attributeDTO = new AdHasAttributeDTO();
            attributeDTO= AdHasAttributeAdapter.AdHasAttributeEntityToAdHasAttributeDTO(attribute);
            dtos.add(attributeDTO);
        }
        return dtos;
    }


    @GetMapping(value="/getById/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public AdHasAttributeDTO getById(@PathVariable("id") int id) throws AdHasAttributeNotFoundException {
        AdHasAttributeDTO attributeDTO = new AdHasAttributeDTO();
        AdHasAttributeEntity attribute = new AdHasAttributeEntity();
        attribute = hasAttributeService.getById(id);
        attributeDTO=AdHasAttributeAdapter.AdHasAttributeEntityToAdHasAttributeDTO(attribute);
        return attributeDTO;
    }

    @PostMapping(value="/save", consumes= MediaType.APPLICATION_JSON_VALUE)
    public String saveAHA(@RequestBody AdHasAttributeDTO adHasDTO) throws AdNotFoundException, AttributeNotFoundException {
        AdHasAttributeEntity adHas =new AdHasAttributeEntity();
        AttributeEntity attribute =new AttributeEntity();
        attribute=attributeService.getById(adHasDTO.getAttributeByAttributeIdAttribute());
        AdEntity ad =new AdEntity();
        ad=adService.getById(adHasDTO.getAdByAdIdAd());
        adHas = AdHasAttributeAdapter.AdHasAttributeDTOToAdHasAttributeEntity(adHasDTO, ad, attribute);
        hasAttributeService.save(adHas);
        return "Ad Has Attribute SAVED!";
    }


    @PostMapping(value="/resetAttributes/{idAd}", consumes= MediaType.APPLICATION_JSON_VALUE)
    public String resetMedia(@PathVariable("idAd") int idAd) throws AdNotFoundException {

        hasAttributeService.resetAttributes(idAd);
        return "Attributes RESETTED!";
    }
}
