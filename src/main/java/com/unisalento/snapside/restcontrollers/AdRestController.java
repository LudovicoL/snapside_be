package com.unisalento.snapside.restcontrollers;

import com.unisalento.snapside.adapters.AdAdapter;
import com.unisalento.snapside.adapters.BenefitAdapter;
import com.unisalento.snapside.adapters.UserAdapter;
import com.unisalento.snapside.exceptions.AdNotFoundException;
import com.unisalento.snapside.exceptions.BenefitNotFoundException;
import com.unisalento.snapside.exceptions.ItemNotFoundException;
import com.unisalento.snapside.exceptions.UserNotFoundException;
import com.unisalento.snapside.generated.domain.AdEntity;
import com.unisalento.snapside.generated.domain.BenefitEntity;
import com.unisalento.snapside.generated.domain.ItemEntity;
import com.unisalento.snapside.generated.domain.UserEntity;
import com.unisalento.snapside.iservices.IBenefitService;
import com.unisalento.snapside.iservices.IItemService;
import com.unisalento.snapside.iservices.IUserService;
import com.unisalento.snapside.models.AdDTO;
import com.unisalento.snapside.iservices.IAdService;
import com.unisalento.snapside.models.BenefitDTO;
import com.unisalento.snapside.models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RestController()    // This means that this class is a Controller
@RequestMapping(value="/ad") // This means URL's start with /ad (after Application path)
public class AdRestController {

    IAdService adService;
    IUserService userService;
    IItemService itemService;
    IBenefitService benefitService;

    @Autowired
    public AdRestController(IAdService adService, IUserService userService, IItemService itemService, IBenefitService benefitService) {
        super();
        this.adService = adService;
        this.userService = userService;
        this.itemService = itemService;
        this.benefitService = benefitService;
    }

    @RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<AdDTO> getAll(@RequestHeader HttpHeaders headers) throws AdNotFoundException {
        List<AdDTO> dtos = new ArrayList<AdDTO>();
        List<AdEntity> entities = adService.getAll();
        Iterator<AdEntity> iteratorElement = entities.iterator();
        while(iteratorElement.hasNext()){
            AdEntity ad = iteratorElement.next();
            AdDTO adDTO = new AdDTO();
            adDTO=AdAdapter.AdEntityToAdDTO(ad);
            dtos.add(adDTO);

        }
//        System.out.println("Authme custom header "+headers.get("Authme"));
        return dtos;
    }
    @RequestMapping(value="/getAllbySeller/{seller_id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<AdDTO> getAllbySeller(@PathVariable("seller_id") int seller_id) throws AdNotFoundException, UserNotFoundException {

        List<AdDTO> dtos = new ArrayList<AdDTO>();
        List<AdEntity> entities = adService.getAllbySeller(seller_id);
        Iterator<AdEntity> iteratorElement = entities.iterator();
        while(iteratorElement.hasNext()){
            AdEntity ad = iteratorElement.next();
            AdDTO adDTO = new AdDTO();
            adDTO=AdAdapter.AdEntityToAdDTO(ad);
            dtos.add(adDTO);

        }

        return dtos;
    }

    @RequestMapping(value="/getAllPublished", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<AdDTO> getAllPublished(@RequestHeader HttpHeaders headers) throws AdNotFoundException, UserNotFoundException {

        List<AdDTO> dtos = new ArrayList<AdDTO>();
        List<AdEntity> entities = adService.getAllPublished();
        Iterator<AdEntity> iteratorElement = entities.iterator();
        while(iteratorElement.hasNext()){
            AdEntity ad = iteratorElement.next();
            AdDTO adDTO = new AdDTO();
            adDTO=AdAdapter.AdEntityToAdDTO(ad);
            dtos.add(adDTO);

        }

        return dtos;
    }




    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    @GetMapping(value="/getById/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public AdDTO getById(@PathVariable("id") int id) throws AdNotFoundException{
        AdDTO adDTO=new AdDTO();
        AdEntity ad = new AdEntity();
        ad = adService.getById(id);
        adDTO=AdAdapter.AdEntityToAdDTO(ad);
        return adDTO;
    }

    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    @GetMapping(value="/getLastBySeller/{idSeller}", produces=MediaType.APPLICATION_JSON_VALUE)
    public AdDTO getLastBySeller(@PathVariable("idSeller") int idSeller) throws AdNotFoundException{
        AdDTO adDTO=new AdDTO();
        AdEntity ad = new AdEntity();
        ad = adService.getLastBySeller(idSeller);
        adDTO=AdAdapter.AdEntityToAdDTO(ad);

        return adDTO;
    }

    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    @GetMapping(value="/getAllByTitle/{title}", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<AdDTO> getByTitle(@PathVariable("title") String title) throws AdNotFoundException{
        List<AdDTO> dtos = new ArrayList<AdDTO>();
        List<AdEntity> entities = adService.getAllByTitle(title);
        Iterator<AdEntity> iteratorElement = entities.iterator();
        while(iteratorElement.hasNext()){
            AdEntity ad = iteratorElement.next();
            AdDTO adDTO = new AdDTO();
            adDTO=AdAdapter.AdEntityToAdDTO(ad);
            dtos.add(adDTO);

        }

       return dtos;
    }



    @PostMapping(value="/save", consumes= MediaType.APPLICATION_JSON_VALUE)
    public String saveAd(@RequestBody AdDTO adDTO) throws AdNotFoundException, UserNotFoundException, ItemNotFoundException {
        System.out.println("ID seller "+adDTO.getUser_id_seller());
        AdEntity ad = new AdEntity();
        UserEntity user = userService.getById(adDTO.getUser_id_seller());
        System.out.println("user "+user);
        ItemEntity item = itemService.getById(adDTO.getItem_id_item());
        ad = AdAdapter.AdDTOToAdEntity(adDTO, user, item);
        System.out.println("ID seller "+ad.getUserByUserIdSeller());
        adService.save(ad);
        return "Ad SAVED!";
    }

    @PostMapping(value="/editAd", consumes= MediaType.APPLICATION_JSON_VALUE)
    public String editAd(@RequestBody AdDTO adDTO) throws AdNotFoundException, UserNotFoundException, ItemNotFoundException {
        System.out.println("ID seller "+adDTO.getUser_id_seller());
        AdEntity ad = new AdEntity();
        UserEntity user = userService.getById(adDTO.getUser_id_seller());
        System.out.println("user "+user);
        ItemEntity item = itemService.getById(adDTO.getItem_id_item());
        ad = AdAdapter.AdDTOToAdEntity(adDTO, user, item);
        System.out.println("ID seller "+ad.getUserByUserIdSeller());
        System.out.println(ad.getIdAd()+"edited id");
        adService.editAd(ad);
        return "Ad EDITED!";
    }


    @GetMapping(value="/logicDeleteAd/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable("id") int id) throws AdNotFoundException{
        adService.logicDeleteAd(id);
        return "Ad HIDDEN!";
    }

    @GetMapping(value="/deleteAd/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public String deleteAd(@PathVariable("id") int id) throws AdNotFoundException{
        adService.deleteAd(id);
        return "Ad DELETED!";
    }


    @PostMapping(value="/approve/{idAd}", consumes= MediaType.APPLICATION_JSON_VALUE)
    public String updateLastAccess(@PathVariable("idAd") int idAd, @RequestBody AdDTO adDTO) throws UserNotFoundException {

        int approved=adDTO.getApproved();
        adService.approve(idAd, approved);
        return "Ad Approval UPDATED!";
    }

}