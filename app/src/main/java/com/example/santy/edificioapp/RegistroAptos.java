package com.example.santy.edificioapp;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;



import java.util.ArrayList;

public class RegistroAptos extends AppCompatActivity {

    private RadioButton rbotonsombraSi,rbotonsombraNo,rbotonbalconSi,rbotonbalconNo;
    private EditText txmetros,txprecio,txnomenclatura;
    private Spinner OpcionesUbicacionPiso;
    private Resources res;
    private String[] opc;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_aptos);
        res=this.getResources();
        txnomenclatura=(EditText)findViewById(R.id.txtnomenclatura);
        txmetros=(EditText)findViewById(R.id.txtMtscuadrados);
        txprecio=(EditText)findViewById(R.id.txtPrice);
        rbotonsombraSi=(RadioButton)findViewById(R.id.RdsombraSI);
        rbotonsombraNo=(RadioButton)findViewById(R.id.RdsombraNo);
        rbotonbalconSi=(RadioButton)findViewById(R.id.RdbalconSI);
        rbotonbalconNo=(RadioButton)findViewById(R.id.RdbalconNo);

        OpcionesUbicacionPiso=(Spinner)findViewById(R.id.cmdUbicacionPiso);
        opc=res.getStringArray(R.array.opciones_piso);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opc);
        OpcionesUbicacionPiso.setAdapter(adapter);
    }

    public void guardar(View v){
        if (todovalidar()){
            if (validacionNOmActual()){
                if (validarUbicacion()){
                    String nomenclatura,piso,metros,precio,balcon,sombra;
                    Apto apartamento;
                    nomenclatura=txnomenclatura.getText().toString();
                    piso=OpcionesUbicacionPiso.getSelectedItem().toString();
                    metros=txmetros.getText().toString();
                    precio=txprecio.getText().toString();
                    if (rbotonsombraSi.isChecked())sombra=res.getString(R.string.encuentra_sombra);
                    else sombra=res.getString(R.string.no_encuentra_sombra);
                    if (rbotonbalconSi.isChecked())balcon=res.getString(R.string.tiene_balcon);
                    else balcon=res.getString(R.string.no_tiene_balcon);

                    apartamento=new Apto(nomenclatura,piso,metros,precio,balcon,sombra);
                    apartamento.guardar(getApplicationContext());
                    Toast.makeText(getApplicationContext(), res.getString(R.string.guardado_exitoso),Toast.LENGTH_SHORT).show();
                    limpiar();
                }
            }
        }

    }

    public boolean todovalidar(){
        if (txnomenclatura.getText().toString().isEmpty()){
            txnomenclatura.setError(res.getString(R.string.error_nomenclatura));
            txnomenclatura.requestFocus();
            return false;
        }
        if (txmetros.getText().toString().isEmpty()){
            txmetros.setError(res.getString(R.string.error_metros));
            txmetros.requestFocus();
            return false;
        }
        if (txprecio.getText().toString().isEmpty()){
            txprecio.setError(res.getString(R.string.error_precio));
            txprecio.requestFocus();
            return false;
        }
        return true;
    }


    public boolean validarUbicacion(){
        ArrayList<Apto> a=Datos.traerApartamentos(getApplicationContext());
        String piso=OpcionesUbicacionPiso.getSelectedItem().toString();
        int cont=0;
        for (int i=0;i<a.size();i++){
            if (a.get(i).getPiso().equals(piso))cont=cont+1;
        }
        if (cont>=3){
            Toast.makeText(getApplicationContext(),res.getString(R.string.error_piso),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean validacionNOmActual(){
        ArrayList<Apto> apartamentos= Datos.traerApartamentos(getApplicationContext());
        for (int i=0;i<apartamentos.size();i++){
            if (apartamentos.get(i).getNomenclatura().equalsIgnoreCase(txnomenclatura.getText().toString())){
                txnomenclatura.setError(res.getString(R.string.error_nomenclatura_igual));
                txnomenclatura.requestFocus();
                return false;
            }
        }
        return true;
    }

    public boolean ValidarfiltroAptos(){
        if (txnomenclatura.getText().toString().isEmpty()){
            txnomenclatura.setError(res.getString(R.string.error_nomenclatura));
            txnomenclatura.requestFocus();
            return false;
        }
        return true;
    }

    public void buscar(View v){
        Apto a;
        if (ValidarfiltroAptos()){
            a=Datos.buscarApartamentos(getApplicationContext(),txnomenclatura.getText().toString());
            if (a!=null){
                if (a.getPiso().equalsIgnoreCase(res.getString(R.string.uno)))OpcionesUbicacionPiso.setSelection(0);
                if (a.getPiso().equalsIgnoreCase(res.getString(R.string.dos)))OpcionesUbicacionPiso.setSelection(1);
                if (a.getPiso().equalsIgnoreCase(res.getString(R.string.tres)))OpcionesUbicacionPiso.setSelection(2);
                if (a.getPiso().equalsIgnoreCase(res.getString(R.string.cuatro)))OpcionesUbicacionPiso.setSelection(3);
                if (a.getPiso().equalsIgnoreCase(res.getString(R.string.cinco)))OpcionesUbicacionPiso.setSelection(4);

                txmetros.setText(a.getMetros());
                txprecio.setText(a.getPrecio());

                if (a.getBalcon().equalsIgnoreCase(res.getString(R.string.tiene_balcon)))rbotonbalconSi.setChecked(true);
                else rbotonbalconNo.setChecked(true);

                if (a.getSombra().equalsIgnoreCase(res.getString(R.string.encuentra_sombra)))rbotonsombraSi.setChecked(true);
                else rbotonsombraNo.setChecked(true);
            }
        }
    }


    public void eliminar(View v){
        Apto a;
        if (ValidarfiltroAptos()){
            a=Datos.buscarApartamentos(getApplicationContext(),txnomenclatura.getText().toString());
            if (a!=null){
                AlertDialog.Builder ventana=new AlertDialog.Builder(this);
                ventana.setTitle(res.getString(R.string.confirmacion));
                ventana.setMessage(res.getString(R.string.mensaje_eliminacion));
                ventana.setPositiveButton(res.getString(R.string.confirmar),new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Apto a;
                        a=Datos.buscarApartamentos(getApplicationContext(),txnomenclatura.getText().toString());
                        a.eliminar(getApplicationContext());
                        Toast.makeText(getApplicationContext(),res.getString(R.string.eliminado_exitoso),
                                Toast.LENGTH_SHORT).show();
                        limpiar();
                    }
                });

                ventana.setNegativeButton(res.getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        txnomenclatura.requestFocus();
                    }
                });
                ventana.show();
            }
        }
    }

    public void borrar(View v){
        limpiar();
    }

    public void limpiar(){
        txnomenclatura.setText("");
        OpcionesUbicacionPiso.setSelection(0);
        txmetros.setText("");
        txprecio.setText("");
        rbotonbalconSi.setChecked(true);
        rbotonsombraSi.setChecked(true);
        txnomenclatura.requestFocus();
    }








}
