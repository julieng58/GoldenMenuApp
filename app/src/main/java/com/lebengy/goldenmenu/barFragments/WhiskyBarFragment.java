package com.lebengy.goldenmenu.barFragments;

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
import com.lebengy.goldenmenu.model.Aperitif;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: base/dex/classes2.dex */
public class WhiskyBarFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Aperitif> listPays;
    private String mParam1;
    private String mParam2;

    public static WhiskyBarFragment newInstance(String param1, String param2) {
        WhiskyBarFragment whiskyBarFragment = new WhiskyBarFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, param1);
        bundle.putString(ARG_PARAM2, param2);
        whiskyBarFragment.setArguments(bundle);
        return whiskyBarFragment;
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
        View inflate = inflater.inflate(R.layout.fragment_whisky_bar, container, false);
        this.listPays = new ArrayList();
        fetchVins(inflate);
        return inflate;
    }

    public void fetchVins(final View v) {
        this.db.collection("Spiritueux").whereEqualTo("type", Integer.valueOf((int) 2)).addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.barFragments.WhiskyBarFragment.1
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                WhiskyBarFragment.this.listPays = new ArrayList();
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    WhiskyBarFragment.this.listPays.add(WhiskyBarFragment.this.unserializeAperitif(it.next()));
                }
                WhiskyBarFragment.this.dispoVins1(v);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Aperitif unserializeAperitif(QueryDocumentSnapshot doc) {
        Aperitif aperitif = new Aperitif();
        aperitif.setDispo(doc.getBoolean("dispo").booleanValue());
        aperitif.setNom(doc.getString("nom"));
        aperitif.setType(Math.toIntExact(doc.getLong("type").longValue()));
        aperitif.setPrix1(doc.getDouble("prix1").doubleValue());
        aperitif.setPrix2(doc.getDouble("prix2").doubleValue());
        aperitif.setPrix4(doc.getDouble("prix4").doubleValue());
        aperitif.setPrix50(doc.getDouble("prix50").doubleValue());
        aperitif.setPrix150(doc.getDouble("prix150").doubleValue());
        return aperitif;
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
        arrayList2.add((TextView) v.findViewById(R.id.S1mill1));
        arrayList2.add((TextView) v.findViewById(R.id.S1mill2));
        arrayList2.add((TextView) v.findViewById(R.id.S1mill3));
        arrayList2.add((TextView) v.findViewById(R.id.S1mill4));
        arrayList2.add((TextView) v.findViewById(R.id.S1mill5));
        arrayList2.add((TextView) v.findViewById(R.id.S1mill6));
        arrayList2.add((TextView) v.findViewById(R.id.S1mill7));
        arrayList2.add((TextView) v.findViewById(R.id.S1mill8));
        arrayList2.add((TextView) v.findViewById(R.id.S1mill9));
        arrayList2.add((TextView) v.findViewById(R.id.S1mill10));
        arrayList2.add((TextView) v.findViewById(R.id.S1mill11));
        arrayList2.add((TextView) v.findViewById(R.id.S1mill12));
        arrayList2.add((TextView) v.findViewById(R.id.S1mill13));
        arrayList2.add((TextView) v.findViewById(R.id.S1mill14));
        arrayList2.add((TextView) v.findViewById(R.id.S1mill15));
        ArrayList arrayList5 = arrayList2;
        arrayList3.add((TextView) v.findViewById(R.id.S1prix1));
        arrayList3.add((TextView) v.findViewById(R.id.S1prix2));
        arrayList3.add((TextView) v.findViewById(R.id.S1prix3));
        arrayList3.add((TextView) v.findViewById(R.id.S1prix4));
        arrayList3.add((TextView) v.findViewById(R.id.S1prix5));
        arrayList3.add((TextView) v.findViewById(R.id.S1prix6));
        arrayList3.add((TextView) v.findViewById(R.id.S1prix7));
        arrayList3.add((TextView) v.findViewById(R.id.S1prix8));
        arrayList3.add((TextView) v.findViewById(R.id.S1prix9));
        arrayList3.add((TextView) v.findViewById(R.id.S1prix10));
        arrayList3.add((TextView) v.findViewById(R.id.S1prix11));
        arrayList3.add((TextView) v.findViewById(R.id.S1prix12));
        arrayList3.add((TextView) v.findViewById(R.id.S1prix13));
        arrayList3.add((TextView) v.findViewById(R.id.S1prix14));
        arrayList3.add((TextView) v.findViewById(R.id.S1prix15));
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
        Collections.sort(this.listPays);
        int i = 0;
        while (i < this.listPays.size() && i < 15) {
            Aperitif aperitif = this.listPays.get(i);
            ArrayList arrayList6 = arrayList4;
            TextView textView2 = (TextView) arrayList6.get(i);
            ArrayList arrayList7 = arrayList5;
            TextView textView3 = (TextView) arrayList7.get(i);
            TextView textView4 = (TextView) arrayList3.get(i);
            textView2.setVisibility(View.VISIBLE);
            if (aperitif.getPrix2() != 0.0d) {
                textView3.setVisibility(View.VISIBLE);
                textView3.setText(aperitif.getPrix2ToString() + " €");
            } else {
                textView3.setVisibility(View.GONE);
            }
            if (aperitif.getPrix4() != 0.0d) {
                textView4.setVisibility(View.VISIBLE);
                textView4.setText(aperitif.getPrix4ToString() + " €");
            } else {
                textView4.setVisibility(View.GONE);
            }
            textView2.setText(aperitif.getNom());
            if (!aperitif.isDispo()) {
                crossout(textView2, textView3, textView4);
            } else {
                uncrossout(textView2, textView3, textView4);
            }
            i += 1;
            arrayList4 = arrayList6;
            arrayList5 = arrayList7;
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