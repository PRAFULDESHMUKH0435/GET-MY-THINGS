package com.getmythings.admin2.ui.home;

import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ImageView imageView;

    public HomeViewModel ( ) {
        mText = new MutableLiveData<>( );

    }

    public LiveData<String> getText ( ) {
        return mText;
    }


}