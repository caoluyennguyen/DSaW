package com.nguyenhongphuc98.dsaw.data;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DataManager {

    FirebaseDatabase database;

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
        database = FirebaseDatabase.getInstance();
    }

    public DataManager(Context c) {
        database = FirebaseDatabase.getInstance();
        mContext=c;
    }

    public void TestConnectDB() {
        final DatabaseReference organs_Reference = database.getReference("message");
        organs_Reference.setValue("this is test connenction from nguyenhongphuc98");
    }
}
