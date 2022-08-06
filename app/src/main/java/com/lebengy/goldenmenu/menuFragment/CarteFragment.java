package com.lebengy.goldenmenu.menuFragment;

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
import com.lebengy.goldenmenu.R;
import com.lebengy.goldenmenu.model.Plat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: base/dex/classes2.dex */
public class CarteFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Plat> listDessert;
    private List<Plat> listEntree;
    private List<Plat> listPlat;
    private String mParam1;
    private String mParam2;

    public static CarteFragment newInstance(String param1, String param2) {
        CarteFragment carteFragment = new CarteFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, param1);
        bundle.putString(ARG_PARAM2, param2);
        carteFragment.setArguments(bundle);
        return carteFragment;
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
        View inflate = inflater.inflate(R.layout.fragment_carte, container, false);
        this.listEntree = new ArrayList();
        this.listPlat = new ArrayList();
        this.listDessert = new ArrayList();
        fetchPlats(inflate);
        return inflate;
    }

    public void fetchPlats(final View v) {
        CollectionReference collection = this.db.collection("Entree");
        Integer valueOf = Integer.valueOf((int) 1);
        collection.whereGreaterThan("aLaCarte", valueOf).addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.menuFragment.CarteFragment.1
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                CarteFragment.this.listEntree = new ArrayList();
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    CarteFragment.this.listEntree.add(CarteFragment.this.unserializePlat(it.next()));
                }
                Log.e("DEBUG", "On a fini");
                CarteFragment.this.dispoEntree(v);
            }
        });
        this.db.collection("Plat").whereGreaterThan("aLaCarte", valueOf).addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.menuFragment.CarteFragment.2
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                CarteFragment.this.listPlat = new ArrayList();
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    CarteFragment.this.listPlat.add(CarteFragment.this.unserializePlat(it.next()));
                }
            }
        });
        this.db.collection("Formule").whereEqualTo("aLaCarte", (Object) true).addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.menuFragment.CarteFragment.3
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    CarteFragment.this.listPlat.add(CarteFragment.this.convertToPlat(it.next()));
                }
                CarteFragment.this.dispoPlat(v);
            }
        });
        this.db.collection("Dessert").whereGreaterThanOrEqualTo("aLaCarte", valueOf).addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.menuFragment.CarteFragment.4
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                CarteFragment.this.listDessert = new ArrayList();
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    CarteFragment.this.listDessert.add(CarteFragment.this.unserializePlat(it.next()));
                }
                CarteFragment.this.dispoDessert(v);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Plat unserializePlat(QueryDocumentSnapshot doc) {
        Plat plat = new Plat();
        plat.setNom(doc.getString("nom"));
        plat.setDispo(doc.getBoolean("dispo").booleanValue());
        plat.setPrixCarte(doc.getDouble("prixCarte").doubleValue());
        plat.setVegan(doc.getBoolean("vegan").booleanValue());
        plat.setGlutenFree(doc.getBoolean("glutenFree").booleanValue());
        return plat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Plat convertToPlat(QueryDocumentSnapshot doc) {
        Plat plat = new Plat();
        plat.setNom(doc.getString("nomPlat"));
        plat.setDispo(doc.getBoolean("dispo").booleanValue());
        plat.setPrixCarte(doc.getDouble("prixCarte").doubleValue());
        plat.setVegan(doc.getBoolean("vegan").booleanValue());
        plat.setGlutenFree(doc.getBoolean("glutenFree").booleanValue());
        return plat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoEntree(View v) {
        TextView textView;
        int i;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        TextView textView7;
        TextView textView8;
        TextView textView9;
        TextView textView10;
        TextView textView11 = (TextView) v.findViewById(R.id.entree1);
        textView11.setVisibility(View.GONE);
        TextView textView12 = (TextView) v.findViewById(R.id.entree2);
        textView12.setVisibility(View.GONE);
        TextView textView13 = (TextView) v.findViewById(R.id.entree3);
        textView13.setVisibility(View.GONE);
        TextView textView14 = (TextView) v.findViewById(R.id.entree4);
        textView14.setVisibility(View.GONE);
        TextView textView15 = (TextView) v.findViewById(R.id.entree5);
        textView15.setVisibility(View.GONE);
        TextView textView16 = (TextView) v.findViewById(R.id.entree6);
        textView16.setVisibility(View.GONE);
        TextView textView17 = (TextView) v.findViewById(R.id.entree7);
        textView17.setVisibility(View.GONE);
        TextView textView18 = (TextView) v.findViewById(R.id.entree8);
        textView18.setVisibility(View.GONE);
        TextView textView19 = (TextView) v.findViewById(R.id.entree9);
        textView19.setVisibility(View.GONE);
        TextView textView20 = (TextView) v.findViewById(R.id.prixE1);
        textView20.setVisibility(View.GONE);
        TextView textView21 = (TextView) v.findViewById(R.id.prixE2);
        textView21.setVisibility(View.GONE);
        TextView textView22 = (TextView) v.findViewById(R.id.prixE3);
        textView22.setVisibility(View.GONE);
        TextView textView23 = (TextView) v.findViewById(R.id.prixE4);
        textView23.setVisibility(View.GONE);
        TextView textView24 = (TextView) v.findViewById(R.id.prixE5);
        TextView textView25 = textView20;
        textView24.setVisibility(View.GONE);
        TextView textView26 = (TextView) v.findViewById(R.id.prixE6);
        TextView textView27 = textView11;
        textView26.setVisibility(View.GONE);
        TextView textView28 = (TextView) v.findViewById(R.id.prixE7);
        TextView textView29 = textView21;
        textView28.setVisibility(View.GONE);
        TextView textView30 = (TextView) v.findViewById(R.id.prixE8);
        TextView textView31 = textView12;
        textView30.setVisibility(View.GONE);
        TextView textView32 = (TextView) v.findViewById(R.id.prixE9);
        textView32.setVisibility(View.GONE);
        Collections.sort(this.listEntree);
        TextView textView33 = textView22;
        int i2 = 0;
        while (i2 < this.listEntree.size() && i2 < 9) {
            Plat plat = this.listEntree.get(i2);
            TextView textView34 = textView13;
            switch (i2) {
                case 0:
                    textView = textView32;
                    i = i2;
                    TextView textView35 = textView29;
                    TextView textView36 = textView31;
                    textView2 = textView34;
                    textView3 = textView28;
                    textView4 = textView33;
                    textView5 = textView27;
                    textView6 = textView24;
                    textView5.setVisibility(View.VISIBLE);
                    textView7 = textView35;
                    textView8 = textView25;
                    textView8.setVisibility(View.VISIBLE);
                    textView5.setText(plat.getNom());
                    textView9 = textView36;
                    textView8.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView5, textView8);
                    } else {
                        uncrossout(textView5, textView8);
                        continue;
                    }
                    i2 = i + 1;
                    textView24 = textView6;
                    textView33 = textView4;
                    textView13 = textView2;
                    textView31 = textView9;
                    textView29 = textView7;
                    textView25 = textView8;
                    textView27 = textView5;
                    textView28 = textView3;
                    textView32 = textView;
                case 1:
                    textView = textView32;
                    i = i2;
                    TextView textView37 = textView31;
                    textView3 = textView28;
                    TextView textView38 = textView33;
                    textView6 = textView24;
                    textView37.setVisibility(View.VISIBLE);
                    textView2 = textView34;
                    TextView textView39 = textView29;
                    textView39.setVisibility(View.VISIBLE);
                    textView37.setText(plat.getNom());
                    textView4 = textView38;
                    textView39.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView37, textView39);
                    } else {
                        uncrossout(textView37, textView39);
                    }
                    textView5 = textView27;
                    textView7 = textView39;
                    textView8 = textView25;
                    textView9 = textView37;
                    //continue;
                    i2 = i + 1;
                    textView24 = textView6;
                    textView33 = textView4;
                    textView13 = textView2;
                    textView31 = textView9;
                    textView29 = textView7;
                    textView25 = textView8;
                    textView27 = textView5;
                    textView28 = textView3;
                    textView32 = textView;
                case 2:
                    textView = textView32;
                    i = i2;
                    textView34.setVisibility(View.VISIBLE);
                    textView3 = textView28;
                    TextView textView40 = textView33;
                    textView40.setVisibility(View.VISIBLE);
                    textView34.setText(plat.getNom());
                    textView6 = textView24;
                    textView40.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView34, textView40);
                    } else {
                        uncrossout(textView34, textView40);
                    }
                    TextView textView41 = textView31;
                    textView2 = textView34;
                    textView8 = textView25;
                    textView9 = textView41;
                    TextView textView42 = textView29;
                    textView4 = textView40;
                    textView5 = textView27;
                    textView7 = textView42;
                    //continue;
                    i2 = i + 1;
                    textView24 = textView6;
                    textView33 = textView4;
                    textView13 = textView2;
                    textView31 = textView9;
                    textView29 = textView7;
                    textView25 = textView8;
                    textView27 = textView5;
                    textView28 = textView3;
                    textView32 = textView;
                case 3:
                    i = i2;
                    textView14.setVisibility(View.VISIBLE);
                    textView23.setVisibility(View.VISIBLE);
                    textView14.setText(plat.getNom());
                    textView = textView32;
                    textView23.setText(plat.getPrixCarteString() + " euros");
                    if (plat.isDispo()) {
                        uncrossout(textView14, textView23);
                        break;
                    } else {
                        crossout(textView14, textView23);
                        break;
                    }
                case 4:
                    i = i2;
                    textView10 = textView23;
                    textView15.setVisibility(View.VISIBLE);
                    textView24.setVisibility(View.VISIBLE);
                    textView15.setText(plat.getNom());
                    textView24.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView15, textView24);
                    } else {
                        uncrossout(textView15, textView24);
                    }
                    textView23 = textView10;
                    textView = textView32;
                    break;
                case 5:
                    i = i2;
                    textView10 = textView23;
                    textView16.setVisibility(View.VISIBLE);
                    textView26.setVisibility(View.VISIBLE);
                    textView16.setText(plat.getNom());
                    textView26.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView16, textView26);
                    } else {
                        uncrossout(textView16, textView26);
                    }
                    textView23 = textView10;
                    textView = textView32;
                    break;
                case 6:
                    i = i2;
                    textView10 = textView23;
                    textView17.setVisibility(View.VISIBLE);
                    textView28.setVisibility(View.VISIBLE);
                    textView17.setText(plat.getNom());
                    textView28.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView17, textView28);
                    } else {
                        uncrossout(textView17, textView28);
                    }
                    textView23 = textView10;
                    textView = textView32;
                    break;
                case 7:
                    i = i2;
                    textView10 = textView23;
                    textView18.setVisibility(View.VISIBLE);
                    textView30.setVisibility(View.VISIBLE);
                    textView18.setText(plat.getNom());
                    textView30.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView18, textView30);
                    } else {
                        uncrossout(textView18, textView30);
                    }
                    textView23 = textView10;
                    textView = textView32;
                    break;
                case 8:
                    i = i2;
                    textView19.setVisibility(View.VISIBLE);
                    textView32.setVisibility(View.VISIBLE);
                    textView19.setText(plat.getNom());
                    textView10 = textView23;
                    textView32.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView19, textView32);
                    } else {
                        uncrossout(textView19, textView32);
                    }
                    textView23 = textView10;
                    textView = textView32;
                    break;
                default:
                    return;
            }
            textView8 = textView25;
            textView9 = textView31;
            textView2 = textView34;
            textView3 = textView28;
            textView5 = textView27;
            textView7 = textView29;
            textView4 = textView33;
            textView6 = textView24;
            i2 = i + 1;
            textView24 = textView6;
            textView33 = textView4;
            textView13 = textView2;
            textView31 = textView9;
            textView29 = textView7;
            textView25 = textView8;
            textView27 = textView5;
            textView28 = textView3;
            textView32 = textView;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoPlat(View v) {
        TextView textView;
        int i;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        TextView textView7;
        TextView textView8;
        TextView textView9;
        TextView textView10;
        TextView textView11;
        TextView textView12;
        TextView textView13;
        TextView textView14;
        TextView textView15;
        TextView textView16;
        TextView textView17;
        TextView textView18;
        TextView textView19;
        TextView textView20 = (TextView) v.findViewById(R.id.plat1);
        textView20.setVisibility(View.GONE);
        TextView textView21 = (TextView) v.findViewById(R.id.plat2);
        textView21.setVisibility(View.GONE);
        TextView textView22 = (TextView) v.findViewById(R.id.plat3);
        textView22.setVisibility(View.GONE);
        TextView textView23 = (TextView) v.findViewById(R.id.plat4);
        textView23.setVisibility(View.GONE);
        TextView textView24 = (TextView) v.findViewById(R.id.plat5);
        textView24.setVisibility(View.GONE);
        TextView textView25 = (TextView) v.findViewById(R.id.plat6);
        textView25.setVisibility(View.GONE);
        TextView textView26 = (TextView) v.findViewById(R.id.plat7);
        textView26.setVisibility(View.GONE);
        TextView textView27 = (TextView) v.findViewById(R.id.plat8);
        textView27.setVisibility(View.GONE);
        TextView textView28 = (TextView) v.findViewById(R.id.plat9);
        textView28.setVisibility(View.GONE);
        TextView textView29 = (TextView) v.findViewById(R.id.plat10);
        textView29.setVisibility(View.GONE);
        TextView textView30 = (TextView) v.findViewById(R.id.plat11);
        textView30.setVisibility(View.GONE);
        TextView textView31 = (TextView) v.findViewById(R.id.plat12);
        textView31.setVisibility(View.GONE);
        TextView textView32 = (TextView) v.findViewById(R.id.plat13);
        textView32.setVisibility(View.GONE);
        TextView textView33 = (TextView) v.findViewById(R.id.prixP1);
        TextView textView34 = textView20;
        textView33.setVisibility(View.GONE);
        TextView textView35 = (TextView) v.findViewById(R.id.prixP2);
        TextView textView36 = textView33;
        textView35.setVisibility(View.GONE);
        TextView textView37 = (TextView) v.findViewById(R.id.prixP3);
        TextView textView38 = textView35;
        textView37.setVisibility(View.GONE);
        TextView textView39 = (TextView) v.findViewById(R.id.prixP4);
        TextView textView40 = textView21;
        textView39.setVisibility(View.GONE);
        TextView textView41 = (TextView) v.findViewById(R.id.prixP5);
        TextView textView42 = textView37;
        textView41.setVisibility(View.GONE);
        TextView textView43 = (TextView) v.findViewById(R.id.prixP6);
        TextView textView44 = textView22;
        textView43.setVisibility(View.GONE);
        TextView textView45 = (TextView) v.findViewById(R.id.prixP7);
        TextView textView46 = textView39;
        textView45.setVisibility(View.GONE);
        TextView textView47 = (TextView) v.findViewById(R.id.prixP8);
        TextView textView48 = textView23;
        textView47.setVisibility(View.GONE);
        TextView textView49 = (TextView) v.findViewById(R.id.prixP9);
        TextView textView50 = textView41;
        textView49.setVisibility(View.GONE);
        TextView textView51 = (TextView) v.findViewById(R.id.prixP10);
        TextView textView52 = textView24;
        textView51.setVisibility(View.GONE);
        TextView textView53 = (TextView) v.findViewById(R.id.prixP11);
        TextView textView54 = textView43;
        textView53.setVisibility(View.GONE);
        TextView textView55 = (TextView) v.findViewById(R.id.prixP12);
        TextView textView56 = textView25;
        textView55.setVisibility(View.GONE);
        TextView textView57 = (TextView) v.findViewById(R.id.prixP13);
        textView57.setVisibility(View.GONE);
        Collections.sort(this.listPlat);
        TextView textView58 = textView45;
        int i2 = 0;
        while (i2 < this.listPlat.size() && i2 < 9) {
            Plat plat = this.listPlat.get(i2);
            TextView textView59 = textView26;
            switch (i2) {
                case 0:
                    textView = textView57;
                    i = i2;
                    TextView textView60 = textView38;
                    textView2 = textView44;
                    textView3 = textView46;
                    textView4 = textView52;
                    textView5 = textView54;
                    textView6 = textView47;
                    textView7 = textView58;
                    textView8 = textView34;
                    textView9 = textView51;
                    TextView textView61 = textView40;
                    textView10 = textView42;
                    textView11 = textView48;
                    textView12 = textView50;
                    textView13 = textView56;
                    textView14 = textView59;
                    textView15 = textView55;
                    textView8.setVisibility(View.VISIBLE);
                    textView16 = textView60;
                    textView17 = textView36;
                    textView17.setVisibility(View.VISIBLE);
                    textView8.setText(plat.getNom());
                    textView18 = textView61;
                    textView17.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView8, textView17);
                    } else {
                        uncrossout(textView8, textView17);
                        continue;
                    }
                    textView51 = textView9;
                    textView58 = textView7;
                    textView26 = textView14;
                    textView56 = textView13;
                    textView54 = textView5;
                    textView52 = textView4;
                    textView50 = textView12;
                    textView48 = textView11;
                    textView46 = textView3;
                    textView44 = textView2;
                    textView42 = textView10;
                    textView38 = textView16;
                    textView40 = textView18;
                    textView36 = textView17;
                    textView34 = textView8;
                    textView47 = textView6;
                    textView57 = textView;
                    i2 = i + 1;
                    textView55 = textView15;
                case 1:
                    textView = textView57;
                    i = i2;
                    TextView textView62 = textView42;
                    textView11 = textView48;
                    textView12 = textView50;
                    textView13 = textView56;
                    textView14 = textView59;
                    textView6 = textView47;
                    textView15 = textView55;
                    TextView textView63 = textView44;
                    textView3 = textView46;
                    textView4 = textView52;
                    textView5 = textView54;
                    textView7 = textView58;
                    textView9 = textView51;
                    TextView textView64 = textView40;
                    textView64.setVisibility(View.VISIBLE);
                    textView10 = textView62;
                    TextView textView65 = textView38;
                    textView65.setVisibility(View.VISIBLE);
                    textView64.setText(plat.getNom());
                    textView2 = textView63;
                    textView65.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView64, textView65);
                    } else {
                        uncrossout(textView64, textView65);
                    }
                    textView8 = textView34;
                    textView16 = textView65;
                    textView17 = textView36;
                    textView18 = textView64;
                    //continue;
                    textView51 = textView9;
                    textView58 = textView7;
                    textView26 = textView14;
                    textView56 = textView13;
                    textView54 = textView5;
                    textView52 = textView4;
                    textView50 = textView12;
                    textView48 = textView11;
                    textView46 = textView3;
                    textView44 = textView2;
                    textView42 = textView10;
                    textView38 = textView16;
                    textView40 = textView18;
                    textView36 = textView17;
                    textView34 = textView8;
                    textView47 = textView6;
                    textView57 = textView;
                    i2 = i + 1;
                    textView55 = textView15;
                case 2:
                    textView = textView57;
                    i = i2;
                    TextView textView66 = textView46;
                    textView4 = textView52;
                    textView5 = textView54;
                    textView6 = textView47;
                    textView7 = textView58;
                    TextView textView67 = textView48;
                    textView12 = textView50;
                    textView13 = textView56;
                    textView14 = textView59;
                    textView15 = textView55;
                    textView9 = textView51;
                    TextView textView68 = textView44;
                    textView68.setVisibility(View.VISIBLE);
                    textView3 = textView66;
                    TextView textView69 = textView42;
                    textView69.setVisibility(View.VISIBLE);
                    textView68.setText(plat.getNom());
                    textView11 = textView67;
                    textView69.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView68, textView69);
                    } else {
                        uncrossout(textView68, textView69);
                    }
                    textView8 = textView34;
                    textView16 = textView38;
                    textView2 = textView68;
                    TextView textView70 = textView40;
                    textView10 = textView69;
                    textView17 = textView36;
                    textView18 = textView70;
                    //continue;
                    textView51 = textView9;
                    textView58 = textView7;
                    textView26 = textView14;
                    textView56 = textView13;
                    textView54 = textView5;
                    textView52 = textView4;
                    textView50 = textView12;
                    textView48 = textView11;
                    textView46 = textView3;
                    textView44 = textView2;
                    textView42 = textView10;
                    textView38 = textView16;
                    textView40 = textView18;
                    textView36 = textView17;
                    textView34 = textView8;
                    textView47 = textView6;
                    textView57 = textView;
                    i2 = i + 1;
                    textView55 = textView15;
                case 3:
                    textView = textView57;
                    i = i2;
                    TextView textView71 = textView50;
                    textView13 = textView56;
                    textView14 = textView59;
                    textView6 = textView47;
                    textView15 = textView55;
                    TextView textView72 = textView48;
                    TextView textView73 = textView52;
                    textView5 = textView54;
                    textView7 = textView58;
                    textView9 = textView51;
                    textView72.setVisibility(View.VISIBLE);
                    textView12 = textView71;
                    TextView textView74 = textView46;
                    textView74.setVisibility(View.VISIBLE);
                    textView72.setText(plat.getNom());
                    textView4 = textView73;
                    textView74.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView72, textView74);
                    } else {
                        uncrossout(textView72, textView74);
                    }
                    TextView textView75 = textView44;
                    textView3 = textView74;
                    textView17 = textView36;
                    textView18 = textView40;
                    textView10 = textView42;
                    textView11 = textView72;
                    textView8 = textView34;
                    textView16 = textView38;
                    textView2 = textView75;
                    //continue;
                    textView51 = textView9;
                    textView58 = textView7;
                    textView26 = textView14;
                    textView56 = textView13;
                    textView54 = textView5;
                    textView52 = textView4;
                    textView50 = textView12;
                    textView48 = textView11;
                    textView46 = textView3;
                    textView44 = textView2;
                    textView42 = textView10;
                    textView38 = textView16;
                    textView40 = textView18;
                    textView36 = textView17;
                    textView34 = textView8;
                    textView47 = textView6;
                    textView57 = textView;
                    i2 = i + 1;
                    textView55 = textView15;
                case 4:
                    textView = textView57;
                    i = i2;
                    TextView textView76 = textView54;
                    textView6 = textView47;
                    textView7 = textView58;
                    TextView textView77 = textView56;
                    textView14 = textView59;
                    textView15 = textView55;
                    textView9 = textView51;
                    TextView textView78 = textView52;
                    textView78.setVisibility(View.VISIBLE);
                    textView5 = textView76;
                    TextView textView79 = textView50;
                    textView79.setVisibility(View.VISIBLE);
                    textView78.setText(plat.getNom());
                    textView13 = textView77;
                    textView79.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView78, textView79);
                    } else {
                        uncrossout(textView78, textView79);
                    }
                    textView8 = textView34;
                    textView16 = textView38;
                    textView2 = textView44;
                    textView3 = textView46;
                    textView4 = textView78;
                    TextView textView80 = textView48;
                    textView12 = textView79;
                    textView17 = textView36;
                    textView18 = textView40;
                    textView10 = textView42;
                    textView11 = textView80;
                    //continue;
                    textView51 = textView9;
                    textView58 = textView7;
                    textView26 = textView14;
                    textView56 = textView13;
                    textView54 = textView5;
                    textView52 = textView4;
                    textView50 = textView12;
                    textView48 = textView11;
                    textView46 = textView3;
                    textView44 = textView2;
                    textView42 = textView10;
                    textView38 = textView16;
                    textView40 = textView18;
                    textView36 = textView17;
                    textView34 = textView8;
                    textView47 = textView6;
                    textView57 = textView;
                    i2 = i + 1;
                    textView55 = textView15;
                case 5:
                    textView = textView57;
                    i = i2;
                    textView6 = textView47;
                    textView15 = textView55;
                    TextView textView81 = textView58;
                    TextView textView82 = textView56;
                    textView9 = textView51;
                    textView82.setVisibility(View.VISIBLE);
                    textView14 = textView59;
                    TextView textView83 = textView54;
                    textView83.setVisibility(View.VISIBLE);
                    textView82.setText(plat.getNom());
                    textView7 = textView81;
                    textView83.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView82, textView83);
                    } else {
                        uncrossout(textView82, textView83);
                    }
                    TextView textView84 = textView52;
                    textView5 = textView83;
                    textView17 = textView36;
                    textView18 = textView40;
                    textView10 = textView42;
                    textView11 = textView48;
                    textView12 = textView50;
                    textView13 = textView82;
                    textView8 = textView34;
                    textView16 = textView38;
                    textView2 = textView44;
                    textView3 = textView46;
                    textView4 = textView84;
                    //continue;
                    textView51 = textView9;
                    textView58 = textView7;
                    textView26 = textView14;
                    textView56 = textView13;
                    textView54 = textView5;
                    textView52 = textView4;
                    textView50 = textView12;
                    textView48 = textView11;
                    textView46 = textView3;
                    textView44 = textView2;
                    textView42 = textView10;
                    textView38 = textView16;
                    textView40 = textView18;
                    textView36 = textView17;
                    textView34 = textView8;
                    textView47 = textView6;
                    textView57 = textView;
                    i2 = i + 1;
                    textView55 = textView15;
                case 6:
                    textView = textView57;
                    i = i2;
                    textView6 = textView47;
                    textView59.setVisibility(View.VISIBLE);
                    textView15 = textView55;
                    TextView textView85 = textView58;
                    textView85.setVisibility(View.VISIBLE);
                    textView59.setText(plat.getNom());
                    textView9 = textView51;
                    textView85.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView59, textView85);
                    } else {
                        uncrossout(textView59, textView85);
                    }
                    textView8 = textView34;
                    textView16 = textView38;
                    textView2 = textView44;
                    textView3 = textView46;
                    textView4 = textView52;
                    textView5 = textView54;
                    textView7 = textView85;
                    TextView textView86 = textView56;
                    textView14 = textView59;
                    textView17 = textView36;
                    textView18 = textView40;
                    textView10 = textView42;
                    textView11 = textView48;
                    textView12 = textView50;
                    textView13 = textView86;
                    //continue;
                    textView51 = textView9;
                    textView58 = textView7;
                    textView26 = textView14;
                    textView56 = textView13;
                    textView54 = textView5;
                    textView52 = textView4;
                    textView50 = textView12;
                    textView48 = textView11;
                    textView46 = textView3;
                    textView44 = textView2;
                    textView42 = textView10;
                    textView38 = textView16;
                    textView40 = textView18;
                    textView36 = textView17;
                    textView34 = textView8;
                    textView47 = textView6;
                    textView57 = textView;
                    i2 = i + 1;
                    textView55 = textView15;
                case 7:
                    i = i2;
                    textView27.setVisibility(View.VISIBLE);
                    textView6 = textView47;
                    textView6.setVisibility(View.VISIBLE);
                    textView27.setText(plat.getNom());
                    textView = textView57;
                    textView6.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView27, textView6);
                    } else {
                        uncrossout(textView27, textView6);
                    }
                    textView8 = textView34;
                    textView17 = textView36;
                    textView16 = textView38;
                    textView18 = textView40;
                    textView10 = textView42;
                    textView2 = textView44;
                    textView3 = textView46;
                    textView11 = textView48;
                    textView12 = textView50;
                    textView4 = textView52;
                    textView5 = textView54;
                    textView13 = textView56;
                    textView14 = textView59;
                    textView15 = textView55;
                    textView7 = textView58;
                    break;
                case 8:
                    textView19 = textView47;
                    i = i2;
                    textView28.setVisibility(View.VISIBLE);
                    textView49.setVisibility(View.VISIBLE);
                    textView28.setText(plat.getNom());
                    textView49.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView28, textView49);
                    } else {
                        uncrossout(textView28, textView49);
                    }
                    textView8 = textView34;
                    textView16 = textView38;
                    textView2 = textView44;
                    textView3 = textView46;
                    textView4 = textView52;
                    textView5 = textView54;
                    textView6 = textView19;
                    textView = textView57;
                    textView7 = textView58;
                    textView17 = textView36;
                    textView18 = textView40;
                    textView10 = textView42;
                    textView11 = textView48;
                    textView12 = textView50;
                    textView13 = textView56;
                    textView14 = textView59;
                    textView15 = textView55;
                    break;
                case 9:
                    textView19 = textView47;
                    i = i2;
                    textView29.setVisibility(View.VISIBLE);
                    textView51.setVisibility(View.VISIBLE);
                    textView29.setText(plat.getNom());
                    textView51.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView29, textView51);
                    } else {
                        uncrossout(textView29, textView51);
                    }
                    textView8 = textView34;
                    textView16 = textView38;
                    textView2 = textView44;
                    textView3 = textView46;
                    textView4 = textView52;
                    textView5 = textView54;
                    textView6 = textView19;
                    textView = textView57;
                    textView7 = textView58;
                    textView17 = textView36;
                    textView18 = textView40;
                    textView10 = textView42;
                    textView11 = textView48;
                    textView12 = textView50;
                    textView13 = textView56;
                    textView14 = textView59;
                    textView15 = textView55;
                    break;
                case 10:
                    textView19 = textView47;
                    i = i2;
                    textView30.setVisibility(View.VISIBLE);
                    textView53.setVisibility(View.VISIBLE);
                    textView30.setText(plat.getNom());
                    textView53.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView30, textView53);
                    } else {
                        uncrossout(textView30, textView53);
                    }
                    textView8 = textView34;
                    textView16 = textView38;
                    textView2 = textView44;
                    textView3 = textView46;
                    textView4 = textView52;
                    textView5 = textView54;
                    textView6 = textView19;
                    textView = textView57;
                    textView7 = textView58;
                    textView17 = textView36;
                    textView18 = textView40;
                    textView10 = textView42;
                    textView11 = textView48;
                    textView12 = textView50;
                    textView13 = textView56;
                    textView14 = textView59;
                    textView15 = textView55;
                    break;
                case 11:
                    textView19 = textView47;
                    i = i2;
                    textView31.setVisibility(View.VISIBLE);
                    textView55.setVisibility(View.VISIBLE);
                    textView31.setText(plat.getNom());
                    textView55.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView31, textView55);
                    } else {
                        uncrossout(textView31, textView55);
                    }
                    textView8 = textView34;
                    textView16 = textView38;
                    textView2 = textView44;
                    textView3 = textView46;
                    textView4 = textView52;
                    textView5 = textView54;
                    textView6 = textView19;
                    textView = textView57;
                    textView7 = textView58;
                    textView17 = textView36;
                    textView18 = textView40;
                    textView10 = textView42;
                    textView11 = textView48;
                    textView12 = textView50;
                    textView13 = textView56;
                    textView14 = textView59;
                    textView15 = textView55;
                    break;
                case 12:
                    i = i2;
                    textView32.setVisibility(View.VISIBLE);
                    textView57.setVisibility(View.VISIBLE);
                    textView32.setText(plat.getNom());
                    textView19 = textView47;
                    textView57.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView32, textView57);
                    } else {
                        uncrossout(textView32, textView57);
                    }
                    textView8 = textView34;
                    textView16 = textView38;
                    textView2 = textView44;
                    textView3 = textView46;
                    textView4 = textView52;
                    textView5 = textView54;
                    textView6 = textView19;
                    textView = textView57;
                    textView7 = textView58;
                    textView17 = textView36;
                    textView18 = textView40;
                    textView10 = textView42;
                    textView11 = textView48;
                    textView12 = textView50;
                    textView13 = textView56;
                    textView14 = textView59;
                    textView15 = textView55;
                    break;
                default:
                    return;
            }
            textView9 = textView51;
            textView51 = textView9;
            textView58 = textView7;
            textView26 = textView14;
            textView56 = textView13;
            textView54 = textView5;
            textView52 = textView4;
            textView50 = textView12;
            textView48 = textView11;
            textView46 = textView3;
            textView44 = textView2;
            textView42 = textView10;
            textView38 = textView16;
            textView40 = textView18;
            textView36 = textView17;
            textView34 = textView8;
            textView47 = textView6;
            textView57 = textView;
            i2 = i + 1;
            textView55 = textView15;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoDessert(View v) {
        TextView textView;
        int i;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        TextView textView7 = (TextView) v.findViewById(R.id.dessert1);
        textView7.setVisibility(View.GONE);
        TextView textView8 = (TextView) v.findViewById(R.id.dessert2);
        textView8.setVisibility(View.GONE);
        TextView textView9 = (TextView) v.findViewById(R.id.dessert3);
        textView9.setVisibility(View.GONE);
        TextView textView10 = (TextView) v.findViewById(R.id.dessert4);
        textView10.setVisibility(View.GONE);
        TextView textView11 = (TextView) v.findViewById(R.id.dessert5);
        textView11.setVisibility(View.GONE);
        TextView textView12 = (TextView) v.findViewById(R.id.dessert6);
        textView12.setVisibility(View.GONE);
        TextView textView13 = (TextView) v.findViewById(R.id.dessert7);
        textView13.setVisibility(View.GONE);
        TextView textView14 = (TextView) v.findViewById(R.id.prixD1);
        textView14.setVisibility(View.GONE);
        TextView textView15 = (TextView) v.findViewById(R.id.prixD2);
        textView15.setVisibility(View.GONE);
        TextView textView16 = (TextView) v.findViewById(R.id.prixD3);
        textView16.setVisibility(View.GONE);
        TextView textView17 = (TextView) v.findViewById(R.id.prixD4);
        textView17.setVisibility(View.GONE);
        TextView textView18 = (TextView) v.findViewById(R.id.prixD5);
        textView18.setVisibility(View.GONE);
        TextView textView19 = (TextView) v.findViewById(R.id.prixD6);
        textView19.setVisibility(View.GONE);
        TextView textView20 = (TextView) v.findViewById(R.id.prixD7);
        textView20.setVisibility(View.GONE);
        Collections.sort(this.listDessert);
        TextView textView21 = textView14;
        int i2 = 0;
        while (i2 < this.listDessert.size() && i2 < 9) {
            Plat plat = this.listDessert.get(i2);
            TextView textView22 = textView7;
            switch (i2) {
                case 0:
                    textView = textView20;
                    i = i2;
                    textView2 = textView22;
                    textView2.setVisibility(View.VISIBLE);
                    textView3 = textView8;
                    textView4 = textView21;
                    textView4.setVisibility(View.VISIBLE);
                    textView2.setText(plat.getNom());
                    textView5 = textView9;
                    textView4.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView2, textView4);
                    } else {
                        uncrossout(textView2, textView4);
                        continue;
                    }
                    i2 = i + 1;
                    textView7 = textView2;
                    textView9 = textView5;
                    textView20 = textView;
                    textView21 = textView4;
                    textView8 = textView3;
                case 1:
                    i = i2;
                    textView8.setVisibility(View.VISIBLE);
                    textView15.setVisibility(View.VISIBLE);
                    textView8.setText(plat.getNom());
                    textView = textView20;
                    textView15.setText(plat.getPrixCarteString() + " euros");
                    if (plat.isDispo()) {
                        uncrossout(textView8, textView15);
                        break;
                    } else {
                        crossout(textView8, textView15);
                        break;
                    }
                case 2:
                    i = i2;
                    textView6 = textView15;
                    textView9.setVisibility(View.VISIBLE);
                    textView16.setVisibility(View.VISIBLE);
                    textView9.setText(plat.getNom());
                    textView16.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView9, textView16);
                    } else {
                        uncrossout(textView9, textView16);
                    }
                    textView15 = textView6;
                    textView = textView20;
                    break;
                case 3:
                    i = i2;
                    textView6 = textView15;
                    textView10.setVisibility(View.VISIBLE);
                    textView17.setVisibility(View.VISIBLE);
                    textView10.setText(plat.getNom());
                    textView17.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView10, textView17);
                    } else {
                        uncrossout(textView10, textView17);
                    }
                    textView15 = textView6;
                    textView = textView20;
                    break;
                case 4:
                    i = i2;
                    textView6 = textView15;
                    textView11.setVisibility(View.VISIBLE);
                    textView18.setVisibility(View.VISIBLE);
                    textView11.setText(plat.getNom());
                    textView18.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView11, textView18);
                    } else {
                        uncrossout(textView11, textView18);
                    }
                    textView15 = textView6;
                    textView = textView20;
                    break;
                case 5:
                    i = i2;
                    textView6 = textView15;
                    textView12.setVisibility(View.VISIBLE);
                    textView19.setVisibility(View.VISIBLE);
                    textView12.setText(plat.getNom());
                    textView19.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView12, textView19);
                    } else {
                        uncrossout(textView12, textView19);
                    }
                    textView15 = textView6;
                    textView = textView20;
                    break;
                case 6:
                    i = i2;
                    textView13.setVisibility(View.VISIBLE);
                    textView20.setVisibility(View.VISIBLE);
                    textView13.setText(plat.getNom());
                    textView6 = textView15;
                    textView20.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView13, textView20);
                    } else {
                        uncrossout(textView13, textView20);
                    }
                    textView15 = textView6;
                    textView = textView20;
                    break;
                default:
                    return;
            }
            textView2 = textView22;
            textView3 = textView8;
            textView4 = textView21;
            textView5 = textView9;
            i2 = i + 1;
            textView7 = textView2;
            textView9 = textView5;
            textView20 = textView;
            textView21 = textView4;
            textView8 = textView3;
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

    private void wipeLabel(View v) {
        ((TextView) v.findViewById(R.id.entree1)).setText("");
        ((TextView) v.findViewById(R.id.entree2)).setText("");
        ((TextView) v.findViewById(R.id.entree3)).setText("");
        ((TextView) v.findViewById(R.id.plat1)).setText("");
        ((TextView) v.findViewById(R.id.plat2)).setText("");
        ((TextView) v.findViewById(R.id.plat3)).setText("");
        ((TextView) v.findViewById(R.id.dessert1)).setText("");
        ((TextView) v.findViewById(R.id.dessert2)).setText("");
        ((TextView) v.findViewById(R.id.dessert3)).setText("");
        ((TextView) v.findViewById(R.id.dessert4)).setText("");
        ((TextView) v.findViewById(R.id.dessert5)).setText("");
    }
}