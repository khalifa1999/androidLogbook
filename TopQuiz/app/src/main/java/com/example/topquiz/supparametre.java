package com.example.topquiz;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class supparametre extends AppCompatActivity {
    DatePickerDialog datePickerDialog;
    private Button delete;
    private EditText  jour;
    DatabaseHelper myDb;


    public supparametre(){}
    public supparametre(Button delete, EditText jour){
        this.delete = delete;
        this.jour = jour;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DatabaseHelper(this);
        setContentView(R.layout.activity_supparametre);
        jour = (EditText) findViewById(R.id.jour);

        delete = (Button) findViewById(R.id.delete);

        DeleteData();
    }
    public void DeleteData(){
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteDatab(jour.getText().toString());
                        if (deletedRows>0) {
                            jour.setText("");
                            Toast.makeText(supparametre.this, "Données supprimées", Toast.LENGTH_LONG).show();
                        }else
                            Toast.makeText(supparametre.this, "Echec suppresion", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
