package com.androquad.weatherapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends BaseActivity  {
ArrayList<WeatherForecastObject> forecasts ;
    WeatherForecastObject weatherForecastObject;
    String woeid;
    String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20%28select%20woeid%20from%20geo.places%281%29%20where%20woeid%3D%22";
    String url1 = "%22)%20and%20u%3D'c'&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
    String cityData, woeidData;

    private ProgressDialog pDialog;
    Button button;
    TextView districtTv,temparatureTv,dateTv,sunriseTv,sunsetTv,tempTv,windTv,pressureTv,humidityTv;
    ImageView tempIv;
    static  String finalUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        //button= (Button) findViewById(R.id.button);
        districtTv = (TextView) findViewById(R.id.districtTv);
        temparatureTv = (TextView) findViewById(R.id.temparatureTv);
        dateTv = (TextView) findViewById(R.id.dateTv);
        sunriseTv = (TextView) findViewById(R.id.sunriseTv);
        sunsetTv = (TextView) findViewById(R.id.sunsetTv);
        tempTv = (TextView) findViewById(R.id.tempTv);
        windTv = (TextView) findViewById(R.id.windTv);
        pressureTv =(TextView) findViewById(R.id.pressureTv);
        humidityTv = (TextView) findViewById(R.id.humidityTv);
        tempIv= (ImageView) findViewById(R.id.tempIv);
        forecasts = new ArrayList<WeatherForecastObject>();

        Preference preference = new Preference(getApplicationContext());
        woeidData = preference.retrieveWoeidData();
        if((woeidData.equals("NOWOEID"))&&(getIntent().getStringExtra("key")==null)){
            Intent intent = new Intent(getApplicationContext(), SearchCityActivity.class);
            startActivity(intent);
        }else if((woeidData.equals("NOWOEID"))&&(getIntent().getStringExtra("key")!=null)){
            woeid = getIntent().getStringExtra("key").toString();
            finalUrl = url + woeid + url1;
            weatherInfo();
        }else if ((!woeidData.equals("NOWOEID"))&&(getIntent().getStringExtra("key")==null)){
            woeid = woeidData;
            finalUrl = url + woeid + url1;
            weatherInfo();
        }else if ((!woeidData.equals("NOWOEID"))&&(getIntent().getStringExtra("key")!=null)) {
            woeid = getIntent().getStringExtra("key").toString();
            finalUrl = url + woeid + url1;
            weatherInfo();
        }



        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForecastActivity.class);
                weatherForecastObject = new WeatherForecastObject()
                startActivity(intent);
            }
        });*/
    }



    private void weatherInfo() {

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, finalUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

               LongOperation.onResponse(response);

                districtTv.setText(LongOperation.cityWeatherObject.getDistrict());
                dateTv.setText(LongOperation.cityWeatherObject.getDate());
                temparatureTv.setText(LongOperation.cityWeatherObject.getTemparature());
                sunriseTv.setText(LongOperation.cityWeatherObject.getSunrise());
                sunsetTv.setText(LongOperation.cityWeatherObject.getSunset());
                tempTv.setText(LongOperation.cityWeatherObject.getTemp());
                windTv.setText(LongOperation.cityWeatherObject.getWind());
                pressureTv.setText(LongOperation.cityWeatherObject.getPressure());
                humidityTv.setText(LongOperation.cityWeatherObject.getHumidity());

                if (pDialog.isShowing())
                    pDialog.dismiss();

                try {
                    JSONObject object = response.getJSONObject("query");
                    JSONObject results=object.getJSONObject("results");
                    JSONObject channel=results.getJSONObject("channel");
                    JSONObject item=channel.getJSONObject("item");
                    JSONArray forecast = item.getJSONArray("forecast");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NoConnectionError){
                    Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AppController ac = AppController.getInstance();
        ac.addToRequestQueue(request);
    }



}
