package com.unisalento.snapside.models;

import java.sql.Timestamp;

public class NotifDTO {
    private int idNotif;
    private String title;
    private String subject;
    private String status;
    private Integer cleared;
    private Integer userByUserIdUser;
    private Timestamp date;
    private Integer senderIdUser;
    private Integer adByAdIdAd;
    private String body;
    private String additive;

    public int getIdNotif() {
        return idNotif;
    }

    public void setIdNotif(int idNotif) {
        this.idNotif = idNotif;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCleared() {
        return cleared;
    }

    public void setCleared(Integer cleared) {
        this.cleared = cleared;
    }

    public Integer getUserByUserIdUser() {
        return userByUserIdUser;
    }

    public void setUserByUserIdUser(Integer userByUserIdUser) {
        this.userByUserIdUser = userByUserIdUser;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getAdByAdIdAd() {
        return adByAdIdAd;
    }

    public void setAdByAdIdAd(Integer adByAdIdAd) {
        this.adByAdIdAd = adByAdIdAd;
    }

    public Integer getSenderIdUser() {
        return senderIdUser;
    }

    public void setSenderIdUser(Integer senderIdUser) {
        this.senderIdUser = senderIdUser;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAdditive() {
        return additive;
    }

    public void setAdditive(String additive) {
        this.additive = additive;
    }
}
