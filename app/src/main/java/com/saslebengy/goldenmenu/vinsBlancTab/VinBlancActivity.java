package com.saslebengy.goldenmenu.vinsBlancTab;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.saslebengy.goldenmenu.R;
import com.saslebengy.goldenmenu.vinsBlancTab.ui.main.SectionsPagerAdapter;

/* loaded from: base/dex/classes2.dex */
public class VinBlancActivity extends AppCompatActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vin_blanc);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        ((TabLayout) findViewById(R.id.tabs)).setupWithViewPager(viewPager);
        getWindow().addFlags(128);
    }
}