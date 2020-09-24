package com.example.topquiz;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class parametre extends AppCompatActivity {


    private TextView poidsnet, poidsbrut, rejet, jour, trait, supprimer;

    private SQLiteDatabase sql;
    private Button valider;
    DatePickerDialog datePickerDialog;
    DatabaseHelper myDb;

    public parametre(){};

    public parametre(TextView supprimer, SQLiteDatabase sql, TextView poidsnet, Button valider, TextView poidsbrut, TextView rejet, TextView jour, TextView trait){

        this.poidsnet = poidsnet;
        this.poidsbrut = poidsbrut;
        this.rejet= rejet;
        this.jour = jour;
        this.trait = trait;
        this.supprimer = supprimer;
        this.sql = sql;



        this.valider = valider;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre);
        myDb = new DatabaseHelper(this);

        this.poidsnet= (TextView) findViewById(R.id.poidsnet);
        this.poidsbrut= (TextView) findViewById(R.id.poidsbrut);
        this.rejet= (TextView) findViewById(R.id.rejet);
        this.trait= (TextView) findViewById(R.id.numerotrait);
        this.jour= (TextView) findViewById(R.id.jour);
        this.valider = (Button) findViewById(R.id.valider);
        this.supprimer =(TextView) findViewById(R.id.supprimer);
        this.jour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int jj = c.get(Calendar.DAY_OF_MONTH);
                int mm = c.get(Calendar.MONTH);
                int yyyy = c.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(parametre.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        jour.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },yyyy,mm,jj);
                datePickerDialog.show();
            }
        });




        this.supprimer.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parametre.this, supparametre.class);
                startActivity(intent);

            }
        });

        adData();
    }

    private void adData() {
        this.valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pdsnet = poidsnet.getText().toString();
                String pdsbrut = poidsbrut.getText().toString();
                String rej = rejet.getText().toString();
                String jou = jour.getText().toString();
                String trai = trait.getText().toString();


                if (TextUtils.isEmpty(pdsnet) || TextUtils.isEmpty(pdsbrut) || TextUtils.isEmpty(rej) ||TextUtils.isEmpty(jou) || TextUtils.isEmpty(trai) ) {
                    Toast.makeText(parametre.this, "Remplissez tous les cases", Toast.LENGTH_LONG).show();
                } else {

                    boolean isInserted = myDb.insertData3( pdsnet, pdsbrut, rej, jou, trai);
                    if (isInserted) {

                        Toast.makeText(parametre.this, "Données enregistrées avec succés", Toast.LENGTH_LONG).show();

                        poidsnet.setText("");
                        poidsbrut.setText("");
                        rejet.setText("");
                        jour.setText("");
                        trait.setText("");

                    } else
                        Toast.makeText(parametre.this, "Erreur dans l'enregistrement des données", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    public JSONArray convertTOJSONARRAY(){
        List laliste = new ArrayList();
        laliste.add( poidsnet.getText().toString());
        laliste.add(poidsbrut.getText().toString());
        laliste.add(jour.getText().toString());
        laliste.add(trait.getText().toString());

        return new JSONArray(laliste);
    }


}
