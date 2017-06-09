package com.example.lucie.absences20;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by lucie on 22/05/2017.
 */

public class InfosStatsPromo extends AppCompatActivity {
    private String nbr;
    private String nom;

    public InfosStatsPromo(String nbr, String nom) {
        this.nbr = nbr;
        this.nom = nom;
    }

    public String getNbr() {
        return nbr;
    }

    public void setNbr(String nbr) {
        this.nbr = nbr;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
