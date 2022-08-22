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
        Log.e("DEBUG", "Dispo entrée");
        int i = 0;
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
        textView24.setVisibility(View.GONE);
        TextView textView26 = (TextView) v.findViewById(R.id.prixE6);
        textView26.setVisibility(View.GONE);
        TextView textView28 = (TextView) v.findViewById(R.id.prixE7);
        textView28.setVisibility(View.GONE);
        TextView textView30 = (TextView) v.findViewById(R.id.prixE8);
        textView30.setVisibility(View.GONE);
        TextView textView32 = (TextView) v.findViewById(R.id.prixE9);
        textView32.setVisibility(View.GONE);
        Collections.sort(this.listEntree);
        while (i < this.listEntree.size() && i < 8) {
            Plat plat = this.listEntree.get(i);
            switch (i) {
                case 0:
                    textView11.setVisibility(View.VISIBLE);
                    textView20.setVisibility(View.VISIBLE);
                    textView11.setText(plat.getNom());
                    textView20.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView11, textView20);
                    } else {
                        uncrossout(textView11, textView20);
                    }
                    i++;
                    break;
                case 1:
                    textView12.setVisibility(View.VISIBLE);
                    textView21.setVisibility(View.VISIBLE);
                    textView12.setText(plat.getNom());
                    textView21.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView12, textView21);
                    } else {
                        uncrossout(textView12, textView21);
                    }
                    i++;
                    break;
                case 2:
                    textView13.setVisibility(View.VISIBLE);
                    textView22.setVisibility(View.VISIBLE);
                    textView13.setText(plat.getNom());
                    textView22.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView13, textView22);
                    } else {
                        uncrossout(textView13, textView22);
                    }
                    i++;
                    break;
                case 3:
                    textView14.setVisibility(View.VISIBLE);
                    textView23.setVisibility(View.VISIBLE);
                    textView14.setText(plat.getNom());
                    textView23.setText(plat.getPrixCarteString() + " euros");
                    if (plat.isDispo()) {
                        uncrossout(textView14, textView23);
                    } else {
                        crossout(textView14, textView23);

                    }
                    i++;
                    break;
                case 4:
                    textView15.setVisibility(View.VISIBLE);
                    textView24.setVisibility(View.VISIBLE);
                    textView15.setText(plat.getNom());
                    textView24.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView15, textView24);
                    } else {
                        uncrossout(textView15, textView24);
                    }
                    i++;
                    break;
                case 5:
                    textView16.setVisibility(View.VISIBLE);
                    textView26.setVisibility(View.VISIBLE);
                    textView16.setText(plat.getNom());
                    textView26.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView16, textView26);
                    } else {
                        uncrossout(textView16, textView26);
                    }
                    i++;
                    break;
                case 6:
                    textView17.setVisibility(View.VISIBLE);
                    textView28.setVisibility(View.VISIBLE);
                    textView17.setText(plat.getNom());
                    textView28.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView17, textView28);
                    } else {
                        uncrossout(textView17, textView28);
                    }
                    i++;
                    break;
                case 7:
                    textView18.setVisibility(View.VISIBLE);
                    textView30.setVisibility(View.VISIBLE);
                    textView18.setText(plat.getNom());
                    textView30.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView18, textView30);
                    } else {
                        uncrossout(textView18, textView30);
                    }
                    i++;
                    break;
                case 8:
                    textView19.setVisibility(View.VISIBLE);
                    textView32.setVisibility(View.VISIBLE);
                    textView19.setText(plat.getNom());
                    textView32.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView19, textView32);
                    } else {
                        uncrossout(textView19, textView32);
                    }
                    i++;
                    break;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoPlat(View v) {
        Log.e("DEBUG", "Dispo plat");
        int i = 0;
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
        textView33.setVisibility(View.GONE);
        TextView textView35 = (TextView) v.findViewById(R.id.prixP2);
        textView35.setVisibility(View.GONE);
        TextView textView37 = (TextView) v.findViewById(R.id.prixP3);
        textView37.setVisibility(View.GONE);
        TextView textView39 = (TextView) v.findViewById(R.id.prixP4);
        textView39.setVisibility(View.GONE);
        TextView textView41 = (TextView) v.findViewById(R.id.prixP5);
        textView41.setVisibility(View.GONE);
        TextView textView43 = (TextView) v.findViewById(R.id.prixP6);
        textView43.setVisibility(View.GONE);
        TextView textView45 = (TextView) v.findViewById(R.id.prixP7);
        textView45.setVisibility(View.GONE);
        TextView textView47 = (TextView) v.findViewById(R.id.prixP8);
        textView47.setVisibility(View.GONE);
        TextView textView49 = (TextView) v.findViewById(R.id.prixP9);
        textView49.setVisibility(View.GONE);
        TextView textView51 = (TextView) v.findViewById(R.id.prixP10);
        textView51.setVisibility(View.GONE);
        TextView textView53 = (TextView) v.findViewById(R.id.prixP11);
        textView53.setVisibility(View.GONE);
        TextView textView55 = (TextView) v.findViewById(R.id.prixP12);
        textView55.setVisibility(View.GONE);
        TextView textView57 = (TextView) v.findViewById(R.id.prixP13);
        textView57.setVisibility(View.GONE);
        Collections.sort(this.listPlat);
        while (i < this.listPlat.size() && i < 12) {
            Plat plat = this.listPlat.get(i);

            switch (i) {
                case 0:
                    textView20.setVisibility(View.VISIBLE);
                    textView33.setVisibility(View.VISIBLE);
                    textView20.setText(plat.getNom());
                    textView33.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView20, textView33);
                    } else {
                        uncrossout(textView20, textView33);
                    }
                    i++;
                    break;
                case 1:

                    textView21.setVisibility(View.VISIBLE);
                    textView35.setVisibility(View.VISIBLE);
                    textView21.setText(plat.getNom());
                    textView35.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView21, textView35);
                    } else {
                        uncrossout(textView21, textView35);
                    }
                    i++;
                    break;
                case 2:

                    textView22.setVisibility(View.VISIBLE);
                    textView37.setVisibility(View.VISIBLE);
                    textView22.setText(plat.getNom());
                    textView37.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView22, textView37);
                    } else {
                        uncrossout(textView22, textView37);
                    }
                    i++;
                    break;
                case 3:

                    textView23.setVisibility(View.VISIBLE);
                    textView39.setVisibility(View.VISIBLE);
                    textView23.setText(plat.getNom());
                    textView39.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView23, textView39);
                    } else {
                        uncrossout(textView23, textView39);
                    }
                    i++;
                    break;
                case 4:

                    textView24.setVisibility(View.VISIBLE);
                    textView41.setVisibility(View.VISIBLE);
                    textView24.setText(plat.getNom());
                    textView41.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView24, textView41);
                    } else {
                        uncrossout(textView24, textView41);
                    }
                    i++;
                    break;
                case 5:

                    textView25.setVisibility(View.VISIBLE);
                    textView43.setVisibility(View.VISIBLE);
                    textView25.setText(plat.getNom());
                    textView43.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView25, textView43);
                    } else {
                        uncrossout(textView25, textView43);
                    }
                    i++;
                    break;
                case 6:

                    textView26.setVisibility(View.VISIBLE);
                    textView45.setVisibility(View.VISIBLE);
                    textView26.setText(plat.getNom());
                    textView45.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView26, textView45);
                    } else {
                        uncrossout(textView26, textView45);
                    }
                    i++;
                    break;
                case 7:

                    textView27.setVisibility(View.VISIBLE);
                    textView47.setVisibility(View.VISIBLE);
                    textView27.setText(plat.getNom());
                    textView47.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView27, textView47);
                    } else {
                        uncrossout(textView27, textView47);
                    }
                    i++;
                    break;
                case 8:

                    textView28.setVisibility(View.VISIBLE);
                    textView49.setVisibility(View.VISIBLE);
                    textView28.setText(plat.getNom());
                    textView49.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView28, textView49);
                    } else {
                        uncrossout(textView28, textView49);
                    }
                    i++;
                    break;
                case 9:

                    textView29.setVisibility(View.VISIBLE);
                    textView51.setVisibility(View.VISIBLE);
                    textView29.setText(plat.getNom());
                    textView51.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView29, textView51);
                    } else {
                        uncrossout(textView29, textView51);
                    }
                    i++;
                    break;
                case 10:

                    textView30.setVisibility(View.VISIBLE);
                    textView53.setVisibility(View.VISIBLE);
                    textView30.setText(plat.getNom());
                    textView53.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView30, textView53);
                    } else {
                        uncrossout(textView30, textView53);
                    }
                    i++;
                    break;
                case 11:

                    textView31.setVisibility(View.VISIBLE);
                    textView55.setVisibility(View.VISIBLE);
                    textView31.setText(plat.getNom());
                    textView55.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView31, textView55);
                    } else {
                        uncrossout(textView31, textView55);
                    }
                    i++;
                    break;
                case 12:

                    textView32.setVisibility(View.VISIBLE);
                    textView57.setVisibility(View.VISIBLE);
                    textView32.setText(plat.getNom());
                    textView57.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView32, textView57);
                    } else {
                        uncrossout(textView32, textView57);
                    }
                    i++;
                    break;
                default:
                    return;
            }
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoDessert(View v) {
        Log.e("DEBUG", "Dispo dessert");
        int i=0;
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
        while (i < this.listDessert.size() && i < 6) {
            Plat plat = this.listDessert.get(i);
            TextView textView22 = textView7;
            switch (i) {
                case 0:
                    textView7.setVisibility(View.VISIBLE);
                    textView14.setVisibility(View.VISIBLE);
                    textView7.setText(plat.getNom());
                    textView14.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView7, textView14);
                    } else {
                        uncrossout(textView7, textView14);
                    }
                    i++;
                    break;
                case 1:
                    textView8.setVisibility(View.VISIBLE);
                    textView15.setVisibility(View.VISIBLE);
                    textView8.setText(plat.getNom());
                    textView15.setText(plat.getPrixCarteString() + " euros");
                    if (plat.isDispo()) {
                        uncrossout(textView8, textView15);
                    } else {
                        crossout(textView8, textView15);
                    }
                    i++;
                    break;
                case 2:
                    textView9.setVisibility(View.VISIBLE);
                    textView16.setVisibility(View.VISIBLE);
                    textView9.setText(plat.getNom());
                    textView16.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView9, textView16);
                    } else {
                        uncrossout(textView9, textView16);
                    }
                    i++;
                    break;
                case 3:
                    textView10.setVisibility(View.VISIBLE);
                    textView17.setVisibility(View.VISIBLE);
                    textView10.setText(plat.getNom());
                    textView17.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView10, textView17);
                    } else {
                        uncrossout(textView10, textView17);
                    }
                    i++;
                    break;
                case 4:
                    textView11.setVisibility(View.VISIBLE);
                    textView18.setVisibility(View.VISIBLE);
                    textView11.setText(plat.getNom());
                    textView18.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView11, textView18);
                    } else {
                        uncrossout(textView11, textView18);
                    }
                    i++;
                    break;
                case 5:
                    textView12.setVisibility(View.VISIBLE);
                    textView19.setVisibility(View.VISIBLE);
                    textView12.setText(plat.getNom());
                    textView19.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView12, textView19);
                    } else {
                        uncrossout(textView12, textView19);
                    }
                    i++;
                    break;
                case 6:
                    textView13.setVisibility(View.VISIBLE);
                    textView20.setVisibility(View.VISIBLE);
                    textView13.setText(plat.getNom());
                    textView20.setText(plat.getPrixCarteString() + " euros");
                    if (!plat.isDispo()) {
                        crossout(textView13, textView20);
                    } else {
                        uncrossout(textView13, textView20);
                    }
                    i++;
                    break;
                default:
                    return;
            }
            i++;
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