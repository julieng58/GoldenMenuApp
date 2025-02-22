/*
 *
 *  * Copyright (c) 2025 Florian Nadin
 *  * All rights reserved.
 *  * Last modified 23/08/2022 09:46
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
public class Plat implements Comparable {
    private int aLaCarte;
    private boolean dispo;
    private boolean glutenFree;
    private String nom;
    private double prixCarte;
    private boolean vegan;

    public Plat() {
    }

    public Plat(boolean dispo, int aLaCarte, String nom, double prixCarte, boolean vegan, boolean glutenFree) {
        this.dispo = dispo;
        this.aLaCarte = aLaCarte;
        this.nom = nom;
        this.prixCarte = prixCarte;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
    }

    public boolean isDispo() {
        return this.dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    public int getaLaCarte() {
        return this.aLaCarte;
    }

    public void setaLaCarte(int aLaCarte) {
        this.aLaCarte = aLaCarte;
    }

    public String getEmojiByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }

    public String getNom() {
        String str = this.nom;
        if (isGlutenFree()) {
            Log.e("DEBUG", "Gluten");
            str = str + " " + getEmojiByUnicode(127806);
        }
        if (isVegan()) {
            String str2 = str + " " + getEmojiByUnicode(127793);
            Log.e("DEBUG", "Vegan : " + this.nom + " dans le menu : " + Integer.toString(this.aLaCarte));
            return str2;
        }
        return str;
    }

    public String getNomSansAjout() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public double compareTo(Plat comparePlat) {
        return getPrixCarte() - comparePlat.getPrixCarte();
    }

    @Override // java.lang.Comparable
    public int compareTo(Object o) {
        Plat plat = (Plat) o;
        if (getPrixCarte() > plat.getPrixCarte()) {
            return 1;
        }
        if (getPrixCarte() >= plat.getPrixCarte()) {
            return 0;
        }
        return -1;
    }
}