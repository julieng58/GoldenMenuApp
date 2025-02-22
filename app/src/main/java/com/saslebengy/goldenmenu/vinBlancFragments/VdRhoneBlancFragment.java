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
public class VdRhoneBlancFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private HashMap<Integer, ArrayList<Vin>> hashMapVins;
    private List<Vin> listPays;
    private String mParam1;
    private String mParam2;

    public static VdRhoneBlancFragment newInstance(String param1, String param2) {
        VdRhoneBlancFragment vdRhoneBlancFragment = new VdRhoneBlancFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, param1);
        bundle.putString(ARG_PARAM2, param2);
        vdRhoneBlancFragment.setArguments(bundle);
        return vdRhoneBlancFragment;
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
        View inflate = inflater.inflate(R.layout.fragment_vd_rhone_blanc, container, false);
        this.listPays = new ArrayList();
        this.hashMapVins = new HashMap<>();
        fetchVins(inflate);
        return inflate;
    }

    public void fetchVins(final View v) {
        this.db.collection("Vin").whereEqualTo("type", 1).whereEqualTo("region", 13).whereArrayContains("contenant", "75").addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.vinBlancFragments.VdRhoneBlancFragment.1
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                VdRhoneBlancFragment.this.listPays = new ArrayList();
                VdRhoneBlancFragment.this.hashMapVins = new HashMap();
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    QueryDocumentSnapshot next = it.next();
                    Log.e("DEBUG", "On est la");
                    if (next.getLong("appellation").longValue() != 1) {
                        VdRhoneBlancFragment.this.listPays.add(VdRhoneBlancFragment.this.unserializeVin(next, "75"));
                    } else {
                        int intExact = Math.toIntExact(next.getLong("appellationNom").longValue());
                        if (VdRhoneBlancFragment.this.hashMapVins.containsKey(Integer.valueOf(intExact))) {
                            ((ArrayList) VdRhoneBlancFragment.this.hashMapVins.get(Integer.valueOf(intExact))).add(VdRhoneBlancFragment.this.unserializeVin(next, "75"));
                        } else {
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(VdRhoneBlancFragment.this.unserializeVin(next, "75"));
                            VdRhoneBlancFragment.this.hashMapVins.put(Integer.valueOf(intExact), arrayList);
                        }
                    }
                }
                VdRhoneBlancFragment.this.dispoVins(v);
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
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
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
        ArrayList arrayList7 = arrayList;
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
        ArrayList arrayList8 = arrayList2;
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
        ArrayList arrayList9 = arrayList3;
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
        ArrayList arrayList10 = arrayList4;
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
        ArrayList arrayList11 = arrayList5;
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
        Iterator it = arrayList7.iterator();
        while (it.hasNext()) {
            ((TextView) it.next()).setVisibility(View.GONE);;
        }
        Iterator it2 = arrayList10.iterator();
        while (it2.hasNext()) {
            ((TextView) it2.next()).setVisibility(View.GONE);;
        }
        Iterator it3 = arrayList8.iterator();
        while (it3.hasNext()) {
            ((TextView) it3.next()).setVisibility(View.GONE);;
        }
        Iterator it4 = arrayList11.iterator();
        while (it4.hasNext()) {
            ((TextView) it4.next()).setVisibility(View.GONE);;
        }
        Iterator it5 = arrayList9.iterator();
        while (it5.hasNext()) {
            ((TextView) it5.next()).setVisibility(View.GONE);;
        }
        Iterator it6 = arrayList6.iterator();
        while (it6.hasNext()) {
            ((TextView) it6.next()).setVisibility(View.GONE);;
        }
        Collections.sort(this.listPays);
        Iterator<Integer> it7 = this.hashMapVins.keySet().iterator();
        int i = 0;
        while (true) {
            boolean hasNext = it7.hasNext();
            int i2 = 15;
            if (!hasNext) {
                break;
            }
            ArrayList<Vin> arrayList12 = this.hashMapVins.get(Integer.valueOf(it7.next().intValue()));
            Collections.sort(arrayList12);
            int i3 = 0;
            while (i3 < arrayList12.size() && i < i2 && i3 < i2) {
                Vin vin = arrayList12.get(i3);
                ArrayList arrayList13 = arrayList7;
                TextView textView2 = (TextView) arrayList13.get(i);
                ArrayList arrayList14 = arrayList8;
                TextView textView3 = (TextView) arrayList14.get(i);
                ArrayList arrayList15 = arrayList9;
                Iterator<Integer> it8 = it7;
                TextView textView4 = (TextView) arrayList15.get(i);
                arrayList9 = arrayList15;
                textView2.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);
                textView4.setVisibility(View.VISIBLE);
                ArrayList<Vin> arrayList16 = arrayList12;
                textView2.setText(vin.getNom() + " (" + vin.getVigneron() + ")");
                textView3.setText(vin.getPrixToString() + " €");
                Log.e("DEBUG", "On est la");
                try {
                    textView4.setText(vin.getMillesimeToString());
                } catch (Exception unused) {
                }
                if (!vin.isDispo()) {
                    crossout(textView2, textView4, textView3);
                } else {
                    uncrossout(textView2, textView4, textView3);
                }
                i += 1;
                i3 += 1;
                it7 = it8;
                arrayList12 = arrayList16;
                i2 = 15;
                arrayList7 = arrayList13;
                arrayList8 = arrayList14;
            }
            arrayList7 = arrayList7;
            it7 = it7;
            arrayList8 = arrayList8;
        }
        int i4 = 0;
        while (i4 < this.listPays.size() && i4 < 15) {
            Log.e("DEBUG", "On est la 2");
            Vin vin2 = this.listPays.get(i4);
            ArrayList arrayList17 = arrayList10;
            TextView textView5 = (TextView) arrayList17.get(i4);
            ArrayList arrayList18 = arrayList11;
            TextView textView6 = (TextView) arrayList18.get(i4);
            TextView textView7 = (TextView) arrayList6.get(i4);
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
            arrayList10 = arrayList17;
            arrayList11 = arrayList18;
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