package com.androquad.weatherapp;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by sujon on 03/04/2016.
 */

public class LongOperation  {

    static CityWeatherObject cityWeatherObject;


            static final void onResponse(JSONObject response) {
                try {
                    JSONObject object = response.getJSONObject("query");
                    JSONObject results=object.getJSONObject("results");
                    JSONObject channel=results.getJSONObject("channel");
                    JSONObject location=channel.getJSONObject("location");
                    String name=location.getString("city");
                 //   +", "+location.getString("country")
                  //  districtTv.setText(name);

                    JSONObject units=channel.getJSONObject("units");
                    String distance = units.getString("distance");
                    String pressureU = units.getString("pressure");
                    String speedU = units.getString("speed");
                    String temperatureU = units.getString("temperature");


                    JSONObject astronomy=channel.getJSONObject("astronomy");
                    String sunrise = astronomy.getString("sunrise");
                  //  sunriseTv.setText(sunrise);
                    String sunset = astronomy.getString("sunset");
                  //  sunsetTv.setText(sunset);

                    JSONObject item=channel.getJSONObject("item");
                    JSONObject condition=item.getJSONObject("condition");
                    String temp = condition.getString("temp");
                    //temparatureTv.setText(temp + (char) 0x00B0 + temperatureU);
                    String temparature = temp + (char) 0x00B0 + temperatureU;


                    String date = condition.getString("date");
                    String text = condition.getString("text");
                    String substr=date.substring(0, 16);
                   // dateTv.setText(substr);

                    String description=item.getString("description");
                    String photo_url_str = description.substring(description.indexOf("src=") + 5, description.indexOf("gif") + 3);
                    //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

                    JSONObject atmosphere=channel.getJSONObject("atmosphere");
                    String humidity = atmosphere.getString("humidity");
                    String pressure = atmosphere.getString("pressure");
                    String visibility = atmosphere.getString("visibility");

                    //pressureTv.setText(pressure + "" + pressureU);
                    String pressureS = pressure + "" + pressureU;
                    //humidityTv.setText(humidity + "%");
                    String humidityS = humidity + "%";

                    JSONObject wind=channel.getJSONObject("wind");
                    String speed = wind.getString("speed");
                    String direction = wind.getString("direction");
                    //windTv.setText(speed + speedU + " " + direction + "d");
                    String windS = speed + speedU + " " + direction + "d";


                    JSONArray forecast = item.getJSONArray("forecast");

                    JSONObject forecast1=forecast.getJSONObject(0) ;
                    String high = forecast1.getString("high");
                    String low = forecast1.getString("low");
                    //tempTv.setText(low + (char) 0x00B0 + temperatureU + "   " + high + (char) 0x00B0 + temperatureU );
                    String tempS = low + (char) 0x00B0 + temperatureU + "   " + high + (char) 0x00B0 + temperatureU;

                 cityWeatherObject = new CityWeatherObject(name,sunrise,sunset,temparature,substr,humidityS,tempS,pressureS,windS);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }



