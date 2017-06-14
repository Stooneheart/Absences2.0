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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

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

public class StatsClassementPromotion extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener, View.OnClickListener {

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
        setContentView(R.layout.activity_stats_classement);
        spinner = (Spinner) findViewById(R.id.spinner2);

        Button button = (Button) findViewById(R.id.buttonGraph);
        button.setOnClickListener(this);

        Button button2 = (Button) findViewById(R.id.buttonGoList);
        button2.setOnClickListener(this);

        mListView = (ListView) findViewById(R.id.listViewClassement);
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

        AfficherClassementPromo(id);

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

        } else if (id == R.id.dashboard) {
            try {
                JSONObject jsonObject = new JSONObject(userInfos);
                Intent intent3 = new Intent(this, AccueilActivity.class);
                intent3.putExtra("user", jsonObject.toString());
                intent3.putExtra("token", token);
                this.finish();
                this.startActivity(intent3);
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

        } else if (id == R.id.deconnexion) {

            Intent intent = new Intent(this,MainActivity.class);
            StatsClassementPromotion.this.finish();
            startActivity(intent);
        } else if (id == R.id.absences_direct) {
            try {
                JSONObject jsonObject = new JSONObject(userInfos);
                Intent intent3 = new Intent(this, AbsencesDirect.class);
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
            nomPrenom= json.getString("prenom") + " " + json.getString("nom").toUpperCase();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getMenuInflater().inflate(R.menu.utilisateur_menu, menu);
        MenuItem item =  menu.findItem(R.id.nomUser);
        item.setTitle(nomPrenom);
        return true;
    }

    public void AfficherClassementPromo (String id){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.absencesepf.fr/api/eleves.php?token=" + token + "&id=" + id;

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

                        if(mJsonArray.length() ==0) {
                            try {

                                mJsonInfos = new JSONObject(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        ArrayList<InfosClassement> infos = new ArrayList<>();

                        if(mJsonArray.length() == 0){
                            try {
                                Object jsonPrenom = mJsonInfos.get("prenom");
                                String prenom = jsonPrenom.toString();
                                Object jsonNom = mJsonInfos.get("nom");
                                String nom = jsonNom.toString();
//                                Object jsonHeureF = mJsonArray.getJSONObject(i).get("heure_fin");
//                                String heureF = jsonHeureF.toString();
                                Object jsonNbr = mJsonInfos.get("nbr_absences");
                                String nbrAbsences = jsonNbr.toString();
                                InfosClassement classement = new InfosClassement(prenom, nom, nbrAbsences);
                                infos.add(classement);

                                ClassementPromoListeAdapter adapter = new ClassementPromoListeAdapter(getApplicationContext(), R.layout.affichage_classement_promo, infos);
                                mListView.setAdapter(adapter);
                            } catch( JSONException e){
                                e.printStackTrace();
                            }
                        } else {

                            try {

                                for (int i = 0; i < mJsonArray.length(); i++) {
                                    Object jsonPrenom = mJsonArray.getJSONObject(i).get("prenom");
                                    String prenom = jsonPrenom.toString();
                                    Object jsonnom = mJsonArray.getJSONObject(i).get("nom");
                                    String nom = jsonnom.toString();
//                                    Object jsonHeureF = mJsonArray.getJSONObject(i).get("heure_fin");
//                                    String heureF = jsonHeureF.toString();
                                    Object jsonNbr = mJsonArray.getJSONObject(i).get("nbr_absences");
                                    String nbrAbsences = jsonNbr.toString();

                                    InfosClassement classement = new InfosClassement(prenom, nom, nbrAbsences);
                                    infos.add(classement);
                                }

                                ClassementPromoListeAdapter adapter = new ClassementPromoListeAdapter(getApplicationContext(), R.layout.affichage_classement_promo, infos);
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
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.buttonGraph :
            Intent intent = new Intent(this, StatsPromotion.class);
            intent.putExtra("token", token);
            intent.putExtra("promos", promos);
            intent.putExtra("idPromos", idPromos);
            intent.putExtra("promo", promo);
            intent.putExtra("user", userInfos);
            this.finish();
            startActivity(intent);
                break;
            case R.id.buttonGoList :
                Intent intent2 = new Intent(this, AbsencesPromotion.class);
                intent2.putExtra("token", token);
                intent2.putExtra("promos", promos);
                intent2.putExtra("idPromos", idPromos);
                intent2.putExtra("promo", promo);
                intent2.putExtra("user", userInfos);
                this.finish();
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        promo = parent.getItemAtPosition(position).toString();
        String idSelection = String.valueOf(Arrays.asList(promos).indexOf(promo));
        String idPromo = idPromos[Integer.valueOf(idSelection)];

        AfficherClassementPromo(idPromo);
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
