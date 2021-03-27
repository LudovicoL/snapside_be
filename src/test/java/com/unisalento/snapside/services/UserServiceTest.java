package com.unisalento.snapside.services;

import com.unisalento.snapside.exceptions.UserNotFoundException;
import com.unisalento.snapside.repositories.UserRepository;
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
class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private UserService userService;

    private UserEntity user = new UserEntity();
    private UserEntity user1 = new UserEntity();
    private UserEntity user2 = new UserEntity();

    @BeforeEach
    void setUp() {
        user.setIdUser(1);
        user.setName("Name");
        user.setSurname("Surname");
        user.setDob(new java.sql.Date(new Long(0)).valueOf("2020-01-01"));
        user.setEmail("email@mail.com");
        user.setUsername("username");
        user.setPassword("password");
        user.setAddress("Address");
        user.setEnabled(true);
        user.setOnline(true);
        user.setUserType("SELLER");
        user.setPhone("phone");
        user.setUserImg("".getBytes());
        user.setLastAccess(new Timestamp(System.currentTimeMillis()));

        user1.setIdUser(2);
        user1.setName("Name1");
        user1.setSurname("Surname1");
        user1.setDob(new java.sql.Date(new Long(0)).valueOf("2020-01-01"));
        user1.setEmail("email1@mail.com");
        user1.setUsername("username1");
        user1.setPassword("password1");
        user1.setAddress("Address1");
        user1.setEnabled(true);
        user1.setOnline(true);
        user1.setUserType("SELLER");
        user1.setPhone("phone1");
        user1.setUserImg("".getBytes());
        user1.setLastAccess(new Timestamp(System.currentTimeMillis()));

        user2.setIdUser(3);
        user2.setName("Name2");
        user2.setSurname("Surname2");
        user2.setDob(new java.sql.Date(new Long(0)).valueOf("2020-01-01"));
        user2.setEmail("email2@mail.com");
        user2.setUsername("username2");
        user2.setPassword("password2");
        user2.setAddress("Address2");
        user2.setEnabled(true);
        user2.setOnline(true);
        user2.setUserType("SELLER");
        user2.setPhone("phone2");
        user2.setUserImg("".getBytes());
        user2.setLastAccess(new Timestamp(System.currentTimeMillis()));
    }

    @Test
    void getAll() throws UserNotFoundException {
        when(userRepositoryMock.findAll()).thenReturn(Arrays.asList(user1, user2));
        List<UserEntity> user0 = userService.getAll();

        assertEquals(user1.getIdUser(), user0.get(0).getIdUser());
        assertEquals(user1.getUsername(), user0.get(0).getUsername());
        assertEquals(user1.getPassword(), user0.get(0).getPassword());
        assertEquals(user2.getIdUser(), user0.get(1).getIdUser());
        assertEquals(user2.getUsername(), user0.get(1).getUsername());
        assertEquals(user2.getPassword(), user0.get(1).getPassword());
    }

    @Test
    void save() {
        when(userRepositoryMock.save(Mockito.any(UserEntity.class))).thenReturn(user1);
        UserEntity user0 = userService.save(user1);
        assertEquals(user1.getIdUser(), user0.getIdUser());
        assertEquals(user1.getUsername(), user0.getUsername());
        assertEquals(user1.getPassword(), user0.getPassword());
    }

    @Test
    void removeUserById() {    // Void
    }

    @Test
    void getById() throws UserNotFoundException {
        when(userRepositoryMock.getOne(1)).thenReturn(user);
        UserEntity user0 = userService.getById(user.getIdUser());
        assertEquals(user.getIdUser(), user0.getIdUser());
        assertEquals(user.getUsername(), user0.getUsername());
        assertEquals(user.getPassword(), user0.getPassword());
    }

    @Test
    void getByUsername() throws UserNotFoundException {
        when(userRepositoryMock.getByUsername("username")).thenReturn(user);
        UserEntity user0 = userService.getByUsername(user.getUsername());
        assertEquals(user.getIdUser(), user0.getIdUser());
        assertEquals(user.getUsername(), user0.getUsername());
        assertEquals(user.getPassword(), user0.getPassword());
    }

    @Test
    void count() {    // Int
    }

    @Test
    void getAllBenefitsAtAd() {
    }

    @Test
    void getUserFromComment() {
        CommentEntity comment = new CommentEntity();
        comment.setIdComment(1);

        when(userRepositoryMock.getUserFromComment(1)).thenReturn(user);
        UserEntity user0 = userService.getUserFromComment(comment.getIdComment());
        assertEquals(user.getIdUser(), user0.getIdUser());
        assertEquals(user.getUsername(), user0.getUsername());
        assertEquals(user.getPassword(), user0.getPassword());
    }

    @Test
    void updateLastAccess() {    // Void
    }

    @Test
    void updateFirebaseToken() {    // Void
    }
}