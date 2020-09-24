package com.example.topquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class choose extends AppCompatActivity {

    private TextView parametremaree;
    private TextView ajoutrait;
    private TextView inventairetrait;
    private TextView affichage;

    public choose(){};
    public choose(TextView parametremaree, TextView ajoutrait, TextView inventairetrait,TextView affichage){
        this.parametremaree = parametremaree;
        this.ajoutrait = ajoutrait;
        this.inventairetrait = inventairetrait;
        this.affichage = affichage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        this.parametremaree = (TextView) findViewById(R.id.parametremaree);
        this.ajoutrait = (TextView) findViewById(R.id.ajoutrait);
        this.inventairetrait = (TextView) findViewById(R.id.inventairetrait);
        this.affichage= (TextView) findViewById(R.id.datadisplay);
        this.parametremaree.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(choose.this, addInfo.class);
                startActivity(intent);
            }
        });
        this.ajoutrait.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent inte = new Intent(choose.this, daily.class);
                startActivity(inte);
            }
        });
        this.inventairetrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(choose.this, parametre.class);
                startActivity(intent);
            }
        });
        this.affichage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(choose.this, affichage.class);
                startActivity(intent);
            }
        });
    }



}
