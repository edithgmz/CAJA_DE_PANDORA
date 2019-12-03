package edith.gomez.cajadepandora.ui.salud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

import edith.gomez.cajadepandora.R;
import edith.gomez.cajadepandora.data.BaseDatos;

public class CrearCategoriaMed extends AppCompatActivity implements Button.OnClickListener {
    private Button btnGuardarCategMed, btnEliminarCategMed;
    private EditText etCategMedNombre;
    private BaseDatos baseDatos;
    private Bundle bDatos;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_categoria_med);
        //Vincular componentes
        Toolbar toolbar = findViewById(R.id.toolbarCategMed);
        btnEliminarCategMed = findViewById(R.id.btnEliminarCategMed);
        btnGuardarCategMed = findViewById(R.id.btnGuardarCategMed);
        etCategMedNombre = findViewById(R.id.etCategMedNombre);
        //Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //Base de datos
        baseDatos = new BaseDatos(this);
        //Escuchadores de eventos
        btnGuardarCategMed.setOnClickListener(this);
        btnEliminarCategMed.setOnClickListener(this);
        //Obtener datos de la actividad padre
        Intent inDatos;
        if ((inDatos = getIntent()) != null) {
            bDatos = inDatos.getExtras();
        }
        //Ocultar botón para eliminar
        btnEliminarCategMed.setVisibility(View.GONE);
    }

    @Override protected void onResume() {
        super.onResume();
        //Comprobar que hay valores
        if (bDatos != null) {
            String nombre = bDatos.getString("NOMBRE");
            //Asignar valores obtenidos
            etCategMedNombre.setText(nombre);
            //Mostrar botón para eliminar
            btnEliminarCategMed.setVisibility(View.VISIBLE);
            //Ocultar botón para guardar
            btnGuardarCategMed.setVisibility(View.GONE);
        }
    }

    @Override public void onClick(View v) {
        //Obtener datos
        String nombre = etCategMedNombre.getText().toString();
        switch (v.getId()) {
            case R.id.btnGuardarCategMed:
                if (!nombre.equals("")) {
                    //Guardar en la base de datos
                    baseDatos.insertCategMedicamento(nombre);
                    baseDatos.close();
                    //Mostrar mensaje de confirmación
                    Toast.makeText(this, "Categoría registrada.", Toast.LENGTH_SHORT).show();
                    //Salir de la actividad
                    finish();
                } else {
                    //Mostrar advertencia
                    Toast.makeText(this, "Por favor indique nombre.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnEliminarCategMed:
                //Eliminar de la base de datos
                baseDatos.deleteCategMedicamento(nombre);
                baseDatos.close();
                //Mostrar mensaje de confirmación
                Toast.makeText(this, "Categoría eliminada.", Toast.LENGTH_SHORT).show();
                //Salir de la actividad
                finish();
                break;
        }
    }
}
