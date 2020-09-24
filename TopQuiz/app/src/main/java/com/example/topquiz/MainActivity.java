package com.example.topquiz;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.view.MenuItem;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {
    CheckBox checkBox;
    String uri ="geo:0,0";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse(uri);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
        final EditText passWord = (EditText) findViewById(R.id.edit_password);
        final Button loginButton = (Button) findViewById(R.id.btn_submit);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passWord.getText().toString().equals("senegal")) {
                    Intent myIntent = new Intent(MainActivity.this, choose.class);
                    startActivity(myIntent);
                    finish();
                }else{
                    show("Mauvais mot de passe, veuillez réessayer");
                    finish();}
            }
        });
    }
    public void show(String str)
    {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
}
