package com.nguyenhongphuc98.dsaw.ui.admin;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nguyenhongphuc98.dsaw.R;

public class AdminFragment extends Fragment {

    private AdminViewModel mViewModel;

    View importCase;
    View createWaring;
    View createSurvey;
    View viewSurvey;
    View createPost;
    View viewStatistics;

    public static AdminFragment newInstance() {
        return new AdminFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.admin_fragment, container, false);

        setupView(view);
        setupAction();

        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AdminViewModel.class);
        // TODO: Use the ViewModel
    }

    private void setupView(View view) {
        importCase = view.findViewById(R.id.item_import_case);
        createWaring = view.findViewById(R.id.item_create_waring);
        createSurvey = view.findViewById(R.id.item_create_survey);
        viewSurvey = view.findViewById(R.id.item_view_survey);
        createPost = view.findViewById(R.id.item_create_post);
        viewStatistics = view.findViewById(R.id.item_view_statistics);
    }

    private void setupAction() {
        importCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImportPatientPage();
            }
        });

        createWaring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateWaringPage();
            }
        });

        createSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateSurveyPage();
            }
        });

        viewSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewSurveyPage();
            }
        });

        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreatePostPage();
            }
        });

        viewStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatisticsPage();
            }
        });
    }

    public void openImportPatientPage() {
        Toast.makeText(getContext(),"nhập case",Toast.LENGTH_SHORT).show();
    }

    public void openCreateWaringPage() {
        Toast.makeText(getContext(),"create waring",Toast.LENGTH_SHORT).show();
    }

    public void openCreateSurveyPage() {
        Toast.makeText(getContext(),"create survey",Toast.LENGTH_SHORT).show();
    }

    public void openViewSurveyPage() {
        Toast.makeText(getContext(),"view survey",Toast.LENGTH_SHORT).show();
    }

    public void openCreatePostPage() {
        Toast.makeText(getContext(),"create post",Toast.LENGTH_SHORT).show();
    }
    public void openStatisticsPage() {
        Toast.makeText(getContext(),"statistics",Toast.LENGTH_SHORT).show();
    }
}
