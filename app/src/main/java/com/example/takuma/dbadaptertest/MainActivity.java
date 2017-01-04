package com.example.takuma.dbadaptertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

     Button btnAlumnos, btnProfesores, btnBorrarBD, btnConsultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAlumnos = (Button)findViewById(R.id.btnAlumnos);
        btnProfesores = (Button)findViewById(R.id.btnProfesores);
        btnBorrarBD = (Button)findViewById(R.id.btnBorrarBD);
        btnConsultas = (Button)findViewById(R.id.btnConsultas);
        aPantallaAlumnos();
        aPantallaProfesores();
        aPantallaConsultas();
        borrarDB();
    }

//METODOS QUE MANEJAN LAS ACTIVITIES
    private void aPantallaAlumnos(){
        btnAlumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aPantallaAlumnos = new Intent(MainActivity.this, InsertAlumnos.class);
                startActivity(aPantallaAlumnos);
            }
        });
    }
    private void aPantallaProfesores(){
        btnProfesores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aPantallaProfesores = new Intent(MainActivity.this, InsertProfesores.class);
                startActivity(aPantallaProfesores);
            }
        });
    }
    public void borrarDB(){
        btnBorrarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDatabase("colegioDB");
                Toast.makeText(MainActivity.this, "Se ha borrado la base de datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void aPantallaConsultas(){
        btnConsultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aPantallaConsultas = new Intent(MainActivity.this, Consultas.class);
                startActivity(aPantallaConsultas);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
