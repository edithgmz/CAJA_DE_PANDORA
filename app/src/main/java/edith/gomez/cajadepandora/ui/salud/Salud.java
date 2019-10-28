package edith.gomez.cajadepandora.ui.salud;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edith.gomez.cajadepandora.R;

public class Salud extends AppCompatActivity implements FloatingActionButton.OnClickListener {
    private Intent inCrearMedicamento, inCrearCategoriaMed;
    private FloatingActionButton fabSalud, fabMedicamento, fabCategoriaMed;
    private TextView txtMedicamento, txtCategoriaMed;
    private Animation fab_abrir, fab_cerrar, fab_rotar_reloj, fab_rotar_contra_reloj;
    private boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salud);
        //Vincular componentes
        BottomNavigationView navView = findViewById(R.id.navSalud);
        fabSalud = findViewById(R.id.fabSalud);
        fabMedicamento = findViewById(R.id.fabMedicamento);
        fabCategoriaMed = findViewById(R.id.fabCategoriaMed);
        txtMedicamento = findViewById(R.id.txtMedicamento);
        txtCategoriaMed = findViewById(R.id.txtCategoriaMed);
        fab_abrir = AnimationUtils.loadAnimation(this, R.anim.fab_abrir);
        fab_cerrar = AnimationUtils.loadAnimation(this, R.anim.fab_cerrar);
        fab_rotar_reloj = AnimationUtils.loadAnimation(this, R.anim.fab_rotar_reloj);
        fab_rotar_contra_reloj = AnimationUtils.loadAnimation(this, R.anim.fab_rotar_contra_reloj);
        //Navigation
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //Botón flotante principal
        fabSalud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOpen){
                    abrirFABMenu();
                } else{
                    cerrarFABMenu();
                }
            }
        });
        //Botones flotantes Ingreso y Gasto
        fabMedicamento.setOnClickListener(this);
        fabCategoriaMed.setOnClickListener(this);
        //Intentos a otras actividades
        inCrearMedicamento = new Intent(this, CrearMedicamento.class);
        inCrearCategoriaMed = new Intent(this, CrearCategoriaMed.class);
        //Cargar fragmento principal
        cargarFragment(new MedicamentosFrag());
    }

    private void abrirFABMenu(){
        isOpen = true;
        //Mostrar botones
        txtMedicamento.setVisibility(View.VISIBLE);
        txtCategoriaMed.setVisibility(View.VISIBLE);
        //Animaciones
        fabMedicamento.startAnimation(fab_abrir);
        fabCategoriaMed.startAnimation(fab_abrir);
        fabSalud.startAnimation(fab_rotar_reloj);
        //Establecer propiedad
        fabMedicamento.setClickable(true);
        fabCategoriaMed.setClickable(true);
    }

    private void cerrarFABMenu(){
        isOpen =false;
        //Ocultar botones
        txtMedicamento.setVisibility(View.INVISIBLE);
        txtCategoriaMed.setVisibility(View.INVISIBLE);
        //Animaciones
        fabMedicamento.startAnimation(fab_cerrar);
        fabCategoriaMed.startAnimation(fab_cerrar);
        fabSalud.startAnimation(fab_rotar_contra_reloj);
        //Establecer propiedad
        fabMedicamento.setClickable(false);
        fabCategoriaMed.setClickable(false);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_medic:
                    cargarFragment(new MedicamentosFrag());
                    return true;
                case R.id.nav_categ:
                    cargarFragment(new CategoriasMedFrag());
                    return true;
            }
            return false;
        }
    };

    private boolean cargarFragment(Fragment fragment){
        //Se cambian los fragmentos
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.flSalud, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fabMedicamento:
                startActivity(inCrearMedicamento);
                break;
            case R.id.fabCategoriaMed:
                startActivity(inCrearCategoriaMed);
                break;
            default:
                throw new IllegalStateException("Valor inesperado: " + v.getId());
        }
    }
}
