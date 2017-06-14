package com.example.lucie.absences20;

import android.support.v7.app.AppCompatActivity;

import java.sql.Date;
import java.text.DateFormat;
import java.util.Locale;

/**
 * Created by lucie on 22/05/2017.
 */

public class InfosAbsences extends AppCompatActivity {
    private String module;
    private String date;
    private String prenom_prof;
    private String nom_prof;
    private String statut;

    public InfosAbsences(String module, String date, String prenom_prof, String nom_prof, String statut) {
        this.module = module;
        if (date.length() > 15) {
              date = date.substring(0, date.length()-9);
        Date dateSql = Date.valueOf(date);
                  DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.FRANCE);
             this.date = df.getDateInstance().format(dateSql);
        }
        else
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
