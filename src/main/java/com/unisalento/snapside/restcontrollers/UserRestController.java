package com.unisalento.snapside.restcontrollers;

import java.security.Principal;
//import java.security.Timestamp;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.unisalento.snapside.adapters.AdAdapter;
import com.unisalento.snapside.adapters.CommentAdapter;
import com.unisalento.snapside.adapters.UserAdapter;
import com.unisalento.snapside.exceptions.AdNotFoundException;
import com.unisalento.snapside.exceptions.CommentNotFoundException;
import com.unisalento.snapside.generated.domain.AdEntity;
import com.unisalento.snapside.generated.domain.CommentEntity;
import com.unisalento.snapside.generated.domain.UserEntity;
import com.unisalento.snapside.iservices.ICommentService;
import com.unisalento.snapside.models.AdDTO;
import com.unisalento.snapside.models.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.unisalento.snapside.services.UserService;

import com.unisalento.snapside.iservices.IUserService;
import com.unisalento.snapside.models.UserDTO;

import com.unisalento.snapside.exceptions.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;


@RestController() //
@RequestMapping(value="/user")

public class UserRestController {
    Logger logger = Logger.getLogger(UserRestController.class.getName());

    IUserService userService;
    ICommentService commentService;

    @Autowired
    public UserRestController(IUserService userService, ICommentService commentService) {
        super();
        this.userService = userService;
        this.commentService = commentService;
    }

    @RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAll(@RequestHeader HttpHeaders headers) throws  UserNotFoundException {
        List<UserDTO> dtos = new ArrayList<UserDTO>();
        List<UserEntity> entities = userService.getAll();
        Iterator<UserEntity> iteratorElement = entities.iterator();
        while(iteratorElement.hasNext()){
            UserEntity user = iteratorElement.next();
            UserDTO userDTO = new UserDTO();
            userDTO= UserAdapter.UserEntityToUserDTO(user);
            dtos.add(userDTO);

        }
        System.out.println("Authme custom header "+headers.get("Authme"));
        return dtos;
    }

    @RequestMapping(value="/getUserFromComment/{idComment}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getAllCommentsFromAd(@PathVariable("idComment") int idComment) throws  UserNotFoundException {
        UserDTO userDTO = new UserDTO();
        UserEntity user = userService.getUserFromComment(idComment);
        userDTO = UserAdapter.UserEntityToUserDTO(user);
        return userDTO;
    }


    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    @RequestMapping(value="/login",produces= MediaType.APPLICATION_JSON_VALUE)
    public UserDTO login(@RequestBody UserEntity user) throws UserNotFoundException {
        boolean loggedin = false;
        List<UserEntity> users = null;
        users=(userService.getAll());
        UserDTO userDTO= new UserDTO();
        Iterator<UserEntity> users_iter = users.iterator();

        while(users_iter.hasNext()) {
            UserDTO currentUserDTO= new UserDTO();

            UserEntity currentUserEntity=users_iter.next();

            currentUserDTO= UserAdapter.UserEntityToUserDTO(currentUserEntity);


            if(currentUserDTO.getUsername().equalsIgnoreCase(user.getUsername()) && currentUserDTO.getPassword().equals(user.getPassword() ))
            {
                loggedin=true;
                System.out.println(loggedin);
                System.out.println("User: " + user.getUsername() + " - logged in now" );
                logger.log(Level.INFO,"User: " + user.getUsername() + " - logged in now" );
                userDTO=currentUserDTO;
                break;
            }
        }
        return userDTO;
    }


    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    @GetMapping(value="/getById/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getById(@PathVariable("id") int id) throws UserNotFoundException{
        UserDTO userDTO=new UserDTO();
        UserEntity user = new UserEntity();
        user = userService.getById(id);
        userDTO=UserAdapter.UserEntityToUserDTO(user);
        return userDTO;
    }

    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    @GetMapping(value="/getByUsername/{username}", produces=MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getByUsername(@PathVariable("username") String username) throws UserNotFoundException{
        UserDTO userDTO=new UserDTO();
        UserEntity user = new UserEntity();
        user = userService.getByUsername(username);

        if(user==null)
        {
            System.out.println("empty set");
            return userDTO;
        }
        System.out.println("found");
        userDTO=UserAdapter.UserEntityToUserDTO(user);

        return userDTO;
    }

    @PostMapping(value="/updateLastAccess/{idUser}", consumes= MediaType.APPLICATION_JSON_VALUE)
    public String updateLastAccess(@PathVariable("idUser") int idUser, @RequestBody UserDTO userDTO) throws UserNotFoundException {
        Timestamp lastAccess;
        lastAccess=userDTO.getLastAccess();
        userService.updateLastAccess(idUser, lastAccess);
        return "Last Access UPDATED!";
    }

    @PostMapping(value="/updateFirebaseToken/{idUser}", consumes= MediaType.APPLICATION_JSON_VALUE)
    public String updateFirebaseToken(@PathVariable("idUser") int idUser, @RequestBody UserDTO userDTO) throws UserNotFoundException {
        String token;
        token=userDTO.getToken();
        userService.updateFirebaseToken(idUser, token);
        return "Firebase Token UPDATED!";
    }
    
    @PostMapping(value="/save", consumes= MediaType.APPLICATION_JSON_VALUE)
    public String saveUser(@RequestBody UserDTO userDTO) throws AdNotFoundException, UserNotFoundException, UserNotFoundException {
        System.out.println(userDTO.getUsername());
        UserEntity user = new UserEntity();
        user = UserAdapter.UserDTOToUserEntity(userDTO);

        userService.save(user);
        System.out.println(user.getUsername());
        return "User SAVED!";
    }
}
