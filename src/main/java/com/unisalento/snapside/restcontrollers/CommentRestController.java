package com.unisalento.snapside.restcontrollers;

import com.unisalento.snapside.adapters.CategoryAdapter;
import com.unisalento.snapside.adapters.CommentAdapter;
import com.unisalento.snapside.exceptions.AdNotFoundException;
import com.unisalento.snapside.exceptions.CommentNotFoundException;
import com.unisalento.snapside.exceptions.ItemNotFoundException;
import com.unisalento.snapside.exceptions.UserNotFoundException;
import com.unisalento.snapside.generated.domain.AdEntity;
import com.unisalento.snapside.generated.domain.CategoryEntity;
import com.unisalento.snapside.generated.domain.CommentEntity;
import com.unisalento.snapside.generated.domain.UserEntity;
import com.unisalento.snapside.iservices.IAdService;
import com.unisalento.snapside.iservices.ICommentService;
import com.unisalento.snapside.iservices.IUserService;
import com.unisalento.snapside.models.CategoryDTO;
import com.unisalento.snapside.models.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController()
@RequestMapping(value="/comment")
public class CommentRestController {

    ICommentService commentService;
    IAdService adService;
    IUserService userService;

    @Autowired
    public CommentRestController(ICommentService commentService, IAdService adService, IUserService userService) {
        super();
        this.commentService = commentService;
        this.adService = adService;
        this.userService = userService;
    }

    @RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<CommentDTO> getAll() throws CommentNotFoundException {
        List<CommentDTO> dtos = new ArrayList<CommentDTO>();
        List<CommentEntity> entities = commentService.getAll();
        Iterator<CommentEntity> iteratorElement = entities.iterator();
        while(iteratorElement.hasNext()){
            CommentEntity comment = iteratorElement.next();
            CommentDTO commentDTO = new CommentDTO();
            commentDTO= CommentAdapter.CommentEntityToCommentDTO(comment);
            dtos.add(commentDTO);
        }
        return dtos;
    }


    @RequestMapping(value="/getAllCommentsFromAd/{idAd}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<CommentDTO> getAllCommentsFromAd(@PathVariable("idAd") int idAd) throws CommentNotFoundException{
        List<CommentDTO> dtos = new ArrayList<CommentDTO>();
        List<CommentEntity> entities = commentService.getAllCommentsFromAd(idAd);
        Iterator<CommentEntity> iteratorElement = entities.iterator();
        while(iteratorElement.hasNext()){
            CommentEntity comment = iteratorElement.next();
            CommentDTO commentDTO = new CommentDTO();
            commentDTO= CommentAdapter.CommentEntityToCommentDTO(comment);

            dtos.add(commentDTO);
        }
        return dtos;
    }






    @GetMapping(value="/getById/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public CommentDTO getById(@PathVariable("id") int id) throws CommentNotFoundException{
        CommentDTO commentDTO = new CommentDTO();
        CommentEntity comment = new CommentEntity();
        comment = commentService.getById(id);
        commentDTO=CommentAdapter.CommentEntityToCommentDTO(comment);
        return commentDTO;
    }



    @PostMapping(value="/save", consumes= MediaType.APPLICATION_JSON_VALUE)
    public String saveComment(@RequestBody CommentDTO commentDTO) throws AdNotFoundException, UserNotFoundException, CommentNotFoundException {

        CommentEntity comment = new CommentEntity();
        CommentEntity comment_to_comment = new CommentEntity();

        AdEntity ad = new AdEntity();
        UserEntity commenter = new UserEntity();
        ad=adService.getById(commentDTO.getAd_id_ad());
        commenter=userService.getById(commentDTO.getUser_id_user());
        comment_to_comment=commentService.getById(commentDTO.getIdComment());
        comment = CommentAdapter.CommentDTOToCommentEntity(commentDTO, ad, commenter);

        commentService.save(comment);
        return "Comment SAVED!";
    }

    @GetMapping(value="/delete/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable("id") int id) throws CommentNotFoundException{

        commentService.delete(id);

        return "Comment DELETED!";
    }


}

