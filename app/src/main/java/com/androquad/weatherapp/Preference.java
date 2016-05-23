package com.androquad.weatherapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sujon on 07/03/2016.
 */
public class Preference {

    private static SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public Preference(Context context) {
        preferences = context.getSharedPreferences("city", context.MODE_PRIVATE);
        editor = preferences.edit();
        this.context = context;
    }

    public void saveData(String city, String woeid){

        editor.putString("city", city);
        editor.putString("woeid", woeid);
        editor.commit();
}
    public static  String retrieveCityData(){
        String city = preferences.getString("city", "NO CITY");
        return city;
        }
    public static String retrieveWoeidData(){

        String woeid = preferences.getString("woeid", "NOWOEID");
        return woeid;

    }
    public void delete(){
        editor.remove("city");
        editor.remove("woeid");
        editor.commit();

    }
}
