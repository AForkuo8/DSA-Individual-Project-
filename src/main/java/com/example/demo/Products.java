package com.example.demo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Products {
    private SimpleStringProperty product_name;
    private SimpleStringProperty category;
    private SimpleIntegerProperty quantity;
    private SimpleIntegerProperty price;

    private SimpleIntegerProperty id;

    public Products() {
        this.product_name = new SimpleStringProperty();
        this.category = new SimpleStringProperty();
        this.quantity = new SimpleIntegerProperty();
        this.price = new SimpleIntegerProperty();
        this.id = new SimpleIntegerProperty();
    }

    public void setProductName(String product_name) {
        this.product_name.set(product_name);
    }

    public String getProductName() {
        return product_name.get();
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getCategory() {
        return this.category.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public int getQuantity() {
        return this.quantity.get();
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public int getPrice() {
        return this.price.get();
    }

    public SimpleIntegerProperty productIdProperty() {return id;}

    public SimpleStringProperty productNameProperty() {return product_name;}

    public SimpleStringProperty categoryProperty() {return category;}

    public SimpleIntegerProperty quantityProperty() {return quantity;}

    public SimpleIntegerProperty priceProperty() {return price;}
}
