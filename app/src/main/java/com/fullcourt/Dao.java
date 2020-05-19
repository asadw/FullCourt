package com.fullcourt;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Dao {

    private DatabaseReference uid_db_ref;

    private static Dao instance = null;

    public static Dao getInstance() {
        if (instance == null) {
            instance = new Dao();
        }
        return instance;
}

    public void updateTime(String uid) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        uid_db_ref = database.getReference(uid);
        uid_db_ref.push();

        DatabaseReference myOtherRef = uid_db_ref.child("time");
        myOtherRef.setValue(Calendar.getInstance().getTime().toString());
        myOtherRef.push();
    }

    public void saveLocation(String uid, String placeId, String place) {
        uid_db_ref = FirebaseDatabase.getInstance().getReference(uid);
        DatabaseReference placesReference = uid_db_ref.child("places");
        placesReference.push();
        DatabaseReference placeReference = placesReference.child(placeId);
        placeReference.setValue(place);
        placeReference.push();
    }

    public String[] loadPlaces(String uid){
        DatabaseReference places = FirebaseDatabase.getInstance().getReference(uid).child("places");
        places.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    Log.i("onDataChange", child.getKey());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("onCancelled", "ERROR CANCEL");
            }
        });

        return new String[] {"asad", "testing", "list"};
    }

    public void removeLocation(String uid, String place) {

    }
}
