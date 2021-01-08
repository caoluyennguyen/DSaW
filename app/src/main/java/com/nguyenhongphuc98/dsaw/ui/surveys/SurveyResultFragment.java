package com.nguyenhongphuc98.dsaw.ui.surveys;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.adaptor.SurveyResultAdaptor;
import com.nguyenhongphuc98.dsaw.data.DataCenter;
import com.nguyenhongphuc98.dsaw.data.model.AnswerViewModel;
import com.nguyenhongphuc98.dsaw.data.model.City;

import java.util.ArrayList;
import java.util.List;

public class SurveyResultFragment extends Fragment {

    private SurveyResultViewModel mViewModel;

    private ListView lv;

    private SurveyResultAdaptor adaptor;

    private String surveyID;

    private List<AnswerViewModel> lsAnswers = new ArrayList<>();

    private ArrayList<City> lsCity = new ArrayList<>();
    private ArrayAdapter<String> adCityName;
    private Spinner mSpinCity;

    public static SurveyResultFragment newInstance() {
        return new SurveyResultFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_survey_result, container, false);
        setupView(view);
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
    }

    public void setSurveyId(String sid) {
        this.surveyID = sid;
    }

    private void RegisterDataLiveListener()
    {
        mViewModel = ViewModelProviders.of(this).get(SurveyResultViewModel.class);

        adaptor = new SurveyResultAdaptor(getContext(),lsAnswers);
        lv.setAdapter(adaptor);

        mViewModel.getlistAnswers().observe(this, new Observer<List<AnswerViewModel>>() {
            @Override
            public void onChanged(List<AnswerViewModel> answerViewModels) {
                lsAnswers.clear();
                for (AnswerViewModel a : answerViewModels) {
                    lsAnswers.add(a);
                }

                if (lsAnswers.size() == 0)
                    lsAnswers.add(new AnswerViewModel("Tạm thời chưa có câu hỏi", new ArrayList<String>()));

                adaptor.notifyDataSetChanged();
            }
        });
        mViewModel.fetchData(DataCenter.surveyID);

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
}
