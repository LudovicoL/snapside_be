package com.unisalento.snapside.services;

import com.unisalento.snapside.exceptions.CommentNotFoundException;
import com.unisalento.snapside.repositories.CommentRepository;
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
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepositoryMock;
    @InjectMocks
    private CommentService commentService;

    private CommentEntity comment = new CommentEntity();
    private CommentEntity comment1 = new CommentEntity();
    private CommentEntity comment2 = new CommentEntity();

    @BeforeEach
    void setUp() {
        comment.setIdComment(1);
        comment.setText("Text");
        comment.setRating(1);
        comment.setCommentIdComment(1);
        comment.setCreationDate(new Timestamp(System.currentTimeMillis()));
        comment.setUserByUserIdUser(new UserEntity());
        comment.setAdByAdIdAd(new AdEntity());

        comment1.setIdComment(2);
        comment1.setText("Text1");
        comment1.setRating(2);
        comment1.setCommentIdComment(1);
        comment1.setCreationDate(new Timestamp(System.currentTimeMillis()));
        comment1.setUserByUserIdUser(new UserEntity());
        comment1.setAdByAdIdAd(new AdEntity());

        comment2.setIdComment(2);
        comment2.setText("Text2");
        comment2.setRating(2);
        comment2.setCommentIdComment(1);
        comment2.setCreationDate(new Timestamp(System.currentTimeMillis()));
        comment2.setUserByUserIdUser(new UserEntity());
        comment2.setAdByAdIdAd(new AdEntity());
    }

    @Test
    void getAll() throws CommentNotFoundException {
        when(commentRepositoryMock.findAll()).thenReturn(Arrays.asList(comment1, comment2));
        List<CommentEntity> comment0 = commentService.getAll();

        assertEquals(comment1.getIdComment(), comment0.get(0).getIdComment());
        assertEquals(comment1.getText(), comment0.get(0).getText());
        assertEquals(comment1.getRating(), comment0.get(0).getRating());
        assertEquals(comment2.getIdComment(), comment0.get(1).getIdComment());
        assertEquals(comment2.getText(), comment0.get(1).getText());
        assertEquals(comment2.getRating(), comment0.get(1).getRating());
    }

    @Test
    void getById() throws CommentNotFoundException {
        when(commentRepositoryMock.getOne(1)).thenReturn(comment);
        CommentEntity comment0 = commentService.getById(comment.getIdComment());
        assertEquals(comment.getIdComment(), comment0.getIdComment());
        assertEquals(comment.getText(), comment0.getText());
        assertEquals(comment.getRating(), comment0.getRating());
    }

    @Test
    void getAllCommentsFromAd() throws CommentNotFoundException {
        AdEntity ad = new AdEntity();
        ad.setIdAd(1);
        when(commentRepositoryMock.getAllCommentsFromAd(1)).thenReturn(Arrays.asList(comment1, comment2));
        List<CommentEntity> comment0 = commentService.getAllCommentsFromAd(ad.getIdAd());
        assertEquals(comment1.getIdComment(), comment0.get(0).getIdComment());
        assertEquals(comment1.getText(), comment0.get(0).getText());
        assertEquals(comment1.getRating(), comment0.get(0).getRating());
        assertEquals(comment2.getIdComment(), comment0.get(1).getIdComment());
        assertEquals(comment2.getText(), comment0.get(1).getText());
        assertEquals(comment2.getRating(), comment0.get(1).getRating());
    }

    @Test
    void save() {
        when(commentRepositoryMock.save(Mockito.any(CommentEntity.class))).thenReturn(comment);
        CommentEntity comment0 = commentService.save(comment);
        assertEquals(comment.getIdComment(), comment0.getIdComment());
        assertEquals(comment.getText(), comment0.getText());
        assertEquals(comment.getRating(), comment0.getRating());
    }

    @Test
    void delete() {
    }
}