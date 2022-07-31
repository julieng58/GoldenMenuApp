package com.lebengy.goldenmenu.model;

import com.lebengy.goldenmenu.R;

/* loaded from: base/dex/classes2.dex */
public class VinVerre implements Comparable {
    private String appellation;
    private int appellationValue;
    private boolean dispo;
    private int millesime;
    private String nom;
    private double prix1;
    private double prix45;
    private double prix75;
    private int regionValue;
    private String taille;
    private int type;
    private int typeAppellationValue;
    private String vigneron;

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

    public double getPrix75() {
        return this.prix75;
    }

    public void setPrix75(double prix75) {
        this.prix75 = prix75;
    }

    public double getPrix45() {
        return this.prix45;
    }

    public void setPrix45(double prix45) {
        this.prix45 = prix45;
    }

    public double getPrix1() {
        return this.prix1;
    }

    public void setPrix1(double prix1) {
        this.prix1 = prix1;
    }

    public int getMillesime() {
        return this.millesime;
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

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object o) {
        VinVerre vinVerre = (VinVerre) o;
        if (getPrix1() > vinVerre.getPrix1()) {
            return 1;
        }
        if (getPrix1() >= vinVerre.getPrix1()) {
            return 0;
        }
        return -1;
    }

    public String getPrix75ToString() {
        return String.valueOf(this.prix75).replace(".0", ",00").replace(".1", ",10").replace(".2", ",20").replace(".3", ",30").replace(".4", ",40").replace(".5", ",50").replace(".6", ",60").replace(".7", ",70").replace(".8", ",80").replace(".9", ",90").replace(".", ",");
    }

    public String getPrix45ToString() {
        return String.valueOf(this.prix45).replace(".0", ",00").replace(".1", ",10").replace(".2", ",20").replace(".3", ",30").replace(".4", ",40").replace(".5", ",50").replace(".6", ",60").replace(".7", ",70").replace(".8", ",80").replace(".9", ",90").replace(".", ",");
    }

    public String getPrix1ToString() {
        return String.valueOf(this.prix1).replace(".0", ",00").replace(".1", ",10").replace(".2", ",20").replace(".3", ",30").replace(".4", ",40").replace(".5", ",50").replace(".6", ",60").replace(".7", ",70").replace(".8", ",80").replace(".9", ",90").replace(".", ",");
    }
}