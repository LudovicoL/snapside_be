package com.unisalento.snapside.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryDTO {
    private int idCategory;
    private String categoryName;
    private String descriptionCat;

    public int getIdcategory() {
        return idCategory;
    }

    public void setIdcategory(int idCategory) {
        this.idCategory = idCategory;
    }
    @JsonProperty("categoryName")
    public String getCategoryname() {
        return categoryName;
    }

    public void setCategoryname(String categoryName) {
        this.categoryName = categoryName;
    }

    @JsonProperty("descriptionCat")
    public String getDescription() {
        return descriptionCat;
    }

    public void setDescription(String descriptionCat) {
        this.descriptionCat = descriptionCat;
    }
}
