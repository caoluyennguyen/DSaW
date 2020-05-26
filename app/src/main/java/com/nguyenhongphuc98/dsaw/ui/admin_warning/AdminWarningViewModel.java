package com.nguyenhongphuc98.dsaw.ui.admin_warning;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nguyenhongphuc98.dsaw.data.DataManager;
import com.nguyenhongphuc98.dsaw.data.model.Warning;

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

    public void setContent(String mContent) {
        this.mContent.setValue(mContent);
    }

    public void setCMND(String mCMND) {
        this.mCMND.setValue(mCMND);
    }

    public void CreateWarning(Warning warning)
    {
        DataManager.Instance().CreateWarning(warning);
    }
}
