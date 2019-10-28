package edith.gomez.cajadepandora.ui.alarmas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

import edith.gomez.cajadepandora.R;

public class CrearAlarma extends AppCompatActivity implements Button.OnClickListener {
    Button btnGuardarAlarma, btnEliminarAlarma, btnCancelarAlarma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_alarma);
        //Vincular componentes
        Toolbar toolbar = findViewById(R.id.toolbarAlarma);
        btnGuardarAlarma = findViewById(R.id.btnGuardarAlarma);
        btnEliminarAlarma = findViewById(R.id.btnEliminarAlarma);
        btnCancelarAlarma = findViewById(R.id.btnCancelarAlarma);
        //Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //Escuchador para el evento click
        btnGuardarAlarma.setOnClickListener(this);
        btnEliminarAlarma.setOnClickListener(this);
        btnCancelarAlarma.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnGuardarAlarma:
                Toast.makeText(this, "Guardar Alarma", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnEliminarAlarma:
                Toast.makeText(this, "Eliminar Alarma", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnCancelarAlarma:
                Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
