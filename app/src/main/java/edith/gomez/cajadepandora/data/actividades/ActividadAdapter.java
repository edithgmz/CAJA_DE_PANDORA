package edith.gomez.cajadepandora.data.actividades;
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
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import edith.gomez.cajadepandora.R;

public class ActividadAdapter extends ArrayAdapter<Actividad> {
    private Context context;
    private int iLayout;
    private ArrayList<Actividad> alDatos;

    public ActividadAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Actividad> objects) {
        super(context, resource, objects);
        this.context = context;
        iLayout = resource;
        alDatos = objects;
    }

    @SuppressLint("DefaultLocale") @NonNull @Override public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView actividadCategoria, actividadNombre, actividadFecha;
        View vLayout = convertView;
        //Si el layout no existe lo crea
        if (vLayout == null) {
            LayoutInflater liCrearLayout = ((Activity) context).getLayoutInflater();
            vLayout = liCrearLayout.inflate(iLayout, parent, false);
        }
        //Vincular componentes
        actividadCategoria = vLayout.findViewById(R.id.actividadCategoria);
        actividadNombre = vLayout.findViewById(R.id.actividadNombre);
        actividadFecha = vLayout.findViewById(R.id.actividadFecha);
        //Crea lista de notas con los datos obtenidos del constructor
        ArrayList<Actividad> alActividades = alDatos;
        //Verifica que la lista no se encuentre vacía
        if (alActividades != null) {
            //Se obtienen los datos de la lista
            String categoria = alActividades.get(position).getCategoria();
            String nombre = alActividades.get(position).getNombre();
            String fecha = alActividades.get(position).getFecha();
            int hora = alActividades.get(position).getHora();
            int minutos = alActividades.get(position).getMinutos();
            String fechaHora;
            //Se colocan los datos en los componentes
            switch (categoria) {
                case "verde":
                    actividadCategoria.setBackgroundColor(ContextCompat.getColor(context, R.color.colorCategoria1));
                    break;
                case "amarillo":
                    actividadCategoria.setBackgroundColor(ContextCompat.getColor(context, R.color.colorCategoria2));
                    break;
                case "rojo":
                    actividadCategoria.setBackgroundColor(ContextCompat.getColor(context, R.color.colorCategoria3));
                    break;
            }
            actividadNombre.setText(nombre);
            if (hora <= 9 && minutos <= 9) {
                fechaHora = fecha + ", 0" + hora + ":0" + minutos;
            } else if (hora > 9 && minutos <= 9) {
                fechaHora = fecha + ", " + hora + ":0" + minutos;
            } else if (hora <= 9) {
                fechaHora = fecha + ", 0" + hora + ":" + minutos;
            } else {
                fechaHora = fecha + ", " + hora + ":" + minutos;
            }
            actividadFecha.setText(fechaHora);
        }
        //Devuelve el layout creado
        return vLayout;
    }
}
