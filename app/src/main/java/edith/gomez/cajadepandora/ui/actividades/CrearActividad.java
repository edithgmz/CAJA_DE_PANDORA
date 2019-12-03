package edith.gomez.cajadepandora.ui.actividades;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;
import java.util.Objects;

import edith.gomez.cajadepandora.R;
import edith.gomez.cajadepandora.data.BaseDatos;

public class CrearActividad extends AppCompatActivity implements View.OnClickListener {
    private EditText etActividadNombre, etActividadDesc;
    private TextView tvActividadFecha, tvActividadHora;
    private RadioButton rbActividadCat1, rbActividadCat2, rbActividadCat3;
    private Button btnGuardarActividad, btnEliminarActividad;
    private BaseDatos baseDatos;
    private int diaAct, mesAct, anioAct, horaAct, minutosAct;
    private Bundle bDatos;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_actividad);
        //Vincular componentes
        Toolbar toolbar = findViewById(R.id.toolbarActividad);
        etActividadNombre = findViewById(R.id.etActividadNombre);
        etActividadDesc = findViewById(R.id.etActividadDesc);
        tvActividadFecha = findViewById(R.id.tvActividadFecha);
        tvActividadHora = findViewById(R.id.tvActividadHora);
        rbActividadCat1 = findViewById(R.id.rbActividadCat1);
        rbActividadCat2 = findViewById(R.id.rbActividadCat2);
        rbActividadCat3 = findViewById(R.id.rbActividadCat3);
        btnGuardarActividad = findViewById(R.id.btnGuardarActividad);
        btnEliminarActividad = findViewById(R.id.btnEliminarActividad);
        //Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //Obtener fecha y horaAct actuales
        final Calendar calendar = Calendar.getInstance();
        anioAct = calendar.get(Calendar.YEAR);
        mesAct = calendar.get(Calendar.MONTH);
        diaAct = calendar.get(Calendar.DAY_OF_MONTH);
        horaAct = calendar.get(Calendar.HOUR_OF_DAY);
        minutosAct = calendar.get(Calendar.MINUTE);
        String fecha;
        if (diaAct <= 9 && (mesAct + 1) <= 9) {
            fecha = "0" + diaAct + "-0" + (mesAct + 1) + "-" + anioAct;
        } else if (diaAct > 9 && (mesAct + 1) <= 9) {
            fecha = diaAct + "-0" + (mesAct + 1) + "-" + anioAct;
        } else if (diaAct <= 9) {
            fecha = "0" + diaAct + "-" + (mesAct + 1) + "-" + anioAct;
        } else {
            fecha = diaAct + "-" + (mesAct + 1) + "-" + anioAct;
        }
        String hora;
        if (horaAct <= 9 && minutosAct <= 9) {
            hora = "0" + horaAct + ":0" + minutosAct;
        } else if (horaAct > 9 && minutosAct <= 9) {
            hora = horaAct + ":0" + minutosAct;
        } else if (horaAct <= 9) {
            hora = "0" + horaAct + ":" + minutosAct;
        } else {
            hora = horaAct + ":" + minutosAct;
        }
        tvActividadFecha.setText(fecha);
        tvActividadHora.setText(hora);
        //Base de datos
        baseDatos = new BaseDatos(this);
        //Botones
        btnGuardarActividad.setOnClickListener(this);
        btnEliminarActividad.setOnClickListener(this);
        tvActividadFecha.setOnClickListener(this);
        tvActividadHora.setOnClickListener(this);
        //Obtener datos de la actividad padre
        Intent inDatos;
        if ((inDatos = getIntent()) != null) {
            bDatos = inDatos.getExtras();
        }
        //Ocultar botón para eliminar
        btnEliminarActividad.setVisibility(View.GONE);
    }

    @Override protected void onResume() {
        super.onResume();
        //Comprobar que hay valores
        if (bDatos != null) {
            String nombre = bDatos.getString("NOMBRE");
            String descripcion = bDatos.getString("DESCRIPCION");
            String fecha = bDatos.getString("FECHA");
            String categoria = bDatos.getString("CATEGORIA");
            int hora = bDatos.getInt("HORA");
            int minutos = bDatos.getInt("MINUTOS");
            String horaMinutos;
            //Asignar valores obtenidos
            etActividadNombre.setText(nombre);
            etActividadDesc.setText(descripcion);
            if (hora <= 9 && minutos <= 9) {
                horaMinutos = "0" + hora + ":0" + minutos;
            } else if (hora > 9 && minutos <= 9) {
                horaMinutos = hora + ":0" + minutos;
            } else if (hora <= 9) {
                horaMinutos = "0" + hora + ":" + minutos;
            } else {
                horaMinutos = hora + ":" + minutos;
            }
            tvActividadFecha.setText(fecha);
            tvActividadHora.setText(horaMinutos);
            if (categoria != null) {
                switch (categoria) {
                    case "verde":
                        rbActividadCat1.setChecked(true);
                        break;
                    case "amarillo":
                        rbActividadCat2.setChecked(true);
                        break;
                    case "rojo":
                        rbActividadCat3.setChecked(true);
                        break;
                }
            }
            //Mostrar botón para eliminar
            btnEliminarActividad.setVisibility(View.VISIBLE);
            //Ocultar botón para guardar
            btnGuardarActividad.setVisibility(View.GONE);
        }
    }

    @Override public void onClick(View v) {
        //Obtener datos
        String nombre = etActividadNombre.getText().toString();
        String descripcion = etActividadDesc.getText().toString();
        String categoria = "";
        if (rbActividadCat1.isChecked()) {
            categoria = "verde";
        } else if (rbActividadCat2.isChecked()) {
            categoria = "amarillo";
        } else if (rbActividadCat3.isChecked()) {
            categoria = "rojo";
        }
        String fecha = tvActividadFecha.getText().toString();
        int hora = Integer.parseInt(tvActividadHora.getText().toString().substring(0, 2));
        int minutos = Integer.parseInt(tvActividadHora.getText().toString().substring(3, 5));
        //Realizar acciones dependiendo del botón presionado
        switch (v.getId()) {
            case R.id.btnGuardarActividad:
                if (!nombre.equals("")) {
                    //Guardar en la base de datos
                    baseDatos.insertActividad(nombre, descripcion, fecha, categoria, hora, minutos);
                    baseDatos.close();
                    //Mostrar mensaje de confirmación
                    Toast.makeText(this, "Actividad guardada.", Toast.LENGTH_SHORT).show();
                    //Salir de la actividad
                    finish();
                } else {
                    //Mostrar advertencia
                    Toast.makeText(this, "Por favor indique un nombre.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnEliminarActividad:
                //Elimina de la base de datos
                baseDatos.deleteActividad(nombre, descripcion, fecha);
                baseDatos.close();
                //Mostrar mensaje de confirmación
                Toast.makeText(this, "Actividad eliminada.", Toast.LENGTH_SHORT).show();
                //Salir de la actividad
                finish();
                break;
            case R.id.tvActividadFecha:
                //Abrir selector de fecha
                DatePickerDialog seleccionarFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String fecha;
                        if (dayOfMonth <= 9 && (monthOfYear + 1) <= 9) {
                            fecha = "0" + dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year;
                        } else if (dayOfMonth > 9 && (monthOfYear + 1) <= 9) {
                            fecha = dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year;
                        } else if (dayOfMonth <= 9) {
                            fecha = "0" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        } else {
                            fecha = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        }
                        tvActividadFecha.setText(fecha);
                    }
                }, anioAct, mesAct, diaAct);
                seleccionarFecha.show();
                break;
            case R.id.tvActividadHora:
                //Abrir selector de hora
                TimePickerDialog seleccionarHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String horaMinutos;
                        if (hourOfDay <= 9 && minute <= 9) {
                            horaMinutos = "0" + hourOfDay + ":0" + minute;
                        } else if (hourOfDay > 9 && minute <= 9) {
                            horaMinutos = hourOfDay + ":0" + minute;
                        } else if (hourOfDay <= 9) {
                            horaMinutos = "0" + hourOfDay + ":" + minute;
                        } else {
                            horaMinutos = hourOfDay + ":" + minute;
                        }
                        tvActividadHora.setText(horaMinutos);
                    }
                }, horaAct, minutosAct, true);
                seleccionarHora.show();
                break;
        }
    }
}
