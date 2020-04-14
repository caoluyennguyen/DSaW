package com.nguyenhongphuc98.dsaw;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private int mMenuSet = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.inflateMenu(R.menu.bottom_nav_menu);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

//        AppBarConfiguration appBarConfiguration;
//
//            appBarConfiguration = new AppBarConfiguration.Builder(
//                    R.id.navigation_home, R.id.navigation_news)
//                    .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
       // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

}
