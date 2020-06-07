package com.nguyenhongphuc98.dsaw.ui.surveys;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyenhongphuc98.dsaw.adaptor.SurveyAdaptor;
import com.nguyenhongphuc98.dsaw.data.DataCenter;
import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.SurveyModel;

import java.util.ArrayList;
import java.util.List;

public class UserSurveyListViewModel extends ViewModel {

    private MutableLiveData<List<SurveyModel>> mSurveyKBYT;

    private MutableLiveData<List<SurveyModel>> mSurveyKBNT;

    private MutableLiveData<List<SurveyModel>> mSurveyBC;

    public UserSurveyListViewModel() {

        mSurveyKBYT = new MutableLiveData<>();
        mSurveyKBNT = new MutableLiveData<>();
        mSurveyBC = new MutableLiveData<>();

        fetchData();
    }

    public void fetchData() {

        DataManager.Instance().fetchListSurveyByType(mSurveyKBYT,"personal_medical", DataCenter.currentUser.getIdentity());

        DataManager.Instance().fetchListSurveyByType(mSurveyKBNT,"relatives_medical", DataCenter.currentUser.getIdentity());

        DataManager.Instance().fetchListSurveyByType(mSurveyBC,"report", DataCenter.currentUser.getIdentity());

    }

    public MutableLiveData<List<SurveyModel>> getlistKBYT() {return  mSurveyKBYT; }
    public MutableLiveData<List<SurveyModel>> getlistKBNT() {return  mSurveyKBNT; }
    public MutableLiveData<List<SurveyModel>> getlistBC() {return  mSurveyBC; }
}
