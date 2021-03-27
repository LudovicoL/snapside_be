package com.unisalento.snapside.generated.domain;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "media", schema = "snapside", catalog = "")
public class MediaEntity {
    private int idMedia;
    private Boolean defaultImg;
    private String mediaName;
    private byte[] content;
    private AdEntity adByAdIdAd;

    @Id
    @Column(name = "id_media", nullable = false)
    public int getIdMedia() {
        return idMedia;
    }

    public void setIdMedia(int idMedia) {
        this.idMedia = idMedia;
    }

    @Basic
    @Column(name = "default_img", nullable = true)
    public Boolean getDefaultImg() {
        return defaultImg;
    }

    public void setDefaultImg(Boolean defaultImg) {
        this.defaultImg = defaultImg;
    }

    @Basic
    @Column(name = "media_name", nullable = true, length = 2000)
    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    @Basic
    @Column(name = "content", nullable = true)
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaEntity that = (MediaEntity) o;
        return idMedia == that.idMedia &&
                Objects.equals(defaultImg, that.defaultImg) &&
                Objects.equals(mediaName, that.mediaName) &&
                Arrays.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idMedia, defaultImg, mediaName);
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ad_id_ad", referencedColumnName = "id_ad", nullable = false)
    public AdEntity getAdByAdIdAd() {
        return adByAdIdAd;
    }

    public void setAdByAdIdAd(AdEntity adByAdIdAd) {
        this.adByAdIdAd = adByAdIdAd;
    }
}
