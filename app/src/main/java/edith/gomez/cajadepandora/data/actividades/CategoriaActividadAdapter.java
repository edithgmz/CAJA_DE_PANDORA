package edith.gomez.cajadepandora.data.actividades;
/*
 * Created by Edith on 18-May-19.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import edith.gomez.cajadepandora.R;

public class CategoriaActividadAdapter extends ArrayAdapter<CategoriaActividad> {
    private Context context;
    private int iLayout;
    private ArrayList<CategoriaActividad> alDatos;

    public CategoriaActividadAdapter(@NonNull Context context, int resource, @NonNull ArrayList<CategoriaActividad> objects) {
        super(context, resource, objects);
        this.context = context;
        iLayout = resource;
        alDatos = objects;
    }

    @SuppressLint("DefaultLocale") @NonNull @Override public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        RadioButton categoriaActividad;
        View vLayout = convertView;
        //Si el layout no existe lo crea
        if (vLayout == null) {
            LayoutInflater liCrearLayout = ((Activity) context).getLayoutInflater();
            vLayout = liCrearLayout.inflate(iLayout, parent, false);
        }
        //Vincular componentes
        categoriaActividad = vLayout.findViewById(R.id.categoriaActividad);
        //Crea lista de notas con los datos obtenidos del constructor
        ArrayList<CategoriaActividad> alCategsActividad = alDatos;
        //Verifica que la lista no se encuentre vacía
        if (alCategsActividad != null) {
            //Se obtienen los datos de la lista
            String color = alCategsActividad.get(position).getColor();
            String nombre = alCategsActividad.get(position).getNombre();
            //Se colocan los datos en los componentes
            switch (nombre) {
                case "verde":
                    categoriaActividad.setBackgroundColor(ContextCompat.getColor(context, R.color.colorCategoria1));
                case "amarillo":
                    categoriaActividad.setBackgroundColor(ContextCompat.getColor(context, R.color.colorCategoria2));
                case "rojo":
                    categoriaActividad.setBackgroundColor(ContextCompat.getColor(context, R.color.colorCategoria3));
            }
            categoriaActividad.setBackgroundColor(Color.parseColor(color));
        }
        //Devuelve el layout creado
        return vLayout;
    }
}
