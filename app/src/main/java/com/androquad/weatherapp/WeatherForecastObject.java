package com.androquad.weatherapp;

import java.io.Serializable;

/**
 * Created by sujon on 04/04/2016.
 */
public class WeatherForecastObject implements Serializable{


    private String code, date, day, high, low, text;

    public WeatherForecastObject(String code, String date, String day, String high, String low, String text) {
        this.code = code;
        this.date = date;
        this.day = day;
        this.high = high;
        this.low = low;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getText() {
        return text;
    }
}
