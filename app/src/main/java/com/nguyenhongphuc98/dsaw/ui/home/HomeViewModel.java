package com.nguyenhongphuc98.dsaw.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.PublicData;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mArea;

    private String[] areas = {"Việt Nam","Thế giới"};
    private String[] areasTag = {"vn","tg"};
    private int areaIndex = 0;

    private MutableLiveData<List<PublicData>> lsData;


    public HomeViewModel() {
        mArea = new MutableLiveData<>();
        mArea.setValue(areas[areaIndex]);

        lsData = new MutableLiveData<>();
        DataManager.Instance().fetchPublicData(lsData,"vn");
    }

    public LiveData<String> getArea() {
        return mArea;
    }
    public LiveData<List<PublicData>> getData() {
        return lsData;
    }

    public void changeArea() {
        mArea.setValue(areas[(++areaIndex) % areas.length]);
        //Change model chart here
        DataManager.Instance().fetchPublicData(lsData,areasTag[(areaIndex) % areasTag.length]);
    }
}