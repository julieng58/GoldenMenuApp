package com.lebengy.goldenmenu.model;

import com.lebengy.goldenmenu.R;

/* loaded from: base/dex/classes2.dex */
public class Biere implements Comparable {
    private String brasseur;
    private String description;
    private boolean dispo;
    private String nom;
    private Double prix;

    public Biere() {
    }

    public Biere(String nom, String description, String brasseur, Double prix, boolean dispo) {
        this.nom = nom;
        this.description = description;
        this.brasseur = brasseur;
        this.prix = prix;
        this.dispo = dispo;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrasseur() {
        return this.brasseur;
    }

    public void setBrasseur(String brasseur) {
        this.brasseur = brasseur;
    }

    public Double getPrix() {
        return this.prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public boolean isDispo() {
        return this.dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object o) {
        Biere biere = (Biere) o;
        if (getPrix().doubleValue() > biere.getPrix().doubleValue()) {
            return 1;
        }
        if (getPrix().doubleValue() >= biere.getPrix().doubleValue()) {
            return 0;
        }
        return -1;
    }

    public String getPrixToString() {
        return String.valueOf(this.prix).replace(".0", ",00").replace(".1", ",10").replace(".2", ",20").replace(".3", ",30").replace(".4", ",40").replace(".5", ",50").replace(".6", ",60").replace(".7", ",70").replace(".8", ",80").replace(".9", ",90").replace(".", ",");
    }
}