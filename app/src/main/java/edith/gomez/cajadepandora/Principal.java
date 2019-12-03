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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import edith.gomez.cajadepandora.data.BaseDatos;
import edith.gomez.cajadepandora.data.notas.Nota;
import edith.gomez.cajadepandora.data.notas.NotaAdapter;
import edith.gomez.cajadepandora.ui.actividades.Actividades;
import edith.gomez.cajadepandora.ui.alarmas.Alarmas;
import edith.gomez.cajadepandora.ui.extras.Info;
import edith.gomez.cajadepandora.ui.extras.Tdah;
import edith.gomez.cajadepandora.ui.finanzas.Finanzas;
import edith.gomez.cajadepandora.ui.notas.CrearNota;
import edith.gomez.cajadepandora.ui.notas.Notas;
import edith.gomez.cajadepandora.ui.salud.Salud;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ListView.OnItemClickListener, OneCalendarView.OnCalendarChangeListener,
                   OneCalendarView.OneCalendarClickListener {
    private DrawerLayout drawer;
    private Intent inAlarmas, inNotas, inActividades, inSalud, inFinanzas, inTdah, inInfo, inCrearNota1, inCrearNota2;
    private OneCalendarView calendario;
    private ListView lvNotasPpal;
    private ArrayList<Nota> lstNotas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        //Vincular componentes
        Toolbar toolbarPrincipal = findViewById(R.id.toolbarPrincipal);
        NavigationView navigation = findViewById(R.id.navigation);
        FloatingActionButton fabNotaPpal = findViewById(R.id.fabNotaPpal);
        lvNotasPpal = findViewById(R.id.lvNotasPpal);
        calendario = findViewById(R.id.calendario);
        drawer = findViewById(R.id.drawer_layout);
        //Toolbar
        setSupportActionBar(toolbarPrincipal);
        //Navigation
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbarPrincipal, R.string.nav_drawer_open, R.string.nav_drawer_close);
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
        inCrearNota1 = new Intent(this, CrearNota.class);
        inCrearNota2 = new Intent(this, CrearNota.class);
        //Botón flotante
        fabNotaPpal.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(inCrearNota1);
            }
        });
        //Calendario
        calendario.setOnCalendarChangeListener(this);
        calendario.setOneCalendarClickListener(this);
        //Lista de notas
        lvNotasPpal.setOnItemClickListener(this);
    }

    @Override protected void onResume() {
        super.onResume();
        //Base de datos
        BaseDatos baseDatos = new BaseDatos(this);
        lstNotas = baseDatos.datosNota();
        lvNotasPpal.setAdapter(new NotaAdapter(this, R.layout.layout_nota, lstNotas));
        baseDatos.close();
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
            Toast.makeText(this, "Configuración de la aplicación aún no disponible.", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Envía el contenido de la nota seleccionada a la actividad Crear Nota
        Bundle bDatosNota = new Bundle();
        bDatosNota.putString("TITULO", lstNotas.get(position).getTitulo());
        bDatosNota.putString("CONTENIDO", lstNotas.get(position).getContenido());
        bDatosNota.putByteArray("IMAGEN", lstNotas.get(position).getImagen());
        bDatosNota.putByteArray("AUDIO", lstNotas.get(position).getAudio());
        bDatosNota.putByteArray("ESTADO_EMOCIONAL", lstNotas.get(position).getEstadoEmocional());
        bDatosNota.putString("FECHA", lstNotas.get(position).getFecha());
        inCrearNota2.putExtras(bDatosNota);
        startActivity(inCrearNota2);
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
        //Obtener fecha seleccionada del calendario
        Calendar calendar = Calendar.getInstance();
        Date date = day.getDate();
        calendar.setTime(date);
        int anio = calendario.getYear();
        int diaNum = calendar.get(Calendar.DAY_OF_MONTH);
        String mes = calendario.getStringMonth(calendario.getMonth()).substring(0, 3).toLowerCase();
        String[] fecha = {diaNum + " " + mes + " " + anio};
        //Base de datos
        BaseDatos baseDatos = new BaseDatos(this);
        //Realizar acción dependiendo si un día ha sido seleccionado o no
        if (calendario.isDaySelected(position)) {
            //Quitar color del día seleccionado y mostrar lista completa de notas
            calendario.removeDaySeleted(position);
            lstNotas = baseDatos.datosNota();
            baseDatos.close();
            lvNotasPpal.setAdapter(new NotaAdapter(this, R.layout.layout_nota, lstNotas));
        } else {
            //Poner color en día seleccionado y mostrar lista filtrada de notas
            calendario.addDaySelected(position);
            lstNotas = baseDatos.notasPorFecha(fecha);
            baseDatos.close();
            lvNotasPpal.setAdapter(new NotaAdapter(this, R.layout.layout_nota, lstNotas));
        }
    }

    @Override
    public void dateOnLongClick(Day day, int position) {

    }
}
