package com.example.cs407_foodroulette;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.MissingResourceException;

public class LoadFromSearchFragment extends Fragment {
    private String cuisine;
    private String distance;
    private int price;

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