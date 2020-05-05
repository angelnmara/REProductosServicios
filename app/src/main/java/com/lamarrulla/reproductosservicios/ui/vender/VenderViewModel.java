package com.lamarrulla.reproductosservicios.ui.vender;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VenderViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VenderViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}