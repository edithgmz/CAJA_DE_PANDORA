package edith.gomez.cajadepandora.data.finanzas;
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

public class CategoriaGastoAdapter extends ArrayAdapter<CategoriaGasto> {
    private Context context;
    private int iLayout;
    private ArrayList<CategoriaGasto> alDatos;

    public CategoriaGastoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<CategoriaGasto> objects) {
        super(context, resource, objects);
        this.context = context;
        iLayout = resource;
        alDatos = objects;
    }

    @SuppressLint("DefaultLocale") @NonNull @Override public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView categoriaGasto;
        View vLayout = convertView;
        //Si el layout no existe lo crea
        if (vLayout == null) {
            LayoutInflater liCrearLayout = ((Activity) context).getLayoutInflater();
            vLayout = liCrearLayout.inflate(iLayout, parent, false);
        }
        //Vincular componentes
        categoriaGasto = vLayout.findViewById(R.id.categoriaGasto);
        //Crea lista de notas con los datos obtenidos del constructor
        ArrayList<CategoriaGasto> alCategsGasto = alDatos;
        //Verifica que la lista no se encuentre vacía
        if (alCategsGasto != null) {
            //Se obtienen los datos de la lista
            String nombre = alCategsGasto.get(position).getNombre();
            //Se colocan los datos en los componentes
            categoriaGasto.setText(nombre);
        }
        //Devuelve el layout creado
        return vLayout;
    }
}
