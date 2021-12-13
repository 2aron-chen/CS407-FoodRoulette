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
        View view = inflater.inflate(R.layout.fragment_results, container, false);

        final_ID = bundle.getString(Constants.Final_KEY);

        if (bundle == null) {
            throw new MissingResourceException("ERROR: Bundle missing!", "Bundle", "");
        }

        restaurantTextView = (TextView) view.findViewById(R.id.textViewResultRestaurant);
        hourTextView = (TextView) view.findViewById(R.id.textViewResultHours);
        addressTextView = (TextView) view.findViewById(R.id.textViewResultAddress);
        ratingTextView = (TextView) view.findViewById(R.id.textViewResultStars);
        phoneNumberTextView = (TextView) view.findViewById(R.id.textViewResultPhoneNumber);

        if (final_ID != "null"){
            Restaurant finalRestaurant = Restaurant.getRestaurantById(final_ID);
            String restaurant = finalRestaurant.getRestaurantName();
            String star = finalRestaurant.getRating() + " stars";
            String hours = finalRestaurant.getHoursOfOperation().substring(0,4);
            String phoneNumber = finalRestaurant.getPhoneNumber();
            String address = finalRestaurant.getStreetName();

            restaurantTextView.setText(restaurant);
            hourTextView.setText(hours);
            addressTextView.setText(address);
            ratingTextView.setText(star);
            phoneNumberTextView.setText(phoneNumber);
        }else{
            restaurantTextView.setText("No Restaurant found");
            hourTextView.setText("");
            addressTextView.setText("");
            ratingTextView.setText("");
            phoneNumberTextView.setText("");
        }

        // Inflate the layout for this fragment
        return view;
    }
}