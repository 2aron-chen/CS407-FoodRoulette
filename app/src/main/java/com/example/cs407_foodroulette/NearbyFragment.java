package com.example.cs407_foodroulette;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cs407_foodroulette.RestuarantUtilities.Restaurant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

public class NearbyFragment extends Fragment {
    private LatLng currLatLng;
    private GoogleMap googleMap;
    private List<Restaurant> restaurantList;
    private List<Marker> markerList;
    private int lessIdx;
    private int moreIdx;

    public NearbyFragment() {
        lessIdx = 0;
        moreIdx = 0;
        markerList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        View view = inflater.inflate(R.layout.fragment_nearby, container, false);

        if (bundle == null) {
            throw new MissingResourceException("ERROR: Bundle missing!", "Bundle", "");
        }

        currLatLng = new LatLng(bundle.getDouble(Constants.LAT_KEY), bundle.getDouble(Constants.LONG_KEY));

        restaurantList = findRestaurants();
        addMarkers(restaurantList);
        moveCamera();
        setupOnClickListeners(view);
        onClickMore();

        return view;
    }

    private List<Restaurant> findRestaurants() {
        ArrayList<Restaurant> restaurantList = Restaurant.getRestaurantsByCuisinePrice(Constants.DEFAULT_CUISINE, Constants.DEFAULT_PRICE+1);

        return Restaurant.getRestaurantsWithinDistance(restaurantList, 1, currLatLng.latitude, currLatLng.longitude);
    }

    private void addMarkers(List<Restaurant> restaurantList) {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_map);

        mapFragment.getMapAsync(map -> {
            googleMap = map;
            Marker userMarker = map.addMarker(new MarkerOptions().position(currLatLng).title("You are here"));
            userMarker.showInfoWindow();

            for (Restaurant restaurant : restaurantList) {
                Marker marker = map.addMarker(new MarkerOptions()
                        .position(new LatLng(restaurant.getLat(), restaurant.getLng()))
                        .title(restaurant.getRestaurantName()));

                markerList.add(marker);

                // setonclicklistener for each marker
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(final Marker marker) {
                        String title = marker.getTitle();
                        Restaurant match = (restaurantList.size() != 0) ? restaurantList.get(0) : null;

                        for (Restaurant restaurant : restaurantList) {
                            if (restaurant.getRestaurantName().equals(title)) {
                                match = restaurant;
                                break;
                            }
                        }

                        String url = match.getURL();
                        Uri uri = Uri.parse(url);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        return true;
                    }
                });

            } // end for

            for (Marker marker : markerList) {
                marker.setVisible(false);
            }

            for (int i = 0; i < markerList.size() / 6; i++) {
                markerList.get(moreIdx).setVisible(true);
                moreIdx++;
            }

        });
    }

    private void moveCamera() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_map);

        mapFragment.getMapAsync(map -> {
            googleMap = map;

            map.animateCamera(CameraUpdateFactory.newLatLngZoom(currLatLng, 13));
        });
    }

    private void onClickMore() {
        int diff = markerList.size() / 6;

        if (markerList.size() == 0 || (moreIdx - lessIdx) >= (markerList.size() - diff)) return;

        while (diff > 0) {
            int idx = moreIdx % markerList.size();
            Marker marker  = markerList.get(idx);
            moreIdx++;

            if (marker.isVisible()) continue;

            marker.setVisible(true);
            diff--;
        }

    }

    private void onClickLess() {
        int diff = markerList.size() / 6;

        if (markerList.size() == 0 || (lessIdx == moreIdx)) return;

        while (diff > 0) {
            int idx = lessIdx % markerList.size();
            Marker marker  = markerList.get(idx);
            lessIdx++;

            if (!marker.isVisible()) continue;

            marker.setVisible(false);
            diff--;
        }
    }

    private void setupOnClickListeners(View view) {
        Button button = (Button) view.findViewById(R.id.moreBtn);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickMore();
            }
        });

        button = (Button) view.findViewById(R.id.lessBtn);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickLess();
            }
        });
    }
}