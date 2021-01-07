package com.example.newproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class EtudiantDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "EtudiantManager";
    private static final String TABLE_Etudiant = "table_etudiant";
    private static final String COLONNE_ID = "id";
    private static final String COLONNE_NOM = "nom";
    private static final String COLONNE_PRENOM = "prenom";
    private static final String COLONNE_LOGIN = "login";
    private static final String COLONNE_PASSWORD = "password";
    private static final String REQUETE_CREATION_BD = "create table "+ TABLE_Etudiant + " (" + COLONNE_ID+ " integer primary key autoincrement, " + COLONNE_PRENOM + " TEXT not null, " + COLONNE_NOM + " TEXT not null, " + COLONNE_LOGIN + " TEXT not null, " + COLONNE_PASSWORD + " TEXT not null);";


    public EtudiantDatabaseHandler(Context context, String nom, SQLiteDatabase.CursorFactory cursorFactory, int version) {
        super(context, nom, cursorFactory, version);

    }

    public EtudiantDatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(REQUETE_CREATION_BD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+TABLE_Etudiant+";");
        onCreate(db);
    }


    public void insertEtudiant(Etudiant etudiant){
        SQLiteDatabase maBD = this.getWritableDatabase();

        ContentValues valeurs = new ContentValues();

        valeurs.put(COLONNE_PRENOM, etudiant.getPrenom());
        valeurs.put(COLONNE_NOM, etudiant.getNom());
        valeurs.put(COLONNE_LOGIN, etudiant.getLogin());
        valeurs.put(COLONNE_PASSWORD, etudiant.getPassword());

        maBD.insert(TABLE_Etudiant,null,valeurs);
        maBD.close();
    }

    public ArrayList<Etudiant> getAllEtudiantes(){
        SQLiteDatabase maBD = this.getReadableDatabase();

        Cursor c = maBD.query(TABLE_Etudiant, new String[]{COLONNE_ID, COLONNE_PRENOM, COLONNE_NOM, COLONNE_LOGIN, COLONNE_PASSWORD},null,null,null,null,null);
        return cursorToEtudiants(c);
    }

    private Etudiant cusorToEtudiant(Cursor c){
        if (c==null || c.getCount() ==0){
            return null;
        }
        c.moveToFirst();
        Etudiant retEtudiant = new Etudiant();
        retEtudiant.setId(c.getInt(0));
        retEtudiant.setPrenom(c.getString(1));
        retEtudiant.setNom(c.getString(2));
        retEtudiant.setLogin(c.getString(3));
        retEtudiant.setPassword(c.getString(4));
        c.close();
        return retEtudiant;
    }

    public Etudiant getEtudiant(String login) {
        SQLiteDatabase maBD = this.getReadableDatabase();
        Cursor c = maBD.query(TABLE_Etudiant, new String[] {COLONNE_ID, COLONNE_PRENOM, COLONNE_NOM, COLONNE_LOGIN, COLONNE_PASSWORD},
                        COLONNE_LOGIN + " =? " , new String[]{login},null, null, null);
        return cusorToEtudiant(c);
    }


    private ArrayList<Etudiant> cursorToEtudiants(Cursor c){
        if (c.getCount()==0){
            return new ArrayList<Etudiant>(0);
        }
        ArrayList<Etudiant> retEtudiants = new ArrayList<Etudiant>(c.getCount());
        c.moveToFirst();
        do {
            Etudiant etudiant = new Etudiant();
            etudiant.setId(c.getInt(0));
            etudiant.setPrenom(c.getString(1));
            etudiant.setNom(c.getString(2));
            etudiant.setLogin(c.getString(3));
            etudiant.setPassword(c.getString(4));
            retEtudiants.add(etudiant);
        }while (c.moveToNext());
        c.close();
        return retEtudiants;
    }

}
