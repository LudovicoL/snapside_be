package com.unisalento.snapside.services;

import com.unisalento.snapside.exceptions.AdNotFoundException;
import com.unisalento.snapside.repositories.AdRepository;
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
class AdServiceTest {

    @Mock
    private AdRepository adRepositoryMock;
    @InjectMocks
    private AdService adService;

    private AdEntity ad = new AdEntity();
    private AdEntity ad1 = new AdEntity();
    private AdEntity ad2 = new AdEntity();

    @BeforeEach
    public void setUp() {
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

        ad1.setIdAd(2);
        ad1.setTitle("Titolo1");
        ad1.setDescription("Descrizione1");
        ad1.setSellPrice(1.2);
        ad1.setAddress("Address1");
        ad1.setCoordinates("Coordinate1");
        ad1.setApproved(1);
        ad1.setActive(true);
        ad1.setBeginDate(new Timestamp(System.currentTimeMillis()));
        ad1.setEndDate(new Timestamp(System.currentTimeMillis()));
        ad1.setAdType("Tipo1");
        ad1.setFiles("".getBytes());
        ad1.setDeleted("Deleted1");
        ad1.setLastEdit(new Timestamp(System.currentTimeMillis()));
        ad1.setCreationDate(new Timestamp(System.currentTimeMillis()));
        ad1.setUserByUserIdSeller(new UserEntity());
        ad1.setItemByItemIdItem(new ItemEntity());

        ad2.setIdAd(3);
        ad2.setTitle("Titolo2");
        ad2.setDescription("Descrizione2");
        ad2.setSellPrice(1.2);
        ad2.setAddress("Address2");
        ad2.setCoordinates("Coordinate2");
        ad2.setApproved(1);
        ad2.setActive(true);
        ad2.setBeginDate(new Timestamp(System.currentTimeMillis()));
        ad2.setEndDate(new Timestamp(System.currentTimeMillis()));
        ad2.setAdType("Tipo2");
        ad2.setFiles("".getBytes());
        ad2.setDeleted("Deleted2");
        ad2.setLastEdit(new Timestamp(System.currentTimeMillis()));
        ad2.setCreationDate(new Timestamp(System.currentTimeMillis()));
        ad2.setUserByUserIdSeller(new UserEntity());
        ad2.setItemByItemIdItem(new ItemEntity());
    }

    @Test
    void getAll() throws AdNotFoundException {
        when(adRepositoryMock.findAll()).thenReturn(Arrays.asList(ad1, ad2));
        List<AdEntity> ad0 = adService.getAll();

        assertEquals(ad1.getIdAd(), ad0.get(0).getIdAd());
        assertEquals(ad1.getTitle(), ad0.get(0).getTitle());
        assertEquals(ad1.getDescription(), ad0.get(0).getDescription());
        assertEquals(ad2.getIdAd(), ad0.get(1).getIdAd());
        assertEquals(ad2.getTitle(), ad0.get(1).getTitle());
        assertEquals(ad2.getDescription(), ad0.get(1).getDescription());
    }

    @Test
    void getAllbySeller() throws AdNotFoundException {
        UserEntity u = new UserEntity();
        u.setIdUser(1);
        when(adRepositoryMock.getAllbySeller(1)).thenReturn(Arrays.asList(ad1, ad2));
        List<AdEntity> ad0 = adService.getAllbySeller(u.getIdUser());

        assertEquals(ad1.getIdAd(), ad0.get(0).getIdAd());
        assertEquals(ad1.getTitle(), ad0.get(0).getTitle());
        assertEquals(ad1.getDescription(), ad0.get(0).getDescription());
        assertEquals(ad2.getIdAd(), ad0.get(1).getIdAd());
        assertEquals(ad2.getTitle(), ad0.get(1).getTitle());
        assertEquals(ad2.getDescription(), ad0.get(1).getDescription());
    }

    @Test
    void getById() throws AdNotFoundException {
        when(adRepositoryMock.getOne(1)).thenReturn(ad);
        AdEntity ad0 = adService.getById(ad.getIdAd());
        assertEquals(ad.getIdAd(), ad0.getIdAd());
        assertEquals(ad.getTitle(), ad0.getTitle());
        assertEquals(ad.getDescription(), ad0.getDescription());
    }

    @Test
    void save() {
        when(adRepositoryMock.save(Mockito.any(AdEntity.class))).thenReturn(ad);
        AdEntity ad0 = adService.save(ad);
        assertEquals(ad.getIdAd(), ad0.getIdAd());
        assertEquals(ad.getTitle(), ad0.getTitle());
        assertEquals(ad.getDescription(), ad0.getDescription());
    }

    @Test
    void getAllByTitle() {
        when(adRepositoryMock.getAllByTitle("Titolo")).thenReturn(Arrays.asList(ad1, ad2));
        List<AdEntity> ad0 = adService.getAllByTitle(ad.getTitle());
        assertEquals(ad1.getIdAd(), ad0.get(0).getIdAd());
        assertEquals(ad1.getTitle(), ad0.get(0).getTitle());
        assertEquals(ad1.getDescription(), ad0.get(0).getDescription());
        assertEquals(ad2.getIdAd(), ad0.get(1).getIdAd());
        assertEquals(ad2.getTitle(), ad0.get(1).getTitle());
        assertEquals(ad2.getDescription(), ad0.get(1).getDescription());
    }

    @Test
    void logicDeleteAd() {
    }

    @Test
    void deleteAd() {
    }

    @Test
    void getLastBySeller() {
        UserEntity u = new UserEntity();
        u.setIdUser(1);
        when(adRepositoryMock.getLastBySeller(1)).thenReturn(ad);
        AdEntity ad0 = adService.getLastBySeller(u.getIdUser());
        assertEquals(ad.getIdAd(), ad0.getIdAd());
        assertEquals(ad.getTitle(), ad0.getTitle());
        assertEquals(ad.getDescription(), ad0.getDescription());

    }

    @Test
    void getAllPublished() {
        when(adRepositoryMock.getAllPublished()).thenReturn(Arrays.asList(ad1, ad2));
        List<AdEntity> ad0 = adService.getAllPublished();

        assertEquals(ad1.getIdAd(), ad0.get(0).getIdAd());
        assertEquals(ad1.getTitle(), ad0.get(0).getTitle());
        assertEquals(ad1.getDescription(), ad0.get(0).getDescription());
        assertEquals(ad2.getIdAd(), ad0.get(1).getIdAd());
        assertEquals(ad2.getTitle(), ad0.get(1).getTitle());
        assertEquals(ad2.getDescription(), ad0.get(1).getDescription());
    }

    @Test
    void approve() {
    }

    @Test
    void editAd() {
    }
}