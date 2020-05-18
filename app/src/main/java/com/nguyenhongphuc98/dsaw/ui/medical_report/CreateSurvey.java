package com.nguyenhongphuc98.dsaw.ui.medical_report;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyenhongphuc98.dsaw.R;

public class CreateSurvey extends Fragment {

    private CreateSurveyViewModel mViewModel;

    public static CreateSurvey newInstance() {
        return new CreateSurvey();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_survey, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CreateSurveyViewModel.class);
        // TODO: Use the ViewModel
    }

}
