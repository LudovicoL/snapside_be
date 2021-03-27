package com.unisalento.snapside.generated.domain;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "snapside", catalog = "")
public class UserEntity {
    private int idUser;
    private String name;
    private String surname;
    private Date dob;
    private String email;
    private String username;
    private String password;
    private String address;
    private Byte enabled;
    private Byte online;
    private String userType;
    private String phone;
    private byte[] userImg;
    private Timestamp lastAccess;
    private Collection<AdEntity> adsByIdUser;
    private Collection<BenefitEntity> benefitsByIdUser;
    private Collection<CommentEntity> commentsByIdUser;
    private Collection<NotifEntity> notifsByIdUser;

    @Id
    @Column(name = "id_user", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 128)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname", nullable = true, length = 255)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "dob", nullable = true)
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "enabled", nullable = true)
    public Byte getEnabled() {
        return enabled;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "online", nullable = true)
    public Byte getOnline() {
        return online;
    }

    public void setOnline(Byte online) {
        this.online = online;
    }

    @Basic
    @Column(name = "user_type", nullable = true, length = 45)
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 128)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "user_img", nullable = true)
    public byte[] getUserImg() {
        return userImg;
    }

    public void setUserImg(byte[] userImg) {
        this.userImg = userImg;
    }

    @Basic
    @Column(name = "last_access", nullable = true)
    public Timestamp getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Timestamp lastAccess) {
        this.lastAccess = lastAccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return idUser == that.idUser &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(dob, that.dob) &&
                Objects.equals(email, that.email) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(address, that.address) &&
                Objects.equals(enabled, that.enabled) &&
                Objects.equals(online, that.online) &&
                Objects.equals(userType, that.userType) &&
                Objects.equals(phone, that.phone) &&
                Arrays.equals(userImg, that.userImg) &&
                Objects.equals(lastAccess, that.lastAccess);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idUser, name, surname, dob, email, username, password, address, enabled, online, userType, phone, lastAccess);
        result = 31 * result + Arrays.hashCode(userImg);
        return result;
    }

    @OneToMany(mappedBy = "userByUserIdSeller")
    public Collection<AdEntity> getAdsByIdUser() {
        return adsByIdUser;
    }

    public void setAdsByIdUser(Collection<AdEntity> adsByIdUser) {
        this.adsByIdUser = adsByIdUser;
    }

    @OneToMany(mappedBy = "userByUserIdUser")
    public Collection<BenefitEntity> getBenefitsByIdUser() {
        return benefitsByIdUser;
    }

    public void setBenefitsByIdUser(Collection<BenefitEntity> benefitsByIdUser) {
        this.benefitsByIdUser = benefitsByIdUser;
    }

    @OneToMany(mappedBy = "userByUserIdUser")
    public Collection<CommentEntity> getCommentsByIdUser() {
        return commentsByIdUser;
    }

    public void setCommentsByIdUser(Collection<CommentEntity> commentsByIdUser) {
        this.commentsByIdUser = commentsByIdUser;
    }

    @OneToMany(mappedBy = "userByUserIdUser")
    public Collection<NotifEntity> getNotifsByIdUser() {
        return notifsByIdUser;
    }

    public void setNotifsByIdUser(Collection<NotifEntity> notifsByIdUser) {
        this.notifsByIdUser = notifsByIdUser;
    }

    private String token;

    @Basic
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
