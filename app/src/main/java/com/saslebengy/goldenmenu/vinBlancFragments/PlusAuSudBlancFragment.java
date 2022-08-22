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
import java.util.Iterator;
import java.util.List;

/* loaded from: base/dex/classes2.dex */
public class PlusAuSudBlancFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Vin> listEtranger;
    private List<Vin> listLR;
    private List<Vin> listSO;
    private String mParam1;
    private String mParam2;

    public static PlusAuSudBlancFragment newInstance(String param1, String param2) {
        PlusAuSudBlancFragment plusAuSudBlancFragment = new PlusAuSudBlancFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, param1);
        bundle.putString(ARG_PARAM2, param2);
        plusAuSudBlancFragment.setArguments(bundle);
        return plusAuSudBlancFragment;
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
        View inflate = inflater.inflate(R.layout.fragment_plus_au_sud_blanc, container, false);
        fetchVins(inflate);
        return inflate;
    }

    public void fetchVins(final View v) {
        CollectionReference collection = this.db.collection("Vin");
        Integer valueOf = Integer.valueOf((int) 1);
        collection.whereEqualTo("type", valueOf).whereEqualTo("appellation", valueOf).whereEqualTo("region", (Object) 99).whereArrayContains("contenant", "75").addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.vinBlancFragments.PlusAuSudBlancFragment.1
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                PlusAuSudBlancFragment.this.listEtranger = new ArrayList();
                Log.e("DEBUG", "On est la");
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    PlusAuSudBlancFragment.this.listEtranger.add(PlusAuSudBlancFragment.this.unserializeVin(it.next(), "75"));
                }
                PlusAuSudBlancFragment.this.dispoVins3(v);
            }
        });
        this.db.collection("Vin").whereEqualTo("type", valueOf).whereEqualTo("appellation", valueOf).whereEqualTo("region", Integer.valueOf((int) 15)).whereArrayContains("contenant", "75").addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.vinBlancFragments.PlusAuSudBlancFragment.2
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                PlusAuSudBlancFragment.this.listSO = new ArrayList();
                Log.e("DEBUG", "On est la");
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    PlusAuSudBlancFragment.this.listSO.add(PlusAuSudBlancFragment.this.unserializeVin(it.next(), "75"));
                }
                PlusAuSudBlancFragment.this.dispoVins2(v);
            }
        });
        this.db.collection("Vin").whereEqualTo("type", valueOf).whereEqualTo("appellation", valueOf).whereEqualTo("region",9).whereArrayContains("contenant", "75").addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.vinBlancFragments.PlusAuSudBlancFragment.3
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                PlusAuSudBlancFragment.this.listLR = new ArrayList();
                Log.e("DEBUG", "On est la");
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    PlusAuSudBlancFragment.this.listLR.add(PlusAuSudBlancFragment.this.unserializeVin(it.next(), "75"));
                }
                PlusAuSudBlancFragment.this.dispoVins1(v);
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
            ((TextView) it.next()).setVisibility(View.GONE);;
        }
        Iterator it2 = arrayList5.iterator();
        while (it2.hasNext()) {
            ((TextView) it2.next()).setVisibility(View.GONE);;
        }
        Iterator it3 = arrayList3.iterator();
        while (it3.hasNext()) {
            ((TextView) it3.next()).setVisibility(View.GONE);;
        }
        Collections.sort(this.listLR);
        int i = 0;
        while (i < this.listLR.size() && i < 15) {
            Vin vin = this.listLR.get(i);
            ArrayList arrayList6 = arrayList4;
            TextView textView2 = (TextView) arrayList6.get(i);
            ArrayList arrayList7 = arrayList5;
            TextView textView3 = (TextView) arrayList7.get(i);
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
            arrayList4 = arrayList6;
            arrayList5 = arrayList7;
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
        arrayList2.add((TextView) v.findViewById(R.id.S2prix1));
        arrayList2.add((TextView) v.findViewById(R.id.S2prix2));
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
            ((TextView) it.next()).setVisibility(View.GONE);;
        }
        Iterator it2 = arrayList5.iterator();
        while (it2.hasNext()) {
            ((TextView) it2.next()).setVisibility(View.GONE);;
        }
        Iterator it3 = arrayList3.iterator();
        while (it3.hasNext()) {
            ((TextView) it3.next()).setVisibility(View.GONE);;
        }
        Collections.sort(this.listSO);
        int i = 0;
        while (i < this.listSO.size() && i < 15) { //TODO: Trouver Valeur de R.styleable.ChipGroup => Mis 15 sans conviction
            Vin vin = this.listSO.get(i);
            ArrayList arrayList6 = arrayList4;
            TextView textView2 = (TextView) arrayList6.get(i);
            ArrayList arrayList7 = arrayList5;
            TextView textView3 = (TextView) arrayList7.get(i);
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
            arrayList4 = arrayList6;
            arrayList5 = arrayList7;
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
            ((TextView) it.next()).setVisibility(View.GONE);;
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            ((TextView) it2.next()).setVisibility(View.GONE);;
        }
        Iterator it3 = arrayList3.iterator();
        while (it3.hasNext()) {
            ((TextView) it3.next()).setVisibility(View.GONE);;
        }
        Collections.sort(this.listEtranger);
        for (int i = 0; i < this.listEtranger.size() && i < 10; i += 1) {
            Vin vin = this.listEtranger.get(i);
            TextView textView = (TextView) arrayList.get(i);
            TextView textView2 = (TextView) arrayList2.get(i);
            TextView textView3 = (TextView) arrayList3.get(i);
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