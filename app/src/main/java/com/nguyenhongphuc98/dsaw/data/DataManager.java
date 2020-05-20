package com.nguyenhongphuc98.dsaw.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nguyenhongphuc98.dsaw.data.model.PublicData;

public class DataManager {

    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseRef;

    Context mContext;

    private static DataManager _instance;
    public static DataManager Instance() {
        if(_instance == null) {
            _instance = new DataManager();
        }

        return _instance;
    }

    public static DataManager Instance(Context c) {
        if(_instance == null){
            _instance = new DataManager(c);
        }

        return _instance;
    }


    public DataManager() {
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference();
    }

    public DataManager(Context c) {
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference();
        mContext=c;
    }

    public void TestConnectDB() {
        final DatabaseReference organs_Reference = mDatabase.getReference("message");
        organs_Reference.setValue("this is test connenction from nguyenhongphuc98");
    }

    // Public data part
    public void SaveStatistic(final PublicData data){

        //update time format: "2020-05-19T23:00:00.000Z"
        String date = data.getUpdate_date();

        // Try to get curent static of this date to update
        Query query=mDatabaseRef.child("PublicData").orderByChild("update_date").equalTo(date);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        mDatabaseRef.child("PublicData").child(snapshot.getKey()).setValue(data);
                    }
                } else {
                    Log.d("TAGGGG", "onDataChange: new update");
                    mDatabaseRef.child("PublicData").push().setValue(data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
