package com.corazon98.dsaw.ui.statistic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.corazon98.dsaw.data.DataManager;
import com.corazon98.dsaw.data.model.Area;
import com.corazon98.dsaw.data.model.Case;

import java.util.ArrayList;
import java.util.List;

public class StatisticViewModel extends ViewModel {

    //areas
    public MutableLiveData<List<Area>> lsAreas;

    //pie chart
    private List<DataEntry> caseData;
    private MutableLiveData<List<DataEntry>> mCaseData;
    private MutableLiveData<List<Case>> listCases;

    //column chart
    List<DataEntry> dichTeData;
    private MutableLiveData<List<DataEntry>> mDichTeData;

    public StatisticViewModel() {

        lsAreas = new MutableLiveData<>();
        listCases = new MutableLiveData<>();

        DataManager.Instance().fetchAllAreas(lsAreas);
        fetchPieDataAllArea();

//        mCaseData = new MutableLiveData<>();
//        caseData = new ArrayList<>();
//        caseData.add(new ValueDataEntry("F0 - Dương tính (10)", 10));
//        caseData.add(new ValueDataEntry("F1 - Tiếp xúc F0 (55)", 55));
//        caseData.add(new ValueDataEntry("F2 - Di chuyển từ vùng dịch (365)", 365));
//        caseData.add(new ValueDataEntry("F3 - Tiếp xúc F1 (256)", 256));
//        caseData.add(new ValueDataEntry("F4 - Tiếp xúc F2 (20)", 20));
//        mCaseData.setValue(caseData);

        mDichTeData = new MutableLiveData<>();
        dichTeData  = new ArrayList<>();
        dichTeData.add(new ValueDataEntry("Ho", 10));
        dichTeData.add(new ValueDataEntry("Sốt", 232));
        dichTeData.add(new ValueDataEntry("Tim đập nhanh", 33));
        dichTeData.add(new ValueDataEntry("Khó thở", 152));
        mDichTeData.setValue(dichTeData);
    }

    public void fetchPieDataAllArea() {
        DataManager.Instance().fetchAllCase(listCases);
    }

    public void fetchPieDataFor(String areaID) {
        DataManager.Instance().fetchAllCaseOfArea(listCases, areaID);
    }

    public LiveData<List<DataEntry>> getPie() {
        return mCaseData;
    }

    public LiveData<List<DataEntry>> getColumn() {
        return mDichTeData;
    }

    public LiveData<List<Area>> getListAreas() {
        return lsAreas;
    }

    public LiveData<List<Case>> getListCases() {
        return listCases;
    }
}
