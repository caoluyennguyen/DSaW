package com.nguyenhongphuc98.dsaw.ui.medical_report;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyenhongphuc98.dsaw.adaptor.PersonalReportAdaptor;
import com.nguyenhongphuc98.dsaw.data.model.Question;

import java.util.ArrayList;
import java.util.List;

public class PersonalReportViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<PersonalReportAdaptor> mAdaptor;

    private PersonalReportAdaptor adaptor;
    private List<Question> lsQuestion;

    private Context context;

    public PersonalReportViewModel() {
        mAdaptor = new MutableLiveData<>();
    }

    public void setContext(Context c) {
        this.context = c;

        lsQuestion = new ArrayList<>();
        ArrayList lsAnswer1 = new ArrayList<>();
        lsAnswer1.add("Đau đầu");
        lsAnswer1.add("Khó thở");
        lsAnswer1.add("Tim dập nhanh");
        lsAnswer1.add("Viêm màng túi");
        lsQuestion.add(new Question("1", lsAnswer1,"survey1_key", "Các triệu chứng gần đây của bạn?", "MT"));
        ArrayList lsAnswer2 = new ArrayList();
        lsAnswer2.add("Có");
        lsAnswer2.add("Không");
        lsQuestion.add(new Question("2", lsAnswer2, "survey1_key","Bạn có tiền sử bệnh tim không?", "MT"));
        adaptor = new PersonalReportAdaptor(this.context, lsQuestion);
        mAdaptor.setValue(adaptor);
    }

    public LiveData<PersonalReportAdaptor> getAdaptor() {
        return mAdaptor;
    }

    public List<Question> getListQuestion() {return  lsQuestion; }
}
