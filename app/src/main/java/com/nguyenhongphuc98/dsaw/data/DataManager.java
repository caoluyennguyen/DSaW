package com.nguyenhongphuc98.dsaw.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nguyenhongphuc98.dsaw.data.model.Account;
import com.nguyenhongphuc98.dsaw.data.model.Warning;

public class DataManager {

    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseRef;

    //private StorageReference mStorageRef;

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
        internalInit();
    }

    public DataManager(Context c) {
        internalInit();
        mContext=c;
    }

    private void internalInit() {
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference();
        //mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    public void TestConnectDB() {
        final DatabaseReference organs_Reference = mDatabase.getReference("message");
        organs_Reference.setValue("this is test connenction from nguyenhongphuc98");
    }

    public void CreateWarning(Warning warning)
    {
        mDatabaseRef.child("Warnings").child("2").setValue(warning);
    }

    public void GetUserData(String id)
    {
        try {
            Query query = mDatabase.getReference("Account").orderByChild("role").equalTo(id);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Account account = snapshot.getValue(Account.class);

                            DataCenter.userName = account.getUsername();
                            DataCenter.identity = account.getIdentity();
                            DataCenter.birthday = account.getBirthday();
                            DataCenter.phoneNumber = account.getPhonenumber();
                        }
                    }
                    else Log.e("DataManager","Account not found");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        catch (Exception e){
            Log.e("DataManager","Error get account: " + e.getMessage());
        }
    }

    public void UpdateUser(final String name, final String identity, final String birthday, final String phoneNumber)
    {
        try {
            Query query = mDatabase.getReference("Account").orderByChild("role").equalTo("manager");

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Account account = snapshot.getValue(Account.class);
                            account.setUsername(name);
                            account.setIdentity(identity);
                            account.setBirthday(birthday);
                            account.setPhonenumber(phoneNumber);
                            mDatabase.getReference("Account").child(snapshot.getKey()).setValue(account);
                            Log.e("DataManager", "Update user successfully");
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });
        }
        catch (Exception e){
            Log.e("DataManager","Error update user: " + e.getMessage());
        }
    }
}
