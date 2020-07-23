package com.nguyenhongphuc98.dsaw.ui.map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyenhongphuc98.dsaw.data.DataCenter;
import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.Case;

import java.util.ArrayList;
import java.util.List;

public class MapVisualizeViewModel extends ViewModel {

    private MutableLiveData<List<Case>> lsCases;

    public List<Case> ls;

    public MapVisualizeViewModel() {
        lsCases = new MutableLiveData<>();
        ls = new ArrayList<>();
        fetchData();
    }

    public LiveData<List<Case>> getListCases() {
        return lsCases;
    }

    public void fetchData() {
        DataManager.Instance().fetchAllCase(lsCases);
    }
}

