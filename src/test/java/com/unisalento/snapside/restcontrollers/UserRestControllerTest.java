package com.unisalento.snapside.restcontrollers;

import com.unisalento.snapside.adapters.UserAdapter;
import com.unisalento.snapside.iservices.ICommentService;
import com.unisalento.snapside.iservices.IUserService;
import com.unisalento.snapside.models.UserDTO;
import com.unisalento.snapside.generated.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    IUserService userServiceMock;
    @Mock
    ICommentService commentServiceMock;

    private UserEntity user = new UserEntity();
    private UserAdapter userAdapter = new UserAdapter();
    private ObjectMapper mapper = new ObjectMapper();
    private UserDTO userDTO = new UserDTO();
    private List<UserEntity> entities = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new UserRestController(userServiceMock, commentServiceMock))
                .build();
        user.setIdUser(1);
        user.setName("Mario");
        user.setSurname("Rossi");
        java.sql.Date sqlDate = new java.sql.Date(new Long(0)).valueOf("2020-01-01");
        user.setDob(sqlDate);
        user.setEmail("mariorossi@mail.com");
        user.setUsername("mariorossi");
        user.setPassword("password");
        user.setAddress("Via street");
        user.setEnabled(true);
        user.setOnline(true);
        user.setUserType("SELLER");
        user.setPhone("3333333333");
        user.setUserImg("".getBytes());
        user.setLastAccess(new Timestamp(System.currentTimeMillis()));


        userDTO.setIdUser(1);
        userDTO.setName("Mario");
        userDTO.setSurname("Rossi");
        userDTO.setDob(sqlDate);
        userDTO.setEmail("mariorossi@mail.com");
        userDTO.setUsername("mariorossi");
        userDTO.setPassword("password");
        userDTO.setAddress("Via street");
        userDTO.setEnabled(true);
        userDTO.setUserType("SELLER");
        userDTO.setUserImg("".getBytes());
        userDTO.setLastAccess(new Timestamp(System.currentTimeMillis()));
        userDTO.setToken("token");

        for(int i = 0; i < 5; i++) entities.add(user);
    }

    @Test
    void getAll() throws Exception {
        when(userServiceMock.getAll()).thenReturn(entities);
        mockMvc.perform(get("/user/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllCommentsFromAd() throws Exception {
        when(userServiceMock.getUserFromComment(1)).thenReturn(user);
        mockMvc.perform(get("/user/getUserFromComment/{idComment}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void login() throws Exception {
        when(userServiceMock.getAll()).thenReturn(entities);
        mockMvc.perform(post("/user/login")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        when(userServiceMock.getById(1)).thenReturn(user);
        mockMvc.perform(get("/user/getById/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void getByUsername() throws Exception {
        when(userServiceMock.getById(1)).thenReturn(user);
        mockMvc.perform(get("/user/getByUsername/{username}", "mariorossi")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void updateLastAccess() throws Exception {
        //when(userServiceMock.updateLastAccess(1, user.getLastAccess())).thenReturn();
        mockMvc.perform(post("/user/updateLastAccess/{idUser}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(userDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void updateFirebaseToken() throws Exception {
        mockMvc.perform(post("/user/updateFirebaseToken/{idUser}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(userDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void saveUser() throws Exception {
        when(userServiceMock.save(user)).thenReturn(user);
        mockMvc.perform(post("/user/save")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(userDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}