package com.example.lucie.absences20;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by lucie on 22/05/2017.
 */

public class InfosElevesDirect extends AppCompatActivity {
    private String prenom;
    private String nom;
    private boolean absent;

    public InfosElevesDirect(String prenom, String nom, boolean absent) {
        this.prenom = prenom;
        this.nom = nom;
        this.absent = absent;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isAbsent() {
        return absent;
    }

    public void setAbsent(boolean absent) {
        this.absent = absent;
    }
}
