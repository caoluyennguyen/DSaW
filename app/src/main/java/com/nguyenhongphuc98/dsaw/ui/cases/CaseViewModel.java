package com.nguyenhongphuc98.dsaw.ui.cases;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.adaptor.CaseAdaptor;
import com.nguyenhongphuc98.dsaw.adaptor.NewsAdaptor;
import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.Account;
import com.nguyenhongphuc98.dsaw.data.model.Case;
import com.nguyenhongphuc98.dsaw.data.model.News;
import com.nguyenhongphuc98.dsaw.data.model.TrackingStatus;

import java.util.ArrayList;
import java.util.List;

public class CaseViewModel extends ViewModel {

   // private MutableLiveData<List<Case>> mListCases;

    private MutableLiveData<Account> liveAccount;

    MutableLiveData<TrackingStatus> lastLocation;

    public CaseViewModel() {
        liveAccount = new MutableLiveData<>();
        lastLocation = new MutableLiveData<>();
    }

    public LiveData<Account> getLiveAccount() {
        return liveAccount;
    }

    public LiveData<TrackingStatus> getLastLocation() {
        return lastLocation;
    }

    public void updateCase(String cmnd, String f) {

        //find user from db and save new case
        DataManager.Instance().fetchAccountById(cmnd, liveAccount);
    }
}
