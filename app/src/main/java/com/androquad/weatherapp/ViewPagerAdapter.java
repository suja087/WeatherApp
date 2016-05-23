package com.androquad.weatherapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

/**
 * Created by sujon on 03/04/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
String url;
    public ViewPagerAdapter( FragmentManager fm) {
        super(fm);
            }


    @Override
    public Fragment getItem(int position) {
        /** Show a Fragment based on the position of the current screen */
        if (position == 0) {
            return new Fragment1();
        } else
            return new Fragment2();
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}

