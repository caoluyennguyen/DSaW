package com.corazon98.dsaw.ui.cases;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.corazon98.dsaw.data.DataManager;
import com.corazon98.dsaw.data.model.Account;
import com.corazon98.dsaw.data.model.TrackingStatus;

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
