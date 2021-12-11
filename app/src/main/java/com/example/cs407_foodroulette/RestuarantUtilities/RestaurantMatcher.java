package com.example.cs407_foodroulette.RestuarantUtilities;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMatcher {

    public RestaurantMatcher() {
        return;
    }

    public Restaurant getMatch(RestaurantPreferences preferences) {
        return null;
    }

    public RestaurantPreferences configureUserRestaurantPreferences(){
        // TODO: configure preference criteria
        RestaurantPreferences preferences = new RestaurantPreferences(0, null, "0"); // Changed int 0 to string.

        return preferences;
    }

    private List<Restaurant> filterByDistances() {
        // TODO: implement distance calculation

        return new ArrayList<Restaurant>();
    }

}
