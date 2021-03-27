package com.unisalento.snapside.restcontrollers;

import com.unisalento.snapside.adapters.NotifAdapter;
import com.unisalento.snapside.exceptions.NotifNotFoundException;
import com.unisalento.snapside.exceptions.UserNotFoundException;
import com.unisalento.snapside.generated.domain.NotifEntity;
import com.unisalento.snapside.generated.domain.UserEntity;
import com.unisalento.snapside.iservices.INotifService;
import com.unisalento.snapside.iservices.IUserService;
import com.unisalento.snapside.models.NotifDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController()
@RequestMapping(value="/notif")
public class NotifRestController {

    INotifService notifService;
    IUserService userService;

    @Autowired
    public NotifRestController(INotifService notifService, IUserService userService) {
        super();
        this.notifService = notifService;
        this.userService = userService;
    }


    @RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<NotifDTO> getAll() throws NotifNotFoundException {
        List<NotifDTO> dtos = new ArrayList<NotifDTO>();
        List<NotifEntity> entities = notifService.getAll();
        Iterator<NotifEntity> iteratorElement = entities.iterator();
        while(iteratorElement.hasNext()){
            NotifEntity notif = iteratorElement.next();
            NotifDTO notifDTO = new NotifDTO();
            notifDTO= NotifAdapter.NotifEntityToNotifDTO(notif);
            dtos.add(notifDTO);
        }
        return dtos;
    }

    @GetMapping(value="/getById/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public NotifDTO getById(@PathVariable("id") int id) throws NotifNotFoundException{
        NotifDTO notifDTO = new NotifDTO();
        NotifEntity notif = new NotifEntity();
        notif = notifService.getById(id);
        notifDTO=NotifAdapter.NotifEntityToNotifDTO(notif);
        return notifDTO;
    }

    @GetMapping(value="/getAllbyUser/{userid}", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<NotifDTO> getAllbyUser(@PathVariable("userid") int userid) throws NotifNotFoundException{

        List<NotifDTO> dtos = new ArrayList<NotifDTO>();
        List<NotifEntity> entities = notifService.getAllbyUser(userid);
        Iterator<NotifEntity> iteratorElement = entities.iterator();
        while(iteratorElement.hasNext()){
            NotifEntity notif = iteratorElement.next();
            NotifDTO notifDTO = new NotifDTO();
            notifDTO= NotifAdapter.NotifEntityToNotifDTO(notif);
            dtos.add(notifDTO);
        }
        return dtos;
    }

    @GetMapping(value="/clearPush/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public String clearPush(@PathVariable("id") int id) throws NotifNotFoundException{
        notifService.clearPush(id);
        return "Push Notification CLEARED!";
    }

    @GetMapping(value="/clearNotif/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public String clearNotif(@PathVariable("id") int id) throws NotifNotFoundException{
        notifService.clearNotif(id);
        return "Static Notification CLEARED!";
    }

    @GetMapping(value="/clearAllByUser/{userid}", produces=MediaType.APPLICATION_JSON_VALUE)
    public String clearAllByUser(@PathVariable("userid") int userid) throws NotifNotFoundException{
        notifService.clearAllByUser(userid);
        return "All Static Notifications CLEARED!";
    }

    @PostMapping(value="/save", consumes= MediaType.APPLICATION_JSON_VALUE)
    public String saveNotif(@RequestBody NotifDTO notifDTO) throws NotifNotFoundException, UserNotFoundException {

        NotifEntity notif = new NotifEntity();
        UserEntity receiverUser = new UserEntity();

        receiverUser=userService.getById(notifDTO.getUserByUserIdUser());
        notif = NotifAdapter.NotifDTOToNotifEntity(notifDTO,receiverUser);

        notifService.save(notif);
        return "Notification SAVED!";
    }
    
}