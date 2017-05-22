package com.example.santy.edificioapp;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SANTY on 21/05/2017.
 */

public class AclopadorApto  extends BaseAdapter {
    private Context contexto;
    private ArrayList<Apto> apartamentos;
    private String[] tirulos;

    public AclopadorApto(Context contexto, ArrayList<Apto> apartamentos, String[] tirulos) {
        this.contexto = contexto;
        this.apartamentos = apartamentos;
        this.tirulos = tirulos;
    }

    @Override
    public int getCount() {
        return apartamentos.size();
    }

    @Override
    public Object getItem(int position) {
        return apartamentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Declarar las variables
        TextView txnomenclatura,txpiso,txmetros,txprecio,txbalcon,txsombras;
        LayoutInflater inflater;
        View itemView;

        //uso el inflater
        inflater=(LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemView=inflater.inflate(R.layout.item_apto,null);

        //Capturar los objetos
        txnomenclatura=(TextView)itemView.findViewById(R.id.txtNomenclaturaP);
        txpiso=(TextView)itemView.findViewById(R.id.txtPisoP);
        txmetros=(TextView)itemView.findViewById(R.id.txtMetrosP);
        txprecio=(TextView)itemView.findViewById(R.id.txtPrecioP);
        txbalcon=(TextView)itemView.findViewById(R.id.txtBalconP);
        txsombras=(TextView)itemView.findViewById(R.id.txtSombraP);
        // cajaUbicacion=(TextView)itemView.findViewById(R.id.ubicacionP);

        //Pasar la informacion
        txnomenclatura.setText(tirulos[0]+": "+apartamentos.get(position).getNomenclatura());
        txpiso.setText(tirulos[1]+": "+apartamentos.get(position).getPiso());
        txmetros.setText(tirulos[2]+": "+apartamentos.get(position).getMetros());
        txprecio.setText(tirulos[3]+": $"+apartamentos.get(position).getPrecio());
        txbalcon.setText(apartamentos.get(position).getBalcon());
        txsombras.setText(apartamentos.get(position).getSombra());

        return itemView;
    }

}
