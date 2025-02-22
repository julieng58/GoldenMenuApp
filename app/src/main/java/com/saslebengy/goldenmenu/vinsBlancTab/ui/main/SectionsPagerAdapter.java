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

package com.saslebengy.goldenmenu.vinsBlancTab.ui.main;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.saslebengy.goldenmenu.R;
import com.saslebengy.goldenmenu.vinBlancFragments.AlsaceSavoieBlancFragment;
import com.saslebengy.goldenmenu.vinBlancFragments.BourgogneBlancFragment;
import com.saslebengy.goldenmenu.vinBlancFragments.LoireBlancFragment;
import com.saslebengy.goldenmenu.vinBlancFragments.PlusAuSudBlancFragment;
import com.saslebengy.goldenmenu.vinBlancFragments.VdRhoneBlancFragment;
import com.saslebengy.goldenmenu.vinBlancFragments.VinDePaysBlancFragment;

/* loaded from: base/dex/classes2.dex */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private static final int[] TAB_TITLES= new int[]{R.string.tab_text_vinB1,R.string.tab_text_vinB2,R.string.tab_text_vinB3,R.string.tab_text_vinB4,R.string.tab_text_vinB5,R.string.tab_text_vinB6};
    private final Context mContext;

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return 6;
    }

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int position) {
        if (position != 0) {
            if (position == 1) {
                return BourgogneBlancFragment.newInstance("", "");
            }
            if (position == 2) {
                return VdRhoneBlancFragment.newInstance("", "");
            }
            if (position == 3) {
                return AlsaceSavoieBlancFragment.newInstance("", "");
            }
            if (position == 4) {
                return PlusAuSudBlancFragment.newInstance("", "");
            }
            if (position == 5) {
                return VinDePaysBlancFragment.newInstance("", "");
            }
            return PlaceholderFragment.newInstance(position + 1);
        }
        return LoireBlancFragment.newInstance("", "");
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int position) {
        return this.mContext.getResources().getString(TAB_TITLES[position]);
    }
}