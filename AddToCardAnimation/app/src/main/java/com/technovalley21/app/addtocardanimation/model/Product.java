package com.technovalley21.app.addtocardanimation.model;

/**
 * Created by sarker on 11/20/17.
 */

public class Product {
    private int id;
    private String name;
    private float price;
    private int iconId;

    public Product(){

    }

    public Product(int id, String name, float price, int iconId){
        this.id = id;
        this.name = name;
        this.price = price;
        this.iconId = iconId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
