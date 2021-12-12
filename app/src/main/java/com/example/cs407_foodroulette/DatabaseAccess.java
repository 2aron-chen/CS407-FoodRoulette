package com.example.cs407_foodroulette;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.cs407_foodroulette.RestuarantUtilities.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseAccess {

    private DatabaseAccess(){ return;}

    public static ArrayList<HashMap<String, String>> getRestaurantByCuisinePrice(String cuisine, int price)
    {
        Cursor c;
        SQLiteOpenHelper openHelper = new DatabaseOpenHelper(MainActivity.context);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        if (!cuisine.equals("Unknown") && price != 0) {
            c = db.rawQuery("SELECT * FROM Restaurants WHERE Cuisine = '"+cuisine+"' AND Price = "+price, null);
        } else if (cuisine.equals("Unknown") && price != 0) {
            c = db.rawQuery("SELECT * FROM Restaurants WHERE Price = "+price, null);
        } else if (!cuisine.equals("Unknown") && price == 0) {
            c = db.rawQuery("SELECT * FROM Restaurants WHERE Cuisine = '"+cuisine+"'", null);
        } else {
            c = db.rawQuery("SELECT * FROM Restaurants", null);
        }

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
        if (c.moveToFirst())
        {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0; i<c.getColumnCount();i++)
                {
                    map.put(c.getColumnName(i), c.getString(i));
                }
                list.add(map);
            } while (c.moveToNext());
        }
        db.close();
        return list;
    }

    public static HashMap<String, String> getRestaurantByID(String id)
    {
        Cursor c;
        SQLiteOpenHelper openHelper = new DatabaseOpenHelper(MainActivity.context);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        c = db.rawQuery("SELECT * FROM Restaurants WHERE Place_ID = '" + id +"'", null);
        HashMap<String, String> row = new HashMap<String, String>();
        if (c.moveToFirst())
        {
            for(int i=0; i<c.getColumnCount();i++)
            {
                row.put(c.getColumnName(i), c.getString(i));
            }
        }
        db.close();
        return row;
    }
}

