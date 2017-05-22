package com.example.santy.edificioapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Lista_Aptos extends AppCompatActivity {
    private ListView ls;
    private ArrayList<Apto> apartamentos;
    private AclopadorApto adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__aptos);

        ls=(ListView)findViewById(R.id.lsApartamentos);
        apartamentos=Datos.traerApartamentos(getApplicationContext());
        adapter=new AclopadorApto(getApplicationContext(),apartamentos,getResources().getStringArray(R.array.opciones_lista_personalizada));
        ls.setAdapter(adapter);
    }
}

