package edith.gomez.cajadepandora.ui.finanzas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;
import java.util.Objects;

import edith.gomez.cajadepandora.R;
import edith.gomez.cajadepandora.data.BaseDatos;

public class CrearIngreso extends AppCompatActivity implements View.OnClickListener {
    private Button btnGuardarIngreso, btnEliminarIngreso;
    private EditText etIngresoCantidad, etIngresoNota;
    private TextView tvIngresoFecha;
    private Spinner spIngresoCateg;
    private BaseDatos baseDatos;
    private int diaAct, mesAct, anioAct;
    private Bundle bDatos;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_ingreso);
        //Vincular componentes
        Toolbar toolbar = findViewById(R.id.toolbarIngreso);
        btnEliminarIngreso = findViewById(R.id.btnEliminarIngreso);
        btnGuardarIngreso = findViewById(R.id.btnGuardarIngreso);
        etIngresoCantidad = findViewById(R.id.etIngresoCantidad);
        etIngresoNota = findViewById(R.id.etIngresoNota);
        tvIngresoFecha = findViewById(R.id.tvIngresoFecha);
        spIngresoCateg = findViewById(R.id.spIngresoCateg);
        //Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //Obtener fecha y hora actuales
        final Calendar calendar = Calendar.getInstance();
        anioAct = calendar.get(Calendar.YEAR);
        mesAct = calendar.get(Calendar.MONTH);
        diaAct = calendar.get(Calendar.DAY_OF_MONTH);
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
        tvIngresoFecha.setText(fecha);
        //Base de datos
        baseDatos = new BaseDatos(this);
        //Escuchadores de eventos
        btnGuardarIngreso.setOnClickListener(this);
        btnEliminarIngreso.setOnClickListener(this);
        tvIngresoFecha.setOnClickListener(this);
        //Obtener datos de la actividad padre
        Intent inDatos;
        if ((inDatos = getIntent()) != null) {
            bDatos = inDatos.getExtras();
        }
        //Ocultar botón para eliminar
        btnEliminarIngreso.setVisibility(View.GONE);
    }

    @Override protected void onResume() {
        super.onResume();
        //Comprobar que hay valores
        if (bDatos != null) {
            float cantidad = bDatos.getFloat("CANTIDAD");
            String fecha = bDatos.getString("FECHA");
            String categoria = bDatos.getString("CATEGORIA");
            String nota = bDatos.getString("NOTA");
            //Asignar valores obtenidos
            etIngresoCantidad.setText(String.format("%s", cantidad));
            etIngresoNota.setText(nota);
            tvIngresoFecha.setText(fecha);
            if (categoria != null) {
                switch (categoria) {
                    case "Beca":
                        spIngresoCateg.setSelection(0);
                        break;
                    case "Cupones":
                        spIngresoCateg.setSelection(1);
                        break;
                    case "Inversiones":
                        spIngresoCateg.setSelection(2);
                        break;
                    case "Otros":
                        spIngresoCateg.setSelection(3);
                        break;
                    case "Premio":
                        spIngresoCateg.setSelection(4);
                        break;
                    case "Regalo":
                        spIngresoCateg.setSelection(5);
                        break;
                    case "Salario":
                        spIngresoCateg.setSelection(6);
                        break;
                }
            }
            //Mostrar botón para eliminar
            btnEliminarIngreso.setVisibility(View.VISIBLE);
            //Ocultar botón para guardar
            btnGuardarIngreso.setVisibility(View.GONE);
            //Deshabilitar componentes
            etIngresoCantidad.setEnabled(false);
            etIngresoNota.setEnabled(false);
            tvIngresoFecha.setEnabled(false);
            spIngresoCateg.setEnabled(false);
        }
    }

    @Override public void onClick(View v) {
        //Obtener datos
        float cantidad = 0;
        if (!etIngresoCantidad.getText().toString().equals("")) {
            cantidad = Float.parseFloat(etIngresoCantidad.getText().toString());
        }
        String nota = etIngresoNota.getText().toString();
        String fecha = tvIngresoFecha.getText().toString();
        String categoria = spIngresoCateg.getSelectedItem().toString();
        switch (v.getId()) {
            case R.id.btnGuardarIngreso:
                if (cantidad != 0) {
                    //Guardar en la base de datos
                    baseDatos.insertIngreso(cantidad, fecha, categoria, nota);
                    baseDatos.close();
                    //Mostrar mensaje de confirmación
                    Toast.makeText(this, "Ingreso registrado.", Toast.LENGTH_SHORT).show();
                    //Salir de la actividad
                    finish();
                } else {
                    //Mostrar advertencia
                    Toast.makeText(this, "Por favor indique cantidad.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnEliminarIngreso:
                //Eliminar de la base de datos
                baseDatos.deleteIngreso(cantidad, fecha);
                baseDatos.close();
                //Mostrar mensaje de confirmación
                Toast.makeText(this, "Ingreso eliminado.", Toast.LENGTH_SHORT).show();
                //Salir de la actividad
                finish();
                break;
            case R.id.tvIngresoFecha:
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
                        tvIngresoFecha.setText(fecha);
                    }
                }, anioAct, mesAct, diaAct);
                seleccionarFecha.show();
                break;
        }
    }
}
