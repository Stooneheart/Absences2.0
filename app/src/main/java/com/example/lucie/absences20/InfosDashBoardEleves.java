package com.example.lucie.absences20;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by lucie on 22/05/2017.
 */

public class InfosDashBoardEleves extends AppCompatActivity {
    private String nbrEleves;
    private String promo;

    public InfosDashBoardEleves(String nbrEleves, String promo) {
        this.nbrEleves = nbrEleves;
        this.promo = promo;
    }

    public String getNbrEleves() {
        return nbrEleves;
    }

    public void setNbrEleves(String nbrEleves) {
        this.nbrEleves = nbrEleves;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }
}
