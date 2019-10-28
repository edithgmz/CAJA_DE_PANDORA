package edith.gomez.cajadepandora.ui.alarmas;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import java.util.Objects;

import edith.gomez.cajadepandora.R;

public class Alarmas extends AppCompatActivity {
    private Intent inCrearAlarma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmas);
        FloatingActionButton fab = findViewById(R.id.fabAlarma);
        //Vincular componentes
        Toolbar toolbar = findViewById(R.id.toolbarAlarmas);
        //Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        // Intento a crear alarma
        inCrearAlarma = new Intent(this, CrearAlarma.class);
        //Botón flotante
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(inCrearAlarma);
            }
        });
    }

}
