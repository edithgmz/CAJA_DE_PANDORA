package edith.gomez.cajadepandora.data.finanzas;
/*
 * Created by Edith on 18-May-19.
 */

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

public class GastoAdapter extends ArrayAdapter<Gasto> {
    private Context context;
    private int iLayout;
    private ArrayList<Gasto> alDatos;

    public GastoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Gasto> objects) {
        super(context, resource, objects);
        this.context = context;
        iLayout = resource;
        alDatos = objects;
    }

    @NonNull @Override public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView gastoCantidad, gastoFecha, gastoCategoria;
        View vLayout = convertView;
        //Si el layout no existe lo crea
        if (vLayout == null) {
            LayoutInflater liCrearLayout = ((Activity) context).getLayoutInflater();
            vLayout = liCrearLayout.inflate(iLayout, parent, false);
        }
        //Vincular componentes
        gastoCantidad = vLayout.findViewById(R.id.gastoCantidad);
        gastoFecha = vLayout.findViewById(R.id.gastoFecha);
        gastoCategoria = vLayout.findViewById(R.id.gastoCategoria);
        //Crea lista de notas con los datos obtenidos del constructor
        ArrayList<Gasto> alGastos = alDatos;
        //Verifica que la lista no se encuentre vacía
        if (alGastos != null) {
            //Se obtienen los datos de la lista
            float cant = alGastos.get(position).getCantidadGasto();
            String cantidad;
            String fecha = alGastos.get(position).getFechaGasto();
            String categoria = alGastos.get(position).getCategoriaGasto();
            //Se colocan los datos en los componentes
            if (cant <= 9) {
                cantidad = "$0" + cant;
            } else {
                cantidad = "$" + cant;
            }
            gastoCantidad.setText(cantidad);
            gastoFecha.setText(fecha);
            gastoCategoria.setText(categoria);
        }
        //Devuelve el layout creado
        return vLayout;
    }
}
