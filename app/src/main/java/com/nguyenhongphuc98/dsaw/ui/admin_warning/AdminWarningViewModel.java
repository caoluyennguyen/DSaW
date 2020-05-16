package com.nguyenhongphuc98.dsaw.ui.admin_warning;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdminWarningViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mContent = new MutableLiveData<>();
    private MutableLiveData<String> mCMND = new MutableLiveData<>();

    public AdminWarningViewModel() {
        mContent.setValue("");
        mCMND.setValue("");
    }

    public MutableLiveData<String> getContent() {
        return mContent;
    }

    public MutableLiveData<String> getCMND() {
        return mCMND;
    }
}
