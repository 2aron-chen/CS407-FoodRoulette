package com.example.cs407_foodroulette;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cs407_foodroulette.RestuarantUtilities.Restaurant;

import java.lang.reflect.Array;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashSet;

public class RecentsFragment extends Fragment {

    public RecentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recents, container, false);
        Activity activity = this.getActivity();
        SharedPreferences sharedPreferences = activity.getSharedPreferences(Constants.PACKAGE_NAME, Context.MODE_PRIVATE);
        ArrayList<String> recents = new ArrayList<String>();
        for (int i = 0; i<10;i++){
            recents.add(sharedPreferences.getString("RECENT"+Integer.toString(i+1), ""));
        }
        ArrayList<String> displayRestaurants = new ArrayList<String>();
        for (String id : recents) {
            if (id == "") {
                continue;
            }
            Restaurant restaurant = Restaurant.getRestaurantById(id);
            String price = "";
            for (int i = 0; i<restaurant.getPriceLevel();i++) {
                price += "$";
            }
            displayRestaurants.add(String.format("%s\n%s\t\t\t%s\t\t\t%s", restaurant.getRestaurantName(), restaurant.getCuisineName(), price, restaurant.getRating()));
        }
        ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1, displayRestaurants);
        ListView listView = (ListView) view.findViewById(R.id.recentList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id){
               Fragment results = new ResultsFragment();
               Bundle args = new Bundle();
               args.putBoolean("FromRecent", true);
               args.putInt("Position", position);
               results.setArguments(args);
               getParentFragmentManager()
                       .beginTransaction()
                       .setCustomAnimations(
                               R.anim.slide_in,
                               R.anim.fade_out,
                               R.anim.fade_in,
                               R.anim.slide_out
                       )
                       .replace(R.id.container, results)
                       .commit();
           }
        });

        return view;
    }
}