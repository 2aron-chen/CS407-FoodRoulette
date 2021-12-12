package com.example.cs407_foodroulette;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.MissingResourceException;
import java.util.Random;

public class LoadFromSearchFragment extends Fragment {
    private String cuisine;
    private String distance;
    private int price;

    double current_lat = 43.070042109796695;
    double current_lng = -89.39036747340725;
    int condition = 0;
    ArrayList<String> restaurantList = new ArrayList<>();

    public LoadFromSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();

        if (bundle == null) {
            throw new MissingResourceException("ERROR: Bundle missing!", "Bundle", "");
        }

        cuisine = bundle.getString(Constants.CUISINE_KEY);
        distance = bundle.getString(Constants.DISTANCE_KEY);
        price = bundle.getInt(Constants.PRICE_KEY);


        if (price == -1) {  //distance and cuisine are locked
            condition = 1;
        } else if (cuisine.equals("Unknown")) { //distance and price are locked
            condition = 2;
            price++;
        } else if (distance.equals("-1 Mile")) { //cuisine and price are locked
            condition = 3;
            price++;
        }

        int index = distance.indexOf(" ");
        distance = distance.substring(0,index);

        Bundle args = new Bundle();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());
        databaseAccess.open();
        restaurantList = databaseAccess.getRestaurant(cuisine, String.valueOf(price), Double.parseDouble(distance), current_lat, current_lng, condition);
        if (restaurantList == null){    // No restaurant found.
            args.putString(Constants.Final_KEY, "null");
        }else {
            Random rand = new Random();
            if (restaurantList.size()==1){  // only found 1 restaurant
                args.putString(Constants.Final_KEY, restaurantList.get(0));
            }else {
                int randomPick = rand.nextInt(restaurantList.size());
                args.putString(Constants.Final_KEY, restaurantList.get(randomPick));
            }
        }
        databaseAccess.close();


        View view = inflater.inflate(R.layout.fragment_load_from_search, container, false);

        return view;
    }

    private void validateSelections() {
        String [] cuisineArr = getActivity().getResources().getStringArray(R.array.cuisine_array);
        String [] locationArr = getActivity().getResources().getStringArray(R.array.location_array);
        String [] pricesarr = getActivity().getResources().getStringArray(R.array.price_array);

        if (cuisine.equals(Constants.DEFAULT_CUISINE)) {

        }

        if (distance.equals(Constants.DEFAULT_DISTANCE)) {

        }

        if (price == Constants.DEFAULT_PRICE) {

        }

    }
}