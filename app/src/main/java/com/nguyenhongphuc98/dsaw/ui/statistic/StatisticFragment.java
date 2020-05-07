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

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.nguyenhongphuc98.dsaw.R;

import java.util.List;

public class StatisticFragment extends Fragment {

    private StatisticViewModel mViewModel;

    private AnyChartView anyChartView;

    private Pie pieChart;

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
                pieChart.data(dataEntries);
            }
        });
    }

    void setupView(View view) {
        anyChartView = view.findViewById(R.id.statistic_pie_chart);
        pieChart = AnyChart.pie();
        pieChart.title("Biểu đồ dịch bệnh (người)");

        pieChart.labels().position("outside");

        pieChart.legend().title().enabled(true);
        pieChart.legend().title()
                .text("Các loại được xác định")
                .padding(0d, 0d, 10d, 0d);

        pieChart.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        anyChartView.setChart(pieChart);
    }
}
