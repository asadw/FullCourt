package com.fullcourt;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

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

    public void saveLocation(String uid, String place) {
        uid_db_ref = FirebaseDatabase.getInstance().getReference(uid);
        DatabaseReference placesReference = uid_db_ref.child("places");
        placesReference.push();
        DatabaseReference placeReference = placesReference.child(place);
        placeReference.setValue("I luv dis place");
        placeReference.push();
    }
}
