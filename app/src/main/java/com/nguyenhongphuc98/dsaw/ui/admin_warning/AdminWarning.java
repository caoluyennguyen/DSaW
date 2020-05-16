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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.nguyenhongphuc98.dsaw.R;

import org.w3c.dom.Text;

public class AdminWarning extends Fragment {

    private AdminWarningViewModel mViewModel;

    SwitchCompat mSwitch;
    LinearLayout mCmndLayout;
    EditText mTextContent;
    EditText mTextCmnd;

    public static AdminWarning newInstance() {
        return new AdminWarning();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_warning_fragment, container, false);
        InitComponent(view);

        Log.d("show", "View created");

        return inflater.inflate(R.layout.admin_warning_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("show", "View created");
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AdminWarningViewModel.class);
        // TODO: Use the ViewModel
        RegisterDataLiveListener();
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("check", "Switch is checked");
                if (isChecked) mCmndLayout.setVisibility(View.GONE);
                else mCmndLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    public void InitComponent(View view)
    {
        mSwitch = view.findViewById(R.id.stateSwitch);
        mCmndLayout = view.findViewById(R.id.cmnd);
        mTextContent = view.findViewById(R.id.contentEdit);
        mTextCmnd = view.findViewById(R.id.cmndEdit);
    }

    public void RegisterDataLiveListener() {
        mViewModel.getContent().observe(this, new Observer<String>() {
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
        });
    }
}
