package com.corazon98.dsaw.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.corazon98.dsaw.data.DataManager;
import com.corazon98.dsaw.data.model.PublicData;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mArea;

    private String[] areas = {"Việt Nam","Thế giới"};
    private String[] areasTag = {"vn","tg"};
    private int areaIndex = 0;

    private MutableLiveData<List<PublicData>> lsData;


    public HomeViewModel() {
        mArea = new MutableLiveData<>();
        mArea.setValue(areas[areaIndex]);

        lsData = new MutableLiveData<>();
        DataManager.Instance().fetchPublicData(lsData,"vn");
    }

    public LiveData<String> getArea() {
        return mArea;
    }
    public LiveData<List<PublicData>> getData() {
        return lsData;
    }

    public void changeArea() {
        mArea.setValue(areas[(++areaIndex) % areas.length]);
        //Change model chart here
        DataManager.Instance().fetchPublicData(lsData,areasTag[(areaIndex) % areasTag.length]);
    }
}