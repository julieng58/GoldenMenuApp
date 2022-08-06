package com.lebengy.goldenmenu.vinRougeFragments;

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
import com.lebengy.goldenmenu.R;
import com.lebengy.goldenmenu.model.Vin;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: base/dex/classes2.dex */
public class BeaujolaisRougeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private HashMap<Integer, ArrayList<Vin>> hashMapVins;
    private String mParam1;
    private String mParam2;

    public static BeaujolaisRougeFragment newInstance(String param1, String param2) {
        BeaujolaisRougeFragment beaujolaisRougeFragment = new BeaujolaisRougeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, param1);
        bundle.putString(ARG_PARAM2, param2);
        beaujolaisRougeFragment.setArguments(bundle);
        return beaujolaisRougeFragment;
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
        View inflate = inflater.inflate(R.layout.fragment_beaujolais_rouge, container, false);
        this.hashMapVins = new HashMap<>();
        fetchVins(inflate);
        return inflate;
    }

    public void fetchVins(final View v) {
        this.db.collection("Vin").whereEqualTo("type", Integer.valueOf((int) 2)).whereEqualTo("appellation", Integer.valueOf((int) 1)).whereEqualTo("region", Integer.valueOf((int) 3)).whereArrayContains("contenant", "75").addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.vinRougeFragments.BeaujolaisRougeFragment.1
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                BeaujolaisRougeFragment.this.hashMapVins = new HashMap();
                Log.e("DEBUG", "On est la");
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    QueryDocumentSnapshot next = it.next();
                    int intExact = Math.toIntExact(next.getLong("appellationNom").longValue());
                    if (BeaujolaisRougeFragment.this.hashMapVins.containsKey(Integer.valueOf(intExact))) {
                        ((ArrayList) BeaujolaisRougeFragment.this.hashMapVins.get(Integer.valueOf(intExact))).add(BeaujolaisRougeFragment.this.unserializeVin(next, "75"));
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(BeaujolaisRougeFragment.this.unserializeVin(next, "75"));
                        BeaujolaisRougeFragment.this.hashMapVins.put(Integer.valueOf(intExact), arrayList);
                    }
                }
                BeaujolaisRougeFragment.this.dispoVins(v);
            }
        });
    }

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

    public void dispoVins(View v) {
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
        int i = 0;
        for (Integer num : this.hashMapVins.keySet()) {
            ArrayList<Vin> arrayList6 = this.hashMapVins.get(Integer.valueOf(num.intValue()));
            Collections.sort(arrayList6);
            int i2 = 0;
            while (i2 < arrayList6.size() && i < 15  && i2 < 15) {
                Vin vin = arrayList6.get(i2);
                ArrayList arrayList7 = arrayList4;
                TextView textView2 = (TextView) arrayList7.get(i);
                ArrayList arrayList8 = arrayList5;
                TextView textView3 = (TextView) arrayList8.get(i);
                TextView textView4 = (TextView) arrayList3.get(i);
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
                i += 1;
                i2 += 1;
                arrayList4 = arrayList7;
                arrayList5 = arrayList8;
            }
            arrayList4 = arrayList4;
            arrayList5 = arrayList5;
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