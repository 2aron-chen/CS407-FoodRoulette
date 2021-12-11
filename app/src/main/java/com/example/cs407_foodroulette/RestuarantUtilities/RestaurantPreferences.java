package com.example.cs407_foodroulette.RestuarantUtilities;

import com.example.cs407_foodroulette.Constants;

public class RestaurantPreferences {
    private int price;
    private String cuisine;
    private String distance;

    public RestaurantPreferences(int price, String cuisine, String distance) {
        this.price = price;
        this.cuisine = cuisine;
        this.distance = distance;

        return;
    }

    public RestaurantPreferences() {
        this.price = Constants.DEFAULT_PRICE;
        this.cuisine = Constants.DEFAULT_CUISINE;
        this.distance = Constants.DEFAULT_DISTANCE;

        return;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
