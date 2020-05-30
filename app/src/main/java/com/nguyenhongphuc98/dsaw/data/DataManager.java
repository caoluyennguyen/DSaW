package com.nguyenhongphuc98.dsaw.data;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.anychart.scales.DateTime;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nguyenhongphuc98.dsaw.data.model.Account;
import com.nguyenhongphuc98.dsaw.data.model.News;
import com.nguyenhongphuc98.dsaw.data.model.Post;
import com.nguyenhongphuc98.dsaw.data.model.Question;
import com.nguyenhongphuc98.dsaw.data.model.Warning;

import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DataManager {
    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseRef;

    StorageReference mStorageRef;

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
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    public void TestConnectDB() {
        final DatabaseReference organs_Reference = mDatabase.getReference("message");
        organs_Reference.setValue("this is test connenction from nguyenhongphuc98");
    }

    public void ProcessLogin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("DATAMANAGER", "errcallback login");
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Log.d(TAG, "signInWithEmail:failure");


                        }
                    }
                });
    }

    public boolean registerProcess(String userName, String passWord) {

        Log.d(TAG, "createAccount:" + userName);

        // [START create_user_with_userName]
        mAuth.createUserWithEmailAndPassword(userName, passWord)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            Log.d(TAG, "createUserWithEmail:success");
                            // [START send_email_verification]
                            mAuth.getCurrentUser().sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task1) {
                                            if (task1.isSuccessful()) {
                                                Log.d(TAG, "Verificate mail sent.");
                                            } else Log.w(TAG, "Send verificate mail failed.");
                                        }
                                    });
                            // [END send_email_verification]
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
        // [END create_user_with_email]

        if (mAuth.getCurrentUser() != null) return true;
        else return false;
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

    public void GetAllQuestion(final List<Question> lsQuestion)
    {
        try {
            Query query = mDatabase.getReference("Question");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Question question = snapshot.getValue(Question.class);
                            lsQuestion.add(question);
                            Log.e("DataManager","Title of question was found is " + question.getTitle());
                            Log.e("DataManager","Type of question was found is " + question.getType());
                            Log.e("DataManager","Answer of question was found is " + question.getAnswers());
                        }
                    }
                    else Log.e("DataManager","Question not found");
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
        Question newQues = new Question(lsAnswer,"4", "survey1_key", question, type);
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

    public void AddNewPost(String title, String content, String idCover)
    {
        Post post = new Post(DataCenter.userName, content, idCover, LocalDateTime.now().toString(), null, null, title, "write");

        try{
            String key=mDatabaseRef.child("Post").push().getKey();
            post.setId(key);
            Task task = mDatabaseRef.child("Post").child(key).setValue(post);
            task.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("DataManager",e.toString());
                }
            }).addOnSuccessListener(new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {
                    Log.d("DataManager","Create new post success");
                }
            });
        }
        catch (Exception e){
            Log.d("DataManager",e.toString());
        }
    }

    public String UploadFileToFirebase(String folder, Uri uriOfImage)
    {
        String id;

        if (folder.equals("posts/")){
            Long localDateTime=System.currentTimeMillis();
            id = localDateTime.toString();
        }
        else id = "1";

        String child = folder + id;
        StorageReference childRef = mStorageRef.child(child);

        UploadTask uploadTask = childRef.putFile(uriOfImage);

        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.e("DTM","save image suscess");
            }
        });
        return id;
    }
}
