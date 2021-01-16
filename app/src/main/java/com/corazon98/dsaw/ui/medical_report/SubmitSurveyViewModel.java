package com.corazon98.dsaw.ui.medical_report;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.corazon98.dsaw.adaptor.QuestionAdapter;
import com.corazon98.dsaw.data.DataManager;
import com.corazon98.dsaw.data.model.Question;

import java.util.List;

public class SubmitSurveyViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<QuestionAdapter> mAdaptor;

    private QuestionAdapter adaptor;
    private List<Question> lsQuestion;
    private MutableLiveData<List<Question>> mListQuestion;

    public SubmitSurveyViewModel()
    {
        mAdaptor = new MutableLiveData<>();
        mListQuestion = new MutableLiveData<>();
    }

    public void FetchData()
    {
        DataManager.Instance().GetAllQuestion(mListQuestion);
    }

    public LiveData<QuestionAdapter> getAdaptor() {
        return mAdaptor;
    }

    public MutableLiveData<List<Question>> getmListQuestion() {
        return mListQuestion;
    }
}