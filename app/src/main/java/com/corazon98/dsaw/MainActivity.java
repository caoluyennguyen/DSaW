package com.corazon98.dsaw;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.corazon98.dsaw.data.DataCenter;
import com.corazon98.dsaw.data.DataManager;
import com.corazon98.dsaw.data.model.Case;
import com.corazon98.dsaw.data.model.Warning;
import com.corazon98.dsaw.data.network.DataService;
import com.corazon98.dsaw.utils.Utils;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.activity = this;
        DataManager.Instance(getApplicationContext());

        //fetch data in background
        DataService.Instance().updateCovidStatistic();

        // fetch all warnings to user
        MutableLiveData<Warning> mWarning = new MutableLiveData<>();
        DataManager.Instance().FetchWarning(mWarning);

        mWarning.observe(this, warning -> {
            if (warning.getCode_city() == -1 || warning.getCode_city() == DataCenter.currentUser.getCode_city())
            {
                if (warning.getCode_district() == -1 || warning.getCode_district() == DataCenter.currentUser.getCode_district())
                {
                    if (warning.getCode_ward() == -1 || warning.getCode_ward() == DataCenter.currentUser.getCode_ward())
                    {
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "CHANNEL_ID")
                                .setSmallIcon(R.drawable.warning_icon)
                                .setContentTitle(warning.getTitle())
                                .setContentText(warning.getContent())
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                        // notificationId is a unique int for each notification that you must define
                        notificationManager.notify(1, builder.build());
                    }
                }
            }
        });

        // fetch all cases to user
        MutableLiveData<List<Case>> mLsCases = new MutableLiveData<>();
        DataManager.Instance().fetchAllCase(mLsCases);

        mLsCases.observe(this, cases -> {
            int i = 2;
            for (Case c: cases) {
                //double latitude = Double.parseDouble(c.getLocation().split(",")[0]) - DataCenter.currentLocation.getLatitude();
                //double logtitude = Double.parseDouble(c.getLocation().split(",")[1]) - DataCenter.currentLocation.getLatitude();
                double distance = calculateDistance(Double.parseDouble(c.getLocation().split(",")[0]), DataCenter.currentLocation.getLatitude(),
                        Double.parseDouble(c.getLocation().split(",")[1]), DataCenter.currentLocation.getLongitude());
                Log.d("CASE", "to your location: " + distance + "km");

                if (distance < DataCenter.currentUser.getWarning_distance())
                {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "CHANNEL_ID")
                            .setSmallIcon(R.drawable.warning_icon)
                            .setContentTitle("CẢNH BÁO VÀO VÙNG NGUY HIỂM!")
                            .setContentText("Ban cách đối tượng " + c.getName() + " " + distance + " km.")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    // notificationId is a unique int for each notification that you must define
                    notificationManager.notify(i, builder.build());
                    i++;
                }
            }
        });


        BottomNavigationView navView = findViewById(R.id.nav_view);

        if (!DataCenter.currentUser.getRole().equals("manager"))
            navView.inflateMenu(R.menu.bottom_nav_menu);
        else
            navView.inflateMenu(R.menu.bottom_nav_menu_admin);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


//        ///Test get name of location
//        //GeoHandle handle = new GeoHandle();
//        //Geo.getAddressFromLocation(10.877898, 106.807128,getApplicationContext(),handle);
//
//        /// Location tracking =======================================================================
//        permissions.add(ACCESS_FINE_LOCATION);
//        permissions.add(ACCESS_COARSE_LOCATION);
//
//        permissionsToRequest = findUnAskedPermissions(permissions);
//        //get the permissions we have asked for before but are not granted..
//        //we will store this in a global list to access later.
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//            if (permissionsToRequest.size() > 0)
//                requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
//        }
    }

    public double calculateDistance(double lat1,
                                  double lat2, double lon1,
                                  double lon2)
    {

        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return(c * r);
    }

//    private ArrayList findUnAskedPermissions(ArrayList<String> wanted) {
//        ArrayList result = new ArrayList();
//
//        for (String perm : wanted) {
//            if (!hasPermission(perm)) {
//                result.add(perm);
//            }
//        }
//
//        return result;
//    }
//
//    private boolean hasPermission(String permission) {
//        if (canMakeSmores()) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
//            }
//        }
//        return true;
//    }
//
//    private boolean canMakeSmores() {
//        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
//    }
//
//
//    @TargetApi(Build.VERSION_CODES.M)
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//
//        switch (requestCode) {
//
//            case ALL_PERMISSIONS_RESULT:
//                for (String perms : permissionsToRequest) {
//                    if (!hasPermission(perms)) {
//                        permissionsRejected.add(perms);
//                    }
//                }
//
//                if (permissionsRejected.size() > 0) {
//
//
////                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
////                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
////                                    new DialogInterface.OnClickListener() {
////                                        @Override
////                                        public void onClick(DialogInterface dialog, int which) {
////                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////                                                requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
////                                            }
////                                        }
////                                    });
////                            return;
////                        }
////                    }
//
//                }
//
//                break;
//        }
//
//    }
//
//    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
//        new AlertDialog.Builder(MainActivity.this)
//                .setMessage(message)
//                .setPositiveButton("OK", okListener)
//                .setNegativeButton("Cancel", null)
//                .create()
//                .show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        locationTrack.stopListener();
//    }
}
