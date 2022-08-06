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
public class EtAussiFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Aperitif> listPays;
    private String mParam1;
    private String mParam2;

    public static EtAussiFragment newInstance(String param1, String param2) {
        EtAussiFragment etAussiFragment = new EtAussiFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, param1);
        bundle.putString(ARG_PARAM2, param2);
        etAussiFragment.setArguments(bundle);
        return etAussiFragment;
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
        View inflate = inflater.inflate(R.layout.fragment_et_aussi, container, false);
        this.listPays = new ArrayList();
        fetchVins(inflate);
        return inflate;
    }

    public void fetchVins(final View v) {
        this.db.collection("Spiritueux").whereEqualTo("type", Integer.valueOf((int) 4)).addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.barFragments.EtAussiFragment.1
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                EtAussiFragment.this.listPays = new ArrayList();
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    EtAussiFragment.this.listPays.add(EtAussiFragment.this.unserializeAperitif(it.next()));
                }
                EtAussiFragment.this.dispoVins1(v);
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
        aperitif.setOrdre(Math.toIntExact(doc.getLong("ordre").longValue()));
        return aperitif;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoVins1(View v) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
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
        ArrayList arrayList5 = arrayList;
        arrayList3.add((TextView) v.findViewById(R.id.S1carafe1));
        arrayList3.add((TextView) v.findViewById(R.id.S1carafe2));
        arrayList3.add((TextView) v.findViewById(R.id.S1carafe3));
        arrayList3.add((TextView) v.findViewById(R.id.S1carafe4));
        arrayList3.add((TextView) v.findViewById(R.id.S1carafe5));
        arrayList3.add((TextView) v.findViewById(R.id.S1carafe6));
        arrayList3.add((TextView) v.findViewById(R.id.S1carafe7));
        arrayList3.add((TextView) v.findViewById(R.id.S1carafe8));
        arrayList3.add((TextView) v.findViewById(R.id.S1carafe9));
        arrayList3.add((TextView) v.findViewById(R.id.S1carafe10));
        arrayList3.add((TextView) v.findViewById(R.id.S1carafe11));
        arrayList3.add((TextView) v.findViewById(R.id.S1carafe12));
        arrayList3.add((TextView) v.findViewById(R.id.S1carafe13));
        arrayList3.add((TextView) v.findViewById(R.id.S1carafe14));
        arrayList3.add((TextView) v.findViewById(R.id.S1carafe15));
        ArrayList arrayList6 = arrayList3;
        arrayList2.add((TextView) v.findViewById(R.id.S1verre1));
        arrayList2.add((TextView) v.findViewById(R.id.S1verre2));
        arrayList2.add((TextView) v.findViewById(R.id.S1verre3));
        arrayList2.add((TextView) v.findViewById(R.id.S1verre4));
        arrayList2.add((TextView) v.findViewById(R.id.S1verre5));
        arrayList2.add((TextView) v.findViewById(R.id.S1verre6));
        arrayList2.add((TextView) v.findViewById(R.id.S1verre7));
        arrayList2.add((TextView) v.findViewById(R.id.S1verre8));
        arrayList2.add((TextView) v.findViewById(R.id.S1verre9));
        arrayList2.add((TextView) v.findViewById(R.id.S1verre10));
        arrayList2.add((TextView) v.findViewById(R.id.S1verre11));
        arrayList2.add((TextView) v.findViewById(R.id.S1verre12));
        arrayList2.add((TextView) v.findViewById(R.id.S1verre13));
        arrayList2.add((TextView) v.findViewById(R.id.S1verre14));
        arrayList2.add((TextView) v.findViewById(R.id.S1verre15));
        ArrayList arrayList7 = arrayList2;
        arrayList4.add((TextView) v.findViewById(R.id.S1prix1));
        arrayList4.add((TextView) v.findViewById(R.id.S1prix2));
        arrayList4.add((TextView) v.findViewById(R.id.S1prix3));
        arrayList4.add((TextView) v.findViewById(R.id.S1prix4));
        arrayList4.add((TextView) v.findViewById(R.id.S1prix5));
        arrayList4.add((TextView) v.findViewById(R.id.S1prix6));
        arrayList4.add((TextView) v.findViewById(R.id.S1prix7));
        arrayList4.add((TextView) v.findViewById(R.id.S1prix8));
        arrayList4.add((TextView) v.findViewById(R.id.S1prix9));
        arrayList4.add((TextView) v.findViewById(R.id.S1prix10));
        arrayList4.add((TextView) v.findViewById(R.id.S1prix11));
        arrayList4.add((TextView) v.findViewById(R.id.S1prix12));
        arrayList4.add((TextView) v.findViewById(R.id.S1prix13));
        arrayList4.add((TextView) v.findViewById(R.id.S1prix14));
        arrayList4.add((TextView) v.findViewById(R.id.S1prix15));
        Iterator it = arrayList5.iterator();
        while (it.hasNext()) {
            ((TextView) it.next()).setVisibility(View.GONE);
        }
        Iterator it2 = arrayList6.iterator();
        while (it2.hasNext()) {
            ((TextView) it2.next()).setVisibility(View.GONE);
        }
        Iterator it3 = arrayList7.iterator();
        while (it3.hasNext()) {
            ((TextView) it3.next()).setVisibility(View.GONE);
        }
        Iterator it4 = arrayList4.iterator();
        while (it4.hasNext()) {
            ((TextView) it4.next()).setVisibility(View.GONE);
        }
        Collections.sort(this.listPays);
        int i = 0;
        while (i < this.listPays.size() && i < 15) {
            Aperitif aperitif = this.listPays.get(i);
            ArrayList arrayList8 = arrayList5;
            TextView textView2 = (TextView) arrayList8.get(i);
            ArrayList arrayList9 = arrayList7;
            TextView textView3 = (TextView) arrayList9.get(i);
            ArrayList arrayList10 = arrayList6;
            TextView textView4 = (TextView) arrayList10.get(i);
            TextView textView5 = (TextView) arrayList4.get(i);
            textView2.setVisibility(View.VISIBLE);
            if (aperitif.getPrix50() != 0.0d) {
                textView3.setVisibility(View.VISIBLE);
                textView3.setText(aperitif.getPrix50ToString() + " €");
            } else {
                textView3.setVisibility(View.GONE);
            }
            if (aperitif.getPrix1() != 0.0d) {
                textView4.setVisibility(View.VISIBLE);
                textView4.setText(aperitif.getPrix1ToString() + " €");
            } else {
                textView4.setVisibility(View.GONE);
            }
            if (aperitif.getPrix150() != 0.0d) {
                textView5.setVisibility(View.VISIBLE);
                textView5.setText(aperitif.getPrix150ToString() + " €");
            } else {
                textView5.setVisibility(View.GONE);
            }
            textView2.setText(aperitif.getNom());
            if (!aperitif.isDispo()) {
                crossout(textView2, textView3, textView4, textView5);
            } else {
                uncrossout(textView2, textView3, textView4, textView5);
            }
            i += 1;
            arrayList5 = arrayList8;
            arrayList7 = arrayList9;
            arrayList6 = arrayList10;
        }
    }

    private void crossout(TextView nom, TextView prix, TextView mill, TextView carafe) {
        nom.setPaintFlags(nom.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        prix.setPaintFlags(prix.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        mill.setPaintFlags(mill.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        carafe.setPaintFlags(carafe.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void uncrossout(TextView nom, TextView prix, TextView mill, TextView carafe) {
        nom.setPaintFlags(nom.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        prix.setPaintFlags(prix.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        mill.setPaintFlags(mill.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        carafe.setPaintFlags(carafe.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
    }
}