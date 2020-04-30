package com.nguyenhongphuc98.dsaw.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mArea;
    private MutableLiveData<String> mUpdateTime;
    private MutableLiveData<Cartesian> mCartesian;

    private String[] areas = {"Việt Nam","Thế giới"};
    private int areaIndex = 0;

    public HomeViewModel() {
        mArea = new MutableLiveData<>();
        mArea.setValue(areas[areaIndex]);

        mUpdateTime = new MutableLiveData<>();
        mUpdateTime.setValue("Cập nhật lúc 22:02 - 20/04");

        mCartesian = new MutableLiveData<>();

        Cartesian cartesian = AnyChart.line();

        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 10d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title("Biều đồ diễn biến dịch bệnh.");

        cartesian.yAxis(0).title("Số lượng (người)");
        cartesian.xAxis(0).title("Thời gian (ngày)");
        cartesian.xAxis(0).labels().padding(7d, 7d, 7d, 7d);
        cartesian.yAxis(0).labels().padding(7d, 0d, 7d, 3d);

        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add(new CustomDataEntry("20", 3.6, 2.3, 2.8));
        seriesData.add(new CustomDataEntry("21", 7.1, 4.0, 4.1));
        seriesData.add(new CustomDataEntry("22", 8.5, 6.2, 5.1));
        seriesData.add(new CustomDataEntry("23", 9.2, 11.8, 6.5));
        seriesData.add(new CustomDataEntry("24", 10.1, 13.0, 12.5));
        seriesData.add(new CustomDataEntry("25", 11.6, 13.9, 18.0));
        seriesData.add(new CustomDataEntry("26", 16.4, 18.0, 21.0));


        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name("Nhiễm bệnh");
        series1.color("#FCA903");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series2 = cartesian.line(series2Mapping);
        series2.name("Tử vong");
        series2.color("#FC2003");
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series3 = cartesian.line(series3Mapping);
        series3.name("Bình phục");
        series3.color("#04D446");
        series3.hovered().markers().enabled(true);
        series3.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series3.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);
        mCartesian.setValue(cartesian);
    }

    public LiveData<String> getArea() {
        return mArea;
    }
    public LiveData<String> getUpdateTime() {
        return mUpdateTime;
    }
    public LiveData<Cartesian> getcartesian() {
        return mCartesian;
    }

    public void changeArea() {
        mArea.setValue(areas[(areaIndex++) % areas.length]);
        //Change model chart here
    }

    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
        }

    }
}