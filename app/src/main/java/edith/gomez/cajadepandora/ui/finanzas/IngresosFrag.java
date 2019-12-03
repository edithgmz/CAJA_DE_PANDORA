package edith.gomez.cajadepandora.ui.finanzas;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import edith.gomez.cajadepandora.R;
import edith.gomez.cajadepandora.data.BaseDatos;
import edith.gomez.cajadepandora.data.finanzas.Ingreso;
import edith.gomez.cajadepandora.data.finanzas.IngresoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngresosFrag extends Fragment implements ListView.OnItemClickListener {
    private Context context;
    private Intent inCrearIngreso;
    private ListView lvIngresos;
    private ArrayList<Ingreso> lstIngresos = new ArrayList<>();

    public IngresosFrag() {
        // Required empty public constructor
    }

    @Override public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Crear vista del fragmento
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_ingresos, container, false);
        //Vincular componentes
        lvIngresos = linearLayout.findViewById(R.id.lvIngresos);
        //Intento a crear gasto
        inCrearIngreso = new Intent(context, CrearIngreso.class);
        //Lista de gastos
        lvIngresos.setOnItemClickListener(this);
        //Devolver vista del fragmento
        return linearLayout;
    }

    @Override public void onResume() {
        super.onResume();
        //Base de datos
        BaseDatos baseDatos = new BaseDatos(context);
        lstIngresos = baseDatos.datosIngreso();
        lvIngresos.setAdapter(new IngresoAdapter(context, R.layout.layout_ingreso, lstIngresos));
        baseDatos.close();
    }

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Envía el contenido de la actividad seleccionada a la actividad Crear Ingreso
        Bundle bDatosIngreso = new Bundle();
        bDatosIngreso.putFloat("CANTIDAD", lstIngresos.get(position).getCantidadIngreso());
        bDatosIngreso.putString("FECHA", lstIngresos.get(position).getFechaIngreso());
        bDatosIngreso.putString("CATEGORIA", lstIngresos.get(position).getCategoriaIngreso());
        bDatosIngreso.putString("NOTA", lstIngresos.get(position).getNotaIngreso());
        inCrearIngreso.putExtras(bDatosIngreso);
        startActivity(inCrearIngreso);
    }
}
