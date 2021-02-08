package com.corazon98.dsaw.ui.surveys;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.corazon98.dsaw.data.DataManager;
import com.corazon98.dsaw.data.model.City;
import com.corazon98.dsaw.data.model.ReportModel;

import java.util.List;

public class ReportResultViewModel extends ViewModel {

    private MutableLiveData<List<ReportModel>> lsReports;
    private MutableLiveData<List<City>> lsCity = new MutableLiveData<>();


    public ReportResultViewModel() {
        lsReports = new MutableLiveData<>();

    }

    public void fetchData(String reportID, int city_code) {
        DataManager.Instance().fetchAnswerForReport(lsReports, reportID, city_code);
    }

    public MutableLiveData<List<ReportModel>> getLsReport() {
        return lsReports;
    }

    public MutableLiveData<List<City>> getLsCity() {
        return lsCity;
    }

    public void setLsCity(MutableLiveData<List<City>> lsCity) {
        this.lsCity = lsCity;
    }

    public void GetAllCity()
    {
        DataManager.Instance().GetAllmCity(lsCity);
    }

}
