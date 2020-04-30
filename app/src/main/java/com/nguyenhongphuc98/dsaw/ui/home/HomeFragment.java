package com.nguyenhongphuc98.dsaw.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
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
import com.nguyenhongphuc98.dsaw.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private ImageButton changeAreaBtn;
    private Button mapVisualizeBtn;

    private  AnyChartView anyChartView;

    private TextView areaTv;
    private TextView updateTimeTv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        setupView(root);
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        setupAction();
        setupObserver();

        return root;
    }

    private void setupView(View view) {
        anyChartView = (AnyChartView) view.findViewById(R.id.home_line_chart);
        changeAreaBtn = view.findViewById(R.id.home_btn_change_area);
        mapVisualizeBtn = view.findViewById(R.id.home_btn_visualine);
        areaTv = view.findViewById(R.id.home_tv_area);
        updateTimeTv = view.findViewById(R.id.home_tv_update_time);
    }

    private void setupAction() {
        changeAreaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeViewModel.changeArea();
            }
        });

        mapVisualizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"go to visualize",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupObserver() {
        homeViewModel.getArea().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                areaTv.setText(s);
            }
        });

        homeViewModel.getUpdateTime().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                updateTimeTv.setText(s);
            }
        });

        homeViewModel.getcartesian().observe(this, new Observer<Cartesian>() {
            @Override
            public void onChanged(@Nullable Cartesian c) {
                anyChartView.setChart(c);
            }
        });
    }


}