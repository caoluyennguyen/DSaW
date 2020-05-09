package com.nguyenhongphuc98.dsaw.ui.statistic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StatisticViewModel extends ViewModel {

    //areas
    private HashMap<String,String> areas;
    private MutableLiveData<HashMap> mAreas;

    //pie chart
    private List<DataEntry> caseData;
    private MutableLiveData<List<DataEntry>> mCaseData;

    //column chart
    List<DataEntry> dichTeData;
    private MutableLiveData<List<DataEntry>> mDichTeData;

    public StatisticViewModel() {

        mAreas = new MutableLiveData<>();
        areas = new HashMap<>();
        areas.put("Hồ Chí Minh","area1");
        areas.put("Hà Nội","area2");
        areas.put("Bến Tre","area3");
        areas.put("Đà Nẵng","area4");
        mAreas.setValue(areas);

        mCaseData = new MutableLiveData<>();
        caseData = new ArrayList<>();
        caseData.add(new ValueDataEntry("F0 - Dương tính (10)", 10));
        caseData.add(new ValueDataEntry("F1 - Tiếp xúc F0 (55)", 55));
        caseData.add(new ValueDataEntry("F2 - Di chuyển từ vùng dịch (365)", 365));
        caseData.add(new ValueDataEntry("F3 - Tiếp xúc F1 (256)", 256));
        caseData.add(new ValueDataEntry("F4 - Tiếp xúc F2 (20)", 20));
        mCaseData.setValue(caseData);

        mDichTeData = new MutableLiveData<>();
        dichTeData  = new ArrayList<>();
        dichTeData.add(new ValueDataEntry("Ho", 10));
        dichTeData.add(new ValueDataEntry("Sốt", 232));
        dichTeData.add(new ValueDataEntry("Tim đập nhanh", 33));
        dichTeData.add(new ValueDataEntry("Khó thở", 152));
        mDichTeData.setValue(dichTeData);
    }

    public LiveData<HashMap> getAreas() {
        return mAreas;
    }

    public LiveData<List<DataEntry>> getPie() {
        return mCaseData;
    }

    public LiveData<List<DataEntry>> getColumn() {
        return mDichTeData;
    }
}
