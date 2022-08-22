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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.saslebengy.goldenmenu.R;
import com.saslebengy.goldenmenu.model.Vin;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: base/dex/classes2.dex */
public class RoseActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private HashMap<Integer, ArrayList<Vin>> hashMapVins;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rose);
        getWindow().addFlags(128);
        this.hashMapVins = new HashMap<>();
        fetchVins();
    }

    public void fetchVins() {
        this.db.collection("Vin").whereEqualTo("type", 3).whereArrayContains("contenant", "75").addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.soloPageVin.RoseActivity.1
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                RoseActivity.this.hashMapVins = new HashMap();
                Log.e("DEBUG", "On est la");
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    QueryDocumentSnapshot next = it.next();
                    int intExact = Math.toIntExact(next.getLong("appellationNom").longValue());
                    if (RoseActivity.this.hashMapVins.containsKey(Integer.valueOf(intExact))) {
                        ((ArrayList) RoseActivity.this.hashMapVins.get(Integer.valueOf(intExact))).add(RoseActivity.this.unserializeVin(next, "75"));
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(RoseActivity.this.unserializeVin(next, "75"));
                        RoseActivity.this.hashMapVins.put(Integer.valueOf(intExact), arrayList);
                    }
                }
                RoseActivity.this.dispoVins();
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
    public void dispoVins() {
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
        int i = 0;
        for (Integer num : this.hashMapVins.keySet()) {
            ArrayList<Vin> arrayList6 = this.hashMapVins.get(Integer.valueOf(num.intValue()));
            Collections.sort(arrayList6);
            int i2 = 0;
            while (i2 < arrayList6.size() && i < 15 && i2 < 15) {
                Vin vin = arrayList6.get(i2);
                ArrayList arrayList7 = arrayList5;
                TextView textView2 = (TextView) arrayList7.get(i);
                ArrayList arrayList8 = arrayList4;
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
                arrayList5 = arrayList7;
                arrayList4 = arrayList8;
            }
            arrayList5 = arrayList5;
            arrayList4 = arrayList4;
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