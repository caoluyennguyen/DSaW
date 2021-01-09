package com.nguyenhongphuc98.dsaw.ui.surveys;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.AnswerViewModel;
import com.nguyenhongphuc98.dsaw.data.model.City;
import com.nguyenhongphuc98.dsaw.data.model.SurveyModel;
import com.nguyenhongphuc98.dsaw.data.model.TrackingStatus;

import java.util.ArrayList;
import java.util.List;

public class SurveyResultViewModel extends ViewModel {
    private MutableLiveData<List<AnswerViewModel>> mAnsers;

    private List<AnswerViewModel> lsAnswers;
    private MutableLiveData<List<City>> lsCity = new MutableLiveData<>();

    public SurveyResultViewModel() {
        mAnsers = new MutableLiveData<>();
    }

    public void fetchData(String surveyID, int city_code) {

        DataManager.Instance().fetchAnswerFor(mAnsers, surveyID, city_code);
    }

    public MutableLiveData<List<AnswerViewModel>> getlistAnswers() {
        return mAnsers;
    }

    public MutableLiveData<List<City>> getLsCity() {
        return lsCity;
    }

    public void GetAllCity()
    {
        DataManager.Instance().GetAllmCity(lsCity);
    }
}
