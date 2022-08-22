package com.saslebengy.goldenmenu.vinsRougeTab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.saslebengy.goldenmenu.R;



/* loaded from: base/dex/classes2.dex */
public class PlaceholderFragmentVins extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private PageViewModelVins pageViewModelVins;

    public static PlaceholderFragmentVins newInstance(int index) {
        PlaceholderFragmentVins placeholderFragmentVins = new PlaceholderFragmentVins();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        placeholderFragmentVins.setArguments(bundle);
        return placeholderFragmentVins;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.pageViewModelVins = (PageViewModelVins) new ViewModelProvider(this).get(PageViewModelVins.class);
        this.pageViewModelVins.setIndex(getArguments() != null ? getArguments().getInt(ARG_SECTION_NUMBER) : 1);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_les_menus, container, false);
        final TextView textView = (TextView) inflate.findViewById(R.id.section_label);
        this.pageViewModelVins.getText().observe(getViewLifecycleOwner(), new Observer<String>() { // from class: com.lebengy.goldenmenu.vinsRougeTab.PlaceholderFragmentVins.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
        return inflate;
    }
}