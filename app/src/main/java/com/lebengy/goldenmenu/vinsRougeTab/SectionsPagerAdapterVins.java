package com.lebengy.goldenmenu.vinsRougeTab;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.lebengy.goldenmenu.R;
import com.lebengy.goldenmenu.vinBlancFragments.AlsaceSavoieBlancFragment;
import com.lebengy.goldenmenu.vinBlancFragments.BourgogneBlancFragment;
import com.lebengy.goldenmenu.vinBlancFragments.LoireBlancFragment;
import com.lebengy.goldenmenu.vinBlancFragments.PlusAuSudBlancFragment;
import com.lebengy.goldenmenu.vinBlancFragments.VdRhoneBlancFragment;
import com.lebengy.goldenmenu.vinBlancFragments.VinDePaysBlancFragment;
import com.lebengy.goldenmenu.vinRougeFragments.BeaujolaisRougeFragment;
import com.lebengy.goldenmenu.vinRougeFragments.BordelaisFragment;
import com.lebengy.goldenmenu.vinRougeFragments.BourgogneRougeFragment;
import com.lebengy.goldenmenu.vinRougeFragments.LoireRougeFragment;
import com.lebengy.goldenmenu.vinRougeFragments.PlusAuSudRougeFragment;
import com.lebengy.goldenmenu.vinRougeFragments.VdRhoneRougeFragment;
import com.lebengy.goldenmenu.vinRougeFragments.VinDePaysRougeFragment;
import com.lebengy.goldenmenu.vinsBlancTab.ui.main.PlaceholderFragment;

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
        if (position != 0) {
            if (position == 1) {
                return BourgogneRougeFragment.newInstance("", "");
            }
            if (position == 2) {
                return BeaujolaisRougeFragment.newInstance("", "");
            }
            if (position == 3) {
                return VdRhoneRougeFragment.newInstance("", "");
            }
            if (position == 4) {
                return PlusAuSudRougeFragment.newInstance("", "");
            }
            if (position == 5) {
                return BordelaisFragment.newInstance("", "");
            }
            if (position == 6) {
                return VinDePaysRougeFragment.newInstance("", "");
            }
            return PlaceholderFragment.newInstance(position + 1);
        }
        return LoireRougeFragment.newInstance("", "");
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int position) {
        Log.e("DEBUG", "getPageTitle");
        return this.mContext.getResources().getString(TAB_TITLES[position]);
    }
}