package com.unisalento.snapside.adapters;

import com.unisalento.snapside.generated.domain.AdEntity;
import com.unisalento.snapside.generated.domain.CommentEntity;
import com.unisalento.snapside.generated.domain.UserEntity;
import com.unisalento.snapside.models.CommentDTO;

public class CommentAdapter {
    public static CommentEntity CommentDTOToCommentEntity(CommentDTO commentDTO, AdEntity ad, UserEntity commenter) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setText(commentDTO.getText());
        commentEntity.setRating(commentDTO.getRating());
        commentEntity.setAdByAdIdAd(ad);
        commentEntity.setUserByUserIdUser(commenter);
        commentEntity.setCommentIdComment(commentDTO.getComment_idcomment());
        commentEntity.setCreationDate(commentDTO.getCreationDate());
        return commentEntity;
    }

    public static CommentDTO CommentEntityToCommentDTO(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setText(commentEntity.getText());
        commentDTO.setRating(commentEntity.getRating());
        commentDTO.setIdComment(commentEntity.getIdComment());
        commentDTO.setComment_idcomment(commentEntity.getCommentIdComment());
        commentDTO.setCreationDate(commentEntity.getCreationDate());
        commentDTO.setUser_id_user(commentEntity.getUserByUserIdUser().getIdUser());

        return commentDTO;
    }
}
