package com.nguyenhongphuc98.dsaw.ui.statistic;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Align;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.LegendLayout;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.nguyenhongphuc98.dsaw.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StatisticFragment extends Fragment {

    private StatisticViewModel mViewModel;

    private Spinner spinner;
    private ArrayAdapter areasAdaptor;

    private AnyChartView pieChartView;
    private Pie pieChart;

    private AnyChartView columnChartView;
    private Cartesian cartesian;


    public static StatisticFragment newInstance() {
        return new StatisticFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistic, container, false);
        setupView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(StatisticViewModel.class);

        mViewModel.getPie().observe(this, new Observer<List<DataEntry>>() {
            @Override
            public void onChanged(List<DataEntry> dataEntries) {
                APIlib.getInstance().setActiveAnyChartView(pieChartView);
                pieChart.data(dataEntries);
            }
        });

        mViewModel.getColumn().observe(this, new Observer<List<DataEntry>>() {
            @Override
            public void onChanged(List<DataEntry> dataEntries) {
                APIlib.getInstance().setActiveAnyChartView(columnChartView);
                Column column = cartesian.column(dataEntries);
                column.tooltip()
                        .titleFormat("{%X}")
                        .position(Position.CENTER_BOTTOM)
                        .anchor(Anchor.CENTER_BOTTOM)
                        .offsetX(0d)
                        .offsetY(5d)
                        .format("${%Value}{groupsSeparator: }");
            }
        });

        mViewModel.getAreas().observe(this, new Observer<HashMap>() {
            @Override
            public void onChanged(HashMap hashMap) {
                ArrayList<String> keyList = new ArrayList<String>(hashMap.keySet());
                ArrayAdapter<String> adaptor = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item, keyList);
                spinner.setAdapter(adaptor);
            }
        });
    }

    void setupView(View view) {

        spinner = view.findViewById(R.id.statistic_spinner_area);

        pieChartView = view.findViewById(R.id.statistic_pie_chart);
        APIlib.getInstance().setActiveAnyChartView(pieChartView);
        pieChart = AnyChart.pie();
        //pieChart.title("Biểu đồ dịch bệnh (người)");

        pieChart.labels().position("outside");

        pieChart.legend().title().enabled(true);
        pieChart.legend().title()
                .text("Các loại được xác định")
                .padding(0d, 0d, 10d, 0d);

        pieChart.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        pieChartView.setChart(pieChart);

        //column chart
        columnChartView = view.findViewById(R.id.statistic_column_chart);
        APIlib.getInstance().setActiveAnyChartView(columnChartView);
        cartesian = AnyChart.column();
        cartesian.animation(true);
        //cartesian.title("Thông tin dịch tễ");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Biểu hiện");
        cartesian.yAxis(0).title("Số lượng (người)");
        columnChartView.setChart(cartesian);
    }
}
