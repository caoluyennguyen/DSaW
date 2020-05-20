package com.nguyenhongphuc98.dsaw;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.network.DataService;

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
    }

}
