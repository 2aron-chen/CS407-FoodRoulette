package com.example.cs407_foodroulette;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private NavigationBarView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnItemSelectedListener(bottomnavFunction);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
    }

    public void clickRoulette(View view){
        goToLoadFromSearch();
    }

    public void goToLoadFromSearch() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoadFromSearchFragment()).commit();
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
                    break;
                case R.id.Recent:
                    fragment = new RecentsFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            return true;
        }
    };
}