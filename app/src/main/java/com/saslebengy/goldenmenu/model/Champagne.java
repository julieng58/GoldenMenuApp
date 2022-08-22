package com.saslebengy.goldenmenu.model;

/* loaded from: base/dex/classes2.dex */
public class Champagne implements Comparable {
    private String appellation;
    private int appellationValue;
    private boolean dispo;
    private int millesime;
    private String nom;
    private double prix37;
    private double prix75;
    private int regionValue;
    private String taille;
    private int type;
    private int typeAppellationValue;
    private String vigneron;

    public double getPrix75() {
        return this.prix75;
    }

    public void setPrix75(double prix75) {
        this.prix75 = prix75;
    }

    public double getPrix37() {
        return this.prix37;
    }

    public void setPrix37(double prix37) {
        this.prix37 = prix37;
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
        return this.prix75;
    }

    public void setPrix(double prix75) {
        this.prix75 = prix75;
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
        Champagne champagne = (Champagne) o;
        if (getPrix() > champagne.getPrix()) {
            return 1;
        }
        if (getPrix() >= champagne.getPrix()) {
            return 0;
        }
        return -1;
    }

    public String getPrix75ToString() {
        return String.valueOf(this.prix75).replace(".0", ",00").replace(".1", ",10").replace(".2", ",20").replace(".3", ",30").replace(".4", ",40").replace(".5", ",50").replace(".6", ",60").replace(".7", ",70").replace(".8", ",80").replace(".9", ",90").replace(".", ",");
    }

    public String getPrix37ToString() {
        return String.valueOf(this.prix37).replace(".0", ",00").replace(".1", ",10").replace(".2", ",20").replace(".3", ",30").replace(".4", ",40").replace(".5", ",50").replace(".6", ",60").replace(".7", ",70").replace(".8", ",80").replace(".9", ",90").replace(".", ",");
    }
}