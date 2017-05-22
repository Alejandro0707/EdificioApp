package com.example.santy.edificioapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by SANTY on 21/05/2017.
 */

public class Datos {

    public static ArrayList<Apto> traerApartamentos(Context contexto){
        SQLiteDatabase db;
        String sql,nomenclatura,piso,metros,precio,balcon,sombra;
        ArrayList<Apto> apartamentos=new ArrayList<>();

        AptosSQLiteOpenHelper aux=new AptosSQLiteOpenHelper(contexto,"DBApartamentos",null,1);
        db=aux.getReadableDatabase();

        sql="Select nomenclatura,piso,metros,precio,balcon,sombra from Apartamentos";
        Cursor c=db.rawQuery(sql,null);

        if (c.moveToFirst()){
            do {
                nomenclatura=c.getString(0);
                piso=c.getString(1);
                metros=c.getString(2);
                precio=c.getString(3);
                balcon=c.getString(4);
                sombra=c.getString(5);
                Apto a=new Apto(nomenclatura,piso,metros,precio,balcon,sombra);
                apartamentos.add(a);

            }while (c.moveToNext());
        }

        db.close();
        return apartamentos;
    }

    public static Apto buscarApartamentos(Context contexto,String nom){
        SQLiteDatabase db;
        String sql,nomenclatura,piso,metros,precio,balcon,sombra;
        Apto a=null;

        AptosSQLiteOpenHelper aux=new AptosSQLiteOpenHelper(contexto,"DBApartamentos",null,1);
        db=aux.getReadableDatabase();

        sql="Select * from Apartamentos where nomenclatura ='"+nom+"'";
        Cursor c=db.rawQuery(sql,null);

        if (c.moveToFirst()){
            nomenclatura=c.getString(0);
            piso=c.getString(1);
            metros=c.getString(2);
            precio=c.getString(3);
            balcon=c.getString(4);
            sombra=c.getString(5);
            a=new Apto(nomenclatura,piso,metros,precio,balcon,sombra);

        }

        db.close();
        return a;
    }

}

