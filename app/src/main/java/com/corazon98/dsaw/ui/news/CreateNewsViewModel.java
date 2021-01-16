package com.corazon98.dsaw.ui.news;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CreateNewsViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<String> mTitle = new MutableLiveData<>();
    MutableLiveData<String> mContent = new MutableLiveData<>();

    public CreateNewsViewModel() {
        mTitle.setValue("");
        mContent.setValue("");
    }

    public MutableLiveData<String> getContent() {
        return mContent;
    }

    public MutableLiveData<String> getTitle() {
        return mTitle;
    }

    public void setContent(String mContent) {
        this.mContent.setValue(mContent);
    }

    public void setTitle(String mTitle) {
        this.mContent.setValue(mTitle);
    }
}
