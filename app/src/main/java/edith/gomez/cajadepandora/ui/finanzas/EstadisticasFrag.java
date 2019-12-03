package edith.gomez.cajadepandora.ui.finanzas;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

import edith.gomez.cajadepandora.R;
import edith.gomez.cajadepandora.data.BaseDatos;
import edith.gomez.cajadepandora.data.finanzas.Finanzas;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstadisticasFrag extends Fragment implements OnChartValueSelectedListener {
    private Context context;
    private PieChart graficaPastel;

    public EstadisticasFrag() {
        //Constructor público vacío requerido
    }

    @Override public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Crea el layout del fragmento
        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_estadisticas, container, false);
        //Vincular componentes
        graficaPastel = relativeLayout.findViewById(R.id.graficaPastel);
        //Gráfica
        graficaPastel.getDescription().setEnabled(false);
        graficaPastel.setExtraOffsets(5, 10, 5, 5);
        graficaPastel.setDragDecelerationFrictionCoef(0.95f);
        graficaPastel.setRotationAngle(0);
        graficaPastel.setRotationEnabled(true);
        graficaPastel.setHighlightPerTapEnabled(true);
        graficaPastel.setDrawHoleEnabled(false);
        graficaPastel.animateY(1400, Easing.EaseInOutQuad);
        graficaPastel.setEntryLabelTextSize(0f);
        graficaPastel.setOnChartValueSelectedListener(this);
        //Etiquetas
        Legend legend = graficaPastel.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setTextSize(14f);
        legend.setDrawInside(false);
        legend.setXEntrySpace(5f);
        legend.setYEntrySpace(5f);
        legend.setYOffset(0f);
        //Devuelve el layout del fragmento
        return relativeLayout;
    }

    @Override public void onResume() {
        super.onResume();
        //Base de datos
        BaseDatos baseDatos = new BaseDatos(context);
        ArrayList<Finanzas> lstFinanzas = baseDatos.datosFinanzas();
        int cantidadFinanzas = baseDatos.cantidadGastos() + baseDatos.cantidadIngresos();
        baseDatos.close();
        //Indicar cantidad y datos a graficar
        establecerDatos(cantidadFinanzas, lstFinanzas);
    }

    private void establecerDatos(int cantidad, ArrayList<Finanzas> datos) {
        ArrayList<PieEntry> alDatosFinanzas = new ArrayList<>();
        ArrayList<Integer> alColoresFinanzas = new ArrayList<>();
        //Obtener datos
        for (int i = 0; i < cantidad; i++) {
            alDatosFinanzas.add(new PieEntry(datos.get(i).getCantidad(), datos.get(i).getCategoria()));
        }
        //Establecer sets de datos
        PieDataSet setFinanzas = new PieDataSet(alDatosFinanzas, "");
        setFinanzas.setDrawIcons(false);
        setFinanzas.setSliceSpace(3f);
        setFinanzas.setIconsOffset(new MPPointF(0, 40));
        setFinanzas.setSelectionShift(5f);
        //Establecer colores
        for (int c : ColorTemplate.PASTEL_COLORS) {
            alColoresFinanzas.add(c);
        }
        for (int c : ColorTemplate.VORDIPLOM_COLORS) {
            alColoresFinanzas.add(c);
        }
        for (int c : ColorTemplate.JOYFUL_COLORS) {
            alColoresFinanzas.add(c);
        }
        for (int c : ColorTemplate.LIBERTY_COLORS) {
            alColoresFinanzas.add(c);
        }
        for (int c : ColorTemplate.COLORFUL_COLORS) {
            alColoresFinanzas.add(c);
        }
        for (int c : ColorTemplate.MATERIAL_COLORS) {
            alColoresFinanzas.add(c);
        }
        setFinanzas.setColors(alColoresFinanzas);
        //Establecer datos en la gráfica
        PieData datosFinanzas = new PieData(setFinanzas);
        datosFinanzas.setValueTextSize(14f);
        datosFinanzas.setValueTextColor(Color.BLACK);
        graficaPastel.setData(datosFinanzas);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
