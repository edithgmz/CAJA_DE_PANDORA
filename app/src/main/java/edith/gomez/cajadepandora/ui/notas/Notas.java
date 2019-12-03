package edith.gomez.cajadepandora.ui.notas;

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
import edith.gomez.cajadepandora.data.notas.Nota;
import edith.gomez.cajadepandora.data.notas.NotaAdapter;

public class Notas extends AppCompatActivity implements ListView.OnItemClickListener {
    private Intent inCrearNota1, inCrearNota2;
    private ListView lvNotas;
    private ArrayList<Nota> lstNotas = new ArrayList<>();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        //Vincular componentes
        Toolbar toolbar = findViewById(R.id.toolbarNotas);
        FloatingActionButton fab = findViewById(R.id.fabNota);
        lvNotas = findViewById(R.id.lvNotas);
        //Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //Intento a crear nota
        inCrearNota1 = new Intent(this, CrearNota.class);
        inCrearNota2 = new Intent(this, CrearNota.class);
        //Botón flotante
        fab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(inCrearNota1);
            }
        });
        //Lista de notas
        lvNotas.setOnItemClickListener(this);
    }

    @Override protected void onResume() {
        super.onResume();
        //Base de datos
        BaseDatos baseDatos = new BaseDatos(this);
        lstNotas = baseDatos.datosNota();
        lvNotas.setAdapter(new NotaAdapter(this, R.layout.layout_nota, lstNotas));
        baseDatos.close();
    }

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Envía el contenido de la nota seleccionada a la actividad Crear Nota
        Bundle bDatosNota = new Bundle();
        bDatosNota.putString("TITULO", lstNotas.get(position).getTitulo());
        bDatosNota.putString("CONTENIDO", lstNotas.get(position).getContenido());
        bDatosNota.putByteArray("IMAGEN", lstNotas.get(position).getImagen());
        bDatosNota.putByteArray("AUDIO", lstNotas.get(position).getAudio());
        bDatosNota.putByteArray("ESTADO_EMOCIONAL", lstNotas.get(position).getEstadoEmocional());
        bDatosNota.putString("FECHA", lstNotas.get(position).getFecha());
        inCrearNota2.putExtras(bDatosNota);
        startActivity(inCrearNota2);
    }
}
