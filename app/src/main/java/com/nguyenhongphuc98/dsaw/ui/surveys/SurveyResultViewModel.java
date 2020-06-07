package com.nguyenhongphuc98.dsaw.ui.surveys;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.AnswerViewModel;
import com.nguyenhongphuc98.dsaw.data.model.SurveyModel;
import com.nguyenhongphuc98.dsaw.data.model.TrackingStatus;

import java.util.ArrayList;
import java.util.List;

public class SurveyResultViewModel extends ViewModel {
    private MutableLiveData<List<AnswerViewModel>> mAnsers;

    private List<AnswerViewModel> lsAnswers;

    public SurveyResultViewModel() {
        mAnsers = new MutableLiveData<>();
    }

    public void fetchData(String survetID) {

        DataManager.Instance().fetchAnswerFor(mAnsers, survetID);

//        lsAnswers = new ArrayList<>();
//        ArrayList<String> ls = new ArrayList<String>();
//        ls.add("Có (22)");
//        ls.add("Không (30)");
//        lsAnswers.add(new AnswerViewModel("Bạn có đẹp trai không?", ls));
//
//        ArrayList<String> ls2 = new ArrayList<String>();
//        ls2.add("Khẩu trang (532)");
//        ls2.add("Băng cá nhân (200)");
//        ls2.add("Gen rửa tay (562)");
//        ls2.add("Thuốc ngủ (10)");
//        lsAnswers.add(new AnswerViewModel("Bạn có nhưng thiết bị y tế nào?", ls2));
//
//        mAnsers.setValue(lsAnswers);

    }

    public MutableLiveData<List<AnswerViewModel>> getlistAnswers() {
        return mAnsers;
    }
}
