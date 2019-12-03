package edith.gomez.cajadepandora.ui.alarmas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

import edith.gomez.cajadepandora.R;
import edith.gomez.cajadepandora.data.BaseDatos;
import edith.gomez.cajadepandora.data.alarmas.Alarma;
import edith.gomez.cajadepandora.data.alarmas.AlarmaAdapter;

public class Alarmas extends AppCompatActivity implements ListView.OnItemClickListener {
    private Intent inCrearAlarma1, inCrearAlarma2;
    private ListView lvAlarmas;
    private ArrayList<Alarma> lstAlarmas = new ArrayList<>();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmas);
        //Vincular componentes
        Toolbar toolbar = findViewById(R.id.toolbarAlarmas);
        FloatingActionButton fab = findViewById(R.id.fabAlarma);
        lvAlarmas = findViewById(R.id.lvAlarmas);
        //Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        // Intento a crear alarma
        inCrearAlarma1 = new Intent(this, CrearAlarma.class);
        inCrearAlarma2 = new Intent(this, CrearAlarma.class);
        //Botón flotante
        fab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                startActivity(inCrearAlarma1);
            }
        });
        //Lista de alarmas
        lvAlarmas.setOnItemClickListener(this);
    }

    @Override protected void onResume() {
        super.onResume();
        //Base de datos
        BaseDatos baseDatos = new BaseDatos(this);
        lstAlarmas = baseDatos.datosAlarma();
        lvAlarmas.setAdapter(new AlarmaAdapter(this, R.layout.layout_alarma, lstAlarmas));
        baseDatos.close();
    }

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Envía el contenido de la alarma seleccionada a la actividad Crear Alarma
        Bundle bDatosAlarma = new Bundle();
        bDatosAlarma.putInt("HORA", lstAlarmas.get(position).getHora());
        bDatosAlarma.putInt("MINUTOS", lstAlarmas.get(position).getMinutos());
        bDatosAlarma.putString("NOMBRE", lstAlarmas.get(position).getNombre());
        bDatosAlarma.putBoolean("SE_REPITE", lstAlarmas.get(position).isRepetir());
        bDatosAlarma.putStringArray("DIAS", lstAlarmas.get(position).getDias());
        inCrearAlarma2.putExtras(bDatosAlarma);
        startActivity(inCrearAlarma2);
    }
}
