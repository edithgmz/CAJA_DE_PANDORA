package edith.gomez.cajadepandora.data.notas;
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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import edith.gomez.cajadepandora.R;

public class NotaAdapter extends ArrayAdapter<Nota> {
        private Context context;
        private int iLayout;
        private ArrayList<Nota> alDatos;

    public NotaAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Nota> objects) {
            super(context, resource, objects);
            this.context = context;
            iLayout = resource;
            alDatos = objects;
        }

        @SuppressLint("DefaultLocale")
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ImageView notaImagen, notaEstadoEmocional;
            TextView notaTitulo, notaExtracto, notaTareas, notaAlarmas, notaSalud, notaFecha;
            View vLayout = convertView;
            //Si el layout no existe lo crea
            if(vLayout == null){
                LayoutInflater liCrearLayout = ((Activity) context).getLayoutInflater();
                vLayout = liCrearLayout.inflate(iLayout, parent, false);
            }
            //Vincular componentes
            notaImagen = vLayout.findViewById(R.id.notaImagen);
            notaEstadoEmocional = vLayout.findViewById(R.id.notaEstadoEmocional);
            notaTitulo = vLayout.findViewById(R.id.notaTitulo);
            notaExtracto = vLayout.findViewById(R.id.notaExtracto);
            notaTareas = vLayout.findViewById(R.id.notaActividades);
            notaAlarmas = vLayout.findViewById(R.id.notaAlarmas);
            notaSalud = vLayout.findViewById(R.id.notaSalud);
            notaFecha = vLayout.findViewById(R.id.notaFecha);
            //Crea lista de notas con los datos obtenidos del constructor
            ArrayList<Nota> alNotas = alDatos;
            //Verifica que la lista no se encuentre vacía
            if(alNotas != null){
                //Se obtienen los datos de la lista
                int imagen = alNotas.get(position).getImagen();
                int actividades = alNotas.get(position).getActividades();
                int alarmas = alNotas.get(position).getAlarmas();
                int salud = alNotas.get(position).getSalud();
                int estado_emocional = alNotas.get(position).getEdoemocional();
                String titulo = alNotas.get(position).getTitulo();
                String contenido = alNotas.get(position).getContenido();
                String fecha = alNotas.get(position).getFecha();
                //Se colocan los datos en los componentes
                notaImagen.setImageResource(imagen);
                notaFecha.setText(fecha);
                notaTitulo.setText(titulo);
                notaExtracto.setText(contenido);
                notaTareas.setText(String.format("%d", actividades));
                notaAlarmas.setText(String.format("%d", alarmas));
                notaSalud.setText(String.format("%d", salud));
                notaEstadoEmocional.setImageResource(estado_emocional);
            }
            //Devuelve el layout creado
            return vLayout;
        }
}
