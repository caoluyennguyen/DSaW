package com.nguyenhongphuc98.dsaw.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class GeoHandle extends Handler {

    @Override
    public void handleMessage(@NonNull Message msg) {
        String locationAddress;
        switch (msg.what) {
            case 1:
                Bundle bundle = msg.getData();
                locationAddress = bundle.getString("address");
                break;
            default:
                locationAddress = null;
        }
        Log.e("location Address=", locationAddress);
    }
}
