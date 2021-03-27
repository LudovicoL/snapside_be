package com.unisalento.snapside.generated.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "item", schema = "snapside", catalog = "")
public class ItemEntity {
    private int idItem;
    private String itemName;
    private String descriptionItem;
    private Collection<AdEntity> adsByIdItem;
    private Collection<AttributeEntity> attributesByIdItem;
    private CategoryEntity categoryByCategoryIdCategory;

    @Id
    @Column(name = "id_item", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    @Basic
    @Column(name = "item_name", nullable = true, length = 127)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Basic
    @Column(name = "description_item", nullable = true, length = 5000)
    public String getDescriptionItem() {
        return descriptionItem;
    }

    public void setDescriptionItem(String descriptionItem) {
        this.descriptionItem = descriptionItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return idItem == that.idItem &&
                Objects.equals(itemName, that.itemName) &&
                Objects.equals(descriptionItem, that.descriptionItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idItem, itemName, descriptionItem);
    }

    @OneToMany(mappedBy = "itemByItemIdItem")
    public Collection<AdEntity> getAdsByIdItem() {
        return adsByIdItem;
    }

    public void setAdsByIdItem(Collection<AdEntity> adsByIdItem) {
        this.adsByIdItem = adsByIdItem;
    }

    @OneToMany(mappedBy = "itemByItemIdItem")
    public Collection<AttributeEntity> getAttributesByIdItem() {
        return attributesByIdItem;
    }

    public void setAttributesByIdItem(Collection<AttributeEntity> attributesByIdItem) {
        this.attributesByIdItem = attributesByIdItem;
    }

    @ManyToOne
    @JoinColumn(name = "category_id_category", referencedColumnName = "id_category", nullable = false)
    public CategoryEntity getCategoryByCategoryIdCategory() {
        return categoryByCategoryIdCategory;
    }

    public void setCategoryByCategoryIdCategory(CategoryEntity categoryByCategoryIdCategory) {
        this.categoryByCategoryIdCategory = categoryByCategoryIdCategory;
    }
}
