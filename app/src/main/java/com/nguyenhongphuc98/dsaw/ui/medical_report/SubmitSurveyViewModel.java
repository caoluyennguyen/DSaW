package com.nguyenhongphuc98.dsaw.ui.medical_report;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyenhongphuc98.dsaw.adaptor.MultichoiceQuestionAdaptor;
import com.nguyenhongphuc98.dsaw.adaptor.QuestionAdapter;
import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.Answer;
import com.nguyenhongphuc98.dsaw.data.model.Question;

import java.util.ArrayList;
import java.util.List;

public class SubmitSurveyViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<QuestionAdapter> mAdaptor;

    private QuestionAdapter adaptor;
    private List<Question> lsQuestion;

    private Context context;

    public SubmitSurveyViewModel() {
        mAdaptor = new MutableLiveData<>();
    }

    public void setContext(Context c) {
        this.context = c;
        lsQuestion = new ArrayList<>();

        /*ArrayList<String> lsAnswer1 = new ArrayList<>();
        lsAnswer1.add("Đau đầu");
        lsAnswer1.add("Khó thở");
        lsAnswer1.add("Tim dập nhanh");
        lsAnswer1.add("Viêm màng túi");
        lsQuestion.add(new Question(lsAnswer1,"1","survey1_key", "Các triệu chứng gần đây của bạn?", "MT"));

        lsQuestion.add(new Question(null,"2","survey1_key","Thông tin thêm bạn muốn gửi:", "Text"));*/

        DataManager.Instance().GetAllQuestion(lsQuestion);

        adaptor = new QuestionAdapter(this.context, lsQuestion);
        mAdaptor.setValue(adaptor);
    }

    /*public LiveData<MultichoiceQuestionAdaptor> getAdaptor() {
        return mAdaptor;
    }*/
    public LiveData<QuestionAdapter> getAdaptor() {
        return mAdaptor;
    }

    public List<Question> getListQuestion() {return  lsQuestion; }
}
