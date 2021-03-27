package com.unisalento.snapside.services;

import com.unisalento.snapside.exceptions.NotifNotFoundException;
import com.unisalento.snapside.repositories.NotifRepository;
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

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class NotifServiceTest {

    @Mock
    private NotifRepository notifRepositoryMock;
    @InjectMocks
    private NotifService notifService;

    private NotifEntity notif = new NotifEntity();
    private NotifEntity notif1 = new NotifEntity();
    private NotifEntity notif2 = new NotifEntity();

    @BeforeEach
    void setUp() {
        notif.setIdNotif(1);
        notif.setTitle("Title");
        notif.setSubject("Subject");
        notif.setStatus("Status");
        notif.setCleared(1);
        notif.setDate(new Timestamp(System.currentTimeMillis()));
        notif.setSenderIdUser(1);
        notif.setBody("Body");
        notif.setAdditive("Additive");
        notif.setUserByUserIdUser(new UserEntity());
        notif.setAdByAdIdAd(new AdEntity());

        notif1.setIdNotif(2);
        notif1.setTitle("Title1");
        notif1.setSubject("Subject1");
        notif1.setStatus("Status1");
        notif1.setCleared(2);
        notif1.setDate(new Timestamp(System.currentTimeMillis()));
        notif1.setSenderIdUser(1);
        notif1.setBody("Body1");
        notif1.setAdditive("Additive1");
        notif1.setUserByUserIdUser(new UserEntity());
        notif1.setAdByAdIdAd(new AdEntity());

        notif2.setIdNotif(3);
        notif.setTitle("Title2");
        notif.setSubject("Subject2");
        notif.setStatus("Status2");
        notif.setCleared(3);
        notif.setDate(new Timestamp(System.currentTimeMillis()));
        notif.setSenderIdUser(1);
        notif.setBody("Body2");
        notif.setAdditive("Additive2");
        notif.setUserByUserIdUser(new UserEntity());
        notif.setAdByAdIdAd(new AdEntity());
    }

    @Test
    void getAll() throws NotifNotFoundException {
        when(notifRepositoryMock.findAll()).thenReturn(Arrays.asList(notif1, notif2));
        List<NotifEntity> notif0 = notifService.getAll();

        assertEquals(notif1.getIdNotif(), notif0.get(0).getIdNotif());
        assertEquals(notif1.getTitle(), notif0.get(0).getTitle());
        assertEquals(notif1.getSubject(), notif0.get(0).getSubject());
        assertEquals(notif2.getIdNotif(), notif0.get(1).getIdNotif());
        assertEquals(notif2.getTitle(), notif0.get(1).getTitle());
        assertEquals(notif2.getSubject(), notif0.get(1).getSubject());
    }

    @Test
    void getById() throws NotifNotFoundException {
        when(notifRepositoryMock.getOne(1)).thenReturn(notif);
        NotifEntity notif0 = notifService.getById(notif.getIdNotif());
        assertEquals(notif.getIdNotif(), notif0.getIdNotif());
        assertEquals(notif.getTitle(), notif0.getTitle());
        assertEquals(notif.getSubject(), notif0.getSubject());
    }

    @Test
    void save() {
        when(notifRepositoryMock.save(Mockito.any(NotifEntity.class))).thenReturn(notif1);
        NotifEntity notif0 = notifService.save(notif1);
        assertEquals(notif1.getIdNotif(), notif0.getIdNotif());
        assertEquals(notif1.getTitle(), notif0.getTitle());
        assertEquals(notif1.getSubject(), notif0.getSubject());
    }

    @Test
    void getAllbyUser() {
        UserEntity u = new UserEntity();
        u.setIdUser(1);
        when(notifRepositoryMock.getAllbyUser(1)).thenReturn(Arrays.asList(notif1, notif2));
        List<NotifEntity> notif0 = notifService.getAllbyUser(u.getIdUser());

        assertEquals(notif1.getIdNotif(), notif0.get(0).getIdNotif());
        assertEquals(notif1.getTitle(), notif0.get(0).getTitle());
        assertEquals(notif1.getSubject(), notif0.get(0).getSubject());
        assertEquals(notif2.getIdNotif(), notif0.get(1).getIdNotif());
        assertEquals(notif2.getTitle(), notif0.get(1).getTitle());
        assertEquals(notif2.getSubject(), notif0.get(1).getSubject());
    }

    @Test
    void clearPush() {  // Void
    }

    @Test
    void clearNotif() {  // Void
    }

    @Test
    void clearAllByUser() {  // Void
    }
}