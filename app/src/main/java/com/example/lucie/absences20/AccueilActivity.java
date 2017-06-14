package com.example.lucie.absences20;

import android.content.Intent;
import android.graphics.Color;
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
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;


public class AccueilActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    ActionBarDrawerToggle toggle;
    private int userType;
    private String token;

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
        userInfos = getIntent().getStringExtra("user");
        token = getIntent().getStringExtra("token");
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
        AfficherDashboard();
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
            AccueilActivity.this.finish();
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

    public String getUserInfos() {
        return userInfos;
    }

    public void AfficherDashboard(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.absencesepf.fr/api/promotions.php?token=" + token;

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

                        ArrayList<InfosDashBoardAbsences> infosAbs = new ArrayList<>();

                        if(mJsonArray.length() == 0){
                            try {
                                Object jsonNbrEleves = mJsonInfos.get("nbr");
                                String eleve = jsonNbrEleves.toString();
                                Object jsonNbrAbs = mJsonInfos.get("abs");
                                String abs = jsonNbrAbs.toString();
                                Object jsonPromo = mJsonInfos.get("nom");
                                String promo = jsonPromo.toString();
                                String moyenne = String.valueOf(Double.valueOf(abs)/Double.valueOf(eleve));
                                InfosDashBoardAbsences labsence = new InfosDashBoardAbsences(abs,promo,moyenne, eleve);
                                infosAbs.add(labsence);
                            } catch( JSONException e){
                                e.printStackTrace();
                            }
                        } else {

                            try {

                                for (int i = 0; i < mJsonArray.length(); i++) {
                                    Object jsonNbrEleves = mJsonArray.getJSONObject(i).get("nbr");
                                    String eleve = jsonNbrEleves.toString();
                                    Object jsonNbrAbs = mJsonArray.getJSONObject(i).get("abs");
                                    String abs = jsonNbrAbs.toString();
                                    Object jsonPromo = mJsonArray.getJSONObject(i).get("nom");
                                    String promo = jsonPromo.toString();
                                    String moyenne = String.valueOf(Double.valueOf(abs)/Double.valueOf(eleve));
                                    InfosDashBoardAbsences labsence = new InfosDashBoardAbsences(abs,promo, moyenne, eleve);
                                    infosAbs.add(labsence);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        ///////////// NOMBRE D ABSENCES /////////////////
                        DataPoint[] datasAbs = new DataPoint[infosAbs.size()+2];
                        String[] datasPromos = new String[infosAbs.size()+2];
                        datasAbs[0] = new DataPoint(0, 0);
                        datasPromos[0] = "";
                        for (InfosDashBoardAbsences info : infosAbs){
                            datasAbs[infosAbs.indexOf(info)+1] = new DataPoint(infosAbs.indexOf(info)+1,Double.valueOf(info.getMoyenneAbs()));
                            datasPromos[infosAbs.indexOf(info)+1] = info.getPromo();
                        }

                        datasAbs[infosAbs.size()+1] = new DataPoint(infosAbs.size()+1,0);
                        datasPromos[infosAbs.size()+1] = "";

                        GraphView graphAbs = (GraphView) findViewById(R.id.graphNbrAbsences);
                        graphAbs.removeAllSeries();
                        BarGraphSeries<DataPoint> seriesAbs = new BarGraphSeries<>(datasAbs);
                        graphAbs.addSeries(seriesAbs);

// styling
                        seriesAbs.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                            @Override
                            public int get(DataPoint data) {
                                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
                            }
                        });

                        seriesAbs.setSpacing(50);

// draw values on top
                        seriesAbs.setDrawValuesOnTop(true);
                        seriesAbs.setValuesOnTopColor(Color.BLACK);
                        seriesAbs.setValuesOnTopSize(20);

                        StaticLabelsFormatter staticLabelsFormatterAbs = new StaticLabelsFormatter(graphAbs);
                        staticLabelsFormatterAbs.setHorizontalLabels(datasPromos);
                        graphAbs.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatterAbs);
                        graphAbs.getGridLabelRenderer().setVerticalAxisTitleTextSize(20);
                        graphAbs.getGridLabelRenderer().setHorizontalAxisTitleTextSize(25);

                        graphAbs.setTitle("Répartition du nombre moyen d'absences par élève et par promotion : ");
                        graphAbs.setTitleTextSize(30);
                        graphAbs.getGridLabelRenderer().setVerticalAxisTitle("Nombre moyen d'absences");

                        TextView tvNbrEleves = (TextView) findViewById(R.id.tvRepNbrEleves);
                        TextView tvMoyAbs = (TextView) findViewById(R.id.tvRepMoyenneAbs);

                        String nbrEleves = "";
                        String moyenAbs = "";
                        for (InfosDashBoardAbsences abs : infosAbs){
                            nbrEleves = nbrEleves + abs.getPromo() + " : " + abs.getNbrEleves() + "      -      ";
                            moyenAbs = moyenAbs + abs.getPromo() + " : " + abs.getNbrAbs() + "      -      ";
                        }

                        tvNbrEleves.setText(nbrEleves.substring(0,nbrEleves.length()-13));
                        tvMoyAbs.setText(moyenAbs.substring(0,nbrEleves.length()-13));

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
