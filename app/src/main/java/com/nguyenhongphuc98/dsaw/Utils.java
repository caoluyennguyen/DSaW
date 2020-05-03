package com.nguyenhongphuc98.dsaw;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class Utils {

    public static FragmentActivity activity;

    public static void replaceFragment(Fragment newFragment) {
        FragmentTransaction fragmentTransition=activity.getSupportFragmentManager().beginTransaction();
        fragmentTransition.replace(R.id.nav_host_fragment,newFragment);
        fragmentTransition.commit();
    }
}
