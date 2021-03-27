package com.unisalento.snapside.models;

import java.sql.Timestamp;

public class CommentDTO {
    private int idComment;
    private String text;
    private Integer rating;
    private int user_id_user;
    private Integer comment_idcomment;
    private int ad_id_ad;

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    private Timestamp creationDate;

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public int getUser_id_user() {
        return user_id_user;
    }

    public void setUser_id_user(int user_id_user) {
        this.user_id_user = user_id_user;
    }

    public Integer getComment_idcomment() {
        return comment_idcomment;
    }

    public void setComment_idcomment(Integer comment_idcomment) {
        this.comment_idcomment = comment_idcomment;
    }

    public int getAd_id_ad() {
        return ad_id_ad;
    }

    public void setAd_id_ad(int ad_id_ad) {
        this.ad_id_ad = ad_id_ad;
    }
}
