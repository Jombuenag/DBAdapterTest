package com.example.takuma.dbadaptertest;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class Consultas extends AppCompatActivity {

    private DbHelper myDb;

    RadioGroup rgAlumnosProfesores, rgCiclos;
    EditText nomBusqueda, cicloBusqueda;
    Button btnBuscar;
    String strBusqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);
        rgAlumnosProfesores = (RadioGroup) findViewById(R.id.rgAlumnosProfesores);
        rgCiclos = (RadioGroup) findViewById(R.id.rgCiclos);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        nomBusqueda = (EditText) findViewById(R.id.nomBusqueda);
        cicloBusqueda = (EditText) findViewById(R.id.cursoBusqueda);
        myDb = new DbHelper(this);
        buscador();
    }

    public void buscador() {
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strBusqueda = String.valueOf(cicloBusqueda.getText());
                Cursor cursorDB = myDb.devolverDatosAlumnosBusqueda(strBusqueda);
                StringBuffer buffer = new StringBuffer();
                try {
                    if (datosCorrectos() && cursorDB.getCount() == -1) {
                        verDatosBusqueda("Error", "No se ha encontrado ningún dato");
                    }
                    while (cursorDB.moveToNext()) {
                        buffer.append("id :" + cursorDB.getString(0) + "\n");
                        buffer.append("Nombre :" + cursorDB.getString(1) + "\n");
                        buffer.append("Apellidos :" + cursorDB.getString(2) + "\n");
                        buffer.append("Edad :" + cursorDB.getString(3) + "\n");
                        buffer.append("Curso :" + cursorDB.getString(4) + "\n");
                        buffer.append("Ciclo :" + cursorDB.getString(5) + "\n");
                        buffer.append("Nota :" + cursorDB.getString(6) + "\n\n");
                    }
                }catch (SQLiteException ErrorSQL){

                }
                //Show all data
                verDatosBusqueda("BUSQUEDA", buffer.toString());
            }
        });
    }

        public void verDatosBusqueda(String Title, String Message){
            AlertDialog.Builder verDatosAlumnos = new AlertDialog.Builder(this);
            verDatosAlumnos.setCancelable(true);
            verDatosAlumnos.setTitle(Title);
            verDatosAlumnos.setMessage(Message);
            verDatosAlumnos.show();
        }

    public boolean datosCorrectos(){
        if(cicloBusqueda.getText().length()<=0){
            return false;
        }else {
            if(rgAlumnosProfesores.getCheckedRadioButtonId()==-1){
                return false;
            } else {
                return true;
            }
        }
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_consultas, menu);
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
