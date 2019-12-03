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
import edith.gomez.cajadepandora.data.BaseDatos;

import static edith.gomez.cajadepandora.data.BaseDatos.byteArrayToImage;

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
            TextView notaTitulo, notaExtracto, notaActividades, notaAlarmas, notaSalud, notaFecha;
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
            notaActividades = vLayout.findViewById(R.id.notaActividades);
            notaAlarmas = vLayout.findViewById(R.id.notaAlarmas);
            notaSalud = vLayout.findViewById(R.id.notaSalud);
            notaFecha = vLayout.findViewById(R.id.notaFecha);
            //Crea lista de notas con los datos obtenidos del constructor
            ArrayList<Nota> alNotas = alDatos;
            //Obtener conteos de la base de datos
            BaseDatos baseDatos = new BaseDatos(context);
            //Verifica que la lista no se encuentre vacía
            if(alNotas != null){
                //Se obtienen los datos de la lista
                String titulo = alNotas.get(position).getTitulo();
                String contenido = alNotas.get(position).getContenido();
                String fecha = alNotas.get(position).getFecha();
                String actividades = baseDatos.cantidadActividades() + "";
                String alarmas = baseDatos.cantidadAlarmas() + "";
                String salud = baseDatos.cantidadSalud() + "";
                byte[] imagen = alNotas.get(position).getImagen();
                byte[] estado_emocional = alNotas.get(position).getEstadoEmocional();
                //Se colocan los datos en los componentes
                notaTitulo.setText(titulo);
                notaExtracto.setText(contenido);
                notaFecha.setText(fecha);
                notaActividades.setText(actividades);
                notaAlarmas.setText(alarmas);
                notaSalud.setText(salud);
                if (imagen != null) {
                    notaImagen.setImageBitmap(byteArrayToImage(imagen));
                }
                if (estado_emocional != null) {
                    notaEstadoEmocional.setImageBitmap(byteArrayToImage(estado_emocional));
                }
            }
            //Devuelve el layout creado
            return vLayout;
        }
}
