package com.example.lucie.absences20;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lucie on 22/05/2017.
 */

public class MesStatistiques extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    ActionBarDrawerToggle toggle;
    private String userInfos;

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mes_statistiques);

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
            startActivity(intent2);
        } else if (id == R.id.mes_statistiques) {
            Intent intent3 = new Intent(this, AccueilActivity.class);
            startActivity(intent3);

        } else if (id == R.id.prevenir_absence) {
            Intent intent4 = new Intent(this, AccueilActivity.class);
            startActivity(intent4);

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