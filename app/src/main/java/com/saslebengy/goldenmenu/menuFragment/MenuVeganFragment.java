/*
 *
 *  * Copyright (c) 2025 Florian Nadin
 *  * All rights reserved.
 *  * Last modified 23/08/2022 09:47
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

package com.saslebengy.goldenmenu.menuFragment;

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
import com.saslebengy.goldenmenu.R;
import com.saslebengy.goldenmenu.model.Plat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: base/dex/classes2.dex */
public class MenuVeganFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Plat> listDessert;
    private List<Plat> listEntree;
    private List<Plat> listPlat;
    private String mParam1;
    private String mParam2;

    public static MenuVeganFragment newInstance(String param1, String param2) {
        MenuVeganFragment menuveganFragment = new MenuVeganFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, param1);
        bundle.putString(ARG_PARAM2, param2);
        menuveganFragment.setArguments(bundle);
        return menuveganFragment;
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
        View inflate = inflater.inflate(R.layout.fragment_menuvegan, container, false);
        this.listEntree = new ArrayList();
        this.listPlat = new ArrayList();
        this.listDessert = new ArrayList();
        fetchPlats(inflate, 22);
        return inflate;
    }

    public void fetchPlats(final View v, int carte) {
        this.db.collection("Entree").whereEqualTo("aLaCarte", Integer.valueOf(carte)).addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.menuFragment.Menu32Fragment.1
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                MenuVeganFragment.this.listEntree = new ArrayList();
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    MenuVeganFragment.this.listEntree.add(MenuVeganFragment.this.unserializePlat(it.next()));
                }
                Log.e("DEBUG", "On a fini");
                MenuVeganFragment.this.dispoEntree(v);
            }
        });
        this.db.collection("Plat").whereEqualTo("aLaCarte", Integer.valueOf(carte)).addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.menuFragment.Menu32Fragment.2
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                MenuVeganFragment.this.listPlat = new ArrayList();
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    MenuVeganFragment.this.listPlat.add(MenuVeganFragment.this.unserializePlat(it.next()));
                }
                MenuVeganFragment.this.dispoPlat(v);
            }
        });
        this.db.collection("Dessert").whereEqualTo("aLaCarte", Integer.valueOf((int) 1)).whereEqualTo("vegan",true).addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.menuFragment.Menu32Fragment.3
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                MenuVeganFragment.this.listDessert = new ArrayList();
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    MenuVeganFragment.this.listDessert.add(MenuVeganFragment.this.unserializePlat(it.next()));
                }
                MenuVeganFragment.this.dispoDessert(v);
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
    public void dispoEntree(View v) {
        TextView textView = (TextView) v.findViewById(R.id.entree1);
        textView.setVisibility(View.GONE);
        TextView textView2 = (TextView) v.findViewById(R.id.entree2);
        textView2.setVisibility(View.GONE);
        TextView textView3 = (TextView) v.findViewById(R.id.entree3);
        textView3.setVisibility(View.GONE);
        TextView textView4 = (TextView) v.findViewById(R.id.oue12);
        textView4.setVisibility(View.GONE);
        TextView textView5 = (TextView) v.findViewById(R.id.oue23);
        textView5.setVisibility(View.GONE);
        for (int i = 0; i < this.listEntree.size() && i < 3; i += 1) {
            Plat plat = this.listEntree.get(i);
            if (i == 0) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(plat.getNomSansAjout());
                if (!plat.isDispo()) {
                    crossout(textView);
                } else {
                    uncrossout(textView);
                }
            } else if (i == 1) {
                textView4.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                textView2.setText(plat.getNomSansAjout());
                if (!plat.isDispo()) {
                    crossout(textView2);
                } else {
                    uncrossout(textView2);
                }
            } else if (i != 2) {
                return;
            } else {
                textView5.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);
                textView3.setText(plat.getNomSansAjout());
                if (!plat.isDispo()) {
                    crossout(textView3);
                } else {
                    uncrossout(textView3);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoPlat(View v) {
        TextView textView = (TextView) v.findViewById(R.id.plat1);
        textView.setVisibility(View.GONE);
        TextView textView2 = (TextView) v.findViewById(R.id.plat2);
        textView2.setVisibility(View.GONE);
        TextView textView3 = (TextView) v.findViewById(R.id.plat3);
        textView3.setVisibility(View.GONE);
        TextView textView4 = (TextView) v.findViewById(R.id.oup12);
        textView4.setVisibility(View.GONE);
        TextView textView5 = (TextView) v.findViewById(R.id.oup23);
        textView5.setVisibility(View.GONE);
        for (int i = 0; i < this.listPlat.size() && i < 3; i += 1) {
            Plat plat = this.listPlat.get(i);
            if (i == 0) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(plat.getNomSansAjout());
                if (!plat.isDispo()) {
                    crossout(textView);
                } else {
                    uncrossout(textView);
                }
            } else if (i == 1) {
                textView4.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                textView2.setText(plat.getNomSansAjout());
                if (!plat.isDispo()) {
                    crossout(textView2);
                } else {
                    uncrossout(textView2);
                }
            } else if (i != 2) {
                return;
            } else {
                textView5.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);
                textView3.setText(plat.getNomSansAjout());
                if (!plat.isDispo()) {
                    crossout(textView3);
                } else {
                    uncrossout(textView3);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoDessert(View v) {
        TextView textView = (TextView) v.findViewById(R.id.dessert1);
        textView.setVisibility(View.GONE);
        TextView textView2 = (TextView) v.findViewById(R.id.dessert2);
        textView2.setVisibility(View.GONE);
        TextView textView3 = (TextView) v.findViewById(R.id.dessert3);
        textView3.setVisibility(View.GONE);
        TextView textView4 = (TextView) v.findViewById(R.id.dessert4);
        textView4.setVisibility(View.GONE);
        TextView textView5 = (TextView) v.findViewById(R.id.dessert5);
        textView5.setVisibility(View.GONE);
        TextView textView6 = (TextView) v.findViewById(R.id.oud12);
        textView6.setVisibility(View.GONE);
        TextView textView7 = (TextView) v.findViewById(R.id.oud23);
        textView7.setVisibility(View.GONE);
        TextView textView8 = (TextView) v.findViewById(R.id.oud34);
        textView8.setVisibility(View.GONE);
        TextView textView9 = (TextView) v.findViewById(R.id.oud45);
        textView9.setVisibility(View.GONE);
        for (int i = 0; i < this.listDessert.size() && i < 5; i += 1) {
            Plat plat = this.listDessert.get(i);
            if (i == 0) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(plat.getNomSansAjout());
                if (!plat.isDispo()) {
                    crossout(textView);
                } else {
                    uncrossout(textView);
                }
            } else if (i == 1) {
                textView6.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                textView2.setText(plat.getNomSansAjout());
                if (!plat.isDispo()) {
                    crossout(textView2);
                } else {
                    uncrossout(textView2);
                }
            } else if (i == 2) {
                textView7.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);
                textView3.setText(plat.getNomSansAjout());
                if (!plat.isDispo()) {
                    crossout(textView3);
                } else {
                    uncrossout(textView3);
                }
            } else if (i == 3) {
                textView8.setVisibility(View.VISIBLE);
                textView4.setVisibility(View.VISIBLE);
                textView4.setText(plat.getNomSansAjout());
                if (!plat.isDispo()) {
                    crossout(textView4);
                } else {
                    uncrossout(textView4);
                }
            } else if (i != 4) {
                return;
            } else {
                textView9.setVisibility(View.VISIBLE);
                textView5.setVisibility(View.VISIBLE);
                textView5.setText(plat.getNomSansAjout());
                if (!plat.isDispo()) {
                    crossout(textView5);
                } else {
                    uncrossout(textView5);
                }
            }
        }
    }

    private void crossout(TextView nom) {
        nom.setPaintFlags(nom.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void uncrossout(TextView nom) {
        nom.setPaintFlags(nom.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
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