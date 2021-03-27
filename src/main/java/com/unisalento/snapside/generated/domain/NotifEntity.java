package com.unisalento.snapside.generated.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "notif", schema = "snapside", catalog = "")
public class NotifEntity {
    private int idNotif;
    private String title;
    private String subject;
    private String status;
    private Integer cleared;
    private Timestamp date;
    private Integer senderIdUser;
    private String body;
    private String additive;
    private UserEntity userByUserIdUser;
    private AdEntity adByAdIdAd;

    @Id
    @Column(name = "id_notif", nullable = false)
    public int getIdNotif() {
        return idNotif;
    }

    public void setIdNotif(int idNotif) {
        this.idNotif = idNotif;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "subject", nullable = true, length = 5000)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 128)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "cleared", nullable = true)
    public Integer getCleared() {
        return cleared;
    }

    public void setCleared(Integer cleared) {
        this.cleared = cleared;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "sender_id_user", nullable = true)
    public Integer getSenderIdUser() {
        return senderIdUser;
    }

    public void setSenderIdUser(Integer senderIdUser) {
        this.senderIdUser = senderIdUser;
    }

    @Basic
    @Column(name = "body", nullable = true, length = 5000)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "additive", nullable = true, length = 45)
    public String getAdditive() {
        return additive;
    }

    public void setAdditive(String additive) {
        this.additive = additive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotifEntity that = (NotifEntity) o;
        return idNotif == that.idNotif &&
                Objects.equals(title, that.title) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(status, that.status) &&
                Objects.equals(cleared, that.cleared) &&
                Objects.equals(date, that.date) &&
                Objects.equals(senderIdUser, that.senderIdUser) &&
                Objects.equals(body, that.body) &&
                Objects.equals(additive, that.additive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNotif, title, subject, status, cleared, date, senderIdUser, body, additive);
    }

    @ManyToOne
    @JoinColumn(name = "user_id_user", referencedColumnName = "id_user", nullable = false)
    public UserEntity getUserByUserIdUser() {
        return userByUserIdUser;
    }

    public void setUserByUserIdUser(UserEntity userByUserIdUser) {
        this.userByUserIdUser = userByUserIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "ad_id_ad", referencedColumnName = "id_ad")
    public AdEntity getAdByAdIdAd() {
        return adByAdIdAd;
    }

    public void setAdByAdIdAd(AdEntity adByAdIdAd) {
        this.adByAdIdAd = adByAdIdAd;
    }
}
