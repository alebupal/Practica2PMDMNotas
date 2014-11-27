package com.example.alejandro.practica2pmdmnotas;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends Activity {
    private String ruta;
    private EditText tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Uri data = intent.getData();
        ruta = data.getPath();
            File f=new File(ruta);
            try {
                FileInputStream fin = new FileInputStream(f);
                InputStreamReader isr = new InputStreamReader(fin);
                BufferedReader br = new BufferedReader(isr);
                String linea = br.readLine();
                String texto = "";
                while (linea != null) {
                    texto += linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                isr.close();
                br.close();
                tv = (EditText) findViewById(R.id.etTexto);
                tv.setText(texto.toString());
            }catch(IOException e){
                Toast.makeText(this,"Error al leer archivo", Toast.LENGTH_SHORT).show();
            }
    }
    public void btGuardar(View v){
        File f=new File(ruta);
        EditText etTexto = (EditText) findViewById(R.id.etTexto);
        try {
            FileWriter fw = new FileWriter(f, false);
            fw.write(etTexto.getText().toString());
            fw.flush();
            fw.close();
        } catch(IOException e){
        }
        setResult(0);
        finish();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String texto=(String)savedInstanceState.get("texto");

        tv = (EditText) findViewById(R.id.etTexto);
        tv.setText(texto);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        tv = (EditText) findViewById(R.id.etTexto);

        outState.putString("texto", tv.getText().toString());
    }
}
