package com.example.cs407_foodroulette;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs407_foodroulette.RestuarantUtilities.Restaurant;

import java.util.MissingResourceException;

public class ResultsFragment extends Fragment {
    private String final_ID;
    public TextView restaurantTextView;
    public TextView hourTextView;
    public TextView addressTextView;
    public TextView ratingTextView;
    public TextView phoneNumberTextView;

    public ResultsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Bundle bundle = this.getArguments();

        final_ID = bundle.getString(Constants.Final_KEY);

        if (bundle == null) {
            throw new MissingResourceException("ERROR: Bundle missing!", "Bundle", "");
        }

        Restaurant finalRestaurant = Restaurant.getRestaurantById(final_ID);
        String restaurant = finalRestaurant.getRestaurantName();
        String star = finalRestaurant.getRating();
        String hours = finalRestaurant.getHoursOfOperation();
        String phoneNumber = finalRestaurant.getPhoneNumber();
        String address = finalRestaurant.getStreetName();

        View view = inflater.inflate(R.layout.fragment_results, container, false);
        restaurantTextView = (TextView) view.findViewById(R.id.textViewResultRestaurant);
        hourTextView = (TextView) view.findViewById(R.id.textViewResultHours);
        addressTextView = (TextView) view.findViewById(R.id.textViewResultAddress);
        ratingTextView = (TextView) view.findViewById(R.id.textViewResultStars);
        phoneNumberTextView = (TextView) view.findViewById(R.id.textViewResultPhoneNumber);

        restaurantTextView.setText(restaurant);
        hourTextView.setText(hours);
        addressTextView.setText(address);
        ratingTextView.setText(star);
        phoneNumberTextView.setText(phoneNumber);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results, container, false);
    }
}