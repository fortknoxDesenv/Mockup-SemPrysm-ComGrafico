package com.anyvision.facekeyexample.activities.logged;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.anyvision.facekeyexample.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ChamadoGraficoBarraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamado_grafico_barra);

        //pega os valores do grafico
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int size = sharedPreferences.getInt("chamado_grafico_grupos_size", MODE_PRIVATE);
        final ArrayList<String> listaGestaoControle = new ArrayList<String>(size);

        for (int i = 0; i < size; i++) {
            listaGestaoControle.add(sharedPreferences.getString("chamado_grafico_grupos" + "_" + i, null));
        }

        BarChart barChart = findViewById(R.id.pie_chart_barra);

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setDrawGridBackground(true);

        ArrayList<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(1, 40f));
        entries.add(new BarEntry(2, 44f));
        entries.add(new BarEntry(3, 30f));
        entries.add(new BarEntry(4, 36f));
        entries.add(new BarEntry(5, 48f));


        BarDataSet barDataSet = new BarDataSet(entries, "depenses");
        barDataSet.setColors(ColorTemplate.PASTEL_COLORS);
        barDataSet.setValueTextColor(Color.WHITE);
        barChart.setGridBackgroundColor(Color.parseColor("#252526"));



        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.9f);

        barChart.animateXY(2000,3000);
        barChart.setData(data);

//        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
//        dataSets.add((IBarDataSet) depenses);
//        BarData Data = new BarData(dataSets);
//        barChart.setData(Data);



        String[] months = new String[]{"Jan", "Fev", "Marc", "April", "May", "Jun"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(months));
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);

//        xAxis.setAxisLineColor(Color.BLUE);
        xAxis.setTextColor(Color.WHITE);
//        xAxis.setGridColor(Color.YELLOW);

        YAxis yAxisLeft = barChart.getAxisLeft();
//        yAxisLeft.setAxisLineColor(Color.BLUE);
        yAxisLeft.setTextColor(Color.WHITE);

        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setTextColor(Color.WHITE);





    }

    public class MyXAxisValueFormatter implements IAxisValueFormatter {
        private String[] mValues;
        public MyXAxisValueFormatter(String[] values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int) value];
        }
    }


    public static void startActivity(Context from) {
        Intent intent = new Intent(from, ChamadoGraficoBarraActivity.class);
        from.startActivity(intent);
    }

    public void onBackPressed() {
        ChamadoGraficoGruposActivity.startActivity(ChamadoGraficoBarraActivity.this);
        //finish();
    }

}


