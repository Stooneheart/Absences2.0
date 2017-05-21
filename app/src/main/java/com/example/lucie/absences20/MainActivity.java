package com.example.lucie.absences20;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    String message;
    TextView wrongPassword;
    Intent intent;
    String type;
    RequestQueue queue1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();
        message = String.format(res.getString(R.string.WPassword));
        wrongPassword = (TextView) findViewById(R.id.textView2);
    }


    public void onClick(View v) {
        Intent intent1 = new Intent(this, AccueilActivity.class);
        startActivity(intent1);
    }


    public void login(View view) {
        username = (EditText) findViewById(R.id.editText3);
        password = (EditText) findViewById(R.id.editText4);
        String usname = username.getText().toString();
        String pword = password.getText().toString();
        queue1 = Volley.newRequestQueue(this);
        String url = "http://absence2epf.net16.net/api/token.php?identifiant=" + usname + "&pw=" + pword;
        intent = new Intent(this, RedirectingActivitiy.class);

        StringRequest jsObjRequest = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        intent.putExtra("token", response);
                        startActivity(intent);
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        wrongPassword.setText(message);
                    }
                });
        queue1.add(jsObjRequest);
    }
}
