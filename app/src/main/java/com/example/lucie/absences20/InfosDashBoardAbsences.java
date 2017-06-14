package com.example.lucie.absences20;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by lucie on 22/05/2017.
 */

public class InfosDashBoardAbsences extends AppCompatActivity {
    private String nbrAbs;
    private String promo;
    private String moyenneAbs;
    private String nbrEleves;

    public InfosDashBoardAbsences(String nbrAbs, String promo, String moyenneAbs, String nbrEleves) {
        this.nbrAbs = nbrAbs;
        this.promo = promo;
        this.moyenneAbs = moyenneAbs;
        this.nbrEleves = nbrEleves;
    }

    public String getNbrAbs() {
        return nbrAbs;
    }

    public void setNbrAbs(String nbrAbs) {
        this.nbrAbs = nbrAbs;
    }

    public String getMoyenneAbs() {
        return moyenneAbs;
    }

    public void setMoyenneAbs(String moyenneAbs) {
        this.moyenneAbs = moyenneAbs;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getNbrEleves() {
        return nbrEleves;
    }

    public void setNbrEleves(String nbrEleves) {
        this.nbrEleves = nbrEleves;
    }
}
