package com.example.cs407_foodroulette;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.example.cs407_foodroulette.RestuarantUtilities.RestaurantPreferences;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private NavigationBarView bottomNavigationView;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;
    public LatLng currLatLng;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFusedLocationProviderClient =
                    LocationServices.getFusedLocationProviderClient(this);
        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnItemSelectedListener(bottomnavFunction);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.PACKAGE_NAME, Context.MODE_PRIVATE);
        int view = sharedPreferences.getInt(Constants.VIEW, 0);

        getLocation();
        context = getApplicationContext();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
    }

    public void clickShake(View view){
        goToLoadFromSearch();
    }

    public void goToLoadFromSearch() {
        RestaurantPreferences prefs = new RestaurantPreferences();
        Fragment loadFromSearch = new LoadFromSearchFragment();

        String cuisine = Constants.DEFAULT_CUISINE;
        String distance = Constants.DEFAULT_DISTANCE;
        int price = Constants.DEFAULT_PRICE;

        Spinner spin = findViewById(R.id.typeSpinner);

        if (!spin.isEnabled()) {
            cuisine = spin.getSelectedItem().toString();
        }

        spin = findViewById(R.id.distanceSpinner);

        if (!spin.isEnabled()) {
            distance = spin.getSelectedItem().toString();
        }

        spin = findViewById(R.id.priceSpinner);

        if (!spin.isEnabled()) {
            price = spin.getSelectedItemPosition();
        }

        Bundle args = new Bundle();
        args.putInt(Constants.PRICE_KEY, price);
        args.putString(Constants.CUISINE_KEY, cuisine);
        args.putString(Constants.DISTANCE_KEY, distance);
        LatLng data = (currLatLng != null) ? currLatLng : Constants.DEFAULT_LATLNG;
        args.putDouble(Constants.LAT_KEY, data.latitude);
        args.putDouble(Constants.LONG_KEY, data.longitude);

        loadFromSearch.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, loadFromSearch).commit();
    }

    private NavigationBarView.OnItemSelectedListener bottomnavFunction = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item)
        {
            Fragment fragment = null;
            switch (item.getItemId())
            {
                case R.id.Home:
                    fragment = new HomeFragment();
                    break;
                case R.id.Map:
                    fragment = new NearbyFragment();
                    Bundle payload = new Bundle();
                    LatLng data = (currLatLng != null) ? currLatLng : Constants.DEFAULT_LATLNG;

                    payload.putDouble(Constants.LAT_KEY, data.latitude);
                    payload.putDouble(Constants.LONG_KEY, data.longitude);
                    fragment.setArguments(payload);
                    break;
                case R.id.Recent:
                    fragment = new RecentsFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            return true;
        }
    };

    public void lockSpinner(View view)
    {
        Spinner spinner;
        Button button = (Button) findViewById(view.getId());
        switch (view.getId())
        {
            case R.id.lockTypebtn:
                spinner = (Spinner) findViewById(R.id.typeSpinner);
                break;
            case R.id.lockPricebtn:
                spinner = (Spinner) findViewById(R.id.priceSpinner);
                break;
            case R.id.lockDistancebtn:
                spinner = (Spinner) findViewById(R.id.distanceSpinner);
                break;
            default:
                throw new IllegalStateException("Unexpected lock button id: " + view.getId());
        }
        lockSpinnerToggle(spinner, button);
    }

    private void lockSpinnerToggle(Spinner spinner, Button button)
    {
        if (button.getText().equals("Lock"))
        {
            button.setText("Unlock");
            spinner.setEnabled(false);
        } else {
            button.setText("Lock");
            spinner.setEnabled(true);
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            }
        }
    }

    private void getLocation() {
        int permission = ActivityCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (permission == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        } else {
            mFusedLocationProviderClient.getLastLocation()
                    .addOnCompleteListener(this, task -> {
                        if (task == null) return;
                        Location mLastKnownLocation = task.getResult();

                        if (task.isSuccessful() && mLastKnownLocation != null){
                            currLatLng = new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());
                        }
                    });
        }
    }

    public void setRecentList() {

    }
}