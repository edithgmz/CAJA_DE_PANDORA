package edith.gomez.cajadepandora.ui.notas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

import edith.gomez.cajadepandora.R;

import static edith.gomez.cajadepandora.data.BaseDatos.byteArrayToImage;

public class VerImagen extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_imagen);
        //Vincular componentes
        Toolbar toolbar = findViewById(R.id.toolbarVerImg);
        ImageView notaVerImg = findViewById(R.id.notaVerImg);
        //Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        //Obtener datos de la actividad padre
        Intent inImg = getIntent();
        Bundle bImg = inImg.getExtras();
        if (bImg != null) {
            byte[] imagen = bImg.getByteArray("IMAGEN");
            if (imagen != null) {
                notaVerImg.setImageBitmap(byteArrayToImage(imagen));
            }
        }
    }
}
