package com.nguyenhongphuc98.dsaw.ui.news;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.data.DataManager;

import java.io.IOException;

public class CreateNewsFragment extends Fragment {
    final int CODE_OPEN_DOCUMENT = 22;
    private CreateNewsViewModel mViewModel;

    ImageButton btnUploadCover;
    Uri coverImg;
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
        InitEvent();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CreateNewsViewModel.class);
        // TODO: Use the ViewModel
        RegisterLiveData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //show image on avatar
        try {
            if(data!=null)
            {
                Uri selectedFile = data.getData();
                coverImg = selectedFile;
                Toast.makeText(getContext(), "Tải ảnh bìa thành công", Toast.LENGTH_LONG).show();
                btnUploadCover.setImageURI(coverImg);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InitComponent(View view)
    {
        btnUploadCover = view.findViewById(R.id.btn_upload_cover);
        edtTitle = view.findViewById(R.id.edtTitle);
        edtContent = view.findViewById(R.id.edtContent);
        btnPost = view.findViewById(R.id.button_post);
    }

    public void InitEvent()
    {
        btnUploadCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intentOpenFile=new Intent().setType("*/*").setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intentOpenFile,"Choose image"),CODE_OPEN_DOCUMENT);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Potentially direct the user to the Market with a Dialog
                    Toast.makeText(getContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.Instance().AddNewPost(edtTitle.getText().toString(), edtContent.getText().toString(),
                        DataManager.Instance().UploadFileToFirebase("posts/", coverImg));
                Toast.makeText(getContext(), "Đăng bài viết thành công", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void RegisterLiveData()
    {
        mViewModel.getTitle().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                edtTitle.setText(s);
            }
        });
        mViewModel.getContent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                edtContent.setText(s);
            }
        });
    }
}
