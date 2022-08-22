package com.saslebengy.goldenmenu.model;

/* loaded from: base/dex/classes2.dex */
public class Aperitif implements Comparable {
    private boolean dispo;
    private String nom;
    private int ordre = 0;
    private double prix1;
    private double prix150;
    private double prix2;
    private double prix4;
    private double prix50;
    private String taille;
    private int type;

    public boolean isDispo() {
        return this.dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTaille() {
        return this.taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public double getPrix1() {
        return this.prix1;
    }

    public void setPrix1(double prix1) {
        this.prix1 = prix1;
    }

    public double getPrix2() {
        return this.prix2;
    }

    public void setPrix2(double prix2) {
        this.prix2 = prix2;
    }

    public double getPrix4() {
        return this.prix4;
    }

    public void setPrix4(double prix4) {
        this.prix4 = prix4;
    }

    public double getPrix50() {
        return this.prix50;
    }

    public void setPrix50(double prix50) {
        this.prix50 = prix50;
    }

    public double getPrix150() {
        return this.prix150;
    }

    public void setPrix150(double prix150) {
        this.prix150 = prix150;
    }

    public int getOrdre() {
        return this.ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object o) {
        Aperitif aperitif = (Aperitif) o;
        int i = this.ordre;
        if (i != 0) {
            if (i > aperitif.getOrdre()) {
                return 1;
            }
            if (getOrdre() >= aperitif.getOrdre()) {
                return 0;
            }
            return -1;
        } else if (getPrix2() > aperitif.getPrix2()) {
            return 1;
        } else {
            if (getPrix4() >= aperitif.getPrix4()) {
                return 0;
            }
            return -1;
        }
    }

    public String getPrix2ToString() {
        return String.valueOf(this.prix2).replace(".0", ",00").replace(".1", ",10").replace(".2", ",20").replace(".3", ",30").replace(".4", ",40").replace(".5", ",50").replace(".6", ",60").replace(".7", ",70").replace(".8", ",80").replace(".9", ",90").replace(".", ",");
    }

    public String getPrix4ToString() {
        return String.valueOf(this.prix4).replace(".0", ",00").replace(".1", ",10").replace(".2", ",20").replace(".3", ",30").replace(".4", ",40").replace(".5", ",50").replace(".6", ",60").replace(".7", ",70").replace(".8", ",80").replace(".9", ",90").replace(".", ",");
    }

    public String getPrix150ToString() {
        return String.valueOf(this.prix150).replace(".0", ",00").replace(".1", ",10").replace(".2", ",20").replace(".3", ",30").replace(".4", ",40").replace(".5", ",50").replace(".6", ",60").replace(".7", ",70").replace(".8", ",80").replace(".9", ",90").replace(".", ",");
    }

    public String getPrix50ToString() {
        return String.valueOf(this.prix50).replace(".0", ",00").replace(".1", ",10").replace(".2", ",20").replace(".3", ",30").replace(".4", ",40").replace(".5", ",50").replace(".6", ",60").replace(".7", ",70").replace(".8", ",80").replace(".9", ",90").replace(".", ",");
    }

    public String getPrix1ToString() {
        return String.valueOf(this.prix1).replace(".0", ",00").replace(".1", ",10").replace(".2", ",20").replace(".3", ",30").replace(".4", ",40").replace(".5", ",50").replace(".6", ",60").replace(".7", ",70").replace(".8", ",80").replace(".9", ",90").replace(".", ",");
    }
}