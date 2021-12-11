package com.example.cs407_foodroulette;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);

    }

    public static DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();
    }

    public void close(){
        if (db!=null){
            this.db.close();
        }
    }

    public String getRestaurant(String cuisine, String price, double distance, double curr_lat, double curr_lng){
        c = db.rawQuery("SELECT Restaurant FROM Restaurants WHERE Cuisine = '"+cuisine+"' AND Price = '"+price+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        int count = c.getCount();
        Log.i(TAG, String.valueOf(count));
        if (count == 0){
            return null;
        }
        while (c.moveToNext()){
            String restaurant = c.getString(0);
            Log.i(TAG, restaurant);
            Cursor lat = db.rawQuery("SELECT lat FROM Restaurants WHERE Restaurant = '"+restaurant+"'", new String[]{});
            Cursor lng = db.rawQuery("SELECT lng FROM Restaurants WHERE Restaurant = '"+restaurant+"'", new String[]{});
            double restaurant_lat = 0.0;
            double restaurant_lng = 0.0;
            while(lat.moveToNext()){
                restaurant_lat = Double.parseDouble(lat.getString(0));
            }

            while(lng.moveToNext()){
                restaurant_lng = Double.parseDouble(lng.getString(0));
            }

            double calculate_distance = distancefunc(curr_lat, restaurant_lat, curr_lng, restaurant_lng);
            Log.i(TAG, String.valueOf(calculate_distance));
            if (calculate_distance < distance) {
                Log.i(TAG, restaurant);
                buffer.append(";" + restaurant);
            }
        }

        return buffer.toString();
    }

    public String getID(String name){
        Log.i(TAG, "ID");
        c = db.rawQuery("SELECT Place_ID FROM Restaurants WHERE Restaurant = '"+name+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            String id = c.getString(0);
            buffer.append("" + id);
            Log.i(TAG, id);
        }
        return buffer.toString();
    }

    public String getStreet(String name){
        Log.i(TAG, "street");
        c = db.rawQuery("SELECT Street FROM Restaurants WHERE Restaurant = '"+name+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            String street = c.getString(0);
            buffer.append("" + street);
            Log.i(TAG, street);
        }
        return buffer.toString();
    }

    public String getCity(String name){
        Log.i(TAG, "City");
        c = db.rawQuery("SELECT City FROM Restaurants WHERE Restaurant = '"+name+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            String city = c.getString(0);
            buffer.append("" + city);
            Log.i(TAG, city);
        }
        return buffer.toString();
    }

    public String getUS_State(String name){
        Log.i(TAG, "US_State");
        c = db.rawQuery("SELECT US_State FROM Restaurants WHERE Restaurant = '"+name+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            String us_State = c.getString(0);
            buffer.append("" + us_State);
            Log.i(TAG, us_State);
        }
        return buffer.toString();
    }

    public String getZip(String name){
        Log.i(TAG, "Zip");
        c = db.rawQuery("SELECT Zip FROM Restaurants WHERE Restaurant = '"+name+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            String zip = c.getString(0);
            buffer.append("" + zip);
            Log.i(TAG, zip);
        }
        return buffer.toString();
    }

    public String getPhone(String name){
        Log.i(TAG, "Phone");
        c = db.rawQuery("SELECT Phone FROM Restaurants WHERE Restaurant = '"+name+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            String phone = c.getString(0);
            buffer.append("" + phone);
            Log.i(TAG, phone);
        }
        return buffer.toString();
    }

    public String getHours(String name){
        Log.i(TAG, "Hours");
        c = db.rawQuery("SELECT Hours FROM Restaurants WHERE Restaurant = '"+name+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            String hours = c.getString(0);
            buffer.append("" + hours);
            Log.i(TAG, hours);
        }
        return buffer.toString();
    }

    public String getURL(String name){
        Log.i(TAG, "URL");
        c = db.rawQuery("SELECT URL FROM Restaurants WHERE Restaurant = '"+name+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            String url = c.getString(0);
            buffer.append("" + url);
            Log.i(TAG, url);
        }
        return buffer.toString();
    }

    public String getWebsite(String name){
        Log.i(TAG, "Website");
        c = db.rawQuery("SELECT Website FROM Restaurants WHERE Restaurant = '"+name+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            String website = c.getString(0);
            buffer.append("" + website);
            Log.i(TAG, website);
        }
        return buffer.toString();
    }

    public String getRating(String name){
        Log.i(TAG, "Rating");
        c = db.rawQuery("SELECT Rating FROM Restaurants WHERE Restaurant = '"+name+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){
            String rating = c.getString(0);
            buffer.append("" + rating);
            Log.i(TAG, rating);
        }
        return buffer.toString();
    }

    public static double distancefunc(double lat1, double lat2, double lon1, double lon2) {

        double dlon = Math.toRadians(lon2 - lon1);
        double dlat = Math.toRadians(lat2 - lat1);
        double a = Math.sin(dlat/2) * Math.sin(dlat/2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dlon/2) * Math.sin(dlon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 3956;

        // calculate the result
        return(c * r);
    }
}
