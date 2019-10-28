package edith.gomez.cajadepandora.ui.finanzas;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

import edith.gomez.cajadepandora.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstadisticasFrag extends Fragment implements OnChartValueSelectedListener {
    private PieChart graficaPastel;

    public EstadisticasFrag() {
        //Constructor público vacío requerido
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Crea el layout del fragmento
        RelativeLayout rl = (RelativeLayout) inflater.inflate(R.layout.fragment_estadisticas, container, false);
        //Vincular componentes
        graficaPastel = rl.findViewById(R.id.graficaPastel);
        //Gráfica estadísticas
        graficaPastel.getDescription().setEnabled(false);

        graficaPastel.setUsePercentValues(true);

        graficaPastel.setExtraOffsets(5, 10, 5, 5);

        graficaPastel.setDragDecelerationFrictionCoef(0.95f);

        graficaPastel.setCenterText(generarTextoCentral());

        graficaPastel.setDrawHoleEnabled(true);
        graficaPastel.setDrawCenterText(true);

        graficaPastel.setHoleColor(Color.WHITE);
        graficaPastel.setHoleRadius(58f);

        graficaPastel.setTransparentCircleColor(Color.WHITE);
        graficaPastel.setTransparentCircleAlpha(110);
        graficaPastel.setTransparentCircleRadius(61f);

        graficaPastel.setRotationAngle(0);
        graficaPastel.setRotationEnabled(true);

        graficaPastel.setHighlightPerTapEnabled(true);

        graficaPastel.animateY(1400, Easing.EaseInOutQuad);

        graficaPastel.setEntryLabelColor(Color.BLACK);
        graficaPastel.setEntryLabelTextSize(12f);

        graficaPastel.setOnChartValueSelectedListener(this);

        Legend l = graficaPastel.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        establecerDatos(10, 5);
        //Devuelve el layout del fragmento
        return rl;
    }

    private void establecerDatos(int cuenta, float rango) {
        ArrayList<PieEntry> alDatos = new ArrayList<>();
        ArrayList<Integer> alColores = new ArrayList<>();
        //Datos de ejemplificación
        for (int i = 0; i < cuenta; i++) {
            alDatos.add(new PieEntry((float) (Math.random() * rango) + rango / 5, "Ejemplo " + (i + 1)));
        }
        PieDataSet pds = new PieDataSet(alDatos, "Ejemplo");
        pds.setDrawIcons(false);
        pds.setSliceSpace(3f);
        pds.setIconsOffset(new MPPointF(0, 40));
        pds.setSelectionShift(5f);
        for (int c : ColorTemplate.VORDIPLOM_COLORS) {
            alColores.add(c);
        }
        for (int c : ColorTemplate.JOYFUL_COLORS) {
            alColores.add(c);
        }
        for (int c : ColorTemplate.COLORFUL_COLORS) {
            alColores.add(c);
        }
        for (int c : ColorTemplate.LIBERTY_COLORS) {
            alColores.add(c);
        }
        for (int c : ColorTemplate.PASTEL_COLORS) {
            alColores.add(c);
        }
        alColores.add(ColorTemplate.getHoloBlue());
        pds.setColors(alColores);
        PieData pd = new PieData(pds);
        pd.setValueFormatter(new PercentFormatter(graficaPastel));
        pd.setValueTextSize(11f);
        pd.setValueTextColor(Color.BLACK);
        graficaPastel.setData(pd);
    }

    private SpannableString generarTextoCentral() {
        SpannableString ss = new SpannableString("Gráfica\nestadística de ejemplo");
        ss.setSpan(new RelativeSizeSpan(1.8f), 0, 7, 0);
        ss.setSpan(new StyleSpan(Typeface.NORMAL), 7, ss.length() - 8, 0);
        ss.setSpan(new ForegroundColorSpan(Color.DKGRAY), 7, ss.length() - 8, 0);
        ss.setSpan(new RelativeSizeSpan(.9f), 7, ss.length() - 8, 0);
        ss.setSpan(new StyleSpan(Typeface.ITALIC), ss.length() - 7, ss.length(), 0);
        ss.setSpan(new ForegroundColorSpan(Color.BLACK), ss.length() - 7, ss.length(), 0);
        return ss;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
