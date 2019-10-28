package edith.gomez.cajadepandora.data.notas;
/*
 * Created by Edith on 18-May-19.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import edith.gomez.cajadepandora.R;

public class EstadoEmocionalAdapter extends ArrayAdapter<EstadoEmocional> {
        private Context context;
        private int iLayout;
        private EstadoEmocional[] aeeDatos;

    public EstadoEmocionalAdapter(Context context, int resource, EstadoEmocional[] objects) {
            super(context, resource, objects);
            this.context = context;
            iLayout = resource;
            aeeDatos = objects;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ImageView estadoEmocional;
            View vLayout = convertView;
            //Si el layout no existe lo crea
            if (vLayout == null) {
                LayoutInflater liCreaLayout = ((Activity) context).getLayoutInflater();
                vLayout = liCreaLayout.inflate(iLayout, parent, false);
            }
            //Se vincula el ImageView, se obtiene la imagen del arreglo y se coloca en el ImageView
            estadoEmocional = vLayout.findViewById(R.id.estadoEmocional);
            EstadoEmocional eeImagen = aeeDatos[position];
            estadoEmocional.setImageResource(eeImagen.getImg());
            //Devuelve el layout creado
            return vLayout;
        }
}
