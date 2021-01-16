package com.corazon98.dsaw.ui.surveys;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.corazon98.dsaw.R;
import com.corazon98.dsaw.adaptor.ReportResultAdaptor;
import com.corazon98.dsaw.data.DataCenter;
import com.corazon98.dsaw.data.model.City;
import com.corazon98.dsaw.data.model.ReportModel;

import java.util.ArrayList;
import java.util.List;

public class ReportResultFragment extends Fragment {

    private ReportResultViewModel mViewModel;

    private ListView listView;

    private ReportResultAdaptor adaptor;

    private List<ReportModel> lsReport = new ArrayList<>();

    private ArrayList<City> lsCity = new ArrayList<>();
    private ArrayAdapter<String> adCityName;
    private Spinner mSpinCity;

    public static ReportResultFragment newInstance() {
        return new ReportResultFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_report_result, container, false);
        listView = view.findViewById(R.id.report_result_lv);
        mSpinCity = view.findViewById(R.id.survey_spinner);

        SetupEvent();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RegisterDataLiveListener();
    }

    private void RegisterDataLiveListener()
    {
        mViewModel = ViewModelProviders.of(this).get(ReportResultViewModel.class);

        adaptor = new ReportResultAdaptor(getContext(),lsReport);
        listView.setAdapter(adaptor);

        mViewModel.getLsReport().observe(this, reportModels -> {

            lsReport.clear();

            for (ReportModel r : reportModels) {
                lsReport.add(r);
            }
            adaptor.notifyDataSetChanged();
        });
        //mViewModel.fetchData(DataCenter.surveyID, -1);

        //get all city
        adCityName = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, lsCity);
        adCityName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinCity.setAdapter(adCityName);

        mViewModel.GetAllCity();
        mViewModel.getLsCity().observe(this, cities -> {
            lsCity.clear();
            lsCity.add(new City("0","Tất cả"));
            for (City a : cities) {
                lsCity.add(a);
                Log.e("Survey result fragment get city: ", a.getName());
            }

            if (lsCity.size() == 0)
                lsCity.add(new City());

            adCityName.notifyDataSetChanged();
        });
    }

    private void SetupEvent()
    {
        mSpinCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lsReport.clear();
                mViewModel.fetchData(DataCenter.surveyID, position - 1);
                adaptor = new ReportResultAdaptor(getContext(),lsReport);
                listView.setAdapter(adaptor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
