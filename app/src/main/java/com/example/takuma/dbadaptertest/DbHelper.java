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

    //IMPRESCINDIBLE PARA COMUNICARNOS CON LA BASE DE DATOS
    public DbHelper(Context context) {
        super(context, DATABASE_NOMBRE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //CREACIÓN TABLA ESTUDIANTES
        String  CREATE_TABLAESTUDIANTES = "CREATE TABLE IF NOT EXISTS " +
                TABLAESTUDIANTES + " (" +
                ESTUDIANTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ESTUDIANTE_NOMBRE + " TEXT, " +
                ESTUDIANTE_APELLIDOS + " TEXT, " +
                ESTUDIANTE_EDAD + " TEXT, " +
                ESTUDIANTE_CURSO + " TEXT, " +
                ESTUDIANTE_CICLO + " TEXT, " +
                ESTUDIANTE_NOTA + " TEXT "  + ");";
        db.execSQL(CREATE_TABLAESTUDIANTES);
        //CREACIÓN TABLA PROFESORES
        String CREATE_TABLAPROFESORES = "CREATE TABLE IF NOT EXISTS " +
                TABLAPROFESORES + " ( " +
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

    //NO TIENE MÁS MISTERIO DESCRIPTIVE AS FUCK
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
    //NO TIENE MÁS MISTERIO DESCRIPTIVE AS FUCK
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
    //NO TIENE MÁS MISTERIO DESCRIPTIVE AS FUCK
    public boolean borrarAlumno(Integer idExpulsado){
        SQLiteDatabase db = (this.getWritableDatabase());
        try{
            db.delete(TABLAESTUDIANTES, "estudiante_id=" + idExpulsado + "", null);
        }catch (Exception e){
            System.out.println("No he podido borrar al alumno " + idExpulsado + " de la base de datos");
            e.printStackTrace();
        }
        return true;
    }
    //NO TIENE MÁS MISTERIO DESCRIPTIVE AS FUCK
    public boolean borrarProfesor(Integer idDespedido){
        SQLiteDatabase db = (this.getWritableDatabase());
        try{
            db.delete(TABLAPROFESORES, "profesor_id=" + idDespedido + "", null);
        }catch (Exception e){
            System.out.println("No he podido despedir al profesor " + idDespedido + " de la base de datos");
            e.printStackTrace();
        }
        return true;
    }
    //SELECT * FROM ALUMNOS
    public Cursor devolverDatosAlumnos(){
        SQLiteDatabase db = (this.getWritableDatabase());
        Cursor cursorDB = db.rawQuery("SELECT * FROM " + TABLAESTUDIANTES + " ;" , null );
        return cursorDB;
    }
    //SELECT * FROM PROFESORES
    public Cursor devolverDatosProfesores(){
        SQLiteDatabase db = (this.getWritableDatabase());
        Cursor cursorDB = db.rawQuery("SELECT * FROM " + TABLAPROFESORES + " ;", null);
        return cursorDB;
    }
    //SELECT CICLO ALUMNOS
    public Cursor devolverDatosAlumnosBusqueda(String ciclo){
        SQLiteDatabase db = (this.getWritableDatabase());
        Cursor cursorDB = db.rawQuery("SELECT * FROM " + TABLAESTUDIANTES + " WHERE " + ESTUDIANTE_CICLO + " = " + "'"+ciclo+"';",null);
        return cursorDB;
    }
    //SELECT CICLO PROFESORES
    public Cursor devolverDatosProfesBusqueda(String ciclo){
        SQLiteDatabase db = (this.getWritableDatabase());
        Cursor cursorDB = db.rawQuery("SELECT * FROM " + TABLAPROFESORES + " WHERE " + PROFESOR_CICLO + " = " + "'"+ciclo+"';", null);
        return cursorDB;
    }
    //SELECT NOMBRE ALUMNOS
    public Cursor devolverNomAlumnos(String nombre){
        SQLiteDatabase db = (this.getWritableDatabase());
        Cursor cursorDB = db.rawQuery("SELECT * FROM " + TABLAESTUDIANTES + " WHERE " + ESTUDIANTE_NOMBRE + " = " + "'"+nombre+"';" , null );
        return cursorDB;
    }
    //SELECT NOMBRE PROFESORES
    public Cursor devolverNomProfesores(String nombre){
        SQLiteDatabase db = (this.getWritableDatabase());
        Cursor cursorDB = db.rawQuery("SELECT * FROM " + TABLAPROFESORES + " WHERE " + PROFESOR_NOMBRE + " = " + "'"+nombre+"';" , null );
        return cursorDB;
    }
    //SELECT POR INICIAL
    public Cursor devolverAlumnosPorInicial(String nombre){
        SQLiteDatabase db = (this.getWritableDatabase());
        String query = "SELECT * FROM " + TABLAESTUDIANTES + " WHERE " + ESTUDIANTE_NOMBRE + " LIKE '%+" +nombre+ "+'";
        Cursor cursorDB = db.rawQuery(query ,null);
        return cursorDB;
    }

}