package com.example.quakereport;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Earthquake> mEarthquakes;

    //constructor
    public MyRecyclerViewAdapter(ArrayList<Earthquake> earthquakes) {
        mEarthquakes = earthquakes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bindPlace(mEarthquakes.get(position));
    }

    @Override
    public int getItemCount() {
        return mEarthquakes.size();
    }

    //Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }


     // Return the formatted date string (i.e. "4:30 PM") from a Date object.
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
    // Returns array of strings that contains location primary and location offset for the current earthquake
    private String[] formatLocation(String location){
        String[] locationArray = new String[2];
        String locationPrimary, locationOffset;
        int index = location.indexOf(" of");
        if (index > 0){
            locationOffset = location.substring(0, index+3);
            locationPrimary = location.substring(index+4);
        } else {
            locationOffset = "Near the";
            locationPrimary = location;
        }
        locationArray[0] = locationOffset;
        locationArray[1] = locationPrimary;
        return locationArray;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mMagnitude;
        public final TextView mLocationPrimary;
        public final TextView mLocationOffset;
        public final TextView mDate;
        public final TextView mTime;

        public ViewHolder(View view) {
            super(view);
            mMagnitude = (TextView) view.findViewById(R.id.magnitude);
            mLocationPrimary = (TextView) view.findViewById(R.id.location_primary);
            mLocationOffset = (TextView) view.findViewById(R.id.location_offset);
            mDate = (TextView) view.findViewById(R.id.date);
            mTime = (TextView) view.findViewById(R.id.time);
        }

        public void bindPlace(Earthquake currentEarthquake) {
            mMagnitude.setText(currentEarthquake.getMagnitude());
            String location = currentEarthquake.getLocation();

            String[] formattedLocationArray = formatLocation(location);

            mLocationOffset.setText(formattedLocationArray[0]);
            mLocationPrimary.setText(formattedLocationArray[1]);

            // Create a new Date object from the time in milliseconds of the earthquake
            Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());
            //get date formatted into string
            String formattedDate = formatDate(dateObject);
            //set the textView
            mDate.setText(formattedDate);

            //get time formatted
            String formattedTime = formatTime(dateObject);
            mTime.setText(formattedTime);
        }



        @Override
        public String toString() {
            return super.toString();
        }
    }
}
