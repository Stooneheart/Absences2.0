package com.example.lucie.absences20;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    String message;
    TextView wrongPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.editText3);
        password = (EditText) findViewById(R.id.editText4);
        Resources res = getResources();
        message = String.format(res.getString(R.string.WPassword));
        wrongPassword = (TextView) findViewById(R.id.textView2);
    }



    public void onClick(View v) {
        Intent intent = new Intent(this, AccueilActivity.class);
        startActivity(intent);
    }




    public void login(View view) {
        if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
            Intent intent = new Intent(this, AccueilActivity.class);
            startActivity(intent);
            //correcct password
        } else {
            //wrong password
            wrongPassword.setText(message);
        }

    }
}
