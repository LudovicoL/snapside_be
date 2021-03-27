package com.unisalento.snapside.restcontrollers;

import com.unisalento.snapside.adapters.MediaAdapter;
import com.unisalento.snapside.iservices.*;
import com.unisalento.snapside.models.MediaDTO;
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
class MediaRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    IMediaService mediaServiceMock;
    @Mock
    IUserService userServiceMock;
    @Mock
    IItemService itemServiceMock;
    @Mock
    IAdService adServiceMock;
    @Mock
    IBenefitService benefitServiceMock;

    private MediaEntity media = new MediaEntity();
    private MediaAdapter mediaAdapter = new MediaAdapter();
    private ObjectMapper mapper = new ObjectMapper();
    private MediaDTO mediaDTO = new MediaDTO();
    private List<MediaEntity> entities = new ArrayList<>();
    private AdEntity ad = new AdEntity();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new MediaRestController(mediaServiceMock, userServiceMock, itemServiceMock, adServiceMock, benefitServiceMock))
                .build();
        media.setIdMedia(1);
        media.setDefaultImg(true);
        media.setMediaName("Image");
        media.setContent("".getBytes());
        media.setAdByAdIdAd(new AdEntity());

        mediaDTO.setIdMedia(1);
        mediaDTO.setDefaultImg(true);
        mediaDTO.setMediaName("Image");
        mediaDTO.setContent("".getBytes());
        mediaDTO.setAdByAdIdAd(1);

        for(int i = 0; i < 5; i++) entities.add(media);
    }

    @Test
    void getAll() throws Exception {
        when(mediaServiceMock.getAll()).thenReturn(entities);
        mockMvc.perform(get("/media/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllByAd() throws Exception {
        when(mediaServiceMock.getAllByAd(1)).thenReturn(entities);
        mockMvc.perform(get("/media/getAllByAd/{idAd}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        when(mediaServiceMock.getById(1)).thenReturn(media);
        mockMvc.perform(get("/media/getById/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void resetMedia() throws Exception {
        mockMvc.perform(post("/media/resetMedia/{idAd}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(mediaDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void saveMedia() throws Exception {
        when(adServiceMock.getById(mediaDTO.getAdByAdIdAd())).thenReturn(ad);
        mockMvc.perform(post("/media/save")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(mediaDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}