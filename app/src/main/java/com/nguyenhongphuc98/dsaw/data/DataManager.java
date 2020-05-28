package com.nguyenhongphuc98.dsaw.data;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nguyenhongphuc98.dsaw.data.model.Account;
import com.nguyenhongphuc98.dsaw.data.model.Question;
import com.nguyenhongphuc98.dsaw.data.model.Warning;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    FirebaseAuth mAuth;
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
        mAuth = FirebaseAuth.getInstance();
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

    public void GetUserData(String id, final TextView name, final TextView identity, final TextView birthday, final TextView phonenumber)
    {
        try {
            Query query = mDatabase.getReference("Account").orderByChild("role").equalTo(id);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Account account = snapshot.getValue(Account.class);

                            /*DataCenter.userName = account.getUsername();
                            DataCenter.identity = account.getIdentity();
                            DataCenter.birthday = account.getBirthday();
                            DataCenter.phoneNumber = account.getPhonenumber();*/

                            name.setText(account.getUsername());
                            identity.setText(account.getIdentity());
                            birthday.setText(account.getBirthday());
                            phonenumber.setText(account.getPhonenumber());
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

    public void GetNumberOfQuestion(final int amount)
    {
        try {
            Query query = mDatabase.getReference("Question").orderByChild("survey").equalTo("survey1_key");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

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

    public void AddNewQuestion(String question, ArrayList<String> lsAnswer, String type)
    {
        Question newQues = new Question("4", lsAnswer, "survey1_key", question, type);
        //mDatabaseRef.child("Question").child("question4_key").setValue(newQues);

        try{
            //create new node event
            String key=mDatabaseRef.child("Question").push().getKey();
            newQues.setId(key);
            newQues.setSurvey("survey1_key");

            //save to firebase
            Task task= mDatabaseRef.child("Question").child(key).setValue(newQues);
            task.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("DataManager",e.toString());

                }
            }).addOnSuccessListener(new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {
                    Log.d("DataManager","Create new question success");
                }
            });
        }
        catch (Exception e){
            Log.d("DataManager",e.toString());
        }
    }
}
