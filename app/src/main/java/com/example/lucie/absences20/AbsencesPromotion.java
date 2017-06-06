package com.example.lucie.absences20;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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
import java.util.Arrays;
import java.util.List;

/**
 * Created by lucie on 22/05/2017.
 */

public class AbsencesPromotion extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {

    NavigationView navigationView = null;
    ActionBarDrawerToggle toggle;
    private String userInfos;
    private int userType;
    private static String TAG="TotalsAbsencesProm";
    private ListView mListView;
    private String promo;
    private String token;
    private String [] promos;
    private String [] idPromos;
    private Spinner spinner;

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absences_promotion);
        spinner = (Spinner) findViewById(R.id.spinner2);

        mListView = (ListView) findViewById(R.id.listViewPromo);
        promos = getIntent().getStringArrayExtra("promos");
        idPromos = getIntent().getStringArrayExtra("idPromos");

        List<String> list = Arrays.asList(promos);
        FillSpinner(list);

        userInfos = getIntent().getStringExtra("user");
        promo = getIntent().getStringExtra("promo");
        token = getIntent().getStringExtra("token");

        String idSelection = String.valueOf(Arrays.asList(promos).indexOf(promo));
        Log.d(TAG, "OnCreate : started. ");

        spinner.setSelection(Integer.valueOf(idSelection));

        String id = idPromos[Integer.valueOf(idSelection)];

        AfficherAbsencePromo(id);

        userType = 0;
        try {
            JSONObject jsonObject = new JSONObject(userInfos);
            userType = jsonObject.getInt("type");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (userType == 3) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.navigation_menu_scola);
        }

        navigationView.setNavigationItemSelectedListener(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                intent3.putExtra("token", token);
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
                intent3.putExtra("token", token);
                this.finish();
                this.startActivity(intent3);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (id == R.id.deconnexion) {

            Intent intent = new Intent(this,MainActivity.class);
            AbsencesPromotion.this.finish();
            startActivity(intent);
        } else if (id == R.id.absences_anticipees) {
            try {
                JSONObject jsonObject = new JSONObject(userInfos);
                Intent intent3 = new Intent(this, AbsencesAnticipees.class);
                intent3.putExtra("user", jsonObject.toString());
                intent3.putExtra("token", token);
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
                intent3.putExtra("affichage", "direct");
                intent3.putExtra("token", token);
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
                intent3.putExtra("token", token);
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
                intent3.putExtra("affichage", "promotions");
                intent3.putExtra("token", token);
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
                intent3.putExtra("token", token);
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

    public void AfficherAbsencePromo (String id){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://absence2epf.net16.net/api/absences_promotion.php?id=" + id + "&token=" + token;

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

                        ArrayList<InfosAbsencesPromotion> infos = new ArrayList<>();

                        if(mJsonArray.length() == 0){
                            try {
                                Object jsonCours = mJsonInfos.get("matiere");
                                String cours = jsonCours.toString();
                                Object jsonHeureD = mJsonInfos.get("heure_debut");
                                String heureD = jsonHeureD.toString();
//                                Object jsonHeureF = mJsonArray.getJSONObject(i).get("heure_fin");
//                                String heureF = jsonHeureF.toString();
                                Object jsonNomP = mJsonInfos.get("nom_professeur");
                                String nomP = jsonNomP.toString();
                                Object jsonPrenomP = mJsonInfos.get("prenom_professeur");
                                String prenomP = jsonPrenomP.toString();
                                Object jsonPrenomE = mJsonInfos.get("prenom_eleve");
                                String prenomE = jsonPrenomE.toString();
                                Object jsonNomE = mJsonInfos.get("nom_eleve");
                                String nomE = jsonNomE.toString();
                                Object jsonStatut = mJsonInfos.get("statut");
                                String statut = jsonStatut.toString();
                                InfosAbsencesPromotion absence = new InfosAbsencesPromotion(cours, heureD, prenomP, nomP, prenomE, nomE, statut);
                                infos.add(absence);

                                AbsencesPromoListeAdapter adapter = new AbsencesPromoListeAdapter(getApplicationContext(), R.layout.affichage_absences_promo, infos);
                                mListView.setAdapter(adapter);
                            } catch( JSONException e){
                                e.printStackTrace();
                            }
                        } else {

                            try {

                                for (int i = 0; i < mJsonArray.length(); i++) {
                                    Object jsonCours = mJsonArray.getJSONObject(i).get("matiere");
                                    String cours = jsonCours.toString();
                                    Object jsonHeureD = mJsonArray.getJSONObject(i).get("heure_debut");
                                    String heureD = jsonHeureD.toString();
//                                    Object jsonHeureF = mJsonArray.getJSONObject(i).get("heure_fin");
//                                    String heureF = jsonHeureF.toString();
                                    Object jsonNomP = mJsonArray.getJSONObject(i).get("nom_professeur");
                                    String nomP = jsonNomP.toString();
                                    Object jsonPrenomP = mJsonArray.getJSONObject(i).get("prenom_professeur");
                                    String prenomP = jsonPrenomP.toString();
                                    Object jsonPrenomE = mJsonArray.getJSONObject(i).get("prenom_eleve");
                                    String prenomE = jsonPrenomE.toString();
                                    Object jsonNomE = mJsonArray.getJSONObject(i).get("nom_eleve");
                                    String nomE = jsonNomE.toString();
                                    Object jsonStatut = mJsonArray.getJSONObject(i).get("statut");
                                    String statut = jsonStatut.toString();

                                    InfosAbsencesPromotion absence = new InfosAbsencesPromotion(cours, heureD, prenomP, nomP, prenomE, nomE, statut);
                                    infos.add(absence);
                                }



                                AbsencesPromoListeAdapter adapter = new AbsencesPromoListeAdapter(getApplicationContext(), R.layout.affichage_absences_promo, infos);
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
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        promo = parent.getItemAtPosition(position).toString();
        String idSelection = String.valueOf(Arrays.asList(promos).indexOf(promo));
        String idPromo = idPromos[Integer.valueOf(idSelection)];

        AfficherAbsencePromo(idPromo);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void FillSpinner(List<String> proms){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, proms);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
}
