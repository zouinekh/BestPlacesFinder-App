package com.example.yykifehdemo;

import com.google.android.gms.maps.model.LatLng;

public class destination {
    private LatLng location;
    private Integer image;
    private String name,description;

    public destination(Double lang,Double lat, Integer image, String name, String description) {
        this.location = new LatLng(lat,lang);
        this.image = image;
        this.name = name;
        this.description = description;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
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
}
