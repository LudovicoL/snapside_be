package com.unisalento.snapside.restcontrollers;

import com.unisalento.snapside.adapters.AttributeAdapter;
import com.unisalento.snapside.iservices.IAttributeService;
import com.unisalento.snapside.iservices.ICategoryService;
import com.unisalento.snapside.iservices.IItemService;
import com.unisalento.snapside.models.AttributeDTO;
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
class AttributeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    IAttributeService attributeServiceMock;
    @Mock
    ICategoryService categoryServiceMock;
    @Mock
    IItemService itemServiceMock;

    private AttributeEntity attribute = new AttributeEntity();
    private AttributeAdapter attributeAdapter = new AttributeAdapter();
    private ObjectMapper mapper = new ObjectMapper();
    private AttributeDTO attributeDTO = new AttributeDTO();
    private List<AttributeEntity> entities = new ArrayList<>();

    private ItemEntity item = new ItemEntity();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new AttributeRestController(attributeServiceMock, categoryServiceMock, itemServiceMock))
                .build();
        attribute.setIdAttribute(1);
        attribute.setAttribName("Name");
        attribute.setCategoryByCategoryIdCategory(new CategoryEntity());
        attribute.setItemByItemIdItem(new ItemEntity());

        attributeDTO.setIdattribute(1);
        attributeDTO.setAttributeName("Name");
        attributeDTO.setAttributeValue("Value");
        attributeDTO.setCategory_idcategory(1);
        attributeDTO.setItemByItemIdItem(1);


        for(int i = 0; i < 5; i++) entities.add(attribute);
    }

    @Test
    void getAll() throws Exception {
        when(attributeServiceMock.getAll()).thenReturn(entities);
        mockMvc.perform(get("/attribute/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllFromAd() throws Exception {
        when(attributeServiceMock.getAllFromAd(1)).thenReturn(entities);
        mockMvc.perform(get("/attribute/getAllFromAd/{idAd}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        when(attributeServiceMock.getById(1)).thenReturn(attribute);
        mockMvc.perform(get("/attribute/getById/{idAd}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void saveAttribute() throws Exception {
        when(itemServiceMock.getById(attributeDTO.getItemByItemIdItem())).thenReturn(item);
        when(attributeServiceMock.saveAndFlush(attribute)).thenReturn(attribute);
        mockMvc.perform(post("/attribute/save")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(attributeDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}