package com.nguyenhongphuc98.dsaw.ui.medical_report;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.adaptor.MultichoiceQuestionAdaptor;
import com.nguyenhongphuc98.dsaw.adaptor.QuestionAdapter;
import com.nguyenhongphuc98.dsaw.data.DataCenter;
import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.Question;

import java.util.ArrayList;
import java.util.List;

public class SubmitSurvey extends Fragment {

    private SubmitSurveyViewModel mViewModel;
    private QuestionAdapter adapter;
    private List<Question> lsQuestion = new ArrayList<>();
    private List<String> lsAnswer;

    private ImageView btnBack;
    private ListView lvQuestion;
    private Button btnSubmit;

    public static SubmitSurvey newInstance() {
        return new SubmitSurvey();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_submit_survey, container, false);

        InitView(view);
        InitEvent();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SubmitSurveyViewModel.class);

        adapter = new QuestionAdapter(getContext(), lsQuestion);
        lvQuestion.setAdapter(adapter);

        // TODO: Use the ViewModel
        mViewModel.getmListQuestion().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> mListQuestion) {
                lsQuestion.clear();
                for (Question a : mListQuestion) {
                    lsQuestion.add(a);
                }

                if (lsQuestion.size() == 0)
                    lsQuestion.add(new Question("", new ArrayList<String>(), "", "Tạm thời chưa có câu trả lời", ""));

                adapter.notifyDataSetChanged();
            }
        });

        mViewModel.FetchData();
    }

    public void InitView(View view)
    {
        lvQuestion = view.findViewById(R.id.list_of_question);
        btnBack = view.findViewById(R.id.go_back_btn);
        btnSubmit = view.findViewById(R.id.summit_button);
    }

    public void InitEvent()
    {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.navigation_report);
            }
        });
        lvQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Selected item at position: " + position, Toast.LENGTH_LONG).show();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Submit survey", Toast.LENGTH_LONG).show();
                lsAnswer = new ArrayList<>();
                View parentView = null;

                for (int i = 0; i < lvQuestion.getCount(); i++)
                {
                    parentView = lvQuestion.getChildAt(i);
                    lsAnswer.add(((TextView) parentView.findViewById(R.id.edtAnswer)).getText().toString());
                }

                //save answer to firebase
                DataManager.Instance().AddNewAnswer(DataCenter.surveyID, DataCenter.currentUser.getIdentity(), lsQuestion, lsAnswer);
            }
        });
    }
}