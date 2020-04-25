package com.nguyenhongphuc98.dsaw.ui.admin_warning;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyenhongphuc98.dsaw.R;

public class AdminWarning extends Fragment {

    private AdminWarningViewModel mViewModel;

    public static AdminWarning newInstance() {
        return new AdminWarning();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.admin_warning_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = ViewModelProviders.of(this).get(AdminWarningViewModel.class);
        // TODO: Use the ViewModel
    }

}
