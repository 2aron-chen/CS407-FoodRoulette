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

public class NearbyFragment extends Fragment {

    public NearbyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_map);

        Activity activity = this.getActivity();
        SharedPreferences sharedPreferences = activity.getSharedPreferences(Constants.PACKAGE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(Constants.VIEW, Constants.NEARBY).apply();

        return inflater.inflate(R.layout.fragment_nearby, container, false);
    }
}