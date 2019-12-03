package edith.gomez.cajadepandora.data.alarmas;
/*
 * Created by Edith on 18-May-19.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import edith.gomez.cajadepandora.R;

public class AlarmaAdapter extends ArrayAdapter<Alarma> {
    private Context context;
    private int iLayout;
    private ArrayList<Alarma> alDatos;

    public AlarmaAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Alarma> objects) {
        super( context, resource, objects );
        this.context = context;
        iLayout = resource;
        alDatos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView alarmaHora, alarmaNombre, alarmaDias;
        Switch alarmaActiva;
        View vLayout = convertView;
        //Si el layout no existe lo crea
        if(vLayout == null){
            LayoutInflater liCrearLayout = ((Activity) context).getLayoutInflater();
            vLayout = liCrearLayout.inflate(iLayout, parent, false );
        }
        //Vincular componentes
        alarmaHora = vLayout.findViewById(R.id.alarmaHora);
        alarmaNombre = vLayout.findViewById(R.id.alarmaNombre);
        alarmaDias = vLayout.findViewById(R.id.alarmaDias);
        alarmaActiva = vLayout.findViewById(R.id.alarmaActiva);
        //Crea la lista de alarmas con los datos obtenidos en el constructor
        ArrayList<Alarma> alAlarmas = alDatos;
        //Verifica que la lista no se encuentre vacía
        if(alAlarmas != null){
            //Se obtienen los datos de la lista
            int hora = alAlarmas.get(position).getHora();
            int minutos = alAlarmas.get(position).getMinutos();
            String nombre = alAlarmas.get(position).getNombre();
            String[] dias = alAlarmas.get(position).getDias();
            boolean activa = alAlarmas.get(position).isActiva();
            String horaMinutos;
            //Se colocan los datos en los componentes
            if (hora <= 9 && minutos <= 9) {
                horaMinutos = "0" + hora + ":0" + minutos;
            } else if (hora > 9 && minutos <= 9) {
                horaMinutos = hora + ":0" + minutos;
            } else if (hora <= 9) {
                horaMinutos = "0" + hora + ":" + minutos;
            } else {
                horaMinutos = hora + ":" + minutos;
            }
            alarmaHora.setText(horaMinutos);
            alarmaNombre.setText(nombre);
            if(dias.length != 0){ //Verifica que el arreglo de días no se encuentre vacío
                if(dias[0].equals("todos")){
                    alarmaDias.setText(R.string.alarm_todos_dias);
                } else { //Recorre el arreglo de días y los muestra
                    for (int i = 0, diasLength = dias.length; i < diasLength; i++) {
                        String dia = dias[i];
                        if (!dia.equals("")) {
                            alarmaDias.append(dia);
                            if (i != diasLength - 1) { //Separa con coma los días
                                alarmaDias.append(", ");
                            }
                        }
                    }
                }
            } else { //El arreglo estaba vacío
                alarmaDias.setText(R.string.alarm_una_vez);
            }
            alarmaActiva.setChecked(activa);
        }
        //Devuelve el layout creado
        return vLayout;
    }
}
