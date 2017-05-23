package com.example.lucie.absences20;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lucie on 22/05/2017.
 */

public class TotalsAbsences extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    ActionBarDrawerToggle toggle;
    private static String TAG="TotalsAbsences";
    private ListView mListView;
    private String userInfos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.totals_absences);
        mListView = (ListView) findViewById(R.id.listView);
        Log.d(TAG, "OnCreate : started. ");

        InfosAbsences abs1 = new InfosAbsences("Ethique", "10/01/2017", "Florence", "DE SAINT ROMAN", "absent");
        InfosAbsences abs2 = new InfosAbsences("Expression du besoin", "27/01/2017", "Frédéric", "HOUPERT", "absent");
        InfosAbsences abs3 = new InfosAbsences("L'AMOA en mode projet", "31/01/2017", "Adelaïde", "GUIZE", "absent");
        InfosAbsences abs4 = new InfosAbsences("Ingénieurie Système et Gestion de projet", "31/01/2017", "Thierry", "HALCONRUY", "absent");
        InfosAbsences abs5 = new InfosAbsences("Ethique", "10/01/2017", "Florence", "DE SAINT ROMAN", "absent");
        InfosAbsences abs6 = new InfosAbsences("Expression du besoin", "27/01/2017", "Frédéric", "HOUPERT", "absent");
        InfosAbsences abs7 = new InfosAbsences("L'AMOA en mode projet", "31/01/2017", "Adelaïde", "GUIZE", "absent");
        InfosAbsences abs8 = new InfosAbsences("Ingénieurie Système et Gestion de projet", "31/01/2017", "Thierry", "HALCONRUY", "absent");
        InfosAbsences abs9 = new InfosAbsences("Ethique", "10/01/2017", "Florence", "DE SAINT ROMAN", "absent");
        InfosAbsences abs10 = new InfosAbsences("Expression du besoin", "27/01/2017", "Frédéric", "HOUPERT", "absent");
        InfosAbsences abs11 = new InfosAbsences("L'AMOA en mode projet", "31/01/2017", "Adelaïde", "GUIZE", "absent");
        InfosAbsences abs12 = new InfosAbsences("Ingénieurie Système et Gestion de projet", "31/01/2017", "Thierry", "HALCONRUY", "absent");

        ArrayList<InfosAbsences> absences = new ArrayList<>();
        absences.add(abs1);
        absences.add(abs2);
        absences.add(abs3);
        absences.add(abs4);
        absences.add(abs5);
        absences.add(abs6);
        absences.add(abs7);
        absences.add(abs8);
        absences.add(abs9);
        absences.add(abs10);
        absences.add(abs11);
        absences.add(abs12);

        AbsencesListeAdapter adapter = new AbsencesListeAdapter(this, R.layout.affichage_absences, absences);
        mListView.setAdapter(adapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        userInfos = getIntent().getStringExtra("user");
        try {
            JSONObject jsonObject = new JSONObject(userInfos);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.disconnect){
            Intent intent = new Intent(this,MainActivity.class);
            this.finish();
            startActivity(intent);
        }

        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mes_absences) {
            Intent intent2 = new Intent(this, TotalsAbsences.class);
            intent2.putExtra("user", userInfos);
            startActivity(intent2);
        } else if (id == R.id.mes_statistiques) {
            Intent intent3 = new Intent(this, MesStatistiques.class);
            intent3.putExtra("user", userInfos);
            startActivity(intent3);

        } else if (id == R.id.prevenir_absence) {
            try{
            JSONObject jsonObject = new JSONObject(userInfos);
            Intent intent3 = new Intent(this, AccueilActivity.class);
            intent3.putExtra("user", jsonObject.toString());
            this.finish();
            this.startActivity(intent3);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        } else if (id == R.id.deconnexion) {

            Intent intent = new Intent(this,MainActivity.class);
            this.finish();
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        JSONObject json = null;
        String nomPrenom = "";
        try {
            json = new JSONObject(userInfos);
            nomPrenom= json.getString("prenom") + " " + json.getString("nom").toUpperCase();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getMenuInflater().inflate(R.menu.utilisateur_menu, menu);
        MenuItem item =  menu.findItem(R.id.nomUser);
        item.setTitle(nomPrenom);
        return true;
    }

}
