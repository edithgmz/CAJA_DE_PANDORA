package edith.gomez.cajadepandora.ui.notas;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Objects;

import edith.gomez.cajadepandora.R;

public class CrearNota extends AppCompatActivity implements ImageButton.OnClickListener {
    private final int PERMISO_READ_EXTERNAL_STORAGE = 1100;
    private final int PERMISO_WRITE_EXTERNAL_STORAGE = 1200;
    private final int PERMISO_RECORD_AUDIO = 1300;
    private Intent inEdoEmo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_nota);
        //Vincular componentes
        Toolbar toolbar = findViewById(R.id.toolbarNota);
        ImageButton ibNotaImagen = findViewById(R.id.ibNotaImagen);
        ImageButton ibNotaAudio = findViewById(R.id.ibNotaAudio);
        ImageButton ibNotaEdoEmo = findViewById(R.id.ibNotaEdoEmo);
        //Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //Intento a estado emocional
        inEdoEmo = new Intent(this, ElegirEstadoEmocional.class);
        //Escuchador de evento click
        ibNotaImagen.setOnClickListener(this);
        ibNotaAudio.setOnClickListener(this);
        ibNotaEdoEmo.setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Acciones a realizar dependiendo del permiso garantizado
        switch(requestCode){
            case PERMISO_READ_EXTERNAL_STORAGE:
                if((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    Toast.makeText(this, "Permiso garantizado.", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Acceder a la galería.", Toast.LENGTH_SHORT).show();
                }
                break;
            case PERMISO_WRITE_EXTERNAL_STORAGE:
                if((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    Toast.makeText(this, "Permiso garantizado.", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Acceder a la galería.", Toast.LENGTH_SHORT).show();
                }
                break;
            case PERMISO_RECORD_AUDIO:
                if((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    Toast.makeText(this, "Permiso garantizado.", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Mostrar herramienta para grabar audio.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Genera el menú desplegable
        getMenuInflater().inflate(R.menu.nota, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Muestra los ítems del menú desplegable
        int id = item.getItemId();
        switch (id) {
            case R.id.action_guardar_nota:
                Toast.makeText(this, "Guardar nota", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_eliminar_nota:
                Toast.makeText(this, "Eliminar nota", Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new IllegalStateException("Valor inesperado: " + id);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        //Acciones a realizar dependiendo del ID del botón presionado
        switch(v.getId()){
            case R.id.ibNotaImagen:
                //VERIFICACIÓN DE PERMISOS
                if((ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) &&
                        (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)){
                    //Permiso para leer almacenamiento externo
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISO_READ_EXTERNAL_STORAGE);
                    //Permiso para escribir en almacenamiento externo
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISO_WRITE_EXTERNAL_STORAGE);
                } else{
                    Toast.makeText(this, "Acceder a la galería.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ibNotaAudio:
                //VERIFICACIÓN DE PERMISOS
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
                    //Permiso para grabar audio
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISO_RECORD_AUDIO);
                } else{
                    Toast.makeText(this, "Mostrar herramienta para grabar audio.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ibNotaEdoEmo:
                //Inicia actividad para seleccionar estado emocional
                startActivity(inEdoEmo);
                break;
            default:
                throw new IllegalStateException("Valor inesperado: " + v.getId());
        }
    }
}
