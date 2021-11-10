package com.example.cs407_foodroulette;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.time.LocalDate;

public class HomeFragment extends Fragment {

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Activity activity = this.getActivity();
        SharedPreferences sharedPreferences = activity.getSharedPreferences(Constants.PACKAGE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(Constants.VIEW, Constants.HOME).apply();

        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}