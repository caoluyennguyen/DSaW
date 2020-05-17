package com.nguyenhongphuc98.dsaw.ui.surveys;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyenhongphuc98.dsaw.adaptor.SurveyAdaptor;
import com.nguyenhongphuc98.dsaw.data.model.SurveyModel;

import java.util.ArrayList;
import java.util.List;

public class AdminSurveyListViewModel extends ViewModel {

    private MutableLiveData<SurveyAdaptor> mAdaptor;

    private SurveyAdaptor adaptor;
    private List<SurveyModel> lsSurvey;

    private Context context;

    public AdminSurveyListViewModel() {
        mAdaptor = new MutableLiveData<>();
    }

    public void setContext(Context c) {
        this.context = c;
        lsSurvey = new ArrayList<>();
        lsSurvey.add(new SurveyModel("s1","152","Khai báo y tế"));
        lsSurvey.add(new SurveyModel("s2","34","Báo ca nhiễm"));
        lsSurvey.add(new SurveyModel("s3","152","Báo cáo vi phạm"));
        adaptor = new SurveyAdaptor(this.context, lsSurvey);
        mAdaptor.setValue(adaptor);
    }

    public LiveData<SurveyAdaptor> getAdaptor() {
        return mAdaptor;
    }

    public List<SurveyModel> getListSurvey() {return  lsSurvey; }
}
