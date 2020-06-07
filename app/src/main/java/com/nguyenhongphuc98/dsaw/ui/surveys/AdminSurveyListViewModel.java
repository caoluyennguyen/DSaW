package com.nguyenhongphuc98.dsaw.ui.surveys;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyenhongphuc98.dsaw.adaptor.SurveyAdaptor;
import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.SurveyModel;

import java.util.ArrayList;
import java.util.List;

public class AdminSurveyListViewModel extends ViewModel {

    private MutableLiveData<List<SurveyModel>> lsSurvey;

    public AdminSurveyListViewModel() {
        lsSurvey = new MutableLiveData<>();
        fetchData();
    }

    public void fetchData() {
        DataManager.Instance().fetchListSurveyByType(lsSurvey,"not_importance","admin");
    }

    public MutableLiveData<List<SurveyModel>> getLsSurvey() {return  lsSurvey; }
}
