package com.unisalento.snapside.models;

public class AttributeDTO {
    private int idattribute;
    private String attributeName;
    private String attributeValue;
    private int category_idcategory;
    private int itemByItemIdItem;

    public int getIdattribute() {
        return idattribute;
    }

    public void setIdattribute(int idattribute) {
        this.idattribute = idattribute;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public int getCategory_idcategory() {
        return category_idcategory;
    }

    public void setCategory_idcategory(int category_idcategory) {
        this.category_idcategory = category_idcategory;
    }

    public int getItemByItemIdItem() {
        return itemByItemIdItem;
    }

    public void setItemByItemIdItem(int itemByItemIdItem) {
        this.itemByItemIdItem = itemByItemIdItem;
    }
}
