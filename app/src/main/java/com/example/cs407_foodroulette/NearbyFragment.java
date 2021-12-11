package com.example.cs407_foodroulette;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.MissingResourceException;

public class NearbyFragment extends Fragment {
    private LatLng currLatLng;

    public NearbyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();

        if (bundle == null) {
            throw new MissingResourceException("ERROR: Bundle missing!", "Bundle", "");
        }

        currLatLng = new LatLng(bundle.getDouble(Constants.LAT_KEY), bundle.getDouble(Constants.LONG_KEY));


        return inflater.inflate(R.layout.fragment_nearby, container, false);
    }
}