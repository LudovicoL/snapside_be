package com.unisalento.snapside.services;

import com.unisalento.snapside.exceptions.MediaNotFoundException;
import com.unisalento.snapside.repositories.MediaRepository;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class MediaServiceTest {

    @Mock
    private MediaRepository mediaRepositoryMock;
    @InjectMocks
    private MediaService mediaService;

    private MediaEntity media = new MediaEntity();
    private MediaEntity media1 = new MediaEntity();
    private MediaEntity media2 = new MediaEntity();

    @BeforeEach
    void setUp() {
        media.setIdMedia(1);
        media.setDefaultImg(true);
        media.setMediaName("Image");
        media.setContent("".getBytes());
        media.setAdByAdIdAd(new AdEntity());

        media1.setIdMedia(2);
        media1.setDefaultImg(true);
        media1.setMediaName("Image1");
        media1.setContent("".getBytes());
        media1.setAdByAdIdAd(new AdEntity());

        media2.setIdMedia(3);
        media2.setDefaultImg(true);
        media2.setMediaName("Image2");
        media2.setContent("".getBytes());
        media2.setAdByAdIdAd(new AdEntity());
    }

    @Test
    void getAll() throws MediaNotFoundException {
        when(mediaRepositoryMock.findAll()).thenReturn(Arrays.asList(media1, media2));
        List<MediaEntity> media0 = mediaService.getAll();

        assertEquals(media1.getIdMedia(), media0.get(0).getIdMedia());
        assertEquals(media1.getMediaName(), media0.get(0).getMediaName());
        assertEquals(media2.getIdMedia(), media0.get(1).getIdMedia());
        assertEquals(media2.getMediaName(), media0.get(1).getMediaName());
    }

    @Test
    void getById() throws MediaNotFoundException {
        when(mediaRepositoryMock.getOne(1)).thenReturn(media);
        MediaEntity media0 = mediaService.getById(media.getIdMedia());
        assertEquals(media.getIdMedia(), media0.getIdMedia());
        assertEquals(media.getMediaName(), media0.getMediaName());
    }

    @Test
    void save() {
        when(mediaRepositoryMock.save(Mockito.any(MediaEntity.class))).thenReturn(media1);
        MediaEntity media0 = mediaService.save(media1);
        assertEquals(media1.getIdMedia(), media0.getIdMedia());
        assertEquals(media1.getMediaName(), media0.getMediaName());
    }

    @Test
    void getAllByAd() throws MediaNotFoundException {
        AdEntity ad = new AdEntity();
        ad.setIdAd(1);

        when(mediaRepositoryMock.getAllByAd(1)).thenReturn(Arrays.asList(media1, media2));
        List<MediaEntity> media0 = mediaService.getAllByAd(ad.getIdAd());
        assertEquals(media1.getIdMedia(), media0.get(0).getIdMedia());
        assertEquals(media1.getMediaName(), media0.get(0).getMediaName());
        assertEquals(media2.getIdMedia(), media0.get(1).getIdMedia());
        assertEquals(media2.getMediaName(), media0.get(1).getMediaName());

    }

    @Test
    void resetMedia() {
    }
}