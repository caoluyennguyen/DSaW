package com.nguyenhongphuc98.dsaw.data;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nguyenhongphuc98.dsaw.data.model.Account;
import com.nguyenhongphuc98.dsaw.data.model.Area;
import com.nguyenhongphuc98.dsaw.data.model.Case;
import com.nguyenhongphuc98.dsaw.data.model.News;
import com.nguyenhongphuc98.dsaw.data.model.PublicData;
import com.nguyenhongphuc98.dsaw.data.model.RouteData;
import com.nguyenhongphuc98.dsaw.data.model.Survey;
import com.nguyenhongphuc98.dsaw.data.model.SurveyModel;
import com.nguyenhongphuc98.dsaw.ui.home.HomeDelegate;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManager {

    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseRef;

    private StorageReference mStorageRef;

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
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    public void TestConnectDB() {
        final DatabaseReference organs_Reference = mDatabase.getReference("message");
        organs_Reference.setValue("this is test connenction from nguyenhongphuc98");
    }

    // Public data part
    // area : vn | tg
    public void SaveStatistic(final PublicData data){

        //update time format: "2020-05-19T23:00:00.000Z"
        String date = data.getUpdate_date();

//        PublicData d1 = new PublicData("tg","nil",
//                "Thế Giới","40",
//                "10","10",
//                "2020-05-19T23:00:00.000Z");
//        mDatabaseRef.child("PublicData").push().setValue(d1);
//
//        PublicData d2 = new PublicData("tg","nil",
//                "Thế Giới","150",
//                "7","100",
//                "2020-05-18T23:00:00.000Z");
//        mDatabaseRef.child("PublicData").push().setValue(d2);
//
//        PublicData d3 = new PublicData("tg","nil",
//                "Thế Giới","158",
//                "9","110",
//                "2020-05-17T23:00:00.000Z");
//        mDatabaseRef.child("PublicData").push().setValue(d3);
//
//        PublicData d4 = new PublicData("tg","nil",
//                "Thế Giới","220",
//                "5","230",
//                "2020-05-16T23:00:00.000Z");
//        mDatabaseRef.child("PublicData").push().setValue(d4);
//
//        PublicData d5 = new PublicData("tg","nil",
//                "Thế Giới","80",
//                "23","99",
//                "2020-05-15T23:00:00.000Z");
//        mDatabaseRef.child("PublicData").push().setValue(d5);
//
//        PublicData d6 = new PublicData("tg","nil",
//                "Thế Giới","220",
//                "45","44",
//                "2020-05-14T23:00:00.000Z");
//        mDatabaseRef.child("PublicData").push().setValue(d6);
//
//        PublicData d7 = new PublicData("tg","nil",
//                "Thế Giới","300",
//                "20","250",
//                "2020-05-13T23:00:00.000Z");
//        mDatabaseRef.child("PublicData").push().setValue(d7);


        // Try to get curent static of this date to update
        Query query=mDatabaseRef.child("PublicData").orderByChild("update_date").equalTo(date);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    boolean found = false;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // co ton tai ngay hom nay nhung khong chac la co cua khu vuc can luu hay khong

                        if (snapshot.getValue(PublicData.class).getArea().equals(data.getArea())) {
                            mDatabaseRef.child("PublicData").child(snapshot.getKey()).setValue(data);
                            found = true;
                        }
                    }
                    // chua co du lieu ngay nay` cho khu vuc dang xet, chung ta se tao moi
                    if (found == false) {
                        Log.d("TAGGGG", "onDataChange: new update");
                        mDatabaseRef.child("PublicData").push().setValue(data);
                    }
                } else {
                    Log.d("TAGGGG", "onDataChange: new update");
                    // chua co khu vuc nao co du lieu ngay nay`
                    mDatabaseRef.child("PublicData").push().setValue(data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void fetchPublicData(final MutableLiveData<List<PublicData>> ls, final String area) {

        // arrange increate so we get lastest date
        Query query = mDatabaseRef.child("PublicData").orderByChild("update_date").limitToLast(12);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    List<PublicData> newList = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        PublicData p = snapshot.getValue(PublicData.class);
                        if (p.getArea().equals(area))
                            newList.add(p);
                    }

                    ls.setValue(newList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // count number of reponse for special survey of a user
    int personalSurveyCount = 0;
    int relativesSurveyCount = 0;
    int reportSurveyCount = 0;
    public void fetchCountResponseFor(final MutableLiveData<List<SurveyModel>> ls,
                                      final List<SurveyModel> newList,
                                      final SurveyModel model,
                                      final String userID,
                                      final int numberOfSurvey,
                                      final String type) {

        Query query = mDatabaseRef.child("Answers").child(model.getId()).child(userID);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long count = 0;
                if (dataSnapshot.exists()) {
                    count = dataSnapshot.getChildrenCount();
                }
                model.setCount(String.valueOf(count));

                if (type.equals("personal_medical")) {
                    personalSurveyCount ++;

                    // has process to last survey
                    if (personalSurveyCount == numberOfSurvey) {
                        ls.setValue(newList);
                        //reset for next fetch
                        personalSurveyCount = 0;
                        return;
                    }

                }

                if (type.equals("relatives_medical")) {
                    relativesSurveyCount ++;

                    // has process to last survey
                    if (relativesSurveyCount == numberOfSurvey) {
                        ls.setValue(newList);
                        //reset for next fetch
                        relativesSurveyCount = 0;
                        return;
                    }
                }

                if (type.equals("report")) {
                    reportSurveyCount ++;

                    // has process to last survey
                    if (reportSurveyCount == numberOfSurvey) {
                        ls.setValue(newList);
                        //reset for next fetch
                        reportSurveyCount = 0;
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {  }
        });
    }

    int countSurveyResposeAdmin = 0;
    public void fetchCountResponseForAdmin(final MutableLiveData<List<SurveyModel>> ls,
                                      final List<SurveyModel> newList,
                                      final SurveyModel model,
                                      final int numberOfSurvey) {

        Query query = mDatabaseRef.child("Answers").child(model.getId());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long count = 0;
                if (dataSnapshot.exists()) {
                    // get list uid response this survey
                    Map<String, Object> userReponse = (HashMap<String, Object>) dataSnapshot.getValue();
                    //Log.d("TAGGG", "onDataChange: "+ userReponse);
                    for (String s : userReponse.keySet()) {
                        ArrayList<Map<String,Object>> ls = (ArrayList<Map<String,Object>>) userReponse.get(s);
                        count += ls.size();
                    }
                }
                model.setCount(String.valueOf(count));


                countSurveyResposeAdmin++;

                // has process to last survey
                if (countSurveyResposeAdmin == numberOfSurvey) {
                    ls.setValue(newList);
                    //reset for next fetch
                    countSurveyResposeAdmin = 0;
                    return;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {  }
        });
    }

    // List result depend on uid so we acn using it for fetch in user or admin view
    public void fetchListSurveyByType(final MutableLiveData<List<SurveyModel>> ls, final String type,final String uid) {

        Query query = null;
        if (uid.equals("admin"))
            query = mDatabaseRef.child("Survey");
        else
            query = mDatabaseRef.child("Survey").orderByChild("type").equalTo(type);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    List<SurveyModel> newList = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Survey p = snapshot.getValue(Survey.class);
                        SurveyModel s = new SurveyModel(p.getId(),"0",p.getName());
                        newList.add(s);

                        if (uid.equals("admin"))
                            fetchCountResponseForAdmin(ls,newList,s,(int)dataSnapshot.getChildrenCount());
                        else
                            fetchCountResponseFor(ls,newList,s,uid,(int)dataSnapshot.getChildrenCount(), type);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public Boolean fetchAllPosts(final MutableLiveData<List<News>> lsNews){

        try {

            Query query=mDatabaseRef.child("Post").orderByChild("createtime");

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {

                        List<News> ls = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            News n = snapshot.getValue(News.class);
                            ls.add(n);
                        }
                        lsNews.setValue(ls);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        catch (Exception e){
            Log.e("DB","err get posts (news): "+e.getMessage());
            return false;
        }

        return true;
    }

    public void fetchPhoto(String fileName, final ImageView result) {
        mStorageRef.child("posts/"+fileName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(mContext)
                        .load(uri)
                        .into(result);
                Log.e("DB","downloaded a photo:"+uri.getPath());
            }
        });
    }

    public void updateACcount(final Account user) {

        // arrange increate so we get lastest date
        Query query = mDatabaseRef.child("Account").orderByChild("identity").equalTo(user.getIdentity());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    List<PublicData> newList = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        //actualu it shoud have just one account match :))
                        String p = snapshot.getKey();
                        mDatabaseRef.child("Account").child(p).setValue(user);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void fetchAllAreas(final MutableLiveData<List<Area>> lsAreas){

        Query query = mDatabaseRef.child("Area").orderByChild("name");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    // get list area
//                        Map<String, Object> userReponse = (HashMap<String, Object>) dataSnapshot.getValue();
//                        for (String s : userReponse.keySet()) {
//                            ArrayList<Map<String,Object>> ls = (ArrayList<Map<String,Object>>) userReponse.get(s);
//                            count += ls.size();
//                        }

                    List<Area> ls = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Area n = snapshot.getValue(Area.class);
                        ls.add(n);
                    }
                    lsAreas.setValue(ls);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void fetchAccountById(final String id, final MutableLiveData<Account> account) {

        Query query = mDatabaseRef.child("Account").orderByChild("identity").equalTo(id);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // We expect one account match
                        Account a = snapshot.getValue(Account.class);
                        account.setValue(a);
                        return;
                    }
                } else {
                    Account temp = new Account();
                    temp.setIdentity("null");
                    account.setValue(temp);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void fetchAllAccount(final MutableLiveData<List<Account>> lsAccounts) {

        Query query = mDatabaseRef.child("Account");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    List<Account> ls = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // We expect one account match
                        Account a = snapshot.getValue(Account.class);
                        ls.add(a);
                    }

                    lsAccounts.setValue(ls);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // Just manager can process case, and just case of area - which it manager
    public void fetchAllCase(final MutableLiveData<List<Case>> lsCases, String area) {

        Query query = mDatabaseRef.child("Case").orderByChild("area").equalTo(area);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Case> ls = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    //just load case in this time, mean if da lanh benh thi k con nua
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH:mm");
                    Date date = new Date();
                    String t = dateFormat.format(date);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Case a = snapshot.getValue(Case.class);
                        if (t.compareTo(a.getBegin_time()) > 0 && t.compareTo(a.getEnd_time()) < 0 )
                            ls.add(a);
                    }
                }
                lsCases.setValue(ls);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void insertCase(final Case aCase) {

        // check if exist need update status before
        // if exist id user in any case mean we have to update old and insert new
        Query query = mDatabaseRef.child("Case").orderByChild("user").equalTo(aCase.getUser());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH:mm");
                    Date date = new Date();
                    String t = dateFormat.format(date);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // end time = zzz thi moi la cai can update
                        Case a = snapshot.getValue(Case.class);
                        if (a.getEnd_time().contains("zzzzzzz") ) {
                            a.setEnd_time(t);
                            mDatabaseRef.child("Case").child(snapshot.getKey()).setValue(a);
                        }

                    }
                }

                // cho du co ton tai truoc do hay khong thi van phai them moi nhu thuong
                // de trong nay de dam bao la kiem tra truoc khi insert, k la no xu ly cai vua insert thi chet toi :v
                String key = mDatabaseRef.child("Case").push().getKey();
                aCase.setId(key);
                mDatabaseRef.child("Case").child(key).setValue(aCase);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // featch all case to statitis (for admin) so we don't care about area. get all :v
    public void fetchAllCase(final MutableLiveData<List<Case>> lsCases) {

        Query query = mDatabaseRef.child("Case");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    List<Case> ls = new ArrayList<>();
                    //just load case in this time, mean if da lanh benh thi k con nua
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH:mm");
                    Date date = new Date();
                    String t = dateFormat.format(date);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Case a = snapshot.getValue(Case.class);

                        // mean endtime = zzz moi lay :v
                        if (t.compareTo(a.getBegin_time()) > 0 && t.compareTo(a.getEnd_time()) < 0 )
                            ls.add(a);
                    }

                    lsCases.setValue(ls);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /// Update status route data of secial user
    // if exists, update, else create new
    public void updateRouteData(final RouteData routeData) {

        // check exist route of this user
        Query query = mDatabaseRef.child("RouteData").orderByChild("user").equalTo(routeData.getUser());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    // just update status tracking
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        //actualy it shoud have just one route match :))
                        String routeKey = snapshot.getKey();
                        RouteData r = snapshot.getValue(RouteData.class);
                        r.addStatus(routeData.getStatus().get(0));
                        mDatabaseRef.child("RouteData").child(routeKey).setValue(r);
//                        String keyOfStatus = mDatabaseRef.child("RouteData").child(routeKey).child("status").push().getKey();
//                        mDatabaseRef.child("RouteData").child(routeKey).child("status").child(keyOfStatus).setValue(routeData.getStatus());
                        return;
                    }
                } else {
                    // insert new data new user :)
                    String routeKey = mDatabaseRef.child("RouteData").push().getKey();
                    routeData.setId(routeKey);
                    mDatabaseRef.child("RouteData").child(routeKey).setValue(routeData);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    /// Fetch route for special user
    public void fetchRouteOf(final MutableLiveData<RouteData> routeData, String uid) {

        Query query = mDatabaseRef.child("RouteData").orderByChild("user").equalTo(uid);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        RouteData a = snapshot.getValue(RouteData.class);
                        routeData.setValue(a);
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
