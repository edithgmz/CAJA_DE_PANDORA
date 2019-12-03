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

public class IngresoAdapter extends ArrayAdapter<Ingreso> {
    private Context context;
    private int iLayout;
    private ArrayList<Ingreso> alDatos;

    public IngresoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Ingreso> objects) {
        super(context, resource, objects);
        this.context = context;
        iLayout = resource;
        alDatos = objects;
    }

    @NonNull @Override public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView ingresoCantidad, ingresoFecha, ingresoCategoria;
        View vLayout = convertView;
        //Si el layout no existe lo crea
        if (vLayout == null) {
            LayoutInflater liCrearLayout = ((Activity) context).getLayoutInflater();
            vLayout = liCrearLayout.inflate(iLayout, parent, false);
        }
        //Vincular componentes
        ingresoCantidad = vLayout.findViewById(R.id.ingresoCantidad);
        ingresoFecha = vLayout.findViewById(R.id.ingresoFecha);
        ingresoCategoria = vLayout.findViewById(R.id.ingresoCategoria);
        //Crea lista de notas con los datos obtenidos del constructor
        ArrayList<Ingreso> alIngresos = alDatos;
        //Verifica que la lista no se encuentre vacía
        if (alIngresos != null) {
            //Se obtienen los datos de la lista
            float cant = alIngresos.get(position).getCantidadIngreso();
            String cantidad;
            String fecha = alIngresos.get(position).getFechaIngreso();
            String categoria = alIngresos.get(position).getCategoriaIngreso();
            //Se colocan los datos en los componentes
            if (cant <= 9) {
                cantidad = "$0" + cant;
            } else {
                cantidad = "$" + cant;
            }
            ingresoCantidad.setText(cantidad);
            ingresoFecha.setText(fecha);
            ingresoCategoria.setText(categoria);
        }
        //Devuelve el layout creado
        return vLayout;
    }
}
