package com.example.lucie.absences20;

import java.util.Date;

/**
 * Created by Guillaume Rosenrib on 16/05/2017.
 */

public class AbsenceEleve {
    private String nomMatiere;
    private String heureDebut;
    private String heureFin;
    private String prenomProf;
    private String nomProf;
    private String statut;

    public AbsenceEleve(String nomMatiere, String heureDebut, String heureFin, String prenomProf, String nomProf, String statut) {
        this.nomMatiere = nomMatiere;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.prenomProf = prenomProf;
        this.nomProf = nomProf;
        this.statut = statut;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public String getPrenomProf() {
        return prenomProf;
    }

    public void setPrenomProf(String prenomProf) {
        this.prenomProf = prenomProf;
    }

    public String getNomProf() {
        return nomProf;
    }

    public void setNomProf(String nomProf) {
        this.nomProf = nomProf;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}


