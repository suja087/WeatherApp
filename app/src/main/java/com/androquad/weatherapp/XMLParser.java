package com.androquad.weatherapp;

import android.content.Context;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Reza on 3-Apr-2016.
 */
public class XMLParser {
    private static final String ATTR_ID = "id";
    private static final String NODE_CITY = "city";

    private Context context;

    public XMLParser(Context context) {
        this.context = context;
    }

    //http://developer.android.com/training/basics/network-ops/xml.html
    private static String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private static String readCity(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, NODE_CITY);
        String result = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, NODE_CITY);
        return result;
    }

    public ArrayList<City> getCities() {
        ArrayList<City> cities = new ArrayList<City>();
        try {
            XmlPullParser parser= context.getResources().getXml(R.xml.cities);

            while (parser.getEventType()!=XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType()==XmlPullParser.START_TAG) {
                    if (parser.getName().equals(NODE_CITY)) {
                        int woeid = Integer.parseInt(parser.getAttributeValue(null,ATTR_ID));
                        String cityName = readCity(parser);

                        City city = new City();
                        city.setId(woeid);
                        city.setName(cityName);
                        cities.add(city);
                    }
                }
                parser.next();
            }
            //Toast.makeText(context, "XML Node Length=" + cities.size() + ", w=" + w + ", st=" + st + ", nt=" + nt, Toast.LENGTH_SHORT).show();

            Collections.sort(cities,
                    new Comparator<City>() {
                public int compare(City ct1, City ct2) {
                    return ct1.getName().compareToIgnoreCase(ct2.getName());
                }
            });

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
