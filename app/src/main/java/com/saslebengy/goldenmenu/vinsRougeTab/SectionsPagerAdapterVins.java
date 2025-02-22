/*
 *
 *  * Copyright (c) 2025 Florian Nadin
 *  * All rights reserved.
 *  * Last modified 22/08/2022 17:07
 *  *
 *  * Ce fichier fait partie du logiciel cédé conformément au contrat signé entre les parties.
 *  *
 *  * Toute utilisation, modification ou distribution du code source est soumise aux conditions de la cession :
 *  * - Le logiciel est fourni en l'état, sans garantie d'aucune sorte.
 *  * - L'utilisation est restreinte à l'usage prévu par le cessionnaire.
 *  * - Toute reproduction ou commercialisation du code sans autorisation expresse est interdite.
 *  * - Le cessionnaire est tenu de préserver la confidentialité du code source.
 *  *
 *  * Voir le fichier README.md pour plus de détails sur les conditions d'utilisation.
 *
 */

package com.saslebengy.goldenmenu.vinsRougeTab;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.saslebengy.goldenmenu.R;
import com.saslebengy.goldenmenu.vinRougeFragments.BeaujolaisRougeFragment;
import com.saslebengy.goldenmenu.vinRougeFragments.BordelaisFragment;
import com.saslebengy.goldenmenu.vinRougeFragments.BourgogneRougeFragment;
import com.saslebengy.goldenmenu.vinRougeFragments.LoireRougeFragment;
import com.saslebengy.goldenmenu.vinRougeFragments.PlusAuSudRougeFragment;
import com.saslebengy.goldenmenu.vinRougeFragments.VdRhoneRougeFragment;
import com.saslebengy.goldenmenu.vinRougeFragments.VinDePaysRougeFragment;
import com.saslebengy.goldenmenu.vinsBlancTab.ui.main.PlaceholderFragment;
import com.saslebengy.goldenmenu.R;


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