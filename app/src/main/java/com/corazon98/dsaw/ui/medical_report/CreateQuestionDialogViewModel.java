package com.corazon98.dsaw.ui.medical_report;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.corazon98.dsaw.adaptor.AnswerAdaptor;

import java.util.ArrayList;

public class CreateQuestionDialogViewModel extends ViewModel {
    private MutableLiveData<AnswerAdaptor> mAdaptor;
    private AnswerAdaptor adaptor;
    private ArrayList<String> lsAnswer;
    private Context context;

    public CreateQuestionDialogViewModel() {
        mAdaptor = new MutableLiveData<>();
    }

    public void setContext(Context c) {
        this.context = c;
        lsAnswer = new ArrayList<>();

        //lsAnswer.add("Cau tra loi 1");

        adaptor = new AnswerAdaptor(this.context, lsAnswer);
        mAdaptor.setValue(adaptor);
    }

    public MutableLiveData<AnswerAdaptor> getAdaptor() {
        return mAdaptor;
    }

    public void AddNewAnswer(String answer)
    {
        lsAnswer.add(answer);
        adaptor = new AnswerAdaptor(this.context, lsAnswer);
        mAdaptor.setValue(adaptor);
    }

    public ArrayList<String> getLsAnswer() {
        return lsAnswer;
    }
}
