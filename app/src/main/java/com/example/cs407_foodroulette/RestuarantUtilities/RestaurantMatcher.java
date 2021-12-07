package com.example.cs407_foodroulette.RestuarantUtilities;

import java.util.ArrayList;
import java.util.List;

public class ResturantMatcher {

    public ResturantMatcher() {
        return;
    }

    public Restuarant getMatch(RestuarantPreferences preferences) {
        return null;
    }

    public RestuarantPreferences configureUserRestuarantPreferences(){
        // TODO: configure preference criteria
        RestuarantPreferences preferences = new RestuarantPreferences(0, null, 0);

        return preferences;
    }

    private List<Restuarant> filterByDistances() {
        // TODO: implement distance calculation

        return new ArrayList<Restuarant>();
    }

}
