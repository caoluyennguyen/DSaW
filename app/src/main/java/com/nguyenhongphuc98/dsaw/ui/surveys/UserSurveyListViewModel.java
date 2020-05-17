package com.nguyenhongphuc98.dsaw.ui.surveys;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyenhongphuc98.dsaw.adaptor.SurveyAdaptor;
import com.nguyenhongphuc98.dsaw.data.model.SurveyModel;

import java.util.ArrayList;
import java.util.List;

public class UserSurveyListViewModel extends ViewModel {

    private MutableLiveData<List<SurveyModel>> mSurveyKBYT;

    private MutableLiveData<List<SurveyModel>> mSurveyKBNT;

    private MutableLiveData<List<SurveyModel>> mSurveyBC;

    private List<SurveyModel> lsSurveyKBYT;

    private List<SurveyModel> lsSurveyKBNT;

    private List<SurveyModel> lsSurveyBC;

    public UserSurveyListViewModel() {

        mSurveyKBYT = new MutableLiveData<>();
        mSurveyKBNT = new MutableLiveData<>();
        mSurveyBC = new MutableLiveData<>();

        fetchData();
    }

    public void fetchData() {

        lsSurveyKBYT = new ArrayList<>();
        lsSurveyKBYT.add(new SurveyModel("s1","152","Khai báo y tế"));
        lsSurveyKBYT.add(new SurveyModel("s2","34","Khai báo thiết bị y tế"));
        lsSurveyKBYT.add(new SurveyModel("s3","152","KBSK khỏe tuần 22- 29 / 4"));
        mSurveyKBYT.setValue(lsSurveyKBYT);

        lsSurveyKBNT = new ArrayList<>();
        lsSurveyKBNT.add(new SurveyModel("s1","3","Khai báo y tế"));
        lsSurveyKBNT.add(new SurveyModel("s2","5","Khai báo tình trạng sức khỏe"));
        lsSurveyKBNT.add(new SurveyModel("s3","99","Khai báo cư trú"));
        mSurveyKBNT.setValue(lsSurveyKBNT);

        lsSurveyBC = new ArrayList<>();
        lsSurveyBC.add(new SurveyModel("s1","12","Báo cáo ca nhiễm"));
        lsSurveyBC.add(new SurveyModel("s2","22","Báo cáo tụ tập"));
        lsSurveyBC.add(new SurveyModel("s3","253","Báo cáo thao túng thị trường"));
        mSurveyBC.setValue(lsSurveyBC);

    }

    public MutableLiveData<List<SurveyModel>> getlistKBYT() {return  mSurveyKBYT; }
    public MutableLiveData<List<SurveyModel>> getlistKBNT() {return  mSurveyKBNT; }
    public MutableLiveData<List<SurveyModel>> getlistBC() {return  mSurveyBC; }
}
