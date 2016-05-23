package com.androquad.weatherapp;

/**
 * Created by sujon on 03/04/2016.
 */
public class CityWeatherObject {
    private String district;
    private String sunrise;
    private String sunset;
    private String temparature;
    private String date;
    private String humidity;
    private String temp;
    private String pressure;
    private String wind;

    public CityWeatherObject(String district, String sunrise, String sunset, String temparature, String date, String humidity, String temp, String pressure, String wind) {
        setDistrict(district);
        setSunrise(sunrise);
        setSunset(sunset);
        setTemparature(temparature);
        setDate(date);
        setHumidity(humidity);
        setTemp(temp);
        setPressure(pressure);
        setWind(wind);
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getTemparature() {
        return temparature;
    }

    public void setTemparature(String temparature) {
        this.temparature = temparature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
