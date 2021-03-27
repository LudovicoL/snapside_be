package com.unisalento.snapside.models;

import java.sql.Timestamp;

public class BenefitDTO {
    private int idBenefit;
    private Timestamp checkinDate;
    private Timestamp checkoutDate;
    private Integer interested;
    private String paymentType;
    private Double paidAmount;
    private Boolean paid;
    private int user_id_user;
    private int ad_id_ad;
    private int interested_seller_side;

    public int getInterested_seller_side() {
        return interested_seller_side;
    }

    public void setInterested_seller_side(int interested_seller_side) {
        this.interested_seller_side = interested_seller_side;
    }

    public int getIdBenefit() {
        return idBenefit;
    }

    public void setIdBenefit(int idBenefit) {
        this.idBenefit = idBenefit;
    }

    public Timestamp getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Timestamp checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Timestamp getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Timestamp checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Integer getInterested() {
        return interested;
    }

    public void setInterested(Integer interested) {
        this.interested = interested;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public int getUser_id_user() {
        return user_id_user;
    }

    public void setUser_id_user(int user_id_user) {
        this.user_id_user = user_id_user;
    }

    public int getAd_id_ad() {
        return ad_id_ad;
    }

    public void setAd_id_ad(int ad_id_ad) {
        this.ad_id_ad = ad_id_ad;
    }
}
