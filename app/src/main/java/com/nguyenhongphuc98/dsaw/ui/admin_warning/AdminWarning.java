package com.nguyenhongphuc98.dsaw.ui.admin_warning;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.nguyenhongphuc98.dsaw.R;

public class AdminWarning extends Fragment {

    private AdminWarningViewModel mViewModel;

    public static AdminWarning newInstance() {
        return new AdminWarning();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(AdminWarningViewModel.class);

        View view = inflater.inflate(R.layout.admin_warning_fragment, container, false);
        Switch aSwitch = view.findViewById(R.id.stateSwitch);
        TextView cmndTitle = view.findViewById(R.id.stateSwitch);
        EditText cmndEdit = view.findViewById(R.id.cmndEdt);

        setUpView(view);
        Log.d("show", "View created");

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("show", "Switch clicked");

            }
        });
        return inflater.inflate(R.layout.admin_warning_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = ViewModelProviders.of(this).get(AdminWarningViewModel.class);
        // TODO: Use the ViewModel
    }

    private void setUpView(View view)
    {

    }
}
