package com.example.lucie.absences20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RedirectingActivitiy extends AppCompatActivity {

    private String token;
    private String type;
    private Intent intent;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirecting);

        token = getIntent().getStringExtra("token");
        token = token.substring(1,token.length()-1);
        String url = "http://www.absencesepf.fr/api/utilisateur.php?token="+token;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        intent = new Intent(this, AccueilActivity.class);
        StringRequest jsObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    jsonObject = new JSONObject(response);
                    type = jsonObject.get("type").toString();
                    System.out.println("Ca marche " + type);
                    intent.putExtra("user", jsonObject.toString());
                    intent.putExtra("token", token);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RedirectingActivitiy.this.finish();
                chooseActivityToLoad(intent, type);

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
            }
        });
        requestQueue.add(jsObjReq);
    }

    public void chooseActivityToLoad(Intent intent, String type){
        if(type.equals("1")) {
            intent = new Intent(this, TotalsAbsences.class);
            intent.putExtra("user", jsonObject.toString());
            intent.putExtra("token", token);
        } else if(type.equals("3")){
            intent = new Intent(this, AccueilActivity.class);
            intent.putExtra("user", jsonObject.toString());
            intent.putExtra("token", token);
        } else if(type.equals("4")){
            intent = new Intent(this, choix_promotion.class);
            intent.putExtra("user", jsonObject.toString());
            intent.putExtra("token", token);
            intent.putExtra("affichage", "promotions");
        }

        startActivity(intent);
    }
}
