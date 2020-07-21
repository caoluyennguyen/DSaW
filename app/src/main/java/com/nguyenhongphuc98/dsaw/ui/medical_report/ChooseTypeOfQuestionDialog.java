package com.nguyenhongphuc98.dsaw.ui.medical_report;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.nguyenhongphuc98.dsaw.R;

import java.util.ArrayList;

public class ChooseTypeOfQuestionDialog extends DialogFragment {
    TextView imgQuestion;
    TextView txtQuestion;
    CreateQuestionDialog createQuestionDialog;
    Bundle preArgs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_choose_type_of_question, container, false);
        preArgs = getArguments();

        InitComponent(view);
        InitEvent();

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            String editQuestionString = data.getStringExtra("EDIT_QUESTION");
            String questionType = data.getStringExtra("TYPE_QUESTION");
            Log.d("Create survey", "Question get: " + editQuestionString);
            Intent intent = new Intent();
            intent.putExtra("EDIT_QUESTION", editQuestionString);
            intent.putExtra("TYPE_QUESTION", questionType);
            getTargetFragment().onActivityResult(
                    getTargetRequestCode(), 0, intent);
        }
    }

    public void InitComponent(View view)
    {
        imgQuestion = view.findViewById(R.id.fileQuestion);
        txtQuestion = view.findViewById(R.id.txtQuestion);
    }

    public void InitEvent()
    {
        imgQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createQuestionDialog = new CreateQuestionDialog();
                createQuestionDialog.show(getFragmentManager(), "Create new question");
                createQuestionDialog.setTargetFragment(ChooseTypeOfQuestionDialog.this, 0);
                Bundle args = new Bundle();
                args.putString("type", "image");
                args.putInt("number", preArgs.getInt("number"));
                createQuestionDialog.setArguments(args);
                getDialog().dismiss();
            }
        });

        txtQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createQuestionDialog = new CreateQuestionDialog();
                createQuestionDialog.show(getFragmentManager(), "Create new question");
                createQuestionDialog.setTargetFragment(ChooseTypeOfQuestionDialog.this, 0);
                Bundle args = new Bundle();
                args.putString("type", "text");
                args.putInt("number", preArgs.getInt("number"));
                createQuestionDialog.setArguments(args);
                getDialog().dismiss();
            }
        });
    }
}