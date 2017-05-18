package com.example.lucie.absences20;

import android.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class AccueilActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private AbsenceEleve absences;
    private TextView tvRepCours;
    private TextView tvRepDateD;
    private TextView tvRepDateF;
    private TextView tvRepPrenomP;
    private TextView tvRepNomP;
    private TextView tvRepStatut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){

        int id = item.getItemId();
        FragmentManager fragmentM = getFragmentManager();

        android.app.FragmentManager fragmentManager = getFragmentManager();
        if (id == R.id.mes_absences){
            fragmentManager.beginTransaction().replace(R.id.content_frame, new MesAbsencesFragment()).commit();
        } else if (id == R.id.mes_statistiques){
            fragmentManager.beginTransaction().replace(R.id.content_frame, new MesStatistiquesFragment()).commit();
        } else if (id == R.id.prevenir_absence){
            fragmentManager.beginTransaction().replace(R.id.content_frame, new PrevenirAbsenceFragment()).commit();
        } else if (id == R.id.deconnexion){

        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
}

