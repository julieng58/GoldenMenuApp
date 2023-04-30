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
import com.saslebengy.goldenmenu.model.Biere;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: base/dex/classes2.dex */
public class BiereActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Biere> listDonjon;
    private List<Biere> listOuche;
    private List<Biere> listAutres;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bieres);
        getWindow().addFlags(128);
        this.listDonjon = new ArrayList();
        this.listAutres = new ArrayList();
        this.listOuche = new ArrayList();
        fetchVins();
    }

    public void fetchVins() {
        this.db.collection("Biere").addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.soloPageVin.BiereActivity.1
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                BiereActivity.this.listDonjon = new ArrayList();
                BiereActivity.this.listAutres = new ArrayList();
                BiereActivity.this.listOuche = new ArrayList();
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    QueryDocumentSnapshot next = it.next();
                    if (next.getString("brasseur").equals("401DONJO")) {
                        BiereActivity.this.listDonjon.add(BiereActivity.this.unserializeBiere(next));
                    } else if (next.getString("brasseur").equals("401LOUP")) {
                        BiereActivity.this.listOuche.add(BiereActivity.this.unserializeBiere(next));
                    } else {
                        BiereActivity.this.listAutres.add(BiereActivity.this.unserializeBiere(next));
                    }
                }
                BiereActivity.this.dispoVins();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Biere unserializeBiere(QueryDocumentSnapshot doc) {
        Biere biere = new Biere();
        biere.setDispo(doc.getBoolean("dispo").booleanValue());
        biere.setPrix(doc.getDouble("prix"));
        biere.setBrasseur(doc.getString("brasseur"));
        biere.setDescription(doc.getString("description"));
        biere.setNom(doc.getString("nom"));
        return biere;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoVins() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
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
        arrayList2.add((TextView) findViewById(R.id.S1prix1));
        arrayList2.add((TextView) findViewById(R.id.S1prix2));
        arrayList2.add((TextView) findViewById(R.id.S1prix3));
        arrayList2.add((TextView) findViewById(R.id.S1prix4));
        arrayList2.add((TextView) findViewById(R.id.S1prix5));
        arrayList2.add((TextView) findViewById(R.id.S1prix6));
        arrayList2.add((TextView) findViewById(R.id.S1prix7));
        arrayList2.add((TextView) findViewById(R.id.S1prix8));
        arrayList2.add((TextView) findViewById(R.id.S1prix9));
        arrayList2.add((TextView) findViewById(R.id.S1prix10));
        arrayList3.add((TextView) findViewById(R.id.S2label1));
        arrayList3.add((TextView) findViewById(R.id.S2label2));
        arrayList3.add((TextView) findViewById(R.id.S2label3));
        arrayList3.add((TextView) findViewById(R.id.S2label4));
        arrayList3.add((TextView) findViewById(R.id.S2label5));
        arrayList3.add((TextView) findViewById(R.id.S2label6));
        arrayList3.add((TextView) findViewById(R.id.S2label7));
        arrayList3.add((TextView) findViewById(R.id.S2label8));
        arrayList3.add((TextView) findViewById(R.id.S2label9));
        arrayList3.add((TextView) findViewById(R.id.S2label10));
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
        arrayList5.add((TextView) findViewById(R.id.s3label1));
        arrayList5.add((TextView) findViewById(R.id.s3label2));
        arrayList5.add((TextView) findViewById(R.id.s3label3));
        arrayList5.add((TextView) findViewById(R.id.s3label4));
        arrayList5.add((TextView) findViewById(R.id.s3label5));
        arrayList5.add((TextView) findViewById(R.id.s3label6));
        arrayList5.add((TextView) findViewById(R.id.s3label7));
        arrayList5.add((TextView) findViewById(R.id.s3label8));
        arrayList5.add((TextView) findViewById(R.id.s3label9));
        arrayList5.add((TextView) findViewById(R.id.s3label10));
        arrayList6.add((TextView) findViewById(R.id.s3prix1));
        arrayList6.add((TextView) findViewById(R.id.s3prix2));
        arrayList6.add((TextView) findViewById(R.id.s3prix3));
        arrayList6.add((TextView) findViewById(R.id.s3prix4));
        arrayList6.add((TextView) findViewById(R.id.s3prix5));
        arrayList6.add((TextView) findViewById(R.id.s3prix6));
        arrayList6.add((TextView) findViewById(R.id.s3prix7));
        arrayList6.add((TextView) findViewById(R.id.s3prix8));
        arrayList6.add((TextView) findViewById(R.id.s3prix9));
        arrayList6.add((TextView) findViewById(R.id.s3prix10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((TextView) it.next()).setVisibility(View.GONE);
        }
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            ((TextView) it2.next()).setVisibility(View.GONE);
        }
        Iterator it3 = arrayList5.iterator();
        while (it3.hasNext()) {
            ((TextView) it3.next()).setVisibility(View.GONE);
        }
        Iterator it4 = arrayList2.iterator();
        while (it4.hasNext()) {
            ((TextView) it4.next()).setVisibility(View.GONE);
        }
        Iterator it5 = arrayList4.iterator();
        while (it5.hasNext()) {
            ((TextView) it5.next()).setVisibility(View.GONE);
        }
        Iterator it6 = arrayList6.iterator();
        while (it6.hasNext()) {
            ((TextView) it6.next()).setVisibility(View.GONE);
        }
        Collections.sort(this.listDonjon);
        for (int i = 0; i < this.listDonjon.size() && i < 10; i += 1) {
            Biere biere = this.listDonjon.get(i);
            TextView textView = (TextView) arrayList.get(i);
            TextView textView2 = (TextView) arrayList2.get(i);
            textView.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView.setText(biere.getNom() + " - " + biere.getDescription());
            textView2.setText(biere.getPrixToString() + " €");
            if (!biere.isDispo()) {
                crossout(textView, textView2);
            } else {
                uncrossout(textView, textView2);
            }
        }
        Collections.sort(this.listAutres);
        for (int i2 = 0; i2 < this.listAutres.size() && i2 < 10; i2 += 1) {
            Biere biere2 = this.listAutres.get(i2);
            TextView textView3 = (TextView) arrayList3.get(i2);
            TextView textView4 = (TextView) arrayList4.get(i2);
            textView3.setVisibility(View.VISIBLE);
            textView4.setVisibility(View.VISIBLE);
            textView3.setText(biere2.getNom() + " - " + biere2.getDescription());
            textView4.setText(biere2.getPrixToString() + " €");
            if (!biere2.isDispo()) {
                crossout(textView3, textView4);
            } else {
                uncrossout(textView3, textView4);
            }
        }
        Collections.sort(this.listOuche);
        for (int i3 = 0; i3 < this.listOuche.size() && i3 < 10 ; i3 += 1) {
            Biere biere3 = this.listOuche.get(i3);
            TextView textView5 = (TextView) arrayList5.get(i3);
            TextView textView6 = (TextView) arrayList6.get(i3);
            textView5.setVisibility(View.VISIBLE);
            textView6.setVisibility(View.VISIBLE);
            textView5.setText(biere3.getNom() + " - " + biere3.getDescription());
            textView6.setText(biere3.getPrixToString() + " €");
            if (!biere3.isDispo()) {
                crossout(textView5, textView6);
            } else {
                uncrossout(textView5, textView6);
            }
        }
    }

    private void crossout(TextView nom, TextView prix) {
        nom.setPaintFlags(nom.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        prix.setPaintFlags(prix.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void uncrossout(TextView nom, TextView prix) {
        nom.setPaintFlags(nom.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        prix.setPaintFlags(prix.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
    }
}