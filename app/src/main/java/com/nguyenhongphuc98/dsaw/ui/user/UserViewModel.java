package com.nguyenhongphuc98.dsaw.ui.user;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    private MutableLiveData<String> mName = new MutableLiveData<>();
    private MutableLiveData<String> mCMND = new MutableLiveData<>();
    private MutableLiveData<String> mDayOfBirth = new MutableLiveData<>();
    private MutableLiveData<String> mContact = new MutableLiveData<>();

    public UserViewModel() {
        mName.setValue("");
        mCMND.setValue("");
        mDayOfBirth.setValue("");
        mContact.setValue("");
    }

    public MutableLiveData<String> getName() {
        return mName;
    }

    public MutableLiveData<String> getCMND() {
        return mCMND;
    }

    public MutableLiveData<String> getDayOfBirth() {
        return mDayOfBirth;
    }

    public MutableLiveData<String> getContact() {
        return mContact;
    }
}
