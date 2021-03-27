package com.unisalento.snapside.restcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unisalento.snapside.adapters.AdHasAttributeAdapter;
import com.unisalento.snapside.generated.domain.AdEntity;
import com.unisalento.snapside.generated.domain.AdHasAttributeEntity;
import com.unisalento.snapside.generated.domain.AttributeEntity;
import com.unisalento.snapside.iservices.IAdHasAttributeService;
import com.unisalento.snapside.iservices.IAdService;
import com.unisalento.snapside.iservices.IAttributeService;
import com.unisalento.snapside.iservices.ICategoryService;
import com.unisalento.snapside.models.AdHasAttributeDTO;
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

import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class AdHasAttributeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    IAdHasAttributeService adHasAttributeServiceServiceMock;
    @Mock
    ICategoryService categoryServiceMock;
    @Mock
    IAdService adServiceMock;
    @Mock
    IAttributeService attributeServiceMock;

    private AdHasAttributeEntity aha = new AdHasAttributeEntity();
    private AdHasAttributeAdapter ahaAdapter = new AdHasAttributeAdapter();
    private ObjectMapper mapper = new ObjectMapper();
    private AdHasAttributeDTO ahaDTO = new AdHasAttributeDTO();
    private List<AdHasAttributeEntity> entities = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new AdHasAttributeRestController(adHasAttributeServiceServiceMock, categoryServiceMock, adServiceMock, attributeServiceMock))
                .build();
        aha.setIdHasAttribute(1);
        aha.setAttributeValue("Cilindrata");
        aha.setAttributeByAttributeIdAttribute(new AttributeEntity());
        aha.setAdByAdIdAd(new AdEntity());

        ahaDTO.setIdHasAttribute(1);
        ahaDTO.setAttributeValue("Cilindrata");
        ahaDTO.setAttributeByAttributeIdAttribute(1);
        ahaDTO.setAdByAdIdAd(1);

        for(int i = 0; i < 5; i++) entities.add(aha);

    }

    @Test
    void getAll() throws Exception {
        when(adHasAttributeServiceServiceMock.getAll()).thenReturn(entities);
        mockMvc.perform(get("/hasattrib/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllAttribValuesFromAd() throws Exception {
        when(adHasAttributeServiceServiceMock.getAllAttribValuesFromAd(1)).thenReturn(entities);
        mockMvc.perform(get("/hasattrib/getAllAttribValuesFromAd/{idAd}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        when(adHasAttributeServiceServiceMock.getById(1)).thenReturn(aha);
        mockMvc.perform(get("/hasattrib/getById/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void saveAHA() throws Exception {
        when(adHasAttributeServiceServiceMock.getById(ahaDTO.getAttributeByAttributeIdAttribute())).thenReturn(aha);
        when(adHasAttributeServiceServiceMock.getById(ahaDTO.getAdByAdIdAd())).thenReturn(aha);
        mockMvc.perform(post("/hasattrib/save")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(ahaDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void resetMedia() throws Exception {
        mockMvc.perform(post("/hasattrib/resetAttributes/{idAd}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(ahaDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}