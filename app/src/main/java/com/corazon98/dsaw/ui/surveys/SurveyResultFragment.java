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
import com.corazon98.dsaw.adaptor.SurveyResultAdaptor;
import com.corazon98.dsaw.data.DataCenter;
import com.corazon98.dsaw.data.model.AnswerViewModel;
import com.corazon98.dsaw.data.model.City;
import com.corazon98.dsaw.data.model.District;
import com.corazon98.dsaw.data.model.Ward;

import java.util.ArrayList;
import java.util.List;

public class SurveyResultFragment extends Fragment
{

    private SurveyResultViewModel mViewModel;

    private ListView lv;

    private SurveyResultAdaptor adaptor;

    private List<AnswerViewModel> lsAnswers = new ArrayList<>();

    private ArrayList<City> lsCity = new ArrayList<>();
    private ArrayAdapter<String> adCityName;
    private int cityPos = 0;

    private ArrayList<District> lsDistrict = new ArrayList<>();
    private ArrayAdapter<String> adDistrictName;
    private int districtPos = 0;

    private List<Ward> lsWard = new ArrayList<>();
    private ArrayAdapter<String> adWardName;
    private int wardPos = 0;

    private Spinner mSpinCity;
    private Spinner mSpinDistrict;
    private Spinner mSpinWard;

    public static SurveyResultFragment newInstance() {
        return new SurveyResultFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_survey_result, container, false);
        setupView(view);
        setupEvent();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RegisterDataLiveListener();
    }

    private void setupView(View view) {
        lv = view.findViewById(R.id.survey_result_lv);
        mSpinCity = view.findViewById(R.id.survey_spinner);
        mSpinDistrict = view.findViewById(R.id.survey_spinner_district);
        mSpinWard = view.findViewById(R.id.survey_spinner_ward);
    }

    private void setupEvent()
    {
        mSpinCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lsAnswers.clear();

                mViewModel.GetDistrictOfCity(lsCity.get(position).getCode());
                adDistrictName = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, lsDistrict);
                adDistrictName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinDistrict.setAdapter(adDistrictName);

                cityPos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mSpinDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                districtPos = position;

                mViewModel.GetWardOfDistrict(lsCity.get(cityPos).getCode(), lsDistrict.get(position).getCode());

                adWardName = new ArrayAdapter(getContext(), R.layout.custom_spinner_item, lsWard);
                adWardName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinWard.setAdapter(adWardName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinWard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lsAnswers.clear();
                wardPos = position;

                mViewModel.fetchData(DataCenter.surveyID, cityPos - 1, districtPos, position);
                adaptor = new SurveyResultAdaptor(getContext(),lsAnswers);
                lv.setAdapter(adaptor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void RegisterDataLiveListener()
    {
        mViewModel = ViewModelProviders.of(this).get(SurveyResultViewModel.class);

        mViewModel.getlistAnswers().observe(this, answerViewModels -> {
            lsAnswers.clear();

            for (AnswerViewModel a : answerViewModels) {
                lsAnswers.add(a);
            }

            if (lsAnswers.size() == 0)
                lsAnswers.add(new AnswerViewModel("Tạm thời chưa có câu hỏi", new ArrayList<String>()));

            adaptor.notifyDataSetChanged();
        });

        /*adaptor = new SurveyResultAdaptor(getContext(), lsAnswers);
        lv.setAdapter(adaptor);*/

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

        adDistrictName = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);
        mViewModel.getLsDistrict().observe(this, districts -> {
            lsDistrict.clear();
            for (District a : districts)
            {
                lsDistrict.add(a);
                Log.e("User fragment get districts: ", a.getName());
            }

            if (lsDistrict.size() == 0)
                lsDistrict.add(new District());


            adDistrictName.notifyDataSetChanged();
        });

        adWardName = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);
        mViewModel.getLsWard().observe(this, wards -> {
            lsWard.clear();
            for (Ward a : wards)
            {
                lsWard.add(a);
                Log.e("User fragment get wards: ", a.getName());
            }

            if (lsWard.size() == 0)
                lsWard.add(new Ward());

            adWardName.notifyDataSetChanged();
        });
    }
}
