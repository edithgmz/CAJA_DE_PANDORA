package edith.gomez.cajadepandora.ui.notas;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

import edith.gomez.cajadepandora.R;
import edith.gomez.cajadepandora.data.BaseDatos;

import static edith.gomez.cajadepandora.data.BaseDatos.byteArrayToImage;
import static edith.gomez.cajadepandora.data.BaseDatos.imageToByteArrayJPEG;
import static edith.gomez.cajadepandora.data.BaseDatos.imageToByteArrayPNG;

public class CrearNota extends AppCompatActivity implements View.OnClickListener {
    private final static int PERMISO_READ_EXTERNAL_STORAGE = 1100;
    private final static int PERMISO_RECORD_AUDIO = 1300;
    private final static int OBTENER_ESTADO_EMOCIONAL = 5000;
    private Intent inEdoEmo, inVerImg;
    private ImageButton ibNotaImagen, ibNotaAudio, ibNotaEdoEmo;
    private EditText etNotaTitulo, etNotaContenido;
    private TextView tvNotaActividades, tvNotaAlarmas, tvNotaSalud;
    private ImageView ivNotaImagen, ivNotaEdoEmo;
    private BaseDatos baseDatos;
    private String fecha;
    private Bundle bDatos;

    @SuppressLint("DefaultLocale") @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_nota);
        //Vincular componentes
        Toolbar toolbar = findViewById(R.id.toolbarNota);
        ibNotaImagen = findViewById(R.id.ibNotaImagen);
        ibNotaAudio = findViewById(R.id.ibNotaAudio);
        ibNotaEdoEmo = findViewById(R.id.ibNotaEdoEmo);
        etNotaTitulo = findViewById(R.id.etNotaTitulo);
        etNotaContenido = findViewById(R.id.etNotaContenido);
        tvNotaActividades = findViewById(R.id.tvNotaActividades);
        tvNotaAlarmas = findViewById(R.id.tvNotaAlarmas);
        tvNotaSalud = findViewById(R.id.tvNotaSalud);
        ivNotaImagen = findViewById(R.id.ivNotaImagen);
        ivNotaEdoEmo = findViewById(R.id.ivNotaEdoEmo);
        //Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //Intentos a otras actividades
        inEdoEmo = new Intent(this, ElegirEstadoEmocional.class);
        inVerImg = new Intent(this, VerImagen.class);
        //Base de datos
        baseDatos = new BaseDatos(this);
        //Escuchadores de evento click
        ibNotaImagen.setOnClickListener(this);
        ibNotaAudio.setOnClickListener(this);
        ibNotaEdoEmo.setOnClickListener(this);
        ivNotaImagen.setOnClickListener(this);
        //Obtener datos de la actividad padre
        Intent inDatos;
        if ((inDatos = getIntent()) != null) {
            bDatos = inDatos.getExtras();
        }
    }

    @SuppressLint("DefaultLocale") @Override protected void onResume() {
        super.onResume();
        //Obtener información sobre actividades, alarmas y medicamentos registrados
        String actividades = baseDatos.cantidadActividades() + "";
        String alarmas = baseDatos.cantidadAlarmas() + "";
        String salud = baseDatos.cantidadSalud() + "";
        tvNotaActividades.setText(actividades);
        tvNotaAlarmas.setText(alarmas);
        tvNotaSalud.setText(salud);
        //Comprobar que hay valores
        if (bDatos != null) {
            String titulo = bDatos.getString("TITULO");
            String contenido = bDatos.getString("CONTENIDO");
            byte[] imagen = bDatos.getByteArray("IMAGEN");
            byte[] estado_emocional = bDatos.getByteArray("ESTADO_EMOCIONAL");
            fecha = bDatos.getString("FECHA");
            //Asignar valores obtenidos
            etNotaTitulo.setText(titulo);
            etNotaContenido.setText(contenido);
            if (imagen != null) {
                ivNotaImagen.setImageBitmap(byteArrayToImage(imagen));
                //Hacer visible imagen
                ivNotaImagen.setVisibility(View.VISIBLE);
            }
            if (estado_emocional != null) {
                ivNotaEdoEmo.setImageBitmap(byteArrayToImage(estado_emocional));
                //Hacer visible imagen
                ivNotaEdoEmo.setVisibility(View.VISIBLE);
                //Deshabilitar componentes
                ibNotaImagen.setEnabled(false);
                ibNotaAudio.setEnabled(false);
                ibNotaEdoEmo.setEnabled(false);
                etNotaTitulo.setEnabled(false);
                etNotaContenido.setEnabled(false);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Acciones a realizar dependiendo del permiso garantizado
        switch(requestCode){
            case PERMISO_READ_EXTERNAL_STORAGE:
                if((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    //Mostrar mensaje de confirmación
                    Toast.makeText(this, "Permiso garantizado.", Toast.LENGTH_SHORT).show();
                    //Acceder a la galería
                    Intent inGaleria = new Intent(Intent.ACTION_PICK);
                    inGaleria.setType("image/*");
                    startActivityForResult(inGaleria, PERMISO_READ_EXTERNAL_STORAGE);
                } else {
                    Toast.makeText(this, "No tiene permiso para acceder a la galería.", Toast.LENGTH_SHORT).show();
                }
                break;
            case PERMISO_RECORD_AUDIO:
                if((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    Toast.makeText(this, "Permiso garantizado.", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Función aún no disponible.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Acciones a realizar dependiendo de la respuesta obtenida
        switch (requestCode) {
            case PERMISO_READ_EXTERNAL_STORAGE:
                if ((resultCode == RESULT_OK) && (data != null)) {
                    //Obtener ruta de la imagen
                    Uri uri = data.getData();
                    try {
                        //Resolver la ruta de la imagen para obtenerla
                        InputStream inputStream = null;
                        if (uri != null) {
                            inputStream = getContentResolver().openInputStream(uri);
                        }
                        //Crear bitmap a partir de la imagen obtenida
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        //Mostrar imagen seleccionada
                        ivNotaImagen.setImageBitmap(bitmap);
                        ivNotaImagen.setVisibility(View.VISIBLE);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case OBTENER_ESTADO_EMOCIONAL:
                if ((resultCode == RESULT_OK) && (data != null)) {
                    //Obtener imagen
                    int estadoEmocional = data.getIntExtra("ESTADO_EMOCIONAL", 0);
                    //Mostrar imagen obtenida
                    ivNotaEdoEmo.setImageResource(estadoEmocional);
                    ivNotaEdoEmo.setVisibility(View.VISIBLE);
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
        //Obtener datos
        String titulo = etNotaTitulo.getText().toString();
        String contenido = etNotaContenido.getText().toString();
        byte[] imagen = null;
        byte[] audio = null;
        byte[] estado_emocional = null;
        //Si se ha seleccionado imagen se transforma para guardarla en la base de datos
        if (ivNotaImagen.getDrawable() != null) {
            imagen = imageToByteArrayJPEG(ivNotaImagen);
        }
        //Si se ha seleccionado imagen se transforma para guardarla en la base de datos
        if (ivNotaEdoEmo.getDrawable() != null) {
            estado_emocional = imageToByteArrayPNG(ivNotaEdoEmo);
        }
        //Obtener fecha de creación
        String fecha1 = DateFormat.getDateInstance().format(new Date());
        //Muestra los ítems del menú desplegable
        int id = item.getItemId();
        switch (id) {
            case R.id.action_guardar_nota:
                if (!titulo.equals("")) {
                    //Guardar en la base de datos
                    baseDatos.insertNota(titulo, contenido, fecha1, imagen, audio, estado_emocional);
                    baseDatos.datosNota();
                    baseDatos.close();
                    //Mensaje de confirmación
                    Toast.makeText(this, "Nota guardada.", Toast.LENGTH_SHORT).show();
                    //Salir de la actividad
                    finish();
                } else {
                    //Mostrar advertencia
                    Toast.makeText(this, "Por favor indique un título.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.action_eliminar_nota:
                //Eliminar de la base de datos
                baseDatos.deleteNota(titulo, contenido, fecha);
                baseDatos.close();
                //Mensaje de confirmación
                Toast.makeText(this, "Nota eliminada.", Toast.LENGTH_SHORT).show();
                //Salir de la actividad
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        //Acciones a realizar dependiendo del ID del botón presionado
        switch(v.getId()){
            case R.id.ibNotaImagen:
                //VERIFICACIÓN DE PERMISOS
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //Permiso para leer almacenamiento externo
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISO_READ_EXTERNAL_STORAGE);
                } else{
                    //Acceder a la galería
                    Intent inGaleria = new Intent(Intent.ACTION_PICK);
                    inGaleria.setType("image/*");
                    startActivityForResult(inGaleria, PERMISO_READ_EXTERNAL_STORAGE);
                }
                break;
            case R.id.ibNotaAudio:
                //VERIFICACIÓN DE PERMISOS
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
                    //Permiso para grabar audio
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISO_RECORD_AUDIO);
                } else{
                    Toast.makeText(this, "Función aún no disponible.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ibNotaEdoEmo:
                //Inicia actividad para seleccionar estado emocional
                startActivityForResult(inEdoEmo, OBTENER_ESTADO_EMOCIONAL);
                break;
            case R.id.ivNotaImagen:
                if (ivNotaImagen.getDrawable() != null) {
                    //Enviar la imagen a la actividad Ver Imagen
                    Bundle bVerImagen = new Bundle();
                    byte[] imagen = imageToByteArrayJPEG(ivNotaImagen);
                    bVerImagen.putByteArray("IMAGEN", imagen);
                    inVerImg.putExtras(bVerImagen);
                    startActivity(inVerImg);
                }
                break;
            default:
                throw new IllegalStateException("Valor inesperado: " + v.getId());
        }
    }


}
