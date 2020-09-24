package com.example.topquiz;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class daily extends AppCompatActivity {

    private TextView jour, supprimer;
    private TextView date;
    private TextView heure;
    private TextView ntrait;
    private Spinner virecalet;
    private SQLiteDatabase sql;
    private TextView latitude;
    private TextView longitude;
    private TextView profondeur;
    private TextView poids;
    private Button valider;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    DatabaseHelper myDb;


    public daily(){};

    public daily( TextView supprimer,SQLiteDatabase sql,TextView ntrait,TextView jour,TextView date, EditText heure, Spinner virecalet, TextView latitude, TextView longitude, TextView profondeur, TextView poids, Button valider, ScrollView scrollView){
        this.ntrait = ntrait;

        this.jour = jour ;
        this.date= date;
        this.heure = heure;
        this.virecalet = virecalet;
        this.sql = sql;
        this.latitude = latitude;
        this.longitude = longitude;
        this.profondeur = profondeur;
        this.poids = poids;
        this.valider = valider;
        this.supprimer = supprimer;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        myDb = new DatabaseHelper(this);
        this.jour = (TextView) findViewById(R.id.idjour) ;
     this.date= (TextView) findViewById(R.id.date);
     this.heure = (TextView) findViewById(R.id.heure);
      this.virecalet = (Spinner) findViewById(R.id.virecalet);
        this.ntrait = (TextView) findViewById(R.id.ntrait);
        this.latitude = (TextView) findViewById(R.id.latitude);
        this.longitude = (TextView) findViewById(R.id.longitude);
        this.profondeur = (TextView) findViewById(R.id.profondeur);
        this.poids = (TextView) findViewById(R.id.poids);
        this.valider = (Button) findViewById(R.id.valider);
        this.supprimer = (TextView) findViewById(R.id.supprimer);

        String type[] = {"","Virée des filets","Calets des filets "};


        this.supprimer.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(daily.this, suppdaily.class);
                startActivity(intent);

            }
        });


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int jj = c.get(Calendar.DAY_OF_MONTH);
                int mm = c.get(Calendar.MONTH);
                int aaaa = c.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(daily.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },aaaa,mm,jj);
                datePickerDialog.show();
            }
        });
        heure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int hh = c.get(Calendar.HOUR_OF_DAY);
                int mm = c.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(daily.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        heure.setText(hourOfDay+":"+minute);
                    }
                },hh, mm, false);
                timePickerDialog.show();}
        });
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        virecalet.setAdapter(dataAdapter);
        adData();
    }

    public void adData() {
        this.valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num_trait = ntrait.getText().toString();

                String idjour = jour.getText().toString();
                String spinner = virecalet.getSelectedItem().toString();
                String latit = latitude.getText().toString();
                String longi = longitude.getText().toString();
                String prof = profondeur.getText().toString();
                String pds = poids.getText().toString();
                String hour = heure.getText().toString();
                String dday = date.getText().toString();

              //  String tableau[] = {idjour,spinner,latit,longi,prof,pds,hour,dday};
              //  JSONArray mjson = new JSONArray(Arrays.asList(tableau));
              //  AccesDistant a = new AccesDistant();
              //  a.envoi("enreg",mjson);

            if (TextUtils.isEmpty(num_trait)) {
                    Toast.makeText(daily.this, "Entrez le numéro de trait du jour", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(idjour)) {
                    Toast.makeText(daily.this, "Entrez le jour", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(spinner)) {
                    Toast.makeText(daily.this, "Entrez le type de trait de chalut", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(latit)) {
                    Toast.makeText(daily.this, "Entrez la latitude", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(longi)) {
                    Toast.makeText(daily.this, "Entrez la longitude", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(prof)) {
                    Toast.makeText(daily.this, "Entrez la profondeur", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(pds)) {
                    Toast.makeText(daily.this, "Veuillez renseigner le poids", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(hour)) {
                    Toast.makeText(daily.this, "Veuillez renseigner l'heure", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(dday)) {
                    Toast.makeText(daily.this, "Veuillez renseigner le poids", Toast.LENGTH_LONG).show();
                } else {

                    boolean isInserted = myDb.insertData2(num_trait, idjour, dday, hour, spinner, latit, longi, prof, pds);
                    if (isInserted) {

                        Toast.makeText(daily.this, "Données enregistrées avec succés", Toast.LENGTH_LONG).show();
                        ntrait.setText("");
                        jour.setText("");
                        latitude.setText("");
                        virecalet.setSelection(0);
                        date.setText("");
                        poids.setText("");
                        heure.setText("");
                        longitude.setText("");
                        profondeur.setText("");
                    } else
                        Toast.makeText(daily.this, "Erreur dans l'enregistrement des données", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    public JSONArray convertTOJSONARRAY(){
        List laliste = new ArrayList();
        laliste.add( ntrait.getText().toString());
        laliste.add(jour.getText().toString());
        laliste.add( virecalet.getSelectedItem().toString());
        laliste.add( latitude.getText().toString());
        laliste.add(longitude.getText().toString());
        laliste.add(profondeur.getText().toString());
        laliste.add(poids.getText().toString());
        laliste.add(heure.getText().toString());
        laliste.add(date.getText().toString());

        return new JSONArray(laliste);
    }









}
