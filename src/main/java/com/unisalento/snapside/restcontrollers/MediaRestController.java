package com.unisalento.snapside.restcontrollers;

import com.unisalento.snapside.adapters.MediaAdapter;
import com.unisalento.snapside.exceptions.AdNotFoundException;
import com.unisalento.snapside.exceptions.ItemNotFoundException;
import com.unisalento.snapside.exceptions.MediaNotFoundException;
import com.unisalento.snapside.exceptions.UserNotFoundException;
import com.unisalento.snapside.generated.domain.AdEntity;
import com.unisalento.snapside.generated.domain.MediaEntity;
import com.unisalento.snapside.generated.domain.ItemEntity;
import com.unisalento.snapside.generated.domain.UserEntity;
import com.unisalento.snapside.iservices.*;
import com.unisalento.snapside.models.MediaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RestController()    // This means that this class is a Controller
@RequestMapping(value="/media") // This means URL's start with /media (after Application path)
public class MediaRestController {

    IMediaService mediaService;
    IUserService userService;
    IItemService itemService;
    IAdService adService;
    IBenefitService benefitService;

    @Autowired
    public MediaRestController(IMediaService mediaService, IUserService userService, IItemService itemService, IAdService adService, IBenefitService benefitService) {
        super();
        this.mediaService = mediaService;
        this.userService = userService;
        this.itemService = itemService;
        this.adService = adService;
        this.benefitService = benefitService;
    }

    @RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<MediaDTO> getAll(@RequestHeader HttpHeaders headers) throws  MediaNotFoundException {
        System.out.println("1");
        List<MediaDTO> dtos = new ArrayList<MediaDTO>();
        System.out.println("2");

        List<MediaEntity> entities = mediaService.getAll();
        System.out.println("3");

        Iterator<MediaEntity> iteratorElement = entities.iterator();
        System.out.println("4");

        while(iteratorElement.hasNext()){
            System.out.println("5");

            MediaEntity media = iteratorElement.next();
            MediaDTO mediaDTO = new MediaDTO();

            mediaDTO=MediaAdapter.MediaEntityToMediaDTO(media);
            System.out.println(mediaDTO.getMediaName());
            dtos.add(mediaDTO);

        }

        return dtos;
    }
    @RequestMapping(value="/getAllByAd/{idAd}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<MediaDTO> getAllByAd(@PathVariable("idAd") int idAd) throws MediaNotFoundException {

        List<MediaDTO> dtos = new ArrayList<MediaDTO>();
        List<MediaEntity> entities = mediaService.getAllByAd(idAd);
        Iterator<MediaEntity> iteratorElement = entities.iterator();
        while(iteratorElement.hasNext()){
            MediaEntity media = iteratorElement.next();
            MediaDTO mediaDTO = new MediaDTO();
            mediaDTO=MediaAdapter.MediaEntityToMediaDTO(media);
            dtos.add(mediaDTO);

        }

        return dtos;
    }





    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    @GetMapping(value="/getById/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public MediaDTO getById(@PathVariable("id") int id) throws AdNotFoundException, MediaNotFoundException {
        MediaDTO mediaDTO=new MediaDTO();
        MediaEntity media = new MediaEntity();
        media = mediaService.getById(id);
        mediaDTO=MediaAdapter.MediaEntityToMediaDTO(media);
        return mediaDTO;
    }


    @PostMapping(value="/resetMedia/{idAd}", consumes= MediaType.APPLICATION_JSON_VALUE)
    public String resetMedia(@PathVariable("idAd") int idAd) throws AdNotFoundException, MediaNotFoundException {
        System.out.println(idAd+"BBB");
        mediaService.resetMedia(idAd);
        return "Media RESETTED!";
    }



    @PostMapping(value="/save", consumes= MediaType.APPLICATION_JSON_VALUE)
    public String saveMedia(@RequestBody MediaDTO mediaDTO) throws AdNotFoundException {
        MediaEntity media =new MediaEntity();
        AdEntity ad = new AdEntity();
        ad = adService.getById(mediaDTO.getAdByAdIdAd());
        media = MediaAdapter.MediaDTOToMediaEntity(mediaDTO,ad);
        mediaService.save(media);
        return "Media SAVED!";
    }



}