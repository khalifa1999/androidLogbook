package com.example.topquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class suppdaily extends AppCompatActivity {
    private Button delete;
    private EditText id;
    DatabaseHelper myDb;


    public suppdaily(){}
    public suppdaily(Button delete, EditText id){
        this.delete = delete;
        this.id = id;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppdaily);
        myDb = new DatabaseHelper(this);
        id = (EditText) findViewById(R.id.id);
        delete = (Button) findViewById(R.id.delete);
        DeleteData();
    }
    public void DeleteData(){
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteDatac(id.getText().toString());
                        if (deletedRows>0) {
                            id.setText("");
                            Toast.makeText(suppdaily.this, "Données supprimées", Toast.LENGTH_LONG).show();

                        } else
                            Toast.makeText(suppdaily.this, "Echec suppresion", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
