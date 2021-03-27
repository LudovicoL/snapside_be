package com.unisalento.snapside.restcontrollers;

import com.unisalento.snapside.adapters.CategoryAdapter;
import com.unisalento.snapside.iservices.ICategoryService;
import com.unisalento.snapside.models.CategoryDTO;
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
import org.w3c.dom.Attr;

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
class CategoryRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    ICategoryService categoryServiceMock;

    private CategoryEntity category = new CategoryEntity();
    private CategoryAdapter categoryAdapter = new CategoryAdapter();
    private ObjectMapper mapper = new ObjectMapper();
    private CategoryDTO categoryDTO = new CategoryDTO();
    private List<CategoryEntity> entities = new ArrayList<>();


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new CategoryRestController(categoryServiceMock))
                .build();
        category.setIdCategory(1);
        category.setCategoryName("Name");
        category.setDescriptionCat("Description");

        categoryDTO.setIdcategory(1);
        categoryDTO.setCategoryname("Name");
        categoryDTO.setDescription("Description");

        for(int i = 0; i < 5; i++) entities.add(category);
    }

    @Test
    void getAll() throws Exception {
        when(categoryServiceMock.getAll()).thenReturn(entities);
        mockMvc.perform(get("/category/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        when(categoryServiceMock.getById(1)).thenReturn(category);
        mockMvc.perform(get("/category/getById/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void getCategoryFromAd() throws Exception {
        when(categoryServiceMock.getCategoryFromAd(1)).thenReturn(category);
        mockMvc.perform(get("/category/getCategoryFromAd/{idAd}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void saveCategory() throws Exception {
        when(categoryServiceMock.saveAndFlush(category)).thenReturn(category);
        mockMvc.perform(post("/category/save")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(categoryDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(get("/category/delete/{id}", 1))
                .andExpect(status().isOk());
    }
}