package com.example.lucie.absences20;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import org.w3c.dom.Text;


public class AccueilActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    ActionBarDrawerToggle toggle;
    private AbsenceEleve absences;
    private TextView tvRepCours;
    private TextView tvRepDateD;
    private TextView tvRepDateF;
    private TextView tvRepPrenomP;
    private TextView tvRepNomP;
    private TextView tvRepStatut;
    private String nomPrenom;

    private String userInfos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

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
            nomPrenom = jsonObject.get("prenom") + " " + jsonObject.getString("nom").toUpperCase();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView bienvenue = (TextView) findViewById(R.id.textBienvenue);
        bienvenue.setText(bienvenue.getText() + " " + nomPrenom);
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
            AccueilActivity.this.finish();
            startActivity(intent);
        }

        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mes_absences) {
            try {
                JSONObject jsonObject = new JSONObject(userInfos);
                Intent intent2 = new Intent(this, TotalsAbsences.class);
                intent2.putExtra("user", jsonObject.toString());
                this.finish();
                this.startActivity(intent2);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //MesAbsencesFragment absencesFragment = new MesAbsencesFragment();
            //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            //fragmentTransaction.replace(R.id.fragment_container, absencesFragment).commit();
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

            //MesStatistiquesFragment statsFragment = new MesStatistiquesFragment();
            //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            //fragmentTransaction.replace(R.id.fragment_container, statsFragment).commit();

        } else if (id == R.id.prevenir_absence) {
            PrevenirAbsenceFragment prevenirAbsenceFragment = new PrevenirAbsenceFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, prevenirAbsenceFragment).commit();

        } else if (id == R.id.deconnexion) {

            Intent intent = new Intent(this,MainActivity.class);
            AccueilActivity.this.finish();
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    

    public void OnClick(View v){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://absence2epf.net16.net/api/absences.php?token=9958f381ac95dbbb4fdbeed02e2680f8";

        tvRepCours = (TextView) findViewById(R.id.tvRepCours);
        tvRepDateD = (TextView) findViewById(R.id.tvRepHeureD);
        tvRepDateF = (TextView) findViewById(R.id.tvRepHeureF);
        tvRepNomP = (TextView) findViewById(R.id.tvRepNomP);
        tvRepPrenomP = (TextView) findViewById(R.id.tvRepPrenomP);
        tvRepStatut = (TextView) findViewById(R.id.tvRepStatut);

        StringRequest jsObjRequest = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println("Réussiréussi");
                        JSONArray mJsonArray = new JSONArray();
                        try {
                            mJsonArray = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            Object jsonCours = mJsonArray.getJSONObject(0).get("nom");
                            String cours = jsonCours.toString();
                            Object jsonHeureD = mJsonArray.getJSONObject(0).get("heure_debut");
                            String heureD = jsonHeureD.toString();
                            Object jsonHeureF = mJsonArray.getJSONObject(0).get("heure_fin");
                            String heureF = jsonHeureF.toString();
                            Object jsonNomP = mJsonArray.getJSONObject(0).get("nom_prof");
                            String nomP = jsonNomP.toString();
                            Object jsonPrenomP = mJsonArray.getJSONObject(0).get("prenom_prof");
                            String prenomP = jsonPrenomP.toString();
                            Object jsonStatut = mJsonArray.getJSONObject(0).get("statut");
                            String statut = jsonStatut.toString();
                            absences = new AbsenceEleve(cours, heureD, heureF, nomP, prenomP, statut);
                            tvRepCours.setText("  " + absences.getNomMatiere());
                            tvRepDateD.setText("  " + absences.getHeureDebut().toString());
                            tvRepDateF.setText("  " + absences.getHeureFin().toString());
                            tvRepNomP.setText("  " + absences.getNomProf());
                            tvRepPrenomP.setText("  " + absences.getPrenomProf());
                            tvRepStatut.setText("  " + absences.getStatut());
                        } catch (JSONException e) {
                            e.printStackTrace();
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

    public void OnClick2(View v){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://absence2epf.net16.net/api/absences.php?token=9958f381ac95dbbb4fdbeed02e2680f8";

        tvRepCours = (TextView) findViewById(R.id.tvRepCours);
        tvRepDateD = (TextView) findViewById(R.id.tvRepHeureD);
        tvRepDateF = (TextView) findViewById(R.id.tvRepHeureF);
        tvRepNomP = (TextView) findViewById(R.id.tvRepNomP);
        tvRepPrenomP = (TextView) findViewById(R.id.tvRepPrenomP);
        tvRepStatut = (TextView) findViewById(R.id.tvRepStatut);

        StringRequest jsObjRequest = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println("Réussiréussi");
                        JSONArray mJsonArray = new JSONArray();
                        try {
                            mJsonArray = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            Object jsonCours = mJsonArray.getJSONObject(1).get("nom");
                            String cours = jsonCours.toString();
                            Object jsonHeureD = mJsonArray.getJSONObject(1).get("heure_debut");
                            String heureD = jsonHeureD.toString();
                            Object jsonHeureF = mJsonArray.getJSONObject(1).get("heure_fin");
                            String heureF = jsonHeureF.toString();
                            Object jsonNomP = mJsonArray.getJSONObject(1).get("nom_prof");
                            String nomP = jsonNomP.toString();
                            Object jsonPrenomP = mJsonArray.getJSONObject(1).get("prenom_prof");
                            String prenomP = jsonPrenomP.toString();
                            Object jsonStatut = mJsonArray.getJSONObject(1).get("statut");
                            String statut = jsonStatut.toString();
                            absences = new AbsenceEleve(cours, heureD, heureF, nomP, prenomP, statut);
                            tvRepCours.setText("  " + absences.getNomMatiere());
                            tvRepDateD.setText("  " + absences.getHeureDebut().toString());
                            tvRepDateF.setText("  " + absences.getHeureFin().toString());
                            tvRepNomP.setText("  " + absences.getNomProf());
                            tvRepPrenomP.setText("  " + absences.getPrenomProf());
                            tvRepStatut.setText("  " + absences.getStatut());
                        } catch (JSONException e) {
                            e.printStackTrace();
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
    }

    public String getUserInfos() {
        return userInfos;
    }
}
