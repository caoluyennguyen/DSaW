package com.nguyenhongphuc98.dsaw.ui.cases;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyenhongphuc98.dsaw.adaptor.CaseAdaptor;
import com.nguyenhongphuc98.dsaw.data.model.Case;

import java.util.ArrayList;
import java.util.List;

public class ViewCaseViewModel extends ViewModel {

    private MutableLiveData<CaseAdaptor> mAdaptor;

    private CaseAdaptor adaptor;
    private List<Case> lsCases;

    private Context context;

    public ViewCaseViewModel() {
        mAdaptor = new MutableLiveData<>();
    }

    public LiveData<CaseAdaptor> getAdaptor() {
        return mAdaptor;
    }

    public void setContext(Context c) {
        this.context = c;
        lsCases = new ArrayList<>();
        lsCases.add(new Case("Ha Tinh",
                "22:20 - 02/05/2020",
                "24:50 - 12/05/2020",
                "F0",
                "case01",
                "22356,44426",
                "184335348",
                "Nguyen Hong Phuc"));

        lsCases.add(new Case("Ha Tinh",
                "22:20 - 02/05/2020",
                "24:50 - 12/05/2020",
                "F1",
                "case01",
                "22356,44426",
                "65986589",
                "Nguyen Hong Loc"));

        lsCases.add(new Case("Ha Tinh",
                "22:20 - 02/05/2020",
                "24:50 - 12/05/2020",
                "F2",
                "case01",
                "22356,44426",
                "24563256",
                "Nguyen Hong To"));

        lsCases.add(new Case("Ha Tinh",
                "22:20 - 02/05/2020",
                "24:50 - 12/05/2020",
                "F0",
                "case01",
                "22356,44426",
                "12365895",
                "Nguyen Hong Pink"));


        adaptor = new CaseAdaptor(this.context, lsCases);
        mAdaptor.setValue(adaptor);
    }

    public void updateCase(String cmnd, String f) {

        //find user from db and save new case

        Case c = new Case("Default",
                "22:20 - 02/05/2020",
                "24:50 - 12/05/2020",
                f,
                "case01",
                "22356,44426",
                cmnd,
                "Nguyen Hong Phuc");
        lsCases.add(c);
        adaptor = new CaseAdaptor(this.context, lsCases);
        mAdaptor.setValue(adaptor);
    }

    public void filterByF(String f) {
        List<Case> lsCasesFiltered = new ArrayList<>();
        for (Case c: lsCases) {
            if (f.equals(c.getF())) {
                lsCasesFiltered.add(c);
            }
        }

        //it mean select see all
        if (lsCasesFiltered.size() == 0)
            lsCasesFiltered = lsCases;

        adaptor = null;
        adaptor = new CaseAdaptor(this.context, lsCasesFiltered);
        mAdaptor.setValue(adaptor);
    }

    public void filterByNameOrCMND(String searchText) {

        List<Case> lsCasesFiltered = new ArrayList<>();
        String nomorlizeText = searchText.toLowerCase();

        if (searchText.isEmpty() || searchText.length() == 0)
            lsCasesFiltered = lsCases;
        else  {
            for (Case c: lsCases) {
                if (c.getName().toLowerCase().contains(nomorlizeText) || c.getUser().contains(searchText)) {
                    lsCasesFiltered.add(c);
                }
            }
        }

        adaptor = null;
        adaptor = new CaseAdaptor(this.context, lsCasesFiltered);
        mAdaptor.setValue(adaptor);
    }
}
