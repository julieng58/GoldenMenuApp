/*
 *
 *  * Copyright (c) 2025 Florian Nadin
 *  * All rights reserved.
 *  * Last modified 22/08/2022 17:00
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

package com.saslebengy.goldenmenu.model;

import android.util.Log;

/* loaded from: base/dex/classes2.dex */
public class Formule implements Comparable {
    private boolean dispo;
    private boolean dispoWES;
    private boolean glutenFree;
    private String nomDessert;
    private String nomPlat;
    private double prixCarte;
    private double prixFormule;
    private boolean vegan;

    public Formule() {
    }

    public Formule(boolean dispo, boolean dispoWES, String nomDessert, String nomPlat, double prixCarte, double prixFormule, boolean vegan, boolean glutenFree) {
        this.dispo = dispo;
        this.dispoWES = dispoWES;
        this.nomDessert = nomDessert;
        this.nomPlat = nomPlat;
        this.prixCarte = prixCarte;
        this.prixFormule = prixFormule;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
    }

    public boolean isDispo() {
        return this.dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    public boolean isDispoWES() {
        return this.dispoWES;
    }

    public void setDispoWES(boolean dispoWES) {
        this.dispoWES = dispoWES;
    }

    public String getNomDessert() {
        return this.nomDessert;
    }

    public void setNomDessert(String nomDessert) {
        this.nomDessert = nomDessert;
    }

    public String getNomPlat() {
        return this.nomPlat;
    }

    public void setNomPlat(String nomPlat) {
        this.nomPlat = nomPlat;
    }

    public String getEmojiByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }

    public String getNomFormule() {
        String str = this.nomPlat + " et " + this.nomDessert;
        if (isGlutenFree()) {
            Log.e("DEBUG", "Gluten");
            str = str + " " + getEmojiByUnicode(127806);
        }
        if (isVegan()) {
            String str2 = str + " " + getEmojiByUnicode(127793);
            Log.e("DEBUG", "Vegan");
            return str2;
        }
        return str;
    }

    public double getPrixCarte() {
        return this.prixCarte;
    }

    public String getPrixCarteString() {
        return String.valueOf(this.prixCarte).replace(".0", ",00").replace(".1", ",10").replace(".2", ",20").replace(".3", ",30").replace(".4", ",40").replace(".5", ",50").replace(".6", ",60").replace(".7", ",70").replace(".8", ",80").replace(".9", ",90").replace(".", ",");
    }

    public void setPrixCarte(double prixCarte) {
        this.prixCarte = prixCarte;
    }

    public double getPrixFormule() {
        return this.prixFormule;
    }

    public String getPrixFormuleString() {
        return String.valueOf(this.prixFormule).replace(".0", ",00").replace(".1", ",10").replace(".2", ",20").replace(".3", ",30").replace(".4", ",40").replace(".5", ",50").replace(".6", ",60").replace(".7", ",70").replace(".8", ",80").replace(".9", ",90").replace(".", ",") + " €";
    }

    public void setPrixFormule(double prixFormule) {
        this.prixFormule = prixFormule;
    }

    public boolean isVegan() {
        return this.vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isGlutenFree() {
        return this.glutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object o) {
        Formule formule = (Formule) o;
        if (getPrixFormule() > formule.getPrixFormule()) {
            return 1;
        }
        if (getPrixFormule() >= formule.getPrixFormule()) {
            return 0;
        }
        return -1;
    }
}