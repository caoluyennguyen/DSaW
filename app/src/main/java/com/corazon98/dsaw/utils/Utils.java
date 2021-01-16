package com.corazon98.dsaw.utils;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.corazon98.dsaw.R;

public class Utils {

    public static FragmentActivity activity;

    public static void replaceFragment(Fragment newFragment) {
        FragmentTransaction fragmentTransition=activity.getSupportFragmentManager().beginTransaction();
        fragmentTransition.replace(R.id.nav_host_fragment,newFragment);
        fragmentTransition.addToBackStack(null);
        fragmentTransition.commit();
    }
}
