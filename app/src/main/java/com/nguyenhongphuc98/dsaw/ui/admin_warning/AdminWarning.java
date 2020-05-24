package com.nguyenhongphuc98.dsaw.ui.admin_warning;

import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.nguyenhongphuc98.dsaw.R;

public class AdminWarning extends Fragment {
    private AdminWarningViewModel mViewModel;

    Switch mSwitch;
    LinearLayout mCmndLayout;
    EditText mTextContent;
    EditText mTextCmnd;
    Button mButton;

    public static AdminWarning newInstance() {
        return new AdminWarning();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_warning, container, false);
        InitComponent(view);
        InitEvent();
        Log.d("show", "View created");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("show", "View created");
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AdminWarningViewModel.class);
        // TODO: Use the ViewModel
        RegisterDataLiveListener();
    }

    public void InitComponent(View view)
    {
        mSwitch = (Switch) view.findViewById(R.id.stateSwitch);
        mCmndLayout = view.findViewById(R.id.cmnd);
        mTextContent = view.findViewById(R.id.contentEdit);
        mTextCmnd = view.findViewById(R.id.cmndEdit);
        mButton = view.findViewById(R.id.summit_warning_button);
    }

    public void InitEvent()
    {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Button is clicked",Toast.LENGTH_SHORT).show();
            }
        });

        mSwitch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) mCmndLayout.setVisibility(View.VISIBLE);
                else mCmndLayout.setVisibility(View.GONE);
            }
        });
    }

    public void RegisterDataLiveListener() {
        /*mViewModel.getContent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTextContent.setText(String.valueOf(s));
            }
        });
        mViewModel.getCMND().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTextCmnd.setText(String.valueOf(s));
            }
        });*/
    }
}
