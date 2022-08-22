package com.saslebengy.goldenmenu.demibouteillesTab;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.saslebengy.goldenmenu.R;
import com.saslebengy.goldenmenu.demibouteillesTab.ui.main.SectionsPagerAdapter;

/* loaded from: base/dex/classes2.dex */
public class DemiBouteillesActivity extends AppCompatActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demi_bouteilles);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        ((TabLayout) findViewById(R.id.tabs)).setupWithViewPager(viewPager);
        getWindow().addFlags(128);
    }
}