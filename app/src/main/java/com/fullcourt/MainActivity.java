package com.fullcourt;

import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    protected GeoDataClient mGeoDataClient;
    private DatabaseReference myRef;
    private DatabaseReference myOtherRef;

    public MainActivity(){

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
        myOtherRef = database.getReference("some_data");
        myRef.setValue(Calendar.getInstance().getTime().toString());
        myRef.push();

        myOtherRef.setValue(System.currentTimeMillis());
        myOtherRef.push();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(this, null);

        // Construct a PlaceDetectionClient.
        //PlaceDetectionClient mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);

        Context baseContext = getApplicationContext();

        int permission = ContextCompat.checkSelfPermission(baseContext, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            //Task<PlaceLikelihoodBufferResponse> placeResult = mPlaceDetectionClient.getCurrentPlace(null);
/*            placeResult.addOnCompleteListener(new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
                @Override
                public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                    PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();
                    for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                        Log.i("TAG", String.format("Place '%s' has likelihood: %g", placeLikelihood.getPlace().getName(), placeLikelihood.getLikelihood()));
                    }
                    likelyPlaces.release();
                }
            });*/
        } else {
            int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 99;
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

        final Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myRef.setValue(Calendar.getInstance().getTime().toString());
                myRef.push();
            }
        });
    }
}
