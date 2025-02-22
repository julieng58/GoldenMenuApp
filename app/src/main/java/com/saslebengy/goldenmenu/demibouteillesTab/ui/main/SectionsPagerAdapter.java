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

package com.saslebengy.goldenmenu.demibouteillesTab.ui.main;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.saslebengy.goldenmenu.R;
import com.saslebengy.goldenmenu.demiBouteillesFragments.BeaujolaisDemiFragment;
import com.saslebengy.goldenmenu.demiBouteillesFragments.BordeauxDemiFragment;
import com.saslebengy.goldenmenu.demiBouteillesFragments.BourgogneDemiFragment;
import com.saslebengy.goldenmenu.demiBouteillesFragments.LoireDemiFragment;
import com.saslebengy.goldenmenu.demiBouteillesFragments.RhoneDemiFragment;

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
            return com.saslebengy.goldenmenu.vinsBlancTab.ui.main.PlaceholderFragment.newInstance(position + 1);
        }
        return LoireDemiFragment.newInstance("", "");
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int position) {
        return this.mContext.getResources().getString(TAB_TITLES[position]);
    }
}