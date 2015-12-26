package com.example.takuma.dbadaptertest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertAlumnos extends AppCompatActivity {

    private DbHelper myDb;

    EditText editNombreA, editApellidosA, editEdadA, editCursoA, editCicloA, editNota;
    Button btnAñadir, btnVerInfo, btnBorrarAlumnos;
    public static String alumnoExpulsado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_alumnos);
        editNombreA = (EditText)findViewById(R.id.editNombreA);
        editApellidosA = (EditText)findViewById(R.id.editApellidosA);
        editEdadA = (EditText)findViewById(R.id.editEdadA);
        editCursoA = (EditText)findViewById(R.id.editCursoA);
        editCicloA = (EditText)findViewById(R.id.editCicloA);
        editNota = (EditText)findViewById(R.id.editNota);
        btnAñadir = (Button)findViewById(R.id.btnAñadirAlumno);
        btnVerInfo = (Button)findViewById(R.id.btnVerInfoAlumno);
        btnBorrarAlumnos = (Button)findViewById(R.id.btnBorrarAlumno);
        myDb = new DbHelper(this);
        addDatosAlumnos();
        verInfoAlumnos();
        borrarInfoAlumnos();
    }

    public void addDatosAlumnos() {
             btnAñadir.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean estaDentro = myDb.insertarDatosAlumnos(
                                editNombreA.getText().toString(),
                                editApellidosA.getText().toString(),
                                editEdadA.getText().toString(),
                                editCursoA.getText().toString(),
                                editCicloA.getText().toString(),
                                editNota.getText().toString());
                            if (estaDentro = true) {
                            Toast.makeText(InsertAlumnos.this, "Info Añadida", Toast.LENGTH_SHORT).show();
                            }else{
                            Toast.makeText(InsertAlumnos.this, "Info no Añadida", Toast.LENGTH_SHORT).show();
                            }
                        //VACIAR LOS CAMPOS
                        editNombreA.setText("");
                        editApellidosA.setText("");
                        editEdadA.setText("");
                        editCursoA.setText("");
                        editCicloA.setText("");
                        editNota.setText("");
                        }
                    });
                }

    public void verInfoAlumnos(){
                btnVerInfo.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Cursor cursorDB = myDb.devolverDatosAlumnos();
                                StringBuffer buffer = new StringBuffer();
                                if (cursorDB.getCount() == -1) {
                                    verDatosAlumnos("Error", "No se ha encontrado ningún dato");
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
                                //Show all data
                                verDatosAlumnos("ESTUDIANTES", buffer.toString());
                            }
                        }
                );
    }

    public void verDatosAlumnos(String Title, String Message){
        AlertDialog.Builder verDatosAlumnos = new AlertDialog.Builder(this);
        verDatosAlumnos.setCancelable(true);
        verDatosAlumnos.setTitle(Title);
        verDatosAlumnos.setMessage(Message);
        verDatosAlumnos.show();
    }

    public void borrarInfoAlumnos(){
        btnBorrarAlumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expulsarAlumno("Expulsado", "Pon la ID del expulsado");
            }
        });
    }

    public String expulsarAlumno(String Title, String Message) {
        final EditText aQuienBorro = new EditText(this);
        aQuienBorro.setSingleLine();
        aQuienBorro.setPadding(50, 10, 50, 10);
        AlertDialog.Builder jefeDeEstudios = new AlertDialog.Builder(this);
        jefeDeEstudios.setTitle(Title);
        jefeDeEstudios.setMessage(Message);
        jefeDeEstudios.setView(aQuienBorro);
        jefeDeEstudios.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alumnoExpulsado = aQuienBorro.getText().toString();
                Cursor cursorDB = myDb.borrarAlumno();
                cursorDB.getColumnIndex("estudiante_id");
                myDb.borrarAlumno();
                Toast.makeText(InsertAlumnos.this, "he de borrar la id : " + alumnoExpulsado, Toast.LENGTH_SHORT).show();
            }
        });
        jefeDeEstudios.show();
        return alumnoExpulsado;
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
