package edith.gomez.cajadepandora;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.darwindeveloper.onecalendar.clases.Day;
import com.darwindeveloper.onecalendar.views.OneCalendarView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import edith.gomez.cajadepandora.ui.actividades.Actividades;
import edith.gomez.cajadepandora.ui.alarmas.Alarmas;
import edith.gomez.cajadepandora.ui.extras.Info;
import edith.gomez.cajadepandora.ui.extras.Tdah;
import edith.gomez.cajadepandora.ui.finanzas.Finanzas;
import edith.gomez.cajadepandora.ui.notas.CrearNota;
import edith.gomez.cajadepandora.ui.notas.Notas;
import edith.gomez.cajadepandora.ui.salud.Salud;

public class Principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        ListView.OnItemClickListener, OneCalendarView.OnCalendarChangeListener, OneCalendarView.OneCalendarClickListener {
    private DrawerLayout drawer;
    private Intent inAlarmas, inNotas, inActividades, inSalud, inFinanzas, inTdah, inInfo, inCrearNota;
    private OneCalendarView calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        //Vincular componentes
        Toolbar toolbarPrincipal = findViewById(R.id.toolbarPrincipal);
        NavigationView navigation = findViewById(R.id.navigation);
        final FloatingActionButton fabNotaPpal = findViewById(R.id.fabNotaPpal);
        calendario = findViewById(R.id.calendario);
        ListView lvNotasPpal = findViewById(R.id.lvNotasPpal);
        drawer = findViewById(R.id.drawer_layout);
        //Toolbar
        setSupportActionBar(toolbarPrincipal);
        //Navigation
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                toolbarPrincipal, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigation.setNavigationItemSelectedListener(this);
        //Intentos a otras actividades
        inAlarmas = new Intent(this, Alarmas.class);
        inNotas = new Intent(this, Notas.class);
        inActividades = new Intent(this, Actividades.class);
        inSalud = new Intent(this, Salud.class);
        inFinanzas = new Intent(this, Finanzas.class);
        inTdah = new Intent(this, Tdah.class);
        inInfo = new Intent(this, Info.class);
        inCrearNota = new Intent(this, CrearNota.class);
        //Botón flotante
        fabNotaPpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(inCrearNota);
            }
        });
        //Calendario
        calendario.setOnCalendarChangeListener(this);
        calendario.setOneCalendarClickListener(this);
        //Lista de notas
        lvNotasPpal.setOnItemClickListener(this);
    }

    @Override
    public void onBackPressed() {
        //Cierra la caja de navegación
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Genera el menú desplegable
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Muestra los ítems del menú desplegable
        int id = item.getItemId();
        if (id == R.id.action_config_principal) {
            Toast.makeText(this, "Configuración de la aplicación", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Envía el contenido de la nota seleccionada a la actividad Crear Nota
        startActivity(inCrearNota);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //Inicia actividad dependiendo del id obtenido del menú de navegación
        int id = menuItem.getItemId();
        switch(id){
            case R.id.nav_alarms:
                startActivity(inAlarmas);
                break;
            case R.id.nav_notes:
                startActivity(inNotas);
                break;
            case R.id.nav_activities:
                startActivity(inActividades);
                break;
            case R.id.nav_health:
                startActivity(inSalud);
                break;
            case R.id.nav_money:
                startActivity(inFinanzas);
                break;
            case R.id.nav_tdah:
                startActivity(inTdah);
                break;
            case R.id.nav_about:
                startActivity(inInfo);
                break;
            case R.id.nav_close:
                finish();
                break;
            default:
                throw new IllegalStateException("Valor inesperado: " + id);
        }
        //Cierra la caja de navegación
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void prevMonth() {

    }

    @Override
    public void nextMonth() {

    }

    @Override
    public void dateOnClick(Day day, int position) {
        if (calendario.isDaySelected(position)) {
            calendario.removeDaySeleted(position);
        } else {
            calendario.addDaySelected(position);
        }
    }

    @Override
    public void dateOnLongClick(Day day, int position) {

    }
}
