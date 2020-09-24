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

public class suppaddInfo extends AppCompatActivity {
    private Button delete;
    private EditText id;
    DatabaseHelper myDb;


    public suppaddInfo(){}
    public suppaddInfo(Button delete, EditText id){
        this.delete = delete;
        this.id = id;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DatabaseHelper(this);
        setContentView(R.layout.activity_suppadd_info);
        id = (EditText) findViewById(R.id.identifiant);
        delete = (Button) findViewById(R.id.delete);
       DeleteData();
    }
    public void DeleteData(){
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(id.getText().toString());
                        if (deletedRows>0) {
                            id.setText("");
                            Toast.makeText(suppaddInfo.this, "Données supprimées", Toast.LENGTH_LONG).show();
                        }else
                            Toast.makeText(suppaddInfo.this, "Echec suppresion", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
