package com.nguyenhongphuc98.dsaw.ui.medical_report;

import android.content.Context;
import android.util.Log;

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