package com.lebengy.goldenmenu.demibouteillesTab.ui.main;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.lebengy.goldenmenu.R;
import com.lebengy.goldenmenu.demiBouteillesFragments.BeaujolaisDemiFragment;
import com.lebengy.goldenmenu.demiBouteillesFragments.BordeauxDemiFragment;
import com.lebengy.goldenmenu.demiBouteillesFragments.BourgogneDemiFragment;
import com.lebengy.goldenmenu.demiBouteillesFragments.LoireDemiFragment;
import com.lebengy.goldenmenu.demiBouteillesFragments.RhoneDemiFragment;
import com.lebengy.goldenmenu.vinBlancFragments.AlsaceSavoieBlancFragment;
import com.lebengy.goldenmenu.vinBlancFragments.BourgogneBlancFragment;
import com.lebengy.goldenmenu.vinBlancFragments.LoireBlancFragment;
import com.lebengy.goldenmenu.vinBlancFragments.PlusAuSudBlancFragment;
import com.lebengy.goldenmenu.vinBlancFragments.VdRhoneBlancFragment;
import com.lebengy.goldenmenu.vinBlancFragments.VinDePaysBlancFragment;

/* loaded from: base/dex/classes2.dex */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private static final int[] TAB_TITLES= new int[]{R.string.tab_text_vinD1, R.string.tab_text_vinD2,R.string.tab_text_vinD3,R.string.tab_text_vinD4,R.string.tab_text_vinD5};
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
            if (position == 1) {
                return BourgogneDemiFragment.newInstance("", "");
            }
            if (position == 2) {
                return BeaujolaisDemiFragment.newInstance("", "");
            }
            if (position == 3) {
                return BordeauxDemiFragment.newInstance("", "");
            }
            if (position == 4) {
                return RhoneDemiFragment.newInstance("", "");
            }
            return com.lebengy.goldenmenu.vinsBlancTab.ui.main.PlaceholderFragment.newInstance(position + 1);
        }
        return LoireDemiFragment.newInstance("", "");
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int position) {
        return this.mContext.getResources().getString(TAB_TITLES[position]);
    }
}