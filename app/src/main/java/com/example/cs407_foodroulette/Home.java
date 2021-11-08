package com.example.cs407_foodroulette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {



    public void clickRoulette(View view){



        goToLoadFromSearch();
    }

    public void goToLoadFromSearch(){
        Intent intent = new Intent(this, Load_From_Search.class);
//        intent.putExtra("double", d);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }
}