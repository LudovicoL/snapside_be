package com.unisalento.snapside.services;

import com.unisalento.snapside.exceptions.AttributeNotFoundException;
import com.unisalento.snapside.repositories.AttributeRepository;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import com.unisalento.snapside.generated.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class AttributeServiceTest {

    @Mock
    private AttributeRepository attributeRepositoryMock;
    @InjectMocks
    private AttributeService attributeService;

    private AttributeEntity attribute = new AttributeEntity();
    private AttributeEntity attribute1 = new AttributeEntity();
    private AttributeEntity attribute2 = new AttributeEntity();

    @BeforeEach
    public void setUp() {
        attribute.setIdAttribute(1);
        attribute.setAttribName("Name");
        attribute.setCategoryByCategoryIdCategory(new CategoryEntity());
        attribute.setItemByItemIdItem(new ItemEntity());
    }

    @Test
    void getAll() throws AttributeNotFoundException {
        when(attributeRepositoryMock.findAll()).thenReturn(Arrays.asList(attribute1, attribute2));
        List<AttributeEntity> attribute0 = attributeService.getAll();

        assertEquals(attribute1.getIdAttribute(), attribute0.get(0).getIdAttribute());
        assertEquals(attribute1.getAttribName(), attribute0.get(0).getAttribName());
        assertEquals(attribute2.getIdAttribute(), attribute0.get(1).getIdAttribute());
        assertEquals(attribute2.getAttribName(), attribute0.get(1).getAttribName());
    }

    @Test
    void getById() throws AttributeNotFoundException {
        when(attributeRepositoryMock.getOne(1)).thenReturn(attribute);
        AttributeEntity attribute0 = attributeService.getById(attribute.getIdAttribute());
        assertEquals(attribute.getIdAttribute(), attribute0.getIdAttribute());
        assertEquals(attribute.getAttribName(), attribute0.getAttribName());
    }

    @Test
    void save() {
        when(attributeRepositoryMock.save(Mockito.any(AttributeEntity.class))).thenReturn(attribute1);
        AttributeEntity attribute0 = attributeService.save(attribute1);
        assertEquals(attribute1.getIdAttribute(), attribute0.getIdAttribute());
        assertEquals(attribute1.getAttribName(), attribute0.getAttribName());
    }

    @Test
    void getAllFromAd() throws AttributeNotFoundException {
        AdEntity ad = new AdEntity();
        ad.setIdAd(1);

        when(attributeRepositoryMock.getAllFromAd(1)).thenReturn(Arrays.asList(attribute1, attribute2));
        List<AttributeEntity> attribute0 = attributeService.getAllFromAd(ad.getIdAd());
        assertEquals(attribute1.getIdAttribute(), attribute0.get(0).getIdAttribute());
        assertEquals(attribute1.getAttribName(), attribute0.get(0).getAttribName());
        assertEquals(attribute2.getIdAttribute(), attribute0.get(1).getIdAttribute());
        assertEquals(attribute2.getAttribName(), attribute0.get(1).getAttribName());
    }

    @Test
    void saveAndFlush() {
        when(attributeRepositoryMock.saveAndFlush(Mockito.any(AttributeEntity.class))).thenReturn(attribute1);
        AttributeEntity attribute0 = attributeService.saveAndFlush(attribute1);
        assertEquals(attribute1.getIdAttribute(), attribute0.getIdAttribute());
        assertEquals(attribute1.getAttribName(), attribute0.getAttribName());
    }
}