package edith.gomez.cajadepandora.ui.alarmas;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;
import java.util.Objects;

import edith.gomez.cajadepandora.R;
import edith.gomez.cajadepandora.data.BaseDatos;

public class CrearAlarma extends AppCompatActivity implements View.OnClickListener {
    private TextView tvAlarmaHora;
    private CheckBox chbAlarmaDom, chbAlarmaLun, chbAlarmaMar, chbAlarmaMie, chbAlarmaJue, chbAlarmaVie, chbAlarmaSab, chbAlarmaRepetir;
    private EditText etAlarmaNombre;
    private Button btnGuardarAlarma, btnEliminarAlarma;
    private BaseDatos baseDatos;
    private int horaAct, minutosAct;
    private Bundle bDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_alarma);
        //Vincular componentes
        Toolbar toolbar = findViewById(R.id.toolbarAlarma);
        tvAlarmaHora = findViewById(R.id.tvAlarmaHora);
        chbAlarmaDom = findViewById(R.id.chbAlarmaDom);
        chbAlarmaLun = findViewById(R.id.chbAlarmaLun);
        chbAlarmaMar = findViewById(R.id.chbAlarmaMar);
        chbAlarmaMie = findViewById(R.id.chbAlarmaMie);
        chbAlarmaJue = findViewById(R.id.chbAlarmaJue);
        chbAlarmaVie = findViewById(R.id.chbAlarmaVie);
        chbAlarmaSab = findViewById(R.id.chbAlarmaSab);
        chbAlarmaRepetir = findViewById(R.id.chbAlarmaRepetir);
        etAlarmaNombre = findViewById(R.id.etAlarmaNombre);
        btnGuardarAlarma = findViewById(R.id.btnGuardarAlarma);
        btnEliminarAlarma = findViewById(R.id.btnEliminarAlarma);
        //Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //Obtener horaAct actual
        final Calendar calendar = Calendar.getInstance();
        horaAct = calendar.get(Calendar.HOUR_OF_DAY);
        minutosAct = calendar.get(Calendar.MINUTE);
        String horaMinutos;
        if (horaAct <= 9 && minutosAct <= 9) {
            horaMinutos = "0" + horaAct + ":0" + minutosAct;
        } else if (horaAct > 9 && minutosAct <= 9) {
            horaMinutos = horaAct + ":0" + minutosAct;
        } else if (horaAct <= 9) {
            horaMinutos = "0" + horaAct + ":" + minutosAct;
        } else {
            horaMinutos = horaAct + ":" + minutosAct;
        }
        tvAlarmaHora.setText(horaMinutos);
        //Base de datos
        baseDatos = new BaseDatos(this);
        //Escuchador para el evento click
        btnGuardarAlarma.setOnClickListener(this);
        btnEliminarAlarma.setOnClickListener(this);
        tvAlarmaHora.setOnClickListener(this);
        //Obtener datos de la actividad padre
        Intent inDatos;
        if ((inDatos = getIntent()) != null) {
            bDatos = inDatos.getExtras();
        }
        //Ocultar botón para eliminar
        btnEliminarAlarma.setVisibility(View.GONE);
    }

    @Override protected void onResume() {
        super.onResume();
        //Comprobar que hay valores
        if (bDatos != null) {
            int hora = bDatos.getInt("HORA");
            int minutos = bDatos.getInt("MINUTOS");
            String horaMinutos;
            String nombre = bDatos.getString("NOMBRE");
            boolean se_repite = bDatos.getBoolean("SE_REPITE");
            String[] dias = bDatos.getStringArray("DIAS");
            //Asignar valores obtenidos
            if (hora <= 9 && minutos <= 9) {
                horaMinutos = "0" + hora + ":0" + minutos;
            } else if (hora > 9 && minutos <= 9) {
                horaMinutos = hora + ":0" + minutos;
            } else if (hora <= 9) {
                horaMinutos = "0" + hora + ":" + minutos;
            } else {
                horaMinutos = hora + ":" + minutos;
            }
            tvAlarmaHora.setText(horaMinutos);
            etAlarmaNombre.setText(nombre);
            if (se_repite) {
                chbAlarmaRepetir.setChecked(true);
            }
            if (dias != null && dias.length != 0) {
                if (dias[0].equals("todos")) {
                    chbAlarmaDom.setChecked(true);
                    chbAlarmaLun.setChecked(true);
                    chbAlarmaMar.setChecked(true);
                    chbAlarmaMie.setChecked(true);
                    chbAlarmaJue.setChecked(true);
                    chbAlarmaVie.setChecked(true);
                    chbAlarmaSab.setChecked(true);
                } else {
                    for (String dia : dias) {
                        if (!dia.equals("")) {
                            if (dia.equals("Domingo")) {
                                chbAlarmaDom.setChecked(true);
                            }
                            if (dia.equals("Lunes")) {
                                chbAlarmaLun.setChecked(true);
                            }
                            if (dia.equals("Martes")) {
                                chbAlarmaMar.setChecked(true);
                            }
                            if (dia.equals("Miércoles")) {
                                chbAlarmaMie.setChecked(true);
                            }
                            if (dia.equals("Jueves")) {
                                chbAlarmaJue.setChecked(true);
                            }
                            if (dia.equals("Viernes")) {
                                chbAlarmaVie.setChecked(true);
                            }
                            if (dia.equals("Sábado")) {
                                chbAlarmaSab.setChecked(true);
                            }
                        }
                    }
                }
            }
            //Mostrar botón para eliminar
            btnEliminarAlarma.setVisibility(View.VISIBLE);
            //Ocultar botón para guardar
            btnGuardarAlarma.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        //Obtener datos
        int hora = Integer.parseInt(tvAlarmaHora.getText().toString().substring(0, 2));
        int minutos = Integer.parseInt(tvAlarmaHora.getText().toString().substring(3, 5));
        String nombre = etAlarmaNombre.getText().toString();
        boolean seRepite = chbAlarmaRepetir.isChecked();
        String DOM = "Domingo";
        String LUN = "Lunes";
        String MAR = "Martes";
        String MIE = "Miércoles";
        String JUE = "Jueves";
        String VIE = "Viernes";
        String SAB = "Sábado";
        String[] dias = new String[7];
        if (chbAlarmaDom.isChecked() && chbAlarmaLun.isChecked() && chbAlarmaMar.isChecked() && chbAlarmaMie.isChecked() &&
            chbAlarmaJue.isChecked() && chbAlarmaVie.isChecked() && chbAlarmaSab.isChecked()) {
            dias[0] = "todos";
        } else {
            if (chbAlarmaDom.isChecked()) {
                dias[0] = DOM;
            } else {
                dias[0] = "";
            }
            if (chbAlarmaLun.isChecked()) {
                dias[1] = LUN;
            } else {
                dias[1] = "";
            }
            if (chbAlarmaMar.isChecked()) {
                dias[2] = MAR;
            } else {
                dias[2] = "";
            }
            if (chbAlarmaMie.isChecked()) {
                dias[3] = MIE;
            } else {
                dias[3] = "";
            }
            if (chbAlarmaJue.isChecked()) {
                dias[4] = JUE;
            } else {
                dias[4] = "";
            }
            if (chbAlarmaVie.isChecked()) {
                dias[5] = VIE;
            } else {
                dias[5] = "";
            }
            if (chbAlarmaSab.isChecked()) {
                dias[6] = SAB;
            } else {
                dias[6] = "";
            }
        }
        //Realizar acciones dependiendo del botón presionado
        switch(v.getId()){
            case R.id.btnGuardarAlarma:
                //Guardar en la base de datos
                baseDatos.insertAlarma(hora, minutos, 0, 0, "", nombre, "", null, seRepite, true, dias);
                baseDatos.close();
                //Mostrar mensaje de confirmación
                Toast.makeText(this, "Alarma guardada", Toast.LENGTH_SHORT).show();
                //Salir de la actividad
                finish();
                break;
            case R.id.btnEliminarAlarma:
                //Eliminar de la base de datos
                baseDatos.deleteAlarma(hora, minutos);
                baseDatos.close();
                //Mostrar mensaje de confirmación
                Toast.makeText(this, "Alarma eliminada", Toast.LENGTH_SHORT).show();
                //Salir de la actividad
                finish();
                break;
            case R.id.tvAlarmaHora:
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
                        tvAlarmaHora.setText(horaMinutos);
                    }
                }, horaAct, minutosAct, true);
                seleccionarHora.show();
                break;
        }
    }
}
