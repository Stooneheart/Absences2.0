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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirecting);

        String token = getIntent().getStringExtra("token");
        token = token.substring(1,token.length()-1);
        String url = "http://absence2epf.net16.net/api/utilisateur.php?token="+token;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final Intent intent = new Intent(this, AccueilActivity.class);
        StringRequest jsObjReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String type = jsonObject.get("type").toString();
                    System.out.println("Ca marche " + type);
                    intent.putExtra("user", jsonObject.toString());

                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
            }
        });
        requestQueue.add(jsObjReq);
    }
}
