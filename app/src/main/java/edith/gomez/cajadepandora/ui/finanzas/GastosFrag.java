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
import edith.gomez.cajadepandora.data.finanzas.Gasto;
import edith.gomez.cajadepandora.data.finanzas.GastoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class GastosFrag extends Fragment implements ListView.OnItemClickListener {
    private Context context;
    private Intent inCrearGasto;
    private ListView lvGastos;
    private ArrayList<Gasto> lstGastos = new ArrayList<>();

    public GastosFrag() {
        // Required empty public constructor
    }

    @Override public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Crear vista del fragmento
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_gastos, container, false);
        //Vincular componentes
        lvGastos = linearLayout.findViewById(R.id.lvGastos);
        //Intento a crear gasto
        inCrearGasto = new Intent(context, CrearGasto.class);
        //Lista de gastos
        lvGastos.setOnItemClickListener(this);
        //Devolver vista del fragmento
        return linearLayout;
    }

    @Override public void onResume() {
        super.onResume();
        //Base de datos
        BaseDatos baseDatos = new BaseDatos(context);
        lstGastos = baseDatos.datosGasto();
        lvGastos.setAdapter(new GastoAdapter(context, R.layout.layout_gasto, lstGastos));
        baseDatos.close();
    }

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Envía el contenido de la actividad seleccionada a la actividad Crear Gasto
        Bundle bDatosGasto = new Bundle();
        bDatosGasto.putFloat("CANTIDAD", lstGastos.get(position).getCantidadGasto());
        bDatosGasto.putString("FECHA", lstGastos.get(position).getFechaGasto());
        bDatosGasto.putString("CATEGORIA", lstGastos.get(position).getCategoriaGasto());
        bDatosGasto.putString("NOTA", lstGastos.get(position).getNotaGasto());
        inCrearGasto.putExtras(bDatosGasto);
        startActivity(inCrearGasto);
    }
}
