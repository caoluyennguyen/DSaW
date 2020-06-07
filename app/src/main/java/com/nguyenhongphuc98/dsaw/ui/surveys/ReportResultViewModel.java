package com.nguyenhongphuc98.dsaw.ui.surveys;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.ReportModel;

import java.util.ArrayList;
import java.util.List;

public class ReportResultViewModel extends ViewModel {

    private MutableLiveData<List<ReportModel>> lsReports;

    public ReportResultViewModel() {
        lsReports = new MutableLiveData<>();

    }

    public void fetchData(String reportID) {

        DataManager.Instance().fetchAnswerForReport(lsReports, reportID);
//        List<ReportModel> ls = new ArrayList<>();
//        List<String> ans = new ArrayList<>();
//        ans.add("Người báo cáo: Phúc");
//        ans.add("Người vi phạm: Plazaaaa");
//        ans.add("Nội dung: đi chơi không về");
//
//        ls.add(new ReportModel("null",ans));
//
//        List<String> ans2 = new ArrayList<>();
//        ans2.add("Người báo cáo: Abc");
//        ans2.add("Người vi phạm: kkk");
//        ans2.add("Nội dung: đi chơi về như không");
//        ls.add(new ReportModel("null",ans2));
//
//        lsReports.setValue(ls);
    }

    public MutableLiveData<List<ReportModel>> getLsReport() {
        return lsReports;
    }
}
