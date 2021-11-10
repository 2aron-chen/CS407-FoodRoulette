package com.example.cs407_foodroulette;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecentsFragment extends Fragment {

    public RecentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Activity activity = this.getActivity();
        SharedPreferences sharedPreferences = activity.getSharedPreferences(Constants.PACKAGE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(Constants.VIEW, Constants.RECENTS).apply();

        return inflater.inflate(R.layout.fragment_recents, container, false);
    }
}