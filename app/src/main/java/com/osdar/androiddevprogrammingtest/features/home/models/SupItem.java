package com.osdar.androiddevprogrammingtest.features.home.models;

public class SupItem {
    private String name;
    private String image;

    public SupItem() {
    }

    public SupItem(String name, String image) {
        this.name = name;
        this.image = image;
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

    @Override
    public String toString() {
        return "SupItem{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
