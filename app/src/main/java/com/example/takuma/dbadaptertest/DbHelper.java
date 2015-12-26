package com.example.takuma.dbadaptertest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DbHelper extends SQLiteOpenHelper {
//VARIABLES DE CLASE
    public static final String DATABASE_NOMBRE = "colegioDB";

    public static String alumnoExpulsado = InsertAlumnos.alumnoExpulsado;

//TABLA ESTUDIANTES
    public static final String TABLAESTUDIANTES = "tabla_estudiantes";
    public static final String ESTUDIANTE_ID = "estudiante_id";
    public static final String ESTUDIANTE_NOMBRE = "estudiante_nombre";
    public static final String ESTUDIANTE_APELLIDOS = "estudiante_apellidos";
    public static final String ESTUDIANTE_EDAD = "estudiante_edad";
    public static final String ESTUDIANTE_CURSO = "estudiante_curso";
    public static final String ESTUDIANTE_CICLO = "estudiante_ciclo";
    public static final String ESTUDIANTE_NOTA = "estudiante_nota";

    public static final String TABLAPROFESORES = "tabla_profesores";
    public static final String PROFESOR_ID = "profesor_id";
    public static final String PROFESOR_NOMBRE = "profesor_nombre";
    public static final String PROFESOR_APELLIDOS = "profesor_apellidos";
    public static final String PROFESOR_EDAD = "profesor_edad";
    public static final String PROFESOR_TUTOR = "profesor_tutor";
    public static final String PROFESOR_CICLO = "profesor_ciclo";
    public static final String PROFESOR_DESPACHO = "profesor_despacho";

    public DbHelper(Context context) {
        super(context, DATABASE_NOMBRE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //TABLA UNO (CON VARIABLES)
        String  CREATE_TABLAESTUDIANTES = "CREATE TABLE IF NOT EXISTS " + TABLAESTUDIANTES + " (" +
                ESTUDIANTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ESTUDIANTE_NOMBRE + " TEXT, " +
                ESTUDIANTE_APELLIDOS + " TEXT, " +
                ESTUDIANTE_EDAD + " TEXT, " +
                ESTUDIANTE_CURSO + " TEXT, " +
                ESTUDIANTE_CICLO + " TEXT, " +
                ESTUDIANTE_NOTA + " TEXT "  + ");";
        db.execSQL(CREATE_TABLAESTUDIANTES);
        //TABLA PROFESORES
        String CREATE_TABLAPROFESORES = "CREATE TABLE IF NOT EXISTS " + TABLAPROFESORES + " ( " +
                PROFESOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PROFESOR_NOMBRE + " TEXT, " +
                PROFESOR_APELLIDOS + " TEXT, "+
                PROFESOR_EDAD + " TEXT, " +
                PROFESOR_TUTOR + " TEXT, " +
                PROFESOR_CICLO + " TEXT, " +
                PROFESOR_DESPACHO + " TEXT " + ");";
        db.execSQL(CREATE_TABLAPROFESORES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLAESTUDIANTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLAPROFESORES);
        onCreate(db);
    }

    public boolean insertarDatosAlumnos(String nombre, String apellidos, String edad, String curso, String ciclo, String nota){
        SQLiteDatabase db = (this.getWritableDatabase());
        ContentValues contentValues = new ContentValues();
        contentValues.put(ESTUDIANTE_NOMBRE, nombre);
        contentValues.put(ESTUDIANTE_APELLIDOS, apellidos);
        contentValues.put(ESTUDIANTE_EDAD, edad);
        contentValues.put(ESTUDIANTE_CURSO, curso);
        contentValues.put(ESTUDIANTE_CICLO, ciclo);
        contentValues.put(ESTUDIANTE_NOTA, nota);
        long result = db.insert(TABLAESTUDIANTES, null, contentValues);
        Log.d("insertAlumnTAG", "HE INSERTADO UN ALUMNO");
        Log.d("StudentAddTAG", nombre + apellidos + "\n " + "HA ENTRADO EN LA BASE DE DATOS");

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    public boolean insertarDatosProfesores(String nombre, String apellidos, String edad, String tutor, String ciclo, String despacho){
        SQLiteDatabase db = (this.getWritableDatabase());
        ContentValues contentValues = new ContentValues();
        contentValues.put(PROFESOR_NOMBRE, nombre);
        contentValues.put(PROFESOR_APELLIDOS, apellidos);
        contentValues.put(PROFESOR_EDAD, edad);
        contentValues.put(PROFESOR_TUTOR, tutor);
        contentValues.put(PROFESOR_CICLO, ciclo);
        contentValues.put(PROFESOR_DESPACHO, despacho);
        long result = db.insert(TABLAPROFESORES, null, contentValues);
        Log.d("insertProfeTAG", "HE INSERTADO UN PROFESOR");
        Log.d("TeacherAddTAG", nombre + apellidos + "\n " + "HA ENTRADO EN LA BASE DE DATOS");

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    //SAVESTATE HAY QUE CONSEGUIR COMUNICAR CUAL ES LA ID DEL ALUMNO EXPULSADO DESDE INSERTALUMNOS

    public Cursor borrarAlumno(){
        SQLiteDatabase db = (this.getWritableDatabase());
        Cursor cursorDB = db.rawQuery("DELETE FROM " + TABLAESTUDIANTES + " WHERE alumno_id"  + " = 9" + " ;", null);
        return cursorDB;
    }

    public Cursor devolverDatosAlumnos(){
        SQLiteDatabase db = (this.getWritableDatabase());
        Cursor cursorDB = db.rawQuery("SELECT * FROM " + TABLAESTUDIANTES + " ;" , null );
        return cursorDB;
    }

    public Cursor devolverDatosProfesores(){
        SQLiteDatabase db = (this.getWritableDatabase());
        Cursor cursorDB = db.rawQuery("SELECT * FROM " + TABLAPROFESORES + " ;", null);
        return cursorDB;
    }




}
