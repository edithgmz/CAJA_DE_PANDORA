package edith.gomez.cajadepandora.ui.actividades;

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
import edith.gomez.cajadepandora.data.actividades.Actividad;
import edith.gomez.cajadepandora.data.actividades.ActividadAdapter;

public class Actividades extends AppCompatActivity implements ListView.OnItemClickListener {
    private Intent inCrearActividad1, inCrearActividad2;
    private ListView lvActividades;
    private ArrayList<Actividad> lstActividades = new ArrayList<>();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);
        //Vincular componentes
        Toolbar toolbar = findViewById(R.id.toolbarActividades);
        FloatingActionButton fab = findViewById(R.id.fabActividad);
        lvActividades = findViewById(R.id.lvActividades);
        //Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //Intento a crear actividad
        inCrearActividad1 = new Intent(this, CrearActividad.class);
        inCrearActividad2 = new Intent(this, CrearActividad.class);
        //Botón flotante
        fab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                startActivity(inCrearActividad1);
            }
        });
        //Lista de actividades
        lvActividades.setOnItemClickListener(this);
    }

    @Override protected void onResume() {
        super.onResume();
        //Base de datos
        BaseDatos baseDatos = new BaseDatos(this);
        lstActividades = baseDatos.datosActividad();
        lvActividades.setAdapter(new ActividadAdapter(this, R.layout.layout_actividad, lstActividades));
        baseDatos.close();
    }

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Envía el contenido de la actividad seleccionada a la actividad Crear Actividad
        Bundle bDatosActividad = new Bundle();
        bDatosActividad.putString("NOMBRE", lstActividades.get(position).getNombre());
        bDatosActividad.putString("DESCRIPCION", lstActividades.get(position).getDescripcion());
        bDatosActividad.putString("FECHA", lstActividades.get(position).getFecha());
        bDatosActividad.putString("CATEGORIA", lstActividades.get(position).getCategoria());
        bDatosActividad.putInt("HORA", lstActividades.get(position).getHora());
        bDatosActividad.putInt("MINUTOS", lstActividades.get(position).getMinutos());
        inCrearActividad2.putExtras(bDatosActividad);
        startActivity(inCrearActividad2);
    }
}
