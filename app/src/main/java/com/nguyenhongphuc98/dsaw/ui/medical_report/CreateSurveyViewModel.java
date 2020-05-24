package com.nguyenhongphuc98.dsaw.ui.medical_report;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyenhongphuc98.dsaw.adaptor.QuestionAdapter;
import com.nguyenhongphuc98.dsaw.data.model.Answer;
import com.nguyenhongphuc98.dsaw.data.model.Question;

import java.util.ArrayList;
import java.util.List;

public class CreateSurveyViewModel extends ViewModel {
    List<Question> lsQuestion;
    MutableLiveData<QuestionAdapter> mAdaptor;
    QuestionAdapter adaptor;
    Context context;

    // TODO: Implement the ViewModel
    public CreateSurveyViewModel() { mAdaptor = new MutableLiveData<>();}

    public MutableLiveData<QuestionAdapter> GetAdapter() {return mAdaptor;}

    public void setContext(Context c) {
        this.context = c;
        lsQuestion = new ArrayList<>();

        ArrayList<Answer> lsAnswer1 = new ArrayList<>();
        lsAnswer1.add(new Answer("Đau đầu"));
        lsAnswer1.add(new Answer("Khó thở"));
        lsAnswer1.add(new Answer("Tim dập nhanh"));
        lsAnswer1.add(new Answer("Viêm màng túi"));
        lsQuestion.add(new Question("1", lsAnswer1, "1", "Câu hỏi đầu tiên:", "MT"));

        //lsQuestion.add(new Question("2", null, "1", "Câu hỏi đầu tiên:", "text"));

        adaptor = new QuestionAdapter(this.context, lsQuestion);
        mAdaptor.setValue(adaptor);
    }
    public void AddNewQuestion(String question)
    {

    }
}
