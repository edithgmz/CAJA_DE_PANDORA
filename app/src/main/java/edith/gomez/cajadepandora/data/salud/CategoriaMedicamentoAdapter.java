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
import edith.gomez.cajadepandora.data.BaseDatos;

public class CategoriaMedicamentoAdapter extends ArrayAdapter<CategoriaMedicamento> {
    private Context context;
    private int iLayout;
    private ArrayList<CategoriaMedicamento> alDatos;

    public CategoriaMedicamentoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<CategoriaMedicamento> objects) {
        super(context, resource, objects);
        this.context = context;
        iLayout = resource;
        alDatos = objects;
    }

    @SuppressLint("DefaultLocale") @NonNull @Override public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView categMedNombre, categMedCantidad;
        View vLayout = convertView;
        //Si el layout no existe lo crea
        if (vLayout == null) {
            LayoutInflater liCrearLayout = ((Activity) context).getLayoutInflater();
            vLayout = liCrearLayout.inflate(iLayout, parent, false);
        }
        //Vincular componentes
        categMedNombre = vLayout.findViewById(R.id.categMedNombre);
        categMedCantidad = vLayout.findViewById(R.id.categMedCantidad);
        //Crea lista de notas con los datos obtenidos del constructor
        ArrayList<CategoriaMedicamento> alCategsMedicamento = alDatos;
        //Verifica que la lista no se encuentre vacía
        if (alCategsMedicamento != null) {
            //Se obtienen los datos de la lista
            String nombre = alCategsMedicamento.get(position).getNombre();
            String[] categoria = new String[]{nombre};
            int cantidad;
            BaseDatos baseDatos = new BaseDatos(context);
            cantidad = baseDatos.cantidadMedsPorCateg(categoria);
            baseDatos.close();
            //Se colocan los datos en los componentes
            categMedNombre.setText(nombre);
            categMedCantidad.setText(String.format("%d", cantidad));
        }
        //Devuelve el layout creado
        return vLayout;
    }
}
