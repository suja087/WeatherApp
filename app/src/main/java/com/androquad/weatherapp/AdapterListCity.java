package com.androquad.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Reza on 2-Apr-2016.
 */
public class AdapterListCity extends ArrayAdapter implements Filterable {

    ArrayList<City> cityListOrig, cityListTemp;
    Context context;

    public AdapterListCity(Context context, ArrayList<City> cityList) {
        super(context, R.layout.row_city_list,cityList);
        this.cityListOrig = this.cityListTemp = cityList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cityListTemp.size(); //super.getCount();
    }

    @Override
    public City getItem(int position) {
        return cityListTemp.get(position); //super.getItem(position);
    }

    class CityViewHolder{
        TextView cityTV;

        public CityViewHolder(View view) {
            cityTV = (TextView) view.findViewById(R.id.cityTV);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;
        CityViewHolder holder=null;

        //City wed = cityListTemp.get(position);

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_city_list,null);
            holder=new CityViewHolder(row);
            row.setTag(holder);

        }else{
            holder= (CityViewHolder) row.getTag();
        }

        holder.cityTV.setText(cityListTemp.get(position).getName());

        return row;
    }

    @Override
    public Filter getFilter(){
        return new Filter(){
            @Override
            protected FilterResults performFiltering(CharSequence charSequence){
                FilterResults results = new FilterResults();

                //If there's nothing to filter on, return the original data for your list
                if(charSequence == null || charSequence.length() == 0) {
                    results.values = cityListOrig;
                    results.count = cityListOrig.size();
                }
                else {
                    ArrayList<City> filteredCities = new ArrayList<City>();

                    for(City city : cityListOrig) {
                        //In this loop, you'll filter through originalData and compare each item to charSequence.
                        //If you find a match, add it to your new ArrayList

                        //if(city.toString().toLowerCase().contains(charSequence)){
                        if (city.getName().toUpperCase().startsWith(charSequence.toString().toUpperCase())){
                            filteredCities.add(city);
                        }
                    }

                    results.values = filteredCities;
                    results.count = filteredCities.size();
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                // Now we have to inform the adapter about the new list filtered
                cityListTemp = (ArrayList<City>)filterResults.values;
                if (filterResults.count == 0)
                    notifyDataSetInvalidated();
                else {
                    notifyDataSetChanged();
                }
            }
        };
    }
}

