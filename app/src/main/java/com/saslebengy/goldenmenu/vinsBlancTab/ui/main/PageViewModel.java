/*
 *
 *  * Copyright (c) 2025 Florian Nadin
 *  * All rights reserved.
 *  * Last modified 22/08/2022 17:00
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

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

/* loaded from: base/dex/classes2.dex */
public class PageViewModel extends ViewModel {
    private MutableLiveData<Integer> mIndex;
    private LiveData<String> mText;

    public PageViewModel() {
        MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
        this.mIndex = mutableLiveData;
        this.mText = Transformations.map(mutableLiveData, new Function<Integer, String>() { // from class: com.lebengy.goldenmenu.vinsBlancTab.ui.main.PageViewModel.1
            @Override // androidx.arch.core.util.Function
            public String apply(Integer input) {
                return "Hello world from section: " + input;
            }
        });
    }

    public void setIndex(int index) {
        this.mIndex.setValue(Integer.valueOf(index));
    }

    public LiveData<String> getText() {
        return this.mText;
    }
}