package com.example.topquiz;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class affichage extends AppCompatActivity {
    Button btnfetch, btntrait, btninventaire;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage);
        myDb = new DatabaseHelper(this);
        btnfetch=(Button) findViewById(R.id.btnfetch);
        btntrait=(Button) findViewById(R.id.btntrait);
        btninventaire=(Button) findViewById(R.id.btninventaire);

        viewAll();
    }
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void viewAll(){
        btnfetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getALLData();

                if (res.getCount()==0){
                    //afficher le message
                    showMessage("Alert","Nothing has been found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id Navire :"+res.getString(0)+"\n");
                    buffer.append("Nom Navire :"+res.getString(1)+"\n");
                    buffer.append("Armateur :"+res.getString(2)+"\n");
                    buffer.append("Date départ :"+res.getString(3)+"\n");
                    buffer.append("Heure Départ :"+res.getString(4)+"\n");
                    buffer.append("Date Retour :"+res.getString(5)+"\n");
                    buffer.append("Port Départ :"+res.getString(6)+"\n"+"\n");
                }
                showMessage("Données Générales", buffer.toString());
            }
        });
        btntrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getALData();

                if (res.getCount()==0){
                    //afficher le message
                    showMessage("Alert","Nothing has been found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID Trait  :"+res.getString(0)+"\n");
                    buffer.append("Numéro du jour (marée) :"+res.getString(2)+"\n");
                    buffer.append("Jour :"+res.getString(3)+"\n");
                    buffer.append("Heure :"+res.getString(4)+"\n");
                    buffer.append("Numéro trait du jour:"+res.getString(1)+"\n");

                    buffer.append("Trait de chalut :"+res.getString(5)+"\n");
                    buffer.append("Latitude :"+res.getString(6)+"\n");
                    buffer.append("Longitude :"+res.getString(7)+"\n");
                    buffer.append("Profondeur :"+res.getString(8)+"\n");
                    buffer.append("Poids :"+res.getString(9)+"\n"+"\n");
                }
                showMessage("Données Traits", buffer.toString());
            }
        });


        btninventaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAData();

                if (res.getCount()==0){
                    //afficher le message
                    showMessage("Alert","Nothing has been found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID Inventaire :"+res.getString(0)+"\n");
                    buffer.append("Jour :"+res.getString(4)+"\n");
                    buffer.append("Numéro Trait :"+res.getString(5)+"\n");
                    buffer.append("Poids Net :"+res.getString(1)+"\n");
                    buffer.append("Poids Brut :"+res.getString(2)+"\n");
                    buffer.append("Rejet :"+res.getString(3)+"\n"+"\n");



                }
                showMessage("Données Inventaires", buffer.toString());
            }
        });

    }
}
