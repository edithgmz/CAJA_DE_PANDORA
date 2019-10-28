package edith.gomez.cajadepandora.ui.extras;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

import edith.gomez.cajadepandora.R;

public class Tdah extends AppCompatActivity implements Button.OnClickListener {
    private Intent inVistaWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tdah);
        //Vincular componentes
        Toolbar toolbar = findViewById(R.id.toolbarTdah);
        Button btnPruebaTdah = findViewById(R.id.btnPruebaTdah);
        Button btnGrupoTdah = findViewById(R.id.btnGrupoTdah);
        Button btnChadd = findViewById(R.id.btnChadd);
        Button btnPuzzles = findViewById(R.id.btnPuzzles);
        Button btnMandalas = findViewById(R.id.btnMandalas);
        //Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //Intento a Vista Web
        inVistaWeb = new Intent(this, VistaWeb.class);
        //Escuchador de evento click en los botones
        btnPruebaTdah.setOnClickListener(this);
        btnGrupoTdah.setOnClickListener(this);
        btnChadd.setOnClickListener(this);
        btnPuzzles.setOnClickListener(this);
        btnMandalas.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Dependiendo del ID del botón presionado se envía la URL a la actividad Vista Web
        switch (v.getId()){
            case R.id.btnPruebaTdah:
                inVistaWeb.putExtra(VistaWeb.SITIO_WEB, "https://psychcentral.com/quizzes/adhd-quiz/");
                startActivity(inVistaWeb);
                break;
            case R.id.btnGrupoTdah:
                inVistaWeb.putExtra(VistaWeb.SITIO_WEB, "https://es-la.facebook.com/groups/ayudatdahadultos/");
                startActivity(inVistaWeb);
                break;
            case R.id.btnChadd:
                inVistaWeb.putExtra(VistaWeb.SITIO_WEB, "https://chadd.org/understanding-adhd/recursos-en-espanol/");
                startActivity(inVistaWeb);
                break;
            case R.id.btnPuzzles:
                inVistaWeb.putExtra(VistaWeb.SITIO_WEB, "https://www.jigsawplanet.com/");
                startActivity(inVistaWeb);
                break;
            case R.id.btnMandalas:
                inVistaWeb.putExtra(VistaWeb.SITIO_WEB, "https://mandalas.dibujos.net/");
                startActivity(inVistaWeb);
                break;
            default:
                throw new IllegalStateException("Valor inesperado: " + v.getId());
        }
    }
}
