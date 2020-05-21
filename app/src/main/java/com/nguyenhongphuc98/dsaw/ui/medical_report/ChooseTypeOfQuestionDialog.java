package com.nguyenhongphuc98.dsaw.ui.medical_report;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.nguyenhongphuc98.dsaw.R;

public class ChooseTypeOfQuestionDialog extends DialogFragment {
    TextView cbQuestion;
    TextView txtQuestion;
    CreateQuestionDialog createQuestionDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_choose_type_of_question, container, false);

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

    public void InitComponent(View view)
    {
        cbQuestion = view.findViewById(R.id.cbQuestion);
        txtQuestion = view.findViewById(R.id.txtQuestion);
    }

    public void InitEvent()
    {
        cbQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createQuestionDialog = new CreateQuestionDialog();
                createQuestionDialog.show(getFragmentManager(), "Create new question");
            }
        });
    }
}