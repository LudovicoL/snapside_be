package com.unisalento.snapside.generated.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "category", schema = "snapside", catalog = "")
public class CategoryEntity {
    private int idCategory;
    private String categoryName;
    private String descriptionCat;
    private Collection<AttributeEntity> attributesByIdCategory;
    private Collection<ItemEntity> itemsByIdCategory;

    @Id
    @Column(name = "id_category", nullable = false)
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Basic
    @Column(name = "category_name", nullable = true, length = 127)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Basic
    @Column(name = "description_cat", nullable = true, length = 5000)
    public String getDescriptionCat() {
        return descriptionCat;
    }

    public void setDescriptionCat(String descriptionCat) {
        this.descriptionCat = descriptionCat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return idCategory == that.idCategory &&
                Objects.equals(categoryName, that.categoryName) &&
                Objects.equals(descriptionCat, that.descriptionCat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategory, categoryName, descriptionCat);
    }

    @OneToMany(mappedBy = "categoryByCategoryIdCategory")
    public Collection<AttributeEntity> getAttributesByIdCategory() {
        return attributesByIdCategory;
    }

    public void setAttributesByIdCategory(Collection<AttributeEntity> attributesByIdCategory) {
        this.attributesByIdCategory = attributesByIdCategory;
    }

    @OneToMany(mappedBy = "categoryByCategoryIdCategory")
    public Collection<ItemEntity> getItemsByIdCategory() {
        return itemsByIdCategory;
    }

    public void setItemsByIdCategory(Collection<ItemEntity> itemsByIdCategory) {
        this.itemsByIdCategory = itemsByIdCategory;
    }
}
