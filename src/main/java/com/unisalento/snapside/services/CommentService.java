package com.unisalento.snapside.services;

import com.unisalento.snapside.exceptions.CommentNotFoundException;
import com.unisalento.snapside.generated.domain.CommentEntity;
import com.unisalento.snapside.repositories.CommentRepository;
import com.unisalento.snapside.iservices.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService implements ICommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<CommentEntity> getAll() throws CommentNotFoundException {
        // TODO Auto-generated method stub
        return commentRepository.findAll();
    }

    @Override
    public CommentEntity getById(int id) throws CommentNotFoundException {
        // TODO Auto-generated method stub
        return commentRepository.getOne(id);
    }

    @Override
    public List<CommentEntity> getAllCommentsFromAd(int idAd) throws CommentNotFoundException {
        // TODO Auto-generated method stub
        return commentRepository.getAllCommentsFromAd(idAd);
    }

    @Override
    public CommentEntity save(CommentEntity comment) {
        // TODO Auto-generated method stub
        return commentRepository.save(comment);
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
        commentRepository.delete(id);
    }

}

