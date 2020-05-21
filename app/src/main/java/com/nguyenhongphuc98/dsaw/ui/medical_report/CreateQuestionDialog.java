package com.nguyenhongphuc98.dsaw.ui.medical_report;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.nguyenhongphuc98.dsaw.R;

public class CreateQuestionDialog extends DialogFragment {
    EditText edtQuestion;
    Button addBtn;

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
        return dialog;
    }

    public void InitComponent(View view)
    {
        addBtn = view.findViewById(R.id.button_add_answer);
    }

    public void InitEvent()
    {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
