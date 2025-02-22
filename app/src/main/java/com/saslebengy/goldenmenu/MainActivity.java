/*
 *
 *  * Copyright (c) 2025 Florian Nadin
 *  * All rights reserved.
 *  * Last modified 15/05/2023 20:24
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

package com.saslebengy.goldenmenu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.saslebengy.goldenmenu.R;
import com.saslebengy.goldenmenu.barTab.BarActivity;
import com.saslebengy.goldenmenu.soloPageVin.BiereActivity;
import com.tomer.fadingtextview.FadingTextView;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: base/dex/classes2.dex */
public class MainActivity extends AppCompatActivity {
    private FadingTextView fadingTextView;
    private ArrayList<String> listInfoList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String[] listInit = {"Bienvenue au restaurant"};

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FadingTextView fadingTextView = (FadingTextView) findViewById(R.id.infoTemp);
        this.fadingTextView = fadingTextView;
        fadingTextView.setTexts(this.listInit);
        getWindow().addFlags(128);
        getInfo();
    }

    private void getInfo() {
        this.db.collection("MessageTemp").addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.MainActivity.1
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                } else if (value.size() <= 0) {
                } else {
                    MainActivity.this.listInfoList = new ArrayList();
                    Iterator<QueryDocumentSnapshot> it = value.iterator();
                    while (it.hasNext()) {
                        MainActivity.this.listInfoList.add(it.next().getString("message"));
                    }
                    MainActivity.this.dispoInfo();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoInfo() {
        this.fadingTextView.setTexts((String[]) this.listInfoList.toArray(new String[0]));
    }

    public void clickOnVins(View view) {
        startActivity(new Intent(this, AccueilVins.class));
    }

    public void clickOnAperos(View view) {
        startActivity(new Intent(this, BarActivity.class));
    }

    public void clickOnBieres(View view) {
        startActivity(new Intent(this, BiereActivity.class));
    }

    public void clickOnMenus(View view) {
        startActivity(new Intent(this, LesMenus.class));
    }
}