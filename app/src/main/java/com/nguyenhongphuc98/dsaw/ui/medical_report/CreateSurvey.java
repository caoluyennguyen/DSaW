package com.nguyenhongphuc98.dsaw.ui.medical_report;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.adaptor.QuestionAdapter;
import com.nguyenhongphuc98.dsaw.adaptor.TextQuestionAdapter;

public class CreateSurvey extends Fragment {

    private CreateSurveyViewModel mViewModel;

    Spinner typeOfSurvey;
    Button chooseTypeQuestion;
    ListView lvQuestion;
    ChooseTypeOfQuestionDialog typeOfQuestionDialog;

    public static CreateSurvey newInstance() {
        return new CreateSurvey();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_create_survey, container, false);

        InitComponent(view);
        InitEvent();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CreateSurveyViewModel.class);
        // TODO: Use the ViewModel
        mViewModel.setContext(this.getContext());
        mViewModel.GetAdapter().observe(this, new Observer<QuestionAdapter>() {
            @Override
            public void onChanged(QuestionAdapter questionAdapter) {
                lvQuestion.setAdapter(questionAdapter);
            }
        });
    }

    public void InitComponent(View view){
        chooseTypeQuestion =view.findViewById(R.id.btn_choose_type_question);
        typeOfSurvey = view.findViewById(R.id.type_of_survey);
        lvQuestion = view.findViewById(R.id.listview_of_new_question);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.type_of_survey, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        typeOfSurvey.setAdapter(adapter);
    }

    public void InitEvent(){
        chooseTypeQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeOfQuestionDialog = new ChooseTypeOfQuestionDialog();
                typeOfQuestionDialog.show(getFragmentManager(), "Type of question");
            }
        });
    }
}
