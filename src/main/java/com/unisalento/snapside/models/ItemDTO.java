package com.unisalento.snapside.models;

public class ItemDTO {
    private int idItem;
    private String name;
    private String description;
    private int category_idcategory;

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory_idcategory() {
        return category_idcategory;
    }

    public void setCategory_idcategory(int category_idcategory) {
        this.category_idcategory = category_idcategory;
    }
}
