package com.example.topquiz;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import org.json.JSONArray;
import org.w3c.dom.Text;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class addInfo extends AppCompatActivity {
    DatabaseHelper myDb;
    private SQLiteDatabase sql;
    private TextView textView, id;
    private Spinner spinner;
    private Button button;
    private TextView textView2 ,textView3, textView4, portD, supprimer;

    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;




   public addInfo(){};
    public addInfo(TextView id, TextView supprimer,TextView portD,TextView textView, Spinner spinner, Button button, SQLiteDatabase sql, TextView textView2, TextView textView3, TextView textView4){
        this.portD=portD;
        this.textView =textView;
        this.textView2 =textView2;
        this.textView3=textView3;
        this.textView4=textView4;
        this.spinner=spinner;
        this.button=button;
        this.sql=sql;
        this.supprimer=supprimer;
        this.id=id;

    }
    String [] npays = {"Armateur","Onudak","Senemer","Lesemar","Promel","Mour Diallo","ENFM","GIE Armement"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DatabaseHelper(this);
        setContentView(R.layout.activity_addinfo);
        button = (Button) findViewById(R.id.button);
        spinner =(Spinner) findViewById(R.id.spinner);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        id = (TextView) findViewById(R.id.id);
        portD= (TextView) findViewById(R.id.portD);
        supprimer = (TextView) findViewById(R.id.supprimer);

        this.supprimer.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addInfo.this, suppaddInfo.class);
                startActivity(intent);

            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int jj = c.get(Calendar.DAY_OF_MONTH);
                int mm = c.get(Calendar.MONTH);
                int aaaa = c.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(addInfo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textView2.setText(dayOfMonth+"/"+month+"/"+year);                    }
                },aaaa,mm,jj);
                datePickerDialog.show();
            }
        });
    textView3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar c = Calendar.getInstance();
            int hh = c.get(Calendar.HOUR_OF_DAY);
            int mm = c.get(Calendar.MINUTE);
            timePickerDialog = new TimePickerDialog(addInfo.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    textView3.setText(hourOfDay+":"+minute);
                }
            },hh, mm, false);
                timePickerDialog.show();}
    });


        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int jj = c.get(Calendar.DAY_OF_MONTH);
                int mm = c.get(Calendar.MONTH);
                int aaaa = c.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(addInfo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textView4.setText(dayOfMonth+"/"+month+"/"+year);                    }
                },aaaa,mm,jj);
                datePickerDialog.show();
            }
        });


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, npays);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    AddData();

    }


    private void AddData() {
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomnavire = textView.getText().toString();
                String armateur = spinner.getSelectedItem().toString();
                String datem = textView2.getText().toString();
                String heuredep = textView3.getText().toString();
                String datea = textView4.getText().toString();
                String portd = portD.getText().toString();
                String iden = id.getText().toString();

             //   String tableau[] = {nomnavire,armateur,datem,heuredep,datea,portd,iden};
             //   JSONArray mjson = new JSONArray(Arrays.asList(tableau));
             //   AccesDistant a = new AccesDistant();
             //   a.envoi("enreg",mjson);

                if (TextUtils.isEmpty(nomnavire)) {
                    Toast.makeText(addInfo.this, "Entrez le nom du navire", Toast.LENGTH_LONG).show();
                }else

                if (TextUtils.isEmpty(armateur)) {
                    Toast.makeText(addInfo.this, "Entrez le nom de l'armateur", Toast.LENGTH_LONG).show();
                }else
                if (TextUtils.isEmpty(datem)) {
                    Toast.makeText(addInfo.this, "Entrez la date de départ", Toast.LENGTH_LONG).show();
                }else
                if (TextUtils.isEmpty(heuredep)) {
                    Toast.makeText(addInfo.this, "Entrez l'heure de départ", Toast.LENGTH_LONG).show();
                }else
                if (TextUtils.isEmpty(datea)) {
                    Toast.makeText(addInfo.this, "Entrez la date de d'arivée", Toast.LENGTH_LONG).show();
                }else

                if (TextUtils.isEmpty(portd)) {
                    Toast.makeText(addInfo.this, "Veuillez renseigner le port de départ", Toast.LENGTH_LONG).show();
                }else
                    if(TextUtils.isEmpty(iden)) {
                    Toast.makeText(addInfo.this, "Veuillez renseigner l'identifiant", Toast.LENGTH_LONG).show();
                }else{

                boolean isInserted =  myDb.insertData(iden, nomnavire, armateur, datem, heuredep, datea, portd);
                if(isInserted){

                        Toast.makeText(addInfo.this, "Données enregistrées avec succés", Toast.LENGTH_LONG).show();
                        textView.setText("");
                        spinner.setSelection(0);
                    textView2.setText("");
                    textView3.setText("");
                    textView4.setText("");
                    portD.setText("");
                    id.setText("");

                } else
                    Toast.makeText(addInfo.this, "Erreur dans l'enregistrement des données", Toast.LENGTH_LONG).show();

            }}
        });
    }

    public JSONArray convertTOJSONARRAY(){
        List laliste = new ArrayList();
        laliste.add(textView.getText().toString());
        laliste.add(spinner.getSelectedItem().toString());
        laliste.add(textView2.getText().toString());
        laliste.add(textView3.getText().toString());
        laliste.add(textView4.getText().toString());
        laliste.add(portD.getText().toString());
        laliste.add(id.getText().toString());
        return new JSONArray(laliste);
    }

}
