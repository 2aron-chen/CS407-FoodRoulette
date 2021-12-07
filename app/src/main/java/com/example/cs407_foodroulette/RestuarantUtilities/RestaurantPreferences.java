package com.example.cs407_foodroulette.RestuarantUtilities;

public class RestuarantPreferences {
    private int price;
    private String cuisine;
    private int distance;

    public RestuarantPreferences(int price, String cuisine, int distance) {
        this.price = price;
        this.cuisine = cuisine;
        this.distance = distance;

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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
