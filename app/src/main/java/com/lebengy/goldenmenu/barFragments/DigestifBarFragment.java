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
public class DigestifBarFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Aperitif> listPays;
    private String mParam1;
    private String mParam2;

    public static DigestifBarFragment newInstance(String param1, String param2) {
        DigestifBarFragment digestifBarFragment = new DigestifBarFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, param1);
        bundle.putString(ARG_PARAM2, param2);
        digestifBarFragment.setArguments(bundle);
        return digestifBarFragment;
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
        View inflate = inflater.inflate(R.layout.fragment_digestif_bar, container, false);
        this.listPays = new ArrayList();
        fetchVins(inflate);
        return inflate;
    }

    public void fetchVins(final View v) {
        this.db.collection("Spiritueux").whereEqualTo("type", 3).addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.barFragments.DigestifBarFragment.1
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                DigestifBarFragment.this.listPays = new ArrayList();
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    DigestifBarFragment.this.listPays.add(DigestifBarFragment.this.unserializeAperitif(it.next()));
                }
                DigestifBarFragment.this.dispoVins1(v);
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
        ArrayList arrayList3 = arrayList;
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
        Iterator it = arrayList3.iterator();
        while (it.hasNext()) {
            ((TextView) it.next()).setVisibility(View.GONE);
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            ((TextView) it2.next()).setVisibility(View.GONE);
        }
        Collections.sort(this.listPays);
        int i = 0;
        while (i < this.listPays.size() && i < 15) {
            Aperitif aperitif = this.listPays.get(i);
            ArrayList arrayList4 = arrayList3;
            TextView textView2 = (TextView) arrayList4.get(i);
            TextView textView3 = (TextView) arrayList2.get(i);
            textView2.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            if (aperitif.getPrix4() != 0.0d) {
                textView3.setVisibility(View.VISIBLE);
                textView3.setText(aperitif.getPrix4ToString() + " €");
            } else {
                textView3.setVisibility(View.GONE);
            }
            textView2.setText(aperitif.getNom());
            if (!aperitif.isDispo()) {
                crossout(textView2, textView3);
            } else {
                uncrossout(textView2, textView3);
            }
            i += 1;
            arrayList3 = arrayList4;
        }
    }

    private void crossout(TextView nom, TextView prix) {
        nom.setPaintFlags(nom.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        prix.setPaintFlags(prix.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void uncrossout(TextView nom, TextView prix){
        nom.setPaintFlags(nom.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        prix.setPaintFlags(prix.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
    }
}