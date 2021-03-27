package com.unisalento.snapside.restcontrollers;

import com.unisalento.snapside.adapters.NotifAdapter;
import com.unisalento.snapside.iservices.INotifService;
import com.unisalento.snapside.iservices.IUserService;
import com.unisalento.snapside.models.NotifDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.Before;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class NotifRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    INotifService notifServiceMock;
    @Mock
    IUserService userServiceMock;

    private NotifEntity notif = new NotifEntity();
    private NotifAdapter notifAdapter = new NotifAdapter();
    private ObjectMapper mapper = new ObjectMapper();
    private NotifDTO notifDTO = new NotifDTO();
    private List<NotifEntity> entities = new ArrayList<>();
    private UserEntity user = new UserEntity();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new NotifRestController(notifServiceMock, userServiceMock))
                .build();
        notif.setIdNotif(1);
        notif.setTitle("Title");
        notif.setSubject("Subject");
        notif.setStatus("Status");
        notif.setCleared(1);
        notif.setDate(new Timestamp(System.currentTimeMillis()));
        notif.setSenderIdUser(1);
        notif.setBody("Body");
        notif.setAdditive("Additive");
        notif.setUserByUserIdUser(new UserEntity());
        notif.setAdByAdIdAd(new AdEntity());

        notifDTO.setIdNotif(1);
        notifDTO.setTitle("Title");
        notifDTO.setSubject("Subject");
        notifDTO.setStatus("Status");
        notifDTO.setCleared(1);
        notifDTO.setUserByUserIdUser(1);
        notifDTO.setDate(new Timestamp(System.currentTimeMillis()));
        notifDTO.setSenderIdUser(1);
        notifDTO.setAdByAdIdAd(1);
        notifDTO.setBody("Body");
        notifDTO.setAdditive("Additive");

        for(int i = 0; i < 5; i++) entities.add(notif);
    }

    @Test
    void getAll() throws Exception {
        when(notifServiceMock.getAll()).thenReturn(entities);
        mockMvc.perform(get("/notif/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        when(notifServiceMock.getById(1)).thenReturn(notif);
        mockMvc.perform(get("/notif/getById/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void getAllbyUser() throws Exception {
        when(notifServiceMock.getAllbyUser(1)).thenReturn(entities);
        mockMvc.perform(get("/notif/getAllbyUser/{userid}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void clearPush() throws Exception {
        mockMvc.perform(get("/notif/clearPush/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void clearNotif() throws Exception {
        mockMvc.perform(get("/notif/clearNotif/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void clearAllByUser() throws Exception {
        mockMvc.perform(get("/notif/clearAllByUser/{userid}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void saveNotif() throws Exception {
        when(userServiceMock.getById(notifDTO.getUserByUserIdUser())).thenReturn(user);
        mockMvc.perform(post("/notif/save")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(notifDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}