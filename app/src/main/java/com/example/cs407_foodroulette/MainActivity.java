package com.example.cs407_foodroulette;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private NavigationBarView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnItemSelectedListener(bottomnavFunction);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.PACKAGE_NAME, Context.MODE_PRIVATE);
        int view = sharedPreferences.getInt(Constants.VIEW, 0);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
    }

    public void clickShake(View view){
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
}