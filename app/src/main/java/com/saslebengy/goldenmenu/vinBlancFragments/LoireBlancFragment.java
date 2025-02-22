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

package com.saslebengy.goldenmenu.vinBlancFragments;

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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.saslebengy.goldenmenu.R;
import com.saslebengy.goldenmenu.model.Vin;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: base/dex/classes2.dex */
public class LoireBlancFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private HashMap<Integer, ArrayList<Vin>> hashMapLoire;
    private List<Vin> listPF;
    private List<Vin> listSanc;
    private String mParam1;
    private String mParam2;

    public static LoireBlancFragment newInstance(String param1, String param2) {
        LoireBlancFragment loireBlancFragment = new LoireBlancFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, param1);
        bundle.putString(ARG_PARAM2, param2);
        loireBlancFragment.setArguments(bundle);
        return loireBlancFragment;
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
        View inflate = inflater.inflate(R.layout.fragment_loire_blanc, container, false);
        this.listSanc = new ArrayList();
        this.listPF = new ArrayList();
        this.hashMapLoire = new HashMap<>();
        fetchVins(inflate);
        return inflate;
    }

    public void fetchVins(final View v) {
        CollectionReference collection = this.db.collection("Vin");
        Integer valueOf = Integer.valueOf((int) 1);
        collection.whereEqualTo("type", valueOf).whereEqualTo("appellation", valueOf).whereEqualTo("region", 10).whereArrayContains("contenant", "75").addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.vinBlancFragments.LoireBlancFragment.1
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                LoireBlancFragment.this.listSanc = new ArrayList();
                LoireBlancFragment.this.listPF = new ArrayList();
                LoireBlancFragment.this.hashMapLoire = new HashMap();
                Log.e("DEBUG", "On est la");
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    QueryDocumentSnapshot next = it.next();
                    if (next.getLong("appellationNom").longValue() == 45) {
                        LoireBlancFragment.this.listSanc.add(LoireBlancFragment.this.unserializeVin(next, "75"));
                    } else if (next.getLong("appellationNom").longValue() == 36) {
                        LoireBlancFragment.this.listPF.add(LoireBlancFragment.this.unserializeVin(next, "75"));
                    } else {
                        int intExact = Math.toIntExact(next.getLong("appellationNom").longValue());
                        if (LoireBlancFragment.this.hashMapLoire.containsKey(Integer.valueOf(intExact))) {
                            ((ArrayList) LoireBlancFragment.this.hashMapLoire.get(Integer.valueOf(intExact))).add(LoireBlancFragment.this.unserializeVin(next, "75"));
                        } else {
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(LoireBlancFragment.this.unserializeVin(next, "75"));
                            LoireBlancFragment.this.hashMapLoire.put(Integer.valueOf(intExact), arrayList);
                        }
                    }
                }
                LoireBlancFragment.this.dispoVins(v);
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
        vin.setPrix(doc.getDouble("prix75").doubleValue());
        vin.setMillesime(Math.toIntExact(doc.getLong("millesime").longValue()));
        vin.setVigneron(doc.getString("vigneron"));
        vin.setNom(doc.getString("nom"));
        return vin;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoVins(View v) {
        int i;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        ArrayList arrayList9 = new ArrayList();
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
        arrayList.add((TextView) v.findViewById(R.id.S1label14));
        arrayList.add((TextView) v.findViewById(R.id.S1label15));
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
        arrayList4.add((TextView) v.findViewById(R.id.S2label1));
        arrayList4.add((TextView) v.findViewById(R.id.S2label2));
        arrayList4.add((TextView) v.findViewById(R.id.S2label3));
        arrayList4.add((TextView) v.findViewById(R.id.S2label4));
        arrayList4.add((TextView) v.findViewById(R.id.S2label5));
        arrayList4.add((TextView) v.findViewById(R.id.S2label6));
        arrayList4.add((TextView) v.findViewById(R.id.S2label7));
        arrayList4.add((TextView) v.findViewById(R.id.S2label8));
        arrayList4.add((TextView) v.findViewById(R.id.S2label9));
        arrayList4.add((TextView) v.findViewById(R.id.S2label10));
        arrayList4.add((TextView) v.findViewById(R.id.S2label11));
        arrayList4.add((TextView) v.findViewById(R.id.S2label12));
        arrayList4.add((TextView) v.findViewById(R.id.S2label13));
        arrayList4.add((TextView) v.findViewById(R.id.S2label14));
        arrayList4.add((TextView) v.findViewById(R.id.S2label15));
        ArrayList arrayList12 = arrayList4;
        arrayList5.add((TextView) v.findViewById(R.id.S2prix2));
        arrayList5.add((TextView) v.findViewById(R.id.S2prix1));
        arrayList5.add((TextView) v.findViewById(R.id.S2prix3));
        arrayList5.add((TextView) v.findViewById(R.id.S2prix4));
        arrayList5.add((TextView) v.findViewById(R.id.S2prix5));
        arrayList5.add((TextView) v.findViewById(R.id.S2prix6));
        arrayList5.add((TextView) v.findViewById(R.id.S2prix7));
        arrayList5.add((TextView) v.findViewById(R.id.S2prix8));
        arrayList5.add((TextView) v.findViewById(R.id.S2prix9));
        arrayList5.add((TextView) v.findViewById(R.id.S2prix10));
        arrayList5.add((TextView) v.findViewById(R.id.S2prix11));
        arrayList5.add((TextView) v.findViewById(R.id.S2prix12));
        arrayList5.add((TextView) v.findViewById(R.id.S2prix13));
        arrayList5.add((TextView) v.findViewById(R.id.S2prix14));
        arrayList5.add((TextView) v.findViewById(R.id.S2prix15));
        ArrayList arrayList13 = arrayList5;
        arrayList6.add((TextView) v.findViewById(R.id.S2mill1));
        arrayList6.add((TextView) v.findViewById(R.id.S2mill2));
        arrayList6.add((TextView) v.findViewById(R.id.S2mill3));
        arrayList6.add((TextView) v.findViewById(R.id.S2mill4));
        arrayList6.add((TextView) v.findViewById(R.id.S2mill5));
        arrayList6.add((TextView) v.findViewById(R.id.S2mill6));
        arrayList6.add((TextView) v.findViewById(R.id.S2mill7));
        arrayList6.add((TextView) v.findViewById(R.id.S2mill8));
        arrayList6.add((TextView) v.findViewById(R.id.S2mill9));
        arrayList6.add((TextView) v.findViewById(R.id.S2mill10));
        arrayList6.add((TextView) v.findViewById(R.id.S2mill11));
        arrayList6.add((TextView) v.findViewById(R.id.S2mill12));
        arrayList6.add((TextView) v.findViewById(R.id.S2mill13));
        arrayList6.add((TextView) v.findViewById(R.id.S2mill14));
        arrayList6.add((TextView) v.findViewById(R.id.S2mill15));
        ArrayList arrayList14 = arrayList6;
        arrayList7.add((TextView) v.findViewById(R.id.s3label1));
        arrayList7.add((TextView) v.findViewById(R.id.s3label2));
        arrayList7.add((TextView) v.findViewById(R.id.s3label3));
        arrayList7.add((TextView) v.findViewById(R.id.s3label4));
        arrayList7.add((TextView) v.findViewById(R.id.s3label5));
        arrayList7.add((TextView) v.findViewById(R.id.s3label6));
        arrayList7.add((TextView) v.findViewById(R.id.s3label7));
        arrayList7.add((TextView) v.findViewById(R.id.s3label8));
        arrayList7.add((TextView) v.findViewById(R.id.s3label9));
        arrayList7.add((TextView) v.findViewById(R.id.s3label10));
        arrayList8.add((TextView) v.findViewById(R.id.s3prix1));
        arrayList8.add((TextView) v.findViewById(R.id.s3prix2));
        arrayList8.add((TextView) v.findViewById(R.id.s3prix3));
        arrayList8.add((TextView) v.findViewById(R.id.s3prix4));
        arrayList8.add((TextView) v.findViewById(R.id.s3prix5));
        arrayList8.add((TextView) v.findViewById(R.id.s3prix6));
        arrayList8.add((TextView) v.findViewById(R.id.s3prix7));
        arrayList8.add((TextView) v.findViewById(R.id.s3prix8));
        arrayList8.add((TextView) v.findViewById(R.id.s3prix9));
        arrayList8.add((TextView) v.findViewById(R.id.s3prix10));
        arrayList9.add((TextView) v.findViewById(R.id.s3mill1));
        arrayList9.add((TextView) v.findViewById(R.id.s3mill2));
        arrayList9.add((TextView) v.findViewById(R.id.s3mill3));
        arrayList9.add((TextView) v.findViewById(R.id.s3mill4));
        arrayList9.add((TextView) v.findViewById(R.id.s3mill5));
        arrayList9.add((TextView) v.findViewById(R.id.s3mill6));
        arrayList9.add((TextView) v.findViewById(R.id.s3mill7));
        arrayList9.add((TextView) v.findViewById(R.id.s3mill8));
        arrayList9.add((TextView) v.findViewById(R.id.s3mill9));
        arrayList9.add((TextView) v.findViewById(R.id.s3mill10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((TextView) it.next()).setVisibility(View.GONE);;
        }
        Iterator it2 = arrayList12.iterator();
        while (it2.hasNext()) {
            ((TextView) it2.next()).setVisibility(View.GONE);;
        }
        Iterator it3 = arrayList7.iterator();
        while (it3.hasNext()) {
            ((TextView) it3.next()).setVisibility(View.GONE);;
        }
        Iterator it4 = arrayList2.iterator();
        while (it4.hasNext()) {
            ((TextView) it4.next()).setVisibility(View.GONE);;
        }
        Iterator it5 = arrayList13.iterator();
        while (it5.hasNext()) {
            ((TextView) it5.next()).setVisibility(View.GONE);;
        }
        Iterator it6 = arrayList8.iterator();
        while (it6.hasNext()) {
            ((TextView) it6.next()).setVisibility(View.GONE);;
        }
        Iterator it7 = arrayList3.iterator();
        while (it7.hasNext()) {
            ((TextView) it7.next()).setVisibility(View.GONE);;
        }
        Iterator it8 = arrayList14.iterator();
        while (it8.hasNext()) {
            ((TextView) it8.next()).setVisibility(View.GONE);;
        }
        Iterator it9 = arrayList9.iterator();
        while (it9.hasNext()) {
            ((TextView) it9.next()).setVisibility(View.GONE);;
        }
        Collections.sort(this.listSanc);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int size = this.listSanc.size();
            if (i3 >= size || i3 >= 15) {
                break;
            }
            Vin vin = this.listSanc.get(i3);
            ArrayList arrayList15 = arrayList12;
            TextView textView2 = (TextView) arrayList15.get(i3);
            ArrayList arrayList16 = arrayList13;
            TextView textView3 = (TextView) arrayList16.get(i3);
            TextView textView4 = (TextView) arrayList14.get(i3);
            textView2.setVisibility(i2);
            textView3.setVisibility(i2);
            textView4.setVisibility(i2);
            ArrayList arrayList17 = arrayList14;
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
            i3 += 1;
            arrayList12 = arrayList15;
            arrayList13 = arrayList16;
            arrayList14 = arrayList17;
            i2 = 0;
        }
        Collections.sort(this.listPF);
        int i4 = 0;
        for (i = 15; i4 < this.listPF.size() && i4 < i; i = 15) {
            Vin vin2 = this.listPF.get(i4);
            TextView textView5 = (TextView) arrayList.get(i4);
            TextView textView6 = (TextView) arrayList2.get(i4);
            TextView textView7 = (TextView) arrayList3.get(i4);
            textView5.setVisibility(View.VISIBLE);
            textView6.setVisibility(View.VISIBLE);
            textView7.setVisibility(View.VISIBLE);
            textView5.setText(vin2.getNom() + " (" + vin2.getVigneron() + ")");
            textView6.setText(vin2.getPrixToString() + " €");
            try {
                textView7.setText(vin2.getMillesimeToString());
            } catch (Exception unused2) {
            }
            if (!vin2.isDispo()) {
                crossout(textView5, textView7, textView6);
            } else {
                uncrossout(textView5, textView7, textView6);
            }
            i4 += 1;
        }
        Iterator<Integer> it10 = this.hashMapLoire.keySet().iterator();
        int i5 = 0;
        while (it10.hasNext()) {
            ArrayList<Vin> arrayList20 = this.hashMapLoire.get(Integer.valueOf(it10.next().intValue()));
            Collections.sort(arrayList20);
            int i6 = i5;
            int i7 = 0;
            while (i7 < arrayList20.size() && i6 < 10 && i7 < 10) {
                Vin vin3 = arrayList20.get(i7);
                TextView textView8 = (TextView) arrayList7.get(i6);
                TextView textView9 = (TextView) arrayList8.get(i6);
                TextView textView10 = (TextView) arrayList9.get(i6);
                textView8.setVisibility(View.VISIBLE);
                textView9.setVisibility(View.VISIBLE);
                textView10.setVisibility(View.VISIBLE);
                Iterator<Integer> it11 = it10;
                textView8.setText(vin3.getNom() + " (" + vin3.getVigneron() + ")");
                textView9.setText(vin3.getPrixToString() + " €");
                try {
                    textView10.setText(vin3.getMillesimeToString());
                } catch (Exception unused3) {
                }
                if (!vin3.isDispo()) {
                    crossout(textView8, textView10, textView9);
                } else {
                    uncrossout(textView8, textView10, textView9);
                }
                i6 += 1;
                i7 += 1;
                it10 = it11;
            }
            i5 = i6;
            it10 = it10;
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