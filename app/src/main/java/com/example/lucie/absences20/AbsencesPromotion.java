package com.example.lucie.absences20;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.Arrays;
import java.util.List;

/**
 * Created by lucie on 22/05/2017.
 */

public class AbsencesPromotion extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener, View.OnClickListener, AdapterView.OnItemClickListener {

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
    private ArrayList<InfosAbsencesPromotion> infos;
    private AbsencesPromoListeAdapter adapter;
    private EditText recherchePromo;

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absences_promotion);
        spinner = (Spinner) findViewById(R.id.spinner2);
        recherchePromo = (EditText) findViewById(R.id.filtreProm);

        mListView = (ListView) findViewById(R.id.listViewPromo);
        promos = getIntent().getStringArrayExtra("promos");
        idPromos = getIntent().getStringArrayExtra("idPromos");
        Button button = (Button) findViewById(R.id.buttonGoStats);
        button.setOnClickListener(this);
        mListView.setOnItemClickListener(this);

        List<String> list = Arrays.asList(promos);
        FillSpinner(list);

        userInfos = getIntent().getStringExtra("user");
        promo = getIntent().getStringExtra("promo");
        token = getIntent().getStringExtra("token");

        String idSelection = String.valueOf(Arrays.asList(promos).indexOf(promo));
        Log.d(TAG, "OnCreate : started. ");

        spinner.setSelection(Integer.valueOf(idSelection));

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

    public void AfficherAbsencePromo (String id){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2/api/absences_promotion.php?id=" + id + "&token=" + token;

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

                        infos = new ArrayList<>();

                        if(mJsonArray.length() == 0){
                            try {
                                Object jsonId = mJsonInfos.get("id");
                                String id = jsonId.toString();
                                Object jsonCours = mJsonInfos.get("matiere");
                                String cours = jsonCours.toString();
                                Object jsonHeureD = mJsonInfos.get("heure_debut");
                                String heureD = jsonHeureD.toString();
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
                                InfosAbsencesPromotion absence = new InfosAbsencesPromotion(id, cours, heureD, prenomE, nomE, prenomP, nomP, statut);
                                infos.add(absence);

                                adapter = new AbsencesPromoListeAdapter(getApplicationContext(), infos);
                                mListView.setAdapter(adapter);
                            } catch( JSONException e){
                                e.printStackTrace();
                            }
                        } else {

                            try {

                                for (int i = 0; i < mJsonArray.length(); i++) {

                                    Object jsonId = mJsonArray.getJSONObject(i).get("id");
                                    String id = jsonId.toString();
                                    Object jsonCours = mJsonArray.getJSONObject(i).get("matiere");
                                    String cours = jsonCours.toString();
                                    Object jsonHeureD = mJsonArray.getJSONObject(i).get("heure_debut");
                                    String heureD = jsonHeureD.toString();
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

                                    InfosAbsencesPromotion absence = new InfosAbsencesPromotion(id, cours, heureD, prenomE, nomE, prenomP, nomP, statut);
                                    infos.add(absence);
                                }



                                adapter = new AbsencesPromoListeAdapter(getApplicationContext(), infos);
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

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.buttonGoStats :
            Intent intent = new Intent(this, StatsPromotion.class);
            intent.putExtra("token", token);
            intent.putExtra("promos", promos);
            intent.putExtra("idPromos", idPromos);
            intent.putExtra("promo", promo);
            intent.putExtra("user", userInfos);
            this.finish();
            startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final InfosAbsencesPromotion absence = infos.get(position);

        TextView title = new TextView(this);
        title.setText("Statut de l'absence :");
        title.setBackgroundColor(Color.GRAY);
        title.setPadding(10, 20, 10, 20);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.BLACK);
        title.setTextSize(24);
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        View dialog = View.inflate(this,R.layout.affichage_dialog_statut,null);
        adb.setCustomTitle(title);
        adb.setView(dialog);
        TextView tvInfos = (TextView) dialog.findViewById(R.id.tvInfos);
        tvInfos.setText("Date : " + absence.getDate() + "  -  Élève : " + absence.getPrenom_eleve() + " " + absence.getNom_eleve() + "  -  Cours : " + absence.getModule() + "  -  Statut actuel : " + absence.getStatut());
        final Spinner spin = (Spinner) dialog.findViewById(R.id.spinnerStatut);
        Button buttonSuppr = (Button) dialog.findViewById(R.id.btnSupprimer);

        final AlertDialog dialogCreate = adb.show();
        buttonSuppr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModifierStatut(absence.getId(), "delete");
                String idSelection = String.valueOf(Arrays.asList(promos).indexOf(promo));
                String idPromo = idPromos[Integer.valueOf(idSelection)];
                AfficherAbsencePromo(idPromo);
                dialogCreate.dismiss();
                Toast toast = Toast.makeText(getApplicationContext(), "Suppression effectuée avec succès !", Toast.LENGTH_LONG);
                toast.show();

            }
        });
        Button buttonValid = (Button) dialog.findViewById(R.id.btnValider);
        buttonValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModifierStatut(absence.getId(), spin.getSelectedItem().toString());
                String idSelection = String.valueOf(Arrays.asList(promos).indexOf(promo));
                String idPromo = idPromos[Integer.valueOf(idSelection)];
                AfficherAbsencePromo(idPromo);
                dialogCreate.dismiss();
                Toast toast = Toast.makeText(getApplicationContext(), "Modification effectuée avec succès !", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        Button buttonAnnul = (Button) dialog.findViewById(R.id.btnAnnuler);
        buttonAnnul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCreate.dismiss();
            }
        });
    }

    public void onClickFilterPromo(View v){
        adapter.getFilter(recherchePromo.getText().toString());
        mListView.setAdapter(adapter);

    }

    public void onClickUnfilterPromo(View v){
        recherchePromo.setText(null);
        adapter.getUnfilter();
        mListView.setAdapter(adapter);
    }

    public void ModifierStatut (String id, String statut){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2/api/statut.php?token=" + token +"&statut=" + statut +"&id=" + id;

        StringRequest jsObjRequest = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println("Réussiréussi");
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
