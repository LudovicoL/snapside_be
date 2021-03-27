package com.unisalento.snapside.restcontrollers;

import com.unisalento.snapside.adapters.ItemAdapter;
import com.unisalento.snapside.iservices.ICategoryService;
import com.unisalento.snapside.iservices.IItemService;
import com.unisalento.snapside.models.CategoryDTO;
import com.unisalento.snapside.models.ItemDTO;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class ItemRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    IItemService itemServiceMock;
    @Mock
    ICategoryService categoryServiceMock;

    private ItemEntity item = new ItemEntity();
    private ItemAdapter itemAdapter = new ItemAdapter();
    private ObjectMapper mapper = new ObjectMapper();
    private ItemDTO itemDTO = new ItemDTO();
    private List<ItemEntity> entities = new ArrayList<>();
    private CategoryEntity category = new CategoryEntity();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ItemRestController(itemServiceMock, categoryServiceMock))
                .build();
        item.setIdItem(1);
        item.setItemName("Name");
        item.setDescriptionItem("Description");
        item.setCategoryByCategoryIdCategory(new CategoryEntity());

        itemDTO.setIdItem(1);
        itemDTO.setName("Name");
        itemDTO.setDescription("Description");
        itemDTO.setCategory_idcategory(1);

        for(int i = 0; i < 5; i++) entities.add(item);
    }


    @Test
    void getAll() throws Exception {
        when(itemServiceMock.getAll()).thenReturn(entities);
        mockMvc.perform(get("/item/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        when(itemServiceMock.getById(1)).thenReturn(item);
        mockMvc.perform(get("/item/getById/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void getItemFromAd() throws Exception {
        when(itemServiceMock.getItemFromAd(1)).thenReturn(item);
        mockMvc.perform(get("/item/getItemFromAd/{idAd}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void saveItem() throws Exception {
        when(categoryServiceMock.getById(itemDTO.getCategory_idcategory())).thenReturn(category);
        when(itemServiceMock.saveAndFlush(item)).thenReturn(item);
        mockMvc.perform(post("/item/save")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(itemDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}