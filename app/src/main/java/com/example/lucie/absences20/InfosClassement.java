package com.example.lucie.absences20;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by lucie on 22/05/2017.
 */

public class InfosClassement extends AppCompatActivity {
    private String prenom;
    private String nom;
    private String nbr_absences;

    public InfosClassement(String prenom, String nom, String nbr_absences) {
        this.prenom = prenom;
        this.nom = nom;
        this.nbr_absences = nbr_absences;
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

    public String getNbr_absences() {
        return nbr_absences;
    }

    public void setNbr_absences(String nbr_absences) {
        this.nbr_absences = nbr_absences;
    }
}
