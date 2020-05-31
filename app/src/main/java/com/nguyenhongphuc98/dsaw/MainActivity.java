package com.nguyenhongphuc98.dsaw;

import android.os.Bundle;

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
import com.nguyenhongphuc98.dsaw.data.network.DataService;
import com.nguyenhongphuc98.dsaw.utils.Geo;
import com.nguyenhongphuc98.dsaw.utils.GeoHandle;
import com.nguyenhongphuc98.dsaw.utils.Utils;

public class MainActivity extends AppCompatActivity {
    private int mMenuSet = 2;

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
        user.observe(this, new Observer<Account>() {
            @Override
            public void onChanged(Account account) {
                DataCenter.currentUser = account;
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
        GeoHandle handle = new GeoHandle();
        Geo.getAddressFromLocation(10.877898, 106.807128,getApplicationContext(),handle);

//        A a = new A();
//        a.s ="aaa";
//        test(a);
//        Log.d("TAGGG", "onCreate: s="+ a.s);
    }

//    void test(final A a) {
//        a.s ="bbb";
//    }
//
//    class A {
//        String s;
//    }
}
