package com.fullcourt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;

public class MainActivity extends AppCompatActivity {

    // private FusedLocationProviderClient client = new FusedLocationProviderClient(this);
    protected GeoDataClient mGeoDataClient;

    public MainActivity(){
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, Nevada!");
        myRef.push();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(this, null);

        // Construct a PlaceDetectionClient.
        PlaceDetectionClient mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);

    }
}
