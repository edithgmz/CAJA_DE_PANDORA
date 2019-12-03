package edith.gomez.cajadepandora.ui.salud;


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
import edith.gomez.cajadepandora.data.salud.CategoriaMedicamento;
import edith.gomez.cajadepandora.data.salud.CategoriaMedicamentoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriasMedFrag extends Fragment implements ListView.OnItemClickListener {
    private Context context;
    private Intent inCrearCategoriaMed;
    private ListView lvCategoriasMed;
    private ArrayList<CategoriaMedicamento> lstCategoriasMed = new ArrayList<>();

    public CategoriasMedFrag() {
        // Required empty public constructor
    }

    @Override public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Crear vista del fragmento
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_categorias_med, container, false);
        //Vincular componentes
        lvCategoriasMed = linearLayout.findViewById(R.id.lvCategoriasMed);
        //Intento a crear gasto
        inCrearCategoriaMed = new Intent(context, CrearCategoriaMed.class);
        //Lista de gastos
        lvCategoriasMed.setOnItemClickListener(this);
        //Devolver vista del fragmento
        return linearLayout;
    }

    @Override public void onResume() {
        super.onResume();
        //Base de datos
        BaseDatos baseDatos = new BaseDatos(context);
        lstCategoriasMed = baseDatos.datosCategsMedicamento();
        lvCategoriasMed.setAdapter(new CategoriaMedicamentoAdapter(context, R.layout.layout_categoria_medicamento, lstCategoriasMed));
        baseDatos.close();
    }

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Envía el contenido de la actividad seleccionada a la actividad Crear Medicamento
        Bundle bDatosCategoriasMed = new Bundle();
        bDatosCategoriasMed.putString("NOMBRE", lstCategoriasMed.get(position).getNombre());
        inCrearCategoriaMed.putExtras(bDatosCategoriasMed);
        startActivity(inCrearCategoriaMed);
    }
}
