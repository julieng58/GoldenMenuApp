package com.lebengy.goldenmenu.model;

import com.lebengy.goldenmenu.R;

/* loaded from: base/dex/classes2.dex */
public class Vin implements Comparable {
    private String appellation;
    private int appellationValue;
    private boolean dispo;
    private int millesime;
    private String nom;
    private double prix;
    private int regionValue;
    private String taille;
    private int type;
    private int typeAppellationValue;
    private String vigneron;

    public Vin() {
    }

    public Vin(boolean dispo, int typeAppellationValue, String appellation, int appellationValue, int regionValue, String taille, int type, double prix, int millesime, String vigneron) {
        this.dispo = dispo;
        this.typeAppellationValue = typeAppellationValue;
        this.appellation = appellation;
        this.appellationValue = appellationValue;
        this.regionValue = regionValue;
        this.taille = taille;
        this.type = type;
        this.prix = prix;
        this.millesime = millesime;
        this.vigneron = vigneron;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isDispo() {
        return this.dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    public int getTypeAppellationValue() {
        return this.typeAppellationValue;
    }

    public void setTypeAppellationValue(int typeAppellationValue) {
        this.typeAppellationValue = typeAppellationValue;
    }

    public String getAppellation() {
        return this.appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public int getAppellationValue() {
        return this.appellationValue;
    }

    public void setAppellationValue(int appellationValue) {
        this.appellationValue = appellationValue;
    }

    public int getRegionValue() {
        return this.regionValue;
    }

    public void setRegionValue(int regionValue) {
        this.regionValue = regionValue;
    }

    public String getTaille() {
        return this.taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getPrix() {
        return this.prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getMillesime() {
        return this.millesime;
    }

    public String getMillesimeToString() {
        return String.valueOf(this.millesime);
    }

    public void setMillesime(int millesime) {
        this.millesime = millesime;
    }

    public String getVigneron() {
        return this.vigneron;
    }

    public void setVigneron(String vigneron) {
        this.vigneron = vigneron;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object o) {
        Vin vin = (Vin) o;
        if (getPrix() > vin.getPrix()) {
            return 1;
        }
        if (getPrix() >= vin.getPrix()) {
            return 0;
        }
        return -1;
    }

    public String getPrixToString() {
        return String.valueOf(this.prix).replace(".0", ",00").replace(".1", ",10").replace(".2", ",20").replace(".3", ",30").replace(".4", ",40").replace(".5", ",50").replace(".6", ",60").replace(".7", ",70").replace(".8", ",80").replace(".9", ",90").replace(".", ",");
    }
}