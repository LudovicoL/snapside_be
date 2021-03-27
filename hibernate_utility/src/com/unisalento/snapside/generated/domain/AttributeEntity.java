package com.unisalento.snapside.generated.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "attribute", schema = "snapside", catalog = "")
public class AttributeEntity {
    private int idAttribute;
    private String attribName;
    private Collection<AdHasAttributeEntity> adHasAttributesByIdAttribute;
    private CategoryEntity categoryByCategoryIdCategory;
    private ItemEntity itemByItemIdItem;

    @Id
    @Column(name = "id_attribute", nullable = false)
    public int getIdAttribute() {
        return idAttribute;
    }

    public void setIdAttribute(int idAttribute) {
        this.idAttribute = idAttribute;
    }

    @Basic
    @Column(name = "attrib_name", nullable = true, length = 255)
    public String getAttribName() {
        return attribName;
    }

    public void setAttribName(String attribName) {
        this.attribName = attribName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributeEntity that = (AttributeEntity) o;
        return idAttribute == that.idAttribute &&
                Objects.equals(attribName, that.attribName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAttribute, attribName);
    }

    @OneToMany(mappedBy = "attributeByAttributeIdAttribute")
    public Collection<AdHasAttributeEntity> getAdHasAttributesByIdAttribute() {
        return adHasAttributesByIdAttribute;
    }

    public void setAdHasAttributesByIdAttribute(Collection<AdHasAttributeEntity> adHasAttributesByIdAttribute) {
        this.adHasAttributesByIdAttribute = adHasAttributesByIdAttribute;
    }

    @ManyToOne
    @JoinColumn(name = "category_id_category", referencedColumnName = "id_category")
    public CategoryEntity getCategoryByCategoryIdCategory() {
        return categoryByCategoryIdCategory;
    }

    public void setCategoryByCategoryIdCategory(CategoryEntity categoryByCategoryIdCategory) {
        this.categoryByCategoryIdCategory = categoryByCategoryIdCategory;
    }

    @ManyToOne
    @JoinColumn(name = "item_id_item", referencedColumnName = "id_item", nullable = false)
    public ItemEntity getItemByItemIdItem() {
        return itemByItemIdItem;
    }

    public void setItemByItemIdItem(ItemEntity itemByItemIdItem) {
        this.itemByItemIdItem = itemByItemIdItem;
    }
}
