package com.corazon98.dsaw.ui.medical_report;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.corazon98.dsaw.adaptor.QuestionAdapter;
import com.corazon98.dsaw.data.DataManager;
import com.corazon98.dsaw.data.model.Question;

import java.util.ArrayList;
import java.util.List;

public class CreateSurveyViewModel extends ViewModel {
    List<Question> lsQuestion;
    MutableLiveData<QuestionAdapter> mAdaptor;
    QuestionAdapter adaptor;
    Context context;
    private MutableLiveData<List<Question>> mListQuestion;

    // TODO: Implement the ViewModel
    public CreateSurveyViewModel()
    {
        mAdaptor = new MutableLiveData<>();
        mListQuestion = new MutableLiveData<>();
    }

    public MutableLiveData<QuestionAdapter> GetAdapter() {return mAdaptor;}

    public void setContext(Context c) {
        this.context = c;
        lsQuestion = new ArrayList<>();

        /*ArrayList<String> lsAnswer1 = new ArrayList<>();
        lsAnswer1.add("Đau đầu");
        lsAnswer1.add("Khó thở");
        lsAnswer1.add("Tim dập nhanh");
        lsAnswer1.add("Viêm màng túi");
        lsQuestion.add(new Question(lsAnswer1, "1", "1", "Câu hỏi đầu tiên:", "MT"));

        lsQuestion.add(new Question(null, "2", "1", "Câu hỏi đầu tiên:", "text"));*/

        DataManager.Instance().GetAllQuestion(lsQuestion);

        adaptor = new QuestionAdapter(this.context, lsQuestion);
        mAdaptor.setValue(adaptor);
    }
    public void AddNewQuestion(String question)
    {

    }
}
