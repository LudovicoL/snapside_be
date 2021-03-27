package com.unisalento.snapside.restcontrollers;

import com.unisalento.snapside.adapters.CommentAdapter;
import com.unisalento.snapside.iservices.IAdService;
import com.unisalento.snapside.iservices.ICommentService;
import com.unisalento.snapside.iservices.IUserService;
import com.unisalento.snapside.models.CommentDTO;
import org.junit.jupiter.api.Test;

import com.unisalento.snapside.generated.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class CommentRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    ICommentService commentServiceMock;
    @Mock
    IAdService adServiceMock;
    @Mock
    IUserService userServiceMock;

    private CommentEntity comment = new CommentEntity();
    private CommentAdapter commentAdapter = new CommentAdapter();
    private ObjectMapper mapper = new ObjectMapper();
    private CommentDTO commentDTO = new CommentDTO();
    private List<CommentEntity> entities = new ArrayList<>();

    private UserEntity user = new UserEntity();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new CommentRestController(commentServiceMock, adServiceMock, userServiceMock))
                .build();
        comment.setIdComment(1);
        comment.setText("Text");
        comment.setRating(1);
        comment.setCommentIdComment(1);
        comment.setCreationDate(new Timestamp(System.currentTimeMillis()));
        comment.setUserByUserIdUser(new UserEntity());
        comment.setAdByAdIdAd(new AdEntity());

        commentDTO.setIdComment(1);
        commentDTO.setText("Text");
        commentDTO.setRating(1);
        commentDTO.setUser_id_user(1);
        commentDTO.setComment_idcomment(1);
        commentDTO.setAd_id_ad(1);

        for(int i = 0; i < 5; i++) entities.add(comment);
    }

    @Test
    void getAll() throws Exception {
        when(commentServiceMock.getAll()).thenReturn(entities);
        mockMvc.perform(get("/comment/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllCommentsFromAd() throws Exception {
        when(commentServiceMock.getAllCommentsFromAd(1)).thenReturn(entities);
        mockMvc.perform(get("/comment/getAllCommentsFromAd/{idAd}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        when(commentServiceMock.getById(1)).thenReturn(comment);
        mockMvc.perform(get("/comment/getById/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void saveComment() throws Exception {
        when(userServiceMock.getById(commentDTO.getUser_id_user())).thenReturn(user);
        when(commentServiceMock.getById(commentDTO.getIdComment())).thenReturn(comment);
        mockMvc.perform(post("/comment/save")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(commentDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(get("/comment/delete/{id}", 1))
                .andExpect(status().isOk());
    }
}