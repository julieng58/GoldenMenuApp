package com.lebengy.goldenmenu.menuFragment;

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
import com.lebengy.goldenmenu.model.Formule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: base/dex/classes2.dex */
public class NosFormulesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Formule> listForm;
    private String mParam1;
    private String mParam2;

    public static NosFormulesFragment newInstance(String param1, String param2) {
        NosFormulesFragment nosFormulesFragment = new NosFormulesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, param1);
        bundle.putString(ARG_PARAM2, param2);
        nosFormulesFragment.setArguments(bundle);
        return nosFormulesFragment;
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
        View inflate = inflater.inflate(R.layout.fragment_nos_formules, container, false);
        wipeLabel(inflate);
        this.listForm = new ArrayList();
        fetchFormule(inflate);
        return inflate;
    }

    public void fetchFormule(final View v) {
        this.db.collection("Formule").whereEqualTo("aLaCarte", (Object) true).addSnapshotListener(new EventListener<QuerySnapshot>() { // from class: com.lebengy.goldenmenu.menuFragment.NosFormulesFragment.1
            @Override // com.google.firebase.firestore.EventListener
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("DEBUG", "Listen failed.", e);
                    return;
                }
                NosFormulesFragment.this.listForm = new ArrayList();
                Iterator<QueryDocumentSnapshot> it = value.iterator();
                while (it.hasNext()) {
                    NosFormulesFragment.this.listForm.add(NosFormulesFragment.this.unserializeFormule(it.next()));
                }
                NosFormulesFragment.this.dispoFormule(v);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Formule unserializeFormule(QueryDocumentSnapshot doc) {
        Formule formule = new Formule();
        formule.setNomDessert(doc.getString("nomDessert"));
        formule.setNomPlat(doc.getString("nomPlat"));
        formule.setDispo(doc.getBoolean("dispo").booleanValue());
        formule.setPrixCarte(doc.getDouble("prixCarte").doubleValue());
        formule.setPrixFormule(doc.getDouble("prixFormule").doubleValue());
        formule.setDispoWES(doc.getBoolean("dispoWes").booleanValue());
        formule.setVegan(doc.getBoolean("vegan").booleanValue());
        formule.setGlutenFree(doc.getBoolean("glutenFree").booleanValue());
        return formule;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispoFormule(View v) {
        int i;
        int i2;
        TextView textView;
        int i3;
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
        int i4;
        TextView textView12 = (TextView) v.findViewById(R.id.formule1NWES);
        textView12.setVisibility(View.GONE);
        TextView textView13 = (TextView) v.findViewById(R.id.prix1NWES);
        textView13.setVisibility(View.GONE);
        TextView textView14 = (TextView) v.findViewById(R.id.formule2NWES);
        textView14.setVisibility(View.GONE);
        TextView textView15 = (TextView) v.findViewById(R.id.prix2NWES);
        textView15.setVisibility(View.GONE);
        TextView textView16 = (TextView) v.findViewById(R.id.formule3NWES);
        textView16.setVisibility(View.GONE);
        TextView textView17 = (TextView) v.findViewById(R.id.prix3NWES);
        textView17.setVisibility(View.GONE);
        TextView textView18 = (TextView) v.findViewById(R.id.formule4NWES);
        textView18.setVisibility(View.GONE);
        TextView textView19 = (TextView) v.findViewById(R.id.prix4NWES);
        textView19.setVisibility(View.GONE);
        TextView textView20 = (TextView) v.findViewById(R.id.formule1WES);
        textView20.setVisibility(View.GONE);
        TextView textView21 = (TextView) v.findViewById(R.id.prix1WES);
        textView21.setVisibility(View.GONE);
        TextView textView22 = (TextView) v.findViewById(R.id.formule2WES);
        textView22.setVisibility(View.GONE);
        TextView textView23 = (TextView) v.findViewById(R.id.prix2WES);
        textView23.setVisibility(View.GONE);
        TextView textView24 = (TextView) v.findViewById(R.id.formule3WES);
        textView24.setVisibility(View.GONE);
        TextView textView25 = (TextView) v.findViewById(R.id.prix3WES);
        TextView textView26 = textView13;
        textView25.setVisibility(View.GONE);
        TextView textView27 = (TextView) v.findViewById(R.id.formule4WES);
        TextView textView28 = textView12;
        textView27.setVisibility(View.GONE);
        TextView textView29 = (TextView) v.findViewById(R.id.prix4WES);
        TextView textView30 = textView15;
        textView29.setVisibility(View.GONE);
        TextView textView31 = (TextView) v.findViewById(R.id.ou12NWES);
        TextView textView32 = textView14;
        textView31.setVisibility(View.GONE);
        TextView textView33 = (TextView) v.findViewById(R.id.ou23NWES);
        TextView textView34 = textView31;
        textView33.setVisibility(View.GONE);
        TextView textView35 = (TextView) v.findViewById(R.id.ou34NWES);
        TextView textView36 = textView17;
        textView35.setVisibility(View.GONE);
        TextView textView37 = (TextView) v.findViewById(R.id.ou12WES);
        TextView textView38 = textView16;
        textView37.setVisibility(View.GONE);
        TextView textView39 = (TextView) v.findViewById(R.id.ou23WES);
        textView39.setVisibility(View.GONE);
        TextView textView40 = (TextView) v.findViewById(R.id.ou34WES);
        TextView textView41 = textView19;
        textView40.setVisibility(View.GONE);
        TextView textView42 = (TextView) v.findViewById(R.id.ou41WES);
        TextView textView43 = textView18;
        textView42.setVisibility(View.GONE);
        TextView textView44 = (TextView) v.findViewById(R.id.limitation);
        wipeLabel(v);
        Collections.sort(this.listForm);
        TextView textView45 = textView35;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            i = i7;
            if (i5 >= this.listForm.size() || i5 >= 4) {
                break;
            }
            if (this.listForm.get(i5).isDispoWES()) {
                if (i6 == 0) {
                    textView20.setVisibility(View.VISIBLE);
                    textView21.setVisibility(View.VISIBLE);
                    textView20.setText(this.listForm.get(i5).getNomFormule());
                    textView21.setText(this.listForm.get(i5).getPrixFormuleString());
                    if (!this.listForm.get(i5).isDispo()) {
                        crossout(textView20, textView21);
                    } else {
                        uncrossout(textView20, textView21);
                    }
                } else if (i6 == 1) {
                    textView37.setVisibility(View.VISIBLE);
                    textView22.setVisibility(View.VISIBLE);
                    textView23.setVisibility(View.VISIBLE);
                    textView22.setText(this.listForm.get(i5).getNomFormule());
                    textView23.setText(this.listForm.get(i5).getPrixFormuleString());
                    if (!this.listForm.get(i5).isDispo()) {
                        crossout(textView22, textView23);
                    } else {
                        uncrossout(textView22, textView23);
                    }
                } else if (i6 == 2) {
                    textView39.setVisibility(View.VISIBLE);
                    textView24.setVisibility(View.VISIBLE);
                    textView25.setVisibility(View.VISIBLE);
                    textView24.setText(this.listForm.get(i5).getNomFormule());
                    textView25.setText(this.listForm.get(i5).getPrixFormuleString());
                    if (!this.listForm.get(i5).isDispo()) {
                        crossout(textView24, textView25);
                    } else {
                        uncrossout(textView24, textView25);
                    }
                } else if (i6 != 3) {
                    return;
                } else {
                    textView40.setVisibility(View.VISIBLE);
                    textView27.setVisibility(View.VISIBLE);
                    textView29.setVisibility(View.VISIBLE);
                    textView27.setText(this.listForm.get(i5).getNomFormule());
                    textView29.setText(this.listForm.get(i5).getPrixFormuleString());
                    if (!this.listForm.get(i5).isDispo()) {
                        crossout(textView27, textView29);
                    } else {
                        uncrossout(textView27, textView29);
                    }
                }
                i6 += 1;
                textView = textView43;
                textView2 = textView25;
                i4 = i;
                textView3 = textView45;
                textView4 = textView29;
                textView5 = textView28;
                textView6 = textView32;
                textView7 = textView36;
                textView8 = textView34;
                textView9 = textView38;
                textView10 = textView27;
                textView11 = textView26;
            } else {
                if (i != 0) {
                    i3 = i;
                    if (i3 != 1) {
                        if (i3 == 2) {
                            textView = textView43;
                            textView3 = textView45;
                            textView4 = textView29;
                            textView2 = textView25;
                            textView33.setVisibility(View.VISIBLE);
                            TextView textView46 = textView38;
                            textView46.setVisibility(View.VISIBLE);
                            textView10 = textView27;
                            TextView textView47 = textView36;
                            textView47.setVisibility(View.VISIBLE);
                            textView46.setText(this.listForm.get(i5).getNomFormule());
                            textView47.setText(this.listForm.get(i5).getPrixFormuleString());
                            if (!this.listForm.get(i5).isDispo()) {
                                crossout(textView46, textView47);
                            } else {
                                uncrossout(textView46, textView47);
                            }
                            textView8 = textView34;
                            textView9 = textView46;
                            textView5 = textView28;
                            textView6 = textView32;
                            textView7 = textView47;
                        } else if (i3 != 3) {
                            return;
                        } else {
                            TextView textView48 = textView45;
                            textView4 = textView29;
                            textView48.setVisibility(View.VISIBLE);
                            textView3 = textView48;
                            textView = textView43;
                            textView.setVisibility(View.VISIBLE);
                            textView2 = textView25;
                            TextView textView49 = textView41;
                            textView49.setVisibility(View.VISIBLE);
                            textView.setText(this.listForm.get(i5).getNomFormule());
                            textView49.setText(this.listForm.get(i5).getPrixFormuleString());
                            if (!this.listForm.get(i5).isDispo()) {
                                crossout(textView, textView49);
                            } else {
                                uncrossout(textView, textView49);
                            }
                            textView41 = textView49;
                            textView5 = textView28;
                            textView6 = textView32;
                            textView7 = textView36;
                            textView8 = textView34;
                            textView9 = textView38;
                            textView10 = textView27;
                        }
                        textView11 = textView26;
                    } else {
                        textView = textView43;
                        textView3 = textView45;
                        textView4 = textView29;
                        textView2 = textView25;
                        TextView textView50 = textView34;
                        textView9 = textView38;
                        textView10 = textView27;
                        TextView textView51 = textView36;
                        textView50.setVisibility(View.VISIBLE);
                        textView8 = textView50;
                        TextView textView52 = textView32;
                        textView52.setVisibility(View.VISIBLE);
                        textView7 = textView51;
                        TextView textView53 = textView30;
                        textView53.setVisibility(View.VISIBLE);
                        textView52.setText(this.listForm.get(i5).getNomFormule());
                        textView53.setText(this.listForm.get(i5).getPrixFormuleString());
                        if (!this.listForm.get(i5).isDispo()) {
                            crossout(textView52, textView53);
                        } else {
                            uncrossout(textView52, textView53);
                        }
                        textView30 = textView53;
                        textView11 = textView26;
                        textView5 = textView28;
                        textView6 = textView52;
                    }
                } else {
                    textView = textView43;
                    i3 = i;
                    textView2 = textView25;
                    textView3 = textView45;
                    textView4 = textView29;
                    textView5 = textView28;
                    textView6 = textView32;
                    textView7 = textView36;
                    textView8 = textView34;
                    textView9 = textView38;
                    textView10 = textView27;
                    textView5.setVisibility(View.VISIBLE);
                    textView11 = textView26;
                    textView11.setVisibility(View.VISIBLE);
                    textView5.setText(this.listForm.get(i5).getNomFormule());
                    textView11.setText(this.listForm.get(i5).getPrixFormuleString());
                    if (!this.listForm.get(i5).isDispo()) {
                        crossout(textView5, textView11);
                    } else {
                        uncrossout(textView5, textView11);
                    }
                }
                i4 = i3 + 1;
            }
            i5 += 1;
            textView26 = textView11;
            textView27 = textView10;
            textView38 = textView9;
            textView34 = textView8;
            textView36 = textView7;
            textView32 = textView6;
            textView28 = textView5;
            textView29 = textView4;
            textView45 = textView3;
            TextView textView54 = textView;
            i7 = i4;
            textView25 = textView2;
            textView43 = textView54;
        }
        if (i6 == 0 || i <= 0) {
            i2 = 0;
        } else {
            i2 = 0;
            textView42.setVisibility(View.VISIBLE);
        }
        if (i == 0) {
            if (i6 == 0) {
                textView44.setText("Aucune formule disponible actuellement");
            } else {
                textView44.setVisibility(View.GONE);
            }
        } else if (i == 1) {
            textView44.setVisibility(View.VISIBLE);
            textView44.setText("(Cette formule est servie uniquement le midi du mardi au vendredi)");
        } else if (i == 2) {
            textView44.setText("(Ces deux formules sont servies uniquement le midi du mardi au vendredi)");
            textView44.setVisibility(View.VISIBLE);
        } else if (i == 3) {
            textView44.setVisibility(View.VISIBLE);
            textView44.setText("(Ces trois formules sont servies uniquement le midi du mardi au vendredi)");
        } else if (i != 4) {
        } else {
            textView44.setVisibility(View.VISIBLE);
            textView44.setText("(Ces quatres formules sont servies uniquement le midi du mardi au vendredi)");
        }
    }

    private void crossout(TextView prix, TextView nom) {
        prix.setPaintFlags(prix.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        nom.setPaintFlags(nom.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void uncrossout(TextView prix, TextView nom) {
        prix.setPaintFlags(prix.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        nom.setPaintFlags(nom.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void wipeLabel(View v) {
        ((TextView) v.findViewById(R.id.formule1NWES)).setText("");
        ((TextView) v.findViewById(R.id.formule2NWES)).setText("");
        ((TextView) v.findViewById(R.id.formule3NWES)).setText("");
        ((TextView) v.findViewById(R.id.formule4NWES)).setText("");
        ((TextView) v.findViewById(R.id.prix1NWES)).setText("");
        ((TextView) v.findViewById(R.id.prix2NWES)).setText("");
        ((TextView) v.findViewById(R.id.prix3NWES)).setText("");
        ((TextView) v.findViewById(R.id.prix4NWES)).setText("");
        ((TextView) v.findViewById(R.id.formule1WES)).setText("");
        ((TextView) v.findViewById(R.id.formule2WES)).setText("");
        ((TextView) v.findViewById(R.id.formule3WES)).setText("");
        ((TextView) v.findViewById(R.id.formule4WES)).setText("");
        ((TextView) v.findViewById(R.id.prix1WES)).setText("");
        ((TextView) v.findViewById(R.id.prix2WES)).setText("");
        ((TextView) v.findViewById(R.id.prix3WES)).setText("");
        ((TextView) v.findViewById(R.id.prix4WES)).setText("");
    }
}