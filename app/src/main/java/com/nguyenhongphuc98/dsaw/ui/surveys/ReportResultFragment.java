package com.nguyenhongphuc98.dsaw.ui.surveys;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.adaptor.ReportResultAdaptor;
import com.nguyenhongphuc98.dsaw.data.DataCenter;
import com.nguyenhongphuc98.dsaw.data.model.ReportModel;

import java.util.ArrayList;
import java.util.List;

public class ReportResultFragment extends Fragment {

    private ReportResultViewModel mViewModel;

    private ListView listView;

    private ReportResultAdaptor adaptor;

    private List<ReportModel> lsReport = new ArrayList<>();

    public static ReportResultFragment newInstance() {
        return new ReportResultFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_report_result, container, false);
        listView = view.findViewById(R.id.report_result_lv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ReportResultViewModel.class);

        adaptor = new ReportResultAdaptor(getContext(),lsReport);
        listView.setAdapter(adaptor);

        mViewModel.getLsReport().observe(this, new Observer<List<ReportModel>>() {
            @Override
            public void onChanged(List<ReportModel> reportModels) {

                lsReport.clear();

                for (ReportModel r : reportModels) {
                    lsReport.add(r);
                }

                adaptor.notifyDataSetChanged();
            }
        });

        mViewModel.fetchData(DataCenter.surveyID);
    }

}
