package com.nguyenhongphuc98.dsaw.ui.medical_report;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyenhongphuc98.dsaw.adaptor.TextQuestionAdapter;
import com.nguyenhongphuc98.dsaw.data.model.Question;

import java.util.ArrayList;
import java.util.List;

public class CreateSurveyViewModel extends ViewModel {
    List<Question> lsQuestion;
    MutableLiveData<TextQuestionAdapter> mAdaptor;
    TextQuestionAdapter adaptor;
    Context context;

    // TODO: Implement the ViewModel
    public CreateSurveyViewModel() { mAdaptor = new MutableLiveData<>();}

    public MutableLiveData<TextQuestionAdapter> GetAdapter() {return mAdaptor;}

    public void setContext(Context c) {
        this.context = c;
        lsQuestion = new ArrayList<>();
        lsQuestion.add(new Question("1", null, "1", "Câu hỏi đầu tiên:", "text"));

        adaptor = new TextQuestionAdapter(this.context, lsQuestion);
        mAdaptor.setValue(adaptor);
    }
    public void AddNewQuestion(String question)
    {

    }
}
