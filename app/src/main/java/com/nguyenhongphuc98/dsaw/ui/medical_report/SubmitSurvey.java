package com.nguyenhongphuc98.dsaw.ui.medical_report;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.adaptor.MultichoiceQuestionAdaptor;
import com.nguyenhongphuc98.dsaw.adaptor.QuestionAdapter;

public class SubmitSurvey extends Fragment {

    private SubmitSurveyViewModel mViewModel;

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

        // TODO: Use the ViewModel
        mViewModel.setContext(getContext());
        mViewModel.getAdaptor().observe(this, new Observer<QuestionAdapter>() {
            @Override
            public void onChanged(QuestionAdapter questionAdaptor) {
                lvQuestion.setAdapter(questionAdaptor);
            }
        });
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
            }
        });
    }
}
