package com.example.topquiz;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static AccesDistant accesDistant;
    private static final String name = "dpm.db";
    private static final int version =1;
    public static final String NAVIRE_TABLE="NAVIRE";
    public static final String COLUMN_IDNAVIRE = "id";
    public static final String COLUMN_NOMNAVIRE ="nom_navire";
    public static final String COLUMN_ARMATEUR ="nom_armateur";
    public static final String COLUMN_DATE ="date";
    public static final String COLUMN_HEURED ="heured";

    public static final String COLUMN_DATEA ="datea";

     public static final String COLUMN_PORTD ="port_depart";




    public static final String TRAIT_TABLE="TRAIT";
    public static final String COLUMN_IDTRAIT= "id_trait";
    public static final String COLUMN_NUMTRAIT= "num_trait";
    public static final String COLUMN_IDJOUR = "id_jour";
    public static final String COLUMN_JOUR= "date_jour";
    public static final String COLUMN_HJOUR= "heure_jour";
    public static final String COLUMN_SPINNER= "spinner";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE ="longitude";
    public static final String COLUMN_PROFONDEUR= "profondeur";
    public static final String COLUMN_POIDS ="poids";


    public static final String INVENTAIRE_TABLE="INVENTAIRE";

    public static final String COLUMN_IDINVENTAIRE="id_inventaire";
    public static final String COLUMN_POIDSNET="poids_net";
    public static final String COLUMN_POIDSBRUT="poids_brut";
    public static final String COLUMN_REJET="rejet";
    public static final String COLUMN_JOUr="jour";
    public static final String COLUMN_TRAIT="trait";



    public DatabaseHelper(Context context) {
        super(context, name,null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ NAVIRE_TABLE +"(id INTEGER PRIMARY KEY , nom_navire TEXT, nom_armateur TEXT, date TEXT, heured TEXT, datea TEXT , port_depart TEXT)");
        db.execSQL("create table "+ TRAIT_TABLE +"(id_trait INTEGER PRIMARY KEY AUTOINCREMENT,num_trait TEXT, id_jour TEXT, date_jour TEXT, heure_jour TEXT, spinner TEXT , latitude TEXT, longitude TEXT, profondeur TEXT, poids TEXT)");
        db.execSQL("create table "+ INVENTAIRE_TABLE +"(id_inventaire INTEGER PRIMARY KEY AUTOINCREMENT, poids_net TEXT, poids_brut TEXT,rejet TEXT,jour TEXT,trait TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+ NAVIRE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ INVENTAIRE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ TRAIT_TABLE);
        onCreate(db);
    }
    public boolean insertData3(String poids_net, String poids_brut, String rejet, String jour, String trait){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_POIDSNET,poids_net);
        contentValues.put(COLUMN_POIDSBRUT,poids_brut);
        contentValues.put(COLUMN_REJET,rejet);
        contentValues.put(COLUMN_JOUr,jour);
        contentValues.put(COLUMN_TRAIT,trait);


        long result = db.insert(INVENTAIRE_TABLE,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertData(String id, String nom_navire, String nom_armateur, String date,String heured, String datea, String port_depart){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IDNAVIRE,id);
        contentValues.put(COLUMN_NOMNAVIRE, nom_navire);
        contentValues.put(COLUMN_ARMATEUR,nom_armateur);
        contentValues.put(COLUMN_DATE,date);
        contentValues.put(COLUMN_HEURED,heured);
        contentValues.put(COLUMN_DATEA,datea);
        contentValues.put(COLUMN_PORTD,port_depart);
        long result = db.insert(NAVIRE_TABLE,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    public boolean insertData2(String num_trait,String id_jour, String date_jour, String heure_jour,String spinner, String latitude, String longitude,String profondeur, String poids){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NUMTRAIT, num_trait);
        contentValues.put(COLUMN_IDJOUR, id_jour);
        contentValues.put(COLUMN_JOUR,date_jour);
        contentValues.put(COLUMN_HJOUR,heure_jour);
        contentValues.put(COLUMN_SPINNER,spinner);
        contentValues.put(COLUMN_LATITUDE,latitude);
        contentValues.put(COLUMN_LONGITUDE,longitude);
        contentValues.put(COLUMN_PROFONDEUR,profondeur);
        contentValues.put(COLUMN_POIDS,poids);
        long result = db.insert(TRAIT_TABLE,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getALLData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ NAVIRE_TABLE, null);
        return res;
    }
    public Cursor getALData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TRAIT_TABLE, null);
        return res;
    }
    public Cursor getAData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ INVENTAIRE_TABLE, null);
        return res;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(NAVIRE_TABLE, COLUMN_IDNAVIRE + "= ?",new String[]{id});
    }

    public Integer deleteDatab(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(INVENTAIRE_TABLE, COLUMN_IDINVENTAIRE + "= ?",new String[]{id});
    }

    public Integer deleteDatac(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TRAIT_TABLE, COLUMN_IDTRAIT + "= ?",new String[]{id});
    }


}
