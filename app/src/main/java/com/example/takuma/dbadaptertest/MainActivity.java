package com.example.takuma.dbadaptertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

     Button btnAlumnos, btnProfesores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAlumnos = (Button)findViewById(R.id.btnAlumnos);
        btnProfesores = (Button)findViewById(R.id.btnProfesores);
        aPantallaAlumnos();
        aPantallaProfesores();

    }

//METODOS QUE MANEJAN LOS BOTONES
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
