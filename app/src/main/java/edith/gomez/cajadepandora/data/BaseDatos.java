package edith.gomez.cajadepandora.data;
/*
 * Created by Edith on 21-May-19.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import edith.gomez.cajadepandora.data.actividades.Actividad;
import edith.gomez.cajadepandora.data.actividades.CategoriaActividad;
import edith.gomez.cajadepandora.data.alarmas.Alarma;
import edith.gomez.cajadepandora.data.finanzas.CategoriaGasto;
import edith.gomez.cajadepandora.data.finanzas.CategoriaIngreso;
import edith.gomez.cajadepandora.data.finanzas.Finanzas;
import edith.gomez.cajadepandora.data.finanzas.Gasto;
import edith.gomez.cajadepandora.data.finanzas.Ingreso;
import edith.gomez.cajadepandora.data.notas.Nota;
import edith.gomez.cajadepandora.data.salud.CategoriaMedicamento;
import edith.gomez.cajadepandora.data.salud.Medicamento;

public class BaseDatos extends SQLiteOpenHelper {
    //Tabla actividades
    private static final String TABLA_ACTIVIDADES = "actividades";
    private static final String COL_ACT_NOM = "nombre";
    private static final String COL_ACT_DESC = "descripcion";
    private static final String COL_ACT_FECHA = "fecha";
    private static final String COL_ACT_CAT = "categoria";
    private static final String COL_ACT_HORA = "hora";
    private static final String COL_ACT_MINS = "minutos";
    private static final String CREAR_TABLA_ACTIVIDADES =
            "CREATE TABLE " + TABLA_ACTIVIDADES + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ACT_NOM + " TEXT, " + COL_ACT_DESC + " TEXT, " +
            COL_ACT_FECHA + " TEXT, " + COL_ACT_CAT + " TEXT, " + COL_ACT_HORA + " INTEGER, " + COL_ACT_MINS + " INTEGER)";
    private static final String LEER_TABLA_ACTIVIDADES = "SELECT * FROM " + TABLA_ACTIVIDADES;
    //Tabla alarmas
    private static final String TABLA_ALARMAS = "alarmas";
    private static final String COL_ALA_HORA = "hora";
    private static final String COL_ALA_MINS = "minutos";
    private static final String COL_ALA_POS_MINS = "posponerMinutos";
    private static final String COL_ALA_POS_VECES = "posponerVeces";
    private static final String COL_ALA_PER = "periodo";
    private static final String COL_ALA_NOM = "nombre";
    private static final String COL_ALA_TONO = "tono";
    private static final String COL_ALA_REP = "seRepite";
    private static final String COL_ALA_ACT = "estaActiva";
    private static final String COL_ALA_DIAS = "dias";
    private static final String CREAR_TABLA_ALARMA =
            "CREATE TABLE " + TABLA_ALARMAS + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ALA_HORA + " INTEGER, " + COL_ALA_MINS + " INTEGER, " +
            COL_ALA_POS_MINS + " INTEGER, " + COL_ALA_POS_VECES + " INTEGER, " + COL_ALA_PER + " INTEGER, " + COL_ALA_NOM + " TEXT, " + COL_ALA_TONO +
            " BLOB, " + COL_ALA_REP + " NUMERIC, " + COL_ALA_ACT + " NUMERIC, " + COL_ALA_DIAS + " TEXT)";
    private static final String LEER_TABLA_ALARMA = "SELECT * FROM " + TABLA_ALARMAS;
    //Tabla categorías actividades
    private static final String TABLA_CATEG_ACTIVS = "categoriasActividades";
    private static final String COL_CAT_ACT_COLOR = "color";
    private static final String COL_CAT_ACT_NOM = "nombre";
    private static final String CREAR_TABLA_CATEG_ACTVS =
            "CREATE TABLE " + TABLA_CATEG_ACTIVS + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_CAT_ACT_COLOR + " TEXT, " + COL_CAT_ACT_NOM +
            " TEXT)";
    private static final String LEER_TABLA_CATEG_ACTIVS = "SELECT * FROM " + TABLA_CATEG_ACTIVS;
    //Tabla categorías gastos
    private static final String TABLA_CATEG_GASTOS = "categoriasGastos";
    private static final String COL_CAT_GAS_NOM = "nombre";
    private static final String CREAR_TABLA_CATEG_GASTOS =
            "CREATE TABLE " + TABLA_CATEG_GASTOS + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_CAT_GAS_NOM + " TEXT)";
    private static final String LEER_TABLA_CATEG_GASTOS = "SELECT * FROM " + TABLA_CATEG_GASTOS;
    //Tabla categorías ingresos
    private static final String TABLA_CATEG_INGRESOS = "categoriasIngresos";
    private static final String COL_CAT_ING_NOM = "nombre";
    private static final String CREAR_TABLA_CATEG_INGRESOS =
            "CREATE TABLE " + TABLA_CATEG_INGRESOS + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_CAT_ING_NOM + " TEXT)";
    private static final String LEER_TABLA_CATEG_INGRESOS = "SELECT * FROM " + TABLA_CATEG_INGRESOS;
    //Tabla categorías medicamentos
    private static final String TABLA_CATEG_MEDS = "categoriasMedicamentos";
    private static final String COL_CAT_MED_NOM = "nombre";
    private static final String CREAR_TABLA_CATEG_MEDS =
            "CREATE TABLE " + TABLA_CATEG_MEDS + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_CAT_MED_NOM + " TEXT)";
    private static final String LEER_TABLA_CATEG_MEDS = "SELECT * FROM " + TABLA_CATEG_MEDS;
    //Tabla gastos
    private static final String TABLA_GASTOS = "gastos";
    private static final String COL_GAS_CANT = "cantidad";
    private static final String COL_GAS_FECHA = "fecha";
    private static final String COL_GAS_CAT = "categoria";
    private static final String COL_GAS_NOTA = "nota";
    private static final String CREAR_TABLA_GASTOS =
            "CREATE TABLE " + TABLA_GASTOS + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_GAS_CANT + " REAL, " + COL_GAS_FECHA + " TEXT, " +
            COL_GAS_CAT + " TEXT, " + COL_GAS_NOTA + " TEXT)";
    private static final String LEER_TABLA_GASTOS = "SELECT * FROM " + TABLA_GASTOS;
    //Tabla ingresos
    private static final String TABLA_INGRESOS = "ingresos";
    private static final String COL_ING_CANT = "cantidad";
    private static final String COL_ING_FECHA = "fecha";
    private static final String COL_ING_CAT = "categoria";
    private static final String COL_ING_NOTA = "nota";
    private static final String CREAR_TABLA_INGRESOS =
            "CREATE TABLE " + TABLA_INGRESOS + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ING_CANT + " REAL, " + COL_ING_FECHA + " TEXT, " +
            COL_ING_CAT + " TEXT, " + COL_ING_NOTA + " TEXT)";
    private static final String LEER_TABLA_INGRESOS = "SELECT * FROM " + TABLA_INGRESOS;
    //Tabla notas
    private static final String TABLA_NOTAS = "notas";
    private static final String COL_NOT_TITU = "titulo";
    private static final String COL_NOT_CONT = "contenido";
    private static final String COL_NOT_FECHA = "fecha";
    private static final String COL_NOT_IMG = "imagen";
    private static final String COL_NOT_AUDIO = "audio";
    private static final String COL_NOT_EDO_EMO = "estado_emocional";
    private static final String CREAR_TABLA_NOTAS =
            "CREATE TABLE " + TABLA_NOTAS + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NOT_TITU + " TEXT, " + COL_NOT_CONT + " TEXT, " +
            COL_NOT_FECHA + " TEXT, " + COL_NOT_IMG + " BLOB, " + COL_NOT_AUDIO + " BLOB, " + COL_NOT_EDO_EMO + " BLOB)";
    private static final String LEER_TABLA_NOTAS = "SELECT * FROM " + TABLA_NOTAS;
    //Tabla salud
    private static final String TABLA_SALUD = "salud";
    private static final String COL_SAL_MED = "medicamento";
    private static final String COL_SAL_CAT = "categoria";
    private static final String COL_SAL_DOSIS = "dosis";
    private static final String COL_SAL_HORA = "hora";
    private static final String COL_SAL_MINS = "minutos";
    private static final String COL_SAL_CANT = "cantidad";
    private static final String CREAR_TABLA_SALUD =
            "CREATE TABLE " + TABLA_SALUD + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_SAL_MED + " TEXT, " + COL_SAL_CAT + " TEXT, " +
            COL_SAL_DOSIS + " INTEGER, " + COL_SAL_HORA + " INTEGER, " + COL_SAL_MINS + " INTEGER, " + COL_SAL_CANT + " INTEGER)";
    private static final String LEER_TABLA_SALUD = "SELECT * FROM " + TABLA_SALUD;
    //Datos específicos de las tablas
    private static final String CANTIDAD_ACTIVIDADES = "SELECT count(*) AS " + TABLA_ACTIVIDADES + " FROM " + TABLA_ACTIVIDADES;
    private static final String CANTIDAD_ALARMAS = "SELECT count(*) AS " + TABLA_ALARMAS + " FROM " + TABLA_ALARMAS;
    private static final String CANTIDAD_SALUD = "SELECT count(*) AS " + TABLA_SALUD + " FROM " + TABLA_SALUD;
    private static final String CANTIDAD_MEDS_CATEG = "SELECT count(*) AS " + COL_SAL_CAT + " FROM " + TABLA_SALUD + " WHERE " + COL_SAL_CAT + "=?";
    private static final String CANTIDAD_CATEG_MEDS = "SELECT count(*) AS " + COL_SAL_CAT + " FROM " + TABLA_CATEG_MEDS;
    private static final String CANTIDAD_GASTOS = "SELECT count(*) AS " + COL_GAS_CANT + " FROM " + TABLA_GASTOS;
    private static final String CANTIDAD_INGRESOS = "SELECT count(*) AS " + COL_ING_CANT + " FROM " + TABLA_INGRESOS;
    private static final String GASTOS = "SELECT " + COL_GAS_CANT + ", " + COL_GAS_CAT + " FROM " + TABLA_GASTOS;
    private static final String INGRESOS = "SELECT " + COL_ING_CANT + ", " + COL_ING_CAT + " FROM " + TABLA_INGRESOS;
    private static final String NOTAS_POR_FECHA = "SELECT * FROM " + TABLA_NOTAS + " WHERE " + COL_NOT_FECHA + "=?";
    //Información base de datos
    private static final String NOMBRE_BD = "cajapandora.sqlite";
    private static final int VERSION_BD = 1;
    private static final String PAGINACION = "PRAGMA page_size = 65536";
    private SQLiteDatabase bd;

    public BaseDatos(Context context) {
        super( context, NOMBRE_BD, null, VERSION_BD );
        bd = this.getWritableDatabase();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) db.setForeignKeyConstraintsEnabled(true);
    }

    public static byte[] imageToByteArrayJPEG(ImageView imageView) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 25, baos);
        return baos.toByteArray();
    }

    public static byte[] imageToByteArrayPNG(ImageView imageView) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /*MÉTODOS PARA INSERTAR REGISTROS EN LAS TABLAS*/

    public static Bitmap byteArrayToImage(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PAGINACION);
        db.execSQL(CREAR_TABLA_ACTIVIDADES);
        db.execSQL(CREAR_TABLA_ALARMA);
        db.execSQL(CREAR_TABLA_CATEG_ACTVS);
        db.execSQL(CREAR_TABLA_CATEG_GASTOS);
        db.execSQL(CREAR_TABLA_CATEG_INGRESOS);
        db.execSQL(CREAR_TABLA_CATEG_MEDS);
        db.execSQL(CREAR_TABLA_GASTOS);
        db.execSQL(CREAR_TABLA_INGRESOS);
        db.execSQL(CREAR_TABLA_NOTAS);
        db.execSQL(CREAR_TABLA_SALUD);
    }

    public void insertActividad(String nombre, String descripcion, String fecha, String categoria, int hora, int minutos) {
        ContentValues cv = new ContentValues();
        cv.put(COL_ACT_NOM, nombre);
        cv.put(COL_ACT_DESC, descripcion);
        cv.put(COL_ACT_FECHA, fecha);
        cv.put(COL_ACT_CAT, categoria);
        cv.put(COL_ACT_HORA, hora);
        cv.put(COL_ACT_MINS, minutos);

        bd.insert(TABLA_ACTIVIDADES, null, cv);
    }

    public void insertAlarma(int hora, int minutos, int posponerMinutos, int posponerVeces, String periodo, String nombre, String descripcion,
                             byte[] tono, boolean seRepite, boolean estaActiva, String[] dias) {
        StringBuilder dia = new StringBuilder();
        for (String d : dias) {
            dia.append(d).append(",");
        }
        ContentValues cv = new ContentValues();
        cv.put(COL_ALA_HORA, hora);
        cv.put(COL_ALA_MINS, minutos);
        cv.put(COL_ALA_POS_MINS, posponerMinutos);
        cv.put(COL_ALA_POS_VECES, posponerVeces);
        cv.put(COL_ALA_PER, periodo);
        cv.put(COL_ALA_NOM, nombre);
        cv.put(COL_ALA_DIAS, descripcion);
        cv.put(COL_ALA_TONO, tono);
        cv.put(COL_ALA_REP, seRepite);
        cv.put(COL_ALA_ACT, estaActiva);
        cv.put(COL_ALA_DIAS, dia.toString());

        bd.insert(TABLA_ALARMAS, null, cv);
    }

    public void insertCategActividad(String color, String nombre) {
        ContentValues cv = new ContentValues();
        cv.put(COL_CAT_ACT_COLOR, color);
        cv.put(COL_CAT_ACT_NOM, nombre);

        bd.insert(TABLA_CATEG_ACTIVS, null, cv);
    }

    public void insertCategGasto(String nombre) {
        ContentValues cv = new ContentValues();
        cv.put(COL_CAT_ACT_NOM, nombre);

        bd.insert(TABLA_CATEG_ACTIVS, null, cv);
    }

    public void insertCategIngreso(String nombre) {
        ContentValues cv = new ContentValues();
        cv.put(COL_CAT_ACT_NOM, nombre);

        bd.insert(TABLA_CATEG_ACTIVS, null, cv);
    }

    public void insertCategMedicamento(String nombre) {
        ContentValues cv = new ContentValues();
        cv.put(COL_CAT_ACT_NOM, nombre);

        bd.insert(TABLA_CATEG_MEDS, null, cv);
    }

    public void insertGasto(float cantidad, String fecha, String categoria, String nota) {
        ContentValues cv = new ContentValues();
        cv.put(COL_GAS_CANT, cantidad);
        cv.put(COL_GAS_FECHA, fecha);
        cv.put(COL_GAS_CAT, categoria);
        cv.put(COL_GAS_NOTA, nota);

        bd.insert(TABLA_GASTOS, null, cv);
    }

    /*MÉTODOS PARA OBTENER LOS REGISTROS DE LAS TABLAS*/

    public void insertIngreso(float cantidad, String fecha, String categoria, String nota) {
        ContentValues cv = new ContentValues();
        cv.put(COL_ING_CANT, cantidad);
        cv.put(COL_ING_FECHA, fecha);
        cv.put(COL_ING_CAT, categoria);
        cv.put(COL_ING_NOTA, nota);

        bd.insert(TABLA_INGRESOS, null, cv);
    }

    public void insertNota(String titulo, String contenido, String fecha, byte[] imagen, byte[] audio, byte[] estado_emocional) {
        ContentValues cv = new ContentValues();
        cv.put(COL_NOT_TITU, titulo);
        cv.put(COL_NOT_CONT, contenido);
        cv.put(COL_NOT_FECHA, fecha);
        cv.put(COL_NOT_AUDIO, audio);
        cv.put(COL_NOT_IMG, imagen);
        cv.put(COL_NOT_EDO_EMO, estado_emocional);

        bd.insert(TABLA_NOTAS, null, cv);
    }

    public void insertMedicamento(String medicamento, String categoria, int dosis, int hora, int minutos, int cantidad) {
        ContentValues cv = new ContentValues();
        cv.put(COL_SAL_MED, medicamento);
        cv.put(COL_SAL_CAT, categoria);
        cv.put(COL_SAL_DOSIS, dosis);
        cv.put(COL_SAL_HORA, hora);
        cv.put(COL_SAL_MINS, minutos);
        cv.put(COL_SAL_CANT, cantidad);

        bd.insert(TABLA_SALUD, null, cv);
    }

    public ArrayList<Actividad> datosActividad() {
        ArrayList<Actividad> alActividades = new ArrayList<>();

        Cursor c = bd.rawQuery(LEER_TABLA_ACTIVIDADES, null);
        if(c != null && c.getCount() > 0){
            c.moveToFirst();
            do {
                String nombre = c.getString(c.getColumnIndex(COL_ACT_NOM));
                String descripcion = c.getString(c.getColumnIndex(COL_ACT_DESC));
                String fecha = c.getString(c.getColumnIndex(COL_ACT_FECHA));
                String categoria = c.getString(c.getColumnIndex(COL_ACT_CAT));
                int hora = c.getInt(c.getColumnIndex(COL_ACT_HORA));
                int minutos = c.getInt(c.getColumnIndex(COL_ACT_MINS));

                alActividades.add(new Actividad(nombre, descripcion, fecha, categoria, hora, minutos));
            } while(c.moveToNext());
        }
        if(c != null){
            c.close();
        }

        return alActividades;
    }

    public ArrayList<Alarma> datosAlarma(){
        ArrayList<Alarma> alAlarmas = new ArrayList<>();

        Cursor c = bd.rawQuery(LEER_TABLA_ALARMA, null);
        if(c != null && c.getCount() > 0){
            c.moveToFirst();
            do {
                int hora = c.getInt(c.getColumnIndex(COL_ALA_HORA));
                int minutos = c.getInt(c.getColumnIndex(COL_ALA_MINS));
                int posponerMinutos = c.getInt(c.getColumnIndex(COL_ALA_POS_MINS));
                int posponerVeces = c.getInt(c.getColumnIndex(COL_ALA_POS_VECES));
                String periodo = c.getString(c.getColumnIndex(COL_ALA_PER));
                String nombre = c.getString(c.getColumnIndex(COL_ALA_NOM));
                byte[] tono = c.getBlob(c.getColumnIndex(COL_ALA_TONO));
                int repetir = c.getInt(c.getColumnIndex(COL_ALA_REP));
                int activa = c.getInt(c.getColumnIndex(COL_ALA_ACT));
                boolean seRepite;
                boolean estaActiva;
                seRepite = repetir > 0;
                estaActiva = activa > 0;
                /*if(repetir > 0) seRepite = true; else seRepite = false;
                if(activa > 0) estaActiva = true; else estaActiva = false;*/
                String dia = c.getString(c.getColumnIndex(COL_ALA_DIAS));
                String[] dias = dia.split(",");

                alAlarmas.add(new Alarma(hora, minutos, posponerMinutos, posponerVeces, periodo, nombre, tono, seRepite, estaActiva, dias));
            } while(c.moveToNext());
        }
        if(c != null){
            c.close();
        }

        return alAlarmas;
    }

    public ArrayList<CategoriaActividad> datosCategsActividad() {
        ArrayList<CategoriaActividad> alCategsActivs = new ArrayList<>();

        Cursor c = bd.rawQuery(LEER_TABLA_CATEG_ACTIVS, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String color = c.getString(c.getColumnIndex(COL_CAT_ACT_COLOR));
                String nombre = c.getString(c.getColumnIndex(COL_CAT_ACT_NOM));

                alCategsActivs.add(new CategoriaActividad(color, nombre));
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }

        return alCategsActivs;
    }

    public ArrayList<CategoriaGasto> datosCategsGasto() {
        ArrayList<CategoriaGasto> alCategsGasto = new ArrayList<>();

        Cursor c = bd.rawQuery(LEER_TABLA_CATEG_GASTOS, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String nombre = c.getString(c.getColumnIndex(COL_CAT_GAS_NOM));

                alCategsGasto.add(new CategoriaGasto(nombre));
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }

        return alCategsGasto;
    }

    public ArrayList<CategoriaIngreso> datosCategsIngreso() {
        ArrayList<CategoriaIngreso> alCategsIngreso = new ArrayList<>();

        Cursor c = bd.rawQuery(LEER_TABLA_CATEG_INGRESOS, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String nombre = c.getString(c.getColumnIndex(COL_CAT_ING_NOM));

                alCategsIngreso.add(new CategoriaIngreso(nombre));
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }

        return alCategsIngreso;
    }

    public ArrayList<CategoriaMedicamento> datosCategsMedicamento() {
        ArrayList<CategoriaMedicamento> alCategsMedicamento = new ArrayList<>();

        Cursor c = bd.rawQuery(LEER_TABLA_CATEG_MEDS, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String nombre = c.getString(c.getColumnIndex(COL_CAT_MED_NOM));

                alCategsMedicamento.add(new CategoriaMedicamento(nombre));
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }

        return alCategsMedicamento;
    }

    public ArrayList<Gasto> datosGasto() {
        ArrayList<Gasto> alGastos = new ArrayList<>();

        Cursor c = bd.rawQuery(LEER_TABLA_GASTOS, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                float cantidad = c.getFloat(c.getColumnIndex(COL_GAS_CANT));
                String fecha = c.getString(c.getColumnIndex(COL_GAS_FECHA));
                String categoria = c.getString(c.getColumnIndex(COL_GAS_CAT));
                String nota = c.getString(c.getColumnIndex(COL_GAS_NOTA));

                alGastos.add(new Gasto(cantidad, fecha, categoria, nota));
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }

        return alGastos;
    }

    /*MÉTODOS PARA OBTENER DATOS ESPECÍFICOS DE LAS TABLAS*/

    public ArrayList<Ingreso> datosIngreso() {
        ArrayList<Ingreso> alIngresos = new ArrayList<>();

        Cursor c = bd.rawQuery(LEER_TABLA_INGRESOS, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                float cantidad = c.getFloat(c.getColumnIndex(COL_ING_CANT));
                String fecha = c.getString(c.getColumnIndex(COL_ING_FECHA));
                String categoria = c.getString(c.getColumnIndex(COL_ING_CAT));
                String nota = c.getString(c.getColumnIndex(COL_ING_NOTA));

                alIngresos.add(new Ingreso(cantidad, fecha, categoria, nota));
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }

        return alIngresos;
    }

    public ArrayList<Nota> datosNota() {
        ArrayList<Nota> alNotas = new ArrayList<>();

        Cursor c = bd.rawQuery(LEER_TABLA_NOTAS, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String titulo = c.getString(c.getColumnIndex(COL_NOT_TITU));
                String contenido = c.getString(c.getColumnIndex(COL_NOT_CONT));
                String fecha = c.getString(c.getColumnIndex(COL_NOT_FECHA));
                byte[] imagen = c.getBlob(c.getColumnIndex(COL_NOT_IMG));
                byte[] audio = c.getBlob(c.getColumnIndex(COL_NOT_AUDIO));
                byte[] estado_emocional = c.getBlob(c.getColumnIndex(COL_NOT_EDO_EMO));

                alNotas.add(new Nota(titulo, contenido, fecha, imagen, audio, estado_emocional));
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }

        return alNotas;
    }

    public ArrayList<Medicamento> datosMedicamento() {
        ArrayList<Medicamento> alMedicamentos = new ArrayList<>();

        Cursor c = bd.rawQuery(LEER_TABLA_SALUD, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String medicamento = c.getString(c.getColumnIndex(COL_SAL_MED));
                String categoria = c.getString(c.getColumnIndex(COL_SAL_CAT));
                int dosis = c.getInt(c.getColumnIndex(COL_SAL_DOSIS));
                int hora = c.getInt(c.getColumnIndex(COL_SAL_HORA));
                int minutos = c.getInt(c.getColumnIndex(COL_SAL_MINS));
                int cantidad = c.getInt(c.getColumnIndex(COL_SAL_CANT));

                alMedicamentos.add(new Medicamento(medicamento, categoria, dosis, hora, minutos, cantidad));
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }

        return alMedicamentos;
    }

    public int cantidadActividades() {
        int actividades = 0;

        Cursor c = bd.rawQuery(CANTIDAD_ACTIVIDADES, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                actividades = c.getInt(c.getColumnIndex(TABLA_ACTIVIDADES));
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }

        return actividades;
    }

    public int cantidadAlarmas() {
        int alarmas = 0;

        Cursor c = bd.rawQuery(CANTIDAD_ALARMAS, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                alarmas = c.getInt(c.getColumnIndex(TABLA_ALARMAS));
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }

        return alarmas;
    }

    public int cantidadSalud() {
        int salud = 0;

        Cursor c = bd.rawQuery(CANTIDAD_SALUD, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                salud = c.getInt(c.getColumnIndex(TABLA_SALUD));
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }

        return salud;
    }

    public ArrayList<Finanzas> datosFinanzas() {
        ArrayList<Finanzas> alFinanzas = new ArrayList<>();

        Cursor c = bd.rawQuery(GASTOS + " UNION " + INGRESOS, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                float cantidad = c.getFloat(c.getColumnIndex(COL_GAS_CANT));
                String categoria = c.getString(c.getColumnIndex(COL_ING_CAT));

                alFinanzas.add(new Finanzas(cantidad, categoria));
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }

        return alFinanzas;
    }

    public int cantidadGastos() {
        int gastos = 0;

        Cursor c = bd.rawQuery(CANTIDAD_GASTOS, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                gastos = c.getInt(c.getColumnIndex(COL_GAS_CANT));
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }

        return gastos;
    }

    public int cantidadIngresos() {
        int ingresos = 0;

        Cursor c = bd.rawQuery(CANTIDAD_INGRESOS, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                ingresos = c.getInt(c.getColumnIndex(COL_ING_CANT));
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }

        return ingresos;
    }

    /*MÉTODOS PARA ELIMINAR REGISTROS DE LAS TABLAS*/

    public int cantidadMedsPorCateg(String[] categoria) {
        int categorias = 0;

        Cursor c = bd.rawQuery(CANTIDAD_MEDS_CATEG, categoria);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                categorias = c.getInt(c.getColumnIndex(COL_SAL_CAT));
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }

        return categorias;
    }

    public int cantidadCategsMed() {
        int categorias = 0;

        Cursor c = bd.rawQuery(CANTIDAD_CATEG_MEDS, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                categorias = c.getInt(c.getColumnIndex(COL_SAL_CAT));
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }

        return categorias;
    }

    public ArrayList<Nota> notasPorFecha(String[] fecha) {
        ArrayList<Nota> alNotas = new ArrayList<>();

        Cursor c = bd.rawQuery(NOTAS_POR_FECHA, fecha);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String titulo = c.getString(c.getColumnIndex(COL_NOT_TITU));
                String contenido = c.getString(c.getColumnIndex(COL_NOT_CONT));
                String fecha1 = c.getString(c.getColumnIndex(COL_NOT_FECHA));
                byte[] imagen = c.getBlob(c.getColumnIndex(COL_NOT_IMG));
                byte[] audio = c.getBlob(c.getColumnIndex(COL_NOT_AUDIO));
                byte[] estado_emocional = c.getBlob(c.getColumnIndex(COL_NOT_EDO_EMO));

                alNotas.add(new Nota(titulo, contenido, fecha1, imagen, audio, estado_emocional));
            } while (c.moveToNext());
        }
        if (c != null) {
            c.close();
        }

        return alNotas;
    }

    public void deleteActividad(String nombre, String descripcion, String fecha) {
        String condicion = COL_ACT_NOM + "=? AND " + COL_ACT_DESC + "=? AND " + COL_ACT_FECHA + "=?";
        String[] argumentos = new String[]{nombre, descripcion, fecha};

        bd.delete(TABLA_ACTIVIDADES, condicion, argumentos);
    }

    public void deleteAlarma(int hora, int minutos) {
        String condicion = COL_ALA_HORA + "=? AND " + COL_ALA_MINS + "=?";
        String[] argumentos = new String[]{String.valueOf(hora), String.valueOf(minutos)};

        bd.delete(TABLA_ALARMAS, condicion, argumentos);
    }

    public void deleteCategActividad(String color, String nombre) {
        String condicion = COL_CAT_ACT_COLOR + "=? AND " + COL_CAT_ACT_NOM + "=?";
        String[] argumentos = new String[]{color, nombre};

        bd.delete(TABLA_CATEG_ACTIVS, condicion, argumentos);
    }

    public void deleteCategGasto(String nombre) {
        String condicion = COL_CAT_GAS_NOM + "=?";
        String[] argumentos = new String[]{nombre};

        bd.delete(TABLA_CATEG_GASTOS, condicion, argumentos);
    }

    public void deleteCategIngreso(String nombre) {
        String condicion = COL_CAT_ING_NOM + "=?";
        String[] argumentos = new String[]{nombre};

        bd.delete(TABLA_CATEG_INGRESOS, condicion, argumentos);
    }

    public void deleteCategMedicamento(String nombre) {
        String condicion = COL_CAT_MED_NOM + "=?";
        String[] argumentos = new String[]{nombre};

        bd.delete(TABLA_CATEG_MEDS, condicion, argumentos);
    }

    public void deleteGasto(float cantidad, String fecha) {
        String condicion = COL_GAS_CANT + "=? AND " + COL_GAS_FECHA + "=?";
        String[] argumentos = new String[]{String.valueOf(cantidad), fecha};

        bd.delete(TABLA_GASTOS, condicion, argumentos);
    }

    /*MÉTODOS PARA ACTUALIZAR REGISTROS DE LAS TABLAS*/

    public void deleteIngreso(float cantidad, String fecha) {
        String condicion = COL_ING_CANT + "=? AND " + COL_ING_FECHA + "=?";
        String[] argumentos = new String[]{String.valueOf(cantidad), fecha};

        bd.delete(TABLA_INGRESOS, condicion, argumentos);
    }

    public void deleteNota(String titulo, String contenido, String fecha) {
        String condicion = COL_NOT_TITU + "=? AND " + COL_NOT_CONT + "=? AND " + COL_NOT_FECHA + "=?";
        String[] argumentos = new String[]{titulo, contenido, fecha};

        bd.delete(TABLA_NOTAS, condicion, argumentos);
    }

    public void deleteMedicamento(String medicamento, int dosis, int hora, int minutos) {
        String condicion = COL_SAL_MED + "=? AND " + COL_SAL_DOSIS + "=? AND " + COL_SAL_HORA + "=? AND " + COL_SAL_MINS + "=?";
        String[] argumentos = new String[]{medicamento, String.valueOf(dosis), String.valueOf(hora), String.valueOf(minutos)};

        bd.delete(TABLA_SALUD, condicion, argumentos);
    }

    public void updateActividad(String nombre, String descripcion, String fecha, String categoria, int hora, int minutos) {
        String condicion = COL_ACT_NOM + "=? AND " + COL_ACT_DESC + "=? AND " + COL_ACT_FECHA + "=? AND " + COL_ACT_CAT + "=?";
        String[] argumentos = new String[]{nombre, descripcion, fecha, categoria};
        ContentValues cv = new ContentValues();
        cv.put(COL_ACT_NOM, nombre);
        cv.put(COL_ACT_DESC, descripcion);
        cv.put(COL_ACT_FECHA, fecha);
        cv.put(COL_ACT_CAT, categoria);
        cv.put(COL_ACT_HORA, hora);
        cv.put(COL_ACT_MINS, minutos);

        bd.update(TABLA_ACTIVIDADES, cv, condicion, argumentos);
    }

    public void updateAlarma(int hora, int minutos, int posponerMinutos, int posponerVeces, String periodo, String nombre, String descripcion,
                             byte[] tono, boolean seRepite, boolean estaActiva, String[] dias) {
        String condicion = COL_ALA_HORA + "=? AND " + COL_ALA_MINS + "=? AND " + COL_ALA_PER + "=? AND " + COL_ALA_NOM + "=?";
        String[] argumentos = new String[]{String.valueOf(hora), String.valueOf(minutos), periodo, nombre};
        StringBuilder dia = new StringBuilder();
        for (String d : dias) {
            dia.append(d).append(",");
        }
        ContentValues cv = new ContentValues();
        cv.put(COL_ALA_HORA, hora);
        cv.put(COL_ALA_MINS, minutos);
        cv.put(COL_ALA_POS_MINS, posponerMinutos);
        cv.put(COL_ALA_POS_VECES, posponerVeces);
        cv.put(COL_ALA_PER, periodo);
        cv.put(COL_ALA_DIAS, nombre);
        cv.put(COL_ALA_DIAS, descripcion);
        cv.put(COL_ALA_DIAS, tono);
        cv.put(COL_ALA_REP, seRepite);
        cv.put(COL_ALA_ACT, estaActiva);
        cv.put(COL_ALA_DIAS, dia.toString());

        bd.update(TABLA_ALARMAS, cv, condicion, argumentos);
    }

    public void updateCategActividad(String color, String nombre) {
        String condicion = COL_CAT_ACT_COLOR + "=? AND " + COL_CAT_ACT_NOM + "=?";
        String[] argumentos = new String[]{color, nombre};
        ContentValues cv = new ContentValues();
        cv.put(COL_CAT_ACT_COLOR, color);
        cv.put(COL_CAT_ACT_NOM, nombre);

        bd.update(TABLA_CATEG_ACTIVS, cv, condicion, argumentos);
    }

    public void updateCategGasto(String nombre) {
        String condicion = COL_CAT_GAS_NOM + "=?";
        String[] argumentos = new String[]{nombre};
        ContentValues cv = new ContentValues();
        cv.put(COL_CAT_ACT_NOM, nombre);

        bd.update(TABLA_CATEG_GASTOS, cv, condicion, argumentos);
    }

    public void updateCategIngreso(String nombre) {
        String condicion = COL_CAT_ING_NOM + "=?";
        String[] argumentos = new String[]{nombre};
        ContentValues cv = new ContentValues();
        cv.put(COL_CAT_ACT_NOM, nombre);

        bd.update(TABLA_CATEG_INGRESOS, cv, condicion, argumentos);
    }

    public void updateCategMedicamento(String nombre) {
        String condicion = COL_CAT_MED_NOM + "=?";
        String[] argumentos = new String[]{nombre};
        ContentValues cv = new ContentValues();
        cv.put(COL_CAT_ACT_NOM, nombre);

        bd.update(TABLA_CATEG_MEDS, cv, condicion, argumentos);
    }

    public void updateGasto(float cantidad, String fecha, String nota) {
        String condicion = COL_GAS_CANT + "=? AND " + COL_GAS_FECHA + "=?";
        String[] argumentos = new String[]{String.valueOf(cantidad), fecha};
        ContentValues cv = new ContentValues();
        cv.put(COL_GAS_CANT, cantidad);
        cv.put(COL_GAS_FECHA, fecha);
        cv.put(COL_GAS_NOTA, nota);

        bd.update(TABLA_GASTOS, cv, condicion, argumentos);
    }

    /*MÉTODOS PARA CONVERTIR ARREGLO DE BYTES EN BITMAP Y VICEVERSA*/

    public void updateIngreso(float cantidad, String fecha, String nota) {
        String condicion = COL_ING_CANT + "=? AND " + COL_ING_FECHA + "=?";
        String[] argumentos = new String[]{String.valueOf(cantidad), fecha};
        ContentValues cv = new ContentValues();
        cv.put(COL_ING_CANT, cantidad);
        cv.put(COL_ING_FECHA, fecha);
        cv.put(COL_ING_NOTA, nota);

        bd.update(TABLA_GASTOS, cv, condicion, argumentos);
    }

    public void updateNota(String titulo, String contenido, String fecha, byte[] imagen, byte[] audio, byte[] edoemo) {
        String condicion = COL_NOT_TITU + "=? AND " + COL_NOT_CONT + "=? AND " + COL_NOT_FECHA + "=?";
        String[] argumentos = new String[]{titulo, contenido, fecha};
        ContentValues cv = new ContentValues();
        cv.put(COL_NOT_TITU, titulo);
        cv.put(COL_NOT_CONT, contenido);
        cv.put(COL_NOT_FECHA, fecha);
        cv.put(COL_NOT_AUDIO, audio);
        cv.put(COL_NOT_IMG, imagen);
        cv.put(COL_NOT_EDO_EMO, edoemo);

        bd.update(TABLA_NOTAS, cv, condicion, argumentos);
    }

    public void updateMedicamento(String medicamento, int dosis, int hora, int minutos, int cantidad) {
        String condicion = COL_SAL_MED + "=? AND " + COL_SAL_DOSIS + "=? AND " + COL_SAL_HORA + "=? AND " + COL_SAL_MINS + "=?";
        String[] argumentos = new String[]{medicamento, String.valueOf(dosis), String.valueOf(hora), String.valueOf(minutos)};
        ContentValues cv = new ContentValues();
        cv.put(COL_SAL_MED, medicamento);
        cv.put(COL_SAL_DOSIS, dosis);
        cv.put(COL_SAL_HORA, hora);
        cv.put(COL_SAL_MINS, minutos);
        cv.put(COL_SAL_CANT, cantidad);

        bd.update(TABLA_SALUD, cv, condicion, argumentos);
    }
}
