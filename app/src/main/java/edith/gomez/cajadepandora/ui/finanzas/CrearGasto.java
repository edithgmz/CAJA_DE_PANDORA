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

public class CrearGasto extends AppCompatActivity implements View.OnClickListener {
    private Button btnGuardarGasto, btnEliminarGasto;
    private EditText etGastoCantidad, etGastoNota;
    private TextView tvGastoFecha;
    private Spinner spGastoCateg;
    private BaseDatos baseDatos;
    private int diaAct, mesAct, anioAct;
    private Bundle bDatos;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_gasto);
        //Vincular componentes
        Toolbar toolbar = findViewById(R.id.toolbarGasto);
        btnEliminarGasto = findViewById(R.id.btnEliminarGasto);
        btnGuardarGasto = findViewById(R.id.btnGuardarGasto);
        etGastoCantidad = findViewById(R.id.etGastoCantidad);
        etGastoNota = findViewById(R.id.etGastoNota);
        tvGastoFecha = findViewById(R.id.tvGastoFecha);
        spGastoCateg = findViewById(R.id.spGastoCateg);
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
        tvGastoFecha.setText(fecha);
        //Base de datos
        baseDatos = new BaseDatos(this);
        //Escuchadores de eventos
        btnGuardarGasto.setOnClickListener(this);
        btnEliminarGasto.setOnClickListener(this);
        tvGastoFecha.setOnClickListener(this);
        //Obtener datos de la actividad padre
        Intent inDatos;
        if ((inDatos = getIntent()) != null) {
            bDatos = inDatos.getExtras();
        }
        //Ocultar botón para eliminar
        btnEliminarGasto.setVisibility(View.GONE);
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
            etGastoCantidad.setText(String.format("%s", cantidad));
            etGastoNota.setText(nota);
            tvGastoFecha.setText(fecha);
            if (categoria != null) {
                switch (categoria) {
                    case "Alimentación":
                        spGastoCateg.setSelection(0);
                        break;
                    case "Automóvil":
                        spGastoCateg.setSelection(1);
                        break;
                    case "Educación":
                        spGastoCateg.setSelection(2);
                        break;
                    case "Entretenimiento":
                        spGastoCateg.setSelection(3);
                        break;
                    case "Facturas":
                        spGastoCateg.setSelection(4);
                        break;
                    case "Higiene":
                        spGastoCateg.setSelection(5);
                        break;
                    case "Hogar":
                        spGastoCateg.setSelection(6);
                        break;
                    case "Mascotas":
                        spGastoCateg.setSelection(7);
                        break;
                    case "Otros":
                        spGastoCateg.setSelection(8);
                        break;
                    case "Ropa":
                        spGastoCateg.setSelection(9);
                        break;
                    case "Salud":
                        spGastoCateg.setSelection(10);
                        break;
                    case "Transporte":
                        spGastoCateg.setSelection(11);
                        break;
                }
            }
            //Mostrar botón para eliminar
            btnEliminarGasto.setVisibility(View.VISIBLE);
            //Ocultar botón para guardar
            btnGuardarGasto.setVisibility(View.GONE);
            //Deshabilitar componentes
            etGastoCantidad.setEnabled(false);
            etGastoNota.setEnabled(false);
            tvGastoFecha.setEnabled(false);
            spGastoCateg.setEnabled(false);
        }
    }

    @Override public void onClick(View v) {
        //Obtener datos
        float cantidad = 0;
        if (!etGastoCantidad.getText().toString().equals("")) {
            cantidad = Float.parseFloat(etGastoCantidad.getText().toString());
        }
        String nota = etGastoNota.getText().toString();
        String fecha = tvGastoFecha.getText().toString();
        String categoria = spGastoCateg.getSelectedItem().toString();
        switch (v.getId()) {
            case R.id.btnGuardarGasto:
                if (cantidad != 0) {
                    //Guardar en la base de datos
                    baseDatos.insertGasto(cantidad, fecha, categoria, nota);
                    baseDatos.close();
                    //Mostrar mensaje de confirmación
                    Toast.makeText(this, "Gasto registrado.", Toast.LENGTH_SHORT).show();
                    //Salir de la actividad
                    finish();
                } else {
                    //Mostrar advertencia
                    Toast.makeText(this, "Por favor indique cantidad.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnEliminarGasto:
                //Eliminar de la base de datos
                baseDatos.deleteGasto(cantidad, fecha);
                baseDatos.close();
                //Mostrar mensaje de confirmación
                Toast.makeText(this, "Gasto eliminado.", Toast.LENGTH_SHORT).show();
                //Salir de la actividad
                finish();
                break;
            case R.id.tvGastoFecha:
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
                        tvGastoFecha.setText(fecha);
                    }
                }, anioAct, mesAct, diaAct);
                seleccionarFecha.show();
                break;
        }
    }
}
