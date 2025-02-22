/*
 *
 *  * Copyright (c) 2025 Florian Nadin
 *  * All rights reserved.
 *  * Last modified 11/02/2024 21:20
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

package com.saslebengy.goldenmenu.barTab.ui.main;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.saslebengy.goldenmenu.R;
import com.saslebengy.goldenmenu.barFragments.AperitifBarFragment;
import com.saslebengy.goldenmenu.barFragments.DigestifBarFragment;
import com.saslebengy.goldenmenu.barFragments.EtAussiFragment;
import com.saslebengy.goldenmenu.barFragments.WhiskyBarFragment;

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
            return com.saslebengy.goldenmenu.vinsBlancTab.ui.main.PlaceholderFragment.newInstance(position + 1);
        }
        return AperitifBarFragment.newInstance("", "");
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int position) {
        return this.mContext.getResources().getString(TAB_TITLES[position]);
    }
}