package com.corazon98.dsaw.ui.surveys;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.corazon98.dsaw.data.DataManager;
import com.corazon98.dsaw.data.model.AnswerViewModel;
import com.corazon98.dsaw.data.model.City;
import com.corazon98.dsaw.data.model.District;
import com.corazon98.dsaw.data.model.Ward;

import java.util.List;

public class SurveyResultViewModel extends ViewModel {
    private MutableLiveData<List<AnswerViewModel>> mAnsers;

    private List<AnswerViewModel> lsAnswers;
    private MutableLiveData<List<City>> lsCity = new MutableLiveData<>();
    private MutableLiveData<List<District>> lsDistrict = new MutableLiveData<>();
    private MutableLiveData<List<Ward>> lsWard = new MutableLiveData<>();

    public SurveyResultViewModel() {
        mAnsers = new MutableLiveData<>();
    }

    public void fetchData(String surveyID, int city_code, int district_code, int ward_code) {

        DataManager.Instance().fetchAnswerFor(mAnsers, surveyID, city_code, district_code, ward_code);
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

    public MutableLiveData<List<District>> getLsDistrict() {
        return lsDistrict;
    }

    public MutableLiveData<List<Ward>> getLsWard() {
        return lsWard;
    }

    public void GetDistrictOfCity(String codeCity)
    {
        DataManager.Instance().GetmDistrictOfCityWarning(lsDistrict, codeCity);
    }

    public void GetWardOfDistrict(String cityCode, String codeDistrict)
    {
        DataManager.Instance().GetmWardOfDistrictWarning(lsWard, cityCode, codeDistrict);
    }
}
