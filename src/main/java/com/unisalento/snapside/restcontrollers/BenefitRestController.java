package com.unisalento.snapside.restcontrollers;

import com.unisalento.snapside.adapters.BenefitAdapter;
import com.unisalento.snapside.exceptions.AdNotFoundException;
import com.unisalento.snapside.exceptions.BenefitNotFoundException;
import com.unisalento.snapside.exceptions.UserNotFoundException;
import com.unisalento.snapside.generated.domain.AdEntity;
import com.unisalento.snapside.generated.domain.BenefitEntity;
import com.unisalento.snapside.generated.domain.UserEntity;
import com.unisalento.snapside.iservices.IAdService;
import com.unisalento.snapside.iservices.IBenefitService;
import com.unisalento.snapside.iservices.IUserService;
import com.unisalento.snapside.models.BenefitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController()
@RequestMapping(value="/benefit")
public class BenefitRestController {

    IBenefitService benefitService;
    IUserService userService;
    IAdService adService;

    @Autowired
    public BenefitRestController(IBenefitService benefitService, IUserService userService, IAdService adService) {
        super();
        this.benefitService = benefitService;
        this.userService = userService;
        this.adService = adService;
    }


    @RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<BenefitDTO> getAll() throws BenefitNotFoundException {
        List<BenefitDTO> dtos = new ArrayList<BenefitDTO>();
        List<BenefitEntity> entities = benefitService.getAll();
        Iterator<BenefitEntity> iteratorElement = entities.iterator();
        while(iteratorElement.hasNext()){
            BenefitEntity benefit = iteratorElement.next();
            BenefitDTO benefitDTO = new BenefitDTO();
            benefitDTO= BenefitAdapter.BenefitEntityToBenefitDTO(benefit);
            dtos.add(benefitDTO);
        }
        return dtos;
    }


    @GetMapping(value="/getById/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public BenefitDTO getById(@PathVariable("id") int id) throws BenefitNotFoundException{
        BenefitDTO benefitDTO = new BenefitDTO();
        BenefitEntity benefit = new BenefitEntity();
        benefit = benefitService.getById(id);
        benefitDTO=BenefitAdapter.BenefitEntityToBenefitDTO(benefit);
        return benefitDTO;
    }

    @RequestMapping(value="/getAllBenefitsAtAd/{id_ad}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<BenefitDTO> getAllBenefitsAtAd(@PathVariable("id_ad") int id_ad) throws AdNotFoundException, UserNotFoundException, BenefitNotFoundException {

        List<BenefitDTO> dtos = new ArrayList<BenefitDTO>();
        List<BenefitEntity> entities = benefitService.getAllBenefitsAtAd(id_ad);
        Iterator<BenefitEntity> iteratorElement = entities.iterator();
        while(iteratorElement.hasNext()) {
            BenefitEntity benefit = iteratorElement.next();
            BenefitDTO benefitDTO = new BenefitDTO();
            benefitDTO = BenefitAdapter.BenefitEntityToBenefitDTO(benefit);
            dtos.add(benefitDTO);

        }

        return dtos;
    }

    @RequestMapping(value="/getAllBenefitsByCustomer/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<BenefitDTO> getAllBenefitsByCustomer(@PathVariable("id") int id) throws  BenefitNotFoundException {

        List<BenefitDTO> dtos = new ArrayList<BenefitDTO>();
        List<BenefitEntity> entities = benefitService.getAllBenefitsByCustomer(id);
        Iterator<BenefitEntity> iteratorElement = entities.iterator();
        while(iteratorElement.hasNext()) {
            BenefitEntity benefit = iteratorElement.next();
            BenefitDTO benefitDTO = new BenefitDTO();
            benefitDTO = BenefitAdapter.BenefitEntityToBenefitDTO(benefit);
            System.out.println(benefitDTO);
            dtos.add(benefitDTO);

        }

        return dtos;
    }

    @RequestMapping(value="/getAllBenefitsBySeller/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<BenefitDTO> getAllBenefitsBySeller(@PathVariable("id") int id) throws  BenefitNotFoundException {

        List<BenefitDTO> dtos = new ArrayList<BenefitDTO>();
        List<BenefitEntity> entities = benefitService.getAllBenefitsBySeller(id);
        Iterator<BenefitEntity> iteratorElement = entities.iterator();
        while(iteratorElement.hasNext()) {
            BenefitEntity benefit = iteratorElement.next();
            BenefitDTO benefitDTO = new BenefitDTO();
            benefitDTO = BenefitAdapter.BenefitEntityToBenefitDTO(benefit);
            System.out.println(benefitDTO);
            dtos.add(benefitDTO);

        }

        return dtos;
    }


    @PostMapping(value="/save", consumes= MediaType.APPLICATION_JSON_VALUE)
    public String saveBenefit(@RequestBody BenefitDTO benefitDTO) throws UserNotFoundException, AdNotFoundException {

        BenefitEntity benefit = new BenefitEntity();
        UserEntity customerUser = new UserEntity();
        AdEntity ad = new AdEntity();
        ad=adService.getById(benefitDTO.getAd_id_ad());
        customerUser=userService.getById(benefitDTO.getUser_id_user());
        benefit = BenefitAdapter.BenefitDTOToBenefitEntity(benefitDTO,customerUser,ad);

        benefitService.save(benefit);
        return "Benefit SAVED!";
    }
}

