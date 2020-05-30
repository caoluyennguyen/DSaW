package com.nguyenhongphuc98.dsaw.ui.medical_report;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.adaptor.AnswerAdaptor;
import com.nguyenhongphuc98.dsaw.data.DataManager;

public class CreateQuestionDialog extends DialogFragment {
    TextView title;
    EditText edtQuestion;
    EditText edtAnswer;
    ListView lsNewAnswer;
    Button addQuesBtn;
    Button addAnsBtn;

    Bundle mArgs;
    String typeOfQuestion;

    private CreateQuestionDialogViewModel mViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_create_survey, container, false);

        InitComponent(view);
        InitEvent();

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        mViewModel = ViewModelProviders.of(this).get(CreateQuestionDialogViewModel.class);
        mViewModel.setContext(getContext());
        mViewModel.getAdaptor().observe(this, new Observer<AnswerAdaptor>() {
            @Override
            public void onChanged(AnswerAdaptor answerAdaptor) {
                lsNewAnswer.setAdapter(answerAdaptor);
            }
        });

        return dialog;
    }

    public void InitComponent(View view)
    {
        title = view.findViewById(R.id.creare_answer_title);
        edtQuestion = view.findViewById(R.id.edtQuestion);
        edtAnswer = view.findViewById(R.id.edtCreateAnswer);
        addQuesBtn = view.findViewById(R.id.button_add_question);
        addAnsBtn = view.findViewById(R.id.btn_add_new_answer);
        lsNewAnswer = view.findViewById(R.id.list_of_new_answer);

        mArgs = getArguments();
        typeOfQuestion = mArgs.getString("type");
        Log.d("Type of question", "Type of question is: " + typeOfQuestion);

        if (typeOfQuestion.equalsIgnoreCase("text")){
            addAnsBtn.setVisibility(View.GONE);
            lsNewAnswer.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            edtAnswer.setVisibility(View.GONE);
        }
    }

    public void InitEvent()
    {
        addQuesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mViewModel.getLsAnswer().isEmpty()) Toast.makeText(getContext(), "Không được để trống câu trả lời!", Toast.LENGTH_LONG).show();
                else if (edtQuestion.getText().toString().isEmpty()) Toast.makeText(getContext(), "Không được để trống câu hỏi!", Toast.LENGTH_LONG).show();
                else {
                    if (lsNewAnswer.getVisibility() == View.GONE) DataManager.Instance().AddNewQuestion(edtQuestion.getText().toString(), null, "text");
                    else DataManager.Instance().AddNewQuestion(edtQuestion.getText().toString(), mViewModel.getLsAnswer(), "MT");
                    Toast.makeText(getContext(), "Bạn vừa mới tạo môt câu hỏi mới", Toast.LENGTH_LONG).show();
                    getDialog().dismiss();
                }
            }
        });

        addAnsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtAnswer.getText().toString().isEmpty()) Toast.makeText(getContext(), "Không được để trống câu trả lời!", Toast.LENGTH_LONG).show();
                else mViewModel.AddNewAnswer(edtAnswer.getText().toString());
            }
        });
    }
}
