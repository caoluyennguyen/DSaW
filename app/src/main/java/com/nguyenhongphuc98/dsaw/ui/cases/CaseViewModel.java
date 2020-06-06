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

import java.util.ArrayList;
import java.util.List;

public class CaseViewModel extends ViewModel {

   // private MutableLiveData<List<Case>> mListCases;

    private MutableLiveData<Account> liveAccount;

    public CaseViewModel() {
        liveAccount = new MutableLiveData<>();
    }

    public LiveData<Account> getLiveAccount() {
        return liveAccount;
    }

//    public void setContext(Context c) {
//        this.context = c;
//        lsCases = new ArrayList<>();
//        lsCases.add(new Case("Ha Tinh",
//                "22:20 - 02/05/2020",
//                "24:50 - 12/05/2020",
//                "F0",
//                "case01",
//                "22356,44426",
//                "184335348",
//                "Nguyen Hong Phuc"));
//
//
//        adaptor = new CaseAdaptor(this.context, lsCases);
//        mAdaptor.setValue(adaptor);
//    }

    public void updateCase(String cmnd, String f) {

        //find user from db and save new case
        DataManager.Instance().fetchAccountById(cmnd, liveAccount);
    }
}
