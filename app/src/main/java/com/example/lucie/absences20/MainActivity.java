package com.example.lucie.absences20;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
        wrongPassword = (TextView) findViewById(R.id.textViewDirect);
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
                        if (!response.equals("0")) {
                            startActivity(intent);
                        } else {
                            Context context = getApplicationContext();
                            CharSequence text = message;

                            Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        //wrongPassword.setText(message);
                        Context context = getApplicationContext();
                        CharSequence text = "Erreur lors de la connexion ! Veuillez recommencer";

                        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
        queue1.add(jsObjRequest);
    }
}
