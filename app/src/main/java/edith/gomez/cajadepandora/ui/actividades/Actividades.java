package edith.gomez.cajadepandora.ui.actividades;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import java.util.Objects;

import edith.gomez.cajadepandora.R;

public class Actividades extends AppCompatActivity {
    private Intent inCrearActividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);
        //Vincular componentes
        Toolbar toolbar = findViewById(R.id.toolbarActividades);
        FloatingActionButton fab = findViewById(R.id.fabActividad);
        //Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //Intento a crear actividad
        inCrearActividad = new Intent(this, CrearActividad.class);
        //Botón flotante
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(inCrearActividad);
            }
        });
    }

}
