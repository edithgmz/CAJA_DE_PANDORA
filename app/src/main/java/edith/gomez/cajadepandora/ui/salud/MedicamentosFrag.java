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
import edith.gomez.cajadepandora.data.salud.Medicamento;
import edith.gomez.cajadepandora.data.salud.MedicamentoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MedicamentosFrag extends Fragment implements ListView.OnItemClickListener {
    private Context context;
    private Intent inCrearMedicamento;
    private ListView lvMedicamentos;
    private ArrayList<Medicamento> lstMedicamentos = new ArrayList<>();

    public MedicamentosFrag() {
        // Required empty public constructor
    }

    @Override public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Crear vista del fragmento
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_medicamentos, container, false);
        //Vincular componentes
        lvMedicamentos = linearLayout.findViewById(R.id.lvMedicamentos);
        //Intento a crear gasto
        inCrearMedicamento = new Intent(context, CrearMedicamento.class);
        //Lista de gastos
        lvMedicamentos.setOnItemClickListener(this);
        //Devolver vista del fragmento
        return linearLayout;
    }

    @Override public void onResume() {
        super.onResume();
        //Base de datos
        BaseDatos baseDatos = new BaseDatos(context);
        lstMedicamentos = baseDatos.datosMedicamento();
        lvMedicamentos.setAdapter(new MedicamentoAdapter(context, R.layout.layout_medicamento, lstMedicamentos));
        baseDatos.close();
    }

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Envía el contenido de la actividad seleccionada a la actividad Crear Medicamento
        Bundle bDatosMedicamento = new Bundle();
        bDatosMedicamento.putString("MEDICAMENTO", lstMedicamentos.get(position).getMedicamento());
        bDatosMedicamento.putString("CATEGORIA", lstMedicamentos.get(position).getCategoria());
        bDatosMedicamento.putInt("DOSIS", lstMedicamentos.get(position).getDosis());
        bDatosMedicamento.putInt("CANTIDAD", lstMedicamentos.get(position).getCantidad());
        bDatosMedicamento.putInt("HORA", lstMedicamentos.get(position).getHora());
        bDatosMedicamento.putInt("MINUTOS", lstMedicamentos.get(position).getMinutos());
        inCrearMedicamento.putExtras(bDatosMedicamento);
        startActivity(inCrearMedicamento);
    }
}
