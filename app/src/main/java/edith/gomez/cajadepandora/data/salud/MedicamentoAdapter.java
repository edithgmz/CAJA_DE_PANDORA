package edith.gomez.cajadepandora.data.salud;
/*
 * Created by Edith on 18-May-19.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import edith.gomez.cajadepandora.R;

public class MedicamentoAdapter extends ArrayAdapter<Medicamento> {
    private Context context;
    private int iLayout;
    private ArrayList<Medicamento> alDatos;

    public MedicamentoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Medicamento> objects) {
        super(context, resource, objects);
        this.context = context;
        iLayout = resource;
        alDatos = objects;
    }

    @SuppressLint("DefaultLocale") @NonNull @Override public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView medicamentoNombre, medicamentoDosis, medicamentoHora, medicamentoCantidad, medicamentoCategoria;
        View vLayout = convertView;
        //Si el layout no existe lo crea
        if (vLayout == null) {
            LayoutInflater liCrearLayout = ((Activity) context).getLayoutInflater();
            vLayout = liCrearLayout.inflate(iLayout, parent, false);
        }
        //Vincular componentes
        medicamentoNombre = vLayout.findViewById(R.id.medicamentoNombre);
        medicamentoDosis = vLayout.findViewById(R.id.medicamentoDosis);
        medicamentoHora = vLayout.findViewById(R.id.medicamentoHora);
        medicamentoCantidad = vLayout.findViewById(R.id.medicamentoCantidad);
        medicamentoCategoria = vLayout.findViewById(R.id.medicamentoCategoria);
        //Crea lista de notas con los datos obtenidos del constructor
        ArrayList<Medicamento> alMedicamentos = alDatos;
        //Verifica que la lista no se encuentre vacía
        if (alMedicamentos != null) {
            //Se obtienen los datos de la lista
            String nombre = alMedicamentos.get(position).getMedicamento();
            int dosis = alMedicamentos.get(position).getDosis();
            int hora = alMedicamentos.get(position).getHora();
            int minutos = alMedicamentos.get(position).getMinutos();
            int cantidad = alMedicamentos.get(position).getCantidad();
            String categoria = alMedicamentos.get(position).getCategoria();
            String horaMinutos;
            //Se colocan los datos en los componentes
            medicamentoNombre.setText(nombre);
            medicamentoDosis.setText(String.format("%d", dosis));
            if (hora <= 9 && minutos <= 9) {
                horaMinutos = "0" + hora + ":0" + minutos;
            } else if (hora > 9 && minutos <= 9) {
                horaMinutos = hora + ":0" + minutos;
            } else if (hora <= 9) {
                horaMinutos = "0" + hora + ":" + minutos;
            } else {
                horaMinutos = hora + ":" + minutos;
            }
            medicamentoHora.setText(horaMinutos);
            medicamentoCantidad.setText(String.format("%d", cantidad));
            medicamentoCategoria.setText(categoria);
        }
        //Devuelve el layout creado
        return vLayout;
    }
}
