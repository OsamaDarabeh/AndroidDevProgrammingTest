package com.osdar.androiddevprogrammingtest.features.home.models;

import java.util.ArrayList;

public class Item {
    private String name;
    private String image;
    private ArrayList<SupItem> supItems = new ArrayList<>();

    public Item() {
    }

    public Item(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Item(String name, String image, ArrayList<SupItem> supItems) {
        this.name = name;
        this.image = image;
        this.supItems = supItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<SupItem> getSupItems() {
        return supItems;
    }

    public void setSupItems(ArrayList<SupItem> supItems) {
        this.supItems = supItems;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", supItems=" + supItems +
                '}';
    }
}
