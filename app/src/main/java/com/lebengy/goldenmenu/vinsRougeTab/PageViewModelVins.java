package com.lebengy.goldenmenu.vinsRougeTab;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

/* loaded from: base/dex/classes2.dex */
public class PageViewModelVins extends ViewModel {
    private MutableLiveData<Integer> mIndex;
    private LiveData<String> mText;

    public PageViewModelVins() {
        MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
        this.mIndex = mutableLiveData;
        this.mText = Transformations.map(mutableLiveData, new Function<Integer, String>() { // from class: com.lebengy.goldenmenu.vinsRougeTab.PageViewModelVins.1
            @Override // androidx.arch.core.util.Function
            public String apply(Integer input) {
                return "Hello world from section: " + input;
            }
        });
    }

    public void setIndex(int index) {
        this.mIndex.setValue(Integer.valueOf(index));
    }

    public LiveData<String> getText() {
        return this.mText;
    }
}