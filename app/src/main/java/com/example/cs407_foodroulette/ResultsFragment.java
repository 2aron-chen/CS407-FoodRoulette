package com.example.cs407_foodroulette;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs407_foodroulette.RestuarantUtilities.Restaurant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.MissingResourceException;

public class ResultsFragment extends Fragment {
    private String final_ID;
    public TextView restaurantTextView;
    public TextView mondayhourTextView;
    public TextView tuesdayhourTextView;
    public TextView wednesdayTextView;
    public TextView thursdayhourTextView;
    public TextView fridayhourTextView;
    public TextView saturdayhourTextView;
    public TextView sundayhourTextView;

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
        if (bundle == null) {
            throw new MissingResourceException("ERROR: Bundle missing!", "Bundle", "");
        }

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.PACKAGE_NAME, Context.MODE_PRIVATE);
        ArrayList<String> recents = new ArrayList<String>();
        for (int i = 0; i<10;i++){
            recents.add(sharedPreferences.getString("RECENT"+Integer.toString(i+1), ""));
        }

        View view = inflater.inflate(R.layout.fragment_results, container, false);

        boolean fromRecent = bundle.getBoolean("FromRecent");
        if (fromRecent == true) {
            final_ID = recents.get(bundle.getInt("Position"));
        } else {
            final_ID = bundle.getString(Constants.Final_KEY);
        }

        restaurantTextView = (TextView) view.findViewById(R.id.textViewResultRestaurant);
        mondayhourTextView = (TextView) view.findViewById(R.id.textViewResultHourMonday);
        tuesdayhourTextView = (TextView) view.findViewById(R.id.textViewResultHourTuesday);
        wednesdayTextView = (TextView) view.findViewById(R.id.textViewResultHourWednesday);
        thursdayhourTextView = (TextView) view.findViewById(R.id.textViewResultHourThursday);
        fridayhourTextView = (TextView) view.findViewById(R.id.textViewResultHourFriday);
        saturdayhourTextView = (TextView) view.findViewById(R.id.textViewResultHourSaturday);
        sundayhourTextView = (TextView) view.findViewById(R.id.textViewResultHourSunday);
        addressTextView = (TextView) view.findViewById(R.id.textViewResultAddress);
        ratingTextView = (TextView) view.findViewById(R.id.textViewResultStars);
        phoneNumberTextView = (TextView) view.findViewById(R.id.textViewResultPhoneNumber);

        if (final_ID != "null"){
            if (recents.size() == 0)
            {
                recents.add(final_ID);
            } else {
                int index = recents.indexOf(final_ID);
                if (index != -1) {
                    recents.remove(index);
                    recents.add("");
                }
                recents.add(0, final_ID);
                while (recents.size() > 10){
                    recents.remove(10);
                }
            }
            for (int i = 0; i<recents.size();i++)
            {
                sharedPreferences.edit().putString("RECENT"+Integer.toString(i+1), recents.get(i)).apply();
            }

            Restaurant finalRestaurant = Restaurant.getRestaurantById(final_ID);
            String restaurant = finalRestaurant.getRestaurantName();
            String star = finalRestaurant.getRating() + " stars";
            String hours = finalRestaurant.getHoursOfOperation();
            String phoneNumber = finalRestaurant.getPhoneNumber();
            String address = finalRestaurant.getStreetName();

//            split the hours
            ArrayList<String> index = new ArrayList<String>();
            int i = hours.indexOf("~");
            while (i >= 0){
                index.add(String.valueOf(i));
                i = hours.indexOf('~', i+1);
            }
            String temp = hours.substring(0, Integer.parseInt(index.get(0)));
            String temp2 = hours.substring(Integer.parseInt(index.get(0))+1, Integer.parseInt(index.get(1)));
            restaurantTextView.setText(restaurant);
            mondayhourTextView.setText(hours.substring(0, Integer.parseInt(index.get(0))));
            tuesdayhourTextView.setText(hours.substring(Integer.parseInt(index.get(0))+1, Integer.parseInt(index.get(1))));
            wednesdayTextView.setText(hours.substring(Integer.parseInt(index.get(1))+1, Integer.parseInt(index.get(2))));
            thursdayhourTextView.setText(hours.substring(Integer.parseInt(index.get(2))+1, Integer.parseInt(index.get(3))));
            fridayhourTextView.setText(hours.substring(Integer.parseInt(index.get(3))+1, Integer.parseInt(index.get(4))));
            saturdayhourTextView.setText(hours.substring(Integer.parseInt(index.get(4))+1, Integer.parseInt(index.get(5))));
            sundayhourTextView.setText(hours.substring(Integer.parseInt(index.get(5))+1));
            addressTextView.setText(address);
            ratingTextView.setText(star);
            phoneNumberTextView.setText(phoneNumber);
        }else{
            restaurantTextView.setText("No Restaurant found");
            mondayhourTextView.setText("");
            tuesdayhourTextView.setText("");
            wednesdayTextView.setText("");
            thursdayhourTextView.setText("");
            fridayhourTextView.setText("");
            saturdayhourTextView.setText("");
            sundayhourTextView.setText("");
            addressTextView.setText("");
            ratingTextView.setText("");
            phoneNumberTextView.setText("");
        }

        // Inflate the layout for this fragment
        return view;
    }
}