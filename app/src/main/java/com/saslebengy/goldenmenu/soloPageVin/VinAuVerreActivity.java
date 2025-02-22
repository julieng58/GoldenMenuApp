/*
 *
 *  * Copyright (c) 2025 Florian Nadin
 *  * All rights reserved.
 *  * Last modified 05/09/2022 19:03
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
import com.saslebengy.goldenmenu.model.VinVerre;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: base/dex/classes2.dex */
public class VinAuVerreActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<VinVerre> listApero;
    private List<VinVerre> listBlanc;
    private List<VinVerre> listRose;
    private List<VinVerre> listRouge;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vin_au_verre);
        getWindow().addFlags(128);
        this.listApero = new ArrayList();
        this.listRouge = new ArrayList();
        this.listBlanc = new ArrayList();
        this.listRose = new ArrayList();
        fetchVins();
    }

    public void fetchVins() {
        this.db.collection("Vin").whereArrayContains("contenant", "1").whereEqualTo("type", 7).addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.soloPageVin.VinAuVerreActivity.1
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                VinAuVerreActivity.this.listApero = new ArrayList();
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    VinAuVerreActivity.this.listApero.add(VinAuVerreActivity.this.unserializeVinVerre(it.next()));
                }
                VinAuVerreActivity.this.dispoVins1();
            }
        });
        this.db.collection("Vin").whereArrayContains("contenant", "1").whereEqualTo("type", 1).addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.soloPageVin.VinAuVerreActivity.2
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                VinAuVerreActivity.this.listBlanc = new ArrayList();
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    VinAuVerreActivity.this.listBlanc.add(VinAuVerreActivity.this.unserializeVinVerre(it.next()));
                }
                VinAuVerreActivity.this.dispoVins2();
            }
        });
        this.db.collection("Vin").whereArrayContains("contenant", "1").whereEqualTo("type",2).addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.soloPageVin.VinAuVerreActivity.3
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                VinAuVerreActivity.this.listRouge = new ArrayList();
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    VinAuVerreActivity.this.listRouge.add(VinAuVerreActivity.this.unserializeVinVerre(it.next()));
                }
                VinAuVerreActivity.this.dispoVins3();
            }
        });
        this.db.collection("Vin").whereArrayContains("contenant", "1").whereEqualTo("type",3).addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.soloPageVin.VinAuVerreActivity.4
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                VinAuVerreActivity.this.listRose = new ArrayList();
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    VinAuVerreActivity.this.listRose.add(VinAuVerreActivity.this.unserializeVinVerre(it.next()));
                }
                VinAuVerreActivity.this.dispoVins4();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public VinVerre unserializeVinVerre(QueryDocumentSnapshot doc) {
        VinVerre vinVerre = new VinVerre();
        vinVerre.setDispo(doc.getBoolean("dispo").booleanValue());
        vinVerre.setTypeAppellationValue(Math.toIntExact(doc.getLong("appellation").longValue()));
        vinVerre.setAppellation(doc.getString("appellationLabel"));
        vinVerre.setAppellationValue(Math.toIntExact(doc.getLong("appellationNom").longValue()));
        vinVerre.setRegionValue(Math.toIntExact(doc.getLong("region").longValue()));
        vinVerre.setType(Math.toIntExact(doc.getLong("type").longValue()));
        vinVerre.setPrix75(doc.getDouble("prix75").doubleValue());
        vinVerre.setPrix45(doc.getDouble("prix45").doubleValue());
        vinVerre.setPrix1(doc.getDouble("prix1").doubleValue());
        vinVerre.setMillesime(Math.toIntExact(doc.getLong("millesime").longValue()));
        vinVerre.setVigneron(doc.getString("vigneron"));
        vinVerre.setNom(doc.getString("nom"));
        return vinVerre;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoVins1() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        arrayList.add((TextView) findViewById(R.id.S1label1));
        arrayList.add((TextView) findViewById(R.id.S1label2));
        arrayList.add((TextView) findViewById(R.id.S1label3));
        arrayList.add((TextView) findViewById(R.id.S1label4));
        arrayList.add((TextView) findViewById(R.id.S1label5));
        arrayList.add((TextView) findViewById(R.id.S1label6));
        arrayList2.add((TextView) findViewById(R.id.S1prix1));
        arrayList2.add((TextView) findViewById(R.id.S1prix2));
        arrayList2.add((TextView) findViewById(R.id.S1prix3));
        arrayList2.add((TextView) findViewById(R.id.S1prix4));
        arrayList2.add((TextView) findViewById(R.id.S1prix5));
        arrayList2.add((TextView) findViewById(R.id.S1prix6));
        arrayList4.add((TextView) findViewById(R.id.S1carafe1));
        arrayList4.add((TextView) findViewById(R.id.S1carafe2));
        arrayList4.add((TextView) findViewById(R.id.S1carafe3));
        arrayList4.add((TextView) findViewById(R.id.S1carafe4));
        arrayList4.add((TextView) findViewById(R.id.S1carafe5));
        arrayList4.add((TextView) findViewById(R.id.S1carafe6));
        arrayList3.add((TextView) findViewById(R.id.S1verre1));
        arrayList3.add((TextView) findViewById(R.id.S1verre2));
        arrayList3.add((TextView) findViewById(R.id.S1verre3));
        arrayList3.add((TextView) findViewById(R.id.S1verre4));
        arrayList3.add((TextView) findViewById(R.id.S1verre5));
        arrayList3.add((TextView) findViewById(R.id.S1verre6));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((TextView) it.next()).setVisibility(View.GONE);
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            ((TextView) it2.next()).setVisibility(View.GONE);
        }
        Iterator it3 = arrayList4.iterator();
        while (it3.hasNext()) {
            ((TextView) it3.next()).setVisibility(View.GONE);
        }
        Iterator it4 = arrayList3.iterator();
        while (it4.hasNext()) {
            ((TextView) it4.next()).setVisibility(View.GONE);
        }
        Collections.sort(this.listApero);
        for (int i = 0; i < this.listApero.size() && i < 6; i += 1) {
            VinVerre vinVerre = this.listApero.get(i);
            TextView textView = (TextView) arrayList.get(i);
            TextView textView2 = (TextView) arrayList2.get(i);
            TextView textView3 = (TextView) arrayList4.get(i);
            TextView textView4 = (TextView) arrayList3.get(i);
            textView.setVisibility(View.VISIBLE);
            textView4.setVisibility(View.VISIBLE);
            textView4.setText(vinVerre.getPrix1ToString() + " €");
            if (vinVerre.getPrix75() != 0.0d) {
                textView2.setVisibility(View.VISIBLE);
                textView2.setText(vinVerre.getPrix75ToString() + " €");
            } else {
                textView2.setVisibility(View.GONE);
            }
            if (vinVerre.getPrix45() != 0.0d) {
                textView3.setVisibility(View.VISIBLE);
                textView3.setText(vinVerre.getPrix45ToString() + " €");
            } else {
                textView3.setVisibility(View.GONE);
            }
            textView.setText(vinVerre.getNom() + " " + vinVerre.getMillesime() + " (" + vinVerre.getVigneron() + ")");
            if (!vinVerre.isDispo()) {
                crossout(textView, textView4, textView3, textView2);
            } else {
                uncrossout(textView, textView4, textView3, textView2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoVins2() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        arrayList.add((TextView) findViewById(R.id.S2label1));
        arrayList.add((TextView) findViewById(R.id.S2label2));
        arrayList.add((TextView) findViewById(R.id.S2label3));
        arrayList.add((TextView) findViewById(R.id.S2label4));
        arrayList.add((TextView) findViewById(R.id.S2label5));
        arrayList.add((TextView) findViewById(R.id.S2label6));
        arrayList.add((TextView) findViewById(R.id.S2label7));
        arrayList.add((TextView) findViewById(R.id.S2label8));
        arrayList.add((TextView) findViewById(R.id.S2label9));
        arrayList.add((TextView) findViewById(R.id.S2label10));
        arrayList2.add((TextView) findViewById(R.id.S2prix1));
        arrayList2.add((TextView) findViewById(R.id.S2prix2));
        arrayList2.add((TextView) findViewById(R.id.S2prix3));
        arrayList2.add((TextView) findViewById(R.id.S2prix4));
        arrayList2.add((TextView) findViewById(R.id.S2prix5));
        arrayList2.add((TextView) findViewById(R.id.S2prix6));
        arrayList2.add((TextView) findViewById(R.id.S2prix7));
        arrayList2.add((TextView) findViewById(R.id.S2prix8));
        arrayList2.add((TextView) findViewById(R.id.S2prix9));
        arrayList2.add((TextView) findViewById(R.id.S2prix10));
        arrayList4.add((TextView) findViewById(R.id.S2carafe1));
        arrayList4.add((TextView) findViewById(R.id.S2carafe2));
        arrayList4.add((TextView) findViewById(R.id.S2carafe3));
        arrayList4.add((TextView) findViewById(R.id.S2carafe4));
        arrayList4.add((TextView) findViewById(R.id.S2carafe5));
        arrayList4.add((TextView) findViewById(R.id.S2carafe6));
        arrayList4.add((TextView) findViewById(R.id.S2carafe7));
        arrayList4.add((TextView) findViewById(R.id.S2carafe8));
        arrayList4.add((TextView) findViewById(R.id.S2carafe9));
        arrayList4.add((TextView) findViewById(R.id.S2carafe10));
        arrayList3.add((TextView) findViewById(R.id.S2verre1));
        arrayList3.add((TextView) findViewById(R.id.S2verre2));
        arrayList3.add((TextView) findViewById(R.id.S2verre3));
        arrayList3.add((TextView) findViewById(R.id.S2verre4));
        arrayList3.add((TextView) findViewById(R.id.S2verre5));
        arrayList3.add((TextView) findViewById(R.id.S2verre6));
        arrayList3.add((TextView) findViewById(R.id.S2verre7));
        arrayList3.add((TextView) findViewById(R.id.S2verre8));
        arrayList3.add((TextView) findViewById(R.id.S2verre9));
        arrayList3.add((TextView) findViewById(R.id.S2verre10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((TextView) it.next()).setVisibility(View.GONE);
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            ((TextView) it2.next()).setVisibility(View.GONE);
        }
        Iterator it3 = arrayList4.iterator();
        while (it3.hasNext()) {
            ((TextView) it3.next()).setVisibility(View.GONE);
        }
        Iterator it4 = arrayList3.iterator();
        while (it4.hasNext()) {
            ((TextView) it4.next()).setVisibility(View.GONE);
        }
        Collections.sort(this.listBlanc);
        for (int i = 0; i < this.listBlanc.size() && i < 10; i += 1) {
            VinVerre vinVerre = this.listBlanc.get(i);
            TextView textView = (TextView) arrayList.get(i);
            TextView textView2 = (TextView) arrayList2.get(i);
            TextView textView3 = (TextView) arrayList4.get(i);
            TextView textView4 = (TextView) arrayList3.get(i);
            textView.setVisibility(View.VISIBLE);
            textView4.setVisibility(View.VISIBLE);
            textView4.setText(vinVerre.getPrix1ToString() + " €");
            if (vinVerre.getPrix75() != 0.0d) {
                textView2.setVisibility(View.VISIBLE);
                textView2.setText(vinVerre.getPrix75ToString() + " €");
            } else {
                textView2.setVisibility(View.GONE);
            }
            if (vinVerre.getPrix45() != 0.0d) {
                textView3.setVisibility(View.VISIBLE);
                textView3.setText(vinVerre.getPrix45ToString() + " €");
            } else {
                textView3.setVisibility(View.GONE);
            }
            String nom = vinVerre.getNom();
            if (vinVerre.getMillesime() != 0) {
                nom = nom + " " + vinVerre.getMillesime();
            }
            if (!vinVerre.getVigneron().equals("zzz")) {
                nom = nom + " (" + vinVerre.getVigneron() + ")";
            }
            textView.setText(nom);
            if (!vinVerre.isDispo()) {
                crossout(textView, textView4, textView3, textView2);
            } else {
                uncrossout(textView, textView4, textView3, textView2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoVins3() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        arrayList.add((TextView) findViewById(R.id.S3label1));
        arrayList.add((TextView) findViewById(R.id.S3label2));
        arrayList.add((TextView) findViewById(R.id.S3label3));
        arrayList.add((TextView) findViewById(R.id.S3label4));
        arrayList.add((TextView) findViewById(R.id.S3label5));
        arrayList.add((TextView) findViewById(R.id.S3label6));
        arrayList.add((TextView) findViewById(R.id.S3label7));
        arrayList.add((TextView) findViewById(R.id.S3label8));
        arrayList.add((TextView) findViewById(R.id.S3label9));
        arrayList.add((TextView) findViewById(R.id.S3label10));
        arrayList2.add((TextView) findViewById(R.id.S3prix1));
        arrayList2.add((TextView) findViewById(R.id.S3prix2));
        arrayList2.add((TextView) findViewById(R.id.S3prix3));
        arrayList2.add((TextView) findViewById(R.id.S3prix4));
        arrayList2.add((TextView) findViewById(R.id.S3prix5));
        arrayList2.add((TextView) findViewById(R.id.S3prix6));
        arrayList2.add((TextView) findViewById(R.id.S3prix7));
        arrayList2.add((TextView) findViewById(R.id.S3prix8));
        arrayList2.add((TextView) findViewById(R.id.S3prix9));
        arrayList2.add((TextView) findViewById(R.id.S3prix10));
        arrayList4.add((TextView) findViewById(R.id.S3carafe1));
        arrayList4.add((TextView) findViewById(R.id.S3carafe2));
        arrayList4.add((TextView) findViewById(R.id.S3carafe3));
        arrayList4.add((TextView) findViewById(R.id.S3carafe4));
        arrayList4.add((TextView) findViewById(R.id.S3carafe5));
        arrayList4.add((TextView) findViewById(R.id.S3carafe6));
        arrayList4.add((TextView) findViewById(R.id.S3carafe7));
        arrayList4.add((TextView) findViewById(R.id.S3carafe8));
        arrayList4.add((TextView) findViewById(R.id.S3carafe9));
        arrayList4.add((TextView) findViewById(R.id.S3carafe10));
        arrayList3.add((TextView) findViewById(R.id.S3verre1));
        arrayList3.add((TextView) findViewById(R.id.S3verre2));
        arrayList3.add((TextView) findViewById(R.id.S3verre3));
        arrayList3.add((TextView) findViewById(R.id.S3verre4));
        arrayList3.add((TextView) findViewById(R.id.S3verre5));
        arrayList3.add((TextView) findViewById(R.id.S3verre6));
        arrayList3.add((TextView) findViewById(R.id.S3verre7));
        arrayList3.add((TextView) findViewById(R.id.S3verre8));
        arrayList3.add((TextView) findViewById(R.id.S3verre9));
        arrayList3.add((TextView) findViewById(R.id.S3verre10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((TextView) it.next()).setVisibility(View.GONE);
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            ((TextView) it2.next()).setVisibility(View.GONE);
        }
        Iterator it3 = arrayList4.iterator();
        while (it3.hasNext()) {
            ((TextView) it3.next()).setVisibility(View.GONE);
        }
        Iterator it4 = arrayList3.iterator();
        while (it4.hasNext()) {
            ((TextView) it4.next()).setVisibility(View.GONE);
        }
        Collections.sort(this.listRouge);
        for (int i = 0; i < this.listRouge.size() && i < 10; i += 1) {
            VinVerre vinVerre = this.listRouge.get(i);
            TextView textView = (TextView) arrayList.get(i);
            TextView textView2 = (TextView) arrayList2.get(i);
            TextView textView3 = (TextView) arrayList4.get(i);
            TextView textView4 = (TextView) arrayList3.get(i);
            textView.setVisibility(View.VISIBLE);
            textView4.setVisibility(View.VISIBLE);
            textView4.setText(vinVerre.getPrix1ToString() + " €");
            if (vinVerre.getPrix75() != 0.0d) {
                textView2.setVisibility(View.VISIBLE);
                textView2.setText(vinVerre.getPrix75ToString() + " €");
            } else {
                textView2.setVisibility(View.GONE);
            }
            if (vinVerre.getPrix45() != 0.0d) {
                textView3.setVisibility(View.VISIBLE);
                textView3.setText(vinVerre.getPrix45ToString() + " €");
            } else {
                textView3.setVisibility(View.GONE);
            }
            String nom = vinVerre.getNom();
            if (vinVerre.getMillesime() != 0) {
                nom = nom + " " + vinVerre.getMillesime();
            }
            if (!vinVerre.getVigneron().equals("zzz")) {
                nom = nom + " (" + vinVerre.getVigneron() + ")";
            }
            textView.setText(nom);
            if (!vinVerre.isDispo()) {
                crossout(textView, textView4, textView3, textView2);
            } else {
                uncrossout(textView, textView4, textView3, textView2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoVins4() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        arrayList.add((TextView) findViewById(R.id.S4label1));
        arrayList.add((TextView) findViewById(R.id.S4label2));
        arrayList.add((TextView) findViewById(R.id.S4label3));
        arrayList.add((TextView) findViewById(R.id.S4label4));
        arrayList.add((TextView) findViewById(R.id.S4label5));
        arrayList.add((TextView) findViewById(R.id.S4label6));
        arrayList2.add((TextView) findViewById(R.id.S4prix1));
        arrayList2.add((TextView) findViewById(R.id.S4prix2));
        arrayList2.add((TextView) findViewById(R.id.S4prix3));
        arrayList2.add((TextView) findViewById(R.id.S4prix4));
        arrayList2.add((TextView) findViewById(R.id.S4prix5));
        arrayList2.add((TextView) findViewById(R.id.S4prix6));
        arrayList4.add((TextView) findViewById(R.id.S4carafe1));
        arrayList4.add((TextView) findViewById(R.id.S4carafe2));
        arrayList4.add((TextView) findViewById(R.id.S4carafe3));
        arrayList4.add((TextView) findViewById(R.id.S4carafe4));
        arrayList4.add((TextView) findViewById(R.id.S4carafe5));
        arrayList4.add((TextView) findViewById(R.id.S4carafe6));
        arrayList3.add((TextView) findViewById(R.id.S4verre1));
        arrayList3.add((TextView) findViewById(R.id.S4verre2));
        arrayList3.add((TextView) findViewById(R.id.S4verre3));
        arrayList3.add((TextView) findViewById(R.id.S4verre4));
        arrayList3.add((TextView) findViewById(R.id.S4verre5));
        arrayList3.add((TextView) findViewById(R.id.S4verre6));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((TextView) it.next()).setVisibility(View.GONE);
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            ((TextView) it2.next()).setVisibility(View.GONE);
        }
        Iterator it3 = arrayList4.iterator();
        while (it3.hasNext()) {
            ((TextView) it3.next()).setVisibility(View.GONE);
        }
        Iterator it4 = arrayList3.iterator();
        while (it4.hasNext()) {
            ((TextView) it4.next()).setVisibility(View.GONE);
        }
        Collections.sort(this.listRose);
        for (int i = 0; i < this.listRose.size() && i < 6; i += 1) {
            VinVerre vinVerre = this.listRose.get(i);
            TextView textView = (TextView) arrayList.get(i);
            TextView textView2 = (TextView) arrayList2.get(i);
            TextView textView3 = (TextView) arrayList4.get(i);
            TextView textView4 = (TextView) arrayList3.get(i);
            textView.setVisibility(View.VISIBLE);
            textView4.setVisibility(View.VISIBLE);
            textView4.setText(vinVerre.getPrix1ToString() + " €");
            if (vinVerre.getPrix75() != 0.0d) {
                textView2.setVisibility(View.VISIBLE);
                textView2.setText(vinVerre.getPrix75ToString() + " €");
            } else {
                textView2.setVisibility(View.GONE);
            }
            if (vinVerre.getPrix45() != 0.0d) {
                textView3.setVisibility(View.VISIBLE);
                textView3.setText(vinVerre.getPrix45ToString() + " €");
            } else {
                textView3.setVisibility(View.GONE);
            }
            String nom = vinVerre.getNom();
            if (vinVerre.getMillesime() != 0) {
                nom = nom + " " + vinVerre.getMillesime();
            }
            if (!vinVerre.getVigneron().equals("zzz")) {
                nom = nom + " (" + vinVerre.getVigneron() + ")";
            }
            textView.setText(nom);
            if (!vinVerre.isDispo()) {
                crossout(textView, textView4, textView3, textView2);
            } else {
                uncrossout(textView, textView4, textView3, textView2);
            }
        }
    }

    private void crossout(TextView nom, TextView prix, TextView verre, TextView carafe) {
        nom.setPaintFlags(nom.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        prix.setPaintFlags(prix.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        verre.setPaintFlags(verre.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        carafe.setPaintFlags(carafe.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void uncrossout(TextView nom, TextView prix, TextView verre, TextView carafe) {
        nom.setPaintFlags(nom.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        prix.setPaintFlags(prix.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        verre.setPaintFlags(verre.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        carafe.setPaintFlags(carafe.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
    }
}