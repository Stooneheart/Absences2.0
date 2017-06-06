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
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
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
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.totals_absences);
        mListView = (ListView) findViewById(R.id.listView);
        token = getIntent().getStringExtra("token");
        Log.d(TAG, "OnCreate : started. ");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://absence2epf.net16.net/api/absences.php?token=" + token;

        StringRequest jsObjRequest = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println("Réussiréussi");
                        JSONObject mJsonInfos = new JSONObject();
                        JSONArray mJsonArray = new JSONArray();
                        try {
                            mJsonArray = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {

                            mJsonInfos = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ArrayList<InfosAbsences> infos = new ArrayList<>();

                        if(mJsonArray.length() == 0){
                            try {
                                Object jsonCours = mJsonInfos.get("nom");
                                String cours = jsonCours.toString();
                                Object jsonHeureD = mJsonInfos.get("heure_debut");
                                String heureD = jsonHeureD.toString();
//                                Object jsonHeureF = mJsonArray.getJSONObject(i).get("heure_fin");
//                                String heureF = jsonHeureF.toString();
                                Object jsonNomP = mJsonInfos.get("nom_prof");
                                String nomP = jsonNomP.toString();
                                Object jsonPrenomP = mJsonInfos.get("prenom_prof");
                                String prenomP = jsonPrenomP.toString();
                                Object jsonStatut = mJsonInfos.get("statut");
                                String statut = jsonStatut.toString();
                                InfosAbsences absence = new InfosAbsences(cours, heureD, prenomP, nomP, statut);
                                infos.add(absence);

                                AbsencesListeAdapter adapter = new AbsencesListeAdapter(getApplicationContext(), R.layout.affichage_absences, infos);
                                mListView.setAdapter(adapter);
                            } catch( JSONException e){
                                e.printStackTrace();
                            }
                        } else {

                            try {

                                for (int i = 0; i < mJsonArray.length(); i++) {
                                    Object jsonCours = mJsonArray.getJSONObject(i).get("nom");
                                    String cours = jsonCours.toString();
                                    Object jsonHeureD = mJsonArray.getJSONObject(i).get("heure_debut");
                                    String heureD = jsonHeureD.toString();
//                                Object jsonHeureF = mJsonArray.getJSONObject(i).get("heure_fin");
//                                String heureF = jsonHeureF.toString();
                                    Object jsonNomP = mJsonArray.getJSONObject(i).get("nom_prof");
                                    String nomP = jsonNomP.toString();
                                    Object jsonPrenomP = mJsonArray.getJSONObject(i).get("prenom_prof");
                                    String prenomP = jsonPrenomP.toString();
                                    Object jsonStatut = mJsonArray.getJSONObject(i).get("statut");
                                    String statut = jsonStatut.toString();
                                    InfosAbsences absence = new InfosAbsences(cours, heureD, prenomP, nomP, statut);
                                    infos.add(absence);
                                }

                                AbsencesListeAdapter adapter = new AbsencesListeAdapter(getApplicationContext(), R.layout.affichage_absences, infos);
                                mListView.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        System.out.println(error.toString());
                        System.out.println("RatéRaté");
                    }
                });


        queue.add(jsObjRequest);

//        InfosAbsences abs1 = new InfosAbsences("Ethique", "10/01/2017", "Florence", "DE SAINT ROMAN", "absent");
//        InfosAbsences abs2 = new InfosAbsences("Expression du besoin", "27/01/2017", "Frédéric", "HOUPERT", "absent");
//        InfosAbsences abs3 = new InfosAbsences("L'AMOA en mode projet", "31/01/2017", "Adelaïde", "GUIZE", "absent");
//        InfosAbsences abs4 = new InfosAbsences("Ingénieurie Système et Gestion de projet", "31/01/2017", "Thierry", "HALCONRUY", "absent");
//        InfosAbsences abs5 = new InfosAbsences("Ethique", "10/01/2017", "Florence", "DE SAINT ROMAN", "absent");
//        InfosAbsences abs6 = new InfosAbsences("Expression du besoin", "27/01/2017", "Frédéric", "HOUPERT", "absent");
//        InfosAbsences abs7 = new InfosAbsences("L'AMOA en mode projet", "31/01/2017", "Adelaïde", "GUIZE", "absent");
//        InfosAbsences abs8 = new InfosAbsences("Ingénieurie Système et Gestion de projet", "31/01/2017", "Thierry", "HALCONRUY", "absent");
//        InfosAbsences abs9 = new InfosAbsences("Ethique", "10/01/2017", "Florence", "DE SAINT ROMAN", "absent");
//        InfosAbsences abs10 = new InfosAbsences("Expression du besoin", "27/01/2017", "Frédéric", "HOUPERT", "absent");
//        InfosAbsences abs11 = new InfosAbsences("L'AMOA en mode projet", "31/01/2017", "Adelaïde", "GUIZE", "absent");
//        InfosAbsences abs12 = new InfosAbsences("Ingénieurie Système et Gestion de projet", "31/01/2017", "Thierry", "HALCONRUY", "absent");
//
//        ArrayList<InfosAbsences> absences = new ArrayList<>();
//        absences.add(abs1);
//        absences.add(abs2);
//        absences.add(abs3);
//        absences.add(abs4);
//        absences.add(abs5);
//        absences.add(abs6);
//        absences.add(abs7);
//        absences.add(abs8);
//        absences.add(abs9);
//        absences.add(abs10);
//        absences.add(abs11);
//        absences.add(abs12);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        userInfos = getIntent().getStringExtra("user");
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
            try {
                JSONObject jsonObject = new JSONObject(userInfos);
                Intent intent2 = new Intent(this, TotalsAbsences.class);
                intent2.putExtra("user", jsonObject.toString());
                intent2.putExtra("token", token);
                this.finish();
                this.startActivity(intent2);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (id == R.id.mes_statistiques) {
            try {
                JSONObject jsonObject = new JSONObject(userInfos);
                Intent intent3 = new Intent(this, MesStatistiques.class);
                intent3.putExtra("user", jsonObject.toString());
                this.finish();
                this.startActivity(intent3);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (id == R.id.prevenir_absence) {
            try {
                JSONObject jsonObject = new JSONObject(userInfos);
                Intent intent3 = new Intent(this, PrevenirAbsence.class);
                intent3.putExtra("user", jsonObject.toString());
                this.finish();
                this.startActivity(intent3);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (id == R.id.deconnexion) {

            Intent intent = new Intent(this,MainActivity.class);
            TotalsAbsences.this.finish();
            startActivity(intent);
        } else if (id == R.id.absences_anticipees) {
            try {
                JSONObject jsonObject = new JSONObject(userInfos);
                Intent intent3 = new Intent(this, AbsencesAnticipees.class);
                intent3.putExtra("user", jsonObject.toString());
                this.finish();
                this.startActivity(intent3);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (id == R.id.absences_direct) {
            try {
                JSONObject jsonObject = new JSONObject(userInfos);
                Intent intent3 = new Intent(this, choix_promotion.class);
                intent3.putExtra("user", jsonObject.toString());
                this.finish();
                this.startActivity(intent3);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (id == R.id.absence_profs) {
            try {
                JSONObject jsonObject = new JSONObject(userInfos);
                Intent intent3 = new Intent(this, AbsencesProfesseurs.class);
                intent3.putExtra("user", jsonObject.toString());
                this.finish();
                this.startActivity(intent3);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (id == R.id.absences_promotion) {
            try {
                JSONObject jsonObject = new JSONObject(userInfos);
                Intent intent3 = new Intent(this, choix_promotion.class);
                intent3.putExtra("user", jsonObject.toString());
                this.finish();
                this.startActivity(intent3);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (id == R.id.alertes) {
            try {
                JSONObject jsonObject = new JSONObject(userInfos);
                Intent intent3 = new Intent(this, Alertes.class);
                intent3.putExtra("user", jsonObject.toString());
                this.finish();
                this.startActivity(intent3);
            } catch (JSONException e) {
                e.printStackTrace();
            }

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
