package edith.gomez.cajadepandora.ui.notas;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

import edith.gomez.cajadepandora.R;
import edith.gomez.cajadepandora.data.BaseDatos;
import edith.gomez.cajadepandora.data.notas.Nota;

public class Notas extends AppCompatActivity {
    private Intent inCrearNota;
    private ListView lstVwNotas;
    private ArrayList<Nota> alNotas;
    private BaseDatos bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        //Vincular componentes
        Toolbar toolbar = findViewById(R.id.toolbarNotas);
        FloatingActionButton fab = findViewById(R.id.fabNota);
        //Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //Intento a crear nota
        inCrearNota = new Intent(this, CrearNota.class);
        //Botón flotante
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(inCrearNota);
            }
        });
    }

}
