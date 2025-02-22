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

package com.saslebengy.goldenmenu.demiBouteillesFragments;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.saslebengy.goldenmenu.R;
import com.saslebengy.goldenmenu.model.Vin;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: base/dex/classes2.dex */
public class LoireDemiFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private HashMap<Integer, ArrayList<Vin>> hashMap1;
    private HashMap<Integer, ArrayList<Vin>> hashMap2;
    private HashMap<Integer, ArrayList<Vin>> hashMap3;
    private String mParam1;
    private String mParam2;

    public static LoireDemiFragment newInstance(String param1, String param2) {
        LoireDemiFragment loireDemiFragment = new LoireDemiFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, param1);
        bundle.putString(ARG_PARAM2, param2);
        loireDemiFragment.setArguments(bundle);
        return loireDemiFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mParam1 = getArguments().getString(ARG_PARAM1);
            this.mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_loire_demi, container, false);
        this.hashMap1 = new HashMap<>();
        this.hashMap2 = new HashMap<>();
        this.hashMap3 = new HashMap<>();
        fetchVins(inflate);
        return inflate;
    }

    public void fetchVins(final View v) {
        CollectionReference collection = this.db.collection("Vin");
        Integer valueOf = Integer.valueOf((int) 1);
        Query whereEqualTo = collection.whereEqualTo("type", valueOf).whereEqualTo("appellation", valueOf);
        Integer valueOf2 = Integer.valueOf((int) 10);
        whereEqualTo.whereEqualTo("region", valueOf2).whereArrayContains("contenant", "37").addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.demiBouteillesFragments.LoireDemiFragment.1
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                LoireDemiFragment.this.hashMap1 = new HashMap();
                Log.e("DEBUG", "On est la");
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    QueryDocumentSnapshot next = it.next();
                    int intExact = Math.toIntExact(next.getLong("appellationNom").longValue());
                    if (LoireDemiFragment.this.hashMap1.containsKey(Integer.valueOf(intExact))) {
                        ((ArrayList) LoireDemiFragment.this.hashMap1.get(Integer.valueOf(intExact))).add(LoireDemiFragment.this.unserializeVin(next, "37"));
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(LoireDemiFragment.this.unserializeVin(next, "37"));
                        LoireDemiFragment.this.hashMap1.put(Integer.valueOf(intExact), arrayList);
                    }
                }
                LoireDemiFragment.this.dispoVins1(v);
            }
        });
        this.db.collection("Vin").whereEqualTo("type", Integer.valueOf((int) 2)).whereEqualTo("appellation", valueOf).whereEqualTo("region", valueOf2).whereArrayContains("contenant", "37").addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.demiBouteillesFragments.LoireDemiFragment.2
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                LoireDemiFragment.this.hashMap2 = new HashMap();
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    QueryDocumentSnapshot next = it.next();
                    int intExact = Math.toIntExact(next.getLong("appellationNom").longValue());
                    if (LoireDemiFragment.this.hashMap2.containsKey(Integer.valueOf(intExact))) {
                        ((ArrayList) LoireDemiFragment.this.hashMap2.get(Integer.valueOf(intExact))).add(LoireDemiFragment.this.unserializeVin(next, "37"));
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(LoireDemiFragment.this.unserializeVin(next, "37"));
                        LoireDemiFragment.this.hashMap2.put(Integer.valueOf(intExact), arrayList);
                    }
                }
                LoireDemiFragment.this.dispoVins2(v);
            }
        });
        this.db.collection("Vin").whereEqualTo("type", Integer.valueOf((int) 3)).whereEqualTo("appellation", valueOf).whereEqualTo("region", valueOf2).whereArrayContains("contenant", "37").addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.demiBouteillesFragments.LoireDemiFragment.3
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                LoireDemiFragment.this.hashMap3 = new HashMap();
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    QueryDocumentSnapshot next = it.next();
                    int intExact = Math.toIntExact(next.getLong("appellationNom").longValue());
                    if (LoireDemiFragment.this.hashMap3.containsKey(Integer.valueOf(intExact))) {
                        ((ArrayList) LoireDemiFragment.this.hashMap3.get(Integer.valueOf(intExact))).add(LoireDemiFragment.this.unserializeVin(next, "37"));
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(LoireDemiFragment.this.unserializeVin(next, "37"));
                        LoireDemiFragment.this.hashMap3.put(Integer.valueOf(intExact), arrayList);
                    }
                }
                LoireDemiFragment.this.dispoVins3(v);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Vin unserializeVin(QueryDocumentSnapshot doc, String taille) {
        Vin vin = new Vin();
        vin.setDispo(doc.getBoolean("dispo").booleanValue());
        vin.setTypeAppellationValue(Math.toIntExact(doc.getLong("appellation").longValue()));
        vin.setAppellation(doc.getString("appellationLabel"));
        vin.setAppellationValue(Math.toIntExact(doc.getLong("appellationNom").longValue()));
        vin.setRegionValue(Math.toIntExact(doc.getLong("region").longValue()));
        vin.setType(Math.toIntExact(doc.getLong("type").longValue()));
        vin.setPrix(doc.getDouble("prix37").doubleValue());
        vin.setMillesime(Math.toIntExact(doc.getLong("millesime").longValue()));
        vin.setVigneron(doc.getString("vigneron"));
        vin.setNom(doc.getString("nom"));
        return vin;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoVins1(View v) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        TextView textView = (TextView) v.findViewById(R.id.S1label14);
        arrayList.add((TextView) v.findViewById(R.id.S1label1));
        arrayList.add((TextView) v.findViewById(R.id.S1label2));
        arrayList.add((TextView) v.findViewById(R.id.S1label3));
        arrayList.add((TextView) v.findViewById(R.id.S1label4));
        arrayList.add((TextView) v.findViewById(R.id.S1label5));
        arrayList.add((TextView) v.findViewById(R.id.S1label6));
        arrayList.add((TextView) v.findViewById(R.id.S1label7));
        arrayList.add((TextView) v.findViewById(R.id.S1label8));
        arrayList.add((TextView) v.findViewById(R.id.S1label9));
        arrayList.add((TextView) v.findViewById(R.id.S1label10));
        arrayList.add((TextView) v.findViewById(R.id.S1label11));
        arrayList.add((TextView) v.findViewById(R.id.S1label12));
        arrayList.add((TextView) v.findViewById(R.id.S1label13));
        arrayList.add(textView);
        arrayList.add((TextView) v.findViewById(R.id.S1label15));
        ArrayList arrayList4 = arrayList;
        arrayList2.add((TextView) v.findViewById(R.id.S1prix1));
        arrayList2.add((TextView) v.findViewById(R.id.S1prix2));
        arrayList2.add((TextView) v.findViewById(R.id.S1prix3));
        arrayList2.add((TextView) v.findViewById(R.id.S1prix4));
        arrayList2.add((TextView) v.findViewById(R.id.S1prix5));
        arrayList2.add((TextView) v.findViewById(R.id.S1prix6));
        arrayList2.add((TextView) v.findViewById(R.id.S1prix7));
        arrayList2.add((TextView) v.findViewById(R.id.S1prix8));
        arrayList2.add((TextView) v.findViewById(R.id.S1prix9));
        arrayList2.add((TextView) v.findViewById(R.id.S1prix10));
        arrayList2.add((TextView) v.findViewById(R.id.S1prix11));
        arrayList2.add((TextView) v.findViewById(R.id.S1prix12));
        arrayList2.add((TextView) v.findViewById(R.id.S1prix13));
        arrayList2.add((TextView) v.findViewById(R.id.S1prix14));
        arrayList2.add((TextView) v.findViewById(R.id.S1prix15));
        ArrayList arrayList5 = arrayList2;
        arrayList3.add((TextView) v.findViewById(R.id.S1mill1));
        arrayList3.add((TextView) v.findViewById(R.id.S1mill2));
        arrayList3.add((TextView) v.findViewById(R.id.S1mill3));
        arrayList3.add((TextView) v.findViewById(R.id.S1mill4));
        arrayList3.add((TextView) v.findViewById(R.id.S1mill5));
        arrayList3.add((TextView) v.findViewById(R.id.S1mill6));
        arrayList3.add((TextView) v.findViewById(R.id.S1mill7));
        arrayList3.add((TextView) v.findViewById(R.id.S1mill8));
        arrayList3.add((TextView) v.findViewById(R.id.S1mill9));
        arrayList3.add((TextView) v.findViewById(R.id.S1mill10));
        arrayList3.add((TextView) v.findViewById(R.id.S1mill11));
        arrayList3.add((TextView) v.findViewById(R.id.S1mill12));
        arrayList3.add((TextView) v.findViewById(R.id.S1mill13));
        arrayList3.add((TextView) v.findViewById(R.id.S1mill14));
        arrayList3.add((TextView) v.findViewById(R.id.S1mill15));
        Iterator it = arrayList4.iterator();
        while (it.hasNext()) {
            ((TextView) it.next()).setVisibility(View.GONE);
        }
        Iterator it2 = arrayList5.iterator();
        while (it2.hasNext()) {
            ((TextView) it2.next()).setVisibility(View.GONE);
        }
        Iterator it3 = arrayList3.iterator();
        while (it3.hasNext()) {
            ((TextView) it3.next()).setVisibility(View.GONE);
        }
        ArrayList arrayList6 = new ArrayList();
        for (Integer num : this.hashMap1.keySet()) {
            arrayList6.addAll(this.hashMap1.get(Integer.valueOf(num.intValue())));
        }
        Collections.sort(arrayList6);
        int i = 0;
        int i2 = i;
        while (i < arrayList6.size() && i2 < 15 && i < 15) {
            Vin vin = (Vin) arrayList6.get(i);
            ArrayList arrayList7 = arrayList4;
            TextView textView2 = (TextView) arrayList7.get(i2);
            ArrayList arrayList8 = arrayList5;
            TextView textView3 = (TextView) arrayList8.get(i2);
            TextView textView4 = (TextView) arrayList3.get(i2);
            textView2.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            textView4.setVisibility(View.VISIBLE);
            textView2.setText(vin.getNom() + " (" + vin.getVigneron() + ")");
            textView3.setText(vin.getPrixToString() + " €");
            try {
                textView4.setText(vin.getMillesimeToString());
            } catch (Exception unused) {
            }
            if (!vin.isDispo()) {
                crossout(textView2, textView4, textView3);
            } else {
                uncrossout(textView2, textView4, textView3);
            }
            i2 += 1;
            i += 1;
            arrayList4 = arrayList7;
            arrayList5 = arrayList8;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoVins2(View v) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        TextView textView = (TextView) v.findViewById(R.id.S2label14);
        arrayList.add((TextView) v.findViewById(R.id.S2label1));
        arrayList.add((TextView) v.findViewById(R.id.S2label2));
        arrayList.add((TextView) v.findViewById(R.id.S2label3));
        arrayList.add((TextView) v.findViewById(R.id.S2label4));
        arrayList.add((TextView) v.findViewById(R.id.S2label5));
        arrayList.add((TextView) v.findViewById(R.id.S2label6));
        arrayList.add((TextView) v.findViewById(R.id.S2label7));
        arrayList.add((TextView) v.findViewById(R.id.S2label8));
        arrayList.add((TextView) v.findViewById(R.id.S2label9));
        arrayList.add((TextView) v.findViewById(R.id.S2label10));
        arrayList.add((TextView) v.findViewById(R.id.S2label11));
        arrayList.add((TextView) v.findViewById(R.id.S2label12));
        arrayList.add((TextView) v.findViewById(R.id.S2label13));
        arrayList.add(textView);
        arrayList.add((TextView) v.findViewById(R.id.S2label15));
        ArrayList arrayList4 = arrayList;
        arrayList2.add((TextView) v.findViewById(R.id.S2prix2));
        arrayList2.add((TextView) v.findViewById(R.id.S2prix1));
        arrayList2.add((TextView) v.findViewById(R.id.S2prix3));
        arrayList2.add((TextView) v.findViewById(R.id.S2prix4));
        arrayList2.add((TextView) v.findViewById(R.id.S2prix5));
        arrayList2.add((TextView) v.findViewById(R.id.S2prix6));
        arrayList2.add((TextView) v.findViewById(R.id.S2prix7));
        arrayList2.add((TextView) v.findViewById(R.id.S2prix8));
        arrayList2.add((TextView) v.findViewById(R.id.S2prix9));
        arrayList2.add((TextView) v.findViewById(R.id.S2prix10));
        arrayList2.add((TextView) v.findViewById(R.id.S2prix11));
        arrayList2.add((TextView) v.findViewById(R.id.S2prix12));
        arrayList2.add((TextView) v.findViewById(R.id.S2prix13));
        arrayList2.add((TextView) v.findViewById(R.id.S2prix14));
        arrayList2.add((TextView) v.findViewById(R.id.S2prix15));
        ArrayList arrayList5 = arrayList2;
        arrayList3.add((TextView) v.findViewById(R.id.S2mill1));
        arrayList3.add((TextView) v.findViewById(R.id.S2mill2));
        arrayList3.add((TextView) v.findViewById(R.id.S2mill3));
        arrayList3.add((TextView) v.findViewById(R.id.S2mill4));
        arrayList3.add((TextView) v.findViewById(R.id.S2mill5));
        arrayList3.add((TextView) v.findViewById(R.id.S2mill6));
        arrayList3.add((TextView) v.findViewById(R.id.S2mill7));
        arrayList3.add((TextView) v.findViewById(R.id.S2mill8));
        arrayList3.add((TextView) v.findViewById(R.id.S2mill9));
        arrayList3.add((TextView) v.findViewById(R.id.S2mill10));
        arrayList3.add((TextView) v.findViewById(R.id.S2mill11));
        arrayList3.add((TextView) v.findViewById(R.id.S2mill12));
        arrayList3.add((TextView) v.findViewById(R.id.S2mill13));
        arrayList3.add((TextView) v.findViewById(R.id.S2mill14));
        arrayList3.add((TextView) v.findViewById(R.id.S2mill15));
        Iterator it = arrayList4.iterator();
        while (it.hasNext()) {
            ((TextView) it.next()).setVisibility(View.GONE);
        }
        Iterator it2 = arrayList5.iterator();
        while (it2.hasNext()) {
            ((TextView) it2.next()).setVisibility(View.GONE);
        }
        Iterator it3 = arrayList3.iterator();
        while (it3.hasNext()) {
            ((TextView) it3.next()).setVisibility(View.GONE);
        }
        ArrayList arrayList6 = new ArrayList();
        for (Integer num : this.hashMap2.keySet()) {
            arrayList6.addAll(this.hashMap2.get(Integer.valueOf(num.intValue())));
        }
        Collections.sort(arrayList6);
        int i = 0;
        int i2 = i;
        while (i < arrayList6.size() && i2 < 15 && i < 15) {
            Vin vin = (Vin) arrayList6.get(i);
            ArrayList arrayList7 = arrayList4;
            TextView textView2 = (TextView) arrayList7.get(i2);
            ArrayList arrayList8 = arrayList5;
            TextView textView3 = (TextView) arrayList8.get(i2);
            TextView textView4 = (TextView) arrayList3.get(i2);
            textView2.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            textView4.setVisibility(View.VISIBLE);
            textView2.setText(vin.getNom() + " (" + vin.getVigneron() + ")");
            textView3.setText(vin.getPrixToString() + " €");
            try {
                textView4.setText(vin.getMillesimeToString());
            } catch (Exception unused) {
            }
            if (!vin.isDispo()) {
                crossout(textView2, textView4, textView3);
            } else {
                uncrossout(textView2, textView4, textView3);
            }
            i2 += 1;
            i += 1;
            arrayList4 = arrayList7;
            arrayList5 = arrayList8;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoVins3(View v) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        arrayList.add((TextView) v.findViewById(R.id.s3label1));
        arrayList.add((TextView) v.findViewById(R.id.s3label2));
        arrayList.add((TextView) v.findViewById(R.id.s3label3));
        arrayList.add((TextView) v.findViewById(R.id.s3label4));
        arrayList.add((TextView) v.findViewById(R.id.s3label5));
        arrayList.add((TextView) v.findViewById(R.id.s3label6));
        arrayList.add((TextView) v.findViewById(R.id.s3label7));
        arrayList.add((TextView) v.findViewById(R.id.s3label8));
        arrayList.add((TextView) v.findViewById(R.id.s3label9));
        arrayList.add((TextView) v.findViewById(R.id.s3label10));
        arrayList2.add((TextView) v.findViewById(R.id.s3prix1));
        arrayList2.add((TextView) v.findViewById(R.id.s3prix2));
        arrayList2.add((TextView) v.findViewById(R.id.s3prix3));
        arrayList2.add((TextView) v.findViewById(R.id.s3prix4));
        arrayList2.add((TextView) v.findViewById(R.id.s3prix5));
        arrayList2.add((TextView) v.findViewById(R.id.s3prix6));
        arrayList2.add((TextView) v.findViewById(R.id.s3prix7));
        arrayList2.add((TextView) v.findViewById(R.id.s3prix8));
        arrayList2.add((TextView) v.findViewById(R.id.s3prix9));
        arrayList2.add((TextView) v.findViewById(R.id.s3prix10));
        arrayList3.add((TextView) v.findViewById(R.id.s3mill1));
        arrayList3.add((TextView) v.findViewById(R.id.s3mill2));
        arrayList3.add((TextView) v.findViewById(R.id.s3mill3));
        arrayList3.add((TextView) v.findViewById(R.id.s3mill4));
        arrayList3.add((TextView) v.findViewById(R.id.s3mill5));
        arrayList3.add((TextView) v.findViewById(R.id.s3mill6));
        arrayList3.add((TextView) v.findViewById(R.id.s3mill7));
        arrayList3.add((TextView) v.findViewById(R.id.s3mill8));
        arrayList3.add((TextView) v.findViewById(R.id.s3mill9));
        arrayList3.add((TextView) v.findViewById(R.id.s3mill10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((TextView) it.next()).setVisibility(View.GONE);
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            ((TextView) it2.next()).setVisibility(View.GONE);
        }
        Iterator it3 = arrayList3.iterator();
        while (it3.hasNext()) {
            ((TextView) it3.next()).setVisibility(View.GONE);
        }
        ArrayList arrayList4 = new ArrayList();
        for (Integer num : this.hashMap3.keySet()) {
            arrayList4.addAll(this.hashMap3.get(Integer.valueOf(num.intValue())));
        }
        Collections.sort(arrayList4);
        int i = 0;
        int i2 = i;
        while (i < arrayList4.size() && i2 < 10 && i < 10) {
            Vin vin = (Vin) arrayList4.get(i);
            TextView textView = (TextView) arrayList.get(i2);
            TextView textView2 = (TextView) arrayList2.get(i2);
            TextView textView3 = (TextView) arrayList3.get(i2);
            textView.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            textView.setText(vin.getNom() + " (" + vin.getVigneron() + ")");
            textView2.setText(vin.getPrixToString() + " €");
            try {
                textView3.setText(vin.getMillesimeToString());
            } catch (Exception unused) {
            }
            if (!vin.isDispo()) {
                crossout(textView, textView3, textView2);
            } else {
                uncrossout(textView, textView3, textView2);
            }
            i2 += 1;
            i += 1;
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