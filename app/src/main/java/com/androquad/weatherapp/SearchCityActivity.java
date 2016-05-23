package com.androquad.weatherapp;


import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import java.util.Locale;

/**
 * Created by Reza on 01-Apr-2016.
 */

public class SearchCityActivity extends BaseActivity {

    ListView listView;
    EditText myFilter;

    ArrayList<City> cities = new ArrayList<City>();
    AdapterListCity dataAdapter= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);



        listView = (ListView) findViewById(R.id.listCity);
        myFilter = (EditText) findViewById(R.id.myFilter);




        Toast.makeText(this, "LOCATION INTENT !!", Toast.LENGTH_SHORT).show();

        cities = new XMLParser(this).getCities();
        dataAdapter=new AdapterListCity(getApplicationContext(), cities);

        listView.setAdapter(dataAdapter);
        listView.setVisibility(View.INVISIBLE);
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                City city = dataAdapter.getItem(position);
                String cityName = city.getName();
                String pid = String.valueOf(city.getId());
                Preference preference = new Preference(SearchCityActivity.this);
                preference.saveData(cityName,pid);
                Intent intent = new Intent(SearchCityActivity.this, MainActivity.class);
                intent.putExtra("key", pid);

                startActivity(intent);
            }
        });


        myFilter.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (listView.getVisibility() != View.VISIBLE)
                    listView.setVisibility(View.VISIBLE);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dataAdapter.getFilter().filter(s.toString().toLowerCase(Locale.US));
                //((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
            }
        });
    }

}
