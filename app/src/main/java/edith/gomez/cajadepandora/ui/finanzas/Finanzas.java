package edith.gomez.cajadepandora.ui.finanzas;

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

public class Finanzas extends AppCompatActivity implements FloatingActionButton.OnClickListener {
    private Intent inCrearGasto, inCrearIngreso;
    private FloatingActionButton fabFinanzas, fabGasto, fabIngreso;
    private TextView txtGasto, txtIngreso;
    private Animation fab_abrir, fab_cerrar, fab_rotar_reloj, fab_rotar_contra_reloj;
    private boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finanzas);
        //Vincular componentes
        BottomNavigationView navView = findViewById(R.id.navFinanzas);
        fabFinanzas = findViewById(R.id.fabFinanzas);
        fabGasto = findViewById(R.id.fabGasto);
        fabIngreso = findViewById(R.id.fabIngreso);
        txtGasto = findViewById(R.id.txtGasto);
        txtIngreso = findViewById(R.id.txtIngreso);
        fab_abrir = AnimationUtils.loadAnimation(this, R.anim.fab_abrir);
        fab_cerrar = AnimationUtils.loadAnimation(this, R.anim.fab_cerrar);
        fab_rotar_reloj = AnimationUtils.loadAnimation(this, R.anim.fab_rotar_reloj);
        fab_rotar_contra_reloj = AnimationUtils.loadAnimation(this, R.anim.fab_rotar_contra_reloj);
        //Navigation
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //Botón flotante principal
        fabFinanzas.setOnClickListener(new View.OnClickListener() {
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
        fabGasto.setOnClickListener(this);
        fabIngreso.setOnClickListener(this);
        //Intentos a otras actividades
        inCrearGasto = new Intent(this, CrearGasto.class);
        inCrearIngreso = new Intent(this, CrearIngreso.class);
        //Cargar fragmento principal
        cargarFragment(new IngresosFrag());
    }

    private void abrirFABMenu(){
        isOpen = true;
        //Mostrar botones
        txtGasto.setVisibility(View.VISIBLE);
        txtIngreso.setVisibility(View.VISIBLE);
        //Animaciones
        fabGasto.startAnimation(fab_abrir);
        fabIngreso.startAnimation(fab_abrir);
        fabFinanzas.startAnimation(fab_rotar_reloj);
        //Establecer propiedad
        fabGasto.setClickable(true);
        fabIngreso.setClickable(true);
    }

    private void cerrarFABMenu(){
        isOpen =false;
        //Ocultar botones
        txtGasto.setVisibility(View.INVISIBLE);
        txtIngreso.setVisibility(View.INVISIBLE);
        //Animaciones
        fabGasto.startAnimation(fab_cerrar);
        fabIngreso.startAnimation(fab_cerrar);
        fabFinanzas.startAnimation(fab_rotar_contra_reloj);
        //Establecer propiedad
        fabGasto.setClickable(false);
        fabIngreso.setClickable(false);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_ingreso:
                    cargarFragment(new IngresosFrag());
                    return true;
                case R.id.nav_gasto:
                    cargarFragment(new GastosFrag());
                    return true;
                case R.id.nav_stats:
                    cargarFragment(new EstadisticasFrag());
                    return true;
            }
            return false;
        }
    };

    private boolean cargarFragment(Fragment fragment){
        //Se cambian los fragmentos
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.flFinanzas, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fabGasto:
                startActivity(inCrearGasto);
                break;
            case R.id.fabIngreso:
                startActivity(inCrearIngreso);
                break;
            default:
                throw new IllegalStateException("Valor inesperado: " + v.getId());
        }
    }
}
