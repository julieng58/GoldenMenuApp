package com.lebengy.goldenmenu.vinsRougeTab;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.lebengy.goldenmenu.R;
import com.lebengy.goldenmenu.vinRougeFragments.BeaujolaisRougeFragment;
import com.lebengy.goldenmenu.vinRougeFragments.BordelaisFragment;
import com.lebengy.goldenmenu.vinRougeFragments.BourgogneRougeFragment;
import com.lebengy.goldenmenu.vinRougeFragments.LoireRougeFragment;
import com.lebengy.goldenmenu.vinRougeFragments.PlusAuSudRougeFragment;
import com.lebengy.goldenmenu.vinRougeFragments.VdRhoneRougeFragment;
import com.lebengy.goldenmenu.vinRougeFragments.VinDePaysRougeFragment;

/* loaded from: base/dex/classes2.dex */
public class SectionsPagerAdapterVins extends FragmentPagerAdapter {
    private static final int[] TAB_TITLES= new int[]{R.string.tab_text_vinR1,R.string.tab_text_vinR2,R.string.tab_text_vinR3,R.string.tab_text_vinR4,R.string.tab_text_vinR5,R.string.tab_text_vinR6,R.string.tab_text_vinR7};
    private final Context mContext;

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return 7;
    }

    public SectionsPagerAdapterVins(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return LoireRougeFragment.newInstance("", "");
            case 1:
                return BourgogneRougeFragment.newInstance("", "");
            case 2:
                return BeaujolaisRougeFragment.newInstance("", "");
            case 3:
                return VdRhoneRougeFragment.newInstance("", "");
            case 4:
                return PlusAuSudRougeFragment.newInstance("", "");
            case 5:
                return BordelaisFragment.newInstance("", "");
            case 6:
                return VinDePaysRougeFragment.newInstance("", "");
            default:
                return PlaceholderFragmentVins.newInstance(position + 1);
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int position) {
        Log.e("DEBUG", "getPageTitle");
        return this.mContext.getResources().getString(TAB_TITLES[position]);
    }
}