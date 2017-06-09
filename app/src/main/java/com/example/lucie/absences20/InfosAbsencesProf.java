package com.example.lucie.absences20;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by lucie on 09/06/2017.
 */

public class InfosAbsencesProf extends AppCompatActivity {

    private String module;
    private String date;
    private String prenom_prof;
    private String nom_prof;
    private String statut;

    public InfosAbsencesProf(String module, String date, String prenom_prof, String nom_prof, String statut) {
        this.module = module;
        this.date = date;
        this.prenom_prof = prenom_prof;
        this.nom_prof = nom_prof;
        this.statut = statut;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrenom_prof() {
        return prenom_prof;
    }

    public void setPrenom_prof(String prenom_prof) {
        this.prenom_prof = prenom_prof;
    }

    public String getNom_prof() {
        return nom_prof;
    }

    public void setNom_prof(String nom_prof) {
        this.nom_prof = nom_prof;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
