package com.unisalento.snapside.restcontrollers;

import com.unisalento.snapside.adapters.AdAdapter;
import com.unisalento.snapside.iservices.IAdService;
import com.unisalento.snapside.iservices.IBenefitService;
import com.unisalento.snapside.iservices.IItemService;
import com.unisalento.snapside.iservices.IUserService;
import com.unisalento.snapside.models.AdDTO;
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
class AdRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    IAdService adServiceMock;
    @Mock
    IUserService userServiceMock;
    @Mock
    IItemService itemServiceMock;
    @Mock
    IBenefitService benefitServiceMock;

    private AdEntity ad = new AdEntity();
    private AdAdapter adAdapter = new AdAdapter();
    private ObjectMapper mapper = new ObjectMapper();
    private AdDTO adDTO = new AdDTO();
    private List<AdEntity> entities = new ArrayList<>();
    private UserEntity user = new UserEntity();
    private ItemEntity item = new ItemEntity();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new AdRestController(adServiceMock, userServiceMock, itemServiceMock, benefitServiceMock))
                .build();
        ad.setIdAd(1);
        ad.setTitle("Titolo");
        ad.setDescription("Descrizione");
        ad.setSellPrice(1.2);
        ad.setAddress("Address");
        ad.setCoordinates("Coordinate");
        ad.setApproved(1);
        ad.setActive(true);
        ad.setBeginDate(new Timestamp(System.currentTimeMillis()));
        ad.setEndDate(new Timestamp(System.currentTimeMillis()));
        ad.setAdType("Tipo");
        ad.setFiles("".getBytes());
        ad.setDeleted("Deleted");
        ad.setLastEdit(new Timestamp(System.currentTimeMillis()));
        ad.setCreationDate(new Timestamp(System.currentTimeMillis()));
        ad.setUserByUserIdSeller(new UserEntity());
        ad.setItemByItemIdItem(new ItemEntity());

        adDTO.setIdAd(1);
        adDTO.setTitle("Titolo");
        adDTO.setDescription("Descrizione");
        adDTO.setSellPrice(1.2);
        adDTO.setAddress("Address");
        adDTO.setCoordinates("Coordinate");
        adDTO.setApproved(1);
        adDTO.setActive(true);
        adDTO.setBeginDate(new Timestamp(System.currentTimeMillis()));
        adDTO.setEndDate(new Timestamp(System.currentTimeMillis()));
        adDTO.setAdType("Tipo");
        adDTO.setUser_id_seller(1);
        adDTO.setFiles("".getBytes());
        adDTO.setLastEdit(new Timestamp(System.currentTimeMillis()));
        adDTO.setCreationDate(new Timestamp(System.currentTimeMillis()));

        for(int i = 0; i < 5; i++) entities.add(ad);
    }

    @Test
    void getAll() throws Exception {
        when(adServiceMock.getAll()).thenReturn(entities);
        mockMvc.perform(get("/ad/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllbySeller() throws Exception {
        when(adServiceMock.getAllbySeller(1)).thenReturn(entities);
        mockMvc.perform(get("/ad/getAllbySeller/{seller_id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void getAllPublished() throws Exception {
        when(adServiceMock.getAllPublished()).thenReturn(entities);
        mockMvc.perform(get("/ad/getAllPublished"))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        when(adServiceMock.getById(1)).thenReturn(ad);
        mockMvc.perform(get("/ad/getById/{id}",1))
                .andExpect(status().isOk());
    }

    @Test
    void getLastBySeller() throws Exception {
        when(adServiceMock.getLastBySeller(1)).thenReturn(ad);
        mockMvc.perform(get("/ad/getLastBySeller/{id}",1))
                .andExpect(status().isOk());
    }

    @Test
    void getByTitle() throws Exception {
        when(adServiceMock.getAllByTitle("title")).thenReturn(entities);
        mockMvc.perform(get("/ad/getAllByTitle/{title}","title"))
                .andExpect(status().isOk());
    }

    @Test
    void saveAd() throws Exception {
        when(userServiceMock.getById(adDTO.getUser_id_seller())).thenReturn(user);
        when(itemServiceMock.getById(adDTO.getItem_id_item())).thenReturn(item);
        mockMvc.perform(post("/ad/save")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(adDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void editAd() throws Exception {
        when(userServiceMock.getById(adDTO.getUser_id_seller())).thenReturn(user);
        when(itemServiceMock.getById(adDTO.getItem_id_item())).thenReturn(item);
        mockMvc.perform(post("/ad/editAd")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(adDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(get("/ad/logicDeleteAd/{id}",1))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAd() throws Exception {
        mockMvc.perform(get("/ad/deleteAd/{id}",1))
                .andExpect(status().isOk());
    }

    @Test
    void updateLastAccess() throws Exception {
        mockMvc.perform(post("/ad/approve/{idAd}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(adDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}