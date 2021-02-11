package com.corazon98.dsaw.ui.surveys;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.corazon98.dsaw.data.DataManager;
import com.corazon98.dsaw.data.model.City;
import com.corazon98.dsaw.data.model.District;
import com.corazon98.dsaw.data.model.ReportModel;
import com.corazon98.dsaw.data.model.Ward;

import java.util.List;

public class ReportResultViewModel extends ViewModel {

    private MutableLiveData<List<ReportModel>> lsReports;
    private MutableLiveData<List<City>> lsCity = new MutableLiveData<>();
    private MutableLiveData<List<District>> lsDistrict = new MutableLiveData<>();
    private MutableLiveData<List<Ward>> lsWard = new MutableLiveData<>();


    public ReportResultViewModel() {
        lsReports = new MutableLiveData<>();

    }

    public void fetchData(String reportID, int city_code, int district_code, int ward_code, boolean sort_type) {
        if (sort_type)
        {
            DataManager.Instance().fetchAnswerForReport(lsReports, reportID, city_code, district_code, ward_code);
        }
        else
            DataManager.Instance().fetchAnswerForStatistic(lsReports, reportID, city_code);
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
