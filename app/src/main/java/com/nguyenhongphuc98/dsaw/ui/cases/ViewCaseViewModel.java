package com.nguyenhongphuc98.dsaw.ui.cases;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyenhongphuc98.dsaw.data.DataCenter;
import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.Case;

import java.util.ArrayList;
import java.util.List;

public class ViewCaseViewModel extends ViewModel {

    private MutableLiveData<List<Case>> lsCases;

    public List<Case> ls;

    public ViewCaseViewModel() {
        lsCases = new MutableLiveData<>();
        ls = new ArrayList<>();
        fetchData();
    }

    public LiveData<List<Case>> getListCases() {
        return lsCases;
    }

    public void fetchData() {

        // just have permission to view case in this area
        DataManager.Instance().fetchAllCaseOfArea(lsCases, DataCenter.currentUser.getArea_management());
    }

    public List<Case> filterByF(String f) {
        List<Case> lsCasesFiltered = new ArrayList<>();

        for (Case c: lsCases.getValue()) {
            if (f.equals(c.getF())) {
                lsCasesFiltered.add(c);
            }
        }

        //it mean select see all
        if (lsCasesFiltered.size() == 0)
            lsCasesFiltered = lsCases.getValue();

       return lsCasesFiltered;
    }

    public List<Case> filterByNameOrCMND(String searchText) {

        List<Case> lsCasesFiltered = new ArrayList<>();
        String nomorlizeText = searchText.toLowerCase();

        if (searchText.isEmpty() || searchText.length() == 0)
            lsCasesFiltered = lsCases.getValue();
        else  {
            for (Case c: lsCases.getValue()) {
                if (c.getName().toLowerCase().contains(nomorlizeText) || c.getUser().contains(searchText)) {
                    lsCasesFiltered.add(c);
                }
            }
        }

       return lsCasesFiltered;
    }
}
