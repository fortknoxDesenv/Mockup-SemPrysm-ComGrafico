package com.anyvision.facekeyexample.activities.logged;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.anyvision.facekeyexample.R;
import com.anyvision.facekeyexample.models.ChamadoGrafico;
import com.anyvision.facekeyexample.models.GetVariables;
import com.anyvision.facekeyexample.prysm.Authentication;
import com.anyvision.facekeyexample.utils.Enum;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ChamadoGraficoGruposActivity extends AppCompatActivity {

    private static Authentication auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamado_grafico_grupo);

        auth = new Authentication(GetVariables.getInstance().getServerUrl());

        //pega os valores do grafico
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int size = sharedPreferences.getInt("chamado_grafico_grupos_size", MODE_PRIVATE);
        final ArrayList<String> listaGestaoControle = new ArrayList<String>(size);

        for (int i = 0; i < size; i++) {
            listaGestaoControle.add(sharedPreferences.getString("chamado_grafico_grupos" + "_" + i, null));
        }

        PieChart pieChart = findViewById(R.id.pie_chart_grupo);
        pieChart.setUsePercentValues(true);

        Description desc = new Description();
        desc.setText("Total de ocupação");
        desc.setTextSize(20f);

        pieChart.setDescription(desc);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleRadius(25f);
        pieChart.setHoleColor(Color.BLACK);
        pieChart.setNoDataText("Não ha informações no momento");

        List<PieEntry> value = new ArrayList<>();
        for (String li : listaGestaoControle) {
            String nome = li;
            if(nome.length() < 10)
            value.add(new PieEntry(2.5f, nome));
        }

        PieDataSet pieDataSet = new PieDataSet(value, "Ocupação");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(10f);

        Legend legend = pieChart.getLegend();
        legend.setTextColor(Color.WHITE);
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS); //roxo verde azul
//        pieChart.animateXY(1400, 1400);
        pieChart.animateY(3000, Easing.EasingOption.EaseInOutBack);

        pieChart.setOnChartGestureListener(new OnChartGestureListener() {
            @Override
            public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture)
            {

            }

            @Override
            public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture)
            {

            }

            @Override
            public void onChartLongPressed(MotionEvent me)
            {

            }

            @Override
            public void onChartDoubleTapped(MotionEvent me)
            {

            }

            @Override
            public void onChartSingleTapped(MotionEvent me)
            {
             ChamadoGraficoBarraActivity.startActivity(ChamadoGraficoGruposActivity.this);
            }

            @Override
            public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY)
            {
             // Callbacks, em seguida, um gesto de arremesso é feito no gráfico.
            }

            @Override
            public void onChartScale(MotionEvent me, float scaleX, float scaleY)
            {
                /* * Retornos de chamada quando o gráfico é dimensionado / ampliado por meio de gesto de pinça de zoom.*/
            }

            @Override
            public void onChartTranslate(MotionEvent me, float dX, float dY)
            {
            // * Retornos de chamada quando o gráfico é movido / traduzido por meio do gesto de arrastar.
            }
        });
    }

    public static void startActivity(Context from) {
        Intent intent = new Intent(from, ChamadoGraficoGruposActivity.class);
        from.startActivity(intent);
    }

    public void onBackPressed() {
        auth.requestToken(Enum.request.aprovaReprovaExtesao.toString(), Enum.LogarSemSesame.GRAFICO_GESTAO.toString());
        ChamadoGraficoActivity.startActivity(ChamadoGraficoGruposActivity.this);
        //finish();
    }
}