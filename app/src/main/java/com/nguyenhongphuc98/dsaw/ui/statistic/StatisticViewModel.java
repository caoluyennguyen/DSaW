package com.nguyenhongphuc98.dsaw.ui.statistic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

public class StatisticViewModel extends ViewModel {

    //pie chart
    private List<DataEntry> data;
    private MutableLiveData<List<DataEntry>> mData;

    public StatisticViewModel() {
        mData = new MutableLiveData<>();

        data = new ArrayList<>();
        data.add(new ValueDataEntry("F0 - Dương tính (10)", 10));
        data.add(new ValueDataEntry("F1 - Tiếp xúc F0 (55)", 55));
        data.add(new ValueDataEntry("F2 - Di chuyển từ vùng dịch (365)", 365));
        data.add(new ValueDataEntry("F3 - Tiếp xúc F1 (256)", 256));
        data.add(new ValueDataEntry("F4 - Tiếp xúc F2 (20)", 20));

        mData.setValue(data);
    }

    public LiveData<List<DataEntry>> getPie() {
        return mData;
    }
}
