package com.lebengy.goldenmenu.ui.main;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.lebengy.goldenmenu.R;
import com.lebengy.goldenmenu.menuFragment.CarteFragment;
import com.lebengy.goldenmenu.menuFragment.FournisseurFragment;
import com.lebengy.goldenmenu.menuFragment.Menu25Fragment;
import com.lebengy.goldenmenu.menuFragment.Menu32Fragment;
import com.lebengy.goldenmenu.menuFragment.NosFormulesFragment;
import com.lebengy.goldenmenu.vinBlancFragments.AlsaceSavoieBlancFragment;
import com.lebengy.goldenmenu.vinBlancFragments.BourgogneBlancFragment;
import com.lebengy.goldenmenu.vinBlancFragments.LoireBlancFragment;
import com.lebengy.goldenmenu.vinBlancFragments.PlusAuSudBlancFragment;
import com.lebengy.goldenmenu.vinBlancFragments.VdRhoneBlancFragment;
import com.lebengy.goldenmenu.vinBlancFragments.VinDePaysBlancFragment;

/* loaded from: base/dex/classes2.dex */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private static final int[] TAB_TITLES= new int[]{R.string.tab_text_5,R.string.tab_text_1,R.string.tab_text_2,R.string.tab_text_3,R.string.tab_text_4};
    private final Context mContext;

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return 5;
    }

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int position) {
        if (position != 0) {
            if (position == 2) {
                return Menu25Fragment.newInstance("", "");
            }
            if (position == 3) {
                return Menu32Fragment.newInstance("", "");
            }
            if (position == 4) {
                return CarteFragment.newInstance("", "");
            }
            if (position == 1) {
                return NosFormulesFragment.newInstance("", "");
            }
            return PlaceholderFragment.newInstance(position + 1);
        }
        return FournisseurFragment.newInstance("", "");
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int position) {
        return this.mContext.getResources().getString(TAB_TITLES[position]);
    }
}