package com.example.quakereport;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mMagnitude;
        public final TextView mPrimaryLocation;
        public final TextView mLocationOffset;

        public final TextView mDate;
        public final TextView mTime;

        public ViewHolder(View view) {
            super(view);
            mMagnitude = (TextView) view.findViewById(R.id.magnitude);
            mLocation = (TextView) view.findViewById(R.id.location);
            mDate = (TextView) view.findViewById(R.id.date);
            mTime = (TextView) view.findViewById(R.id.time);
        }

        public void bindPlace(Earthquake currentEarthquake) {
            mMagnitude.setText(currentEarthquake.getMagnitude());
            mLocation.setText(currentEarthquake.getLocation());

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
