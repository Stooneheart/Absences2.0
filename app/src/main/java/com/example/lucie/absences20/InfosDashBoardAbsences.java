package com.example.lucie.absences20;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by lucie on 22/05/2017.
 */

public class InfosDashBoardAbsences extends AppCompatActivity {
    private String nbrAbs;
    private String promo;

    public InfosDashBoardAbsences(String nbrAbs, String promo) {
        this.nbrAbs = nbrAbs;
        this.promo = promo;
    }

    public String getnbrAbs() {
        return nbrAbs;
    }

    public void setnbrAbs(String nbrAbs) {
        this.nbrAbs = nbrAbs;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }
}
