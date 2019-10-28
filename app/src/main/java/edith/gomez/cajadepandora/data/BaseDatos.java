package edith.gomez.cajadepandora.data;
/*
 * Created by Edith on 21-May-19.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.util.ArrayList;

import edith.gomez.cajadepandora.data.alarmas.Alarma;
import edith.gomez.cajadepandora.data.notas.Nota;

public class BaseDatos extends SQLiteOpenHelper {
    private static final String CREAR_TABLA_ALARMA = "CREATE TABLE alarma (id INTEGER PRIMARY KEY AUTOINCREMENT, hh INTEGER, mm INTEGER, per TEXT, tono TEXT, nom TEXT)";
    private static final String LEER_TABLA_ALARMA = "SELECT hh, mm, per, nom FROM alarma";
    private static final String CREAR_TABLA_NOTA = "CREATE TABLE nota (id INTEGER PRIMARY KEY AUTOINCREMENT, tit TEXT, con TEXT, fecha TEXT, audio TEXT, alarm INTEGER, salud INTEGER, tarea INTEGER, img INTEGER, edoemo INTEGER)";
    private static final String LEER_TABLA_NOTA = "SELECT tit, con, fecha, audio, alarm, salud, tarea, img, edoemo FROM nota";
    private static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS ";
    private static final String NOMBRE_BD = "cajapandora.sqlite";
    private static final int VERSION_BD = 1;
    private final Context context;
    private SQLiteDatabase bd;

    interface Tablas {
        String ALARMA = "alarma";
        String NOTA = "nota";
        String SALUD = "salud";
        String FINANZAS = "finanazas";
        String TAREA = "tarea";
    }

    public BaseDatos(Context context) {
        super( context, NOMBRE_BD, null, VERSION_BD );
        bd = this.getWritableDatabase();
        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_ALARMA);
        db.execSQL(CREAR_TABLA_NOTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ELIMINAR_TABLA + Tablas.ALARMA);
        db.execSQL(ELIMINAR_TABLA + Tablas.NOTA);
        onCreate(db);
    }

    public void insertarNota(String tit, String con, String fecha, String audio, int alarm, int salud, int tarea, int img, int edoemo){
        ContentValues cv = new ContentValues();
        cv.put("tit", tit);
        cv.put("con", con);
        cv.put("fecha", fecha);
        cv.put("audio", audio);
        cv.put("alarm", alarm);
        cv.put("salud", salud);
        cv.put("tarea", tarea);
        cv.put("img", img);
        cv.put("edoemo", edoemo);
        bd.insert("nota", null, cv);
    }

    public ArrayList<Nota> datosNota(){
        ArrayList<Nota> alNotas = new ArrayList<>();

        Cursor c = bd.rawQuery( LEER_TABLA_NOTA, null );
        if(c != null && c.getCount() > 0){
            c.moveToFirst();
            do {
                String tit = c.getString( c.getColumnIndex( "tit" ) );
                String con = c.getString( c.getColumnIndex( "con" ) );
                String fecha = c.getString( c.getColumnIndex( "fecha" ) );
                String audio = c.getString(c.getColumnIndex("audio"));
                int alarm = c.getInt( c.getColumnIndex( "alarm" ) );
                int salud = c.getInt( c.getColumnIndex( "salud" ) );
                int tarea = c.getInt( c.getColumnIndex( "tarea" ) );
                int img = c.getInt( c.getColumnIndex( "img" ) );
                int edoemo = c.getInt( c.getColumnIndex( "edoemo" ) );

                alNotas.add( new Nota(tit, con, fecha, audio, alarm, salud, tarea, img, edoemo) );
            } while(c.moveToNext());
        }
        if(c != null){
            c.close();
        }

        return alNotas;
    }

    public void insertarAlarma(int hh, int mm, String per, String nom){
        ContentValues cv = new ContentValues();
        cv.put( "hh", hh );
        cv.put( "mm", mm );
        cv.put( "per", per );
        cv.put( "nom", nom );
        bd.insert( "alarma", null, cv );
    }

    public ArrayList<Alarma> datosAlarma(){
        ArrayList<Alarma> alAlarmas = new ArrayList<>();

        Cursor c = bd.rawQuery( LEER_TABLA_ALARMA, null );
        if(c != null && c.getCount() > 0){
            c.moveToFirst();
            do {
                int hh = c.getInt( c.getColumnIndex( "hh" ) );
                int mm = c.getInt( c.getColumnIndex( "mm" ) );
                String per = c.getString( c.getColumnIndex( "per" ) );
                String nom = c.getString( c.getColumnIndex( "nom" ) );

                //alAlarmas.add( new Alarma(hh, mm, per, nom) );
            } while(c.moveToNext());
        }
        if(c != null){
            c.close();
        }

        return alAlarmas;
    }
}
