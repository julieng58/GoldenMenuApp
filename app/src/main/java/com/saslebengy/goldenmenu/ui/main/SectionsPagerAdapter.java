/*
 *
 *  * Copyright (c) 2025 Florian Nadin
 *  * All rights reserved.
 *  * Last modified 23/08/2022 09:37
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

package com.saslebengy.goldenmenu.ui.main;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.saslebengy.goldenmenu.R;
import com.saslebengy.goldenmenu.menuFragment.CarteFragment;
import com.saslebengy.goldenmenu.menuFragment.FournisseurFragment;
import com.saslebengy.goldenmenu.menuFragment.Menu25Fragment;
import com.saslebengy.goldenmenu.menuFragment.Menu32Fragment;
import com.saslebengy.goldenmenu.menuFragment.MenuVeganFragment;
import com.saslebengy.goldenmenu.menuFragment.NosFormulesFragment;

/* loaded from: base/dex/classes2.dex */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private static final int[] TAB_TITLES= new int[]{R.string.tab_text_5,R.string.tab_text_2,R.string.tab_text_3,R.string.tab_text_vegan,R.string.tab_text_1,R.string.tab_text_4};
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
                return Menu25Fragment.newInstance("", "");
            }
            if (position == 2) {
                return Menu32Fragment.newInstance("", "");
            }
            if (position == 3) {
                return MenuVeganFragment.newInstance("", "");
            }
            if (position == 5) {
                return CarteFragment.newInstance("", "");
            }
            if (position == 4) {
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