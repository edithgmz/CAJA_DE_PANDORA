package edith.gomez.cajadepandora.ui.salud;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import edith.gomez.cajadepandora.R;
import edith.gomez.cajadepandora.data.BaseDatos;
import edith.gomez.cajadepandora.data.salud.CategoriaMedicamento;

public class CrearMedicamento extends AppCompatActivity implements View.OnClickListener {
    private Button btnGuardarMedicamento, btnEliminarMedicamento;
    private EditText etMedNombre, etMedDosis, etMedCantidad;
    private TextView tvMedHora, tvMedCategoria;
    private Spinner spMedCategoria;
    private BaseDatos baseDatos;
    private int horaAct, minutosAct;
    private Bundle bDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_medicamento);
        //Vincular componentes
        Toolbar toolbar = findViewById(R.id.toolbarMedicamento);
        btnEliminarMedicamento = findViewById(R.id.btnEliminarMedicamento);
        btnGuardarMedicamento = findViewById(R.id.btnGuardarMedicamento);
        etMedNombre = findViewById(R.id.etMedNombre);
        etMedDosis = findViewById(R.id.etMedDosis);
        etMedCantidad = findViewById(R.id.etMedCantidad);
        tvMedHora = findViewById(R.id.tvMedHora);
        tvMedCategoria = findViewById(R.id.tvMedCategoria);
        spMedCategoria = findViewById(R.id.spMedCategoria);
        //Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //Obtener fecha y horaAct actuales
        final Calendar calendar = Calendar.getInstance();
        horaAct = calendar.get(Calendar.HOUR_OF_DAY);
        minutosAct = calendar.get(Calendar.MINUTE);
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
        tvMedHora.setText(hora);
        //Base de datos
        baseDatos = new BaseDatos(this);
        int totalCategorias = baseDatos.cantidadCategsMed();
        ArrayList<CategoriaMedicamento> lstCategorias = baseDatos.datosCategsMedicamento();
        //Categorías
        String[] categorias = new String[totalCategorias];
        if (lstCategorias != null && totalCategorias != 0) {
            for (int i = 0; i < totalCategorias; i++) {
                categorias[i] = lstCategorias.get(i).getNombre();
            }
        } else {
            spMedCategoria.setVisibility(View.GONE);
            tvMedCategoria.setVisibility(View.VISIBLE);
        }
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMedCategoria.setAdapter(spinnerArrayAdapter);
        //Escuchadores de eventos
        btnGuardarMedicamento.setOnClickListener(this);
        btnEliminarMedicamento.setOnClickListener(this);
        tvMedHora.setOnClickListener(this);
        //Obtener datos de la actividad padre
        Intent inDatos;
        if ((inDatos = getIntent()) != null) {
            bDatos = inDatos.getExtras();
        }
        //Ocultar botón para eliminar
        btnEliminarMedicamento.setVisibility(View.GONE);
    }

    @SuppressLint("DefaultLocale") @Override protected void onResume() {
        super.onResume();
        //Comprobar que hay valores
        if (bDatos != null) {
            String medicamento = bDatos.getString("MEDICAMENTO");
            String categoria = bDatos.getString("CATEGORIA");
            int dosis = bDatos.getInt("DOSIS");
            int cantidad = bDatos.getInt("CANTIDAD");
            int hora = bDatos.getInt("HORA");
            int minutos = bDatos.getInt("MINUTOS");
            String horaMinutos;
            //Asignar valores obtenidos
            etMedNombre.setText(medicamento);
            etMedDosis.setText(String.format("%d", dosis));
            etMedCantidad.setText(String.format("%d", cantidad));
            if (hora <= 9 && minutos <= 9) {
                horaMinutos = "0" + hora + ":0" + minutos;
            } else if (hora > 9 && minutos <= 9) {
                horaMinutos = hora + ":0" + minutos;
            } else if (hora <= 9) {
                horaMinutos = "0" + hora + ":" + minutos;
            } else {
                horaMinutos = hora + ":" + minutos;
            }
            tvMedHora.setText(horaMinutos);
            if (categoria != null) {
                tvMedCategoria.setText(categoria);
            }
            //Mostrar texto indicando la categoría
            tvMedCategoria.setVisibility(View.VISIBLE);
            //Ocultar spinner de categorías
            spMedCategoria.setVisibility(View.GONE);
            //Mostrar botón para eliminar
            btnEliminarMedicamento.setVisibility(View.VISIBLE);
            //Ocultar botón para guardar
            btnGuardarMedicamento.setVisibility(View.GONE);
            //Deshabilitar componentes
            etMedNombre.setEnabled(false);
            etMedDosis.setEnabled(false);
            etMedCantidad.setEnabled(false);
            tvMedHora.setEnabled(false);
        }
    }

    @Override public void onClick(View v) {
        //Obtener datos
        String medicamento = etMedNombre.getText().toString();
        int dosis = 0;
        if (!etMedDosis.getText().toString().equals("")) {
            dosis = Integer.parseInt(etMedDosis.getText().toString());
        }
        int cantidad = 0;
        if (!etMedCantidad.getText().toString().equals("")) {
            cantidad = Integer.parseInt(etMedCantidad.getText().toString());
        }
        String categoria = "";
        if (spMedCategoria.getSelectedItem() != null) {
            categoria = spMedCategoria.getSelectedItem().toString();
        }
        int hora = Integer.parseInt(tvMedHora.getText().toString().substring(0, 2));
        int minutos = Integer.parseInt(tvMedHora.getText().toString().substring(3, 5));
        switch (v.getId()) {
            case R.id.btnGuardarMedicamento:
                if ((!medicamento.equals("")) && (dosis != 0) && (cantidad != 0)) {
                    //Guardar en la base de datos
                    baseDatos.insertMedicamento(medicamento, categoria, dosis, hora, minutos, cantidad);
                    baseDatos.close();
                    //Mostrar mensaje de confirmación
                    Toast.makeText(this, "Medicamento registrado.", Toast.LENGTH_SHORT).show();
                    //Salir de la actividad
                    finish();
                } else {
                    //Mostrar advertencia
                    Toast.makeText(this, "Por favor indique medicamento, dosis y cantidad.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnEliminarMedicamento:
                //Eliminar de la base de datos
                baseDatos.deleteMedicamento(medicamento, dosis, hora, minutos);
                baseDatos.close();
                //Mostrar mensaje de confirmación
                Toast.makeText(this, "Medicamento eliminado.", Toast.LENGTH_SHORT).show();
                //Salir de la actividad
                finish();
                break;
            case R.id.tvMedHora:
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
                        tvMedHora.setText(horaMinutos);
                    }
                }, horaAct, minutosAct, true);
                seleccionarHora.show();
                break;
        }
    }
}
