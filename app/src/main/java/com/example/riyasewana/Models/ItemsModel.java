package com.example.riyasewana.Models;

import java.io.Serializable;

public class ItemsModel implements Serializable {

    String name,price,location,discription;
    int image;

    public ItemsModel(String name, String price, String location, String discription, int image) {
        this.name = name;
        this.price = price;
        this.location = location;
        this.discription = discription;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

