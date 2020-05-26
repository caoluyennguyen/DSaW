package com.nguyenhongphuc98.dsaw.ui.user;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nguyenhongphuc98.dsaw.R;
import com.nguyenhongphuc98.dsaw.data.DataManager;

public class UserFragment extends Fragment {

    private UserViewModel mViewModel;

    private TextView mTextName;
    private TextView mTextCMND;
    private TextView mTextDayofBirth;
    private TextView mTextContact;
    private Button mBtnUpdate;

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(UserViewModel.class);
        mViewModel.GetUser("manager");
        View root = inflater.inflate(R.layout.fragment_user, container, false);
        InitComponent(root);
        InitEvent();
        RegisterDataLiveListener();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        // TODO: Use the ViewModel
    }

    public void InitComponent(View view) {
        mTextName = view.findViewById(R.id.txtName);
        mTextCMND = view.findViewById(R.id.txtCMND);
        mTextDayofBirth = view.findViewById(R.id.txtDayOfBirth);
        mTextContact = view.findViewById(R.id.txtContact);
        mBtnUpdate = view.findViewById(R.id.user_update_btn);
    }

    public void InitEvent()
    {
        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.setmName(mTextName.getText().toString());
                mViewModel.setmCMND(mTextCMND.getText().toString());
                mViewModel.setmDayOfBirth(mTextDayofBirth.getText().toString());
                mViewModel.setmContact(mTextContact.getText().toString());
                mViewModel.UpdateUser(mTextName.getText().toString(), mTextCMND.getText().toString(), mTextDayofBirth.getText().toString(), mTextContact.getText().toString());
            }
        });
    }

    public void RegisterDataLiveListener() {
        mViewModel.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTextName.setText(String.valueOf(s));
            }
        });
        mViewModel.getCMND().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTextCMND.setText(String.valueOf(s));
            }
        });
        mViewModel.getDayOfBirth().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTextDayofBirth.setText(String.valueOf(s));
            }
        });
        mViewModel.getContact().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTextContact.setText(String.valueOf(s));
            }
        });
    }
}
