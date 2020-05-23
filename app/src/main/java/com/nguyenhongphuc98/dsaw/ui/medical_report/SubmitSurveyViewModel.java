package com.nguyenhongphuc98.dsaw.ui.medical_report;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyenhongphuc98.dsaw.adaptor.MultichoiceQuestionAdaptor;
import com.nguyenhongphuc98.dsaw.data.model.Answer;
import com.nguyenhongphuc98.dsaw.data.model.Question;

import java.util.ArrayList;
import java.util.List;

public class SubmitSurveyViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<MultichoiceQuestionAdaptor> mAdaptor;

    private MultichoiceQuestionAdaptor adaptor;
    private List<Question> lsQuestion;

    private Context context;

    public SubmitSurveyViewModel() {
        mAdaptor = new MutableLiveData<>();
    }

    public void setContext(Context c) {
        this.context = c;

        lsQuestion = new ArrayList<>();
        ArrayList<Answer> lsAnswer1 = new ArrayList<>();
        lsAnswer1.add(new Answer("Đau đầu"));
        lsAnswer1.add(new Answer("Khó thở"));
        lsAnswer1.add(new Answer("Tim dập nhanh"));
        lsAnswer1.add(new Answer("Viêm màng túi"));
        lsQuestion.add(new Question("1", lsAnswer1,"survey1_key", "Các triệu chứng gần đây của bạn?", "MT"));
        ArrayList<Answer> lsAnswer2 = new ArrayList();
        lsAnswer2.add(new Answer("Có"));
        lsAnswer2.add(new Answer("Không"));
        lsQuestion.add(new Question("2", lsAnswer2, "survey1_key","Bạn có tiền sử bệnh tim không?", "MT"));
        adaptor = new MultichoiceQuestionAdaptor(this.context, lsQuestion);
        mAdaptor.setValue(adaptor);
    }

    public LiveData<MultichoiceQuestionAdaptor> getAdaptor() {
        return mAdaptor;
    }

    public List<Question> getListQuestion() {return  lsQuestion; }
}
