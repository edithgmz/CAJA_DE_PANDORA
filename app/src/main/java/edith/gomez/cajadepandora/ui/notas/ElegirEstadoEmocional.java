package edith.gomez.cajadepandora.ui.notas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

import edith.gomez.cajadepandora.R;
import edith.gomez.cajadepandora.data.notas.EstadoEmocional;
import edith.gomez.cajadepandora.data.notas.EstadoEmocionalAdapter;

public class ElegirEstadoEmocional extends AppCompatActivity implements ListView.OnItemClickListener {
    private EstadoEmocional[] aEstadosEmocionales = {
            new EstadoEmocional(R.drawable.ee01_angry, 11, "Enojado 1"),
            new EstadoEmocional(R.drawable.ee02_angry, 12, "Enojado 2"),
            new EstadoEmocional(R.drawable.ee03_angry, 13, "Enojado 3"),
            new EstadoEmocional(R.drawable.ee04_angry, 14, "Enojado 4"),
            new EstadoEmocional(R.drawable.ee05_calm, 15, "Calmado"),
            new EstadoEmocional(R.drawable.ee06_crying, 16, "Llorando"),
            new EstadoEmocional(R.drawable.ee07_dead, 17, "Muerto"),
            new EstadoEmocional(R.drawable.ee08_disappointed, 18, "Decepcionado"),
            new EstadoEmocional(R.drawable.ee09_disgusted, 19, "Disgustado"),
            new EstadoEmocional(R.drawable.ee10_embarrassed, 20, "Avergonzado"),
            new EstadoEmocional(R.drawable.ee11_fedup, 21, "Harto"),
            new EstadoEmocional(R.drawable.ee12_happy, 22, "Feliz 1"),
            new EstadoEmocional(R.drawable.ee13_happy, 23, "Feliz 2"),
            new EstadoEmocional(R.drawable.ee14_injured, 24, "Lastimado"),
            new EstadoEmocional(R.drawable.ee15_inlove, 25, "Enamorado"),
            new EstadoEmocional(R.drawable.ee16_laughing, 26, "Riendo"),
            new EstadoEmocional(R.drawable.ee17_mischievous, 27, "Travieso"),
            new EstadoEmocional(R.drawable.ee18_sad, 28, "Triste"),
            new EstadoEmocional(R.drawable.ee19_shocked, 29, "Sorprendido 1"),
            new EstadoEmocional(R.drawable.ee20_shocked, 30, "Sorprendido 2"),
            new EstadoEmocional(R.drawable.ee21_sick, 31, "Enfermo 1"),
            new EstadoEmocional(R.drawable.ee22_sick, 32, "Enfermo 2"),
            new EstadoEmocional(R.drawable.ee23_sleeping, 33, "Cansado"),
            new EstadoEmocional(R.drawable.ee24_surprised, 34, "Sorprendido"),
            new EstadoEmocional(R.drawable.ee25_thinking, 35, "Pensativo"),
            new EstadoEmocional(R.drawable.ee26_worried, 36, "Preocupado")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_emocional);
        //Vincular de componentes
        Toolbar toolbar = findViewById(R.id.toolbarEstadoEmocional);
        //Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //Se vincula la lista, agrega el adaptador y el listener
        ListView lvEdoEmo = findViewById(R.id.lvEdoEmo);
        lvEdoEmo.setAdapter(new EstadoEmocionalAdapter(this, R.layout.layout_estado_emocional, aEstadosEmocionales));
        lvEdoEmo.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Enviar imagen a actividad Crear Nota
        Intent inCrearNota = new Intent();
        inCrearNota.putExtra("ESTADO_EMOCIONAL", aEstadosEmocionales[position].getEstadoEmocional());
        setResult(Activity.RESULT_OK, inCrearNota);
        finish();
    }
}
