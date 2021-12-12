package com.example.cs407_foodroulette.RestuarantUtilities;

import com.example.cs407_foodroulette.DatabaseAccess;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Restaurant {
    private String placeID;
    private String restaurantName;
    private String cuisine;
    private String streetName;
    private String cityName;
    private String stateName;
    private String zipcode;
    private String phoneNumber;
    private String hoursOfOperation;
    private int priceLevel;
    private String rating;
    private String URL;
    private String website;
    private double lat;
    private double lng;

    private Restaurant() {
        return;
    }

    private Restaurant(HashMap<String, String> row) {
        this.placeID = row.get("Place_ID");
        this.restaurantName = row.get("Restaurant");
        this.streetName = row.get("Street");
        this.cityName = row.get("City");
        this.cuisine = row.get("Cuisine");
        this.stateName = row.get("US_State");
        this.zipcode = row.get("Zip");
        this.phoneNumber = row.get("Phone");
        this.hoursOfOperation = row.get("Hours");
        this.priceLevel = Integer.parseInt(row.get("Price"));
        this.rating = row.get("Rating");
        this.URL = row.get("URL");
        this.website = row.get("Website");
        this.lat = Double.parseDouble(row.get("Lat"));
        this.lng = Double.parseDouble(row.get("Lng"));
    }

    public String getPlaceID() {return placeID; }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getCuisineName() {return cuisine; }

    public String getStreetName() {
        return streetName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getHoursOfOperation() {
        return hoursOfOperation;
    }

    public int getPriceLevel() {
        return priceLevel;
    }

    public String getRating() {
        return rating;
    }

    public String getURL() {
        return URL;
    }

    public String getWebsite() {
        return website;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public static Restaurant getRestaurantById(String id)
    {
        return new Restaurant(DatabaseAccess.getRestaurantByID(id));
    }

    public static ArrayList<Restaurant> getRestaurantsByCuisinePrice(String cuisine, int price)
    {
        ArrayList<HashMap<String, String>> table = DatabaseAccess.getRestaurantByCuisinePrice(cuisine, price);
        ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
        for (int i = 0; i<table.size(); i++) {
            restaurants.add(new Restaurant(table.get(i)));
        }
        return restaurants;
    }

    public static ArrayList<Restaurant> getRestaurantsWithinDistance(ArrayList<Restaurant> old, double distance, double curr_lat, double curr_lng)
    {
        ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
        for (int i = 0; i < old.size(); i++)
        {
            if (distancefunc(curr_lat, old.get(i).getLat(), curr_lng, old.get(i).getLng()) < distance)
            {
                restaurants.add(old.get(i));
            }
        }
        return restaurants;
    }

    private static double distancefunc(double lat1, double lat2, double lon1, double lon2) {

        double dlon = Math.toRadians(lon2 - lon1);
        double dlat = Math.toRadians(lat2 - lat1);
        double a = Math.sin(dlat/2) * Math.sin(dlat/2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dlon/2) * Math.sin(dlon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 3956;

        // calculate the result
        return(c * r);
    }

}
