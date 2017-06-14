package com.example.lucie.absences20;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucie on 22/05/2017.
 */

public class MesStatistiques extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    NavigationView navigationView = null;
    ActionBarDrawerToggle toggle;
    private String userInfos;
    private String token;
    private String nomPrenom;

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mes_statistiques);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button button = (Button) findViewById(R.id.buttonListeAbs);
        button.setOnClickListener(this);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        userInfos = getIntent().getStringExtra("user");
        token = getIntent().getStringExtra("token");

        StatsRequete();
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
            Intent intent3 = new Intent(this, MesStatistiques.class);
            intent3.putExtra("user", userInfos);
            startActivity(intent3);
        } else if (id == R.id.deconnexion) {

            Intent intent = new Intent(this,MainActivity.class);
            MesStatistiques.this.finish();
            startActivity(intent);
        } else if (id == R.id.absences_direct) {
            try {
                JSONObject jsonObject = new JSONObject(userInfos);
                Intent intent3 = new Intent(this, AbsencesDirect.class);
                intent3.putExtra("user", jsonObject.toString());
                intent3.putExtra("affichage", "direct");
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
                intent3.putExtra("affichage", "promotions");
                this.finish();
                this.startActivity(intent3);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }  else if (id == R.id.alertes) {
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
        this.nomPrenom = nomPrenom;
        return true;
    }

    public void StatsRequete (){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.absencesepf.fr/api/absences.php?token=" + token;

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

                        ArrayList<String> infos = new ArrayList<>();

                        if(mJsonArray.length() == 0){
                            try {
                                Object jsonNom = mJsonInfos.get("nom");
                                String nom = jsonNom.toString();
                                infos.add(nom);
                            } catch( JSONException e){
                                e.printStackTrace();
                            }
                        } else {

                            try {

                                for (int i = 0; i < mJsonArray.length(); i++) {
                                    Object jsonNom = mJsonArray.getJSONObject(i).get("nom");
                                    String nom = jsonNom.toString();
                                    infos.add(nom);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        Set<String> set = new HashSet<>(infos);
                        ArrayList<String> infosNonDup = new ArrayList<>();
                        infosNonDup.addAll(set);
                        DataPoint[] datas = new DataPoint[infosNonDup.size() + 2];
                        String[] datasName = new String[infosNonDup.size() + 2];
                        datas[0] = new DataPoint(0, 0);
                        datasName[0] = "";

                        for (String info : infosNonDup){
                            int occurence = Collections.frequency(infos, info);
                            datas[infosNonDup.indexOf(info)+1] = new DataPoint(infosNonDup.indexOf(info)+1,occurence);
                            datasName[infosNonDup.indexOf(info)+1] = info.replace(" ", "\n");
                        }

                        datas[infosNonDup.size()+1] = new DataPoint(infosNonDup.size()+1, 0);
                        datasName[infosNonDup.size()+1] = "";

                        GraphView graph = (GraphView) findViewById(R.id.graphEleve);
                        graph.removeAllSeries();
                        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(datas);
                        graph.addSeries(series);

// styling
                        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                            @Override
                            public int get(DataPoint data) {
                                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
                            }
                        });

                        series.setSpacing(50);

// draw values on top
                        series.setDrawValuesOnTop(true);
                        series.setValuesOnTopColor(Color.BLACK);
                        series.setValuesOnTopSize(20);

                        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
                        staticLabelsFormatter.setHorizontalLabels(datasName);
                        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
                        graph.getGridLabelRenderer().setLabelHorizontalHeight(80);
                        graph.getGridLabelRenderer().setVerticalAxisTitleTextSize(25);
                        graph.getGridLabelRenderer().setHorizontalAxisTitleTextSize(25);

                        graph.setTitle("Répartition des absences par matière de " + nomPrenom);
                        graph.setTitleTextSize(25);
                        graph.getGridLabelRenderer().setVerticalAxisTitle("Nombre d'absences");

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
        Intent intent = new Intent(this, TotalsAbsences.class);
        intent.putExtra("user", userInfos);
        intent.putExtra("token", token);
        this.finish();
        startActivity(intent);
    }
}
