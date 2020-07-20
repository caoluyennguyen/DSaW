package com.nguyenhongphuc98.dsaw;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nguyenhongphuc98.dsaw.data.DataCenter;
import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.Account;
import com.nguyenhongphuc98.dsaw.data.model.AnswerViewModel;
import com.nguyenhongphuc98.dsaw.data.network.DataService;
import com.nguyenhongphuc98.dsaw.utils.CurrentLocation;
import com.nguyenhongphuc98.dsaw.utils.Geo;
import com.nguyenhongphuc98.dsaw.utils.GeoHandle;
import com.nguyenhongphuc98.dsaw.utils.LocationTrack;
import com.nguyenhongphuc98.dsaw.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {
    private int mMenuSet = 2;

    // Tracking location
    private ArrayList<String> permissionsToRequest;
    private ArrayList permissionsRejected = new ArrayList();
    private ArrayList permissions = new ArrayList();

    private final static int ALL_PERMISSIONS_RESULT = 101;
    LocationTrack locationTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.activity = this;
        DataManager d = DataManager.Instance(getApplicationContext());
        //d.TestConnectDB();

        //fetch data in background
        DataService.Instance().updateCovidStatistic();

        //set current account after login to using later
        MutableLiveData<Account> user = new MutableLiveData<>();
        DataManager.Instance().fetchAccountById("184331234",user);
        //DataManager.Instance().fetchAccountById(userId,user);
        user.observe(this, new Observer<Account>() {
            @Override
            public void onChanged(Account account) {
                //DataCenter.currentUser = account;
                locationTrack = new LocationTrack(MainActivity.this);

                if (locationTrack.canGetLocation()) {

                    double longitude = locationTrack.getLongitude();
                    double latitude = locationTrack.getLatitude();

                    DataCenter.currentLocation = new CurrentLocation(latitude, longitude);

                    //Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_LONG).show();
                    //Log.e("LOCATION", "onCreate: location:"+longitude +"-"+latitude);
                } else {

                    locationTrack.showSettingsAlert();
                }
            }
        });

        BottomNavigationView navView = findViewById(R.id.nav_view);

        if (mMenuSet == 1)
            navView.inflateMenu(R.menu.bottom_nav_menu);
        else
            navView.inflateMenu(R.menu.bottom_nav_menu_admin);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        /*AppBarConfiguration appBarConfiguration;

            appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_home, R.id.navigation_news)
                    .build();*/

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        ///Test get name of location
        //GeoHandle handle = new GeoHandle();
        //Geo.getAddressFromLocation(10.877898, 106.807128,getApplicationContext(),handle);

        /// Location tracking =======================================================================
        permissions.add(ACCESS_FINE_LOCATION);
        permissions.add(ACCESS_COARSE_LOCATION);

        permissionsToRequest = findUnAskedPermissions(permissions);
        //get the permissions we have asked for before but are not granted..
        //we will store this in a global list to access later.


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (permissionsToRequest.size() > 0)
                requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
        }


        /// Test fetch answers
//        MutableLiveData<List<AnswerViewModel>> answers = new MutableLiveData<>();
//        DataManager.Instance().fetchAnswerFor(answers,"survey1_key");
    }

    private ArrayList findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList result = new ArrayList();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case ALL_PERMISSIONS_RESULT:
                for (String perms : permissionsToRequest) {
                    if (!hasPermission(perms)) {
                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {


//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
//                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
//                                    new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                                                requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
//                                            }
//                                        }
//                                    });
//                            return;
//                        }
//                    }

                }

                break;
        }

    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationTrack.stopListener();
    }
}
