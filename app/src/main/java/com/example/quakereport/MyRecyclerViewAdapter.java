package com.example.quakereport;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.graphics.drawable.GradientDrawable;



public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private final ArrayList<Earthquake> mEarthquakes;

    //constructor
    public MyRecyclerViewAdapter(ArrayList<Earthquake> earthquakes) {
        mEarthquakes = earthquakes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
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

    //Return formatted into string magnitude
    private String formatMagnitude(Earthquake earthquake){
        DecimalFormat formatter = new DecimalFormat("0.0");
        String formattedMagnitude = formatter.format(earthquake.getMagnitude());
        return formattedMagnitude;
    }

    //Return specific magnitude color
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(mContext, magnitudeColorResourceId);
    }

    // Return array of strings that contains location primary and location offset for the current earthquake
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
                   //MAGNITUDE
            // Set the proper background color on the magnitude circle.
            // Fetch the background from the TextView, which is a GradientDrawable.
            GradientDrawable magnitudeCircle = (GradientDrawable) mMagnitude.getBackground();
            // Get the appropriate background color based on the current earthquake magnitude
            int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
            // Set the color on the magnitude circle
            magnitudeCircle.setColor(magnitudeColor);
            //Set text to the magnitude view
            mMagnitude.setText(formatMagnitude(currentEarthquake));

                    //LOCATION
            String location = currentEarthquake.getLocation();
            //get location formatted
            String[] formattedLocationArray = formatLocation(location);
            //set the textView
            mLocationOffset.setText(formattedLocationArray[0]);
            mLocationPrimary.setText(formattedLocationArray[1]);

                    //DATE
            // Create a new Date object from the time in milliseconds of the earthquake
            Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());
            //get date formatted into string
            String formattedDate = formatDate(dateObject);
            //set the textView
            mDate.setText(formattedDate);
            //get time formatted
            String formattedTime = formatTime(dateObject);
            //set the textView
            mTime.setText(formattedTime);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

}
