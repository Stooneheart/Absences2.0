package com.example.lucie.absences20;

import android.support.v7.app.AppCompatActivity;

import java.sql.Date;
import java.text.DateFormat;
import java.util.Locale;

/**
 * Created by lucie on 09/06/2017.
 */

public class InfosAlertes extends AppCompatActivity {

    private String prenom_eleve;
    private String nom_eleve;
    private String promotion;


    public InfosAlertes(String prenom_eleve, String nom_eleve, String promotion) {
        this.prenom_eleve = prenom_eleve;
        this.nom_eleve = nom_eleve;
        this.promotion = promotion;
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

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }
}
