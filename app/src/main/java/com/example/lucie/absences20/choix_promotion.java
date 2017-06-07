package com.example.lucie.absences20;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
import java.util.List;

/**
 * Created by lucie on 22/05/2017.
 */

public class choix_promotion extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {

    NavigationView navigationView = null;
    ActionBarDrawerToggle toggle;
    private String userInfos;
    private int userType;
    private String promo;
    private String choixAffichage;
    private String token;
    private String[] promos;
    private String[] idPromos;
    private Spinner spinner;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_promotion);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        spinner = (Spinner) findViewById(R.id.spinner);

        promos = getResources().getStringArray(R.array.promos);
        userInfos = getIntent().getStringExtra("user");
        token = getIntent().getStringExtra("token");
        choixAffichage = getIntent().getStringExtra("affichage");

        Requete();

        toggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        } else if (userType == 4) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.navigation_menu_respos);
        }

        navigationView.setNavigationItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        promo = parent.getItemAtPosition(pos).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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

        if (item.getItemId() == R.id.disconnect) {
            Intent intent = new Intent(this, MainActivity.class);
            this.finish();
            startActivity(intent);
        }

        if (toggle.onOptionsItemSelected(item)) {
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

            Intent intent = new Intent(this, MainActivity.class);
            choix_promotion.this.finish();
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

        } else if (id == R.id.stats_promotion) {
            try {
                JSONObject jsonObject = new JSONObject(userInfos);
                Intent intent3 = new Intent(this, choix_promotion.class);
                intent3.putExtra("user", jsonObject.toString());
                intent3.putExtra("affichage", "stats");
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
            nomPrenom = json.getString("prenom") + " " + json.getString("nom").toUpperCase();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getMenuInflater().inflate(R.menu.utilisateur_menu, menu);
        MenuItem item = menu.findItem(R.id.nomUser);
        item.setTitle(nomPrenom);
        return true;
    }

    public void OnClick(View view) {

        if (choixAffichage.equals("promotions")) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(userInfos);
                Intent intent = new Intent(this, AbsencesPromotion.class);
                intent.putExtra("promo", promo);
                intent.putExtra("user", jsonObject.toString());
                intent.putExtra("token", token);
                intent.putExtra("promos", promos);
                intent.putExtra("idPromos", idPromos);
                this.finish();
                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (choixAffichage.equals("direct")) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(userInfos);
                Intent intent = new Intent(this, AbsencesDirect.class);
                intent.putExtra("promo", promo);
                intent.putExtra("user", jsonObject.toString());
                intent.putExtra("token", token);
                intent.putExtra("promos", promos);
                intent.putExtra("idPromos", idPromos);
                this.finish();
                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (choixAffichage.equals("stats")) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(userInfos);
                Intent intent = new Intent(this, StatsPromotion.class);
                intent.putExtra("promo", promo);
                intent.putExtra("user", jsonObject.toString());
                intent.putExtra("token", token);
                intent.putExtra("promos", promos);
                intent.putExtra("idPromos", idPromos);
                this.finish();
                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public void Requete(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2/api/promotions.php?token=" + token;

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

                        ArrayList<String> proms = new ArrayList<>();
                        ArrayList<String> idProms = new ArrayList<>();

                        if(mJsonArray.length() == 0){
                            try {
                                Object jsonPromo = mJsonInfos.get("nom");
                                String promo = jsonPromo.toString();
                                Object jsonIdPromo = mJsonInfos.get("id");
                                String idPromo = jsonIdPromo.toString();
                                proms.add(promo);
                                idProms.add(idPromo);

                                promos = (String[]) proms.toArray();
                                idPromos = (String[]) idProms.toArray();
                            } catch( JSONException e){
                                e.printStackTrace();
                            }
                        } else {

                            try {

                                for (int i = 0; i < mJsonArray.length(); i++) {
                                    Object jsonPromo = mJsonArray.getJSONObject(i).get("nom");
                                    String promo = jsonPromo.toString();
                                    Object jsonIdPromo = mJsonArray.getJSONObject(i).get("id");
                                    String idPromo = jsonIdPromo.toString();
                                    proms.add(promo);
                                    idProms.add(idPromo);
                                }
                                promos = proms.toArray(new String [proms.size()]);
                                idPromos = idProms.toArray(new String [idProms.size()]);
                                FillSpinner(proms);
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

    public void FillSpinner(List<String> proms){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, proms);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
}
