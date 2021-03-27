package com.unisalento.snapside.services;

import com.unisalento.snapside.exceptions.AdHasAttributeNotFoundException;
import com.unisalento.snapside.repositories.AdHasAttributeRepository;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class AdHasAttributeServiceTest {

    @Mock
    private AdHasAttributeRepository ahaRepositoryMock;
    @InjectMocks
    private AdHasAttributeService ahaService;

    private AdHasAttributeEntity aha = new AdHasAttributeEntity();
    private AdHasAttributeEntity aha1 = new AdHasAttributeEntity();
    private AdHasAttributeEntity aha2 = new AdHasAttributeEntity();

    @BeforeEach
    public void setUp() {
        aha.setIdHasAttribute(1);
        aha.setAttributeValue("Value");
        aha.setAttributeByAttributeIdAttribute(new AttributeEntity());
        aha.setAdByAdIdAd(new AdEntity());

        aha1.setIdHasAttribute(1);
        aha1.setAttributeValue("Value1");
        aha1.setAttributeByAttributeIdAttribute(new AttributeEntity());
        aha1.setAdByAdIdAd(new AdEntity());

        aha2.setIdHasAttribute(1);
        aha2.setAttributeValue("Value2");
        aha2.setAttributeByAttributeIdAttribute(new AttributeEntity());
        aha2.setAdByAdIdAd(new AdEntity());

    }

    @Test
    void getAll() throws AdHasAttributeNotFoundException {
        when(ahaRepositoryMock.findAll()).thenReturn(Arrays.asList(aha1, aha2));
        List<AdHasAttributeEntity> aha0 = ahaService.getAll();

        assertEquals(aha1.getIdHasAttribute(), aha0.get(0).getIdHasAttribute());
        assertEquals(aha1.getAttributeValue(), aha0.get(0).getAttributeValue());
        assertEquals(aha2.getIdHasAttribute(), aha0.get(1).getIdHasAttribute());
        assertEquals(aha2.getAttributeValue(), aha0.get(1).getAttributeValue());
    }

    @Test
    void getById() throws AdHasAttributeNotFoundException {
        when(ahaRepositoryMock.getOne(1)).thenReturn(aha);
        AdHasAttributeEntity aha0 = ahaService.getById(aha.getIdHasAttribute());
        assertEquals(aha.getIdHasAttribute(), aha0.getIdHasAttribute());
        assertEquals(aha.getAttributeValue(), aha0.getAttributeValue());
    }

    @Test
    void save() {
        when(ahaRepositoryMock.save(Mockito.any(AdHasAttributeEntity.class))).thenReturn(aha1);
        AdHasAttributeEntity aha0 = ahaService.save(aha1);
        assertEquals(aha1.getIdHasAttribute(), aha0.getIdHasAttribute());
        assertEquals(aha1.getAttributeValue(), aha0.getAttributeValue());
    }

    @Test
    void resetAttributes() {
    }

    @Test
    void getAllAttribValuesFromAd() throws AdHasAttributeNotFoundException {
        AdEntity ad = new AdEntity();
        ad.setIdAd(1);

        when(ahaRepositoryMock.getAllAttribValuesFromAd(1)).thenReturn(Arrays.asList(aha1, aha2));
        List<AdHasAttributeEntity> aha0 = ahaService.getAllAttribValuesFromAd(ad.getIdAd());
        assertEquals(aha1.getIdHasAttribute(), aha0.get(0).getIdHasAttribute());
        assertEquals(aha1.getAttributeValue(), aha0.get(0).getAttributeValue());
        assertEquals(aha2.getIdHasAttribute(), aha0.get(1).getIdHasAttribute());
        assertEquals(aha2.getAttributeValue(), aha0.get(1).getAttributeValue());

    }
}