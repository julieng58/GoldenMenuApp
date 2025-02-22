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

package com.saslebengy.goldenmenu.soloPageVin;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.saslebengy.goldenmenu.R;
import com.saslebengy.goldenmenu.model.Champagne;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: base/dex/classes2.dex */
public class ChampagneActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private HashMap<Integer, ArrayList<Champagne>> hashMapVins;
    private List<Champagne> listPays;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champagne);
        getWindow().addFlags(128);
        this.hashMapVins = new HashMap<>();
        this.listPays = new ArrayList();
        fetchVins();
    }

    public void fetchVins() {
        Query whereEqualTo = this.db.collection("Vin").whereEqualTo("type", 4);
        Integer valueOf = 6;
        whereEqualTo.whereEqualTo("region", valueOf).addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.soloPageVin.ChampagneActivity.1
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                ChampagneActivity.this.hashMapVins = new HashMap();
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    QueryDocumentSnapshot next = it.next();
                    int intExact = Math.toIntExact(next.getLong("appellationNom").longValue());
                    if (ChampagneActivity.this.hashMapVins.containsKey(Integer.valueOf(intExact))) {
                        ((ArrayList) ChampagneActivity.this.hashMapVins.get(Integer.valueOf(intExact))).add(ChampagneActivity.this.unserializeChampagne(next));
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(ChampagneActivity.this.unserializeChampagne(next));
                        ChampagneActivity.this.hashMapVins.put(Integer.valueOf(intExact), arrayList);
                    }
                }
                ChampagneActivity.this.dispoVins1();
            }
        });
        this.db.collection("Vin").whereEqualTo("type", valueOf).addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.soloPageVin.ChampagneActivity.2
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                ChampagneActivity.this.listPays = new ArrayList();
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    ChampagneActivity.this.listPays.add(ChampagneActivity.this.unserializeChampagne(it.next()));
                }
                ChampagneActivity.this.dispoVins2();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Champagne unserializeChampagne(QueryDocumentSnapshot doc) {
        Champagne champagne = new Champagne();
        champagne.setDispo(doc.getBoolean("dispo").booleanValue());
        champagne.setTypeAppellationValue(Math.toIntExact(doc.getLong("appellation").longValue()));
        champagne.setAppellation(doc.getString("appellationLabel"));
        champagne.setAppellationValue(Math.toIntExact(doc.getLong("appellationNom").longValue()));
        champagne.setRegionValue(Math.toIntExact(doc.getLong("region").longValue()));
        champagne.setType(Math.toIntExact(doc.getLong("type").longValue()));
        champagne.setPrix75(doc.getDouble("prix75").doubleValue());
        champagne.setPrix37(doc.getDouble("prix37").doubleValue());
        champagne.setMillesime(Math.toIntExact(doc.getLong("millesime").longValue()));
        champagne.setVigneron(doc.getString("vigneron"));
        champagne.setNom(doc.getString("nom"));
        return champagne;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoVins1() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = arrayList2;
        TextView textView = (TextView) findViewById(R.id.S1label14);
        arrayList.add((TextView) findViewById(R.id.S1label1));
        arrayList.add((TextView) findViewById(R.id.S1label2));
        arrayList.add((TextView) findViewById(R.id.S1label3));
        arrayList.add((TextView) findViewById(R.id.S1label4));
        arrayList.add((TextView) findViewById(R.id.S1label5));
        arrayList.add((TextView) findViewById(R.id.S1label6));
        arrayList.add((TextView) findViewById(R.id.S1label7));
        arrayList.add((TextView) findViewById(R.id.S1label8));
        arrayList.add((TextView) findViewById(R.id.S1label9));
        arrayList.add((TextView) findViewById(R.id.S1label10));
        arrayList.add((TextView) findViewById(R.id.S1label11));
        arrayList.add((TextView) findViewById(R.id.S1label12));
        arrayList.add((TextView) findViewById(R.id.S1label13));
        arrayList.add(textView);
        arrayList.add((TextView) findViewById(R.id.S1label15));
        ArrayList arrayList5 = arrayList;
        arrayList4.add((TextView) findViewById(R.id.S1prix1));
        arrayList4.add((TextView) findViewById(R.id.S1prix2));
        arrayList4.add((TextView) findViewById(R.id.S1prix3));
        arrayList4.add((TextView) findViewById(R.id.S1prix4));
        arrayList4.add((TextView) findViewById(R.id.S1prix5));
        arrayList4.add((TextView) findViewById(R.id.S1prix6));
        arrayList4.add((TextView) findViewById(R.id.S1prix7));
        arrayList4.add((TextView) findViewById(R.id.S1prix8));
        arrayList4.add((TextView) findViewById(R.id.S1prix9));
        arrayList4.add((TextView) findViewById(R.id.S1prix10));
        arrayList4.add((TextView) findViewById(R.id.S1prix11));
        arrayList4.add((TextView) findViewById(R.id.S1prix12));
        arrayList4.add((TextView) findViewById(R.id.S1prix13));
        arrayList4.add((TextView) findViewById(R.id.S1prix14));
        arrayList4.add((TextView) findViewById(R.id.S1prix15));
        arrayList3.add((TextView) findViewById(R.id.S1mill1));
        arrayList3.add((TextView) findViewById(R.id.S1mill2));
        arrayList3.add((TextView) findViewById(R.id.S1mill3));
        arrayList3.add((TextView) findViewById(R.id.S1mill4));
        arrayList3.add((TextView) findViewById(R.id.S1mill5));
        arrayList3.add((TextView) findViewById(R.id.S1mill6));
        arrayList3.add((TextView) findViewById(R.id.S1mill7));
        arrayList3.add((TextView) findViewById(R.id.S1mill8));
        arrayList3.add((TextView) findViewById(R.id.S1mill9));
        arrayList3.add((TextView) findViewById(R.id.S1mill10));
        arrayList3.add((TextView) findViewById(R.id.S1mill11));
        arrayList3.add((TextView) findViewById(R.id.S1mill12));
        arrayList3.add((TextView) findViewById(R.id.S1mill13));
        arrayList3.add((TextView) findViewById(R.id.S1mill14));
        arrayList3.add((TextView) findViewById(R.id.S1mill15));
        Iterator it = arrayList5.iterator();
        while (it.hasNext()) {
            ((TextView) it.next()).setVisibility(View.GONE);
        }
        Iterator it2 = arrayList4.iterator();
        while (it2.hasNext()) {
            ((TextView) it2.next()).setVisibility(View.GONE);
        }
        Iterator it3 = arrayList3.iterator();
        while (it3.hasNext()) {
            ((TextView) it3.next()).setVisibility(View.GONE);
        }
        Collections.sort(this.listPays);
        ArrayList arrayList6 = new ArrayList();
        for (Integer num : this.hashMapVins.keySet()) {
            arrayList6.addAll(this.hashMapVins.get(Integer.valueOf(num.intValue())));
        }
        Collections.sort(arrayList6);
        int i = 0;
        int i2 = i;
        while (i < arrayList6.size() && i2 < 15 && i < 15) {
            Champagne champagne = (Champagne) arrayList6.get(i);
            ArrayList arrayList7 = arrayList5;
            TextView textView2 = (TextView) arrayList7.get(i2);
            ArrayList arrayList8 = arrayList4;
            TextView textView3 = (TextView) arrayList8.get(i2);
            TextView textView4 = (TextView) arrayList3.get(i2);
            textView2.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            if (champagne.getPrix37() != 0) { //Modifier de 0.0d
                textView4.setVisibility(View.VISIBLE);
                textView4.setText(champagne.getPrix37ToString() + " €");
            } else {
                textView4.setVisibility(View.GONE);
            }
            textView2.setText(champagne.getNom());
            textView3.setText(champagne.getPrix75ToString() + " €");
            if (!champagne.isDispo()) {
                crossout(textView2, textView4, textView3);
            } else {
                uncrossout(textView2, textView4, textView3);
            }
            i2 += 1;
            i += 1;
            arrayList5 = arrayList7;
            arrayList4 = arrayList8;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoVins2() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = arrayList2;
        TextView textView = (TextView) findViewById(R.id.S2label14);
        arrayList.add((TextView) findViewById(R.id.S2label1));
        arrayList.add((TextView) findViewById(R.id.S2label2));
        arrayList.add((TextView) findViewById(R.id.S2label3));
        arrayList.add((TextView) findViewById(R.id.S2label4));
        arrayList.add((TextView) findViewById(R.id.S2label5));
        arrayList.add((TextView) findViewById(R.id.S2label6));
        arrayList.add((TextView) findViewById(R.id.S2label7));
        arrayList.add((TextView) findViewById(R.id.S2label8));
        arrayList.add((TextView) findViewById(R.id.S2label9));
        arrayList.add((TextView) findViewById(R.id.S2label10));
        arrayList.add((TextView) findViewById(R.id.S2label11));
        arrayList.add((TextView) findViewById(R.id.S2label12));
        arrayList.add((TextView) findViewById(R.id.S2label13));
        arrayList.add(textView);
        arrayList.add((TextView) findViewById(R.id.S2label15));
        ArrayList arrayList5 = arrayList;
        arrayList4.add((TextView) findViewById(R.id.S2prix2));
        arrayList4.add((TextView) findViewById(R.id.S2prix1));
        arrayList4.add((TextView) findViewById(R.id.S2prix3));
        arrayList4.add((TextView) findViewById(R.id.S2prix4));
        arrayList4.add((TextView) findViewById(R.id.S2prix5));
        arrayList4.add((TextView) findViewById(R.id.S2prix6));
        arrayList4.add((TextView) findViewById(R.id.S2prix7));
        arrayList4.add((TextView) findViewById(R.id.S2prix8));
        arrayList4.add((TextView) findViewById(R.id.S2prix9));
        arrayList4.add((TextView) findViewById(R.id.S2prix10));
        arrayList4.add((TextView) findViewById(R.id.S2prix11));
        arrayList4.add((TextView) findViewById(R.id.S2prix12));
        arrayList4.add((TextView) findViewById(R.id.S2prix13));
        arrayList4.add((TextView) findViewById(R.id.S2prix14));
        arrayList4.add((TextView) findViewById(R.id.S2prix15));
        arrayList3.add((TextView) findViewById(R.id.S2mill1));
        arrayList3.add((TextView) findViewById(R.id.S2mill2));
        arrayList3.add((TextView) findViewById(R.id.S2mill3));
        arrayList3.add((TextView) findViewById(R.id.S2mill4));
        arrayList3.add((TextView) findViewById(R.id.S2mill5));
        arrayList3.add((TextView) findViewById(R.id.S2mill6));
        arrayList3.add((TextView) findViewById(R.id.S2mill7));
        arrayList3.add((TextView) findViewById(R.id.S2mill8));
        arrayList3.add((TextView) findViewById(R.id.S2mill9));
        arrayList3.add((TextView) findViewById(R.id.S2mill10));
        arrayList3.add((TextView) findViewById(R.id.S2mill11));
        arrayList3.add((TextView) findViewById(R.id.S2mill12));
        arrayList3.add((TextView) findViewById(R.id.S2mill13));
        arrayList3.add((TextView) findViewById(R.id.S2mill14));
        arrayList3.add((TextView) findViewById(R.id.S2mill15));
        Iterator it = arrayList5.iterator();
        while (it.hasNext()) {
            ((TextView) it.next()).setVisibility(View.GONE);
        }
        Iterator it2 = arrayList4.iterator();
        while (it2.hasNext()) {
            ((TextView) it2.next()).setVisibility(View.GONE);
        }
        Iterator it3 = arrayList3.iterator();
        while (it3.hasNext()) {
            ((TextView) it3.next()).setVisibility(View.GONE);
        }
        Collections.sort(this.listPays);
        int i = 0;
        while (i < this.listPays.size() && i < 15) {
            Champagne champagne = this.listPays.get(i);
            ArrayList arrayList6 = arrayList5;
            TextView textView2 = (TextView) arrayList6.get(i);
            ArrayList arrayList7 = arrayList4;
            TextView textView3 = (TextView) arrayList7.get(i);
            TextView textView4 = (TextView) arrayList3.get(i);
            textView2.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            if (champagne.getPrix37() != 0.0d) {
                textView4.setVisibility(View.VISIBLE);
                textView4.setText(champagne.getPrix37ToString() + " €");
            } else {
                textView4.setVisibility(View.GONE);
            }
            textView2.setText(champagne.getNom());
            textView3.setText(champagne.getPrix75ToString() + " €");
            if (!champagne.isDispo()) {
                crossout(textView2, textView4, textView3);
            } else {
                uncrossout(textView2, textView4, textView3);
            }
            i += 1;
            arrayList5 = arrayList6;
            arrayList4 = arrayList7;
        }
    }

    private void crossout(TextView nom, TextView prix, TextView mill) {
        nom.setPaintFlags(nom.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        prix.setPaintFlags(prix.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        mill.setPaintFlags(mill.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void uncrossout(TextView nom, TextView prix, TextView mill) {
        nom.setPaintFlags(nom.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        prix.setPaintFlags(prix.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        mill.setPaintFlags(mill.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
    }
}