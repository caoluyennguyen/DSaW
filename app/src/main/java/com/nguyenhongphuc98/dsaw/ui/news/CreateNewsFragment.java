package com.nguyenhongphuc98.dsaw.ui.news;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.nguyenhongphuc98.dsaw.R;

public class CreateNewsFragment extends Fragment {

    private CreateNewsViewModel mViewModel;

    ImageButton btnUploadCover;
    EditText edtTitle;
    EditText edtContent;
    Button btnPost;

    public static CreateNewsFragment newInstance() {
        return new CreateNewsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_news, container, false);
        InitComponent(view);
        //InitEvent();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CreateNewsViewModel.class);
        // TODO: Use the ViewModel
        RegisterLiveData();
    }

    public void InitComponent(View view)
    {
        btnUploadCover = view.findViewById(R.id.btn_upload_cover);
        edtTitle = view.findViewById(R.id.edtTitle);
        edtContent = view.findViewById(R.id.edtContent);
    }

    public void InitEvent()
    {
        edtTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mViewModel.setTitle(edtTitle.getText().toString());
            }
        });
        edtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mViewModel.setContent(edtContent.getText().toString());
            }
        });
    }

    public void RegisterLiveData()
    {
        mViewModel.getTitle().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                edtTitle.setText(String.valueOf(s));
            }
        });
        mViewModel.getContent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                edtContent.setText(String.valueOf(s));
            }
        });
    }
}
