package com.example.santy.edificioapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SANTY on 21/05/2017.
 */

public class AptosSQLiteOpenHelper extends SQLiteOpenHelper{
    private String sql="CREATE TABLE Apartamentos(nomenclatura text,piso text,metros text,precio text,balcon text,sombra text)";

    public AptosSQLiteOpenHelper(Context contexto, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Apartamentos");
        db.execSQL(sql);
    }
}
