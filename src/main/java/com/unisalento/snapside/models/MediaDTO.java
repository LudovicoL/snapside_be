package com.unisalento.snapside.models;

import com.unisalento.snapside.generated.domain.AdEntity;

public class MediaDTO {
    private int idMedia;
    private Boolean defaultImg;
    private String mediaName;
    private byte[] content;
//    private AdEntity adByAdIdAd;
    private int adByAdIdAd;

    public int getIdMedia() {
        return idMedia;
    }

    public void setIdMedia(int idMedia) {
        this.idMedia = idMedia;
    }

    public Boolean getDefaultImg() {
        return defaultImg;
    }

    public void setDefaultImg(Boolean defaultImg) {
        this.defaultImg = defaultImg;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int getAdByAdIdAd() {
        return adByAdIdAd;
    }

    public void setAdByAdIdAd(int adByAdIdAd) {
        this.adByAdIdAd = adByAdIdAd;
    }
}
