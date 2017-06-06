package com.example.lucie.absences20;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by lucie on 22/05/2017.
 */

public class InfosAbsencesPromotion extends AppCompatActivity {
    private String module;
    private String date;
    private String prenom_eleve;
    private String nom_eleve;
    private String prenom_prof;
    private String nom_prof;
    private String statut;

    public InfosAbsencesPromotion(String module, String date, String prenom_eleve, String nom_eleve, String prenom_prof, String nom_prof, String statut) {
        this.module = module;
        this.date = date;
        this.prenom_eleve = prenom_eleve;
        this.nom_eleve = nom_eleve;
        this.prenom_prof = prenom_prof;
        this.nom_prof = nom_prof;
        this.statut = statut;
    }

    public String getPrenom_eleve() {
        return prenom_eleve;
    }

    public void setPrenom_eleve(String prenom_eleve) {
        this.prenom_eleve = prenom_eleve;
    }

    public String getNom_eleve() {
        return nom_eleve;
    }

    public void setNom_eleve(String nom_eleve) {
        this.nom_eleve = nom_eleve;
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
