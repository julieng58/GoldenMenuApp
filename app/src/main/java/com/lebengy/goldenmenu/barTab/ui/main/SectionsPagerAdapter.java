package com.lebengy.goldenmenu.barTab.ui.main;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.lebengy.goldenmenu.R;
import com.lebengy.goldenmenu.barFragments.AperitifBarFragment;
import com.lebengy.goldenmenu.barFragments.DigestifBarFragment;
import com.lebengy.goldenmenu.barFragments.EtAussiFragment;
import com.lebengy.goldenmenu.barFragments.WhiskyBarFragment;
import com.lebengy.goldenmenu.vinBlancFragments.AlsaceSavoieBlancFragment;
import com.lebengy.goldenmenu.vinBlancFragments.BourgogneBlancFragment;
import com.lebengy.goldenmenu.vinBlancFragments.LoireBlancFragment;
import com.lebengy.goldenmenu.vinBlancFragments.PlusAuSudBlancFragment;
import com.lebengy.goldenmenu.vinBlancFragments.VdRhoneBlancFragment;
import com.lebengy.goldenmenu.vinBlancFragments.VinDePaysBlancFragment;

/* loaded from: base/dex/classes2.dex */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private static final int[] TAB_TITLES= new int[]{R.string.tab_text_aperitifs,R.string.tab_text_whisky,R.string.tab_text_digestifs,R.string.tab_text_etAussi};
    private final Context mContext;

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return 4;
    }

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int position) {
        if (position != 0) {
            if (position == 1) {
                return WhiskyBarFragment.newInstance("", "");
            }
            if (position == 2) {
                return DigestifBarFragment.newInstance("", "");
            }
            if (position == 3) {
                return EtAussiFragment.newInstance("", "");
            }
            return com.lebengy.goldenmenu.vinsBlancTab.ui.main.PlaceholderFragment.newInstance(position + 1);
        }
        return AperitifBarFragment.newInstance("", "");
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int position) {
        return this.mContext.getResources().getString(TAB_TITLES[position]);
    }
}