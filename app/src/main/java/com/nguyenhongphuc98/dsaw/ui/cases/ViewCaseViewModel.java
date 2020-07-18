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
//        lsCases = new ArrayList<>();
//        lsCases.add(new Case("Ha Tinh",
//                "22:20 - 02/05/2020",
//                "24:50 - 12/05/2020",
//                "F0",
//                "case01",
//                "22356,44426",
//                "184335348",
//                "Nguyen Hong Phuc"));
//
//        lsCases.add(new Case("Ha Tinh",
//                "22:20 - 02/05/2020",
//                "24:50 - 12/05/2020",
//                "F1",
//                "case01",
//                "22356,44426",
//                "65986589",
//                "Nguyen Hong Loc"));
//
//        lsCases.add(new Case("Ha Tinh",
//                "22:20 - 02/05/2020",
//                "24:50 - 12/05/2020",
//                "F2",
//                "case01",
//                "22356,44426",
//                "24563256",
//                "Nguyen Hong To"));
//
//        lsCases.add(new Case("Ha Tinh",
//                "22:20 - 02/05/2020",
//                "24:50 - 12/05/2020",
//                "F0",
//                "case01",
//                "22356,44426",
//                "12365895",
//                "Nguyen Hong Pink"));
//
//
//        adaptor = new CaseAdaptor(this.context, lsCases);
//        mAdaptor.setValue(adaptor);
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
