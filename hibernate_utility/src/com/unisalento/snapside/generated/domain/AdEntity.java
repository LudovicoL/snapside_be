package com.unisalento.snapside.generated.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ad", schema = "snapside", catalog = "")
public class AdEntity {
    private int idAd;
    private String title;
    private String description;
    private Double sellPrice;
    private String address;
    private String coordinates;
    private Integer approved;
    private Byte active;
    private Timestamp beginDate;
    private Timestamp endDate;
    private String adType;
    private byte[] files;
    private String deleted;
    private Timestamp lastEdit;
    private Timestamp creationDate;
    private UserEntity userByUserIdSeller;
    private ItemEntity itemByItemIdItem;
    private Collection<AdHasAttributeEntity> adHasAttributesByIdAd;
    private Collection<BenefitEntity> benefitsByIdAd;
    private Collection<CommentEntity> commentsByIdAd;
    private Collection<MediaEntity> mediaByIdAd;
    private Collection<NotifEntity> notifsByIdAd;

    @Id
    @Column(name = "id_ad", nullable = false)
    public int getIdAd() {
        return idAd;
    }

    public void setIdAd(int idAd) {
        this.idAd = idAd;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 127)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 5000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "sell_price", nullable = true, precision = 0)
    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 5000)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "coordinates", nullable = true, length = 5000)
    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    @Basic
    @Column(name = "approved", nullable = true)
    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    @Basic
    @Column(name = "active", nullable = true)
    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    @Basic
    @Column(name = "begin_date", nullable = true)
    public Timestamp getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Timestamp beginDate) {
        this.beginDate = beginDate;
    }

    @Basic
    @Column(name = "end_date", nullable = true)
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "ad_type", nullable = true, length = 45)
    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    @Basic
    @Column(name = "files", nullable = true)
    public byte[] getFiles() {
        return files;
    }

    public void setFiles(byte[] files) {
        this.files = files;
    }

    @Basic
    @Column(name = "deleted", nullable = true, length = 45)
    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    @Basic
    @Column(name = "last_edit", nullable = true)
    public Timestamp getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(Timestamp lastEdit) {
        this.lastEdit = lastEdit;
    }

    @Basic
    @Column(name = "creation_date", nullable = true)
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdEntity adEntity = (AdEntity) o;
        return idAd == adEntity.idAd &&
                Objects.equals(title, adEntity.title) &&
                Objects.equals(description, adEntity.description) &&
                Objects.equals(sellPrice, adEntity.sellPrice) &&
                Objects.equals(address, adEntity.address) &&
                Objects.equals(coordinates, adEntity.coordinates) &&
                Objects.equals(approved, adEntity.approved) &&
                Objects.equals(active, adEntity.active) &&
                Objects.equals(beginDate, adEntity.beginDate) &&
                Objects.equals(endDate, adEntity.endDate) &&
                Objects.equals(adType, adEntity.adType) &&
                Arrays.equals(files, adEntity.files) &&
                Objects.equals(deleted, adEntity.deleted) &&
                Objects.equals(lastEdit, adEntity.lastEdit) &&
                Objects.equals(creationDate, adEntity.creationDate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idAd, title, description, sellPrice, address, coordinates, approved, active, beginDate, endDate, adType, deleted, lastEdit, creationDate);
        result = 31 * result + Arrays.hashCode(files);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id_seller", referencedColumnName = "id_user", nullable = false)
    public UserEntity getUserByUserIdSeller() {
        return userByUserIdSeller;
    }

    public void setUserByUserIdSeller(UserEntity userByUserIdSeller) {
        this.userByUserIdSeller = userByUserIdSeller;
    }

    @ManyToOne
    @JoinColumn(name = "item_id_item", referencedColumnName = "id_item", nullable = false)
    public ItemEntity getItemByItemIdItem() {
        return itemByItemIdItem;
    }

    public void setItemByItemIdItem(ItemEntity itemByItemIdItem) {
        this.itemByItemIdItem = itemByItemIdItem;
    }

    @OneToMany(mappedBy = "adByAdIdAd")
    public Collection<AdHasAttributeEntity> getAdHasAttributesByIdAd() {
        return adHasAttributesByIdAd;
    }

    public void setAdHasAttributesByIdAd(Collection<AdHasAttributeEntity> adHasAttributesByIdAd) {
        this.adHasAttributesByIdAd = adHasAttributesByIdAd;
    }

    @OneToMany(mappedBy = "adByAdIdAd")
    public Collection<BenefitEntity> getBenefitsByIdAd() {
        return benefitsByIdAd;
    }

    public void setBenefitsByIdAd(Collection<BenefitEntity> benefitsByIdAd) {
        this.benefitsByIdAd = benefitsByIdAd;
    }

    @OneToMany(mappedBy = "adByAdIdAd")
    public Collection<CommentEntity> getCommentsByIdAd() {
        return commentsByIdAd;
    }

    public void setCommentsByIdAd(Collection<CommentEntity> commentsByIdAd) {
        this.commentsByIdAd = commentsByIdAd;
    }

    @OneToMany(mappedBy = "adByAdIdAd")
    public Collection<MediaEntity> getMediaByIdAd() {
        return mediaByIdAd;
    }

    public void setMediaByIdAd(Collection<MediaEntity> mediaByIdAd) {
        this.mediaByIdAd = mediaByIdAd;
    }

    @OneToMany(mappedBy = "adByAdIdAd")
    public Collection<NotifEntity> getNotifsByIdAd() {
        return notifsByIdAd;
    }

    public void setNotifsByIdAd(Collection<NotifEntity> notifsByIdAd) {
        this.notifsByIdAd = notifsByIdAd;
    }
}
